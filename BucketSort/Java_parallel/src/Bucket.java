 
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.DataLine;

public class Bucket {
	ArrayList<String> data = new ArrayList<String>();// the data in every bucket

	// add those elements who should be this bucket.
	public void addToBucket(String s){
		this.data.add(s);
	}
}
