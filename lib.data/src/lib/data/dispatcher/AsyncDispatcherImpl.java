/**
 * 
 */
package lib.data.dispatcher;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import lib.data.comm.messages.Message;

/**
 * Permite despachar mensajes a receptores interesados en el tipo de mensaje
 * recibido, esto lo realiza de modo asincrono, para lo cual maneja un ejecutor
 * con un hilo (1) de ejecucion.
 * 
 * Esta clase implementa el patron singleton.
 * 
 * @author sgutierc
 *
 */
public class AsyncDispatcherImpl implements Dispatcher, Runnable {

	// the initialization-on-demand holder/singleton pattern
	private static class LazyHolder {
		static final AsyncDispatcherImpl INSTANCE = new AsyncDispatcherImpl();
	}

	/**
	 * Retorna una instancia de esta clase, bajo patron <b>initialization-on-demand
	 * singleton</b>.
	 * 
	 * @return
	 */
	public static AsyncDispatcherImpl getInstance() {
		return LazyHolder.INSTANCE;
	}

	// Y ahora los parametros de la clase
	protected LinkedBlockingQueue<Message<?, ?>> queue = null;
	protected HashMap<Class<?>, List<Listener>> listeners = null;
	private Executor executor = Executors.newCachedThreadPool();

	/**
	 * 
	 */
	private AsyncDispatcherImpl() {
		queue = new LinkedBlockingQueue<>();
		listeners = (HashMap<Class<?>, List<Listener>>) java.util.Collections.synchronizedMap(new HashMap<Class<?>, List<Listener>>());
		executor.execute(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.data.dispatcher.Dispatcher#dispatch(lib.data.comm.messages.Message)
	 */
	@Override
	public void dispatch(Message<?, ?> message) {
		try {
			queue.put(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.data.dispatcher.Dispatcher#addListener(lib.data.dispatcher.Listener,
	 * java.lang.Class)
	 */
	@Override
	public void addListener(Listener listener, Class<?> interest) {
		List<Listener> listInt = listeners.get(interest);
		if (listInt == null) {// si no se han cargado listeners para la clase, creamos el nuevo listado de
								// listeners.
			listInt = new CopyOnWriteArrayList<>();
		} else {
			try {// si ya existen interesados, primero intentare quitarlo del listado.. asi no se
					// incluye dos veces en la lista.
				listInt.remove(listener);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		listInt.add(listener);// ahora lo añado al listado
		listeners.put(interest, listInt);// y cargo el listado en el hash
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lib.data.dispatcher.Dispatcher#removeListener(lib.data.dispatcher.Listener,
	 * java.lang.Class)
	 */
	@Override
	public void removeListener(Listener listener, Class<?> interest) {
		List<Listener> listInt = listeners.get(interest);
		if (listInt != null)// si el listado para el interes es nulo, no hay nada que hacer. Caso
							// contrario...
			try {
				listInt.remove(listener);// intento eliminar el listener del sub listado interno
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (listInt.isEmpty())// si la lista quedo vacia...
					listeners.remove(interest);// quito el interes del hash principal de interesados
				else
					listeners.put(interest, listInt);// si la lista no esta vacia, entonces actualizo el listado en el
														// hash principal
			}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {
			try {
				Message<?, ?> message = queue.poll(30, TimeUnit.SECONDS);
				if (message != null) {
					Class<?> interest = message.getClass();
					List<Listener> listInt = listeners.get(interest);
					for (Listener listener : listInt)
						executor.execute(new Messenger(message, listener));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Clase interna, que hace las de mensajero para entregar un mensaje a un
	 * receptor interesado
	 * 
	 * @author sgutierc
	 *
	 */
	protected class Messenger implements Runnable {
		private Message<?, ?> message;
		private Listener listener;

		public Messenger(Message<?, ?> message, Listener listener) {
			this.message = message;
			this.listener = listener;
		}

		@Override
		public void run() {
			if (listener != null)
				listener.receive(message);
		}
	}

}
