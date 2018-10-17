package com.ClassDiagrams.Design.Models;

import java.util.Random;

public class Connection
{
	private Transformer transformer;
	private Building building;
	private boolean status;
	private float distance;
	public Connection(Transformer transformer,Building building)
	{
		this.transformer = transformer;
		this.building = building;
		status = false;
		Random random = new Random();
		distance = random.nextFloat();
	}

	public Transformer getTransformer()
	{
		return transformer;
	}

	public Building getBuilding()
	{
		return building;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public boolean getStatus() {
		return status;
	}
}