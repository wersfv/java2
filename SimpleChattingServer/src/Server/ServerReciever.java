package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerReciever {
    public static final int PORT = 5000;
    

	
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        List<PrintWriter> listWriters = new ArrayList<PrintWriter>();
        User_list User_list = new User_list();
    	Room_list Room_list = new Room_list();
    	int roomcount = 0;

        try {
            // ���� ���� ����
            serverSocket = new ServerSocket();

            // ���ε�
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind( new InetSocketAddress(hostAddress, PORT) );
            consoleLog("���� ��ٸ� - " + hostAddress + ":" + PORT);

            // ��û ���
            while(true) {
                Socket socket = serverSocket.accept();
                new ServerProcess(socket, User_list, Room_list).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if( serverSocket != null && !serverSocket.isClosed() ) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consoleLog(String log) {
        System.out.println("[server " + Thread.currentThread().getId() + "] " + log);
    }
}