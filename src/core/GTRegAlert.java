package core;

import config.Config;
import net.Alerter;
import net.NetworkManager;
import org.jsoup.nodes.Document;
import parsing.ClassData;
import parsing.Parser;
import util.Clock;
import util.Logger;

/**
 * Driver for the GTRegAlert Program
 * @author Matthew O'Shaughnessy
 *
 */
public class GTRegAlert
{

    public GTRegAlert() {
    	
    }

    public static void main(String args[]) throws Exception {
    	// validate and parse input
        if(args.length != 3)
        {
            System.err.println("Usage: gtregalert crn refreshInterval(ms) phoneNumber");
            System.exit(1);
        }
        Config.crn = Integer.parseInt(args[0]);
        Config.refresh_interval = Integer.parseInt(args[1]);
        if(Config.smsDst.equals("0") || Config.smsDst.equals(""))
            Config.smsOn = false;
        Config.smsDst = args[2];
        
        // print initial log info
        Logger.info("");
        Logger.info("Matthew O'Shaughnessy, 07/28/13");
        Logger.info("matthewoshaughnessy@gatech.edu");
        Logger.info("");
        Logger.info("Current version updated 05/04/14");
        Logger.info("Semester='FALL14' (" + Config.crn + ")");
        Logger.info("Initializing...");
        Logger.info("");
        Thread.sleep(2000); // wait so user can see current semester
        Logger.info("Init.");
        Logger.info((new StringBuilder("Working directory is: '")).append(System.getProperty("user.dir")).append("'.").toString());
        
        // Make initial connection to google voice and OSCAR
        Logger.info("Initiating connection to google voice...");
        Alerter.init();
        Logger.info("Managing certificates...");
        NetworkManager.setTrustAllCerts();
        Logger.info("Establishing connection...");
        Logger.info("Fetching data...");
        Document doc = NetworkManager.fetchData(Config.term, Config.crn);
        Logger.info("Parsing...");
        ClassData classData = Parser.parseData(doc);
        Logger.displayClassData(classData);
        
        // Repeat checking for seats at specified interval
        boolean alerted = false;
        while(true) {
            doc = NetworkManager.fetchData(Config.term, Config.crn);
            classData = Parser.parseData(doc);
            int rem = classData.getRemaining();
            Logger.data((new StringBuilder("Seats Remaining: ")).append(Integer.toString(rem)).toString());
            if(rem > 0 && !alerted)
            {
                String msg = (new StringBuilder("(")).append(Clock.timeString()).append(") gtregalert: ").append(Integer.toString(rem)).append(" seats open in CRN ").append(Integer.toString(Config.crn)).append("!").toString();
                Alerter.sms(Config.smsDst, msg);
                alerted = true;
            }
            Thread.sleep(Config.refresh_interval);
        }
    }
}
