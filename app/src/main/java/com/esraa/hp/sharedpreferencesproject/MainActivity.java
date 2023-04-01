package com.esraa.hp.sharedpreferencesproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit1,edit2;
 Button save,load,navigate;
 TextView name,email;
 Switch changeColour;
 LinearLayout linearLayout;
    boolean isChecked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String FILE="com.esraa.hp.sharedpreferencesproject.my_file";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        save=findViewById(R.id.btn1);
        load=findViewById(R.id.btn2);
        navigate=findViewById(R.id.btn3);
        name=findViewById(R.id.txt1);
        email=findViewById(R.id.txt2);
        changeColour=findViewById(R.id.changecolour);
        linearLayout=findViewById(R.id.layout);

        //retrieve last color from user settings
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        isChecked=sharedPreferences.getBoolean("colour",false);
        changeColour.setChecked(isChecked);
        linearLayout.setBackgroundColor(isChecked? getResources().getColor(R.color.blue):getResources().getColor(R.color.purple));



        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences(FILE,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name",edit1.getText().toString());
                editor.putString("email",edit2.getText().toString());
                editor.apply();
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences(FILE,Context.MODE_PRIVATE);
                String myName=sharedPreferences.getString("name","Not available");
                String myEmail=sharedPreferences.getString("email","Not available");
                name.setText(myName);
                email.setText(myEmail);

            }
        });

        changeColour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("colour",b);
                editor.apply();
                linearLayout.setBackgroundColor(b? getResources().getColor(R.color.blue):getResources().getColor(R.color.purple));
            }
        });
    }
}
