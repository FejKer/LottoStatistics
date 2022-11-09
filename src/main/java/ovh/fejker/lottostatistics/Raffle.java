package ovh.fejker.lottostatistics;

import java.util.Arrays;

public class Raffle {
    private long drawId;
    private String drawDate;
    private String gameType;
    private int[] results;
    private int[] specialResults;

    public Raffle(long drawId, String drawDate, String gameType, int[] results) {
        this.drawId = drawId;
        this.drawDate = drawDate;
        this.gameType = gameType;
        this.results = results;
    }

    public Raffle(long drawId, String drawDate, String gameType, int[] results, int[] specialResults) {
        this.drawId = drawId;
        this.drawDate = drawDate;
        this.gameType = gameType;
        this.results = results;
        this.specialResults = specialResults;
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

    public int[] getResults() {
        return results;
    }

    public int[] getSpecialResults() {
        return specialResults;
    }

    @Override
    public String toString() {
        if(specialResults == null){
            return "Raffle{"
                    + "drawId=" + drawId
                    + ", drawDate=" + drawDate
                    + ", gameType=" + gameType
                    + ", results=" + Arrays.toString(results)
                    + '}';
        }

        return "Raffle{"
                + "drawId=" + drawId
                + ", drawDate=" + drawDate
                + ", gameType=" + gameType
                + ", results=" + Arrays.toString(results)
                + ", specialResults=" + Arrays.toString(specialResults)
                + '}';
    }
}
