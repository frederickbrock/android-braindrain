package com.pubnub.braindrain.app.model;


/**
 * {
 *     packetType: 'point'
 *     packetContents: {
 *         operations:[ point{ x:5,y:6} ]
 *     }
 * }
 */

public class Packet {

    private String publishedByUuid;
    private String packageType;
    private long publishedAt;
    private String packetContents;



}
