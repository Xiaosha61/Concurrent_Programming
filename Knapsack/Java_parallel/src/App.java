import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	ArrayList<Item> itemList = new ArrayList<Item>();
	int M = 0; // the capacity of package.
	int N = 0; // the number of item's kinds

	public static void main(String[] args){
	
		App app = new App();
		app.go();

	}
	
	private void go(){
		
		/*****************  Read input  **********************/
		int numThread = 1;
		
		Scanner input = new Scanner(System.in);
			 
		int lineCt = 0;
		
		while(true){
			
			lineCt++;
			
			int num1 = input.nextInt();
			int num2 = input.nextInt();
			
			if(lineCt == 1){
				N = num1;// 
				M = num2;//
			}
			else{
				itemList.add(new Item(num1,num2));
				if(lineCt == N+1){
					System.out.println("How many threads do you want this time?");
					numThread = input.nextInt();
					break;
				}
			}
		}
		
		input.close();

		/*****************  DP with Multi-Threads  **********************/

		int[] value = new int[M+1]; // shared array by all threads.

		long timeStart = System.currentTimeMillis();

		//int numOfCores = Runtime.getRuntime().availableProcessors();

		ExecutorService executor = Executors.newFixedThreadPool(numThread);
				
		for(int i=0; i< itemList.size() ; i++){
			executor.submit(new DynamicalProcess(i,value,M,itemList));
		}
	 
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		long timeEnd = System.currentTimeMillis();
		System.out.println("The execution time is: "+ (timeEnd-timeStart) + "ms");

		System.out.println(value[M]); // 表格右下角的数字即为最终结果。

	}
}
	