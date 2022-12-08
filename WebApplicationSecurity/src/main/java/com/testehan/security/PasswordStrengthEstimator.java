package com.testehan.security;

import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.*;
import me.gosimple.nbvcxz.scoring.Result;
import me.gosimple.nbvcxz.scoring.TimeEstimate;

import java.util.List;

public class PasswordStrengthEstimator {

    public static void main(String[] args) {

        final String myPassword = "danPop1994";

        Result estimationResult = estimatePasswordStrength(myPassword);

        printEstimationResults(estimationResult);

        System.out.println("=======================================================");
        System.out.println("=======================================================");
        // Other goodies from the library
        // Generate a passphrase from the standard (eff_large) dictionary with 5 words with a "-" between the words
        String pass1 = Generator.generatePassphrase("-", 5);
        System.out.println("Generated passphrase is " + pass1);

        // Generate a random password with alphanumeric characters that is 15 characters long
        String pass = Generator.generateRandomPassword(Generator.CharacterTypes.ALPHANUMERIC, 20);
        System.out.println("Generated password is " + pass);
    }

    private static void printEstimationResults(Result estimationResult) {
        // Get formatted values for time to crack based on the values we input in our configuration (we used default values in this example)
        String timeToCrackOff = TimeEstimate.getTimeToCrackFormatted(estimationResult, "OFFLINE_BCRYPT_12");
        String timeToCrackOn = TimeEstimate.getTimeToCrackFormatted(estimationResult, "ONLINE_THROTTLED");

        if (!estimationResult.isMinimumEntropyMet()){
            System.out.println("Password did not meet strength estimation requirements");
            System.out.println("Time to crack - online: " + timeToCrackOn);
            System.out.println("Time to crack - offline: " + timeToCrackOff);

            // Get the feedback for the result that contains hints for the user on how to improve their password
            // It is localized based on locale set in configuration
            Feedback feedback = estimationResult.getFeedback();
            if(feedback != null)
            {
                if (feedback.getWarning() != null)
                    System.out.println("Warning: " + feedback.getWarning());
                for (String suggestion : feedback.getSuggestion())
                {
                    System.out.println("Suggestion: "+ suggestion);
                }
            }

        } else {
            System.out.println("Password is meeting estimation requirements");
            System.out.println("Time to crack - online: " + timeToCrackOn);
            System.out.println("Time to crack - offline: " + timeToCrackOff);
        }
    }

    private static Result estimatePasswordStrength(String myPassword) {
        // Create a map of excluded words on a per-user basis. Baically we don't want our user to use the same password as
        // his username, or email, or contain one of his names..hence we mark these words as excluded
        List<Dictionary> dictionaryList = ConfigurationBuilder.getDefaultDictionaries();
        dictionaryList.add(new DictionaryBuilder()
                .setDictionaryName("exclude")
                .setExclusion(true)
                .addWord("dan", 0)
                .addWord("popescu", 0)
                .addWord("dan.p@yahoo.com", 0)
                .createDictionary());



        // Create our configuration object and set our custom minimum entropy, and custom dictionary list
        Configuration configuration = new ConfigurationBuilder()
                .setMinimumEntropy(40d)
                .createConfiguration();

        // Create our Nbvcxz object with the configuration we built
        Nbvcxz nbvcxz = new Nbvcxz(configuration);

        // Estimate password
        Result result = nbvcxz.estimate(myPassword);

        return result;
    }
}
