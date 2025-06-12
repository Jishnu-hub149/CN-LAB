package lab;
import java.io.*; 
import java.net.*; 
public class exp2 {
	public static void main(String[] args) { 
		try { 
			Socket socket = new Socket("www.martinbroadhurst.com", 80); 
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true); 
			out.println("GET / HTTP/1.1"); 
			out.println("Host: www.martinbroadhurst.com"); 
			out.println("");  
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			String responseLine; 
			while ((responseLine = in.readLine()) != null) {
				System.out.println(responseLine); 
			} 
			in.close(); 
			out.close(); 
			socket.close(); 
			} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	}
}
