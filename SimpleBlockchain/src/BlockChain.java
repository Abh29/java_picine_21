import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockChain {
    private List<Block> chain = new ArrayList<>();
    private final int prefix_legth;
    private final char prefix;
    private String  data = null;
    private final int MAXLENGTH = 4096;


    BlockChain(int prefix_length, char prefix) {
        this.prefix_legth = prefix_length;
        this.prefix = prefix;
    }

    public void readData(String data) throws Exception {
        if (data.length() > MAXLENGTH)
            throw new Exception("data too long max length " + MAXLENGTH);
        this.data = data;
    }

    public void addNewBlock() throws Exception {
        if (data == null)
            throw new Exception("no data to add to a block !");
        String lastHash = chain.size() == 0 ? "" : chain.get(chain.size() - 1).getValidHash(prefix_legth, prefix);
        Block newBlock = new Block(data, lastHash, new Date().getTime());
        newBlock.getValidHash(prefix_legth, prefix);
        chain.add(newBlock);
    }

    public boolean checkBlockChain() {
        if (chain.size() == 0)
            return true;
        if (chain.size() == 1)
            return (chain.get(0).getPreviousHash().equals("") &&
                    chain.get(0).getHash().equals(chain.get(0).getValidHash(prefix_legth, prefix)));
        for (int i = 1; i < chain.size(); i++) {
            if (!chain.get(i).getPreviousHash().equals(chain.get(i - 1).getHash()) ||
                    !chain.get(i).getHash().equals(chain.get(i).getValidHash(prefix_legth, prefix)))
                return false;
        }
        return (true);
    }

}
