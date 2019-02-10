import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class VNgineGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel bg;
	private JLabel sprite;
	private JTextField name;
	private JTextArea dialogue;
	private JButton button;
	private JButton choice1;
	private JButton choice2;
	private JPanel gl;
	
	private List<Character> chars = null;
	private int num;
	private List<String[]> commands = null;
	
	public VNgineGUI() throws FileNotFoundException {
		setSize(1500,1000);
		setLayout(new GridBagLayout());
		setResizable(false);
		
		//pane for the bg and sprite
		JLayeredPane p = new JLayeredPane();
		p.setPreferredSize(new Dimension(300,300));
		
		Font font1 = new Font("Serif", Font.BOLD, 50);
		Font font2 = new Font("SansSerif", Font.PLAIN, 30);
		Font font3 = new Font("SansSerif", Font.BOLD, 30);
		
		this.name = new JTextField("Name");
		this.name.setEditable(false);
		this.name.setFont(font1);
		this.name.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		this.name.setBackground(Color.DARK_GRAY);
		this.name.setForeground(Color.WHITE);
		this.dialogue = new JTextArea("Dialogue");
		this.dialogue.setEditable(false);
		this.dialogue.setFont(font2);
		this.dialogue.setBackground(Color.LIGHT_GRAY);
		this.dialogue.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 20));
		this.button = new JButton("-->");
		this.button.setFocusable(true);
		this.button.addActionListener(this);
		this.bg = new JLabel();
		this.bg.setBounds(0,0,1500,1000);
		this.sprite = new JLabel();
		//this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\bg.jpeg"));
		//this.sprite.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\mark.png"));
		this.choice1 = new JButton();
		this.choice2 = new JButton();
		this.choice1.addActionListener(this);
		this.choice2.addActionListener(this);
		
		this.gl = new JPanel(new GridLayout(1,2));
		
		this.chars = new ArrayList<Character>();
		this.commands = new ArrayList<String[]>();
		this.num = 0;
		
		FileReader fr = new FileReader("games/game.txt");
		BufferedReader reader = new BufferedReader(fr);
		this.interpret(reader);
		
		//initialise opening
		String[] currentSet = this.commands.get(num);
		this.name.setText(currentSet[0]);
		this.dialogue.setText(currentSet[1]);
		this.setSprite(currentSet[2]);
		this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet[3]));
		this.num += 1;
		
		for (String[] a : commands) {
			for (String b : a) {
				System.out.println(b);
			}
			System.out.println("-----");
		}
		
		p.add(bg, new Integer(1));
		p.add(sprite, new Integer(2));
		
		gl.add(choice1);
		gl.add(choice2);
		
		p.setBounds(0, 0, 1500, 1000);
		
		gl.setBounds(0, 950, 1500, 1000);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 0.6;
		gbc.weightx = 1;
		add(p, gbc);
		
		gbc.gridy = 2;
		gbc.weighty = 0.05;
		add(name, gbc);
		
		gbc.gridy = 3;
		gbc.weighty = 0.2;
		add(dialogue, gbc);
		
		gbc.gridy = 4;
		gbc.weighty = 0.15;
		add(button, gbc);
		add(gl, gbc);
		gl.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o == button) {
			
			String[] currentSet = this.commands.get(num);
			
			if (currentSet.length == 4) {
				this.name.setText(currentSet[0]);
				this.dialogue.setText(currentSet[1]);
				this.setSprite(currentSet[2]);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet[3]));
				this.num += 1;
			} else if (currentSet.length == 7) {
				System.out.println(num);
				this.name.setText("");
				this.dialogue.setText(currentSet[0]);
				this.setSprite(currentSet[1]);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet[2]));
				choice1.setText(currentSet[3]);
				choice2.setText(currentSet[4]);
				button.setVisible(false);
				gl.setVisible(true);
			} else if (currentSet.length == 2) {
				this.dialogue.setVisible(false);
				this.name.setVisible(false);
				this.sprite.setVisible(false);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet[0]));
				this.button.setVisible(false);
			}
		} else if (o == choice1) {
			System.out.println(num);
			//finding the carry on point
			String[] currentSet = this.commands.get(num);
			for (String i : currentSet) {
				System.out.println(i);
			}
			String id = currentSet[5];
			int i = 0;
			for (String[] blah : commands) {
				if (blah.length == 1 && blah[0].equals(id)) {
					this.num = i+1;
				} else {
					i += 1;
				}
			}
			
			String[] currentSet2 = this.commands.get(num);
			if (currentSet2.length == 4) {
				gl.setVisible(false);
				button.setVisible(true);
				this.name.setText(currentSet2[0]);
				this.dialogue.setText(currentSet2[1]);
				this.setSprite(currentSet2[2]);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet2[3]));
				this.num += 1;
			} else if (currentSet2.length == 7) {
				System.out.println(num);
				this.name.setText("");
				this.dialogue.setText(currentSet2[0]);
				this.setSprite(currentSet2[1]);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet2[2]));
				choice1.setText(currentSet2[3]);
				choice2.setText(currentSet2[4]);
			}
			
			
		} else if (o == choice2) {
			//finding the carry on point
			String[] currentSet = this.commands.get(num);
			for (String i : currentSet) {
				System.out.println(i);
			}
			String id = currentSet[6];
			int i = 0;
			for (String[] blah : commands) {
				if (blah.length == 1 && blah[0].equals(id)) {
					this.num = i+1;
				} else {
					i += 1;
				}
			}
			
			String[] currentSet2 = this.commands.get(num);
			if (currentSet2.length == 4) {
				gl.setVisible(false);
				button.setVisible(true);
				this.name.setText(currentSet2[0]);
				this.dialogue.setText(currentSet2[1]);
				this.setSprite(currentSet2[2]);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet2[3]));
				this.num += 1;
			} else if (currentSet2.length == 7) {
				System.out.println(num);
				this.name.setText("");
				this.dialogue.setText(currentSet2[0]);
				this.setSprite(currentSet2[1]);
				this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + currentSet2[2]));
				choice1.setText(currentSet2[3]);
				choice2.setText(currentSet2[4]);
			}
		}
		
	}
	
	public void interpret(BufferedReader reader) throws FileNotFoundException {
		//Scanner s = new Scanner(System.in);
		Pattern newCharacter = Pattern.compile("char \"[A-z0-9]+\" \".+\" \".+\\.[A-z]+\"");
		Pattern setBackground = Pattern.compile("bg \".+\\.[A-z]+\"");
		Pattern setSprite = Pattern.compile("sprite \".+\" \".+\\.[A-z]+\"");
		Pattern setAction = Pattern.compile("action \".+\"");
		Pattern end = Pattern.compile("end \".+\\.[A-z]+\"");
		Pattern setDialogue = Pattern.compile("dia \"[A-z0-9]+\" \".+\"");
		Pattern branch = Pattern.compile("branch \".+\" \".+\" \".+\" \"[A-z]+\" \"[A-z]+\"");
		//example: branch "What do you want to do?" "Run away" "Walk closer" "run" "approach"

		try {
			String line = reader.readLine();
			String[] currentL = new String[4];
			ArrayList<String> branches = new ArrayList<String>();
			int n = 0;
			while (line != null) {
				if (newCharacter.matcher(line).matches()) {
					String[] charDetails = line.split("\"");
					// add error checking to see if character already exists
					Character character = new Character(charDetails[1], charDetails[3], charDetails[5]);
					this.chars.add(character);
					
				} else if (setBackground.matcher(line).matches()) {
					String[] bg = line.split("\"");
					/**this.bg.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\games\\" + bg[1]));
					this.bg.setBounds(0,0,1500,1000); */
					currentL[3] = bg[1];
				} else if (setSprite.matcher(line).matches()) {
					String[] sprite = line.split("\"");
					Character lad = this.getCharacter(sprite[1]);
					lad.setSprite(sprite[3]);
					currentL[2] = sprite[3];
					//this.setSprite(sprite[3]);
					
				} else if (setAction.matcher(line).matches()) {
					String[] action = line.split("\"");
					currentL[0] = "";
					currentL[1] = action[1];
					commands.add(new String[]{"a", "b", "c", "d"});
					commands.get(n)[0] = currentL[0];
					commands.get(n)[1] = currentL[1];
					commands.get(n)[2] = currentL[2];
					commands.get(n)[3] = currentL[3];
					n += 1;
					
				} else if (line.equals("empty")) {
					currentL[2] = null;
					
				} else if (end.matcher(line).matches()) {
					String[] ends = line.split("\"");
					commands.add(new String[] {"a", "b"});
					commands.get(n)[0] = ends[1];
					n+=1;
				} else if (setDialogue.matcher(line).matches()) {
					// check if name already exists in chars
					String[] dia = line.split("\"");
					// replace with character object and getters for that object
					Character character = this.getCharacter(dia[1]);
					currentL[2] = character.getSprite();
					currentL[0] = character.getName();
					currentL[1] = dia[3];
					commands.add(new String[]{"a", "b", "c", "d"});
					commands.get(n)[0] = currentL[0];
					commands.get(n)[1] = currentL[1];
					commands.get(n)[2] = currentL[2];
					commands.get(n)[3] = currentL[3];
					n += 1;
				} else if (branch.matcher(line).matches()) {
					String[] longboi = line.split("\"");
					commands.add(new String[] {"a", "b", "c", "d", "e", "f", "g"});
					commands.get(n)[0] = longboi[1];
					commands.get(n)[1] = currentL[2];
					commands.get(n)[2] = currentL[3];
					commands.get(n)[3] = longboi[3];
					commands.get(n)[4] = longboi[5];
					commands.get(n)[5] = longboi[7];
					commands.get(n)[6] = longboi[9];
					n += 1;
					//add the ids to the branches listarray
					branches.add(longboi[7]);
					branches.add(longboi[9]);
				} else if (check(branches, line)) {
					commands.add(new String[] {"a"});
					commands.get(n)[0] = line;
					n += 1;
					//this is stupid
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean check(ArrayList<String> list, String item) {
		for (String i : list) {
			if (i.equals(item)) {
				return true;
			}
		}
		return false;
	}
	
	private void setSprite(String spriteLoc) {
		ImageIcon spriteIm = new ImageIcon(System.getProperty("user.dir") + "\\games\\" + spriteLoc);
		this.sprite.setIcon(spriteIm);
		int x = 750 - Math.round(spriteIm.getIconWidth()/2);
		int y = 0;
		if (spriteIm.getIconHeight() < 600) {
			y = (600 - spriteIm.getIconHeight())/2;
		}
		//System.out.println(y);
		this.sprite.setBounds(x,y,1500,600);
	}
	
	private Character getCharacter(String id) {
		Character character = null;
		for (Character lad : this.chars) {
			if (lad.getId().equals(id)) {
				character = lad;
			}
		}
		return character;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		VNgineGUI gui = new VNgineGUI();
		gui.setVisible(true);
	}

}
