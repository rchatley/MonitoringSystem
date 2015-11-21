package ic.doc.monitoring;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Monitor {

    private List<WebPageProbe> probes;

    public Monitor() {
        this.probes = new ArrayList<WebPageProbe>();
        this.probes.add(new WebPageProbe("http://www.google.com", "Google"));
        this.probes.add(new WebPageProbe("http://www.imperial.ac.uk/", "Imperial College"));
    }

    public void check() {
        Emailer emailer = Emailer.getInstance();
        SmsSender smsSender = new SmsSender();

        for (WebPageProbe probe : probes) {
            if (!probe.passes()) {
                emailer.send("support@example.com", probe.getFailureDescription());

                LocalTime now = LocalTime.now();
                if (now.getHour() >= BusinessHours.START_OF_BUSINESS &&
                        now.getHour() < BusinessHours.CLOSE_OF_BUSINESS) {
                    smsSender.send("+447777123456", probe.getFailureDescription());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Monitor().check();
        System.out.println("Completed.");
    }
}