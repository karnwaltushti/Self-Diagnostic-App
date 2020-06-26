package com.example.selfdiagnosticapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class details extends Activity {

    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;
    int weight;
    int height;
    boolean smoke;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Intent data = getIntent();
        final String username=data.getStringExtra("username");
        if(data==null)
            return;
        b1 = (Button)findViewById(R.id.button);
        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        final Intent symptoms=new Intent(details.this,symptoms.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = Integer.parseInt(ed1.getText().toString());
                height = Integer.parseInt(ed2.getText().toString());
                symptoms.putExtra("username",username);
                startActivity(symptoms);
            }


        });


    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.yes:
                if (checked)
                    smoke =true;
                break;
            case R.id.no:
                if (checked)
                    smoke =false;
                break;
        }
    }

}

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText word=(EditText) findViewById(R.id.word);
        Button btnSubmit= (Button) findViewById(R.id.button);
        final TextView tvResults=(TextView) findViewById(R.id.textView);

        btnSubmit.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                String idNumber= word.getText().toString().trim();
                String dob=idNumber.substring(0,6);
                int gender=Integer.parseInt(Character.toString(idNumber.charAt(6)));
                String sGender;
                if(gender<5){
                    sGender="Female";
                }
                else
                    sGender="Male";
                int nationality=Integer.parseInt(Character.toString(idNumber.charAt(10)));
                String snationality;
                if(nationality==0) {
                    snationality = "SA Citizen";
                }
                else
                    snationality="Permanent Resident";
                System.out.println("222");
                String text=dob+"\n"+getString(gender)+sGender+getString(nationality)+snationality;
                tvResults.setText(text);
                                         }
                                     }
        );
        /*etID=(TextView) findViewById(R.id.word);
        //etID=(EditText) findViewById(R.id.word);
        btnSubmit= (Button) findViewById(R.id.button);
        tvResults=(TextView) findViewById(R.id.textView);

        btnSubmit.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                String idNumber= etID.getText().toString().trim();
                String dob=idNumber.substring(0,6);
                int gender=Integer.parseInt(Character.toString(idNumber.charAt(6)));
                String sGender;
                if(gender<5){
                    sGender="Female";
                }
                else
                    sGender="Male";
                int nationality=Integer.parseInt(Character.toString(idNumber.charAt(10)));
                String snationality;
                if(nationality==0) {
                    snationality = "SA Citizen";
                }
                else
                    snationality="Permanent Resident";
                String text=getString(R.string.dob)+dob+"\n"+getString(R.string.gender)+sGender+getString(R.string.nationality)+snationality;
                tvResults.setText(text);
            }
        }
        );*/
//  }
//}
