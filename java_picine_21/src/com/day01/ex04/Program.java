package com.day01.ex04;

import java.util.Arrays;

public class Program {


    public static void main(String[] args) {

        TransactionService transactionService = new TransactionService();



        User john = User.newUser("John", 2500);
        User mark = User.newUser("Mark", 1200);
        User anna = User.newUser("Anna");

        transactionService.addUser(john);
        transactionService.addUser(mark);
        transactionService.addUser(anna);

        anna.setBalance(5000);

        System.out.println("transactions : ");
        transactionService.transferAmount(mark.getID(), anna.getID(), 1000);
        transactionService.transferAmount(john.getID(), anna.getID(), 1000);
        transactionService.transferAmount(mark.getID(), john.getID(), 250);
        transactionService.transferAmount(mark.getID(), john.getID(), 250);
        transactionService.transferAmount(anna.getID(), john.getID(), 300);


        System.out.println("anna's balance : " + transactionService.getUsersBalance(anna.getID()));


        System.out.println("anna's all transactions :");
        Transaction[] annasTransactions = transactionService.retrieveTransfers(anna.getID());
        System.out.println(Arrays.toString(annasTransactions));

        assert (annasTransactions != null);
        transactionService.removeTransactionFromUser(annasTransactions[0].getIdentifier(), anna.getID());

    }

}
