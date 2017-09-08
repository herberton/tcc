package br.com.herberton.tcc.puc.poc.dto.user;

public class EcommerceUserDTO 
	extends DefaultUserDTO<EcommerceUserDTO> {

	private static final long serialVersionUID = -1941158447382948556L;
	
	private boolean specialCustomer;
	
	
	public boolean isSpecialCustomer() {
		return specialCustomer;
	}
	public void setSpecialCustomer(boolean specialCustomer) {
		this.specialCustomer = specialCustomer;
	}
	
}