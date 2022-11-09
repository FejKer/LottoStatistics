package ovh.fejker.lottostatistics;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

public class DataFetcher {

    private String url;
    private String game;
    private int size;

    public DataFetcher(String game, int size) throws IOException {
        this.game = game;
        this.size = size;
        getData();
    }

    public JSONArray getData() throws IOException {
        System.out.println("FETCHING DATA");
        url = "https://www.lotto.pl/api/lotteries/draw-results/by-gametype?game=" + game + "&index=1&size=" + size + "&sort=drawDate&order=DESC";
        String json = IOUtils.toString(URI.create(url), Charset.forName("UTF-8"));
        JSONObject parent = new JSONObject(json);
        System.out.println("PARENT" + parent);
        JSONArray array = new JSONArray(parent.getJSONArray("items"));
        System.out.println("ARRAY" + array);

        for(Object obj : array) {
            boolean isSpecial = false;
            long id;
            String date;
            String type;
            int[] results = null;
            int[] specialResults = null;

            JSONObject child = new JSONObject(obj.toString());
            System.out.println("CHILD" + child);
            JSONArray childArray = new JSONArray(child.getJSONArray("results"));

            for(Object obj2 : childArray) {
                JSONObject child2 = new JSONObject(obj2.toString());
                JSONArray childArray2 = new JSONArray(child2.getJSONArray("resultsJson"));
                results = new int[childArray2.length()];
                int i = 0;
                for(Object obj3 : childArray2) {
                    results[i] = (int) obj3;
                    i++;
                }

                childArray2 = child2.getJSONArray("specialResults");

                if(childArray2.length() == 0) {
                    continue;
                }
                isSpecial = true;
                i = 0;
                specialResults = new int[childArray2.length()];
                for(Object obj3 : childArray2) {
                    specialResults[i] = (int) obj3;
                    i++;
                }
            }

            id = Long.parseLong(child.get("drawSystemId").toString());
            date = (String) child.get("drawDate");
            type = game;

            if(isSpecial){
                Main.addToRaffleList(new Raffle(id, date, type, results, specialResults));
            } else {
                Main.addToRaffleList(new Raffle(id, date, type, results));
            }
        }

        return array;
    }
}
