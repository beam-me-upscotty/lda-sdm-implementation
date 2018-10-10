package com.ClassDiagrams.Design;

import com.ClassDiagrams.Design.Models.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private List<Transformer> transformers;
    private List<Connection> connections;
    private List<Building> buildings;
    private List<Technician> technicians;
    private List<Admin> admins;

    public final void initialize(){
        getTransformers();
        getBuildings();
        getConnections();
    }
    public final List<Transformer> getTransformers() {
        if(transformers == null){
            transformers = new ArrayList<Transformer>();
            transformers.add(new Transformer(1,"transformer1",150,18.102301,16.210154));
            transformers.add(new Transformer(2,"transformer2",250,16.210154,18.102301));
            transformers.add(new Transformer(3,"transformer3",200,17.102301,17.210154));
        }
        return transformers;
    }

    public final List<Connection> getConnections(){
        if(connections == null){
            connections = new ArrayList<Connection>();
        }
        return connections;
    }
    public final List<Building> getBuildings(){
        if(buildings == null){
            buildings = new ArrayList<Building>();
            buildings.add(new Building(1,"building 1",20,18.102451,18.102451,getTransformers().get(0)));
            buildings.add(new Building(2,"building 2",46,16.102451,18.102400,getTransformers().get(1)));
            buildings.add(new Building(3,"building 3",78,18.102800,17.102500,getTransformers().get(2)));
        }
        return buildings;
    }

    public final List<Technician> getTechnicians() {
        if(technicians == null){
            technicians = new ArrayList<Technician>();
            technicians.add(new Technician(1,"tech","tech@lda.com","tech"));
        }
        return technicians;
    }

    public final List<Admin> getAdmins() {
        if(admins == null){
            admins = new ArrayList<Admin>();
            Admin admin = new Admin(1,"admin","admin@lda.com","admin","adminlda",false);
            admins.add(admin);
        }
        return admins;
    }

    public final void initializeConnections() {
        initialize();
        for(Transformer t: transformers)
            for (Building b: buildings)
                connections.add(new Connection(t,b));
    }



    public final Connection getActiveConnection(Building building) {
        for(Connection c : connections)
            if(c.getBuilding() == building && c.isStatus())
                return c;
        return null;
    }

    public final Connection getConnectionFor(Building building,Transformer transformer) {
        for(Connection c : connections) {
            if (c.getBuilding() == building && c.getTransformer() == transformer)
                return c;
        }
        return null;
    }

}
