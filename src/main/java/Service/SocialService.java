package Service;

import Model.Message;
import DAO.SocialMediaDAO;

import java.util.List;


public class SocialService {
    SocialMediaDAO messageDAO;


    public SocialService() {
        messageDAO = new SocialMediaDAO();
    }

    public SocialService(SocialMediaDAO messageDAO) {
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
    public Message createMessage(Message message) {
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()) {
            return null;
        }
        if (message.getMessage_text().length() > 255) {
            return null;
        }
        if (!messageDAO.userExists(message.getPosted_by())) {
            return null;
        }
        return messageDAO.createMessage(message);
    }
}
