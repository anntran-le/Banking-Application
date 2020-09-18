/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author lttran
 */
public class BankAccountGold extends BankAccountState{
    
    
    //Abstraction Function: a "gold" state object
    //representing the gold level of a bank account
    //Rep Invariant:
    public BankAccountGold(){
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
        return "gold";
    }
    
    //Requires: s's myState is instance of BankAccountGold
    //Modifies: s
    //Effects: change s's myState to platinum
    @Override
    public void changeState(BankAccount s){
        s.setState(new BankAccountPlatinum());
    }
    
   
}
