package DelvierySystem.model;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Data
public class PriceField implements Serializable{
@Valid
@JsonProperty
@Id
UnitOfDistance row;
@Valid
@JsonProperty
Map<CarryingCapacity,Double> columns;
public Map<CarryingCapacity, Double> getColumns() {
	return columns;
}
public void setColumns(Map<CarryingCapacity, Double> columns) {
	this.columns = columns;
}
public UnitOfDistance getRow() {
	return row;
}
public void setRow(UnitOfDistance row) {
	this.row = row;
}
}