package sk.matus.asos.java8streams;

public class Account {

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
