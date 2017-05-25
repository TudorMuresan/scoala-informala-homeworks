package ro.sci.tileshop;

/**
 * Surface priced tile.
 * @author Tudor Muresan
 *
 */
public class STile extends AbstractRectangularTile {

	/**
	 * Constructor for rectangular square meter priced tiles with price and sizes.
	 * @param priceForSTile Current price for S type tiles.
	 * @param sTileLength Length of the S tyle tile 
	 * @param sTileWidth Width of the S tyle tile 
	 */
	public STile(double priceForSTile, double sTileLength, double sTileWidth) {
		super(priceForSTile, sTileLength, sTileWidth);
	}

	/**
	 * Method used to calculate the price to cover a surface.
	 * @param surfaceToCover Surface needed to cover.
	 * @return the price for covering a surface with square meter priced tiles.
	 */
	@Override
	public double calculatePrice(double surfaceToCover) {
		if(surfaceToCover <=0){
			System.err.println("Error!You must type a valid surface. Must be greater than 0");
		}
		return surfaceToCover * getPrice();
	}

}
