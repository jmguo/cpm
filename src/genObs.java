import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


public class genObs {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String[] spltype=new String[]{"Apache", "LLVM", "X264", "BDBC", "BDBJ", "SQL"};
		String[] size = new String[]{"N", "N2", "N3", "I2"};
				
		String inputtype="";
		
		String totalobsrate = "./dataset/" + "TotalObsRate" + ".csv";
		String totalobstime = "./dataset/" + "TotalObsTime" + ".csv";

		FileOutputStream fos1 = new FileOutputStream(new File(totalobsrate));
		fos1.close();
		FileOutputStream fos2 = new FileOutputStream(new File(totalobstime));
		fos2.close();
		
		try {
			for(int i=0; i<spltype.length; i++){

					String ratefile = "./dataset/" + spltype[i].trim() + "TotalRelErr" + ".csv";
//					String ratefile = "./dataset/" +  "test.csv";

					String timefile = "./dataset/" + spltype[i].trim() + "TotalTime" + ".csv";

					BufferedReader br1 = new BufferedReader(new FileReader(new File(ratefile)));
					String line1="";
					String[] linelist1=null;
					boolean firstline1=true;
					while( (line1 = br1.readLine()) != null){
						if(firstline1==true){
							firstline1=false;
							continue;
						}
						else{
							linelist1=line1.split(",", -1);
							if(linelist1[0]!=null && linelist1[0].length()>0)
								WriteStreamAppend.method1(totalobsrate, spltype[i].trim()+","+"N"+","+linelist1[0]+"\r\n");
							if(linelist1[1]!=null && linelist1[1].length()>0)
								WriteStreamAppend.method1(totalobsrate, spltype[i].trim()+","+"N2"+","+linelist1[1]+"\r\n");
							if(linelist1[2]!=null && linelist1[2].length()>0)
								WriteStreamAppend.method1(totalobsrate, spltype[i].trim()+","+"N3"+","+linelist1[2]+"\r\n");
							if(linelist1[3]!=null && linelist1[3].length()>0)
								WriteStreamAppend.method1(totalobsrate, spltype[i].trim()+","+"I2"+","+linelist1[3]+"\r\n");
						}
					}
					
					BufferedReader br2 = new BufferedReader(new FileReader(new File(timefile)));
					String line2="";
					String[] linelist2=null;
					boolean firstline2=true;
					while( (line2 = br2.readLine()) != null){
						if(firstline2==true){
							firstline2=false;
							continue;
						}
						else{
							linelist2=line2.split(",", -1);
							if(linelist1[0]!=null && linelist1[0].length()>0)
							WriteStreamAppend.method1(totalobstime, spltype[i].trim()+","+"N"+","+linelist2[0]+"\r\n");
							if(linelist1[1]!=null && linelist1[1].length()>0)
							WriteStreamAppend.method1(totalobstime, spltype[i].trim()+","+"N2"+","+linelist2[1]+"\r\n");
							if(linelist1[2]!=null && linelist1[2].length()>0)
							WriteStreamAppend.method1(totalobstime, spltype[i].trim()+","+"N3"+","+linelist2[2]+"\r\n");
							if(linelist1[3]!=null && linelist1[3].length()>0)
							WriteStreamAppend.method1(totalobstime, spltype[i].trim()+","+"I2"+","+linelist2[3]+"\r\n");
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");

	}

}
