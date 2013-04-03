import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;


public class Collect_TotalTime_and_RelErr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
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
		
		try {
			for(int i=0; i<size.length; i++){

				String totaltimefile = "./dataset/" + spltype+String.valueOf(size[i]) + "TotalTime" + ".csv";
				String totalvalidatefile = "./dataset/" + spltype+String.valueOf(size[i]) + "TotalRelErr" + ".csv";

				FileOutputStream fos1 = new FileOutputStream(new File(totaltimefile));
				fos1.close();
				FileOutputStream fos2 = new FileOutputStream(new File(totalvalidatefile));
				fos2.close();
				
				for(int j=0; j<randomtype.length; j++){
					inputtype =spltype+String.valueOf(size[i])+randomtype[j];

					String timefile = "./dataset/" + inputtype + "Time" + ".txt";
					String validatefile = "./dataset/" + inputtype + "validation" + ".csv";

					BufferedReader br1 = new BufferedReader(new FileReader(new File(timefile)));
					String line="";
					while( (line = br1.readLine()) != null){
						WriteStreamAppend.method1(totaltimefile, line+"\r\n");
					}
					
					BufferedReader br2 = new BufferedReader(new FileReader(new File(validatefile)));
					String line2="";
					String[] linelist=null;
					boolean firstline=true;
					while( (line2 = br2.readLine()) != null){
						if(firstline==true){
							firstline=false;
							continue;
						}
						else{ 
						linelist=line2.split(",");
						WriteStreamAppend.method1(totalvalidatefile, linelist[linelist.length-1]+"\r\n");
						}
					}
					
				}
					


				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");


	}

}
