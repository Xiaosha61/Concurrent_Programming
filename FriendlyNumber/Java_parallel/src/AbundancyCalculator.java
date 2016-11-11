
public class AbundancyCalculator implements Runnable {
	
	private int num;
	private int[] numerator;
	private int[] denominator;
	private int gdc;
	
	
	public static int start;
	public static int end;
	
	public AbundancyCalculator(int num,int[] numerator,int[] denominator) {
		this.num = num;  // num itself is the Denominator
		this.numerator = numerator;
		this.denominator = denominator;
	    this.gdc = this.getGDC();
	}
	
	public int getGDC(){
		return new GDC(this.getSum(), num).getResult();
	}
	
	public int getNumerator(){
		// 约分后分子 after reduction of the fraction
		return this.getSum()/this.gdc;
	}

	public int getDenominator(){
		// 约分后分母 after reduction of the fraction
		return num/this.gdc;
	}
	
	public int getSum(){
		int sum = 1 + num;
		int done = num;
		
		int factor = 2;
		
		while(factor < done){
			if( (num % factor ) == 0){
				sum += (factor  + ( num/factor  ));
				if( (done = num/factor ) == factor  ){
					sum -= factor ;
				}
			}
			factor ++ ;
		}	
		return sum;
		
	}
	
	public void run() {		
		this.numerator[num-start] = this.getNumerator();
		this.denominator[num-start] = this.getDenominator();	
	}
	
}


