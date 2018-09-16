import javax.print.attribute.standard.RequestingUserName;
/**
 * Flight class
 * @author Hao_Chen
 */
public class Flight {
	private String origin = "";
	private String target = "";
	/**
	 * 
	 * @param o: start city
	 * @param t: end city
	 */
	public Flight(String o, String t) {
		this.origin = o;
		this.target = t;
	}
	/**
	 * @return start city
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * @return end city
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * override equals function (used for hashMap)
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;
        if(!origin.equals(flight.origin) || !target.equals(flight.target))
        		return false;
        return true;
    }
	/**
	 * override hasCode function (used for hashMap)
	 */
    @Override
    public int hashCode() {
    		return origin.hashCode() + target.hashCode();
    }
}
