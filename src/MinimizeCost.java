import java.util.ArrayList;

public class MinimizeCost {
    public ArrayList<ServerObject> servers;

    public void readPopulateServer(ArrayList<String> serverStatuses) {
        servers = new ArrayList<ServerObject>();

        for (String s : serverStatuses) {
            String[] sArray = s.split(" ");
            ServerObject sObj = new ServerObject(sArray);
            servers.add(sObj);
        }
    }

    // Getting the smallest server to run on the job first
    // so that we can save the cost because the smaller server cost much less
    public ServerObject getSmallestServer() {
        ServerObject smallestServer = servers.get(servers.size() - 1);
        for (ServerObject s : servers) {
            // > mean smallest
            if (smallestServer.compareTo(s) > 0) {
                smallestServer = s;
            }
        }
        System.out.println("Scheduling to:" + smallestServer);
        return smallestServer;
    }

    public ServerObject getSCHDServer() {
        return getSmallestServer(); // this will provide to the Client class the server object that is the smalled capable 
        // for doing the job
    }

    public String MinimizeCostAlgo(ArrayList<String> serverStatuses){
        readPopulateServer(serverStatuses);
        ServerObject  serverObject= getSmallestServer();
        return serverObject.type + " " + serverObject.id;
    }
}