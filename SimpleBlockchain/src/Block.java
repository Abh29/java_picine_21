import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Block {
    private String hash;
    private final String previousHash;
    private final String data;
    private final long timeStamp;
    private int nonce;

    public Block(String data, String previousHash, long timeStamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = timeStamp;
        this.hash = "";
        this.nonce = -1;
    }

    private void getNextNonce()
    {
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

    public String getValidHash(int prefix_length, char prefix) {
        while (!checkHashValidity(prefix_length, prefix))
        {
            getNextNonce();
            generateHash();
        }
        return this.hash;
    }

    public String getHash()
    {
        return this.hash;
    }

    public String getPreviousHash() {return this.previousHash; }

}
