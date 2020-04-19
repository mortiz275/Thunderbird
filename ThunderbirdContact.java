/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file is licensed under the BSD-3-Clause
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 *****************************************************************************/

class ThunderbirdContact extends HttpRequest implements Runnable {
    private String firstName;
    public String getFirstName() { return Integer.toString(seatLocation) + " " + firstName; }

    private String lastName;
    public String getLastName() {return lastName; }
    
    private int seatLocation; 
    public int getSeat() { return (seatLocation-1); }

    private String preferredName;
    public String getPreferredName(){return preferredName;}

    private String email;
    public String getEmail() {return email;}

    ThunderbirdContact(String urlIn) {
        super(urlIn);

        firstName = "";
        preferredName= "";
        lastName = "";
        email="";
        seatLocation = 0;

        // Todo: Add additional fields. 
        //MO: Added preferred name and email.
    }
    //Verifies if email is valid.
    static boolean emailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public Boolean Load() {
        Boolean returnValue = false;
        System.out.println("Loading: " + requestURL);
        if (super.readURL()) {
            Parse(); 
            returnValue = true;
        }

        return returnValue;
    }

    public void Parse() {
        for (String s : urlContent) {
            String[] subString = s.split("\"");

            // Todo: Parse for additional fields. 
            //MO: Now parses email and preferred name.
            if (subString.length > 3) {
                if (subString[1].equals("firstName")) {
                    firstName = subString[3];
                }
                if (subString[1].equals("lastName")) {
                    lastName = subString[3];
                }
                if(subString[1].equals("email")){
                    email= subString[3]; 
                }
                if(subString[1].equals("preferredName")){
                    preferredName= subString[3];
                }
                if (subString[1].equals("seatLocation")) {
                    try {
                        seatLocation = 0; 
                        if (!subString[3].equals("")) {
                            seatLocation = Integer.parseInt(subString[3]);
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Exception: " + e);
                    }
                }
            }
        }    
    }

    public void Validate() {
        if (urlContent.size() < 1) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: No content loaded\n");
            return; // Returning from the middle of a method is controversial.
        }

        // Todo: Add author's name and email address to failed messages. 
        // MO: Added email and preferred name validation.
        if (firstName.length() == 0) {
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: First Name (\"firstName\") required but not found\n\n");
            System.out.println(this);
        } else if (lastName.length() == 0) {   
            System.out.println("Validating: " + requestURL);
            System.out.println("    **Failed**: Last Name (\"lastName\") required but not found\n\n");
            System.out.println(this);          
        } else {
            System.out.println("Validating: " + requestURL + "... Passed!");
        } 
        if (preferredName.length() < 2){
            System.out.println("Invalid Preferred name. Preferred name must be more than 2 characters.");
        }else if(preferredName.length()>16){
            System.out.println("Invalid Preferred name. Preferred name must be less than 16 characters.");
        }else {
            System.out.println("Valid Preferred Name!");
        }
        if (email.length() > 1) {
            if(emailValid(email)){
                System.out.println("Email is valid.\n");
            }else{
                System.out.println("Email is invalid.\n");
            } 
        } else {System.out.println("Invalid. Email not found.");}
    }

    public String toString() {
        // Todo: Add additional fields to returnString. 
        //MO: Completed. Now returns preferredName and Email.
        String returnString = "firstName: " + firstName + "\n";
        returnString = returnString + "lastName: " + lastName + "\n";
        returnString = returnString + "preferredName: "+ preferredName+"\n";
        returnString = returnString + "email: "+ email +"\n";
        returnString = returnString + "seatNumber: " + seatLocation + "\n";
        returnString = returnString + super.toString();

        return returnString;
    }

    public void run() {
        Load();
    }
}