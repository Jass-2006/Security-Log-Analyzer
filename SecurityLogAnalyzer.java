import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class SecurityLogAnalyzer
{
    public static void main(String[] args)
    {
        System.out.println("===== SECURITY LOG ANALYZER =====");

        int successfulLogins = 0;
        int failedLogins = 0;

        // Store failed attempts for each IP address
        HashMap<String, Integer> failedAttempts = new HashMap<>();

        // Store all log entries
        ArrayList<LogEntry> logEntries = new ArrayList<>();

        try
        {
            File file = new File("logs.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine())
            {
                String line = input.nextLine();

                String[] parts = line.split(",");

                String status = parts[0];
                String ip = parts[1];
                String username = parts[2];
                String timestamp = parts[3];
                String api = parts[4];
                int latency = Integer.parseInt(parts[5]);

                LogEntry entry = new LogEntry(
                    status,
                    ip,
                    username,
                    timestamp,
                    api,
                    latency
                );

                logEntries.add(entry);

                if (entry.isSuccessfulLogin())
                {
                    successfulLogins++;
                }
                else if (entry.isFailedLogin())
                {
                    failedLogins++;

                    if (failedAttempts.containsKey(ip))
                    {
                        failedAttempts.put(
                            ip,
                            failedAttempts.get(ip) + 1
                        );
                    }
                    else
                    {
                        failedAttempts.put(ip, 1);
                    }
                }
            }

            input.close();

            // Login summary
            System.out.println();
            System.out.println("===== LOGIN SUMMARY =====");

            System.out.println(
                "Successful logins: " + successfulLogins
            );

            System.out.println(
                "Failed logins: " + failedLogins
            );

            // Failed attempts by IP
            System.out.println();
            System.out.println("===== FAILED ATTEMPTS BY IP =====");

            for (String ip : failedAttempts.keySet())
            {
                int attempts = failedAttempts.get(ip);

                System.out.println(
                    ip + " - " +
                    attempts +
                    " failed attempts"
                );
            }

            // Suspicious IP detection
            int threshold = 3;

            System.out.println();
            System.out.println("===== SUSPICIOUS IP ADDRESSES =====");

            for (String ip : failedAttempts.keySet())
            {
                int attempts = failedAttempts.get(ip);

                if (attempts >= threshold)
                {
                    System.out.println(
                        ip + " - " +
                        attempts +
                        " failed attempts"
                    );
                }
            }

            // Display all log entries
            System.out.println();
            System.out.println("===== LOG ENTRIES =====");

            for (LogEntry entry : logEntries)
            {
                System.out.println(entry.getDescription());
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println(
                "Could not find logs.txt"
            );
        }
        catch (NumberFormatException e)
        {
            System.out.println(
                "Invalid latency value in logs.txt"
            );
        }
    }
}