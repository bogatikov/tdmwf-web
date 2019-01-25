package my.webapp;

import my.downloader.DownloadManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class DownloaderDaemon implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            InputStream logSetting = DownloaderDaemon.class.getClassLoader().getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(logSetting);
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
        Thread th = new Thread() {

            public void run() {
                DownloadManager dm = DownloadManager.getInstance();
            }
        };

        th.setDaemon(true);
        th.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Daemon killed");
    }
}
