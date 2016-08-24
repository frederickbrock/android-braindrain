package com.pubnub.braindrain.app.model;


import java.util.List;

/**
 * {   //phase 2
 *     packetType: 'point'
 *     packetContents: {
 *         operations:[ point{ x:5,y:6} ]
 *     }
 *
 *     //phase 1
 *
 * }
 */

public class Packet {

    //Identifies the layer, this could be expanded to allow multiple layers
    private String collaboratorID;
    //type of packet is just a type descriminator
    private String packetType;
    //when was this packet sent
    private long publishedOn;
    //TODO, this will be replaced with operations on post 2
    private List<Point> points; //Packets are received and added the layer for this collaborator.

    public String getCollaboratorID() {
        return collaboratorID;
    }

    public void setCollaboratorID(String collaboratorID) {
        this.collaboratorID = collaboratorID;
    }


}
