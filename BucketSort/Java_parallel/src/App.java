
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.lang.*;

public class App {
	public static void main(String[] args){
		
		
		String inputFilePath ;
		System.out.println("Please type in the input file : ");
		Scanner input = new Scanner(System.in);
		inputFilePath = input.next();
		
		String outputFilePath ;
		System.out.println("The output file should be : ");
		outputFilePath = input.next();
		
		int numThread = 1;
		System.out.println("How many threads do you want this time? ");
		numThread = input.nextInt();
	
		App app = new App();
		app.go(inputFilePath,outputFilePath,numThread);
		
	}
	
	public void go(String iPath, String oPath, int numThread){

		/************** read from input.txt ********************/
		
		// readfile class must needed.
		ReadFile r = new ReadFile();
		r.openFile(iPath);
		r.readFile();
		
		ArrayList<String> allString = new ArrayList<String>();
		allString = r.getAllString();
		// read all strings from file and store them into allString
		
		r.closeFile();
		
		/********** create 94 buckets and give them the data ********/
		/********** a better idea: let a BucketManager do this ********/

		final int N_BUCKETS = 94;
		ArrayList<Bucket> bucketList = new ArrayList<Bucket>();
		
		for(int i=0 ; i<N_BUCKETS ; i++){
			bucketList.add(new Bucket());
		}
		
		// copy all the keys to "buckets"
		int rightBucketIndex = 0; // which bucket to add to. 
		for(int i=1; i<allString.size() ; i++){ // i=1 是size value. not elem
			char initialCharacter = allString.get(i).charAt(0);
			int ascii = (int) initialCharacter;
			rightBucketIndex = ascii - 0x21;
			bucketList.get(rightBucketIndex).addToBucket(allString.get(i));
		}
		
		/************** create a threadpool ****************/
		/******** let all threads sort all elemens. ********/
		
		long timeStart=System.currentTimeMillis(); //获取开始时间

		//int numOfCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numThread);
					

		for(int i=0; i<N_BUCKETS ; i++){
			executor.submit(new SortProcess(bucketList.get(i)));
		}

		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long timeEnd=System.currentTimeMillis(); //获取结束时间
		System.out.println("Computing needs ： "+(timeEnd-timeStart)+" ms"); 
		
		
		/************** write to  a file ********************/
		 
		// createfile.java must needed.
		
		WriteFile g = new WriteFile(allString);
		g.openFile(oPath);		
		
		// write the sorted strings to file
		for(int j=0 ; j<N_BUCKETS; j++){
			 //write all
			for(int i=0 ; i<bucketList.get(j).data.size(); i++){
				g.addRecords(bucketList.get(j).data.get(i));	
			}
		}
		
		g.closeFile();
		
	}
}
