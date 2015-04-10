package filemanagers.writers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
	public static void main(String[] args) {
		System.out.println(parseTable("http://parsely.io/parser/view/12412/0"));
	}

	public static String[][] parseTable(String html) {
		Document doc;
		try {
			doc = Jsoup.connect(html).get();
			while (doc == null) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Element tableElements = doc.getElementById("rotation");
			Elements tableRowElements = tableElements.select("tr");
			String[][] table = new String[tableRowElements.size()][tableRowElements.get(2).select("td").size()];
			for (int i = 0; i < tableRowElements.size(); i++) {
				Element row = tableRowElements.get(i);
				Elements rowItems = row.select("td");
				rowItems.addAll(row.select("th"));
					for (int x = 0; x < table[0].length; x++) {
						table[i][x]=rowItems.get(x).text();
					}

			}
			return table;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
