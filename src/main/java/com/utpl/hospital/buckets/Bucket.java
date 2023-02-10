package com.utpl.hospital.buckets;

public enum Bucket {
    PROFILE("hospital-user-pictures");
    private final String name;
    Bucket(String name){
        this.name=name;
    }
    public String getName(){
        return  name;
    }
}
