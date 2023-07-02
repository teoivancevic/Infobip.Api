package hr.fer.rsikspr.teo.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Message {
	
	//private Long id;
	//@NotNull
	//@NotBlank(message = "Field can't be blank")
	private String from;
	
	//@NotNull
	//@NotBlank(message = "Field can't be blank")
	private String to;
	
	//@NotNull
	//@NotBlank(message = "Field can't be blank")
	private String text;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
