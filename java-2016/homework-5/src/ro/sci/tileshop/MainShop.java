package ro.sci.tileshop;


/**
 * Main class used to show the tile shop functionality.
 * @author Tudor Muresan
 *
 */
public class MainShop {

	/** Main method of the class. 
	 * @param args Command Line arguments.
	*/
	public static void main(String[] args) {
		MainShop myShop = new MainShop();
		
		//myShop.calculateStuff();
		
		double surfaceToCover = 10;	///square meters
		
		double priceForSTile = 50.91;	//price in RON - for one square meter of tiles.
		double priceForPTile = 80;	//price in RON - for a piece of tile.
		double priceforPTriangleTile = 40;	//price in RON - for a piece of triangular type tile.
		
		double sTileLength = 50;	//centimeters
		double sTileWidth = 50;		//centimeters
		double pTileLength = 80;	//centimeters
		double pTileWidth = 80;		//centimeters
		double triangleTileLeg = 30;	//centimeters
		
		Tile sTypeTile = new STile(priceForSTile, sTileLength, sTileWidth);
		System.out.println ("1. cost for covering the surface with S type tiles is: " + myShop.formatPrice(sTypeTile.calculatePrice(surfaceToCover)));
		
		Tile pTypeTile = new PTile(priceForPTile, pTileLength, pTileWidth);
		System.out.println ("2. cost for covering the surface with P type tiles is: " + myShop.formatPrice(pTypeTile.calculatePrice(surfaceToCover)));
		
		double splitCost = sTypeTile.calculatePrice(surfaceToCover/2) + pTypeTile.calculatePrice(surfaceToCover/2);
		System.out.println ("3. cost for covering the surface with 50% with S type and 50% P type is: " + myShop.formatPrice(splitCost));

		
		Tile pTriangularTile = new PTTile(priceforPTriangleTile, triangleTileLeg);
		System.out.println ("4. cost for covering the surface with PT type tiles is: " + myShop.formatPrice(pTriangularTile.calculatePrice(surfaceToCover)));

		
	}
	
	/**
	 * Method used to format a certain calculated price. The format sets the value in RON with two decimals.
	 * @param calculatedPrice
	 * @return the formatted price as a String.
	 */
	private String formatPrice(double calculatedPrice){
		String format =	String.format("RON %,.2f", calculatedPrice);
		return format;
	}
}
