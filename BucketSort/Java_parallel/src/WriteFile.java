
import java.util.*;
import java.lang.*;
import java.util.*;

public class WriteFile {

	private Formatter x;
	ArrayList<String> stringList = new ArrayList<String>();
	
	public WriteFile(ArrayList<String> stringList){
		this.stringList = stringList;
	}
	
	public void openFile(String outputFilePath){
		try{
			x = new Formatter(outputFilePath);
		}
		catch(Exception e){
			System.out.println("you got an error!");
		}
	}
	
	public void addRecords(String s){
		x.format("%s\n", s);
	}
	
	public void closeFile(){
		x.close();
	}

	
}
