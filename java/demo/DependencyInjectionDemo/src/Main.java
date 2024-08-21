import service.*;

public class Main {

    public static void main(String[] args) {
        setterInjection();
        constructorInjection();
    }

    /**
     * DI by setter
     */
    private static void setterInjection() {
        EmailService emailService = new EmailService();
        SmsService smsService = new SmsService();

        ClientSetterService clientSetterService1 = new ClientSetterService();
        clientSetterService1.setMessageService(emailService);

        ClientSetterService clientSetterService2 = new ClientSetterService();
        clientSetterService2.setMessageService(smsService);

        clientSetterService1.sendMessage("Hello world!");
        clientSetterService2.sendMessage("Bye world!");
    }

    /**
     * DI by constructor
     */
    private static void constructorInjection() {
        EmailService emailService = new EmailService();
        SmsService smsService = new SmsService();

        ClientConstructorService client1 = new ClientConstructorService(emailService);
        ClientConstructorService client2 = new ClientConstructorService(smsService);

        client1.sendMessage("Hello world!");
        client2.sendMessage("Bye world!");
    }

    /**
     * DI by interface
     */
    private static void interfaceInjection() {
        EmailService emailService = new EmailService();
        SmsService smsService = new SmsService();

        ClientInterfaceService client1 = new ClientInterfaceService();
        client1.setMessageServiceInjection(emailService);
        ClientInterfaceService client2 = new ClientInterfaceService();
        client2.setMessageServiceInjection(smsService);

        client1.sendMessage("Hello world!");
        client2.sendMessage("Bye world!");
    }
}