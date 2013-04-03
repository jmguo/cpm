import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Verify_Testing_Set {

	public static void getRules(String type){
		
	}
	
	public static void validate(String sourcepath, String traintype) throws IOException{
		
		//read sourcefile and store results to validationfile
		BufferedReader br = new BufferedReader(new FileReader(new File(sourcepath)));

		String validatefile = "./dataset/" + traintype + "validation" + ".csv";
		FileOutputStream fos1 = new FileOutputStream(new File(validatefile));
		fos1.close();
		
		String line = null;
		boolean markfirstline = true;
		String labelline = null;
		String rowline = null;
		
		String DomFlist = null;
		String SubFlist = null;
		double DomEst = 0;
		double SubEst = 0;
		double Err100 = 0;
		double Err91 = 0;
		double Err82 = 0;
		double Err73 = 0;
		double stdvalue =0;
		double estvalue =0;
		
		DecimalFormat df1 = new DecimalFormat("0.0000");
		DecimalFormat df2 = new DecimalFormat("0.0000");

		//should create an abstract Configuration Class for reuse
//		ApacheConfiguration config = new ApacheConfiguration();
//		BDBJConfiguration config = new BDBJConfiguration();
		Configuration config = new Configuration();
//		LLVMConfiguration config = new LLVMConfiguration();
//		X264Configuration config = new X264Configuration();
//		SQLConfiguration config = new SQLConfiguration();


		while( (line = br.readLine()) != null){
			//separate the label line and other lines;
			if(markfirstline){
//				labelline = line+",[DomFlist]"+",[SubFlist]"+",DomEst"+",SubEst"+",Err100"+",Err91"+",Err82"+",Err73";
				labelline = line+",[DomFlist]"+",DomEst"+",RelErr";
				markfirstline = false;
				WriteStreamAppend.method1(validatefile, labelline+"\r\n");
			}
			else{
				//foreach row/line/configuration

				DomFlist = config.getDomFlist(line, traintype);
				DomEst = config.getDomEst(line, traintype);
				
				String sDomEst = df1.format(DomEst);
				
				String[] lineset = line.split(",");
				stdvalue = Double.parseDouble(lineset[lineset.length-1]);
				
				estvalue = DomEst;
				Err100 = (Math.abs(stdvalue - estvalue))/stdvalue;
				String sErr100 = df2.format(Err100);
				
//				estvalue = DomEst*0.9+SubEst*0.1;
//				Err91 = (Math.abs(stdvalue - estvalue))/stdvalue;
//				String sErr91 = df2.format(Err91);
//
//				estvalue = DomEst*0.8+SubEst*0.2;
//				Err82 = (Math.abs(stdvalue - estvalue))/stdvalue;
//				String sErr82 = df2.format(Err82);
//
//				estvalue = DomEst*0.7+SubEst*0.3;
//				Err73 = (Math.abs(stdvalue - estvalue))/stdvalue;
//				String sErr73 = df2.format(Err73);

				rowline = line + ",[" + DomFlist + "]," + sDomEst +"," + sErr100;
				WriteStreamAppend.method1(validatefile, rowline+"\r\n");

			}
		}
		//store to files


			
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

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

//		String[] randomtype = new String[]{"R1", "R2", "R3", "R4", "R5"};
		String[] randomtype = new String[]{"R5"};

		String inputtype="";
		
//		validate(sourcefilepath, "BDBC18R1");
		
		try {
			for(int i=0; i<size.length; i++)
				for(int j=0; j<randomtype.length; j++){
					inputtype =spltype+String.valueOf(size[i])+randomtype[j];
					//String sourcefilepath="./dataset/"+inputtype+"testing.csv";
					//for SQL100configs
					String sourcefilepath="./dataset/SQL100testing.csv";
					validate(sourcefilepath, inputtype);

				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}

}
