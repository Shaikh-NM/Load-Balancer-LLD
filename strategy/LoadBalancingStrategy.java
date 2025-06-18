package strategy;

import java.util.List;

import request.Request;
import server.Server;

public interface LoadBalancingStrategy {
    Server getServer(List<Server> servers, Request request);
}
