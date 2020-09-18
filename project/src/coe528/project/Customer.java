/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.io.FileReader;        
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;  

/**
 *
 * @author lttran
 */
public class Customer{
    private String username;
    private String password;
    private BankAccount account;
    
    //Abstraction Function: a customer a who
    //a.username = c.username
    //a.password = c.password
    //Rep Invariant: true if
    //(c.username != null) && (c.password !=null) 
    public Customer(String name, String pass){
        username = name;
        password = pass;
        account = new BankAccount(100.00);
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return true if the object's representation is correct,
    //otherwise return false
    public boolean repOk(){
        if (username == null || password == null){
            return false;
        }else{
            return true;
        }
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return the String representation of this object
    @Override
    public String toString(){
        return "Username: " + username + "\nPassword: " + password;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: if there is no file named with username, return false
    //if there is, return true if password is the same as password in the file, 
    //otherwise return false; also initiates the account using the balance saved in file
    public boolean logIn(){
        File folder = new File("//home//student2//lttran//coe528//project//");
        File[] files = folder.listFiles();
        for (File file : files) 
        {
            if (file.getName().equals(username + ".txt")){
                LineNumberReader lineNumberReader = null;
                try
                {
                   lineNumberReader = new LineNumberReader(new FileReader(file));
                   String line = null;
                   while ((line = lineNumberReader.readLine()) != null)
                   {
                       if (line.equals(password)){
                               line = lineNumberReader.readLine();
                               account = new BankAccount(Double.parseDouble(line));
                               return true;
                       }
                       return false;
                   }

                }
                catch (IOException | NumberFormatException ex)
                {
                    System.out.println("File not found.");
                
                } finally
                {
                   try {
                      if (lineNumberReader != null){
                         lineNumberReader.close();
                      }
                   } catch (IOException ex){
                   }
               }
            }
        }
        return false;
    }
    
    //Requires: none
    //Modifies: System.out
    //Effects:  print "Customer logged out." to System.out
    public void logOut(){
        System.out.println("Customer logged out.");
    }
    
 
    //Requires: none
    //Modifies: account
    //Effects: add d to the balance in account, update the balance in the customer's file
    public void depositMoney(double d) throws IOException{
        account.depositMoney(d);
        System.out.println(account.getBalance());
        FileWriter writer = new FileWriter("//home//student2//lttran//coe528//project//" + username + ".txt");
        writer.write(password);
        writer.write("\n" + account.getBalance());
        writer.close();
    }
    
    //Requires: none
    //Modifies: account
    //Effects: substract d from the balance in account, if successful: 
    //update the balance in the customer's file and return true, otherwise return false
    public boolean withdrawMoney(double d) throws IOException{
        if (account.withdrawMoney(d)){
            FileWriter writer = new FileWriter("//home//student2//lttran//coe528//project//" + username + ".txt");
            writer.write(password);
            writer.write("\n" + account.getBalance());
            writer.close();
            return true;
        }else{
            return false;
        }
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return the balance in account 
    public double getBalance(){
        return account.getBalance();
    }
    
    //Requires: price is a postive double
    //Modifies: account
    //Effects: if price < 50, return false
    // otherwise, return true if purchase is successful and update the balance in the file
    // return false if not
    public boolean purchaseOnline(double price) throws IOException {
        if (price < 50){
            return false;
        }else{
            if (account.purchaseOnline(price)){
                FileWriter writer = new FileWriter("//home//student2//lttran//coe528//project//" + username + ".txt");
                writer.write(password);
                writer.write("\n" + account.getBalance());
                writer.close();
                return true;
            }
            return false;
        }
    }
    
    
}    
    

