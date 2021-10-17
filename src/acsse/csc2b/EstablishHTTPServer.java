package acsse.csc2b;

import java.io.IOException;
import java.net.ServerSocket;

public class EstablishHTTPServer {
	
	private ServerSocket sSocket;
	boolean running = false;
	
	public EstablishHTTPServer(int port)
	{
		try{
			sSocket = new ServerSocket(port);
			running = true;
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public void initiateServer()
	{
		while(running)
		{
			try
			{
				Thread thread = new Thread(new ClientHandler(sSocket.accept()));
				System.out.println("Server listening port: " + sSocket.getLocalPort());
				thread.start();
				
			}catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}
