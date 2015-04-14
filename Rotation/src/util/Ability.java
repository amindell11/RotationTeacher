package util;


public class Ability {
	private double cooldown;
	private String name;
	private String icon;
	private String description;
	private long logID;
	public Ability(double cooldown, String name, String icon,
			String description, long logID) {
		super();
		this.cooldown = cooldown;
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.logID = logID;
	}
	public double getCooldown() {
		return cooldown;
	}
	public String getName() {
		return name;
	}
	public String getIcon() {
		return "icons/"+icon+".png";
	}
	public String getDescription() {
		return description;
	}
	public long getLogID() {
		return logID;
	}
	public String toString(){
		return getName();
	}
}
