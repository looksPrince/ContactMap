package com.android.contactmap.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author DEEPANKAR
 * @since 17-04-2016.
 */
public class ContactData {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("officePhone")
    private String officePhone;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

//    public String print(){
//        return ""+name+"/"+email+"/"+phone+"/"+officePhone+"/"+latitude+"/"+longitude;
//    }

}
