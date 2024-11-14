package org.example.usermicroservice.utils;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class HelperUsers {

    public ArrayList enumRoles;

    public HelperUsers(){
        this.enumRoles.add("usuario");
        this.enumRoles.add("admin");
        this.enumRoles.add("mantenimiento");
    }

}
