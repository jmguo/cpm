import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Generate_Training_and_Testing_Sets {

	public static void generate(String sourcepath, String type, int trainpercent, int testpercent) throws IOException{

		//output sourcefile to Arraylist
		BufferedReader br = new BufferedReader(new FileReader(new File(sourcepath)));
		List<String> linelist = new ArrayList(); 
		String line = null;
		boolean markfirstline = true;
		String labelline = null;
		while( (line = br.readLine()) != null){
			//separate the label line and other lines;
			if(markfirstline){
				labelline = line;
				markfirstline = false;
			}
			else{
				linelist.add(line);
			}
		}
		
		//shuffle
		Collections.shuffle(linelist);
		
		//copy linelist to restlist
		List<String> restlist = new ArrayList();
		restlist.addAll(linelist);
		
		//store to files
		String trainfile = "./dataset/" + type + "training" + ".csv";
		String testfile = "./dataset/" + type + "testing" + ".csv";
		
		FileOutputStream fos1 = new FileOutputStream(new File(trainfile));
		fos1.close();
		FileOutputStream fos2 = new FileOutputStream(new File(testfile));
		fos2.close();
		
		//write the label line
		WriteStreamAppend.method1(trainfile, labelline.trim()+"\r\n");
		WriteStreamAppend.method1(testfile, labelline.trim()+"\r\n");
		
		//write training file
		
		int i;
		//modify trainpercent as the number
//		for(i = 0; i < (int)(linelist.size()*trainpercent*0.01); i++){
		int k=0;
		
		//test skewed
		// for each SPL
		Set<Integer> set = new  HashSet<Integer>();
		//set.size = 0.25*fl.length, 0.5*fl.length
		while(set.size()<12){
			int ran= (int)(Math.random()*38);
			if (ran != 0 && ran != 7 && ran != 10 && ran != 13 && ran != 24 && ran != 27 && ran != 31 && ran != 34 )
				set.add(ran);
		}
		Object[] ints = set.toArray(); 
		
		for(i = 0; k < trainpercent && i < restlist.size()-1; i++){
			
//			WriteStreamAppend.method1(trainfile, linelist.get(i).toString().trim()+"\r\n");
			
			//random select an element
			int index=(int)(Math.random()*restlist.size());
//			WriteStreamAppend.method1(trainfile, restlist.get(index).toString().trim()+"\r\n");
			//test skewed
			String[] fl=restlist.get(index).toString().trim().split(",");

			//set skewed features
			if( fl[Integer.parseInt(ints[0].toString())].contains("Y") && fl[Integer.parseInt(ints[1].toString())].contains("Y") 
					&& fl[Integer.parseInt(ints[2].toString())].contains("Y") && fl[Integer.parseInt(ints[3].toString())].contains("Y")
					&& fl[Integer.parseInt(ints[4].toString())].contains("Y") && fl[Integer.parseInt(ints[5].toString())].contains("Y") 
					&&fl[Integer.parseInt(ints[6].toString())].contains("Y") && fl[Integer.parseInt(ints[7].toString())].contains("Y") 
					&& fl[Integer.parseInt(ints[8].toString())].contains("Y") && fl[Integer.parseInt(ints[9].toString())].contains("Y")
					&& fl[Integer.parseInt(ints[10].toString())].contains("Y") && fl[Integer.parseInt(ints[11].toString())].contains("Y") ){
				WriteStreamAppend.method1(trainfile, restlist.get(index).toString().trim()+"\r\n");
				restlist.remove(index);
				k++;
			}
		}


		
		//write testing file
		//modify to store all of the rest
//		for(int j=linelist.size()-1; j>i && j>(int)(linelist.size()*(100-testpercent)*0.01); j--){
		//test for BDBC
//		for(int j=i; j< i+ (int)(linelist.size()*testpercent*0.01); j++){
//			WriteStreamAppend.method1(testfile, linelist.get(j).toString().trim()+"\r\n");
//		}
		for(int j=0; j< restlist.size(); j++){
//			String[] fl=linelist.get(j).toString().trim().split(",");
//			if(fl[0].contains("Y")){
				//for SQL using 100 random for testing
				//WriteStreamAppend.method1(testfile, linelist.get(j).toString().trim()+"\r\n");
//			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {

//		modify trainsetpercent as the number of training set
//		int trainsetpercent = 154;
//		int testsetpercent = 30;
		//modify to test all of the rest, so now the parameter useless, but keep it for future use
		int testsetpercent = 30;


		//from R1 to R5 for 5 times of random selection
		String[] randomtype = new String[]{"R1", "R2", "R3", "R4", "R5"};
		
		String inputtype="";
		int trainsetsize=0;
		
		//set trainingsetsize as 1N, 2N, 3N, I^2
		
//		String spltype="BDBC";
////		int[] size = new int[]{18, 36, 54, 139};
//		int[] size = new int[]{139};

		
//		String spltype="Apache";
////		//int[] size = new int[]{9, 18, 27, 29};
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

		
		String sourcefilepath="./dataset/"+spltype+"All.csv";
		
		try {
			for(int i=0; i<size.length; i++)
				for(int j=0; j<randomtype.length; j++){
					inputtype =spltype+String.valueOf(size[i])+randomtype[j];
					trainsetsize = size[i];
					generate(sourcefilepath, inputtype, trainsetsize, testsetpercent);
					
					String rawrulefile = "./dataset/" + inputtype + "rawrule" + ".txt";
					FileOutputStream fos1 = new FileOutputStream(new File(rawrulefile));
					fos1.close();

				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");

	}

}
