/**
 * 
 */
package lib.data.comm;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import lib.data.user.User;

/**
 * @author sgutierc
 *
 */
public class CommManager {

	// the initialization-on-demand holder/singleton pattern
	private static class LazyHolder {
		static final CommManager INSTANCE = new CommManager();
	}

	/**
	 * Retorna una instancia de esta clase, bajo patron <b>initialization-on-demand
	 * singleton</b>.
	 * 
	 * @return
	 */
	public static CommManager getInstance() {
		return LazyHolder.INSTANCE;
	}

	// Y ahora los parametros de la clase

	private HashMap<User<?>, List<Device>> usersHash = null;
	private HashMap<Device, User<?>> deviceHash = null;

	/**
	 * 
	 */
	private CommManager() {
		usersHash = (HashMap<User<?>, List<Device>>) java.util.Collections
				.synchronizedMap(new HashMap<User<?>, List<Device>>());
		deviceHash = (HashMap<Device, User<?>>) java.util.Collections.synchronizedMap(new HashMap<Device, User<?>>());
	}

	/**
	 * Permite enlazar internamente el Usuario con el dispositivo.
	 * 
	 * @param user
	 * @param device
	 */
	public void registerUserWithDevice(User<?> user, Device device) {
		List<Device> listInt = usersHash.get(user);
		if (listInt == null) {// si no se han cargado Device para el usuario, creamos el nuevo listado de
								// Device.
			listInt = new CopyOnWriteArrayList<>();
		} else {
			try {// si ya existe el Device, primero intentare quitarlo del listado.. asi no se
					// incluye dos veces en la lista.
				listInt.remove(device);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		listInt.add(device);// ahora lo añado al listado
		usersHash.put(user, listInt);// y cargo el listado en el hash
		deviceHash.put(device, user);
	}

	/**
	 * Retorna un listado de dispositivos asociados a un usuario, o nulo si no posee
	 * dispositivos asociados al usuario
	 * 
	 * @param user
	 * @return
	 */
	public List<Device> getDeviceFor(User<?> user) {
		return usersHash.get(user);
	}

	/**
	 * Retorna el usuario asociado al dispositivo, o nulo si no existe registro del
	 * usuario
	 * 
	 * @param device
	 * @return
	 */
	public User<?> getUserFor(Device device) {
		return deviceHash.get(device);

	}

}
