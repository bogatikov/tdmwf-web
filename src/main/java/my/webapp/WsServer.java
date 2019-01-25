package my.webapp;

import my.downloader.DownloadManager;
import my.downloader.Downloader;
import my.downloader.HttpDownloader;
import my.webapp.cmd.manager.ExecuteCmdException;
import my.webapp.cmd.manager.Manager;
import my.webapp.message.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@ServerEndpoint(value = "/websocketendpoint", encoders = {ReplyMessageEncoder.class}, decoders = {RequestMessageDecoder.class})
public class WsServer {
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());


    public static Set<Session> getSessions()
    {
        return peers;
    }
    @OnOpen
    public void onOpen(Session peer) throws Exception
    {
        peers.add(peer);
        for (Downloader downloader:
             DownloadManager.getInstance().getDownloadList()) {
            ReplyMessage message = new ReplyMessage();
            message.addObject(DownloadManager.getInstance().getDownloadList().indexOf(downloader), (String) downloader.getURL(), downloader.getState(), downloader.getProgress(), downloader.getDownloaded(), downloader.getFileSize());

            for (Object t:
                    downloader.getListDownloadThread()) {
                HttpDownloader.HttpDownloadThread th = (HttpDownloader.HttpDownloadThread) t;
                message.addThread(th);

            }

            peer.getBasicRemote().sendObject(message);
        }
    }
    @OnClose
    public void onClose(Session peer)
    {
        peers.remove(peer);
    }
    @OnMessage
    public void broadcastMessage(RequestMessage message, Session session) throws Exception
    {
        try {
            Manager.getInstanse().cmd(session, message.getCmd(), message.getId(), message.getData());

        } catch (ExecuteCmdException e) {
            e.printStackTrace();
        }
    }
    @OnError
    public void onError(Throwable e, Session session)
    {
        e.printStackTrace();
        System.out.println("Disconnect id " + session.getId());
    }

}
