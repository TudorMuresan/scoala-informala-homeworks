package ro.sci.tileshop;

public class PTTile extends AbstractRectangularTile{

	/**
	 * Constructor for triangular piece priced tiles with price and sizes.
	 * @param priceForPTTile Price for triangular tile by piece.
	 * @param pTtileLegLength Leg length of the triangular tile.
	 */
	public PTTile(double priceForPTTile, double pTtileLegLength) {
		super(priceForPTTile, pTtileLegLength);
	}

	/**
	 * Method used to calculate the number of tiles needed to cover a needed surface.
	 * Surface to cover is in meters so the method divides the area of piece due to the tile centimeters values.
	 * @param surfaceToCover
	 * @return the number of pieces needed to cover the surface.
	 */
	private int getTotalPieces(double surfaceToCover){	
		if(surfaceToCover <=0){
			System.err.println("Error!You must type a valid surface. Must be greater than 0");
		}
		return (int) Math.ceil(surfaceToCover / (this.calculateArea()/10000));
	}
	
	/**
	 * Method used to calculate the price to cover a surface.
	 * @param surfaceToCover Surface needed to cover.
	 * @return the price for covering a surface with triangular tiles priced by piece.
	 */
	@Override
	public double calculatePrice(double surfaceToCover) {
		return getTotalPieces(surfaceToCover) * getPrice();
		
	}
	
	/**
	 * Calculates the area for a triangular tile piece.
	 * @return the calculated area for a tile.
	 */
	@Override
	public double calculateArea() {
		return (getLegLength() * getLegLength())/2;
	}

}
