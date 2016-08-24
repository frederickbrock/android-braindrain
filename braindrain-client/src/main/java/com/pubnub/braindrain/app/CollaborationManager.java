package com.pubnub.braindrain.app;


import android.support.annotation.NonNull;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import com.pubnub.braindrain.app.model.Packet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CollaborationManager {

    public final static Logger logger = LoggerFactory.getLogger(CollaborationManager.class);
    public static String BOARDS = "BOARDS";
    public static String TAG = "collabservice";


    //The lion and the lamb
    private PubNub pubNub;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser mCurrentUser;
    //Firebase Auth for authentication
    private FirebaseAuth fileBaseAuth;

    private static CollaborationManager instance;

    private CollaborationManager(){
    }



    public static CollaborationManager getInstance(){
        if(null == instance) {
            instance = new CollaborationManager();
            instance.initAuthListener();
        }

        return instance;
    }

    private void initAuthListener(){
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser u = firebaseAuth.getCurrentUser();
                    if((u != null) && (mCurrentUser == null)){
                        mCurrentUser = u;
                        initPubNub(); //After we have a user initPubNub
                        initFirebase();
                    }
                }
            });
    }

    private void initPubNub(){
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        String subscribeKey  = remoteConfig.getString("pubnub_subscribe_key");
        String publishKey    = remoteConfig.getString("pubnub_publish_key");

        PNConfiguration configuration = new PNConfiguration();
        configuration.setSubscribeKey(subscribeKey);
        configuration.setPublishKey(publishKey);
        configuration.setUuid(mCurrentUser.getUid());
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
                    Packet p = objectMapper.readValue(messageNode.asText(),Packet.class);


                } catch (IOException e) {
                    Log.d(TAG, "exception while attempting to read packet");
                }


            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }
        });
    }

    public void initFirebase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public boolean isUserAuthenticated() {
        return mCurrentUser != null;
    }

    public FirebaseUser getUser(){
        return mCurrentUser;
    }


}
