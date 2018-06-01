package com.app.toza.api.responsepojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class State implements Serializable {

@SerializedName("StateId")
@Expose
private Integer stateId;
@SerializedName("StateName")
@Expose
private String stateName;

public Integer getStateId() {
return stateId;
}

public void setStateId(Integer stateId) {
this.stateId = stateId;
}

public String getStateName() {
return stateName;
}

public void setStateName(String stateName) {
this.stateName = stateName;
}

}
