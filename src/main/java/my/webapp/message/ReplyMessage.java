package my.webapp.message;

import my.downloader.HttpDownloader;
import org.json.simple.JSONObject;

public class ReplyMessage {

    private JSONObject json;
    private JSONObject threads;

    public ReplyMessage() {
        json = new JSONObject();
        threads = new JSONObject();
        json.put("threads", threads);
    }

    public void addObject(int id, String filename, int status, double progress, int downloaded, int fileSize)
    {
        json.put("id", id);
        json.put("filename", filename);
        json.put("fileSize", fileSize);
        json.put("status", status);
        json.put("progress", progress);
        json.put("downloaded", downloaded);

    }
    public void addThread(HttpDownloader.HttpDownloadThread th) {

        JSONObject thread = new JSONObject();
        thread.put("progress", th.getThreadProgress());
        thread.put("start", th.getStartByte());
        thread.put("endByte", th.getEndByte());
        thread.put("isFinished", th.isFinished());

        threads.put(th.getThreadID(), thread);

    }

    public String getJsonString()
    {
        return json.toJSONString();
    }
}
