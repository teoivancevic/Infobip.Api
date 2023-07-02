package hr.fer.rsikspr.teo.api.service;

import java.util.List;

import hr.fer.rsikspr.teo.api.model.Message;
import hr.fer.rsikspr.teo.api.model.MessageV1;

public interface IMessageService {
	
	List<Message> getAllMessages() ;
	Message createMessage(Message message);
}
