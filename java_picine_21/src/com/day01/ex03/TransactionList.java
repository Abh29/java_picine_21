package com.day01.ex03;

import java.util.UUID;

public interface TransactionList {
    boolean addTransaction(Transaction transaction);
    Transaction removeTransactionById(UUID identifier);
    Transaction[] toArray();
}
