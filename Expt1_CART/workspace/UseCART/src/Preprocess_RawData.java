import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Preprocess_RawData {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void genPerf(String sourcepath, String outfile) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(sourcepath)));

		FileOutputStream fos1 = new FileOutputStream(new File(outfile));
		fos1.close();
		
		String line = null;
		String rowline = null;
		
		int lineno = 0;
		double sum = 0;
		double ave = 0;
		
		while( (line = br.readLine()) != null){
			lineno++;
			if(lineno==1){ //for a new variant
				sum = 0;
				continue;
			}
			if(lineno==2 || lineno ==3){
				sum+=Double.parseDouble(line.toString());
				continue;
			}
			if(lineno==4){
				sum+=Double.parseDouble(line.toString());
				ave=sum/3;
				lineno=0;
				rowline = String.valueOf(ave);
				WriteStreamAppend.method1(outfile, rowline+"\r\n");
				continue;
			}				
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		//Handle performance	
		String perfsourcepath="./dataset/X264RawPerf.txt";
		
		String perffile = "./dataset/X264AllPerf.txt";
		
		String modelpath = "./dataset/X264Model.xml";
		
		String confsource = "./dataset/X264Raw.txt";
		
		String confout = "./dataset/X264AllConf.csv";

		genPerf(perfsourcepath, perffile);	
		
		System.out.println("perf done");
		
		//clear csvOutput
		FileOutputStream fos = new FileOutputStream(new File(confout));
		fos.close();
		
		//obtain FeatureList 
		List<String> featurelist = new ArrayList();
		FeatureModel model = new FeatureModel();
		featurelist = model.getFeatures(modelpath);
		
		String rowstr1="";
		for(Iterator iterfl = featurelist.iterator(); iterfl.hasNext();){
			rowstr1 = rowstr1+iterfl.next().toString().trim()+",";
		}
		
		//write 1st label row
		WriteStreamAppend.method1(confout, rowstr1+"\r\n");
		
		//obtain conf
		BufferedReader br = new BufferedReader(new FileReader(new File(confsource)));
		String line = null;
		while( (line = br.readLine()) != null){
			String rowstr = "";
			for(Iterator iterfl = featurelist.iterator(); iterfl.hasNext();){
 			   if(line.contains(iterfl.next().toString().trim()))
 				   rowstr+="Y"+",";
 			   else
 				   rowstr+="N"+",";
 		   }
		   WriteStreamAppend.method1(confout, rowstr+"\r\n");
		}
		
		System.out.println("conf done");

		
	}

}
