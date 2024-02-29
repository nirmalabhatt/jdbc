/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dell
 */
class FolderNotCreatedException extends Exception
{
     public FolderNotCreatedException(String msg) 
     {
         super(msg);
     }
}
public class MyJdbcImgRet {
    public static void main(String[] args) {
        Connection conn=null;  
        
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//SachinKapoor:1521/orcl","advjavabatch","mystudents");
                System.out.println("Connection opened successfully!");
               
                Statement st=conn.createStatement();
                ResultSet rs=st.executeQuery("Select * from movies");
                File mydir=new File("E:/movies");
                if(mydir.exists()==false)
                {
                    boolean result=mydir.mkdir();
                    if(result==false)
                    {
                        throw new FolderNotCreatedException("Could not create the directory");
                     }
                    else
                        System.out.println("Folder created");
                }
                else
                    System.out.println("Folder already present");
                
                while(rs.next())
                {
                    String movname=rs.getString(1);
                    Blob blob=rs.getBlob(2);
                    byte[]arr=blob.getBytes(1, (int)blob.length());
                    FileOutputStream fout=new FileOutputStream(mydir.getAbsolutePath()+"//"+movname+".jpg");
                    fout.write(arr);
                    fout.close();
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
   catch(FileNotFoundException fnf)
   {
       System.out.println("Cannot open the file");
       
   }
   catch(IOException iox)
   {
       System.out.println("Cannot open the file");
       
   }
   catch(FolderNotCreatedException fne)
   {
       System.out.println("Exception:"+fne.getMessage());
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
                     System.out.println("Problem in closing the file");
                  }
               
                   
              }
             
   }
  }
    

