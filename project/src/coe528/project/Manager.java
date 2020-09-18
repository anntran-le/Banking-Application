/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

/**
 *
 * @author lttran
 */

//Overview:  a Manager class
public class Manager{
    private final String username;
    private final String password; 
    
    //Abstraction Function: a manager m where
    //m.username = c.username
    //m.password = c.password
    //Rep Invariant: true if
    //(c.username == admin) && (c.password == admin) 
    public Manager(){
        username = "admin";
        password = "admin";
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
        return "Username: " + username + " \nPassword: " + password;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: if name and pass are "admin" return true
    //otherwise return false
    public boolean logIn(String name,String pass) throws IOException{
        return (username.equals(name) && password.equals(pass));
    }
    
    //Requires: none
    //Modifies: System.out
    //Effects:  print "Manager logged out." to System.out
    public void logOut(){
        System.out.println("Manager logged out.");
    }
    
    //Requires: name and pass are not empty Strings
    //Modifies: project directory
    //Effects: write a new customer file in the directory using name and pass 
    //and return true if successful
    //return false if file already exists or if unsuccessful
    public boolean addCustomer(String name,String pass) throws IOException{
            File file = new File("//home//student2//lttran//coe528//project//" + name + ".txt");
            if (file.createNewFile()){
                FileWriter writer = new FileWriter(file);
                writer.write(pass);
                writer.write("\n" + 100.00);
                writer.close();
                System.out.println("File created.");
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }
        
    }
    
    //Requires: name is not an empty String
    //Modifies: project directory
    //Effects: find the file named with name and return true if 
    //file deletion is successful, otherwise return false
    public boolean deleteCustomer(String name) throws IOException{
        try
        { 
            if (Files.deleteIfExists(Paths.get("//home//student2//lttran//coe528//project//" + name + ".txt"))){
                //System.out.println("File deletion successful.");
                return true;
            }else{
                //System.out.println("No file to delete.");
                return false;
            } 
        } 
        catch(NoSuchFileException e) 
        { 
            //System.out.println("No such file/directory exists");
            return false;
        } 
        catch(DirectoryNotEmptyException e) 
        { 
            //System.out.println("Directory is not empty.");
            return false;
        } 
        catch(IOException e) 
        { 
            //System.out.println("Invalid permissions.");
            return false;
        } 
          
    }
  
}
    
