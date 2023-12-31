package com.example.baybayingame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    ImageButton playButton;
    ImageButton historyButtonn;
    ImageButton quitButton;

    ImageButton settingsButton;

    MediaPlayer bgMusic;

    VideoView videoView;
    private boolean run;


    @Override
    protected void onPause() {
        super.onPause();
        bgMusic.pause();
        finish();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //background music
        bgMusic = MediaPlayer.create(MainActivity.this, R.raw.mainbgmusic);
        bgMusic.setLooping(true);
        bgMusic.start();
        bgMusic.setVolume(0.3f, 0.3f);

        playButton = (ImageButton) findViewById(R.id.play);
        historyButtonn = (ImageButton) findViewById(R.id.history);
        settingsButton = (ImageButton) findViewById(R.id.settings);
        quitButton = (ImageButton) findViewById(R.id.quit);

        videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bgmain);
        videoView.setVideoURI(uri);
        videoView.start();




        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });



        //to go to settings activity
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadHistoryActivity = new Intent(MainActivity.this , settings.class);
                startActivity(intentLoadHistoryActivity);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                shutdown();

            }

        });
        //to go to history activity
        historyButtonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPop();
            }
        });

        //to go to play activity
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadPlayActivity = new Intent(MainActivity.this , PilianActivity.class);
                startActivity(intentLoadPlayActivity);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                shutdown();
            }
        });
        // to quit application
        quitButton.setOnClickListener(new View.OnClickListener() {
           //to verify quit
            @Override
            public void onClick(View view) {
                String txtview = "Patuloy sa Pag Alis ?";
                popconfirm(txtview);

            }
        });



        }

    private void shutdown() {
        this.run = false;
    }

    private void openPop(){
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.panimula, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(alertCustomDialog);

        ImageButton go_back = alertCustomDialog.findViewById(R.id.back);

        AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.anim.scale_up;

        // show your alert dialog
        alertDialog.show();

        go_back.setOnClickListener(v -> {
            alertDialog.dismiss();

    });

    }

    private void popconfirm(String txt){
        View alertCustomDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.confirmation, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(alertCustomDialog);



        TextView txtview = alertCustomDialog.findViewById(R.id.head);
        ImageButton no = alertCustomDialog.findViewById(R.id.no);
        ImageButton yes = alertCustomDialog.findViewById(R.id.yes);


        txtview.setText(txt);

        AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.getWindow().getAttributes().windowAnimations = R.anim.scale_up;

        // show your alert dialog
        alertDialog.show();

        yes.setOnClickListener(v -> {
            System.exit(1);



        });

        no.setOnClickListener(v -> {
            alertDialog.dismiss();

        });
    }


}

   //LayoutInflater lf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

    //View viewpopup = lf.inflate(R.layout.history,null);



    //PopupWindow popupWindow = new PopupWindow(viewpopup, 900,900,true);

                //popupWindow.showAtLocation( view, Gravity.CENTER , 0, 250);