package ro.sci.eshop;


public interface OrderDao {
	public void deleteOrder(int orderId);
	public void createOrder(int id,double price, int idCustomer);
	public void getOrderById(int orderId);
	public void getAllOrders();
	public void updateOrder(int orderId);
}
