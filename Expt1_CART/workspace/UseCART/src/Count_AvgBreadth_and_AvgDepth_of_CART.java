import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Count_AvgBreadth_and_AvgDepth_of_CART {

	
	public static void generate(String inputtype, String spltype) throws Exception{
		
		//for each size
		String parseresultfile = "./dataset/AccuracyTime/setNumberCP0/" + spltype + "/" + inputtype + "CartComplexity" + ".txt";
		FileOutputStream fos3 = new FileOutputStream(new File(parseresultfile));
		fos3.close();
		double avgbreadth=0;
		int totalbreadth=0;
		double avgdepth=0;
		int totaldepth=0;
		
		//for each random sample
		String[] randomtype = new String[]{"R1", "R2", "R3", "R4", "R5"};
		for (int j = 0; j < randomtype.length; j++) {
			String rawrulefile = "./dataset/AccuracyTime/setNumberCP0/"
					+ spltype + "/" + inputtype + randomtype[j] + "parserule"
					+ ".txt";

			BufferedReader br = new BufferedReader(new FileReader(new File(rawrulefile)));
			String line = null;
			int rulesum = 0; // total rules for all lines
			int rulemax = 0; // max rules for each line
			while ((line = br.readLine()) != null) {
				if (line == "" || line.length() == 0)
					break;
				rulesum++; // each line, each rule -> breadth of CART
				String[] ruleset = line.split(" ");
				if (rulemax < ruleset.length)
					rulemax = ruleset.length;
			}

			totalbreadth += rulesum;
			totaldepth += rulemax;
		}
		
		avgbreadth = (double)totalbreadth/randomtype.length;
		avgdepth = (double)totaldepth/randomtype.length;

		WriteStreamAppend.method1(parseresultfile, avgbreadth+" "+avgdepth);
		
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
//		int[] size = new int[]{18, 36, 54, 139};
//		int[] size = new int[]{139};

//		String spltype="Apache";
//		int[] size = new int[]{9, 18, 27, 29};
//		int[] size = new int[]{29};

//		String spltype="BDBJ";
//		int[] size = new int[]{26, 52, 78, 48};
//		int[] size = new int[]{48};
		
//		String spltype="LLVM";
//		int[] size = new int[]{11, 22, 33, 62};
//		int[] size = new int[]{62};

		String spltype="SQL";
		int[] size = new int[]{39, 78, 117, 566};
//		int[] size = new int[]{566};

		
//		String spltype="X264";
//		int[] size = new int[]{16, 32, 48, 81};
//		int[] size = new int[]{81};

		
		try {
			for(int i=0; i<size.length; i++)
				//for(int j=0; j<randomtype.length; j++)
				{
					inputtype =spltype+String.valueOf(size[i]); //+randomtype[j];
					generate(inputtype, spltype);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}

}
