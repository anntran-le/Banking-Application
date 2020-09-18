/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author lttran
 */
public class BankAccountSilver extends BankAccountState{
    
    
    //Abstraction Function: a "silver" state object 
    //representing the silver level of a bank account
    //Rep Invariant:
    public BankAccountSilver(){
        super();
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return true if the object's representation is correct,
    //otherwise return false
    @Override
    public boolean repOk(){
        return true;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return the String representation of this object
    @Override
    public String toString(){
        return "silver";
    }
    
    //Requires: s's myState is instance of BankAccountSilver
    //Modifies: s
    //Effects: change s's myState to gold
    @Override
    public void changeState(BankAccount s){
        s.setState(new BankAccountGold());
    }
  
}
