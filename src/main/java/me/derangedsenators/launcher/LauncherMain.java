package.me.derangedsentors.launcher

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

class LauncherMain {
	public static void main(String args[]) {
		//Check if game is installed, install if needed
		if(Installer.checkInstall() == false) {
			Installer.loadGUI();
		}
		else {
			Gui.loadGUI();
			updateCheck();
		}
	}
	
	//Retrieves the current Oprerating System of the User's computer
	public static String getOS() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	//Finds installed version of the game
	public static String getVersion() {
		
		BufferedReader versionReader;
		String[] line;
		String version = "Not Found";
		
		try {
			versionReader = new BufferedReader(new FileReader("../game/version.txt"));
			line = versionReader.readLine().split(" ");
			versionReader.close();
			version = line[1];
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return version;
	}
	
	//Checks current installed version against version on the github
	public static void updateCheck() {
		Gui.updateProgress.setString("Checking for updates...");
		boolean updateFound = false;
		
		BufferedReader onlineVersionReader;
		String[] line;
		String onlineVersion = "Not Found";
		
		try {
			onlineVersionReader = new BufferedReader(new FileReader("../online/version.txt"));
			line = onlineVersionReader.readLine().split(" ");
			onlineVersionReader.close();
			onlineVersion = line[1];
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		if (getVersion().equals(onlineVersion) == false) {
			Gui.updateProgress.setString("Update found");
			if(Gui.updateGUI()) {
				updateDownload();
			}
			else {
				exit();
			}
		}
	}
	
	//Downloads version from github
	public static void updateDownload() {
		
		//Download Update
		try {
			Files.copy(Paths.get("../online/version.txt"), Paths.get("../game/version.txt"), StandardCopyOption.REPLACE_EXISTING);
		
			Gui.updateProgress.setString("Latest Version Installed");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		Gui.loadGUI();
	}
	
	//Launches game
	public static void play() {
		System.exit(0);
	}
	
	//Exits launcher
	public static void exit() {
		System.exit(0);
	}
}