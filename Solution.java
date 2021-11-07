package oracleps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

	public static void main(String[] args) {

		
		//add Elements to list
		List<Character> l1 = new ArrayList<Character>();
		l1.add('A');
		l1.add('A');
		List<Character> l2 = new ArrayList<Character>();
		l2.add('A');
		l2.add('M');
		List<Character> l3 = new ArrayList<Character>();
		l3.add('M');
		List<Character> l4 = new ArrayList<Character>();
		l4.add('M');
		l4.add('M');
		l4.add('M');
		l4.add('M');
		l4.add('A');
		l4.add('A');
		l4.add('A');
		List<Character> l5 = new ArrayList<Character>();
		l5.add('A');


		//initialize order objects to add it to final list of order
		order o1 = new order("12", l1, 5);
		order o2 = new order("21", l2, 1);
		order o3 = new order("32", l3, 0.1);
		order o4 = new order("14", l4, 10);
		order o5 = new order("22", l5, 3);
		
		

		//Final list of order
		List<order> lo = new ArrayList<order>();
		lo.add(o1);
		lo.add(o2);
		lo.add(o3);
		lo.add(o4);
		lo.add(o5);

		//Actual logic starts here
		/*
		 * 1.Initialize a priority queue
		 * 2. AvailableSlots = 7 
		 * 3. Check if the SlotsRequired by the order is less than 7 and TimeRequired is less than 150 mins
		 * 4. if yes, further logic continues else exit
		 * 5. if step 4 = true , check if SlotsRequired is less than or equal to Available slots. 
		 * If true - adjust adjust available slots and add to priority queue
		 * if false = step 6
		 * 6. loop till AvailableSlots >= SlotsRequired , calculate  additional timeRequired and adjust Available slots accordingly
		 * 7. Adjust AvailableSlots again w.r.t current order
		 * 8. if time required is < 150 , print as required and add to priority Queue ,
		 *  else adjust the priority queue by added the previous element (buffer)
		 * 
		 * */
		 
		PriorityQueue<order> pq = new PriorityQueue<>();

		int AvailableSlots = 7;
		double TimeAdditional = 0.0;
		for (int i = 0; i < lo.size(); i++) {

			if (lo.get(i).SlotsRequired <= 7 && lo.get(i).TimeRequired <= 150) {
				if (lo.get(i).SlotsRequired <= AvailableSlots) {
					
					AvailableSlots -= lo.get(i).SlotsRequired;
					System.out.println("Order " + lo.get(i).id + " will get delivered in " + lo.get(i).TimeRequired + " minutes");
					pq.add(lo.get(i));
				} else {
					int SlotsRequiredValue = lo.get(i).SlotsRequired;
					order buffer = null;
					while (SlotsRequiredValue > AvailableSlots) {

						if (!pq.isEmpty()) {
							TimeAdditional = Math.max(TimeAdditional, pq.peek().TimeRequired);
							buffer = pq.peek();
							AvailableSlots += pq.poll().SlotsRequired;
						} else {
							System.out.println(
									"Order " + lo.get(i).id + " denied because the restaurant cannot accommodate it");
						}
					}

					
					lo.get(i).TimeRequired = lo.get(i).TimeRequired + TimeAdditional;
					if (lo.get(i).TimeRequired <= 150) {
						AvailableSlots = AvailableSlots - SlotsRequiredValue;
						pq.add(lo.get(i));

						System.out.println(
								"Order " + lo.get(i).id + " will get delivered in " + lo.get(i).TimeRequired + " minutes");
					} else {
						pq.add(buffer);
						System.out.println(
								"Order " + lo.get(i).id + " denied because the restaurant cannot accommodate it");
					}

				}
			}

			else {
				System.out.println("Order " + lo.get(i).id + " denied because the restaurant cannot accommodate it");
			}

		}

	}
}