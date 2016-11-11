
import java.io.*;
import java.util.*;

public class ReadFile {
	
	private Scanner x;
	public ArrayList<String> aSort = new ArrayList<String>();
	
	public void openFile(String filePath){
		try{
			x = new Scanner(new File(filePath));
		}
		catch(Exception e){
			System.out.println("could not find file");
		}
	}
	
	public void readFile(){
		
		while(x.hasNext()){
			
			String a = x.next();
					
			aSort.add(a);
		
		}
	}
	
	public ArrayList<String> getAllString(){
		return aSort;
	}
	
	public void closeFile(){
		x.close();
	}
	

}
