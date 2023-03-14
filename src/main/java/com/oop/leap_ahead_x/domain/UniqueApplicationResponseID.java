package com.oop.leap_ahead_x.domain;

import java.io.Serializable;

public class UniqueApplicationResponseID implements Serializable {
    private Application applicationUuid;
    private InputComponent componentUuid;

    private SubformCanvas canvasUuid;

    public  UniqueApplicationResponseID(){

    }
    public UniqueApplicationResponseID (Application applicationUuid,InputComponent componentUuid,SubformCanvas canvasUuid) {
        this.applicationUuid = applicationUuid;
        this.componentUuid = componentUuid;
        this.canvasUuid =  canvasUuid;
    }
}