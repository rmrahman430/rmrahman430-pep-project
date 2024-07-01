package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import Model.Account;
import Model.Message;
import Service.SocialService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    SocialService socialService;

    public SocialMediaController() {
        socialService = new SocialService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.get("/accounts/{account_id}/messages", this::getMessagesByAccountIdHandler);
        app.post("/messages", this::createMessageHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getAllMessagesHandler(Context ctx) {
        ctx.json(socialService.getAllMessages());
    }

    private void getMessageByIdHandler(Context context) throws JsonProcessingException {
        
        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message message = socialService.getMessageById(messageId);
        if (message != null) {
            context.json(message);
        } else {
            context.json(""); // Respond with an empty body if no message is found
        }
    }

    private void deleteMessageByIdHandler(Context context) throws JsonProcessingException {

        int messageId = Integer.parseInt(context.pathParam("message_id"));
        Message deletedMessage = socialService.deleteMessageById(messageId);
        if (deletedMessage != null) {
            context.json(deletedMessage); // Return the deleted message
        } else {
            context.result(""); // Respond with an empty body if no message was found
        }
    }

    private void getMessagesByAccountIdHandler(Context context) {
        int accountId = Integer.parseInt(context.pathParam("account_id"));
        context.json(socialService.getMessagesByAccountId(accountId));
    }

    private void createMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        
        Message message = mapper.readValue(context.body(), Message.class);
        Message createdMessage = socialService.createMessage(message);
        if (createdMessage != null) {
            context.json(createdMessage);
        } else {
            context.status(400);
        }
    }

}