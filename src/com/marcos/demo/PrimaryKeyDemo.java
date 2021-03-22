package com.marcos.demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo
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
            Student tempStudent = new Student("Sean", "Anything", "sean@gmail.com");
            System.out.println("Creating a new student object...");
            Student tempStudent2 = new Student("Tom", "Something", "tom@gmail.com");
            System.out.println("Creating a new student object...");
            Student tempStudent3 = new Student("Jake", "Paul", "jake@gmail.com");
            session.beginTransaction();
            System.out.println("Saving the student...");
            session.save(tempStudent);
            session.save(tempStudent2);
            session.save(tempStudent3);
            session.getTransaction().commit();
            System.out.println("Completed!!");
        }
        finally
        {
            factory.close();
        }
    }
}
