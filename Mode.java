
public enum Mode {
	DIE("die"),
	FIRE("fire"),
	FORWARD("forward");
	
	private String name = null;
	
	private Mode(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
