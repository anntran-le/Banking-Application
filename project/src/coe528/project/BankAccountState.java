/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author lttran
 */
public abstract class BankAccountState {
    
    //Abstraction Function: an abstract state object
    //representing a bank account's level
    //Rep Invariant: always true
    public BankAccountState(){
        
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return true if the object's representation is correct,
    //otherwise return false
    public boolean repOk(){
        return true;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return the String representation of this object
    @Override
    public String toString(){
        return "level";
    }
    
    //Requires: none
    //Modifies: none
    //Effects: none
    public abstract void changeState(BankAccount s);
   
}
