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
import hr.fer.rsikspr.teo.api.model.ConversationV2;
import hr.fer.rsikspr.teo.api.service.ConversationV1Service;
import hr.fer.rsikspr.teo.api.service.ConversationV2Service;
import hr.fer.rsikspr.teo.api.service.MessageServiceV1;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="/v2/conversations", description="Conversations V2 implementation")
@RequestMapping("/v2/conversations")  // Base URL for all message-related endpoints
public class ConversationV2Controller {
	
	private final ConversationV2Service conversationService;
	
	@Autowired
    public ConversationV2Controller(ConversationV2Service conversationService) {
		super();
		this.conversationService = conversationService;
	}
	
	@GetMapping("")
	@Operation(summary = "Gets all conversations from database")
	public ResponseEntity<List<ConversationV2>> getAllConversations(){
		return ResponseEntity.ok(conversationService.getAllConversations());
	}
	
	@GetMapping("id")
	@Operation(summary = "Gets conversation by id")
	public ResponseEntity<Optional<ConversationV2>> getConversationById(@RequestParam("id") long id){
		return ResponseEntity.ok(conversationService.getConversationById(id));
	}
	
	@GetMapping("user/all")
	@Operation(summary = "Gets all conversations which include given user")
	public ResponseEntity<List<ConversationV2>> getConversationsByUser(@RequestParam("name") String name){
		return ResponseEntity.ok(conversationService.getConversationsByUser(name));
	}
	
	@GetMapping("user/active")
	@Operation(summary = "Gets active conversation for given user")
	public ResponseEntity<ConversationV2> getActiveConversationByUser(@RequestParam("name") String name){
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
	
	@PutMapping("close/name")
	@Operation(summary = "Closes active conversation for given user")
	public ResponseEntity<Void> closeConversation(@RequestParam("name") String name){
		boolean result = conversationService.closeConversationForUser(name);
		
		if(result) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/time-range")
	@Operation(summary = "Closes all conversations in given time range. Format: yyyy-MM-DD'T'HH:mm:ss:SSSXXX")
	public ResponseEntity<List<ConversationV2>> getConversationsInTimeRange(
	        @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
	        @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
	    
	    List<ConversationV2> conversations = conversationService.getConversationsInTimeRange(startTime, endTime);
	    
	    if (conversations.isEmpty()) {
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.ok(conversations);
	    }
	}
}
