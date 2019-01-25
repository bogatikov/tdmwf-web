package my.webapp.cmd.manager;

import my.downloader.DownloadManager;
import my.downloader.Downloader;

import javax.websocket.Session;

public class PauseCmd implements Command {
    private int id;
    @Override
    public void execute(String data) {
        DownloadManager dm = DownloadManager.getInstance();
        Downloader downloader = dm.getDownload(id);
        downloader.pause();
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setSession(Session session) {

    }
}
