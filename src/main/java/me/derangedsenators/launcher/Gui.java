package.me.derangedsentors.launcher

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


class Gui {
	
	public static JProgressBar updateProgress = new JProgressBar(0, 100); //Update Progress Bar
	public static JFrame frame = new JFrame("Cops and Robbers"); //Launcher Window
	
    public static void loadGUI() {

        //Creating a Frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //Creating the bar at the top, adding version number and detected OS
        JMenuBar topBar = new JMenuBar();
		topBar.setLayout(new BorderLayout());
		
		
		JLabel OSLabel = new JLabel("Operating System: " + LauncherMain.getOS());
		topBar.add(OSLabel);
		
        JLabel versionLabel = new JLabel("Version: " + LauncherMain.getVersion() + " ");
        topBar.add(BorderLayout.EAST, versionLabel);

		//Adding pictures in the center of the screen
		ImageIcon splash = new ImageIcon("./splash.png");
		JLabel splashLabel = new JLabel(splash);
		frame.add(splashLabel);

        //Creating the panel at the bottom with play/exit buttons and update progress
        JPanel bottomPanel = new JPanel(new BorderLayout());
		
		//Adding progress bar
		updateProgress.setStringPainted(true);
		bottomPanel.add(updateProgress);
		
		//Sub-panel for play/exit buttons in the bottom-right
		JPanel bottomSubPanel = new JPanel();
        JButton play = new JButton("Play");
		play.setPreferredSize(new Dimension(200, 40));
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LauncherMain.play();              
			}          
		});
		bottomSubPanel.add(play);
			
        JButton exit = new JButton("Exit");
		exit.setPreferredSize(new Dimension(200, 40));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LauncherMain.exit();            
			}          
		});
		bottomSubPanel.add(exit);

        bottomPanel.add(bottomSubPanel, BorderLayout.EAST);

        //Adding all the components to the frame
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.NORTH, topBar);
        frame.setVisible(true);
    }
	
	public static boolean updateGUI() {
		int answer = JOptionPane.showConfirmDialog(frame, "Would you like to update?", "Update Required", JOptionPane.YES_NO_OPTION);
		if(answer == 0) { return true; } 
		else {
			int confirmAnswer = JOptionPane.showConfirmDialog(frame, "Are you sure? The game will not open if you are not running the latest version", "Update Required", JOptionPane.YES_NO_OPTION);
			if (confirmAnswer == 1) { return true; }
			else { return false; }
		}
	}
}