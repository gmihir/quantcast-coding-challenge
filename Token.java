public class Token implements Comparable<Token>{
    private String tokenId;
    private int frequency;

    public Token(String tokenId, int frequency) {
        this.tokenId = tokenId;
        this.frequency = frequency;
    }

    public String getTokenId() {
        return tokenId;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Token other) {
        // equals case
        if(this.getFrequency() == other.getFrequency()) {
            return 0;
        }

        if(this.getFrequency() > other.getFrequency()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return("token: " + this.getTokenId() + ", frequency: " + this.getFrequency());
    }
}