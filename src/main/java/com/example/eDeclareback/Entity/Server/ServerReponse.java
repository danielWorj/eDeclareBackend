package com.example.eDeclareback.Entity.Server;

import lombok.Data;


@Data
public class ServerReponse {
    private String message ;
    private Boolean status ;

    public ServerReponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }
    public ServerReponse(Boolean status , String message) {
        this.message = message;
        this.status = status;
    }
}
