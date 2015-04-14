package files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	public static HashMap getAbilityFileNames(File file) {
		HashMap map = new HashMap<String, String>();
		if (file.getName().contains(".xml")) {
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("Ability");
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					String key = eElement.getElementsByTagName("Label").item(0)
							.getAttributes().item(0).getTextContent();
					// key = key.toLowerCase();
					// double cooldown=
					// Double.parseDouble(eElement.getElementsByTagName("Cooldown")
					// .item(0).getAttributes().item(0).getTextContent());
					String icon = eElement.getElementsByTagName("Icon").item(0)
							.getAttributes().item(0).getTextContent()
							.toLowerCase();
					String description = eElement
							.getElementsByTagName("Description").item(0)
							.getAttributes().item(0).getTextContent();
					long logID = Long.parseLong(eElement
							.getElementsByTagName("CombatLogID").item(0)
							.getAttributes().item(0).getTextContent());
					Ability a = new Ability(0, key, icon, description, logID);
					map.put(key, a);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	public static HashMap indexClasses(File...files){
		HashMap map=new HashMap();
		for (File file:files){
			if (file.getName().contains(".xml")) {
				Document doc = getXMLDoc(file);
				String base=doc.getElementsByTagName("Class").item(0).getAttributes().item(0).getTextContent();
				NodeList classes=doc.getElementsByTagName("AdvancedClass");
				for(int x=0;x<classes.getLength();x++){
					map.put(classes.item(x).getAttributes().item(x).getTextContent(),base);
				}
			}
		}
		return map;
	}
	public static HashMap indexTrees(File...files){
		HashMap map=new HashMap();
		for (File file:files){
			if (file.getName().contains(".xml")) {
				Document doc = getXMLDoc(file);
				NodeList classes=doc.getElementsByTagName("AdvancedClass");
				for(int x=0;x<classes.getLength();x++){
					NodeList trees=doc.getElementsByTagName("DisciplineTree");
					for(int y=0;y<classes.getLength();y++){
					map.put(classes.item(x).getAttributes().item(x).getTextContent(),trees.item(y).getAttributes().item(y).getTextContent());
					}
				}
			}
		}
		return map;
	}

	public static HashMap getAbilityFileNames(File file, String advancedClass,
			String tree) {
		System.out.println(true);
		HashMap map = new HashMap<String, String>();
		Document doc = getXMLDoc(file);
		NodeList base = enterNode(doc.getDocumentElement(), "AdvClass", advancedClass);
		System.out.println(true);
		NodeList nList = enterNode((Element) base, "DisciplineTree", tree);
		map.putAll(getAbilitiesInNode(nList));
		nList=enterNode(doc.getDocumentElement(),"base");
		System.out.println(nList);
		map.putAll(getAbilitiesInNode(nList));
		return map;
	}

	private static NodeList enterNode(Element base,String tag) {
		NodeList bList = base.getElementsByTagName(tag);
		return bList.item(0).getChildNodes();

	}

	public static Document getXMLDoc(File file) {
		if (file.getName().contains(".xml")) {
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
		System.err.println("File " + file.getPath()
				+ "is not a valid XML document or does not exist");
		return null;
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
