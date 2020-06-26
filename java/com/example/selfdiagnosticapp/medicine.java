package com.example.selfdiagnosticapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class medicine extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine);

        HashMap<String, List<String>> medi = new HashMap<>();
        medi.put("Pneumonia", Arrays.asList(new String[] {"Azithromycin","Ciprofloxacin" }));
        medi.put("Diabetes", Arrays.asList(new String[] {"glimepiride","Insulin" }));
        medi.put("Bronchitis", Arrays.asList(new String[] {"Mucinex","Azithromycin" }));
        medi.put("Hepatitis B", Arrays.asList(new String[] {"entecavir ","tenofovir" }));
        medi.put("Influenza", Arrays.asList(new String[] {"Relenza","Tamiflu" }));
        medi.put("Anaemia", Arrays.asList(new String[] {"Vitamin C","Vitamin B-12 " }));
        medi.put("Asthma", Arrays.asList(new String[] {"Fluticasone","Budesonide" }));
        medi.put("Epilepsy", Arrays.asList(new String[] {"sodium valproate.","lamotrigine." }));
        medi.put("Gout", Arrays.asList(new String[] {"Allopurinol","Colchicine" }));



        Intent i=getIntent();
        String disease= i.getStringExtra("disease");
        boolean isKeyPresent = medi.containsKey(disease);
        if(isKeyPresent==true) {
            TextView textView1 = (TextView) findViewById(R.id.textView1);
            textView1.setText("1. " + medi.get(disease).get(0));
            TextView textView2 = (TextView) findViewById(R.id.textView2);
            textView2.setText("2. " + medi.get(disease).get(1));
        }
        else{
            TextView textView1 = (TextView) findViewById(R.id.textView13);
            textView1.setText(" ");
            TextView textView2 = (TextView) findViewById(R.id.textView14);
            textView1.setText("Sorry! We do not have data regarding this disease.");

        }






    }
}
