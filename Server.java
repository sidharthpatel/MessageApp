package main;

import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.event.*;

public class Server extends JFrame{

	/*
	 * Variable that will save the message before submitting it.
 	 */
	private JTextField userText;
	/**
	 * JTextArea displays a window or an area that shows you the conversation.
	 * It is the chat window of the program.
	 */
	private JTextArea chatWindow;
	/**
	 * A stream that flows from your computer/server to the client's computer.
	 * These messages are passed between server and client in packages.
	 * Output is messages sent out to the client.
	 */
	private ObjectOutputStream output;
	/**
	 * Similarly, a stream that flows from client's computer to the server/ your computer.
	 * Input is the messages received from the client.
	 */
	private ObjectInputStream input;
	/**
	 * A server socket is a connection to which each client connects to.
	 */
	private ServerSocket server;
	/**
	 * Sockets are basically connections formed with the server.
	 */
	private Socket connection;
		
	//constructor
	public Server() {
		/**
		 * Name of the program
		 */
		super("Instant Messenger");

		userText = new JTextField();
		/**
		 * You cannot type anything in the message box until you are connected to a client.
		 * That is what the setEditable method does.
		 */
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//sendMessage(e.getActionCommand());
						userText.setText("");
					}
				}
		);
		add(userText, BorderLayout.NORTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(300,150);
		setVisible(true);
		
	}

	//set up and run the sever
	public void startRunning() {
		try {
			server = new ServerSocket(13104, 100);
				while(true) {
					try {
						waitForConnection();
						setupStreams();
						whileChatting();
					} catch(EOFException e) {
						showMessage("\n Server ended the connectin! ");
					} finally {
						closeCrap();
					}
				}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//wait for connection, then display connection information
	private void waitForConnection() throws IOException {
		showMessage("Waiting for someone to connect... \n");
		connection = server.accept();
		showMessage(" Now connected to "
				+ connection.getInetAddress().getHostName());
	}
	
	//get stream to send and receive date
	private void setupStreams() throws IOException {
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup! \n");
	}
	
	//during the chat conversation
	private void whileChatting() throws IOException {
		String message = " You are now connected! ";
		sendMessage(message);
		ableToType(true);
		do{
			try {
				message = (String) input.readObject();
				showMessage("\n" + message);
			} catch(ClassNotFoundException e) {
				showMessage("\n idk wtf that user sent!");
			}
		} while(!message.equals("CLIENT - END"));
	}
	
	//close streams and socket after you are done chatting
	private void closeCrap() {
		showMessage("\n Closing connections... \n");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//send a message to client
	private void sendMessage(String message) {
		try {
			output.writeObject("SERVER - " + message);
			output.flush();
			showMessage("\n SERVER -  " + message);
		} catch(IOException e) {
			chatWindow.append("\n ERROR: DUDE I CANT SEND THAT MESSAGE");
		}
	}
	
	//update chatWindow
	private void showMessage(final String text) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					chatWindow.append(text);
				}
			}
		);
	}
	
	//let the user type into their box
	private void ableToType(final boolean tof) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					userText.setEditable(tof);
				}
			}
		);
	}
	
}