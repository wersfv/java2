package Server;

public class Room_info {
	private int room_num;
	private String room_name;
	
	public Room_info(int room_num, String room_name) {
		this.room_num = room_num;
		this.room_name = room_name;
	}
	
	public int get_num() {
		return this.room_num;
	}
	
	public String get_name() {
		return this.room_name;
	}
}
