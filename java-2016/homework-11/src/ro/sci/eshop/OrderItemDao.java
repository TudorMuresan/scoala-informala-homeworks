package ro.sci.eshop;

public interface OrderItemDao {
	public void deleteOrderItem(int orderItemId);
	public void createOrderItem(int id,int orderId,int quantity, int idProduct);
	public void getOrderItemsById(int orderItemId);
	public void getAllOrderItems();
}
