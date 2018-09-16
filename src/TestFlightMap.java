import static org.junit.Assert.assertEquals;

import java.lang.invoke.VolatileCallSite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

public class TestFlightMap {
	@Test
	public void test_getMaxLength() {
		FlightMap flightMap = new FlightMap();
		List<String> destination = flightMap.destination;
		destination.add("A");
		Stack<String> first = new Stack<String>();
		first.push("D");
		first.push("E");
		destination.add("B");
		Stack<String> second = new Stack<String>();
		second.push("C");
		second.push("D");
		second.push("E");
		flightMap.pathMap.put("A", first);
		flightMap.pathMap.put("B", second);
		int result = flightMap.getMaxLength();
		assertEquals(3, result); 
		
	}
	@Test
	public void test_dfs() {
		FlightMap flightMap = new FlightMap();
		Flight ab = new Flight("A", "B");
		Flight ac = new Flight("A", "C");
		Flight cb = new Flight("C", "B");
	
		flightMap.flights.add(ab);
		flightMap.flights.add(ac);
		flightMap.flights.add(cb);
		
		flightMap.costs.put(ab, 100);
		flightMap.costs.put(ac, 100);
		flightMap.costs.put(cb, 100);
		
		List<String> a = new ArrayList<String>();
		a.add("B");
		a.add("C");
		List<String> b = new ArrayList<String>();
		List<String> c = new ArrayList<String>();
		c.add("B");
		flightMap.originMap.put("A", a);
		flightMap.originMap.put("B", b);
		flightMap.originMap.put("C", c);
		
		Stack<String> path = new Stack<String>();
		path.push("A");
		flightMap.dfs("A", path);
		
		Stack<String> b_path = flightMap.pathMap.get("B");
		String first = b_path.pop();
		String second = b_path.pop();
		boolean result = ((first == "B") && (second == "A"));
		assertEquals(true, result); 
	}
	

}
