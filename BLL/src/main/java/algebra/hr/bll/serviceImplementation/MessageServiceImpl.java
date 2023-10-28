package algebra.hr.bll.serviceImplementation;

import algebra.hr.bll.service.MessageService;
import algebra.hr.dal.entity.Message;
import algebra.hr.dal.repository.MessageRepository;
import hr.algebra.utils.notFoundErrors.CustomNotFoundException;

import java.util.List;
import java.util.Optional;

public class MessageServiceImpl implements MessageService {
    private final MessageRepository _messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        _messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return _messageRepository.findAll();
    }

    @Override
    public Message findById(int id) {
        Optional<Message> messageOptional = _messageRepository.findById(id);

        if (messageOptional.isEmpty()){
            throw new CustomNotFoundException("Message id not found - " + id);
        }

        return messageOptional.get();
    }

    @Override
    public Message save(Message message) {
        return _messageRepository.save(message);
    }

    @Override
    public void deleteById(int id) {
        _messageRepository.deleteById(id);
    }
}
