package hr.fer.rsikspr.teo.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.rsikspr.teo.api.model.MessageV1;
import hr.fer.rsikspr.teo.api.model.User;
import hr.fer.rsikspr.teo.api.repository.MessageRepositoryV1;
import hr.fer.rsikspr.teo.api.repository.UserRepository;

@Service
public class MessageServiceV1  {
	
	private final MessageRepositoryV1 messageRepository;

    public MessageServiceV1(MessageRepositoryV1 messageRepository) {
		super();
		this.messageRepository = messageRepository;
	}



	public List<MessageV1> getAllMessages() {
        return messageRepository.findAll();
    }
	
	public MessageV1 createMessage(MessageV1 message) {
    	return messageRepository.save(message);
    }
}
