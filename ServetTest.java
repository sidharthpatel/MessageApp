package main;

import javax.swing.*;

public class ServetTest {

	public static void main(String[] args) {
		Server sally = new Server();
		sally.setAlwaysOnTop(true);
		sally.setLocationRelativeTo(null);
		sally.setDefaultCloseOperation(sally.EXIT_ON_CLOSE);
		sally.startRunning();

	}

}