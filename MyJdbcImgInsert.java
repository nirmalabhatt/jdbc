/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class MyJdbcImgInsert {
    public static void main(String[] args) {
        Connection conn=null;  
        FileInputStream fin=null;
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//SachinKapoor:1521/orcl","advjavabatch","mystudents");
                System.out.println("Connection opened successfully!");
               
                PreparedStatement ps=conn.prepareStatement("Insert into movies values(?,?)");
                Scanner kb=new Scanner(System.in);
                System.out.println("Enter path to the image:");
                String imgpath=kb.nextLine();
                File fobj=new File(imgpath);
                String fname=fobj.getName();
                int dotpos=fname.lastIndexOf(".");
                String movname=fname.substring(0,dotpos);
                ps.setString(1, movname);
                fin=new FileInputStream(fobj);
                ps.setBinaryStream(2, fin,(int)fobj.length());
                int result=ps.executeUpdate();
                if(result>0)
                    System.out.println("Record saved!");
                else
                    System.out.println("Cannot save the record");
                
               
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
   catch(FileNotFoundException fnf)
   {
       System.out.println("Cannot open the file");
       
   }
   catch(IOException iox)
   {
       System.out.println("Cannot open the file");
       
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
                      if(fin!=null)
                   {
                      fin.close();
                      System.out.println("File closed successfully!");
                   }
               }
                catch(SQLException ex)
                 {
                     System.out.println("Problem in closing the file");
                  }
                catch(IOException ex)
              {
                 System.out.println("Problem in closing the file");
              }
                   
              }
             
   }
  }
    

