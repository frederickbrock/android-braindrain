package com.pubnub.virtualboard.activity;

import android.app.Activity;
import android.os.Bundle;

import com.pubnub.virtualboard.CollaborationService;
import com.pubnub.virtualboard.R;
import com.pubnub.braindrain.view.CollaborationCanvasView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CanvasPresenterActivity extends Activity {


    private CollaborationService collaborationService;

    @Bind(R.id.canvasPresenter)
    CollaborationCanvasView collaborationCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_presenter);
        collaborationService = CollaborationService.getInstance();
        ButterKnife.bind(this);
    }


}
