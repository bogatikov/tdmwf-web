package my.webapp.cmd.manager;

import javax.websocket.Session;

public interface Command {
    void execute(String data); // The main action
    void setId(int id); // If client send ID
    void setSession(Session session); // Sender session
}
