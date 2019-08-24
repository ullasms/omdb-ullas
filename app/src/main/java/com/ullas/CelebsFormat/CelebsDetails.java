package com.ullas.CelebsFormat;

import java.io.Serializable;

public class CelebsDetails  implements Serializable{

    String name;
    String biography;
    String birthday;
    String place_of_birth;
    String profile_path;

    public String getName() {
        return name;
    }

    public String getBiography() {
        return biography;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
