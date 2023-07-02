package hr.fer.rsikspr.teo.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class NewMessageV2 {
	
	
	private String from;
	private String to;
    private String displayName;
    
    private NewContent content;
    
    public NewMessageV2() {
    	
    }

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public NewContent getContent() {
		return content;
	}

	public void setContent(NewContent content) {
		this.content = content;
	}
    
    
}
