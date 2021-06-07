package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

public class ServerProcess extends Thread {
	private String nickname = null;
	private Socket socket = null;
	User_list User_list = null;
	Room_list Room_list = null;

	public ServerProcess(Socket socket, User_list user_list, Room_list room_list) {
		this.socket = socket;
		this.User_list = user_list;
		this.Room_list = room_list;
	}

	@Override
	public void run() {
		try {
			BufferedReader buffereedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

			PrintWriter printWriter = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

			while (true) {
				String request = buffereedReader.readLine();

				if (request == null) {
					consoleLog("클라이언트로부터 연결 끊김");
					break;
				}
				
				System.out.println(request);

				String[] tokens = request.split(":");
				if ("join".equals(tokens[0])) {
					Join(tokens[1]);
				} else if ("message".equals(tokens[0])) {
					Message(tokens[1], Integer.parseInt(tokens[2]));
				} else if ("quit".equals(tokens[0])) {
					Quit();
				} else if ("join_room".equals(tokens[0])) {
					join_room(tokens[1], printWriter);
				} else if ("send_room_list".equals(tokens[0])) {
					send_room_list(printWriter);
				} else if ("create_room".equals(tokens[0])) {
					create_room(tokens[1]);
				} else if ("remove_room".equals(tokens[0])) {
					remove_room(Integer.parseInt(tokens[1]));
				} else if ("room_num_search".equals(tokens[0])) {
					room_num_search(tokens[1], printWriter);
				}
			}
		} catch (IOException e) {
			consoleLog(this.nickname + "님이 채팅방을 나갔습니다.");
		}
	}

	private void Quit() {
	}

	private void Message(String data ,int num) {
		broadcast(this.nickname + ":" + data, num);
	}

	private void Join(String nickname) {
		this.nickname = nickname;
	}


	private void broadcast(String data, int num) {
		Vector<PrintWriter> pw;
		pw= User_list.chatroom(num);
		for (PrintWriter writer : pw) {
			writer.println(data);
			writer.flush();
		}
	}

	private void consoleLog(String log) {
		System.out.println(log);
	}

	private void join_room(String room_num,PrintWriter Writer) {
		
		user_info ui = new user_info(Writer,nickname,Integer.parseInt(room_num));
		User_list.add(ui);
		
	}

	private void create_room(String name) {
		Room_info RI = new Room_info(Room_list.roomnum(),name);
		Room_list.add(RI);
	}
	
	private void room_num_search(String name, PrintWriter Writer) {
		
		Writer.println(Room_list.search(name));
		Writer.flush();
	}

	private void remove_room(int num) {
		Room_list.remove(num);
	}

	private void send_room_list(PrintWriter Writer) {
		String[] name;
		name = Room_list.room_list();
		System.out.println("room_num = "+name.length);
		Writer.println(name.length + "\r\n");
		Writer.flush();
		for(int i=0; i<name.length; i++) {
			Writer.println(name[i] + "\r\n");	
			Writer.flush();
			System.out.println(name[i]);
		}
		
	}

}