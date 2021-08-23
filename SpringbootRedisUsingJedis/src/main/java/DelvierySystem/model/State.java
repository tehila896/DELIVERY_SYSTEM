package DelvierySystem.model;

import java.awt.Point;
import java.io.Serializable;
import java.time.LocalDateTime;

public class State implements Serializable{

	String description;
	LocalDateTime date;
	Point position;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
}
