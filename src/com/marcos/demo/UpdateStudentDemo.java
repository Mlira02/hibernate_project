package com.marcos.demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try
        {
            int studentId = 1;
            session =factory.getCurrentSession();
            session.beginTransaction();

            Student myStudent = session.get(Student.class, studentId);
            System.out.println("Student retrieval complete: " + myStudent);

            myStudent.setFirstName("Scooby");
            System.out.println("Changing student first name to: " + myStudent.getFirstName());

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("update Student set email = 'foo@gmail.com'").executeUpdate();
            System.out.println("Updating all emails for students...");

            System.out.println("Tasks have been completed!!");
            session.getTransaction().commit();
        }
        finally
        {
            factory.close();
        }
    }
}