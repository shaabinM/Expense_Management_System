package com.shaabin_m.login_main;

import android.util.Log;

public class model {
    String Name,Email,Flat;
    model(){

    }
    public model(String name, String email, String flat) {
        Name = name;
        Email = email;
        Flat = flat;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFlat() {
        return Flat;
    }

    public void setFlat(String flat) {
        Flat = flat;
    }
}
