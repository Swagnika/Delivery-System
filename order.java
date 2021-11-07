package oracleps;

import java.util.List;

public class order implements Comparable<order> {
	

	String id;
	List<Character> list ;
	double distance;
	boolean containsM=false;
	int SlotsRequired;
	double TimeRequired;
	
	public order(String id, List<Character> list, double distance) {
		super();
		this.id = id;
		this.list = list;
		this.distance = distance;
		this.SlotsRequired = calculateSloTimeRequiredequired(list);
		this.TimeRequired = timeRequired(distance);
	}

	
	//Calculate slots required based on number of 'A's and 'M's
	
     public int calculateSloTimeRequiredequired(List<Character> list) {
    	
        	 int count =0;
    	 for(int i=0;i<list.size();i++) {
    		 if(list.get(i)=='A') {
    			 count++;
    		 }
    		 else {
    			 count+=2;
    			 containsM=true;
    		 }
    	 }
    	 return count;
    	 
     }
	 
     //Calculate time required based on if M is present.
     
     public double timeRequired(double distance2) {
    	 double val =0.0;
    	 if(containsM) {
    		 val = 29.0+(distance2*8);
    	 }
    	 else {
    		 val = 17.0 + (distance2*8);
    	 }
    	 return val;
     }

  
     // order should be sorted acording to time. This will help later in Priority queue
	@Override
	public int compareTo(order o) {
		// TODO Auto-generated method stub
		if(TimeRequired  < o.TimeRequired) {
			return -1;
		}
		else if(TimeRequired > o.TimeRequired) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	
}
