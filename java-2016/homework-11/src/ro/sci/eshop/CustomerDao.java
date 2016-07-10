package ro.sci.eshop;

public interface CustomerDao {
	public int deleteCustomer(int customerId);
	public void createCustomer(int id,String name,String email);
	public int getCustomerById(int customerId);
	public void getAllCustomers();
	public int updateCustomer(String name, String email, int customerId);
	
}
