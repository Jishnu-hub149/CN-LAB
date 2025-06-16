package lab;
import java.io.*; 
import java.net.*; 
public class exp3cclient { 
    public static void main(String args[]) throws Exception { 
        InetAddress ia = InetAddress.getLocalHost(); 
        Socket s = new Socket(ia, 1024); 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        System.out.println("Enter a new File Name:"); 
        String fname = br.readLine(); 
        File f1 = new File(fname); 
        PrintWriter p = new PrintWriter(new FileWriter(fname)); 
        BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream())); 
        String str; 
        while ((str = br1.readLine()) != null) 
            p.println(str); 
        p.close(); 
        s.close(); 
    } 
}