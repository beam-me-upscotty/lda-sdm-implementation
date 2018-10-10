package com.ClassDiagrams.Design.Models;

import java.util.Scanner;

import static java.lang.System.out;

public class Transformer
{
	private int ID;
	private String name;
	private float kVA, load;
	private boolean status;
	private double latitude, longitude;
	public Transformer(int ID, String name, float kVA, double latitude,double longitude)
	{
		this.ID = ID;
		this.name = name;
		this.kVA = kVA;
		load = 0;
		status = true;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public void toggle()
	{
		if(status) {
			status = false;
		} else {
			status = true;
		}
		out.println("STATUS of "+ this + " changed to "+status);
	}
	public void modify()
	{
		Scanner sc = new Scanner(System.in);
		out.println("Which field would you like to modify?");
		out.print("1. Name\n2. KVA\n3. Location\nEnter your choice: ");
		int choice = sc.nextInt();
		switch(choice)
		{
			case 1: out.print("Enter the modified name: ");
					name = sc.nextLine();
					break;
			case 2: out.print("Enter the modified kVA: ");
					kVA = sc.nextFloat();
					break;
			case 3: out.print("Enter the modified latitude: ");
					latitude = sc.nextDouble();
					out.print("Enter the modified longitude: ");
					longitude = sc.nextDouble();
					break;
			default:
                out.println("Error! Incorrect choice.");
		}
	}
	public float getLoad()
	{
		return load;
	}
	public float getkVA()
	{
		return kVA;
	}
	public void setLoad(float newLoad)
	{
		load = newLoad;
	}

	@Override
	public String toString() {
		return "["+name+":"+kVA+"]";
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

    @Override
    public boolean equals(Object obj) {
        return ((Transformer)obj).ID == this.ID;
    }
}

