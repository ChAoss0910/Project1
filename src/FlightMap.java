import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Stack;
import java.util.Vector;
/**
 * FlightMap class
 * @author Hao_Chen
 */
public class FlightMap {
	public HashMap<String, List<String>> originMap;
	public HashMap<String, Stack<String>> pathMap;
	public List<Flight> flights;
	public List<String> destination;
	public HashMap<Flight, Integer> costs;
	/**
	 * Constructor
	 */
	public FlightMap() {
		this.originMap = new HashMap<String, List<String>>();
		this.pathMap = new HashMap<String, Stack<String>>();
		this.flights = new ArrayList<Flight>();
		this.destination = new ArrayList<String>();
		this.costs = new HashMap<Flight, Integer>();
	}
	/**
	 * 
	 * @return the length of the longest path in the map
	 */
	public int getMaxLength() {
		Vector<Integer> pathLen = new Vector<Integer>();
		for(String target: destination) {
			Stack<String> currPath = pathMap.get(target);
			if(currPath == null)
				continue;
			pathLen.add(currPath.size());
		}
		return Collections.max(pathLen);
	}
	/**
	 * 
	 * @param origin: start city
	 * @param path: the flight path from the start city 
	 */
	public void dfs(String origin, Stack<String> path) {

		String curr = path.peek();
		List<String> targetList = originMap.get(curr);
		if(targetList == null)
			return;
		if (targetList.isEmpty()){
			return;
		}
		for(String s: targetList) {
			if(origin == curr) {
				this.costs.put(new Flight(origin, s), this.costs.get(new Flight(curr, s)));
				path.add(s);
				Stack<String> currStack = (Stack<String>) path.clone ();
				this.pathMap.put(s,currStack);
			}
				
			else {
				if(costs.containsKey(new Flight(origin, s))) {
					if(this.costs.get(new Flight(origin, s)) > this.costs.get(new Flight(origin, curr))+ this.costs.get(new Flight(curr, s))) {
						this.costs.put(new Flight(origin, s), this.costs.get(new Flight(origin, curr))+ this.costs.get(new Flight(curr, s)));
						path.add(s);
						Stack<String> currStack = (Stack<String>) path.clone ();
						this.pathMap.put(s,currStack);
						continue;
					}
					else
						continue;
				}
				else {
					this.costs.put(new Flight(origin, s), this.costs.get(new Flight(origin, curr))+ this.costs.get(new Flight(curr, s)));
					path.add(s);
					Stack<String> currStack = (Stack<String>) path.clone ();
					this.pathMap.put(s,currStack);
				}
					
			}
			dfs(origin, path);
			path.pop();
		}
	}
	
}
