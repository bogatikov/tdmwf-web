package my.webapp.cmd.manager;

import my.downloader.DownloadManager;
import my.webapp.DownloaderObserver;

import javax.websocket.Session;
import java.net.URL;

public class AddCmd implements Executable {
    private Session session;
    @Override
    public void execute(String data) {
        URL url = DownloadManager.verifyURL(data);
        System.out.println("URL: " + data);
        if (url != null) {
            DownloadManager.getInstance().createDownload(url, "").addObserver(DownloaderObserver.getInstance());

        }
    }

    @Override
    public void setId(int id) {}

    @Override
    public void setSession(Session session) {

    }
}
