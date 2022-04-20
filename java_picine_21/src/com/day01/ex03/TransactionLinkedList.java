package com.day01.ex03;

import java.util.UUID;

public class TransactionLinkedList implements TransactionList{
    @Override
    public boolean addTransaction(Transaction transaction) {
        return false;
    }

    @Override
    public boolean removeTransactionById(UUID identifier) {
        return false;
    }

    @Override
    public Transaction[] toArray() {
        return new Transaction[0];
    }
}
