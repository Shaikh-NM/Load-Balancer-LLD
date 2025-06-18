package server;

public class Server {
    private String serverId;
    private boolean health;

    public Server(String id) {
        this.serverId = id;
        this.health = true;
    }

    public boolean isHealthy() {
        return health;
    }

    public void setHealth(boolean health){
        this.health = health;
    }
}
