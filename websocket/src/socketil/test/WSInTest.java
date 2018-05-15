package socketil.test;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.spi.JsonProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lib.data.comm.messages.InMessage;
import lib.data.comm.messages.OutMessage;
import lib.data.dispatcher.AsyncDispatcherImpl;
import lib.data.dispatcher.Listener;

@ServerEndpoint("/tester")
public class WSInTest implements Listener {
	public WSInTest() {
		super();
		outListener = new WSOutTest();
	}

	private WSOutTest outListener = null;

	@OnOpen
	public void open(Session session) {
		System.out.println("open| Listener: " + this);

		AsyncDispatcherImpl.getInstance().addListener(this, InMessage.class);
		AsyncDispatcherImpl.getInstance().addListener(outListener, OutMessage.class);

		try {
			JsonProvider provider = JsonProvider.provider();
			JsonObject theMessage = provider.createObjectBuilder().add("message", "Aloja!").build();

			AsyncDispatcherImpl.getInstance().dispatch(new OutMessage(session, theMessage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("close");
		AsyncDispatcherImpl.getInstance().removeListener(this, InMessage.class);
		AsyncDispatcherImpl.getInstance().removeListener(outListener, OutMessage.class);

		try {
			session.close();
			session = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println("Error");
	}

	int incommingMessages = 0;

	@OnMessage
	public void handleMessage(String message, Session session) {
		System.out.println("ReceivedMessages: " + incommingMessages++);
		try (JsonReader reader = Json.createReader(new StringReader(message))) {

			JsonObject jsonMessage = reader.readObject();
			AsyncDispatcherImpl.getInstance().dispatch(new InMessage(session, jsonMessage));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.data.dispatcher.Listener#receive(java.lang.Object)
	 */
	@Override
	public void receive(Object object) {
		if (object instanceof InMessage) {
			InMessage message = (InMessage) object;
			System.out.println(message);
		}
	}
}