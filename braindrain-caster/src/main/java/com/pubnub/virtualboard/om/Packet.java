package com.pubnub.virtualboard.om;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;


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
