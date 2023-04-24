package DATA;

public class Data {
	
	private int point;
	private String name;
	private int time;
	
	public Data() {
		
	}
	
	public Data(String name, int point, int time) {
		this.point = point;
		this.name = name;
		this.time = time;
	}
	
	public int getPoint() {
		return point;
	}
	public String getName() {
		return name;
	}
	public int getTime() {
		return time;
	}

}
