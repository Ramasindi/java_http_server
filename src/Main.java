import acsse.csc2b.EstablishHTTPServer;

/**
 * 
 */

/**
 * @author Thalukanyo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EstablishHTTPServer server = new EstablishHTTPServer(4321);
		server.initiateServer();
	}

}
