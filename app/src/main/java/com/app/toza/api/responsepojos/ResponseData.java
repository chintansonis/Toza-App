package com.app.toza.api.responsepojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseData implements Serializable{

@SerializedName("State")
@Expose
private List<State> state = null;
@SerializedName("City")
@Expose
private List<City> city = null;

public List<State> getState() {
return state;
}

public void setState(List<State> state) {
this.state = state;
}

public List<City> getCity() {
return city;
}

public void setCity(List<City> city) {
this.city = city;
}

}
