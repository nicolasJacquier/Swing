package ppe3;

import java.util.Comparator;

public class CoefComparator implements Comparator<Integer>{
	
	public int compare(Integer c1, Integer c2) {
		
        return new Integer(c1.compareTo(c2));
        
    }

}
