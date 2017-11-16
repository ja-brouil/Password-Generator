package com.jb.GUI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	public MainFrame(String title) {
		super(title);
		
		// Attributes
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(500, 500));
		setVisible(true);
		setLocationRelativeTo(null);
	
		// Add Main GUI
		PasswordGUI passwordGUI = new PasswordGUI();
		add(passwordGUI);

	}
	
	public static void main(String[] args) {
		
		// Start Program
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new MainFrame("Password Generator");
				
			}
		});
	}

}
