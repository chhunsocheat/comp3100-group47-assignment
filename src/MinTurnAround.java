import java.util.ArrayList;

import javax.lang.model.type.ArrayType;

public class MinTurnAround implements SchedulingAlgo {
    public int jobcoreRequirement;
    public ArrayList<ServerObject> servers;
    public ArrayList<ServerObject> active;
    public ArrayList<ServerObject> booting;
    public ArrayList<ServerObject> idle;
    public ArrayList<ServerObject> inactive;

    public void populateServers(ArrayList<String> serverStatuses) {
        servers = new ArrayList<ServerObject>();
        for (String s : serverStatuses) {
            String[] sArray = s.split(" ");
            ServerObject sObj = new ServerObject(sArray);
            this.servers.add(sObj);
        }
        this.subgroupServers();
    }

    public void subgroupServers() {
        active = new ArrayList<ServerObject>();
        idle = new ArrayList<ServerObject>();
        booting = new ArrayList<ServerObject>();
        inactive = new ArrayList<ServerObject>();
        for (ServerObject sO : this.servers) {
            switch (sO.serverState) {
                case "active": {
                    active.add(sO);
                    break;
                }
                case "booting": {
                    booting.add(sO);
                    break;
                }
                case "idle": {
                    idle.add(sO);
                    break;
                }
                case "inactive": {
                    inactive.add(sO);
                    break;

                }
                default: {
                    
                    System.out.println(sO.serverState + "Server State");
                    break;
                }
            }
        }
    }

    public ServerObject largestCurrentCapable(ArrayList<ServerObject> serverLists) {
        if(serverLists.isEmpty())
            return null;
        ServerObject largest = getLargestServer(serverLists);
        if (largest.coreCount < this.jobcoreRequirement)
            return null;
        return largest;
    }

    // Getting the smallest server to run on the job first
    // so that we can save the cost because the smaller server cost much less
    public ServerObject getLargestServer(ArrayList<ServerObject> serverLists) {
        ServerObject largest = serverLists.get(0);
        for (ServerObject s : serverLists) {
            // < means largest
            if (largest.compareTo(s) < 0) {
                largest = s;
            }
        }
        System.out.println(largest + "Largest Server");
        return largest;
    }
    public ServerObject leastBusyServer(ArrayList<ServerObject> serverLists){
        ServerObject leastBusy = serverLists.get(0);
        for (ServerObject s : serverLists) {
            // < means largest
            if (leastBusy.runningJ+leastBusy.waitingJ > s.runningJ+s.waitingJ) {
                leastBusy = s;
            }
        }        
        return leastBusy;


    }
    // public ServerObject setLargestCurrServer() {

    //     // System.out.println(servers + "Server Curr");
    //     // ServerObject largestCurr = getLargestServer();
    //     // System.out.println(this.jobcoreRequirement + "requirement");

    //     // if(largestCurr.coreCount<jobcoreRequirement){
    //     // servers.remove(largestCurr);
    //     // System.out.println("Bigger");
    //     // }
    //     // System.out.println(servers + "Server Curr");

    //     // largestCurr = getLargestServer();

    //     // System.out.println(largestCurr + "Largest Curr");
    //     // return largestCurr;
    // }

    
    public void setJobCore(int coreCount) {
        this.jobcoreRequirement = coreCount;
    }

    @Override
    public void setServers(ArrayList<ServerObject> s) {
        servers = s;
    }

    @Override
    public ServerObject getSCHDServer() {
        // return this.setLargestCurrServer(); // something like "super-silk 0"
        ServerObject largest = this.largestCurrentCapable(this.active);
        if (largest != null)
            return largest;

        largest = this.largestCurrentCapable(this.idle);
        if (largest != null)
            return largest;

        largest = this.largestCurrentCapable(this.booting);
        if (largest != null)
            return largest;

        largest = this.largestCurrentCapable(this.inactive);
        if (largest != null)
            return largest;

        return this.leastBusyServer(this.servers);
    }
}
