import java.io.File;


import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import javax.print.DocFlavor.STRING;
import javax.swing.Painter;
import javax.swing.plaf.InputMapUIResource;
import org.w3c.dom.ls.LSInput;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
/**
 * 
 * @author Hao_Chen
 * SearchMap class
 * main function
 */
public class SearchMap {
	public static void main(String args[]) {
		try {
			String inputName = "../input.txt";
			FlightMap flightMap = new FlightMap();
			List<Flight> flights = flightMap.flights;
			List<String> destination = flightMap.destination;
			HashMap<Flight, Integer> costs = flightMap.costs;
			File inputfile= new File(inputName);
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(inputfile)); 
			BufferedReader br = new BufferedReader(reader); 
			String line= "";
			String origin = "";
			line = br.readLine();
			origin = line;
			destination.add(origin);
			while(line != null) {
				line = br.readLine();
				if (line == null)
					break;
				String[] word= line.split(" ");
				if (!destination.contains(word[0]) && word[0]!= origin)
					destination.add(word[0]);
				if(!destination.contains(word[1]) && word[1]!= origin)
					destination.add(word[1]);
				Flight curr = new Flight(word[0], word[1]);
				costs.put(curr, Integer.parseInt(word[2]));
				flights.add(curr);
			}
			
			//Key is each city, Value is the list of cities that the key city can reach in one step
			HashMap<String, List<String>> originMap = flightMap.originMap;
			HashMap<String, Stack<String>> pathMap = flightMap.pathMap;
			for(Flight f: flights) {
				String o = f.getOrigin();
				String t = f.getTarget();
				if(!originMap.containsKey(o)) {
					List<String> newlist = new ArrayList<String>();
					originMap.put(o, newlist);
				}
				((List<String>) originMap.get(o)).add(t);	
			}
			Stack<String> path = new Stack<String>();
			path.push(origin);
			flightMap.dfs(origin, path);
			
			File writename = new File("../output.txt"); 
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write("Destination		Flight Route From "+origin+"			Total Cost\r\n"); 
			
			int maxLen = flightMap.getMaxLength();
			for(String target:destination) {
				
				Flight cur = new Flight(origin, target);
				if(!costs.containsKey(cur))
					continue;
				int cost = costs.get(cur);
				Stack<String> currPath = pathMap.get(target);
				int len = currPath.size();
				int remain = maxLen - len;
				String supp = "";
				//Supp
				for(int i=0;i < remain;i++) {
					supp += "  ";
				}
				if(currPath == null)
					continue;
				out.write(target+" 				" );
				Stack<String> reverse = new Stack<String>();
				while(!currPath.isEmpty()) {
					String currCity = currPath.pop();
					reverse.push(currCity);
				}
				while(!reverse.isEmpty()) {
					String currCity= reverse.pop();
					if(reverse.size() != 0)
						out.write(currCity+", ");
					else
						out.write(currCity+supp+"			"+cost+"\r\n");
				}
			}
			out.flush();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
