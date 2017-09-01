package br.com.herberton.tcc.puc.poc.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;

public class TicketDTO implements IDTO<TicketDTO> {
	
	private static final long serialVersionUID = -8316725737245529698L;
	
	private String value;
	
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	public TicketDTO() {
		
	}
	
	public TicketDTO(String value) {
		this.setValue(value);
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof TicketDTO)) {
			return false;
		}
		
		TicketDTO other = (TicketDTO) object;
		
		return 
			new EqualsBuilder()
				.append(this.getValue(), other.getValue())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getValue())
					.toHashCode();
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
	
	
	public static final TicketDTO from(String value) {
		return new TicketDTO(value);
	}
	
}
