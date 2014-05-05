package net;

import config.Config;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import util.Analytics;
import util.Logger;

/**
 * Creates 
 * @author Matthew O'Shaughnessy
 *
 */
public class NetworkManager
{

    public NetworkManager() {
    	
    }

    /**
     * Gets jsoup Document object from oscar for the term and crn given
     * @param term - oscar term identifier
     * @param crn - course ID
     * @return jsoup Document Object with the registration data
     * @throws InterruptedException
     */
    public static Document fetchData(int term, int crn) throws InterruptedException {
        Document doc = new Document("");
        long t0 = 0L;
        long t1 = 0L;
        int attempts = 0;
        boolean success = false;
        setTrustStore();
        // attempt a connection until successful or 10 failures
        while (attempts <= 10 && !success) {
            try {
                attempts++;
                t0 = System.currentTimeMillis();
                // try to get the document
                doc = Jsoup.connect(oscarURL(term, crn)).timeout(Config.timeout_threshold).get();
                t1 = System.currentTimeMillis();
                numFetches++;
                success = true;
            } catch(IOException e) {
                Logger.warn((new StringBuilder("Timeout (")).append(Integer.toString(numFetches)).append(" reads completed.)").toString());
                if(attempts < 10) {
                    int waittime = Config.timeout_wait[attempts] * 1000;
                    Logger.warn((new StringBuilder("Connection attempt ")).append(Integer.toString(attempts)).append(" failed (").append(e.toString()).append("), retrying in ").append(Integer.toString(waittime / 1000)).append("sec...").toString());
                    Thread.sleep(waittime);
                } else {
                    Logger.error((new StringBuilder("Failed after ")).append(Integer.toString(attempts)).append(" attempts, giving up.").toString());
                }
            }
        }
        long connectionTime = t1 - t0;
        totalNetworkTime += connectionTime;
        Analytics.log((int)connectionTime, attempts);
        Logger.info((new StringBuilder("Network request time ")).append(Integer.toString((int)(t1 - t0))).append("ms, average ").append(Integer.toString((int)(totalNetworkTime / (double)numFetches))).append("ms, read ").append(numFetches).append(".").toString());
        return doc;
    }

    /**
     * Builds the oscar URL for a specified term and crn
     * @param term - oscar term identification number
     * @param crn - course ID
     * @return URL for oscar registration data
     */
    public static String oscarURL(int term, int crn)
    {
        String termStr = Integer.toString(term);
        String crnStr = Integer.toString(crn);
        return (new StringBuilder("http://oscar.gatech.edu/pls/bprod/bwckschd.p_disp_detail_sched?term_in=")).append(termStr).append("&crn_in=").append(crnStr).toString();
    }

    /**
     * Sets trust store to allow connection to oscar
     */
    public static void setTrustStore()
    {
        System.setProperty("javax.net.ssl.trustStore", "./res/oscar.gatech.edu.jks");
    }

    /**
     * Set to trust all certificates to allow a connection to oscar. //TODO secure connection
     */
    public static void setTrustAllCerts() {
        TrustManager trustAllCerts[] = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate ax509certificate[], String s) { }
                public void checkServerTrusted(X509Certificate ax509certificate[], String s) { }
            }
        };
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        }
        catch(Exception e) {
        	Logger.error("Connection Error:");
        	Logger.error(e.toString());
        }
    }

    // info for debugging and printing status
    private static int numFetches = 0;
    private static double totalNetworkTime = 0.0D;

}
