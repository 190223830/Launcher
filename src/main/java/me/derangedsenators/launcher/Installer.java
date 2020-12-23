package.me.derangedsentors.launcher

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

class Installer {
	
	public static JFrame frame = new JFrame("Cops and Robbers Installer");
	
	public static void loadGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
		
		String userInfo = "This is where info that the user will need to read should be put.";
		JTextField userInfoBox = new JTextField(userInfo, userInfo.length());
		frame.add(userInfoBox);
		
		JCheckBox checkbox = new JCheckBox("I agree to these terms");
		frame.add(checkbox);
		
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton install = new JButton("Install");
		install.setPreferredSize(new Dimension(100, 40));
		install.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkbox.isSelected()) {
					install();
				}
				else {
					JOptionPane.showMessageDialog(null, "You must agree to the Terms & Conditions before intalling", "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}          
		});
		bottomPanel.add(install, BorderLayout.WEST);
			
        JButton cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(100, 40));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LauncherMain.exit();            
			}          
		});
		bottomPanel.add(cancel, BorderLayout.EAST);

		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	
	public static boolean checkInstall() {
		boolean installed;
		File game = new File("../game/version.txt");
		if(game.isFile()) {
			installed = true;
		}
		else {
			installed = false;
		}
		return installed;
	}
	
	public static void install() {
		try {
			Files.copy(Paths.get("../online/version.txt"), Paths.get("../game/version.txt"), StandardCopyOption.REPLACE_EXISTING);
			Gui.updateProgress.setString("Latest Version Installed");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		Gui.loadGUI();
	}

}