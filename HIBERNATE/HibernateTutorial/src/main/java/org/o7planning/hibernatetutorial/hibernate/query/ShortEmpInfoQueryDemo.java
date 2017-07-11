package org.o7planning.hibernatetutorial.hibernate.query;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.o7planning.hibernatetutorial.hibernate.HibernateUtils;
import org.o7planning.hibernatetutorial.beans.ShortEmpInfo;
import org.o7planning.hibernatetutorial.entities.Employee;
public class ShortEmpInfoQueryDemo {
	  public static void main(String[] args) {
	        SessionFactory factory = HibernateUtils.getSessionFactory();
	 
	        Session session = factory.getCurrentSession();
	 
	        try {
	            session.getTransaction().begin();
	  
	            // Using constructor of ShortEmpInfo
	            String sql = "Select new " + ShortEmpInfo.class.getName()
	                    + "(e.empId, e.empNo, e.empName)" + " from "
	                    + Employee.class.getName() + " e ";
	            
	            System.out.println(sql);
	 
	            Query<ShortEmpInfo> query = session.createQuery(sql);
	  
	             
	            // Execute query.
	            // Get a List of ShortEmpInfo
	            List<ShortEmpInfo> employees = query.getResultList();
	 
	            for (ShortEmpInfo emp : employees) {
	                System.out.println("Emp: " + emp.getEmpNo() + " : "
	                        + emp.getEmpName());
	            }
	  
	            // Commit data.
	            session.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Rollback in case of an error occurred.
	            session.getTransaction().rollback();
	        }
	    }
}
