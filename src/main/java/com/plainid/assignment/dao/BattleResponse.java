package com.plainid.assignment.dao;

public class BattleResponse {
    StatusType status;
    String message;

    public BattleResponse(StatusType status,  String message){
        this.message = message;
        this.status = status;

    }
    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
