package filemanagers.readers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import files.Ability;

public class Database {
	public static final int ACTIONS=1;
	public static final int TIMES=0;
	public static List<Ability> convertToAbilities(List<String> readToList) {
		return null;
	}
	private static HashMap indexXML(File...files){
		HashMap map=new HashMap();
		for (File file:files){
			if(file.isFile()&&file.getName().contains(".xml")){
				Document doc=getXMLDoc(file);
				parseFile(doc);
			}else if(file.isDirectory()){
				indexXML(file);
			}
		}
		return map;
	}

	private static void parseFile(Document doc) {
		
	}


	private static NodeList enterNode(Element base,String tag) {
		NodeList bList = base.getElementsByTagName(tag);
		return bList.item(0).getChildNodes();
	}

	public static Document getXMLDoc(File file) {
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
	}

	public static NodeList enterNode(Element base, String tag,
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

	public static HashMap getAbilitiesInNode(NodeList doc) {
		HashMap map=new HashMap();
		NodeList nList = ((Element) doc).getElementsByTagName("Ability");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			Element eElement = (Element) nNode;
			if (!eElement.getElementsByTagName("Passive").item(0)
					.getAttributes().item(0).getTextContent().equals("True")) {
				String key = eElement.getElementsByTagName("Label").item(0)
						.getAttributes().item(0).getTextContent();
				System.out.println(key);
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
				System.out.println(key);
				map.put(key, a);
			}
		}
		return map;
	}

}

}
