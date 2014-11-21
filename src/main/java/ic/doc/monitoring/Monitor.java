package ic.doc.monitoring;

import org.joda.time.DateTime;

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

                DateTime now = new DateTime();
                if (now.getHourOfDay() >= BusinessHours.START_OF_BUSINESS &&
                        now.getHourOfDay() < BusinessHours.CLOSE_OF_BUSINESS) {
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