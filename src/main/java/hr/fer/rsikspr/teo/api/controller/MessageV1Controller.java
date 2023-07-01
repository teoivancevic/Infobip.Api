package hr.fer.rsikspr.teo.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.model.MessageV2;
import hr.fer.rsikspr.teo.api.service.MessageServiceV1;
import hr.fer.rsikspr.teo.api.service.MessageServiceV2;
import hr.fer.rsikspr.teo.api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody; // JAKO VAZNO DA JE OVO
import jakarta.validation.Valid;

@RestController
@Tag(name="/messages/v1", description="Messages V1 implementation")
@RequestMapping("/messages/v1")  // Base URL for all message-related endpoints
public class MessageV1Controller {
  
	private final MessageServiceV1 messageServiceV1;
	
	@Autowired
    public MessageV1Controller(MessageServiceV1 messageServiceV1) {
		super();
		this.messageServiceV1 = messageServiceV1;
	}

	
	@GetMapping("")  // Endpoint for loading messages in version 1
    public ResponseEntity<List<MessageV1>> getMessagesV1() {
		List<MessageV1> messages = messageServiceV1.getAllMessages();
        return ResponseEntity.ok(messages);
    }
	
	@PostMapping("")  // Endpoint for creating messages in version 1
    public ResponseEntity<MessageV1> createMessagesV1(@Valid @RequestBody MessageV1 message) {
		MessageV1 newMessage = messageServiceV1.createMessage(message);
        return ResponseEntity.ok(newMessage);
    }
	
	
	
	
	
  

}
