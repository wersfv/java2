package Server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

public class Room_list {

Vector<Room_info> RI = new Vector<Room_info>();
	
	public synchronized void add(Room_info ri) {
		RI.add(ri);
	}
	
	public synchronized int roomnum() {
		if(RI.size() == 0) {
			return 1;
		} else {
			return RI.get(RI.size()-1).get_num() + 1;
		}
	}
	
	public synchronized int search(String name) {
		int room_num = 0;
		for(int i=0; i<RI.size();i++)
		{
			if(RI.get(i).get_name().equals(name))
			{
				room_num = RI.get(i).get_num();
			}
		}
		return room_num;
	}
	
	
	public synchronized String[] room_list() {
		String[] name = new String[RI.size()];
		for(int i=0; i<RI.size();i++)
		{
			name[i] = RI.get(i).get_name();
		}
		return name;
	}
	
	public synchronized void remove(int room_num) {
		for(int i=0; i<RI.size();i++)
		{
			if(RI.get(i).get_num() == room_num)
			{
				RI.remove(i);
			}
		}
	}
}