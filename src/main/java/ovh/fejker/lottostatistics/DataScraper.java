package ovh.fejker.lottostatistics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DataScraper {
    private String url;
    private final String game;
    private final HashMap<String, String> months;
    private JSONArray list;
    private final DateTimeFormatter formatter;
    private boolean updating;

    public DataScraper(String game) throws IOException, ParseException {
        this.game = game;

        months = new HashMap<>();
        months.put("JANUARY", "Stycznia");
        months.put("FEBRUARY", "Lutego");
        months.put("MARCH", "Marca");
        months.put("APRIL", "Kwietnia");
        months.put("MAY", "Maja");
        months.put("JUNE", "Czerwca");
        months.put("JULY", "Lipca");
        months.put("AUGUST", "Sierpnia");
        months.put("SEPTEMBER", "Września");
        months.put("OCTOBER", "Października");
        months.put("NOVEMBER", "Listopada");
        months.put("DECEMBER", "Grudnia");
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        url = "https://megalotto.pl/wyniki/" + game + "/losowania-od-1-Stycznia-1980-do-" + LocalDate.now().getDayOfMonth() + "-" + months.get(LocalDate.now().getMonth().toString()) + "-" + LocalDate.now().getYear();
        updating = false;

        File file = new File(game + ".json");
        if(file.exists()) {
            updateRaffles();
            //getRaffles();
        } else {
            getRaffles();
        }
    }

    private void updateRaffles() throws IOException, ParseException {
        System.out.println("UPDATING");

        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(game + ".json");
        Object obj = jsonParser.parse(reader);
        list = (JSONArray) obj;
        JSONObject firstObj = null;
        try{
            firstObj = (JSONObject) list.get(0);
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            File file = new File(game + ".json");
            file.delete();
            getRaffles();
        }

        JSONObject firstChild = (JSONObject) firstObj.get("Draw");

        String dateFile = (String) firstChild.get("Date");

        LocalDate fromFile = LocalDate.parse(dateFile, formatter);
        LocalDate localDate = LocalDate.now();
        String formattedDate = formatter.format(localDate);
        dateFile = formatter.format(fromFile);
        if(dateFile.equals(formattedDate)){
            System.out.println(dateFile + " " + formattedDate + " not needed to update");
            return;
        }
        System.out.println(dateFile + " " + formattedDate + " need to update");
        fromFile = fromFile.plusDays(1);
        int dayFrom = fromFile.getDayOfMonth();
        String monthFrom = fromFile.getMonth().toString();
        int yearFrom = fromFile.getYear();
        url = "https://megalotto.pl/wyniki/" + game + "/losowania-od-" + dayFrom + "-" + months.get(monthFrom) + "-" + yearFrom + "-do-" + localDate.getDayOfMonth() + "-" + months.get(localDate.getMonth().toString()) + "-" + localDate.getYear();

        System.out.println(url);
        updating = true;
        getRaffles();
    }

    private void getRaffles() throws IOException {
        int iteration = 0;
        Document document = Jsoup.connect(url).followRedirects(false).timeout(60000).get();
        list = new JSONArray();
        for(Element table : document.body().select(".lista_ostatnich_losowan")) {
            for(Element row : table.select("ul")) {
                JSONObject parent = new JSONObject();
                JSONObject child = new JSONObject();
                JSONArray numbers = new JSONArray();
                JSONObject winnings = new JSONObject();
                Elements lis = row.select("li");
                Elements as = row.select("a");
                String noOfDraw = lis.get(0).text();
                String date = lis.get(1).text();
                int n1 = Integer.parseInt(lis.get(2).text());
                int n2 = Integer.parseInt(lis.get(3).text());
                int n3 = Integer.parseInt(lis.get(4).text());
                int n4 = Integer.parseInt(lis.get(5).text());
                int n5 = Integer.parseInt(lis.get(6).text());
                System.out.println(noOfDraw + " " + date + " " + n1 + " " + n2 + " " + n3 + " " + n4 + " " + n5);
                String href = as.attr("href");
                System.out.println(href);

                if(!href.isEmpty()){
                    winnings = getWinnings(as.attr("href"));
                }

                child.put("Draw number", noOfDraw);
                child.put("Date", date);
                numbers.add(n1);
                numbers.add(n2);
                numbers.add(n3);
                numbers.add(n4);
                numbers.add(n5);
                child.put("Numbers", numbers);
                child.put("Winnings", winnings);
                parent.put("Draw", child);
                if(updating){
                    list.add(iteration, parent);
                    iteration++;
                } else {
                    list.add(parent);
                }
            }
        }
        updating = false;
        try (FileWriter file = new FileWriter(game + ".json")) {
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getWinnings(String url) throws IOException {
        JSONObject winnings = new JSONObject();
        int iteration = 0;
        Document document = Jsoup.connect(url).followRedirects(false).timeout(60000).get();
        for(Element table : document.body().select(".dl_wygrane_table tbody")) {
            for (Element row : table.select("tr")) {
                Elements tds = row.select("td");
                System.out.println(tds);
                if(tds.isEmpty()) {
                    continue;
                }
                iteration++;
                JSONArray winData = new JSONArray();
                int winners = Integer.parseInt(tds.get(1).text());
                double prize = Double.parseDouble(tds.get(2).text().replaceAll("\\s",""));
                winData.add(winners);
                winData.add(prize);
                winnings.put(Integer.toString(iteration), winData);
            }
        }
        return winnings;
    }

}
