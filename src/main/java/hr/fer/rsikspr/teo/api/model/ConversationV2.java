package hr.fer.rsikspr.teo.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "conversationv2", schema = "public")
public class ConversationV2 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
    private String participant1;
	
	@Column(nullable = false)
    private String participant2;
	
	@Column(nullable = false)
    private LocalDateTime startTime  = LocalDateTime.now();
    
    @Column
    private LocalDateTime endTime;
    
    //@JsonIgnore
    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<MessageV2> messages = new ArrayList<>();

    
    public ConversationV2() {
        // Default constructor
    }
	
    public ConversationV2(String part1, String part2) {
    	super();
    	this.participant1 = part1;
    	this.participant2 = part2;
    }
    
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getParticipant1() {
		return participant1;
	}

	public void setParticipant1(String participant1) {
		this.participant1 = participant1;
	}

	public String getParticipant2() {
		return participant2;
	}

	public void setParticipant2(String participant2) {
		this.participant2 = participant2;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public List<MessageV2> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageV2> messages) {
		this.messages = messages;
	}
    
    
    
	
}
