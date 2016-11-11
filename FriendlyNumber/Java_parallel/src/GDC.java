
public class GDC {
	
	private static int u ;
	private static int v ;
	
	public GDC(int u, int v){
		this.u = Math.max(u, v);
		this.v = Math.min(u, v);

	}
	
	public static int getResult(){
		
		if(v == 0){
			return -1;
		}
		
		else{
			while(u % v !=0){
				
				int temp= u % v;
				u = v;
				v = temp;
			}
			
			return v;	
		}
		
		
	}

}
