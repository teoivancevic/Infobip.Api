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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "messagev1", schema = "public")
public class MessageV1 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message = "Field can't be blank")
	@Column(name = "\"from\"", nullable = false)
	private String from;
    
	@NotNull
	@NotBlank(message = "Field can't be blank")
	@Column(name = "\"to\"", nullable = false)
	private String to;
	
	@NotNull
	@NotBlank(message = "Field can't be blank")
	@Column(nullable = false)
    private String text;
	
	//@NotNull
	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
    private ConversationV1 conversation;


    public MessageV1() {
    	
    }
	
	public MessageV1(Message message) {
		super();
		this.from = message.getFrom();
		this.to = message.getTo();
		this.text = message.getText();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public ConversationV1 getConversation() {
		return conversation;
	}


	public void setConversation(ConversationV1 conversation) {
		this.conversation = conversation;
	}
	
	
	@AssertFalse(message = "Fields cannot have the same value")
    private boolean isFromEqualToTo() {
        return from != null && to != null && from.equals(to);
    }
	
	
	
	
	
	
}
