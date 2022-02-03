package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MainFrame extends JFrame{
	
	Container contentPane;
	private Socket socket = null;
	public ObjectInputStream ois;
	public ObjectOutputStream oos;
	public String req;
	public String resp;
	public String msg;
	boolean signupState = false;

	public MainFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		contentPane = getContentPane();
		contentPane.setLayout(null);

//		loginView.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
//		contentPane.add(loginView);
//		this.setVisible(true);
//		NowView = loginView;
		
		displayView(LoginView);

	}
	
	public void displayView(GameView gc) {
		gc.display();
		gc.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		NowView = gc;
		contentPane.add(gc);
		this.setVisible(true);
	}
	
	
	public void changeView(GameView gc) {
//		contentPane.remove(NowView);
		contentPane.removeAll();
		contentPane.add(gc);
		gc.removeAll();
		gc.display();
		NowView = gc;
		gc.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		revalidate();
		repaint();
	}

//	public static void main(String[] args) {
//		new MainFrame();
//	}
}
