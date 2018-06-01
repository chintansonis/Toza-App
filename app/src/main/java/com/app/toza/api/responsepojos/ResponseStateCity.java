package com.app.toza.api.responsepojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseStateCity {

@SerializedName("Status")
@Expose
private Integer status;
@SerializedName("Message")
@Expose
private String message;
@SerializedName("ResponseData")
@Expose
private List<ResponseData> responseData = null;

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public List<ResponseData> getResponseData() {
return responseData;
}

public void setResponseData(List<ResponseData> responseData) {
this.responseData = responseData;
}

}
