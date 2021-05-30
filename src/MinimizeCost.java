import java.util.ArrayList;

public class MinimizeCost {
    public ArrayList<ServerObject> servers;
    //this method is to populate the serverObj into the this.servers property 
    //of the MinimizeCost class
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
            //compareTo is the method from the serverObj that compares two server to see
            //which one is bigger 
            if (smallestServer.compareTo(s) > 0) {
                smallestServer = s;
            }
        }
        return smallestServer;
    }

    //this method puts everything together and output the server it decide to schedule to
    public String MinimizeCostAlgo(ArrayList<String> serverStatuses){
        readPopulateServer(serverStatuses);
        ServerObject  serverObject= getSmallestServer();
        return serverObject.type + " " + serverObject.id;
    }
}