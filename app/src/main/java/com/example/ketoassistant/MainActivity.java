package com.example.ketoassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String name;
    public static float height;
    public static float weight;
    public static int age;
    public static float activityLevel;;

    CheckBox activitySedentary;
    CheckBox activityLightlyActive;
    CheckBox activityModeratelyActive;
    CheckBox activityVeryActive;

    EditText userName;
    EditText userHeight;
    EditText userWeight;
    EditText userAge;

    Button theCalculateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keto_main);
        theCalculateButton = ((Button)findViewById(R.id.submitButton));
        userName = (EditText) findViewById(R.id.userName);
        userHeight = (EditText) findViewById(R.id.userHeight);
        userWeight = (EditText) findViewById(R.id.userWeight);
        userAge = (EditText) findViewById(R.id.userAge);
        activitySedentary = (CheckBox) findViewById(R.id.activitySedentary);
        activityLightlyActive = (CheckBox) findViewById(R.id.activityLightlyActive);
        activityModeratelyActive = (CheckBox) findViewById(R.id.activityModeratelyActive);
        activityVeryActive = (CheckBox) findViewById(R.id.activityVeryActive);
        theCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = userName.getText().toString();
                height = Integer.valueOf(userHeight.getText().toString());
                weight = Integer.valueOf(userWeight.getText().toString());
                age = Integer.valueOf(userAge.getText().toString());
                if(activitySedentary.isChecked())
                {
                    activityLevel = 1.2F;
                }
                else if(activityLightlyActive.isChecked())
                {
                    activityLevel = 1.375F;
                }
                else if(activityModeratelyActive.isChecked())
                {
                    activityLevel = 1.55F;
                }
                else if(activityVeryActive.isChecked())
                {
                    activityLevel = 1.725F;
                }
                Intent resultScreen = new Intent(getApplicationContext(), Results.class);
                startActivity(resultScreen);
            }
        });


    }
    private void showToast(String text){
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
    }

}
