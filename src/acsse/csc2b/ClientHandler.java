/**
 * 
 */
package acsse.csc2b;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * @author Thalukanyo
 *
 */
public class ClientHandler implements Runnable{
	
	private Socket socket;
	
	public ClientHandler(Socket clientSocket) {
		this.socket = clientSocket;
	}
	@Override
	public void run() {
		BufferedReader in = null;
		DataOutputStream out = null;
		
		try
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			String request = in.readLine();
			
			StringTokenizer tokenizer = new StringTokenizer(request);
			
			String requestType = tokenizer.nextToken();
			String fileName = "data/" + tokenizer.nextToken().substring(1) + ".html";

			File file = new File(fileName);
			
			if(requestType.equals("GET"))
			{
			
				out.writeBytes("HTTP/1.1 200 OK \r\n");
				out.writeBytes("Connection: close \r\n");
				out.writeBytes("Content-Type: text/html\r\n");
				out.writeBytes("Content-Length:"+ file.length()+"\r\n");
				out.writeBytes("\r\n");
				
				BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file));
				
				byte[] buffer = new byte[1024];
				
				int i = 0;
				
				while((i = bin.read(buffer))>0)
				{ 
					out.write(buffer,0,i);
				}
					bin.close();
					out.writeBytes("\r\n");
					out.flush();
			}
		}catch(FileNotFoundException fnfe)
		{
			System.err.println("404");
			
			
			
		}catch(IOException ioe)
		{
			System.err.println("500");
			
		}
		
	}
	
	
	
}