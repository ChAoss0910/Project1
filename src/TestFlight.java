import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFlight {
	@Test
	public void test_getOrigin() {
		Flight f = new Flight("A", "B");
		String result = f.getOrigin();
		assertEquals("successful","A", result); 
	}
	@Test
	public void test_getTarget() {
		Flight f = new Flight("A", "B");
		String result = f.getTarget();
		assertEquals("B", result); 
	}
	@Test
	public void test_equals() {
		Flight first = new Flight("A", "B");
		Flight second = new Flight("A", "B");
		boolean result = first.equals(second);
		assertEquals(true, result); 
	}
	@Test
	public void test_hashCode() {
		Flight f = new Flight("A", "B");
		int result = f.hashCode();
		assertEquals(131, result); 
	}
}
