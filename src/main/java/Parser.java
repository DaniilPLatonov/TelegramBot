import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Parser {

    public static ArrayList<Food> Foods = new ArrayList<Food>();
    private static String url = "http://frs24.ru/st/tablica-kalorijnosti-produktov-pitaniya/";

    public static void parseUrl() throws IOException {
        Document page = getPage();
        Element tableFds = page.select("table[class=norm]").first();
        Elements names = tableFds.select("tr");

        int index = 0;
        Foods.clear();
        Names(names, index);
    }

    private static Document getPage() throws IOException {
        Document page = Jsoup.parse(new URL(url), 4000);
        return page;
    }


    public static void Names(Elements names, int index){
        for(int i = 0; i < 78; i++){
            Element nameLine = names.get(i);
            Food food = new Food();
            food.setProduct(nameLine.select("td").get(0).text());
            food.setCal(nameLine.select("td").get(1).text());
            food.setBelok(nameLine.select("td").get(2).text());
            food.setShir(nameLine.select("td").get(3).text());
            food.setUglevod(nameLine.select("td").get(4).text());

            Foods.add(food);
        }
    }
    public static String getCal( String nameProduct){
        return Foods.stream().filter(food -> nameProduct.equals(food.getProduct()))
                .findFirst()
                .get()
                .getCal();
    }

    public static ArrayList<String> getNameProducts(){
        ArrayList<String> nameProducts = new ArrayList<String>();
        for(int i = 0;i< Foods.size();i++){
            nameProducts.add(String.valueOf(Foods.get(i).getProduct()));

        }
        return nameProducts;
    }

    public static void main(String [] args) throws IOException {
        Document page = getPage();
        Element tableFds = page.select("table[class=norm]").first();
        Elements names = tableFds.select("tr");

    }
}


