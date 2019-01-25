package my.webapp.message;

import org.json.simple.JSONObject;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ReplyMessageEncoder implements Encoder.Text<ReplyMessage> {
    @Override
    public String encode(ReplyMessage replyMessage) throws EncodeException {
        //TODO method must send information about downloads

        return replyMessage.getJsonString();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }
}
