package org.scoula.weather.dto;

import lombok.Data;

import java.util.List;

@Data
public class WeatherDTO {
	private int visibility;
	private int timezone;
	private Main main;
	private Clouds clouds;
	private Sys sys;
	private int dt;
	private Coord coord;
	private List<WeatherItem> weather;
	private String name;
	private int cod;
	private int id;
	private String base;
	private Wind wind;

	@Data
	public static class Main {
		private double temp;
		private int humidity;
	}

	@Data
	public static class WeatherItem {
		private String description;
		private String icon;
	}

	@Data
	public static class Clouds {
		private int all;
	}

	@Data
	public static class Sys {
		private String country;
	}

	@Data
	public static class Coord {
		private double lon;
		private double lat;
	}

	@Data
	public static class Wind {
		private double speed;
		private int deg;
	}
}