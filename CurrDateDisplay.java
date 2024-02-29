/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dell
 */
public class CurrDateDisplay {
    public static void main(String[] args) {
        Date today=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMMM-yyyy");
        String fmtdate=sdf.format(today);
        System.out.println("Current date is:"+fmtdate);
    }
}
