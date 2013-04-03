import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Preprocess_Specific_or_Interaction_RawData {

	
	public static void generate(String inputtype, String spltype) throws Exception{
		
		String rawrulefile = "./specific_confs/" + inputtype + ".txt";
		String modelfile = "./dataset/originalInput/" + spltype + "Model" + ".xml";

		String parseresultfile = "./specific_confs/" + spltype + "Specific" + ".csv";

		FileOutputStream fos2 = new FileOutputStream(new File(parseresultfile));
		fos2.close();

		//obtain FeatureList 
		List<String> featurelist = new ArrayList();
		FeatureModel model = new FeatureModel();
		featurelist = model.getFeatures(modelfile);
		
		//write first line in output file
		String rowstr1="";
		for(Iterator iterfl = featurelist.iterator(); iterfl.hasNext();){
			rowstr1 = rowstr1+iterfl.next().toString().trim()+",";
		}
		
		//write 1st label row
		WriteStreamAppend.method1(parseresultfile, rowstr1+"Performance"+"\r\n");
		
		BufferedReader br = new BufferedReader(new FileReader(new File(rawrulefile)));
		String line = null;
		int lineno=0;
		int ruleno=-1;
		String ruleline="";
		String feature="";
		String featurevalue="";
		int featureid=0;

		while( (line = br.readLine()) != null){
			line=line.trim();
			if(line==""||line.length()==0)
				continue;
			
			//ignore first line in input file
			lineno++;
			if(lineno==1)
				continue;
			
			//obtain configuration
			if(!line.startsWith("value:")){
				String rowstr = "";
			
				for(Iterator<String> iterfl = featurelist.iterator(); iterfl.hasNext();){
					if(line.contains(iterfl.next().toString().trim()))
						rowstr+="Y"+",";
					else
						rowstr+="N"+",";
				}
				WriteStreamAppend.method1(parseresultfile, rowstr);
				continue;
			}
			//obtain performance
			if(line.startsWith("value:")){
				WriteStreamAppend.method1(parseresultfile, line.substring(line.indexOf("value:")+7) + "\r\n");
				continue;
			}
		}
	}

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String[] randomtype = new String[]{"R1", "R2", "R3", "R4", "R5"};

//		generate("BDBC18R1", "BDBC");
		
		//for spl
//		String inputtype = "BerkeleyC";
//		String spltype="BDBC";
////		int[] size = new int[]{18, 36, 54, 139};
//		int[] size = new int[]{139};

//		String inputtype = "Apache";
//		String spltype="Apache";
////		int[] size = new int[]{9, 18, 27, 29};
//		int[] size = new int[]{29};

//		String inputtype = "BerkeleyJava";
//		String spltype="BDBJ";
////		int[] size = new int[]{26, 52, 78, 48};
//		int[] size = new int[]{48};
		
//		String inputtype = "LLVM";
//		String spltype="LLVM";
////		int[] size = new int[]{11, 22, 33, 62};
//		int[] size = new int[]{62};

//		String spltype="SQL";
//		int[] size = new int[]{39, 78, 117, 566};
//		int[] size = new int[]{566};

		String inputtype = "x264";
		String spltype="X264";
////		int[] size = new int[]{16, 32, 48, 81};
//		int[] size = new int[]{81};

		
		try {
//			for(int i=0; i<size.length; i++)
//				for(int j=0; j<randomtype.length; j++){
					//inputtype =spltype+String.valueOf(size[i])+randomtype[j];
					generate(inputtype, spltype);
//				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}

}
