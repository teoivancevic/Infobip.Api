package hr.fer.rsikspr.teo.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.rsikspr.teo.api.model.ConversationV1;
import hr.fer.rsikspr.teo.api.service.ConversationV1Service;
import hr.fer.rsikspr.teo.api.service.MessageServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController


@Tag(name="/v1/conversations", description="Conversations V1 implementation")
@RequestMapping("/v1/conversations")  // Base URL for all message-related endpoints
public class ConversationV1Controller {
	
	private final ConversationV1Service conversationService;
	
	@Autowired
    public ConversationV1Controller(ConversationV1Service conversationService) {
		super();
		this.conversationService = conversationService;
	}
	
	@GetMapping("")
	@Operation(summary = "Gets all conversations from database")
	public ResponseEntity<List<ConversationV1>> getAllConversations(){
		return ResponseEntity.ok(conversationService.getAllConversations());
	}
	
	@GetMapping("id")
	@Operation(summary = "Gets conversation by id")
	public ResponseEntity<Optional<ConversationV1>> getConversationById(@RequestParam("id") long id){
		return ResponseEntity.ok(conversationService.getConversationById(id));
	}
	
	@GetMapping("user/all")
	@Operation(summary = "Gets all conversations which include given user")
	public ResponseEntity<List<ConversationV1>> getConversationsByUser(@RequestParam("name") String name){
		return ResponseEntity.ok(conversationService.getConversationsByUser(name));
	}
	
	@GetMapping("user/active")
	@Operation(summary = "Gets active conversation for given user")
	public ResponseEntity<ConversationV1> getActiveConversationByUser(@RequestParam("name") String name){
		return ResponseEntity.ok(conversationService.getActiveConversationByUser(name));
	}
	
	@PutMapping("close/id")
	@Operation(summary = "Closes active conversation by id")
	public ResponseEntity<Void> closeConversationById(@RequestParam("id") long id){
		
		boolean result = conversationService.closeConversationById(id);
		
		if(result) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	// One of the users closes their own conversation
	@PutMapping("close/name")
	@Operation(summary = "Closes active conversation for given user")
	public ResponseEntity<Void> closeConversationByUser(@RequestParam("name") String name){
		boolean result = conversationService.closeConversationForUser(name);
		
		if(result) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	@GetMapping("/time-range")
	@Operation(summary = "Closes all conversations in given time range. Format: yyyy-MM-DD'T'HH:mm:ss:SSSXXX")
	public ResponseEntity<List<ConversationV1>> getConversationsInTimeRange(
	        @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
	        @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
	    
	    List<ConversationV1> conversations = conversationService.getConversationsInTimeRange(startTime, endTime);
	    
	    if (conversations.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(conversations);
	    }
	}
}
