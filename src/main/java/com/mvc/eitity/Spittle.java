package com.mvc.eitity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

public class Spittle {
    @Null
    private final Long id;
    @Null
    private final String message;
    private final Date time;
    @NotNull
    @Size(min= 3,max = 20)
    private Double latitude;
    private Double longitude;
    @NotNull(message = "内容不能为kong")
    @Size(min = 2,max = 10,message = "长度不能小于2")
    private String context;
    public Spittle(){
        this(null, null, null, null);
    }
    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double longitude, Double latitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
