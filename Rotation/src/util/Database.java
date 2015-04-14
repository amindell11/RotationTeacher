package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Database {
	private static Map<String,Ability> map;
	public static final int ACTIONS=1;
	public static final int TIMES=0;
	public static List<Ability> convertToAbilities(List<String> readToList) {
		List<Ability> temp=new ArrayList<Ability>();
		for(String s:readToList){
			temp.add(getAbility(s));
		}
		return temp;
	}
	public static Ability getAbility(String s) {
		return map.get(s);
	}
	public static void indexXML(File...files){
		map=new HashMap<String,Ability>();
		for (File file:files){
			if(file.isFile()&&file.getName().contains(".xml")){
				Document doc=getXMLDoc(file);
				map.putAll(getAbilitiesInNode(doc.getDocumentElement().getChildNodes()));
			}else if(file.isDirectory()){
				indexXML(file.listFiles());
			}
		}
	}



	private static NodeList enterNode(Element base,String tag) {
		NodeList bList = base.getElementsByTagName(tag);
		return bList.item(0).getChildNodes();
	}

	private static Document getXMLDoc(File file) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				return doc;
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	}

	private static NodeList enterNode(Element base, String tag,
			String query) {
		NodeList bList = base.getElementsByTagName(tag);
		String textContent = "";
		int x =-1;
		while (!textContent.equalsIgnoreCase(query)) {
			x++;
			textContent = bList.item(x).getAttributes().item(0)
					.getTextContent();
		}
		return bList.item(x).getChildNodes();
	}

	private static HashMap getAbilitiesInNode(NodeList doc) {
		HashMap map=new HashMap();
		NodeList nList = ((Element) doc).getElementsByTagName("Ability");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			if (!eElement.getElementsByTagName("Passive").item(0)
					.getAttributes().item(0).getTextContent().equals("True")) {
				String key = eElement.getElementsByTagName("Label").item(0)
						.getAttributes().item(0).getTextContent();
				// key = key.toLowerCase();
				// double cooldown=
				// Double.parseDouble(eElement.getElementsByTagName("Cooldown")
				// .item(0).getAttributes().item(0).getTextContent());
				String icon = eElement.getElementsByTagName("Icon").item(0)
						.getAttributes().item(0).getTextContent().toLowerCase();
				String description = eElement
						.getElementsByTagName("Description").item(0)
						.getAttributes().item(0).getTextContent();
				long logID = Long.parseLong(eElement
						.getElementsByTagName("CombatLogID").item(0)
						.getAttributes().item(0).getTextContent());
				Ability a = new Ability(0, key, icon, description, logID);
				map.put(key, a);
			}
		}
		return map;
	}

}
