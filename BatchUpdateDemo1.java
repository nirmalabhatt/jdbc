
package myjdbcexamples;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class BatchUpdateDemo1 {
    public static void main(String [] args)
    {
      Connection conn=null;  
      boolean result=true;
      try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                System.out.println("Driver loaded successfully!");
                
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//computername:1521/XE","advjava","students");
                System.out.println("Connection opened successfully!");
               
                Statement st=conn.createStatement();
                conn.setAutoCommit(false);
                st.addBatch("Insert into allbooks values(113,'Learning React',600,'React')");
                st.addBatch("Update student set name='Anuj' where roll=101");
                st.addBatch("Delete from emp where empno>=104");
                int[]arr=st.executeBatch();
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
