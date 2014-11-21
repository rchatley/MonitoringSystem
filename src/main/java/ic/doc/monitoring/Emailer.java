package ic.doc.monitoring;

public class Emailer {

    // imagine this class really does e-mail sending

    private static final Emailer INSTANCE = new Emailer();

    private Emailer() {}

    public void send(String to, String msg) {
        System.out.println("Email: " + to + " :" + msg);
    }

    public static Emailer getInstance() {
        return INSTANCE;
    }
}
