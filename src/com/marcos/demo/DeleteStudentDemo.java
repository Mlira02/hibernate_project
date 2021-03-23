package com.marcos.demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo
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

            System.out.println("Deleting student with ID number 1");
            session.delete(myStudent);

            System.out.println("deleting student with ID of 2");
            session.createQuery("delete from Student where id = 2").executeUpdate();

            session.getTransaction().commit();
            System.out.println("All tasks complete!!");
        }
        finally
        {
            factory.close();
        }
    }
}