package com.marcos.demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo
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
            System.out.println("Creating a new student object...");
            Student tempStudent = new Student("Daffy", "Duck", "daffyD@gmail.com");
            session.beginTransaction();
            System.out.println("Saving the student...");
            System.out.println(tempStudent);
            session.save(tempStudent);
            session.getTransaction().commit();

            System.out.println("Saved student generated ID: " + tempStudent.getId());
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Getting student with id:  " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("get complete: " + myStudent);
            session.getTransaction().commit();
            System.out.println("Completed!!");
        }
        finally
        {
            factory.close();
        }
    }
}
