package my.webapp.message;

public class RequestMessage {
    private String [] actions = {"PAUSE", "RESUME", "ADD"};
    private String cmd;
    private String data;
    private int id;


    public void setData(String data) {
        this.data = data;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCmd() {
        return cmd;
    }

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

}
