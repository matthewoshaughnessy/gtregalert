package util;

import config.Config;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Encapsulates time methods for logging
 * @author Matthew O'Shaughnessy
 *
 */
public class Clock
{

    public Clock() {
    	
    }

    /**
     * Returns time as String in format specified in Config
     * @return - time String
     */
    public static String timeString()
    {
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(Config.timeFormat);
        return sdf.format(cal.getTime());
    }

    private static Calendar cal;
}
