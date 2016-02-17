package com.hjc.letsgo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

/**
 * MainActivity
 * email:hanjianchun_happy@163.com
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private VideoView videoView;

    private Button buttonPlay;

    private EditText editResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    /**
     * 1.初始化控件
     */
    private void initViews() {
        videoView = (VideoView) findViewById(R.id.videoView);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        editResp = (EditText) findViewById(R.id.editResp);

        buttonPlay.setOnClickListener(this);

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("MainActivity", videoView.isPlaying() + "");
                //rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp
                if(videoView.isPlaying()){
                    videoView.pause();
                }else{
                    videoView.start();
                    videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Log.v("TAG","complete");
                        }
                    });

                }
                return false;
            }
        });

    }

    /**
     * 获取EditText字符串
     */
    public String getEditRespString(){
        return editResp.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonPlay:
                Uri uri = Uri.parse(getEditRespString());
//        videoView.setMediaController(new MediaController(this));
                videoView.setVideoURI(uri);
                videoView.requestFocus();

                Log.v("TAG", videoView.getDuration()+"");

                break;
        }

    }
}
