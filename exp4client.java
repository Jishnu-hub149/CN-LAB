package lab;

import java.io.*;
import java.net.*;

public class exp4client {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the SERVER/DOMAIN NAME or IP address:");
            String str = in.readLine();
            sendbyte = str.getBytes();
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
            client.send(sender);
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            client.receive(receiver);
            String s = new String(receiver.getData(), 0, receiver.getLength()).trim();
            System.out.println("Response from server: " + s);
            client.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
