package ro.sci.eshop;

public interface ProductDao {
	public void deleteProduct(int productId);
	public void createProduct(int id,String name,String description);
	public void getProductById(int productId);
	public void getAllProducts();
}
