package strategy.implementations;

import java.util.List;

import request.Request;
import server.Server;
import strategy.LoadBalancingStrategy;

public class LeastConnections implements LoadBalancingStrategy {
    private int getConnections(Server server) {
        return 0;
    }

    @Override
    public Server getServer(List<Server> servers, Request request) {
        int minConnections = Integer.MAX_VALUE;
        Server selectedServer = null;

        for (Server server : servers) {
            if (server.isHealthy()) {
                int connections = getConnections(server);
                if (connections < minConnections) {
                    minConnections = connections;
                    selectedServer = server;
                }
            }
        }

        if (selectedServer == null) {
            throw new IllegalStateException("No healthy servers available");
        }
        return selectedServer;
    }
}
