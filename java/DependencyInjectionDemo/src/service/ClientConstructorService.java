package service;

public class ClientConstructorService {
    private MessageService messageService;

    public ClientConstructorService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendMessage(String message) {
        messageService.sendMessage(message);
    }
}
