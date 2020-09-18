/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author lttran
 */
public class BankAccountPlatinum extends BankAccountState{
    
    
    //Abstraction Function: a "platinum" state object
    //representing the platinum level of a bank account
    //Rep Invariant:
    public BankAccountPlatinum(){
        super();
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
        return "platinum";
    }
    
    //Requires: s's myState is instance of BankAccountPlatinum
    //Modifies: s
    //Effects: none
    @Override
    public void changeState(BankAccount s){
        
    }
   
}
