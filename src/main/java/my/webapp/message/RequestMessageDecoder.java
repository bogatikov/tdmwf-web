package my.webapp.message;

import my.webapp.cmd.manager.Manager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class RequestMessageDecoder implements Decoder.Text<RequestMessage> {
    @Override
    public RequestMessage decode(String s) throws DecodeException {
        JSONParser parser = new JSONParser();
        RequestMessage message = new RequestMessage();
        try {
            JSONObject json = (JSONObject) parser.parse(s);
            message.setCmd((String) json.get("cmd"));
            System.out.println("Command get from client: " + message.getCmd());
            message.setData((String) json.get("data"));
            String id = String.valueOf(json.get("id"));
            message.setId(json.get("id") == null ? -1 : Integer.parseInt(id) );

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("ParseException");
            message.setCmd("None");
            message.setId(-1);
            message.setData("");
        }
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
