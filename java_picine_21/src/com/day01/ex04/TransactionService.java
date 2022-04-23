package com.day01.ex04;

import java.util.UUID;

public class TransactionService {

    private TransactionLinkedList transactions;
    private UserArrayList users;

    TransactionService()
    {
        transactions = new TransactionLinkedList();
        users = new UserArrayList();
    }

    public void addUser(User user) {
        users.addUser(user);
    }

    public double getUsersBalance(int id){
        User user = users.getUserById(id);
        return user.getBalance();
    }

    public boolean transferAmount(int recipientID, int senderID, double amount){
        User sender = users.getUserById(senderID);
        User recipient = users.getUserById(recipientID);

        Transaction t = Transaction.newTransaction(recipient, sender, ETransferCategories.DEBIT, amount);
        transactions.addTransaction(t);
        return true;
    }

    public Transaction[] retrieveTransfers(int id){
        return users.getUserById(id).getTransactions().toArray();
    }

    public boolean removeTransactionFromUser(UUID transactionID, int userID){
        users.getUserById(userID).getTransactions().removeTransactionById(transactionID);
        return true;
    }

    public Transaction[] unpairedTransactions(){
        TransactionLinkedList out = new TransactionLinkedList();
        for (Transaction t: transactions.toArray()) {
            if (!t.getRecipient().getTransactions().contains(t.getIdentifier()))
                out.addTransaction(t);
            else if (!t.getSender().getTransactions().contains(t.getIdentifier()))
                out.addTransaction(t);
        }
        return out.toArray();
    }

}
