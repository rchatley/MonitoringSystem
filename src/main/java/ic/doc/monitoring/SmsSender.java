package ic.doc.monitoring;

public class SmsSender {

    // imagine this class really does SMS sending

    public void send(String msisdn, String msg) {
        System.out.println("SMS: " + msisdn + " :" + msg);
    }
}
