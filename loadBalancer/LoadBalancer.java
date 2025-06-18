package loadBalancer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import request.Request;
import server.Server;
import strategy.LoadBalancingStrategy;

public class LoadBalancer {
    private List<Server> servers;
    private static volatile LoadBalancer instance;
    private volatile LoadBalancingStrategy strategy;

    private LoadBalancer(LoadBalancingStrategy strategy) {
        this.strategy = strategy;
        this.servers = new CopyOnWriteArrayList<>();
    }

    public static LoadBalancer getInstance(LoadBalancingStrategy strategy) {
        if (instance == null) {
            synchronized (LoadBalancer.class) {
                if (instance == null) {
                    instance = new LoadBalancer(strategy);
                }
            }
        }
        return instance;
    }

    public void addServer(Server server) {
        if (server == null) {
            throw new IllegalArgumentException("server cannot be null");
        }
        servers.add(server);
    }

    public void removeServer(Server server) {
        servers.remove(server);
    }

    public Server getServer(Request request) {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        return strategy.getServer(servers, request);
    }

    public void updateStrategy(LoadBalancingStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("LB strategy cannot be null");
        }
        this.strategy = strategy;
    }
}
