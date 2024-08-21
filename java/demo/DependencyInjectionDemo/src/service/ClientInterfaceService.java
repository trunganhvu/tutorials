package service;

public class ClientInterfaceService implements MessageServiceInjection{

    private MessageService messageService;

    @Override
    public void setMessageServiceInjection(MessageService messageService) {
        this.messageService = messageService;
    }

    public void sendMessage(String message) {
        messageService.sendMessage(message);
    }
}
