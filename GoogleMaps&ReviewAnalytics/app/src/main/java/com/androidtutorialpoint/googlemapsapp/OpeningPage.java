package com.androidtutorialpoint.googlemapsapp;

/**
 * Created by srihariJoshi on 1/12/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidtutorialpoint.googlemapsapp.multidex.myapplication.R;


public class OpeningPage extends Activity {

    Button button;
    Button button1;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openpage);

        addListenerOnButton();



    }

    public void addListenerOnButton() {

        image = (ImageView) findViewById(R.id.imageView);
       // image.setImageResource(R.drawable.android);
       // button1 = (Button) findViewById(R.id.btnChangeImage);
        //image = (ImageView) findViewById(R.id.imageView);
     //  image.setImageResource(R.drawable.android3d);
        button = (Button) findViewById(R.id.btnChangeImage);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(OpeningPage.this,form.class);
                startActivity(intent);

            }

        });




        }



    public void displayPage(){

    }
}