import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Parse_CART_Rules_Generated_in_Rattle {

	
	public static void generate(String fulltype, String spltype) throws Exception{
		
		String rawrulefile = "./dataset/" + fulltype + "rawrule" + ".txt";
		String modelfile = "./dataset/" + spltype + "Model" + ".xml";

		String timefile = "./dataset/" + fulltype + "Time" + ".txt";
		String parserulefile = "./dataset/" + fulltype + "parserule" + ".txt";
		String parseresultfile = "./dataset/" + fulltype + "parseresult" + ".txt";

		FileOutputStream fos1 = new FileOutputStream(new File(timefile));
		fos1.close();
		FileOutputStream fos2 = new FileOutputStream(new File(parserulefile));
		fos2.close();
		FileOutputStream fos3 = new FileOutputStream(new File(parseresultfile));
		fos3.close();
				
		//obtain FeatureList 
		List<String> featurelist = new ArrayList();
		FeatureModel model = new FeatureModel();
		featurelist = model.getFeatures(modelfile);
		
		BufferedReader br = new BufferedReader(new FileReader(new File(rawrulefile)));
		String line = null;
		int ruleno=-1;
		String ruleline="";
		String feature="";
		String featurevalue="";
		int featureid=0;
		while( (line = br.readLine()) != null){
			line=line.trim();
			if(line==""||line.length()==0)
				continue;
			//obtain Time
			if(line.startsWith("Time"))
				WriteStreamAppend.method1(timefile, line.substring(12, line.indexOf("secs"))+"\r\n");
			else{
				//find a Rule and obtain performance result
				if(line.startsWith("Rule")){
					if(ruleno>=0)
						WriteStreamAppend.method1(parserulefile, ruleline+"\r\n");
					ruleno++;
					ruleline="";
					WriteStreamAppend.method1(parseresultfile, line.substring(line.indexOf("Performance")+12, line.indexOf("cover")-1)+"\r\n");
				}
				else{				
					//in the above Rule, obtain rules
					if(line.contains("=Y")||line.contains("=N")){
						feature=line.substring(0, line.indexOf("="));
						featurevalue=line.substring(line.indexOf("=")+1);
						for(int i=0; i<featurelist.size(); i++)
							if(featurelist.get(i).contains(feature))
								featureid=i+1;
						ruleline += String.valueOf(featureid)+featurevalue+" ";
					}
					
				}	
			}
		}
		//the last rule
		WriteStreamAppend.method1(parserulefile, ruleline+"\r\n");
		
	}

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		String inputtype = "";
		String[] randomtype = new String[]{"R1", "R2", "R3", "R4", "R5"};

//		generate("BDBC18R1", "BDBC");
		
		//for spl
//		String spltype="BDBC";
////		int[] size = new int[]{18, 36, 54, 139};
//		int[] size = new int[]{139};

//		String spltype="Apache";
////		int[] size = new int[]{9, 18, 27, 29};
//		int[] size = new int[]{29};

//		String spltype="BDBJ";
////		int[] size = new int[]{26, 52, 78, 48};
//		int[] size = new int[]{48};
		
//		String spltype="LLVM";
////		int[] size = new int[]{11, 22, 33, 62};
//		int[] size = new int[]{62};

		String spltype="SQL";
//		int[] size = new int[]{39, 78, 117, 566};
		int[] size = new int[]{566};

		
//		String spltype="X264";
////		int[] size = new int[]{16, 32, 48, 81};
//		int[] size = new int[]{81};

		
		try {
			for(int i=0; i<size.length; i++)
				for(int j=0; j<randomtype.length; j++){
					inputtype =spltype+String.valueOf(size[i])+randomtype[j];
					generate(inputtype, spltype);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}

}
