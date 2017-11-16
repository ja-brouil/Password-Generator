package com.jb.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jb.GUI.listeners.KeyBoardInput;
import com.jb.GUI.listeners.MenuListener;
import com.jb.main.PasswordGenerator;

public class PasswordGUI extends JPanel {

	private JTextArea passwordArea;
	private JMenuBar jMenuBar;
	private JMenu file, edit;
	private JMenuItem exit, about, copy, copySelect;
	private MenuListener menuListener;
	private JButton generate, clear;
	private JCheckBox longerWords;
	private PasswordGenerator passwordGenerator;
	private JScrollPane jScrollPane;

	public PasswordGUI() {
		super();

		// Attributes
		setLayout(new BorderLayout());
		setSize(new Dimension(500, 500));
		setVisible(true);
		setFocusable(true);
		requestFocus();

		// Load Text Files
		loadTextFiles();

		// Initialize the Panel
		init();

		// Add Key Listener
		addKeyListener(new KeyBoardInput());

	}

	public void init() {

		// Start Objects
		JPanel panel = new JPanel();

		// Bar
		jMenuBar = new JMenuBar();
		file = new JMenu("File");
		edit = new JMenu("Edit");
		exit = new JMenuItem("Exit");
		copy = new JMenuItem("Copy Last Password");
		copySelect = new JMenuItem("Copy Highlighted Password");
		about = new JMenuItem("Help");
		
		
		// Add to the menu bar
		file.add(about);
		file.add(exit);
		edit.add(copy);
		edit.add(copySelect);
		jMenuBar.add(file);
		jMenuBar.add(edit);

		// Add the GUI Panel
		this.add(jMenuBar, BorderLayout.NORTH);

		// Start Generate Button
		generate = new JButton("Generate");
		generate.setFocusable(false);
		clear = new JButton("Clear");
		clear.setFocusable(false);

		// Start the Checkbox
		longerWords = new JCheckBox("8 letters words");
		longerWords.setSelected(false);
		longerWords.setFocusable(false);

		// Start Text Panel area + Scroll Pane
		passwordArea = new JTextArea();
		jScrollPane = new JScrollPane(passwordArea);
		passwordArea.setEditable(false);
		passwordArea.setVisible(true);
		passwordArea.addKeyListener(new KeyBoardInput());
		this.add(jScrollPane);

		

		// Add Listeners
		menuListener = new MenuListener(longerWords, passwordGenerator, passwordArea);
		exit.addActionListener(menuListener);
		about.addActionListener(menuListener);
		copy.addActionListener(menuListener);
		copySelect.addActionListener(menuListener);
		longerWords.addActionListener(menuListener);
		generate.addActionListener(menuListener);
		clear.addActionListener(menuListener);

		// Add to GUI Panel
		panel.add(generate);
		panel.add(clear);
		panel.add(longerWords);

		// Add Buttons + CheckBox
		this.add(panel, BorderLayout.SOUTH);
	}

	private void loadTextFiles() {
		passwordGenerator = new PasswordGenerator();
	}

	public PasswordGenerator getPasswordGenerator() {
		return passwordGenerator;
	}
}
