package com.ClassDiagrams.Design.Models;

public class Building
{
	private String name;
	private float load;
	private double latitude, longitude;
	private int ID;
    private Transformer defaultTransformer;

	public Building(int ID, String name, float load, double latitude, double longitude, Transformer transformer) {
		this.ID = ID;
		this.name = name;
		this.load = load;
		this.latitude = latitude;
		this.longitude = longitude;
		this.defaultTransformer = transformer;
	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public Transformer getDefaultTransformer() {
		return defaultTransformer;
	}


	public float getLoad()
	{
		return load;
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setLoad(float load) {
        this.load = load;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "["+ getID()+"]"+getName();
    }

    @Override
    public boolean equals(Object obj) {
        return (this.getID() == ((Building) obj).getID());
    }

}
