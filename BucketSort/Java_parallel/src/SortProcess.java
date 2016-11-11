
import java.util.Collections;

public class SortProcess implements Runnable{
	
	private Bucket b;
	
	public SortProcess(Bucket b){
		this.b = b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Collections.sort(b.data);
	}
	 
}
