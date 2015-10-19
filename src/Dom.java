import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {

	public static void main(String[] args) {
		Map<String, Integer> questions = new TreeMap<String, Integer>();
		Map<String, Integer> answers = new TreeMap<String, Integer>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder Builder = factory.newDocumentBuilder();
			Document doc = Builder.parse("C:\\Users\\Ameet\\Desktop\\Project\\posts.xml");
			NodeList list = doc.getElementsByTagName("row");

			for (int i = 0; i < list.getLength(); i++) {
				Node p = list.item(i);
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element postType = (Element) p;
					String postTypeId = postType.getAttribute("PostTypeId");
					String ownerId = postType.getAttribute("OwnerUserId");
					
					if (postTypeId.equals("1")) {
						Integer occ = questions.get(ownerId);
						if (occ == null) {
							occ = 0;

						}
						questions.put(ownerId, occ + 1);
					}
					if (postTypeId.equals("2")) {
						Integer occ = answers.get(ownerId);
						if (occ == null) {
							occ = 0;

						}
						answers.put(ownerId, occ + 1);
					}

				}

			}
			
			System.out.println(questions.size() + "=" + answers.size());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		TreeMap<String, Integer> sortedMapQuestions = SortByValue(questions); 
		TreeMap<String, Integer> sortedMapAnswers = SortByValue(answers);
		System.out.println(sortedMapQuestions);
		System.out.println(sortedMapAnswers);
	int i=0;
	     Set set = sortedMapQuestions.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	    	  while(i<10)
	         {
	    		  Map.Entry mentry = (Map.Entry)iterator.next();
	      
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	i++;
	         }}
	}
		
	public static TreeMap<String, Integer> SortByValue 
	(Map<String, Integer> answers) {
		ValueComparator vc =  new ValueComparator(answers);
		TreeMap<String,Integer> sortedMap = new TreeMap<String,Integer>(vc);
		sortedMap.putAll(answers);
		return sortedMap;
}
}
