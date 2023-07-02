package hr.fer.rsikspr.teo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "messagev2", schema = "public")
public class MessageV2 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name = "\"from\"", nullable = false)
	private String from;
    
	@NotNull
	@Column(name = "\"to\"", nullable = false)
	private String to;
	
	
	
	//@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", referencedColumnName = "id")
    private Content content;
	
	@NotNull
	@Column(nullable = false)
    private String displayName;
	
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private ConversationV2 conversation;

	
	public MessageV2(NewMessageV2 message) {
		super();
		this.from = message.getFrom();
		this.to = message.getTo();
		this.content = new Content(message.getContent());
		this.displayName = message.getDisplayName();

	}
	
	public MessageV2() {
    	
    }
	
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
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

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public ConversationV2 getConversation() {
		return conversation;
	}


	public void setConversation(ConversationV2 conversation) {
		this.conversation = conversation;
	}
	
	@AssertFalse(message = "Fields cannot have the same value")
    private boolean isFromEqualToTo() {
        return from != null && to != null && from.equals(to);
    }
	
	
	
	
	
}



