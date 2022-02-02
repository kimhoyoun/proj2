package org.proj.view;

import static org.proj.Resource.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class GameContainer extends ViewContainer{
	
	public abstract void display();
	
	public abstract void actionPerformed(ActionEvent e);
	
	public void reGame() {
		System.out.println("game replay....");
	}
	
//public static void endGame() {
//		
//		gameResultPane.setLayout(null);
//		JLabel gameNumlbl = new JLabel("총 도전 횟수 : "+gameNum);
//		JLabel gametruelbl = new JLabel("정답 : "+gametrue);
//		JLabel gameEndmsg = new JLabel("게임결과");
//		
//		gameEndmsg.setFont(new Font("맑은 고딕", Font.BOLD, 30));
//		gameNumlbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		gametruelbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		
//		
//		gameResultPane.add(gameEndmsg);
//		gameResultPane.add(gameNumlbl);
//		gameResultPane.add(gametruelbl);
//		gameResultPane.add(replayBtn);
//		gameResultPane.add(goMainBtn);
//		
//		replayBtn.setBounds(10,290,120,50);
//		goMainBtn.setBounds(160,290,120,50);
//		
//		gameEndmsg.setBounds(95, 20, 200, 30);
//		gameNumlbl.setBounds(85, 90, 200, 30);
//		gametruelbl.setBounds(120, 150, 100, 30);
//		
//		gameResultPane.setVisible(true);
////		replayBtn.addActionListener(this);
//		
//	}
}
