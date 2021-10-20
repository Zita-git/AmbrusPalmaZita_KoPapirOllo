package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private ImageView GV,TV;
    private Button buttonKO, buttonPAPIR, buttonOLLO;
    private TextView textViewEredmeny,textViewDontetlen;
    private int gepNyert, teNyert, dontetlen;
    private Random random;
    private int gepValasz, teValasz;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonKO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teValasz=1;
                gepValasz();
                TV.setImageResource(R.drawable.rock);

                valakiNyert();
            }
        });

        buttonPAPIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teValasz=2;
                gepValasz();
                TV.setImageResource(R.drawable.paper);

                valakiNyert();
            }
        });
        buttonOLLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teValasz=3;
                gepValasz();
                TV.setImageResource(R.drawable.scissors);

                valakiNyert();
            }
        });
    }

    public void gepValasz(){
        random=new Random();
        gepValasz=random.nextInt(3)+1;

        if (gepValasz==1){
            GV.setImageResource(R.drawable.rock);
        }
        else if (gepValasz == 2) {
            GV.setImageResource(R.drawable.paper);
        }else{
            GV.setImageResource(R.drawable.scissors);
        }
    }

    public void valakiNyert(){

        if(teValasz==gepValasz){
            dontetlen++;
            textViewEredmeny.setText("Eredmény: Ember: "+teNyert+" Computer: "+gepNyert);
            textViewDontetlen.setText("Döntetlenek száma: "+dontetlen);
            Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_SHORT).show();


        }else if(
                (teValasz==1 && gepValasz==2) ||
                (teValasz==2 && gepValasz==3) ||
                (teValasz==3 && gepValasz==1)
        ){
            gepNyert++;
            textViewEredmeny.setText("Eredmény: Ember: "+teNyert+" Computer: "+gepNyert);
            Toast.makeText(MainActivity.this, "A computer nyert", Toast.LENGTH_SHORT).show();

        }else if(
                (teValasz==2 && gepValasz==1) ||
                (teValasz==3 && gepValasz==2) ||
                (teValasz==1 && gepValasz==3)
        ){
            teNyert++;
            textViewEredmeny.setText("Eredmény: Ember: "+teNyert+" Computer: "+gepNyert);
            Toast.makeText(MainActivity.this, "Te nyertél", Toast.LENGTH_SHORT).show();
        }
        if (gepNyert==3||teNyert==3) {
            nyertes();
        }
    }

    public void nyertes(){


        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setCancelable(false);

        if (gepNyert==3){
            alertBuilder.setTitle("Vereség");
        }else if(teNyert==3){
            alertBuilder.setTitle("Győzelem");
        }

        alertBuilder.setMessage("Szeretnél új játékot játszani?");

        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ujMenet();
            }
        });

        alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertBuilder.create().show();
    }

    public void ujMenet(){
        teNyert=0;
        gepNyert=0;

        dontetlen=0;

        teValasz=1;
        gepValasz=1;

        TV.setImageResource(R.drawable.rock);
        GV.setImageResource(R.drawable.rock);

        textViewEredmeny.setText("Eredmény: Ember: "+teNyert+" Computer: "+gepNyert);
        textViewDontetlen.setText("Döntetlenek száma: "+dontetlen);

    }

    public void init(){
        teValasz=1;
        gepValasz=1;
        dontetlen=0;

        textViewEredmeny=findViewById(R.id.textViewEredmeny);
        textViewDontetlen=findViewById(R.id.textViewDontetlen);

        TV=findViewById(R.id.imagineViewTV);
        GV=findViewById(R.id.imagineViewGV);

        buttonKO=findViewById(R.id.buttonKO);
        buttonPAPIR=findViewById(R.id.buttonPAPIR);
        buttonOLLO=findViewById(R.id.buttonOLLO);
    }
}