package util;

import config.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Logs network data and writes to a CSV file.  For use with MATLAB script
 * for visualization
 * @author Matthew O'Shaughnessy
 *
 */
public class Analytics {

    public Analytics() {
    	
    }

    public static void log(int requestTime, int attempts, String time) {
        data.add(new NetworkFetchData(requestTime, attempts, time));
    }

    public static void log(int requestTime, int attempts) {
        data.add(new NetworkFetchData(requestTime, attempts));
    }

    /**
     * Creates a CSV data file in the location specified in Config.
     * Only use this method once.  Once the CSV file is generated,
     * additional entries can be added with addCSVEntry().
     * @throws IOException
     */
    public static void generateCSV() throws IOException {
        FileWriter fWriter = new FileWriter(Config.csvPath);
        if(data.size() == 0) {
            Logger.warn("CSV cannot be generated with data size=0.");
        } else {
            for(int i = 0; i < data.size(); i++)
            {
                fWriter.append(Integer.toString(i));
                fWriter.append(",");
                fWriter.append(((NetworkFetchData)data.get(i)).getTime().replaceAll("\\D+", ""));
                fWriter.append(",");
                fWriter.append(Integer.toString(((NetworkFetchData)data.get(i)).getRequestTime()));
                fWriter.append(",");
                fWriter.append(Integer.toString(((NetworkFetchData)data.get(i)).getAttempts()));
                fWriter.append(System.getProperty("line.separator"));
            }

            Logger.info((new StringBuilder("CSV written to '")).append(Config.csvPath).append("'.").toString());
        }
        fWriter.flush();
        fWriter.close();
    }

    /**
     * Adds an entry to an already generated CSV file specified in Config
     */
    public static void addCSVEntry() {
        try {
            FileWriter appendWriter = new FileWriter(Config.csvPath, true);
            appendWriter.append("HELLO, WORLD");
            appendWriter.append(Integer.toString(data.size() - 1));
            appendWriter.append(",");
            appendWriter.append(((NetworkFetchData)data.get(data.size() - 1)).getTime().replaceAll("\\D+", ""));
            appendWriter.append(",");
            appendWriter.append(Integer.toString(((NetworkFetchData)data.get(data.size() - 1)).getRequestTime()));
            appendWriter.append(",");
            appendWriter.append(Integer.toString(((NetworkFetchData)data.get(data.size() - 1)).getAttempts()));
            appendWriter.append(System.getProperty("line.separator"));
            Logger.info((new StringBuilder("Entry added to CSV at ")).append(Config.csvPath).append(".").toString());
            appendWriter.close();
        } catch(IOException e) {
            Logger.error((new StringBuilder("Error adding log CSV entry ")).append(e.toString()).append(".").toString());
        }
    }

    private static List<NetworkFetchData> data = new ArrayList<NetworkFetchData>();

}
