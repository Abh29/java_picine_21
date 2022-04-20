package com.day01.ex04;

import java.util.UUID;

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private ETransferCategories transferCategory;
    private double transferAmount;
    private Transaction next = null;


    public static Transaction newTransaction(User recipient, User sender, ETransferCategories transferCategory, double transferAmount)
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

    private Transaction(User recipient, User sender, ETransferCategories transferCategory, double transferAmount) {
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

    public User getRecipient() {
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
