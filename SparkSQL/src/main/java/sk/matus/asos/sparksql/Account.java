package sk.matus.asos.sparksql;

import java.io.Serializable;

public class Account implements Serializable {

    private String number;
    private double balance;

    public Account() {
    }

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" + "number=" + number + ", balance=" + balance + '}';
    }

    public void addInterest() {
        balance = balance + balance * 0.05;
    }
    
    void addInterest(double d) {
         balance = balance + balance * d;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    } 
}
