package Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConsoleLogger implements ILogger {

    public void Log(String _message, SeverityLevel _level) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar date = Calendar.getInstance();

        System.out.printf("[%s][%s] %s", formatter.format(date), _level.toString(), _message);
    }

}
