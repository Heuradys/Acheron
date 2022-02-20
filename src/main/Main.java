package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("The Sands of Acheron");
		
		GPane gPane = new GPane();
		window.add(gPane);
		
		window.pack(); //packages it all up!
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gPane.initGame();
		gPane.startGameThread();
		
	}

}
