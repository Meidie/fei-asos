package sk.matus.asos.spark01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountLogger implements Serializable {

    private final List<String> loglist = new ArrayList<>();

    public void log(String s) {
        loglist.add(s);
    }

    public int logCount() {
        return loglist.size();
    }

    public void logPrint() {
        loglist.forEach(s -> {
            System.out.println(s);
        });
    }
}
