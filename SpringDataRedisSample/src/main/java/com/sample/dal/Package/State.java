package com.sample.dal.Package;

import java.awt.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.Data;

@Data
public class State {
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

@Override
public String toString() {
	return "State [description=" + description + ", date=" + date + ", position=" + position + "]";
}
}
