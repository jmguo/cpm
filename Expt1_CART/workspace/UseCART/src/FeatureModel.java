import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class FeatureModel {

	/**
	 * @param args
	 */
	
	public static List<String> getFeatures(String sourcepath) throws Exception{
		
		List<String> featurelist = new ArrayList();
		
		//read XML using SAXBuilder
		SAXBuilder builder = new SAXBuilder();
		
		try {
			// obtain doc
			Document doc = builder.build(sourcepath);
			// obtain root
			Element root = doc.getRootElement();
			
			List rowlist = root.getChildren("element");
			for (Iterator iter1 = rowlist.iterator(); iter1.hasNext();) {
			       Element row = (Element) iter1.next();
				   //System.out.println(row.getAttributeValue("name"));
				   featurelist.add(row.getAttributeValue("name").toString().trim());
			}

					
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return featurelist;
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String sourcefilepath="./dataset/ApacheModel.xml";
		List<String> featurelist = new ArrayList();
		featurelist = getFeatures(sourcefilepath);
		System.out.println("done");


	}

}
