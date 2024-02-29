/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class MyDisConnInsert {
    public static void main(String[] args) {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            com.sun.rowset.CachedRowSetImpl crs=new com.sun.rowset.CachedRowSetImpl();
            crs.setUrl("jdbc:oracle:thin:@//SachinKapoor:1521/orcl");
            crs.setUsername("advjavabatch");
            crs.setPassword("mystudents");
            crs.setCommand("Select * from allbooks");
            crs.execute();
            crs.moveToInsertRow();
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter bookid");
            int bid=kb.nextInt();
            
            System.out.println("Enter bookname");
            kb.nextLine();
            String bname=kb.nextLine();
            
            System.out.println("Enter bookprice");
            double amt=kb.nextDouble();
            
            System.out.println("Enter subject");
            kb.nextLine();
            String subject=kb.nextLine();
            
            crs.updateInt(1, bid);
            crs.updateString(2, bname);
            crs.updateDouble(3, amt);
            crs.updateString(4, subject);
            
            crs.insertRow();
            crs.moveToCurrentRow();
            
            crs.acceptChanges();
            System.out.println("Update complete");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Sorry! Cannot load the driver");
        }
        catch(SQLException ex)
        {
            System.out.println("Exception:"+ex);
        }
    }
}
