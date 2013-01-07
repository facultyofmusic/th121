package custom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CustomMain extends JFrame {
	public static HashMap<String, String> values = new HashMap<String, String>();
	JTabbedPane tabPane;
	AudioPane audio;
	KeyConfigPane keyConfig;

	public CustomMain() {
		super("Touhou 12.1 Configuration");

		keyConfig = new KeyConfigPane();
		audio = new AudioPane();

		// tab pane
		tabPane = new JTabbedPane(JTabbedPane.TOP);
		tabPane.addTab("Audio", audio);
		tabPane.addTab("Key Config.", keyConfig);

		// cancel button
		JButton cancel = new JButton("Cancel");
		cancel.setLocation(10, 10);

		JButton saveAndExit = new JButton("Save and exit");
		saveAndExit.setLocation(300, 10);

		// bottom pane
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
		bottom.add(cancel);
		bottom.add(saveAndExit);
		bottom.setPreferredSize(new Dimension(100, 33));

		add(tabPane);
		add(bottom, BorderLayout.SOUTH);
		setSize(500, 400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[]) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			BufferedReader in = new BufferedReader(new FileReader("th121.cfg"));

			String s = null;
			while ((s = in.readLine()) != null) {
				s = s.trim();

				if (!(s.equals("") || s.startsWith("//"))) {
					String key = s.split("=")[0];
					String value = s.split("=")[1];
					values.put(key, value);
					System.out.println("KEY: " + key + "  VALUE: " + value);
				}
			}

			in.close();

			// check
			AudioPane.checkValues(values);
			VideoPane.checkValues(values);
			KeyConfigPane.checkValues(values);
		} catch (IOException ex) {
			JOptionPane
					.showMessageDialog(
							null,
							"The configuration file is missing.  A new one will be created with default configurations.",
							"Configuration File Missing",
							JOptionPane.ERROR_MESSAGE);
			createNew();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane
					.showMessageDialog(
							null,
							"The configuration file is corrupt or has been unintentially modified.  A new one will be created with default configurations.",
							"Configuration File Error",
							JOptionPane.ERROR_MESSAGE);
			createNew();
		}

		CustomMain cm = new CustomMain();

	}

	public static void createNew() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("th121.cfg"));

			// write file
			out.println("//  [Touhou Project Doujin 12.1 Configuration]");
			out.println("//");
			out.println("//  * To make a comment (lines that will not be read by the game, like this one), start the line with \"//\"");
			out.println("//  * Please note the conflicting key configuration WILL cause the game to reject the config file.");
			out.println("//  * Please do not change these values unless you know what you're doing. If you accidentally messed it up, you can reset it to default using the configuration tool.");
			out.println("//  * And honestly, you were't supposed to fiddle around with this anyways... =D");
			out.println("");
			out.println("// video config.");
			out.println("V_SYNC=true");
			out.println("FULLSCREEN=false");
			out.println("RESOLUTION=640");
			out.println("FORCE_FRAMERATE=false");
			out.println("");
			out.println("// sound. use java if OpenAL freezes");
			out.println("SOUND_SYSTEM=java");
			out.println("MUSIC_VOLUME=100");
			out.println("SFX_VOLUME=100");
			out.println("");
			out.println("// key configuration, for crappy laptop or netbook keyboards! =)");
			out.println("KEY_FOCUS=42");
			out.println("KEY_A=44");
			out.println("KEY_B=45");
			out.println("KEY_UP=200");
			out.println("KEY_DOWN=208");
			out.println("KEY_LEFT=203");
			out.println("KEY_RIGHT=205");
			out.println("KEY_QUIT=88");
			
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane
					.showMessageDialog(
							null,
							"Modification to the filesystem has been denied.\n"
									+ "Please make sure the game folder isn't placed in a system folder and try again.\n"
									+ "(e.g. C:\\WINDOWS\\..)",
							"Access denied", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
}
