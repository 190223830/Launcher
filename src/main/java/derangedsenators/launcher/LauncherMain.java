/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package derangedsenators.launcher;

import java.io.*;


/**
 *
 * @author Elliot Willis
 */
public class LauncherMain {

    /**
     * @param args the command line arguments
     */    
    public static Boolean updated;
    
    public static void main(String[] args) {     
        GuiFrame gui = new GuiFrame();
        gui.loadGui();
        updated = checkForUpdates();
        
    }
    
    public static void play() {
        if(updated) {
            //launch game
            System.exit(0);
        }
        else {
            
        }
    }
    
    public static void changelog() {
        //download changelog
    }
    
    public static Boolean checkForUpdates() {
        //generate md5 for all files
        //check md5 of github
        //update appropriate files
        
        BufferedReader versionReader, onlineVersionReader;
        String[] lineIn, onlineLineIn;
        String version = "LOCAL VERSION NOT FOUND", onlineVersion = "CANNOT FIND CURRENT VERSION";
        
        try {
            versionReader = new BufferedReader(new FileReader("../game/version.txt"));
            lineIn = versionReader.readLine().split(" ");
            versionReader.close();
            version = lineIn[1];
            
            onlineVersionReader = new BufferedReader(new FileReader("../online/version.txt"));
            onlineLineIn = onlineVersionReader.readLine().split(" ");
            onlineVersionReader.close();
            onlineVersion = lineIn[1];
	}
	catch(IOException e) {
		e.printStackTrace();
	}
        
        if (version.equals(onlineVersion) == false) {
            GuiFrame.progressBarText.setText("Update found");
            //download update
        }
        else {
            GuiFrame.progressBarText.setText(" ");
            return true;
        }
        return false;
    }

}
