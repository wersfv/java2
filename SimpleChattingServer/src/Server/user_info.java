package Server;

import java.io.PrintWriter;
import java.net.Socket;

public class user_info {

	PrintWriter Writer;
	String nic;
	int room_num;
	
	public user_info(PrintWriter Writer,String nic,int room_num){
		this.Writer = Writer;
		this.nic = nic;
		this.room_num = room_num;
	}

	
	public PrintWriter get_Writer(){
		return Writer;
	}
	public String get_nic(){
		return nic;
	}
	public int get_room_num(){
		return room_num;
	}
}
