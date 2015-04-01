package filemanagers.writers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser extends Writer {
	public static void main(String[] args) {
		String html = "http://parsely.io/parser/view/12412/0";
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
			Elements tableRowElements = tableElements.select(":not(thead) tr");
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < tableRowElements.size(); i++) {
				Element row = tableRowElements.get(i);
				Elements rowItems = row.select("td");
				System.out.println(rowItems.text());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}
