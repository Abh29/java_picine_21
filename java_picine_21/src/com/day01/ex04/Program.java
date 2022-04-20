package com.day01.ex04;

public class Program {


    public static void main(String[] args) {

        TransactionList transactions = new TransactionLinkedList();

        User john = User.newUser("John", 2500);
        User mark = User.newUser("Mark", 1200);
        User anna = User.newUser("Anna");


        anna.setBalance(5000);


        System.out.println("transactions : ");
        Transaction t1 = Transaction.newTransaction(mark, anna, ETransferCategories.DEBIT, 1000);
        Transaction t2 = Transaction.newTransaction(john, anna, ETransferCategories.CREDIT, 2300);
        Transaction t3 = Transaction.newTransaction(mark, john, ETransferCategories.CREDIT, 250);
        Transaction t4 = Transaction.newTransaction(mark, john, ETransferCategories.CREDIT, 250);

        transactions.addTransaction(t1);
        transactions.addTransaction(t3);
        transactions.addTransaction(t2);
        transactions.addTransaction(t4);

        System.out.println(transactions);

        assert t3 != null;
        System.out.println(transactions.removeTransactionById(t3.getIdentifier()));

        try{
            transactions.removeTransactionById(t3.getIdentifier());
        }catch (RuntimeException e){
            e.printStackTrace();
        }


        System.out.println(transactions);

    }

}
