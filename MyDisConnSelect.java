/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class MyDisConnSelect {
    public static void main(String[] args) {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaded successfully");
            com.sun.rowset.CachedRowSetImpl crs=new com.sun.rowset.CachedRowSetImpl();
            crs.setUrl("jdbc:oracle:thin:@//SachinKapoor:1521/orcl");
            crs.setUsername("advjavabatch");
            crs.setPassword("mystudents");
            crs.setCommand("Select bookname,bookprice from allbooks");
            crs.execute();
            while(crs.next())
            {
                String bname=crs.getString(1);
                double amt=crs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
            System.out.println("====================================");
            System.out.println("NOW TRAVERSING BACKWARDS");
            System.out.println("====================================");
            
            while(crs.previous())
            {
                String bname=crs.getString(1);
                double amt=crs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Sorry! Cannot load the driver");
        }
        catch(SQLException ex)
        {
            
        }
    }
}
