package hr.fer.rsikspr.teo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.rsikspr.teo.api.model.Message;
import hr.fer.rsikspr.teo.api.model.MessageV2;
import hr.fer.rsikspr.teo.api.model.NewMessageV2;
import hr.fer.rsikspr.teo.api.service.MessageServiceV1;
import hr.fer.rsikspr.teo.api.service.MessageServiceV2;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name="/v2/messages", description="Messages V2 implementation")
@RequestMapping("/v2/messages")  // Base URL for all message-related endpoints
public class MessageV2Controller {

	private final MessageServiceV2 messageServiceV2;
	
	@Autowired
    public MessageV2Controller(MessageServiceV2 messageServiceV2) {
		super();
		this.messageServiceV2 = messageServiceV2;
	}
	
	
    //@GetMapping("")  // Endpoint for receiving messages in version 2
    public ResponseEntity<List<MessageV2>> getMessagesV2() {
    	List<MessageV2> messages = messageServiceV2.getMessages();
        return ResponseEntity.ok(messages);
    }
    
    @PostMapping("")  // Endpoint for creating messages in version 1
    public ResponseEntity<MessageV2> createMessagesV2(@Valid @RequestBody NewMessageV2 message) {
		MessageV2 newMessage = messageServiceV2.createMessage(message);
        return ResponseEntity.ok(newMessage);
    }
}
