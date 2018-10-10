package com.ClassDiagrams.Design.Models;

import java.util.List;

public class Technician extends User {
    public Technician(int id, String name, String email, String password) {
        super(id,name,email,password,false);
    }

    public static Technician findTechnicianUser(List<Technician> technicians, String username, String password) {
        for(Technician a: technicians){
            if(a.login(username,password))
                return a;
        }
        return null;
    }

    public boolean login(String username, String password) {
        if(super.checkCredentials(username, password)) {
            setLoggedIn(true);
            return true;
        }
        return false;
    }

}
