import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Configuration {

	/**
	 * @param args
	 */

	public static double getDomEst(String conf, String type) throws NumberFormatException, IOException {
						
		double DomEst = 0;
		
		String parserulefile = "./dataset/" + type + "parseresult" + ".txt";

		BufferedReader br = new BufferedReader(new FileReader(new File(parserulefile)));
		String line="";
		int i = 0;
		
		while( (line = br.readLine().trim()) != null){
			if(i==ruleno){
				DomEst=Double.parseDouble(line);
				break;
			}
			else
				i++;
				
		}
		
		return DomEst;
		
	}
	
	
	
	public static String getDomFlist(String conf, String type) throws IOException {

		String[] conflist = null;
		conflist = conf.split(",");

		String DomFlist = null;
		
		String parserulefile = "./dataset/" + type + "parserule" + ".txt";

		BufferedReader br = new BufferedReader(new FileReader(new File(parserulefile)));
		String line="";
		String[] lineflist = null;
		int featureid=0;
		String featurevalue="";
		boolean found=false;
		
		ruleno=0;
		
		while( (line = br.readLine()) != null){
			lineflist = line.split(" ");
			for(int i=0; i<lineflist.length; i++){
				featureid = Integer.parseInt(lineflist[i].substring(0, lineflist[i].length()-1));
				featurevalue = lineflist[i].substring(lineflist[i].length()-1, lineflist[i].length());
				if(conflist[featureid-1].contains(featurevalue)){
					found=true;
					continue;
				}
				else{
					found=false;
					break;
				}
			}
			if(found==false){
				ruleno++;
				continue;
			}
			else{
				DomFlist= line;
				break;
			}
		}
		
		return DomFlist;
	}
	
	List<String> rulelist = new ArrayList(); 
	List<Double> perflist = new ArrayList(); 

	public static int ruleno=0;
	
	public static void main(String[] args) throws IOException {

		String inputConf = "N,Y,Y,N,N,Y,Y,Y,Y,N,N,N,N,Y,N,N,Y,N";
		String inputType = "BDBC18R1";
				
		String domFlist = getDomFlist(inputConf, inputType);
		System.out.println(domFlist);
		
		double domEst = getDomEst(inputConf, inputType);
		System.out.println(domEst);
		
	
	}

}
