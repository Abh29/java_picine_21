package com.day01.ex00;

public class Program {
    public static void main(String[] args) {
        User john = User.newUser("John", 2500);
        User mark = User.newUser("Mark", 1200);
        User anna = User.newUser("Anna");
        User err = User.newUser("error", -150);


        System.out.println("users :");
        System.out.println(john);
        System.out.println(mark);
        System.out.println(anna);
        System.out.println(err);

        anna.setBalance(5000);


        System.out.println("transactions : ");
        Transaction t1 = Transaction.newTransaction(mark, anna, ETransferCategories.DEBIT, 1000);
        Transaction t2 = Transaction.newTransaction(john, anna, ETransferCategories.CREDIT, 45000);
        Transaction t3 = Transaction.newTransaction(err, anna, ETransferCategories.CREDIT, 45000);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

    }
}
