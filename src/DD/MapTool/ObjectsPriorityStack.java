package DD.MapTool;

import java.io.Serializable;
import java.util.PriorityQueue;



public class ObjectsPriorityStack implements Serializable{

	private static final long serialVersionUID = 3048624868543442456L;
	private  PriorityQueue<Objects> pQueue;
	private OPSComparator opsc;
	public ObjectsPriorityStack() {
		opsc = new OPSComparator();
		pQueue = new PriorityQueue<Objects>(2,opsc);
	}
	
	public PriorityQueue<Objects> getPQueue(){
		return pQueue;
	}
	
	
	/*
	 * this returns an array of Objects that are in increasing prior
	 */
	public Objects[] toArray(){
		PriorityQueue<Objects> foo = pQueue;
		Objects[] bar = new Objects[foo.size()];
		for (int i = 0; i < bar.length; i++) {
			bar[i] = foo.poll();
		}
		return bar;
	}
	
	public int size(){
		return pQueue.size();
	}
	
	public Objects next(){
		return pQueue.iterator().next();
	}
	
	public void pop(){
		 pQueue.remove();
	}
	
	public Objects peek(){
		return pQueue.peek();
	}
	
	public void push(Objects obj){
		pQueue.add(obj);
	}
	


}
