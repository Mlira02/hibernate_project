package com.marcos.demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo
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
            session.beginTransaction();

            System.out.println("<====== All students below =======>");
            List<Student> theStudents = session.createQuery("from Student").getResultList();

            System.out.println("<====== Students list containing Wall below ======>");
            theStudents = session.createQuery("from Student s where s.lastName = 'Scott'").getResultList();
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName = 'anything' or s.firstName = 'Daffy'").getResultList();
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.email LIKE '%yahoo.com'").getResultList();
            displayStudents(theStudents);

            session.getTransaction().commit();
            System.out.println("Tasks Completed!!");
        }
        finally
        {
            System.out.println("Closing factory...");
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents)
    {
        for (Student tempStudent : theStudents)
        {
            System.out.println(tempStudent);
        }
    }
}
