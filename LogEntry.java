public class LogEntry
{
    private String status;
    private String ipAddress;
    private String username;
    private String timestamp;
    private String api;
    private int latency;

    public LogEntry(
        String status,
        String ipAddress,
        String username,
        String timestamp,
        String api,
        int latency)
    {
        this.status = status;
        this.ipAddress = ipAddress;
        this.username = username;
        this.timestamp = timestamp;
        this.api = api;
        this.latency = latency;
    }

    public String getStatus()
    {
        return status;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public String getUsername()
    {
        return username;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public String getApi()
    {
        return api;
    }

    public int getLatency()
    {
        return latency;
    }

    public boolean isFailedLogin()
    {
        return status.equals("FAILED");
    }

    public boolean isSuccessfulLogin()
    {
        return status.equals("SUCCESS");
    }

    public String getDescription()
    {
        return timestamp + " | " +
               status + " | IP: " + ipAddress +
               " | User: " + username +
               " | API: " + api +
               " | Latency: " + latency + " ms";
    }

    public String toString()
    {
        return status + " - " +
               ipAddress + " - " +
               username + " - " +
               timestamp + " - " +
               api + " - " +
               latency + " ms";
    }
}