/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author dell
 */
public class MyJdbcDateDisplay {
  
        public static void main(String [] args)
    {
      Connection conn=null;  
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//SachinKapoor:1521/orcl","advjavabatch","mystudents");
                System.out.println("Connection opened successfully!");
                
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select ename,hiredate from emp order by empno");
                SimpleDateFormat sdf=new SimpleDateFormat("dd-MMMM-yyyy");
                while(rs.next())
               {
                     String name=rs.getString(1);
                     Date hiredate=rs.getDate(2);
                     String fmtdate=sdf.format(hiredate);
                     System.out.println(name+"\t"+fmtdate);
              }
             
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

