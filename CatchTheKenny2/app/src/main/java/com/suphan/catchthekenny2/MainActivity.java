package com.suphan.catchthekenny2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;

    ImageView[] allImage;

    int number;

    Runnable runnable;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.textViewTimer);
        scoreText=findViewById(R.id.textViewScore);

        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        allImage= new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        number=0;


        //Runnableın içindeki run metodu içinde işlemini yap.Handler ile delayı en sonda belirle.En son handler.post ile runnable
        //çalıştır.


        handler=new Handler();


        runnable =new Runnable() {
            @Override
            public void run() {


                hideAllImages();
                Random random = new Random();
                int randomNumber=random.nextInt(9);

                allImage[randomNumber].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,500);
            }
        };

        handler.post(runnable);





        new CountDownTimer(10999,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

                timeText.setText("Time:DONE!");

                handler.removeCallbacks(runnable);

                hideAllImages();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("RESTART");

                alert.setMessage("Try AGAİN ?");

                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"GAME OVER !",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();


            }
        }.start();


    }

    public void hideAllImages(){

        for(ImageView image: allImage){

            image.setVisibility(View.INVISIBLE);

        }

    }

    public void increaseScore(View view){

        number++;

        scoreText.setText("Score: "+number);


    }

    public void gameControl(){




    }


}