package Server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

public class User_list {

	ArrayList<user_info> UI = new ArrayList<user_info>();
	
	public synchronized void add(user_info ui) {
		UI.add(ui);
	}
	
	public synchronized Vector<PrintWriter> chatroom(int num) {
		Vector<PrintWriter> PW = new Vector<PrintWriter>(); 
		for(int i = 0; i<UI.size(); i++) {
			if(UI.get(i).get_room_num() == num) {
				PW.add(UI.get(i).get_Writer());
			}
		}
		return PW;
	}
	
	public synchronized void remove(user_info ui) {
		for(int i=0; i<UI.size();i++)
		{
			if(UI.get(i).equals(ui))
			{
				UI.remove(i);
			}
		}
	}
}
