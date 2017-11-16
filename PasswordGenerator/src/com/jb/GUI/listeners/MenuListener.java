package com.jb.GUI.listeners;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.jb.main.PasswordGenerator;

public class MenuListener implements ActionListener {

	private JCheckBox longerWords;
	private PasswordGenerator passwordGenerator;
	private JTextArea passwordArea;
	private Clipboard clipboard;
	private StringSelection selection;

	public MenuListener(JCheckBox longerWords, PasswordGenerator passwordGenerator, JTextArea passwordArea) {
		
		this.longerWords = longerWords;
		this.passwordGenerator = passwordGenerator;
		this.passwordArea = passwordArea;
		
		// Start Clipboard
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		// Check for menu items
		// Exit
		if (ae.getActionCommand().equals("Exit")) {
			System.exit(0);
		}

		// Help
		if (ae.getActionCommand().equals("Help")) {
			JOptionPane.showMessageDialog(null,
					"How to use this password generator.\nThis generator uses over 57000 English words to create a unique password.\nSelect the options at the bottom and press generator to create a password.");
		}

		// Copy Last Thing to ClipBoard
		if (ae.getActionCommand().equals("Copy Last Password")) {
			selection = new StringSelection(passwordGenerator.getPassword());
			clipboard.setContents(selection, selection);
			
			// Clear Object
			selection = null;
		}
		
		// Copy Highlighted Text
		if (ae.getActionCommand().equals("Copy Highlighted Password")) {
			selection = new StringSelection(passwordArea.getSelectedText());
			clipboard.setContents(selection, selection);
		}

		// Check Box
		// Words longer than eight
		if (longerWords.isSelected()) {
			PasswordGenerator.setLongWords(true);
		} else {
			PasswordGenerator.setLongWords(false);
		}

		// Buttons
		// Generate Password
		if (ae.getActionCommand().equals("Generate")) {

			// Generate Password and Display Passwords:
			passwordGenerator.generateNewPassword();
			passwordArea.append("----------------------------------------------------------");
			passwordArea.append("\n");
			passwordArea.append("Generated Password: " + passwordGenerator.getPassword());
			passwordArea.append("\n");
			passwordArea.append("----------------------------------------------------------");
			passwordArea.append("\n");

		}

		// Clear Screen
		if (ae.getActionCommand().equals("Clear")) {
			passwordArea.setText(null);
		}

	}

}
