import java.time.LocalDate;

public class TokenPair {
    private String tokenId;
    private LocalDate date;

    public TokenPair(String tokenId, LocalDate date) {
        this.tokenId = tokenId;
        this.date = date;
    }

    public String getTokenId() {
        return tokenId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return("token: " + this.getTokenId() + ", date: " + this.getDate());
    }

    @Override
    public boolean equals(Object other) {
        if(other == null) {
            return false;
        }

        if(other == this) {
            return true;
        }

        if(!(other instanceof TokenPair)) {
            return false;
        }
        
        TokenPair otherTokenPair = (TokenPair)other;
        return(this.getTokenId() == otherTokenPair.getTokenId() && this.getDate().equals(otherTokenPair.getDate()));
    }
}
