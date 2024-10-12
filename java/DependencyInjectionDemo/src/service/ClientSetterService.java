package service;

public class ClientSetterService {
    private MessageService messageService;

    public MessageService getMessageService() {
        return messageService;
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Methods call to dependency
     * @param message
     */
    public void sendMessage(String message) {
        messageService.sendMessage(message);
    }
}
