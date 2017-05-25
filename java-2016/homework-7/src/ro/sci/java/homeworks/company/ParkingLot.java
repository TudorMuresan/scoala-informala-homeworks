package ro.sci.java.homeworks.company;
/**
* The ParkingLot class represents one parking lot from a parking.
* @author Tudor Muresan
*/
public class ParkingLot {
	
	private Integer parkingNumber;
	/**
	  * Constructor.
	  * @param parkingNumber The number of the parking lot.
	  */
	public ParkingLot(int parkingNumber){
		this.parkingNumber = parkingNumber;
	}
	
	/** The method returns the unique number of this parking lot.
	  * parkingNumber The number of this parking lot.
	  * @return parkingNumber Return the number of the parking lot.
	  */
	public Integer getParkingNumber(){
		return parkingNumber;
	}
	
}
