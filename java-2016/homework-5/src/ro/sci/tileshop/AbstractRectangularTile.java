package ro.sci.tileshop;

/**
 * Abstractization for all rectangular tiles.
 * @author Tudor Muresan
 *
 */
public abstract class AbstractRectangularTile implements Tile {

	private double length;
	private double width;
	private double price;
	private double legLength;
	
	/**
	 * Constructor for rectangular tiles with price and sizes.
	 * @param priceForTile Price for a particular type of tile
	 * @param tileLength Length of tile.
	 * @param tileWidth Width of tile.
	 */
	public AbstractRectangularTile(double priceForTile, double tileLength, double tileWidth) {
		if(tileLength <=0){
			System.err.println("Error - The tile must have valid dimensions. Must be greater than 0");
		}
		else if(priceForTile <0){
			System.err.println("Error - The tile must have valid price. Must be positive");
		}
		else if(priceForTile==0){
			System.err.println("Warning! price for the tile is 0. It means that the tile is moca and your business will fail");
		}
		length = tileLength;
		width = tileWidth;
		price = priceForTile;
	}
	
	/**
	 * Constructor for right angle triangular tiles with price and leg size.
	 * @param priceForTile Price for triangular tile by piece.
	 * @param legLength leg length of the right angled triangular tile.
	 */
	public AbstractRectangularTile(double priceForTile, double legLength){
		this.legLength = legLength;
		price = priceForTile;
	}
	
	/**
	 * Calculates the area for one tile.
	 * @return the calculated area for a tile.
	 */
	public double calculateArea() {
		return this.getLength() * this.getWidth();
	}

	/**
	 * Getter for the length of a rectangle.
	 * @return rectangle's length.
	 */
	public double getLength() {
		return length;
	}

	/**
	 * Getter for the width of a rectangle.
	 * @return rectangle's width.
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Getter for legLength of triangular tile.
	 * @return tile's leg length.
	 */
	public double getLegLength(){
		return legLength;
	}
	/**
	 * Getter for the price of a tile.
	 * @return price of one piece of tile or the price for a square meter of a type of tiles.
	 */
	public double getPrice() {
		return price;
	}
}
