
public enum Mode {
	DIE("die"),
	JUMP("jump"),
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
