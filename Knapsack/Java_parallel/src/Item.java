public class Item {
	public int value; 
	public int weight;
	public float density;
	
	public Item(int v, int w){
		this.value = v;
		this.weight = w;
		this.density = (float)value/(float)weight;
	}

	public String toString(){
		return this.value + " \t " + this.weight + " \t " + this.density + "\n";
	}
}
