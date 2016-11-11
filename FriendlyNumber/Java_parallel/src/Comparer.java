public class Comparer implements Runnable{
	
	private int positionInArray;
	public static int[] a;
	public static int[] b;
	public static int n; // number of elements in a and b. n(a) = n(b).
	public static int start;
	public static Object lock;
 	
	public Comparer(int positionInArray){
		this.positionInArray= positionInArray;
	}
	
	public void run() {
		// 这个算法是以一个值为标准，然后把它后面的所有项都和它对比，相等则输出。
		for(int i=positionInArray+1; i<n; i++){
			if(a[positionInArray] == a[i] && b[positionInArray] == b[i]){
				synchronized(lock){
					System.out.printf("%d %s %d %s\n", positionInArray+start,"and", i+start, "are FRIENDLY");			
				}
			}
		}
		
	}

}
