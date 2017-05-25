package ro.sci.tileshop;

/**
 * Interface for all tile types.
 * @author Tudor Muresan
 *
 */
public interface Tile {

	/**
	 * Method used to calculate the price to cover a surface with tiles.
	 * @param surfaceToCover Surface needed to cover.
	 * @return the price for covering a surface with a certain type of tile.
	 */
	double calculatePrice(double surfaceToCover);
	
	/**
	 * Calculates the area for one tile.
	 * @return the calculated area for a tile.
	 */
	double calculateArea();
}
