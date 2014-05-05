package util;

import java.util.Scanner;

/**
 * Encapsulates user input from command prompt
 * @author Matthew O'Shaughnessy
 *
 */
public class User {

    public User() {
    	
    }

    /**
     * Prompts the user for a command line input
     * @return - String of user input before return is pressed
     */
    public static String prompt()
    {
        System.out.print("gtregalert> ");
        return scanner.nextLine();
    }

    private static Scanner scanner;

    static  {
        scanner = new Scanner(System.in);
    }
}
