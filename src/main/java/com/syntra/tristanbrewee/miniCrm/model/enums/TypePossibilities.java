package com.syntra.tristanbrewee.miniCrm.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum TypePossibilities {

    WORK("work"),
    PRIVATE("private"),
    OTHER("other");

    private String value;
    private static final List<String> VALUES = new ArrayList<>();

    private TypePossibilities(String value){
        this.value = value;
    }

    static {
        for (TypePossibilities typePossibilities : TypePossibilities.values()){
            VALUES.add(typePossibilities.getValue());
        }
    }

    public static List<String> getValues(){
        return VALUES;
    }

    public String getValue(){
        return value;
    }
}
