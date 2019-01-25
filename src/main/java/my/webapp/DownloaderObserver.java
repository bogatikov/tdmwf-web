package my.webapp;

import my.downloader.DownloadManager;
import my.downloader.Downloader;
import my.downloader.HttpDownloader;
import my.webapp.message.ReplyMessage;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Observable;
import java.util.Observer;

public class DownloaderObserver implements Observer {
    private static DownloaderObserver instance = null;
    private static long lastTimeSendPacket = 0;
    protected DownloaderObserver (){}


    public static DownloaderObserver getInstance()
    {
        if (instance == null)
            instance = new DownloaderObserver();
        return instance;
    }
    @Override
    public void update(Observable o, Object arg) {

        int index = DownloadManager.getInstance().getDownloadList().indexOf(o);

        HttpDownloader downloader = (HttpDownloader) o;

        ReplyMessage message = new ReplyMessage();
        message.addObject(index, (String) downloader.getURL(), downloader.getState(), downloader.getProgress(), downloader.getDownloaded(), downloader.getFileSize());

        for (Object t:
             downloader.getListDownloadThread()) {
            HttpDownloader.HttpDownloadThread th = (HttpDownloader.HttpDownloadThread) t;
            message.addThread(th);

        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        /*
         * Задрежка отправки информации о загрузке
         * Для избежания задержки последнего объекта, отдельно проверяем текущий прогресс
         * */
        if(downloader.getProgress() < 100) {
            if ((timestamp.getTime() - lastTimeSendPacket) > 50) {
                lastTimeSendPacket = timestamp.getTime();
                for (Session session:
                WsServer.getSessions()) {
                    try {
                        session.getBasicRemote().sendObject(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            for (Session session:
            WsServer.getSessions()) {
                try {
                    session.getBasicRemote().sendObject(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
