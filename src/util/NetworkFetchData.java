package util;

/**
 * Represents data from one successful data retrieval
 * @author Matthew O'Shaughnessy
 *
 */
public class NetworkFetchData {

    private int requestTime;
    private int attempts;
    private String time;
	
    public NetworkFetchData() {
    	
    }
    
    public NetworkFetchData(int requestTime, int attempts, String time) {
        this.requestTime = requestTime;
        this.attempts = attempts;
        this.time = time;
    }

    public NetworkFetchData(int requestTime, int attempts) {
        this.requestTime = requestTime;
        this.attempts = attempts;
        time = Clock.timeString();
    }

    public void setRequestTime(int requestTime) {
        this.requestTime = requestTime;
    }

    public int getRequestTime() {
        return requestTime;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
