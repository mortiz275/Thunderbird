/******************************************************************************
 * Copyright (C) 2019 Eric Pogue.
 * 
 * This file and the ThunderbirdLite application is licensed under the 
 * BSD-3-Clause.
 * 
 * You may use any part of the file as long as you give credit in your 
 * source code.
 * 
 * This application utilizes the HttpRequest.java library developed by 
 * Eric Pogue
 * 
 * Version: 1.3
 *****************************************************************************/


// Todo: Rename the following class to Thunderbird.
// Hint: This will also require you to rename the Java file.
// MO: Renamed the files appropiately.
public class Thunderbird {
    public static void main(String[] args) {

        // Todo: Update the following line so that it reflects the name change to Thunderbird.
        // MO: Implemented.
        System.out.println("Thunderbird Starting...");

        ThunderbirdFrame myThunderbirdFrame = new ThunderbirdFrame();
        myThunderbirdFrame.setVisible(true);
    }
}