package ro.sci.tileshop;
/**
 * Piece priced tile.
 * @author Tudor Muresan
 *
 */
public class PTile extends AbstractRectangularTile{
	
	/**
	 * Constructor for rectangular piece priced tiles with price and sizes.
	 * @param priceForPTile Price for rectangular type tile by piece.
	 * @param pTileLength Tile length.
	 * @param pTileWidth Tile Width.
	 */
	public PTile(double priceForPTile, double pTileLength, double pTileWidth) {
		super(priceForPTile, pTileLength, pTileWidth);
	}
	
	
	/**
	 * Method used to calculate the number of tiles needed to cover a needed surface.
	 * Surface to cover is in meters so the method divides the area of piece due to the tile centimeters values.
	 * @param surfaceToCover
	 * @return the number of pieces needed to cover the surface.
	 */
	private int calculateNeededPieces(double surfaceToCover){	
		return (int) Math.ceil(surfaceToCover / (calculateArea()/10000));
	}
	
	/**
	 * Method used to calculate the price to cover a surface.
	 * @param surfaceToCover Surface needed to cover.
	 * @return the price for covering a surface with rectangular tiles priced by piece.
	 */
	@Override
	public double calculatePrice(double surfaceToCover) {
		if(surfaceToCover <=0){
			System.err.println("Error!You must type a valid surface. Must be greater than 0");
		}
		return calculateNeededPieces(surfaceToCover) * getPrice();
		
	}

}
