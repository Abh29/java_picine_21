package com.example.block_chain.model;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.security.MessageDigest;
import java.sql.SQLData;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;

@Entity(name = "block_chain")
@Table(name = "block_chain")
public class Block extends RecursiveTreeObject<Block> {

    @Id
    @SequenceGenerator(
            name = "block_chain_sequence",
            sequenceName = "block_chain_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "block_chain_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "block_hash",
            nullable = false
    )
    private String hash;

    @Column(
            name = "previous_block_hash"
    )
    private String previousHash;

    @Column(
            name = "block_data",
            columnDefinition = "TEXT"
    )
    private String data;

    @CreationTimestamp
    @Column(
            name = "created_at"
    )
    private Date timeStamp;

    @Column(
            name = "block_nonce",
            nullable = false
    )
    private Integer nonce;

    @Column(
            name = "public_key",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String publicKey;


    public Block(String data, String previousHash, String publicKey) {
        this.data = data;
        this.previousHash = previousHash;
        this.publicKey = publicKey;
        this.hash = "";
        this.nonce = -1;
    }

    public Block() {

    }

    private void getNextNonce() {
        nonce++;
    }

    private void generateHash() {

        String dataToHash = previousHash + timeStamp + nonce + data;
        byte[] digestedData;

        try {
            MessageDigest mg = MessageDigest.getInstance("SHA-256");
            digestedData = mg.digest(dataToHash.getBytes(UTF_8));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        StringBuilder out = new StringBuilder();
        for (byte b : digestedData) {
            out.append(String.format("%02x", b));
        }
        this.hash = out.toString();
    }

    private boolean checkHashValidity(int prefix_length, char prefix) {
        if (prefix_length > hash.length())
            return false;
        for (int i = 0; i < prefix_length; i++) {
            if (hash.charAt(i) != prefix)
                return false;
        }
        return true;
    }

    public String setValidHash(int prefix_length, char prefix) {
        while (!checkHashValidity(prefix_length, prefix)) {
            getNextNonce();
            generateHash();
        }
        return this.hash;
    }

    public String getHash() {
        return (hash == null ?  "null" : hash);
    }

    public String getPreviousHash() {
        return (previousHash == null ? "null" : previousHash);
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return (data == null ? "null" : data);
    }

    public String getTimeStamp() {
        return (timeStamp == null ? "null" : timeStamp.toString());
    }

    public int getNonce() {
        return nonce;
    }

    public String getPublicKey() {
        return (publicKey == null ? "null" : publicKey);
    }

}