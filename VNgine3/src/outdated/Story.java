package outdated;
import java.util.regex.*;


import javax.swing.KeyStroke;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.io.BufferedReader;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Story {
	// location of the bg image stored as string
	private String bg = null;
	private String name = null;
	private String sprite = null;
	private String dialogue = null;
	private List<String> choices = null;
	private HashMap<Integer, String> choices2 = null; // ?
	// arrayList of characters, each character has a name and a current sprite
	private List<Character> chars = null;

	public Story() {
		chars = new ArrayList<Character>();
		choices = new ArrayList<String>();
		choices2 = new HashMap<Integer, String>();
	}

	public String addChoice(String choice) {

		/**
		 * if (choice == null) { //error shit }
		 * 
		 * if () { // if choice doesnt meet regex or whatever error checking // error
		 * shit } else { // need to find a way to add choices to the same option // and
		 * how to pick the option id }
		 **/
		return null;
	}

	// all setters will get values from GUI inputs
	private void setName(String name) {
		if (name == null) {
			// name cannot be null
		}
		// name regex checking
		this.name = name;
	}

	private void setBackground(String bg) {
		this.bg = bg;
	}

	private void setSprite(String sprite) {
		this.sprite = sprite;
	}

	private void setAction(String action) {
		this.dialogue = action;
	}

	// change parameter to FileReader
	// maybe store all string lines in an arrayList
	// store all branchnames and their ids in a hashmap
	// work out current line and do action based on it
	// eg if current line is creating a character, add new character to list
	public void interpret(BufferedReader reader) throws FileNotFoundException {
		//Scanner s = new Scanner(System.in);
		Pattern newCharacter = Pattern.compile("char \"[A-z0-9]+\" \".+\" \".+\\.[A-z]+\"");
		Pattern setBackground = Pattern.compile("bg \".+\\.[A-z]+\"");
		Pattern setSprite = Pattern.compile("sprite \".+\" \".+\\.[A-z]+\"");
		Pattern setAction = Pattern.compile("action \".+\"");
		Pattern end = Pattern.compile("end \".+\\.[A-z]+\"");
		Pattern setDialogue = Pattern.compile("dia \"[A-z0-9]+\" \".+\"");

		try {
			String line = reader.readLine();
			while (line != null) {
				if (newCharacter.matcher(line).matches()) {
					String[] charDetails = line.split("\"");
					// add error checking to see if character already exists
					Character character = new Character(charDetails[1], charDetails[3], charDetails[5]);
					this.chars.add(character);
				} else if (setBackground.matcher(line).matches()) {
					String[] bg = line.split("\"");
					this.setBackground(bg[1]);
				} else if (setSprite.matcher(line).matches()) {
					String[] sprite = line.split("\"");
					this.setSprite(sprite[3]);
				} else if (setAction.matcher(line).matches()) {
					String[] action = line.split("\"");
					this.setAction(action[1]);
					System.out.println(action[1]);
					//s.nextLine();
				} else if (line.equals("empty")) {
					this.dialogue = "";
					this.name = "";
					this.sprite = "";
				} else if (end.matcher(line).matches()) {
					String endImage = line.split("\"")[1];
					this.setBackground(endImage);
				} else if (setDialogue.matcher(line).matches()) {
					// check if name already exists in chars
					String[] dia = line.split("\"");
					// replace with character object and getters for that object
					Character character = this.getCharacter(dia[1]);
					this.name = character.getName();
					this.dialogue = dia[3];
					this.sprite = character.getSprite();
					System.out.println(this.name + ": " + this.dialogue);
					//s.nextLine();
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Character getCharacter(String name) {
		Character character = null;
		for (Character lad : this.chars) {
			if (lad.getId().equals(name)) {
				character = lad;
			}
		}
		return character;
	}
}