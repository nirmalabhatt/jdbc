/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author dell
 */
public class DisplayDOB {
    public static void main(String[] args) {
        Scanner kb=new Scanner(System.in);
        System.out.println("Enter your DOB(dd/MM/yyyy)");
        String datestr=kb.next();
        
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date bday=sdf.parse(datestr);
            SimpleDateFormat sdf2=new SimpleDateFormat("EEEE");
            String day=sdf2.format(bday);
            System.out.println("You were born on :"+day);
        }
        catch(ParseException ex)
        {
            System.out.println("Invalid date!");
        }
        
    }
}
