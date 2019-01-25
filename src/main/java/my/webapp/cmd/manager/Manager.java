package my.webapp.cmd.manager;

import javax.websocket.Session;

public class Manager {

    private static Manager instanse;
    protected Manager() {}

    public static Manager getInstanse() {
        if (instanse == null) instanse = new Manager();
        return instanse;
    }

    public void cmd(Session session,String cmd, int id, String data) throws ExecuteCmdException
    {
        Command command = getCmdClass(cmd);
        if (command == null) throw new ExecuteCmdException();

        command.setSession(session);
        command.setId(id);
        command.execute(data);
    }

    protected Command getCmdClass(String cmd)
    {
        try {
            Class<?> t = Class.forName(getCmdClassName(cmd) );
            Command cls = (Command) t.newInstance();

            return cls;
        } catch (Exception e) {
            return null;
        }
    }

    protected String getCmdClassName(String cmd)
    {
        char [] lits  = cmd.toLowerCase().toCharArray();
        lits[0] = cmd.toUpperCase().charAt(0);
        cmd = String.valueOf(lits);

        return "my.webapp.cmd.manager." + cmd + "Cmd";
    }
}
