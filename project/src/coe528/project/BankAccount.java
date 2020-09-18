/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author lttran
 */
public class BankAccount {
    private BankAccountState myState;
    private double balance;
    
    
    //Abstraction Function: a bank account a where
    // a.balance = c.balance
    // a.level =  c.myState
    //Rep Invariant: true if
    // c.myState is one of either "silver", "gold" or "platinum"
    // c.balance is not negative
    public BankAccount(double balance){
        this.balance = balance;
        this.changeState();
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return true if the object's representation is correct,
    //otherwise return false
    public boolean repOk(){
        if ((myState instanceof BankAccountSilver) || (myState instanceof BankAccountGold) || (myState instanceof BankAccountPlatinum) ){
                if (balance >= 0)
                    return true;
            }
        return false;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return the String representation of this object
    @Override
    public String toString(){
        return "Level: " + myState.toString() + " \nBalance: " + balance;
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return myState
    public BankAccountState getState(){
        return myState;
    }
    
    //Requires: s is an instance of BankAccountState
    //Modifies: myState
    //Effects: set myState equal to s
    public void setState(BankAccountState s){
        myState = s;
    }
    
    //Requires: none
    //Modifies: myState
    //Effects: if balance is less than 10000, set myState as BankAccountSIlver
    // if balance is between 10000 and 20000, set myState as BankAccountGold
    // if balance is from 20000 or more, set myState as BankAccountPlatinum
    public void changeState(){
        this.setState(new BankAccountSilver());
        if (balance >= 10000){
            myState.changeState(this);
        }
        if (balance >= 20000){
            myState.changeState(this);
        }
    }
    
    //Requires: d is a positive double
    //Modifies: balance
    //Effects: add d to balance
    //Side effects: change myState according to new balance
    public void depositMoney(double d){
        balance = balance + d;
        this.changeState();
    }
    
    //Requires: d is a positive double
    //Modifies: balance
    //Effects: if d is larger than balance return false,
    // otherwise substract d from balance and return true
    //Side effects: change myState according to new balance
    public boolean withdrawMoney(double d){
        if (d <= balance){
            balance = balance - d;
            this.changeState();
            return true;
        }else{
            return false;
        }
    }
    
    //Requires: none
    //Modifies: none
    //Effects: return balance
    public double getBalance(){
        return balance;
    }
    
    //Requires: d is a positive double
    //Modifies: balance
    //Effects: add d to balance
    //Side effects: change myState according to new balance
    public boolean purchaseOnline(double price){
        if (price + 20 <= balance){
            if (myState instanceof BankAccountSilver){
                balance = balance - price - 20;
            }
            if (myState instanceof BankAccountGold){
                balance = balance - price - 10;
            }
            if (myState instanceof BankAccountPlatinum){
                balance = balance - price;
            }
            this.changeState();
            return true;
        }else{
            return false;
        }
    }
    
}
