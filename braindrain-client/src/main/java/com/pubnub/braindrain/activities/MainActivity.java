package com.pubnub.braindrain.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.pubnub.braindrain.R;
import com.pubnub.braindrain.app.service.CollaborationService;
import com.pubnub.braindrain.view.CollaborationCanvasView;

public class MainActivity extends AppCompatActivity {


    private CollaborationCanvasView mCollaborationCanvasView;
    private int mode = 0; //nothing 1, drawing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollaborationCanvasView.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    mode = 1;
                }


                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        super.onStart();

    }
}
