package util;

import config.Config;
import java.io.PrintStream;
import java.util.Scanner;
import parsing.ClassData;

/**
 * Conveniently allows logging data to be printed to the command line
 * @author Matthew O'Shaughnessy
 *
 */
public class Logger {

    public Logger() {
    	
    }

    /**
     * Displays as "info"
     * @param msg
     */
    public static void info(String msg)
    {
        String time = Clock.timeString();
        System.out.println((new StringBuilder("INFO (")).append(time).append("): ").append(msg).toString());
    }

    /**
     * Displays as "warning"
     * @param msg
     */
    public static void warn(String msg)
    {
        String time = Clock.timeString();
        System.err.println((new StringBuilder("WARN (")).append(time).append("): ").append(msg).toString());
    }

    /**
     * Displays as "data"
     * @param msg
     */
    public static void data(String msg)
    {
        String time = Clock.timeString();
        System.out.println((new StringBuilder("DATA (")).append(time).append("): ").append(msg).toString());
    }

    /**
     * Displays as "error"
     * @param msg
     */
    public static void error(String msg)
    {
        String time = Clock.timeString();
        System.err.println((new StringBuilder("ERROR (")).append(time).append("): ").append(msg).toString());
    }

    
    public static Object input(String msg, String type)
    {
        String time = Clock.timeString();
        System.out.print((new StringBuilder("(")).append(time).append(") ").append(msg).append(": ").toString());
        String input = scanner.nextLine();
        String s;
        switch((s = type.toLowerCase()).hashCode())
        {
        default:
            break;

        case -891985903: 
            if(s.equals("string"))
                return input;
            break;

        case 104431: 
            if(s.equals("int"))
                return Integer.valueOf(Integer.parseInt(input));
            break;

        case 64711720: 
            if(s.equals("boolean"))
                return Boolean.valueOf(Boolean.parseBoolean(input));
            break;
        }
        return input;
    }

    /**
     * Displays ClassData object to user
     * @param data
     */
    public static void displayClassData(ClassData data)
    {
        data((new StringBuilder("--DATA FOR CRN ")).append(Integer.toString(Config.crn)).append(":").toString());
        data((new StringBuilder("--Capacity:  ")).append(Integer.toString(data.getCapacity())).toString());
        data((new StringBuilder("--Actual:    ")).append(Integer.toString(data.getActual())).toString());
        data((new StringBuilder("--Remaining: ")).append(Integer.toString(data.getRemaining())).toString());
    }

    private static Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }
}
