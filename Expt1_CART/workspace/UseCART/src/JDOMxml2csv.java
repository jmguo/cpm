
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class JDOMxml2csv {

	public static void main(String[] args) throws Exception{
		
//		String xmlpath="./dataset/ApacheAll.xml";
//		String modelpath="./dataset/ApacheModel.xml";
		
//		String xmlpath="./dataset/LLVMAll.xml";
//		String modelpath="./dataset/LLVMModel.xml";
		
//		String xmlpath="./dataset/BDBJAll.xml";
//		String modelpath="./dataset/BDBJModel.xml";

//		String xmlpath="./dataset/BDBCAll.xml";
//		String modelpath="./dataset/BDBCModel.xml";
		
		String xmlpath="./dataset/BDBCFW.xml";
		String modelpath="./dataset/BDBCModel.xml";

//		String xmlpath="./dataset/SQLAll.xml";
//		String modelpath="./dataset/SQLModel.xml";
		//for SQL100configs
//		String xmlpath="./dataset/SQL100eval.xml";


		//read XML using SAXBuilder
		SAXBuilder builder = new SAXBuilder();
		try {
			// obtain doc
			Document doc = builder.build(xmlpath);
			// obtain root
			Element root = doc.getRootElement();
			
			//open csvInputFile
//			String csvInputName = "./dataset/ApacheAll.csv";
//			String csvInputName = "./dataset/LLVMAll.csv";
//			String csvInputName = "./dataset/BDBJAll.csv";
//			String csvInputName = "./dataset/BDBCAll.csv";
			String csvInputName = "./dataset/BDBCFW.csv";

//			String csvInputName = "./dataset/SQLAll.csv";
			//for SQL100configs
//			String csvInputName = "./dataset/SQL100testing.csv";


			//clear csvInputFile
			FileOutputStream fos = new FileOutputStream(new File(csvInputName));
			fos.close();
			
			//obtain FeatureList 
			List<String> featurelist = new ArrayList();
			FeatureModel model = new FeatureModel();
			featurelist = model.getFeatures(modelpath);
			
			String rowstr1="";
			for(Iterator iterfl = featurelist.iterator(); iterfl.hasNext();){
				rowstr1 = rowstr1+iterfl.next().toString().trim()+",";
			}
			rowstr1 += "Performance";
			
			//System.out.println(rowstr1);

			//write 1st label row
			WriteStreamAppend.method1(csvInputName, rowstr1+"\r\n");
			
			// obtain row
			List rowlist = root.getChildren("row");
			for (Iterator iter1 = rowlist.iterator(); iter1.hasNext();) {
			       Element row = (Element) iter1.next();
			       // obtain data
			       List datalist = row.getChildren("data");
		    	   String rowstr="";
			       for (Iterator iter2 = datalist.iterator(); iter2.hasNext();){
			    	   Element data = (Element) iter2.next();
			    	   // obtain Configuration, for SQL using columnname, others use columname
			    	   if(data.getAttributeValue("columname").contains("Configuration")) {
			    		   //System.out.println(data.getTextTrim());
			    		   String conf = data.getTextTrim();
			    		   
			    		   for(Iterator iterfl = featurelist.iterator(); iterfl.hasNext();){
			    			   if(conf.contains(iterfl.next().toString().trim()))
			    				   rowstr+="Y"+",";
			    			   else
			    				   rowstr+="N"+",";
			    		   }
			    	   }
			    	   // obtain Measurement
			    	   if(data.getAttributeValue("columname").contains("Measured Value")) {
			    		   // System.out.println(data.getTextTrim());
			    		   rowstr+=data.getTextTrim();
			    	   }
			       }
			       WriteStreamAppend.method1(csvInputName, rowstr+"\r\n");
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done");

	}


}
