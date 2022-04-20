package com.day01.ex03;

import com.day01.ex00.User;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private com.day01.ex00.User recipient;
    private com.day01.ex00.User sender;
    private ETransferCategories transferCategory;
    private double transferAmount;
    private Transaction next = null;


    public static Transaction newTransaction(com.day01.ex00.User recipient, com.day01.ex00.User sender, ETransferCategories transferCategory, double transferAmount)
    {
        if (recipient == null || sender == null)
        {
            System.err.println("Transaction Users can not be a null value!");
            return null;
        }
        if (transferAmount <= 0 || transferAmount > sender.getBalance())
        {
            System.err.println("Transaction couldn't be made!");
            return null;
        }
        return new Transaction(recipient, sender, transferCategory, transferAmount);
    }

    private Transaction(com.day01.ex00.User recipient, com.day01.ex00.User sender, ETransferCategories transferCategory, double transferAmount) {
        this.identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public com.day01.ex00.User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public ETransferCategories getTransferCategory() {
        return transferCategory;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public Transaction getNext() {
        return next;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "identifier=" + identifier +
                ", recipient=" + recipient +
                ", sender=" + sender +
                ", transferCategory=" + transferCategory +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
