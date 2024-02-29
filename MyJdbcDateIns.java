/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class MyJdbcDateIns {
    public static void main(String [] args)
    {
      Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//SachinKapoor:1521/orcl","advjavabatch","mystudents");
                System.out.println("Connection opened successfully!");
               
                PreparedStatement ps=conn.prepareStatement("Insert into students values(?,?,?)");
 
                
                Scanner kb=new Scanner(System.in);
                System.out.println("Enter rollno:");
                int roll=kb.nextInt();
                 
                System.out.println("Enter name:");
                kb.nextLine();
                String sname=kb.nextLine();

                System.out.println("Enter DOB(dd/MM/yyyy):");
                String datestr=kb.next();
	        
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date d1=sdf.parse(datestr);
              
                long ms=d1.getTime();
                java.sql.Date d2=new java.sql.Date(ms);
                
                ps.setInt( 1,roll);
                ps.setString(2,sname);
                ps.setDate(3, d2);

                int ans=ps.executeUpdate();
                if(ans>0)
                    System.out.println("Rec inserted:"+ans);
                else
                    System.out.println("Cannot insert the record");
                
     }
    catch(ClassNotFoundException cnfe)
    {
        System.out.println("Cannot load the driver class");
        
    }
    catch(SQLException ex)
   {
     System.out.println("Error in DB");
     ex.printStackTrace();       
   }
   catch(ParseException ex)
   {
     System.out.println("Invalid date");
   }
   finally
   {
	try
               {
                      if(conn!=null)
                   {
                      conn.close();
                      System.out.println("Connection closed successfully!");
                 }
              }
              catch(SQLException ex)
              {
                 System.out.println("Problem in closing the connection");
              }
   }
  }
}
