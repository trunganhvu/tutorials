package service;

public class SmsService implements MessageService{
    @Override
    public void sendMessage(String message) {
        System.out.println("SMS message:" + message);
    }
}
