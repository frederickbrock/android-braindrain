package com.pubnub.braindrain.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.pubnub.braindrain.R;

import static com.pubnub.braindrain.app.Constants.LAUNCH_AUTH_ACTIVITY;

import com.pubnub.braindrain.app.CollaborationManager;
import com.pubnub.braindrain.view.CollaborationCanvasView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.canvasView)
    private CollaborationCanvasView mCollaborationCanvasView;

    //Phase 2, render the board.
    private View.OnGenericMotionListener motionListener = new View.OnGenericMotionListener() {
        @Override
        public boolean onGenericMotion(View view, MotionEvent motionEvent) {
            return false;
        }
    };

    private int mode = 0; //nothing 1, drawing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCollaborationCanvasView.setOnGenericMotionListener(motionListener);

    }



    @Override
    protected void onStart() {
        super.onStart();
        CollaborationManager collaborationService = CollaborationManager.getInstance();
        if(!collaborationService.isUserAuthenticated()){
           Intent loginIntent = new Intent(this,SignInActivity.class);
           startActivityForResult(loginIntent, LAUNCH_AUTH_ACTIVITY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CollaborationManager collaborationService = CollaborationManager.getInstance();


        switch (requestCode)
        {
            case LAUNCH_AUTH_ACTIVITY:
                collaborationService.getUser();



        }
    }

}
