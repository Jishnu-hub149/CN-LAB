package lab;
import java.io.*;
import java.net.*;

public class exp4server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            System.out.println("UDP Server is running on port 1309...");
            while (true) {
                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];
                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                server.receive(receiver);
                String str = new String(receiver.getData(), 0, receiver.getLength()).trim();
                System.out.println("Received: " + str);
                InetAddress addr = receiver.getAddress();
                int port = receiver.getPort();
                String[] ip = { "165.165.80.80", "165.165.79.1" };
                String[] name = { "www.aptitudeguru.com", "www.downloadcyclone.blogspot.com" };
                boolean matched = false;
                for (int i = 0; i < ip.length; i++) {
                    if (str.equals(ip[i])) {
                        sendbyte = name[i].getBytes();
                        matched = true;
                    } else if (str.equals(name[i])) {
                        sendbyte = ip[i].getBytes();
                        matched = true;
                    }

                    if (matched) {
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port);
                        server.send(sender);
                        System.out.println("Response sent: " + new String(sendbyte));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
