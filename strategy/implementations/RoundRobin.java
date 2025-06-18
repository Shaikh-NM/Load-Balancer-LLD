package strategy.implementations;

import java.util.List;

import request.Request;
import server.Server;
import strategy.LoadBalancingStrategy;

public class RoundRobin implements LoadBalancingStrategy {
    private int currIdx;

    public RoundRobin() {
        this.currIdx = 0;
    }

    @Override
    public Server getServer(List<Server> servers, Request request) {
        int totalServers = servers.size();
        if (totalServers == 0) {
            throw new IllegalStateException("No Servers available");
        }

        Server server = servers.get(currIdx);
        currIdx = (currIdx + 1) % totalServers;
        return server;
    }
}
