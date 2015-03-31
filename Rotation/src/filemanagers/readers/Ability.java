package filemanagers.readers;

import javafx.scene.image.Image;

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
	public Image getIcon() {
		return new Image("file:icons/"+icon+".png");
	}
	public String getDescription() {
		System.out.println(description);
		return description;
	}
	public long getLogID() {
		return logID;
	}
}
