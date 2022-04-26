package com.day01.ex04;

import java.util.Arrays;
import java.util.UUID;

public class TransactionLinkedList implements TransactionList {

    private Transaction root = null;
    private Transaction tail = null;
    private int size = 0;

    @Override
    public boolean addTransaction(Transaction transaction) {
        if (root == null) {
            root = transaction;
            tail = transaction;
        }
        else{
            tail.setNext(transaction);
            tail = tail.getNext();
        }
        size++;
        return true;
    }

    @Override
    public Transaction removeTransactionById(UUID identifier) {
        Transaction p = null;
        Transaction tmp = root;

        if (root.getIdentifier().equals(identifier))
        {
            if (tail == root) {
                root = null;
                tail = null;
            }else{
                root = root.getNext();
            }
            size--;
            return tmp;
        }

        while(tmp.getNext() != null)
        {
            p = tmp;
            tmp = tmp.getNext();
            if (tmp.getIdentifier().equals(identifier))
            {
                if (tail == tmp)
                {
                    tail = p;
                }
                p.setNext(tmp.getNext());
                size--;
                return tmp;
            }
        }
        throw new RuntimeException("Transaction not found uuid = " + identifier.toString());
    }


    @Override
    public Transaction[] toArray() {
        if (size < 1)
            return null;
        Transaction[] out = new Transaction[size];
        Transaction tmp = root;

        for (int i = 0; i < size && tmp != null ; i++) {
            out[i] = tmp;
            tmp = tmp.getNext();
        }
        return out;
    }

    public int getSize() {
        return size;
    }

    public Transaction getRoot() {
        return root;
    }


    public boolean contains(UUID identifier){
        if (root.getIdentifier().equals(identifier))
            return true;
        Transaction tmp = root;
        while (tmp.getNext() != null)
        {
            tmp = tmp.getNext();
            if (tmp.getIdentifier().equals(identifier))
                return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "TransactionLinkedList{ size= " + size +
                " " + Arrays.toString(toArray()) +
                '}';
    }
}
