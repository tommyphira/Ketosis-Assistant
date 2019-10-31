import java.util.*;
import java.text.DecimalFormat;
import java.math.RoundingMode;


public class KetoAssistant{
    /*
    Metrics/Units:
        Weight: kg (kilograms)
        Height: cm (centimeters)
        Age: years (not months)

    Specifications:
        Activity Level:
            Call method "defineActivityLevels" for clarification
            Sedentary = "S"
            Lightly Active = "LA"
            Moderately Active = "MA"
            Very Active = "VA"
     */
    private String gender;
    private final String MALE = "M";
    private final String FEMALE = "F";

    private int age;
    private float weight;
    private float height;
    private float activityLevel;
    private double BasalMetabolicRate;

    private final int calorieCutValue = 600;
    private final double proteinMultiplier = 1.4;
    private final double fatMultiplier = 0.4;
    private static final int ketoCarbohydrates = 20;


    public KetoAssistant(){
        Scanner user = new Scanner(System.in);
        System.out.println("Enter your gender (M or F): ");
        this.gender = user.nextLine();
        System.out.println("Enter your age (years): ");
        this.age = user.nextInt();
        System.out.println("Enter your weight (kg): ");
        this.weight = user.nextInt();
        System.out.println("Enter your height (cm): ");
        this.height = user.nextInt();
        activityLevel = defineActivityLevels();
        this.activityLevel = activityLevel;
        this.BasalMetabolicRate = calculateBasalMetabolicRate(gender);

    }
    //BEGIN SECTION FOR TDEE ============================================
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

        double BMR = ((this.height * 6.25) + (weight * 9.99) - (this.age * 4.92) + genderOffset);
        return BMR;
    }

    //Method to calculate calorie intake using BMR
    private double calculateCalorieLossIntake(){
        return ((this.BasalMetabolicRate * this.activityLevel) - calorieCutValue);
    }

    //Method to calculate the protein intake
    private double calculateProteinIntake(){
        return (this.weight * proteinMultiplier);
    }

    private double calculateFatIntake() { return (this.weight * fatMultiplier); }

    //Method to help user select activity levels by defining which each one is.
    private float defineActivityLevels(){
        String[] choiceArray = new String[]{"Sedentary", "Lightly Active", "Moderately Active", "Very Active"} ;
        HashMap<String,String> definitions = new HashMap<String,String>();
        definitions.put("Sedentary", "DEFINITION: Not much activity with little to no exercise such as a desk job");
        definitions.put("Lightly Active", "DEFINITION: Daytime walking with less than 20 minutes of exercise per day.");
        definitions.put("Moderately Active", "DEFINITION: A lightly active day job with physical labor or routinely exercise");
        definitions.put("Very Active", "DEFINITION: Labor intensive day job and/or intense amounts of exercise each day");

        int stayInLoop = 1;
        int choice = 0;

        while(stayInLoop == 1) {
            System.out.println(" ");
            System.out.println("Activity Levels:");
            System.out.println("===================");
            System.out.println("1) Sedentary");
            System.out.println("2) Lightly Active");
            System.out.println("3) Moderately Active");
            System.out.println("4) Very Active");
            System.out.println("5) [IMPORTANT] Select Level to use");
            System.out.println("6) Exit");
            System.out.println("===================");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose an option for a more detailed description");
            if(scanner.hasNextInt())
            {
                choice = scanner.nextInt();
            }
            else{
                choice = 6;
            }

            //Sedentary
            if(choice == 1)
            {
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("You have chosen " + choiceArray[choice - 1]);
                System.out.println("===============================");
                System.out.println(definitions.get(choiceArray[choice - 1]));
                System.out.println("===============================");
                System.out.println(" ");
            }
            //Lightly Active
            else if(choice == 2)
            {
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("You have chosen " + choiceArray[choice - 1]);
                System.out.println("===============================");
                System.out.println(definitions.get(choiceArray[choice - 1]));
                System.out.println("===============================");
                System.out.println(" ");
            }
            //Moderately Active
            else if(choice == 3)
            {
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("You have chosen " + choiceArray[choice - 1]);
                System.out.println("===============================");
                System.out.println(definitions.get(choiceArray[choice - 1]));
                System.out.println("===============================");
                System.out.println(" ");
            }
            //Very Active
            else if(choice == 4)
            {
                System.out.println(" ");
                System.out.println(" ");
                System.out.println("You have chosen " + choiceArray[choice - 1]);
                System.out.println("===============================");
                System.out.println(definitions.get(choiceArray[choice - 1]));
                System.out.println("===============================");
                System.out.println(" ");
            }
            else if(choice == 5)
            {
                //sedentaryMultiplier = 1.2F;
                //lightlyActiveMultiplier = 1.375F;
                //moderatelyActiveMultiplier = 1.55F;
                //veryActiveMultiplier = 1.725F;
                int activityChosen;
                System.out.println("1) Sedentary");
                System.out.println("2) Lightly Active");
                System.out.println("3) Moderately Active");
                System.out.println("4) Very Active");

                Scanner scannerActivity = new Scanner(System.in);
                System.out.println("Please choose the activity level to use");
                activityChosen = scanner.nextInt();
                if(activityChosen == 1)
                {
                    activityLevel = 1.2F;
                    return activityLevel;
                }
                else if(activityChosen == 2)
                {
                    activityLevel = 1.375F;
                    return activityLevel;
                }
                else if(activityChosen == 3)
                {
                    activityLevel = 1.55F;
                    return activityLevel;
                }
                else if(activityChosen == 4)
                {
                    activityLevel = 1.725F;
                    return activityLevel;
                }
            }
            else if(choice == 6)
            {
                stayInLoop = 0;
                return 0;
            }
            else
            {
                System.out.println("Please enter a valid entry");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
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
    //END SECTION FOR TDEE ============================================

    public static void main(String[] args){
        KetoAssistant Tommy = new KetoAssistant();
        System.out.println("To stay in Keto and lose weight. Per day you must consume:");
        System.out.println("Calories: " + truncateValue(Tommy.calculateCalorieLossIntake()) + " calories.");
        System.out.println("Protein: " + truncateValue(Tommy.calculateProteinIntake()) + "g");
        System.out.println("Carbohydrates: " + ketoCarbohydrates + "g");
        System.out.println("Fats: " + truncateValue(Tommy.calculateFatIntake()) + "g");

    }
}
