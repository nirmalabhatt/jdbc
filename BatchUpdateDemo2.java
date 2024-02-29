/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class BatchUpdateDemo2 {
    public static void main(String [] args)
    {
      Connection conn=null;  
      boolean result=true;
      Scanner kb=new Scanner(System.in);
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//SachinKapoor:1521/orcl","advjavabatch","mystudents");
                System.out.println("Connection opened successfully!");
               
                PreparedStatement ps=conn.prepareStatement("insert into allbooks values(?,?,?,?)");
                conn.setAutoCommit(false);
                String choice;
                do
                {
                    System.out.println("Enter bookid");
                    int bid=kb.nextInt();
                    kb.nextLine();
                    System.out.println("Enter bookname");
                    String bname=kb.nextLine();
                    System.out.println("Enter book price");
                    double amt=kb.nextDouble();
                    System.out.println("Enter subject:");
                    kb.nextLine();
                    String subject=kb.nextLine();
                    ps.setInt(1, bid);
                    ps.setString(2, bname);
                    ps.setDouble(3, amt);
                    ps.setString(4, subject);
                    ps.addBatch();
                    System.out.println("Any more(Yes/No)?");
                    choice=kb.next();
                    
                }while(choice.equalsIgnoreCase("yes"));
                
                
                int[]arr=ps.executeBatch();
                int qno=1;
                for(int x:arr)
                {
                    switch(x)
                    {
                        case Statement.SUCCESS_NO_INFO:
                            System.out.println("Query no "+qno+" effected unknown number of rows");
                            break;
                        case Statement.EXECUTE_FAILED:
                            System.out.println("Query no "+qno+" did not run properly");
                            result=false;
                            break;   
                        default:
                            System.out.println("Query no "+qno+" effected "+x+" rows");
                    }
                    qno++;
                }
                
     }
    catch(ClassNotFoundException cnfe)
    {
        System.out.println("Cannot load the driver class");
        
    }
    catch(BatchUpdateException ex)
    {
        result=false;
        int []arr=ex.getUpdateCounts();
        System.out.println("Query no "+(arr.length+1)+" in the batch generated exception:"+ex.getMessage());
    }
    catch(SQLException ex)
   {
     System.out.println("Error in DB");
     ex.printStackTrace();       
   }
   
   finally
   {
       if(conn!=null)
       {
              try
               {
                      if(result==true)
                      {
                          conn.commit();
                          System.out.println("Transaction committed!");
                      }
                      else
                      {
                          conn.rollback();
                          System.out.println("Transaction rolled bck!");   
                      }
                      conn.close();
                      System.out.println("Connection closed!");
               }
              catch(SQLException ex)
              {
                 System.out.println("Problem in closing the connection");
              }
       }
   }
  }
}
