import java.util.ArrayList;

public class DynamicalProcess implements Runnable{
	
	Object o = new Object();// if static then lock the whole array, in this way ,only lock the single node you are writing.
	int i;// 代表对第i个新item的一组操作。
	int[] value ;
	ArrayList<Item> itemList = new ArrayList<Item>();
	int M ; // the capacity of package.
	
	public DynamicalProcess(int i,int[] value, int M, ArrayList<Item> itemList){
		this.i = i;
		this.value = value;
		this.itemList = itemList;
		this.M = M;
	}
	// lock this single node value[p] of the array if a thread t1 needs to update it
	// to make sure that no synchronization problem happens here
	// also avoid locking the whole array which will slow down the program.
	// the lock process begins with reading and end after writing.
	public void run() {
		// TODO Auto-generated method stub
		// dynamical programming
		
		// s,p are local variables. no lock needed.
		// M, itemList.get(this.i).weight,itemList.get(this.i).value, are constants, not gonna change, no lock needed.
		
		for(int s=itemList.get(this.i).weight; s<=M; s++){ // itemList.get(this.i).weight之前的包容量太小，放不下，都还是0.
			int p=s-itemList.get(this.i).weight; // s代表了value数组表中现在要写的位置。p则表示除去要加不加的new item后的容量包的最优值。在前面写过的那个。
			int newValue = value[p] + itemList.get(this.i).value;
			// newValue： 如果决定把新的item加进去，决定使用new item，能够得到的value。
			// value[p]： 加入新的item的话，之后还剩下的空间能够实现的最大价值。
			
			// value[s]可能你现在读到一个值是10，但是在你比较后发现你现在的newValue＝20比value[s]大
			// 正准备置换的时候睡着了，
			// 这个时候有另一个人把value[s]改成了30
			// 你再醒来的时候不知道现在已经是30，就把value[s]还改成了20.就会出错。
			synchronized(o){
				if(newValue > value[s]){
					value[s] = newValue;
				}
			}
			
		}
		
	}		
}

