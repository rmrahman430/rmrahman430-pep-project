package Service;

import Model.Message;
import Model.Account;
import DAO.SocialMediaDAO;

import java.util.List;


public class SocialService {
    SocialMediaDAO socialMediaDAO;

    public SocialService() {
        socialMediaDAO = new SocialMediaDAO();
    }

    public SocialService(SocialMediaDAO socialMediaDAO) {
        this.socialMediaDAO = socialMediaDAO;
    }

    public Account createAccount(Account account) {
        // Validate the account details
        if (account.getUsername() == null || account.getUsername().trim().isEmpty() || account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }

        // Check if an account with the username already exists
        if (socialMediaDAO.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }

        // Create the account
        return socialMediaDAO.createAccount(account);
    }
    public Account Login(String username, String password) {
        return socialMediaDAO.Login(username, password);
    }

    public List<Message> getAllMessages() {
        return socialMediaDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        return socialMediaDAO.getMessageById(message_id);
    }

    public Message deleteMessageById(int message_id) {
        return socialMediaDAO.deleteMessageById(message_id);
    }
    
    public List<Message> getMessagesByAccountId(int account_id) {
        return socialMediaDAO.getAllMessagesOfUser(account_id);
    }
    public Message createMessage(Message message) {
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty()) {
            return null;
        }
        if (message.getMessage_text().length() > 255) {
            return null;
        }
        if (!socialMediaDAO.userExists(message.getPosted_by())) {
            return null;
        }
        return socialMediaDAO.createMessage(message);
    }
    public Message updateMessage(int message_id, Message message) {
        Message existingMessage = socialMediaDAO.getMessageById(message_id);

        if(existingMessage == null) {
            return null;
        }
        if (message.getMessage_text() == null || message.getMessage_text().trim().isEmpty() || message.getMessage_text().length() > 255) {
            return null;
        }
        existingMessage.setMessage_text(message.getMessage_text());
        socialMediaDAO.updateMessage(message_id, existingMessage);


        return socialMediaDAO.getMessageById(message_id);
    }
}
