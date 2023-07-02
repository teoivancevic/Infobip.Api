package hr.fer.rsikspr.teo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="/conversations/v1", description="Conversations V1 implementation")
@RequestMapping("/conversations/v1")  // Base URL for all message-related endpoints
public class ConversationV1Controller {
	
	private final ConversationV1Service conversationService;
	
	@Autowired
    public ConversationV1Controller(ConversationV1Service conversationService) {
		super();
		this.conversationService = conversationService;
	}
	
	@GetMapping("byuser")
	public ResponseEntity<List<ConversationV1>> getConversationsByUser(@RequestParam("name") String name){
		return ResponseEntity.ok(conversationService.getConversationsByUser(name));
	}
	
	@GetMapping("byuser/active")
	public ResponseEntity<ConversationV1> getActiveConversationByUser(@RequestParam("name") String name){
		return ResponseEntity.ok(conversationService.getActiveConversationByUser(name));
	}
	
	@PutMapping("close")
	public ResponseEntity<Void> closeConversation(@RequestParam("name") String name){
		boolean result = conversationService.closeConversationForUser(name);
		
		if(result) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
