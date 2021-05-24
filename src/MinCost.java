import java.util.ArrayList;

public class MinCost implements SchedulingAlgo {
    public ArrayList<ServerObject> servers;
    public void populateServers(ArrayList<String> serverStatuses){
        servers = new ArrayList<ServerObject>();
        for (String s : serverStatuses) {
            String[] sArray = s.split(" ");
            ServerObject sObj = new ServerObject(sArray);
            this.servers.add(sObj);
        }
    }
    //Getting the smallest server to run on the job first
    //so that we can save the cost because the smaller server cost much less
    public ServerObject getSmallestServer(){
        ServerObject largest = servers.get(0);
        for(ServerObject s: servers){
            if(largest.compareTo(s) > 0){
                largest = s;
            }
        }
        System.out.println(largest + "Smallest Server");
        return largest;
    }
    @Override
    public void setJobCore(int coreCount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setServers(ArrayList<ServerObject> s) {
        servers = s;
    }

    @Override
    public ServerObject getSCHDServer() {
        return this.getSmallestServer(); // something like "super-silk 0"
    }
}