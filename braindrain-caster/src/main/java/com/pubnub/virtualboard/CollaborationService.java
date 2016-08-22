package com.pubnub.virtualboard;




import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;

import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.virtualboard.om.Packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;



public class CollaborationService {

    public final static Logger logger = LoggerFactory.getLogger(CollaborationService.class);

    private PubNub pubNub;
    private FirebaseDatabase firebaseDatabase;
    private static CollaborationService instance;


    private CollaborationService(PubNub pubnub, FirebaseDatabase firebaseDatabase){
        this.pubNub = pubnub;
        this.firebaseDatabase = firebaseDatabase;
    }

    public void subscribeToBoard(String name){
        DatabaseReference boards = firebaseDatabase.getReference();
        boards.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    Iterable<DataSnapshot> snapShot = dataSnapshot.getChildren();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public static CollaborationService getInstance(){
        if(null == instance){
            FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();

            String subscribeKey = remoteConfig.getString("pubnub_subscribe_key");
            String publishKey    = remoteConfig.getString("pubnub_publish_key");

            PNConfiguration configuration = new PNConfiguration();
            configuration.setSubscribeKey(subscribeKey);
            configuration.setPublishKey(publishKey);
            PubNub pubnub = new PubNub(configuration);

            pubnub.addListener(new SubscribeCallback() {

                private ObjectMapper objectMapper = new ObjectMapper();

                @Override
                public void status(PubNub pubnub, PNStatus status) {

                }

                @Override
                public void message(PubNub pubnub, PNMessageResult message) {

                    JsonNode messageNode = message.getMessage();
                    try {
                        Packet packet = objectMapper.readValue(messageNode.asText(),Packet.class);

                    } catch (IOException e) {
                       logger.error("pubnubListener", "exception while attempting to read packet");
                    }


                }

                @Override
                public void presence(PubNub pubnub, PNPresenceEventResult presence) {

                }
            });

            FirebaseDatabase fireBaseDatabase = FirebaseDatabase.getInstance();
            instance = new CollaborationService(pubnub,fireBaseDatabase);
        }

        return instance;
    }
}
