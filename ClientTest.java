package main;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientTest {

	public static void main(String[] args) {
		Client charlie;
		charlie = new Client("10.135.238.164");
		charlie.setLocationRelativeTo(null);
		charlie.setDefaultCloseOperation(charlie.EXIT_ON_CLOSE);
		charlie.startRunnign();
	}

}