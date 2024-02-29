/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjdbcexamples;

/**
 *
 * @author dell
 */
public class Book {

    @Override
    public String toString() {
        return "Book{" + "bookname=" + bookname + ", oldprice=" + oldprice + ", newprice=" + newprice + '}';
    }

    public Book(String bookname, double oldprice, double newprice) {
        this.bookname = bookname;
        this.oldprice = oldprice;
        this.newprice = newprice;
    }
    private String bookname;
    private double oldprice;
    private double newprice;
}
