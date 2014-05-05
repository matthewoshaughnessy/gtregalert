package config;

/**
 * Allows for easy and uniform access to configuration values
 * @author Matthew O'Shaughnessy
 *
 */
public class Config
{

    public Config() {
    	
    }

    // OSCAR term ID (from URL)
    public static int term = 201408;
    
    // CRN of class to check
    public static int crn = 29665;
    
    // Period to refresh (ms)
    public static int refresh_interval = 5000;
    
    // Period to wait before timing out connection (ms)
    public static int timeout_threshold = 2000;
    
    // Period to wait after each successive timeout before attempting again (sec)
    public static int timeout_wait[] = {2, 5, 10, 15, 15, 15, 60, 60, 60, 60};
    
    // Path to write CSV network debug file to (interpret with MATLAB script)
    public static String csvPath = ".\\gtregalertdata.csv";
    
    // Format to print time in
    public static String timeFormat = "MM/dd/YYYY HH:mm:ss:SSS";
    
    // Sends SMS on open seat if true
    public static boolean smsOn = true;
    
    // Number to deliver SMS to when seat found
    public static String smsDst = "4044315709";

}
