package cpsc.dlsproject.utils;

import java.util.List;

public class TypeCheckLog {
    public List<String> logs;

    public void add(String log) {
        logs.add(log);
    }

    public void printLogs() {
        System.out.println("TypeCheck reports:\n");

        for (String log : logs) {
            System.out.println(log);
        }
    }
}
