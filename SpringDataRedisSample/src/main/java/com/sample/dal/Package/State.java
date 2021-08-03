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
@Override
public String toString() {
	return "State [description=" + description + ", date=" + date + ", position=" + position + "]";
}
}
