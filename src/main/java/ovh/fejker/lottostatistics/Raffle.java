package ovh.fejker.lottostatistics;

import java.util.Arrays;

public class Raffle {
    private final long drawId;
    private final String drawDate;
    private final String gameType;
    private final String results;
    private String specialResults;

    public Raffle(long drawId, String drawDate, String gameType, int[] results) {
        Arrays.sort(results);
        this.drawId = drawId;
        this.drawDate = drawDate;
        this.gameType = gameType;
        this.results = Arrays.toString(results);
    }

    public Raffle(long drawId, String drawDate, String gameType, int[] results, int[] specialResults) {
        Arrays.sort(specialResults);
        Arrays.sort(results);
        this.drawId = drawId;
        this.drawDate = drawDate;
        this.gameType = gameType;
        this.results = Arrays.toString(results);
        this.specialResults = Arrays.toString(specialResults);
    }

    public long getDrawId() {
        return drawId;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public String getGameType() {
        return gameType;
    }

    public String getResults() {
        return results;
    }

    public String getSpecialResults() {
        return specialResults;
    }

    @Override
    public String toString() {
        if(specialResults == null){
            return "Raffle{"
                    + "drawId=" + drawId
                    + ", drawDate=" + drawDate
                    + ", gameType=" + gameType
                    + ", results=" + results
                    + '}';
        }

        return "Raffle{"
                + "drawId=" + drawId
                + ", drawDate=" + drawDate
                + ", gameType=" + gameType
                + ", results=" + results
                + ", specialResults=" + specialResults
                + '}';
    }
}
