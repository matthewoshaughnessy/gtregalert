package net;

import com.techventus.server.voice.Voice;
import config.Config;
import java.io.IOException;
import util.Logger;

/**
 * Handles Google Voice SMS alerts
 * 
 * @author Matthew O'Shaughnessy
 *
 */
public class Alerter
{

    public Alerter() {
    	
    }

    /**
     * Connect to google voice account.
     * Only needs to be performed once.
     */
    public static void init()
    {
        try
        {
            voice = new Voice("gtregalert", "pswdpswd2016");
        }
        catch(IOException e)
        {
            Logger.error((new StringBuilder("Error connecting to Google Voice: ")).append(e.toString()).append(".").toString());
        }
    }

    /**
     * Sends a SMS with specified content to specified destination
     * @param dst - phone number to send to, as a String
     * @param msg - message to send
     */
    public static void sms(String dst, String msg)
    {
        if(Config.smsOn)
            try
            {
                voice.sendSMS(dst, msg);
                Logger.info((new StringBuilder("SMS sent to ")).append(dst).append(": '").append(msg).append("'.").toString());
            }
            catch(IOException e)
            {
                Logger.error((new StringBuilder("Error sending SMS: ")).append(e.toString()).append(".").toString());
            }
        else
            Logger.info((new StringBuilder("SMS not sent to ")).append(dst).append(": '").append(msg).append("' (SMS off).").toString());
    }

    private static Voice voice;
}
