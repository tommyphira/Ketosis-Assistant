package com.example.ketoassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Results extends AppCompatActivity {

    public double BasalMetabolicRate;
    public double caloricIntake;
    public double proteinIntake;
    public double carbIntake;
    public double fatIntake;

    public double height = MainActivity.height;
    public double age = MainActivity.age;
    public double weight = (MainActivity.weight * 0.453592); //Turns lbs to kg
    public double activityLevel = MainActivity.activityLevel;

    TextView calorieResult;
    TextView proteinResult;
    TextView carbResult;
    TextView fatResult;
    TextView bmrResult;

    private final int calorieCutValue = 600;
    private final double proteinMultiplier = 1.4;
    private final double fatMultiplier = 0.6;
    private static final int ketoCarbohydrates = 20;
    private final String MALE = "M";
    private final String FEMALE = "F";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        calorieResult = (TextView) findViewById(R.id.calorieResult);
        proteinResult = (TextView) findViewById(R.id.proteinResult);
        carbResult = (TextView) findViewById(R.id.carbResult);
        fatResult = (TextView) findViewById(R.id.fatResult);
        bmrResult = (TextView) findViewById(R.id.bmrResult);

        BasalMetabolicRate = calculateBasalMetabolicRate("M");
        caloricIntake = calculateCalorieLossIntake();
        proteinIntake = calculateProteinIntake();
        fatIntake = calculateFatIntake();
        carbIntake = ketoCarbohydrates;

        String trunCal = truncateValue(caloricIntake) + " Calories";
        String trunPro = truncateValue(proteinIntake) + "g";
        String trunCarb = truncateValue(carbIntake) + "g";
        String trunFat = truncateValue(fatIntake) + "g";
        String trunBMR = truncateValue(BasalMetabolicRate);

        calorieResult.setText(trunCal);
        proteinResult.setText(trunPro);
        carbResult.setText(trunCarb);
        fatResult.setText(trunFat);
        bmrResult.setText(trunBMR);
    }

    //Method to calculate a user's Basal Metabolic Rate
    private double calculateBasalMetabolicRate(String gender){
        /* Basal Metabolic Rate (BMR)
        Female: [(HEIGHT * 6.25) + (WEIGHT * 9.99) - (AGE * 4.92) + genderOffset]
        Male:   [(HEIGHT * 6.25) + (WEIGHT * 9.99) - (AGE * 4.92) + genderOffset]
         */
        int genderOffset = 0;
        if(gender == MALE)
        {
            genderOffset = 5;
        }
        else if(gender == FEMALE)
        {
            genderOffset = -161;
        }
        return ((height * 6.25) + (weight * 9.99) - (age * 4.92) + genderOffset);
    }


    //Method to calculate calorie intake using BMR
    private double calculateCalorieLossIntake(){
        return ((BasalMetabolicRate * activityLevel) - calorieCutValue);
    }

    //Method to calculate the protein intake
    private double calculateProteinIntake(){
        return (weight * proteinMultiplier);
    }

    //Helper methods to truncate floats or doubles to two decimal places using overloading
    private static String truncateValue(float value){
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(value);
    }
    private static String truncateValue(double value){
        DecimalFormat df = new DecimalFormat("##.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(value);
    }

    private double calculateFatIntake() { return ((weight * 2.20462) * fatMultiplier); }
}
