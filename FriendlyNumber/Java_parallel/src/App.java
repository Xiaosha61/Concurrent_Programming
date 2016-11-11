import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
public class App {

	static ArrayList<Integer> listStart = new ArrayList<Integer>();
	static ArrayList<Integer> listEnd = new ArrayList<Integer>();
	static int numThread = 1;
	
	public static void main(String[] args){
			
		App app = new App();

		System.out.println("Please type in the data you want to test:");

		app.readInput(listStart, listEnd, numThread);

		/***************** do real work one line after one line **************/
		 
		long timeStart=System.currentTimeMillis(); 

		for(int i=0; i<listStart.size(); i++){
			
			int start = listStart.get(i);
			int end = listEnd.get(i);
			
			System.out.printf("%s %d %s %d\n", "Number",start," to",end);

			int numOfElements = end-start+1;
			int[] numerator = new int[numOfElements];// fenzi 
			int[] denominator = new int[numOfElements]; // fenmu 

			// fork the job (get all numbers' abundancy)
			//int numOfCores = Runtime.getRuntime().availableProcessors();
			
			ExecutorService executor = Executors.newFixedThreadPool(numThread);///***///
			
			AbundancyCalculator.start = start;
			AbundancyCalculator.end = end;
			
			for(int j=start ; j<=end; j++){
				executor.submit(new AbundancyCalculator(j,numerator,denominator));
			}
			
			executor.shutdown();
			
			try {
				executor.awaitTermination(10, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// fork the job(compare and print) .
			executor = Executors.newFixedThreadPool(numThread);///***///
						
			Comparer.a = numerator;
			Comparer.b = denominator;
			Comparer.n = numOfElements;//avoid pass those value for every instance
			Comparer.start = start;
			Comparer.lock = new Object();
						
			for(int j=0 ; j<numOfElements; j++){
				executor.submit(new Comparer(j));
			}
						
			executor.shutdown();
						
			try {
				executor.awaitTermination(10, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		long timeEnd=System.currentTimeMillis(); 
		System.out.println("Running time is： " + (timeEnd-timeStart) + "ms"); 
	
		
	}
	
	public void readInput(ArrayList<Integer> listStart, ArrayList<Integer> listEnd, int numThread){
		/**************** standard input ----> arraylist.**************/
		Scanner input ;
		
		while(true){
			
			input = new Scanner(System.in);
			
			int a = input.nextInt();
			int b = input.nextInt();
			
			if( a == 0 && b == 0){
				System.out.println("How many threads do you need this time?");
				this.numThread = input.nextInt();

				break;
			}
			
			listStart.add(a);
			listEnd.add(b);
		}
		
		input.close();
		
	}
	
}