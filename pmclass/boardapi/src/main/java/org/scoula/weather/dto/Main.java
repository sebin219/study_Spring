package org.scoula.weather.dto;

public class Main{
	private Object temp;
	private Object tempMin;
	private int grndLevel;
	private int humidity;
	private int pressure;
	private int seaLevel;
	private Object feelsLike;
	private Object tempMax;

	public Object getTemp(){
		return temp;
	}

	public Object getTempMin(){
		return tempMin;
	}

	public int getGrndLevel(){
		return grndLevel;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public int getSeaLevel(){
		return seaLevel;
	}

	public Object getFeelsLike(){
		return feelsLike;
	}

	public Object getTempMax(){
		return tempMax;
	}
}
