package ovh.fejker.lottostatistics;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Raffle {
    private final long drawId;
    private final String drawDate;
    private final String gameType;
    private final String results;
    private String specialResults;
    private static Map<Integer, Integer> occurrences = new TreeMap<>();
    private int[] resultsArray;
    private int[] specialResultsArray;


    public Raffle(long drawId, String drawDate, String gameType, int[] results) {
        Arrays.sort(results);
        resultsArray = results;
        this.drawId = drawId;
        this.drawDate = drawDate;
        this.gameType = gameType;
        this.results = Arrays.toString(results);
        updateOccurrences();
        System.out.println(occurrences);
    }

    public Raffle(long drawId, String drawDate, String gameType, int[] results, int[] specialResults) {
        Arrays.sort(specialResults);
        Arrays.sort(results);
        resultsArray = results;
        specialResultsArray = specialResults;
        this.drawId = drawId;
        this.drawDate = drawDate;
        this.gameType = gameType;
        this.results = Arrays.toString(results);
        this.specialResults = Arrays.toString(specialResults);
        updateOccurrences();
        System.out.println(occurrences);
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

    public static Map<Integer, Integer> getOccurrence() {
        return occurrences;
    }
    private void updateOccurrences(){
        System.out.println("UPDATING");
        for (int i : this.resultsArray) {
            System.out.println(occurrences.get(i));
            Integer j = occurrences.get(i);
            if(j == null) {
                occurrences.put(i, 1);
                System.out.println(i + " " + 1);
            } else {
                occurrences.replace(i, j+1);
                System.out.println(i + " " + j+1);
            }
        }
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
