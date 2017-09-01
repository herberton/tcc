package br.com.herberton.tcc.puc.poc.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.herberton.tcc.puc.poc.dto.contract.IDTO;

public class MessageDTO implements IDTO<MessageDTO> {
	
	private static final long serialVersionUID = -8316725737245529698L;
	
	private Boolean isError;
	private String text;
	
	
	public Boolean getIsError() {
		return isError;
	}
	public void setIsError(Boolean isError) {
		this.isError = isError;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	public MessageDTO() {
		
	}
	
	public MessageDTO(Boolean isError, String text) {
		this.setIsError(isError);
		this.setText(text);
	}
	
	public MessageDTO(String text) {
		this(false, text);
	}
	
	public MessageDTO(Exception exception) {
		this(true, exception.getMessage());
	}
	
	
	@Override
	public boolean equals(Object object) {
		
		if(!(object instanceof MessageDTO)) {
			return false;
		}
		
		MessageDTO other = (MessageDTO) object;
		
		return 
			new EqualsBuilder()
				.append(this.getText(), other.getText())
					.isEquals();
	
	}
	
	@Override
	public int hashCode() {
		return 
			new HashCodeBuilder()
				.append(this.getText())
					.toHashCode();
	}
	
	@Override
	public String toString() {
		return this.getText();
	}
	
	
	public static final MessageDTO ofSuccess(String text) {
		return new MessageDTO(text);
	}
	
	public static final MessageDTO ofError(Exception exception) {
		return new MessageDTO(exception);
	}
	
}
