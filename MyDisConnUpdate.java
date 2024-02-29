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
public class MyDisConnUpdate {
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
            while(crs.next())
            {
                double amt=crs.getDouble("bookprice");
                amt=amt+amt*0.1;
                crs.updateDouble("bookprice", amt);
                crs.updateRow();
            }
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
