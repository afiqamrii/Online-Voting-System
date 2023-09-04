import java.util.HashMap;
import java.util.Map;

public class VoteCounter {
    private Map<String, Integer> voteCount;

    public VoteCounter() {
        voteCount = new HashMap<>();
    }

    public void countVote(String candidateNumber) {
        int currentVoteCount = voteCount.getOrDefault(candidateNumber, 0);
        voteCount.put(candidateNumber, currentVoteCount + 1);
    }

    public Map<String, Integer> getVoteCount() {
        return voteCount;
    }
}