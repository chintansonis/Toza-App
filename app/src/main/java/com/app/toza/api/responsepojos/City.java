package com.app.toza.api.responsepojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {

@SerializedName("CityId")
@Expose
private Integer cityId;
@SerializedName("StateId")
@Expose
private Integer stateId;
@SerializedName("CityName")
@Expose
private String cityName;
@SerializedName("CountryId")
@Expose
private Integer countryId;

public Integer getCityId() {
return cityId;
}

public void setCityId(Integer cityId) {
this.cityId = cityId;
}

public Integer getStateId() {
return stateId;
}

public void setStateId(Integer stateId) {
this.stateId = stateId;
}

public String getCityName() {
return cityName;
}

public void setCityName(String cityName) {
this.cityName = cityName;
}

public Integer getCountryId() {
return countryId;
}

public void setCountryId(Integer countryId) {
this.countryId = countryId;
}

}


