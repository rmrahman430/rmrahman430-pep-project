package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;


public class MessageService {
    MessageDAO messageDAO;


    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();

    }

    public Message getMessageById(int message_id) {
        return messageDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        return messageDAO.deleteMessageById(message_id);
    }
    
    public List<Message> getMessagesByAccountId(int account_id) {
        return messageDAO.getAllMessagesOfUser(account_id);
    }
}
