package org.proj.view;

import static org.proj.Resource.gameNum;
import static org.proj.Resource.gametrue;
import static org.proj.Resource.*;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.proj.controller.Controller;

public class GameEndPane extends ViewContainer {
	JButton goMainBtn = new JButton("메인화면으로");
	JButton replayBtn = new JButton("다시시작");

	public GameEndPane() {
		replayBtn.addActionListener(this);
		goMainBtn.addActionListener(this);
	}
	@Override
	public void display() {
		this.setLayout(null);
		JLabel gameNumlbl = new JLabel("총 도전 횟수 : " + gameNum);
		JLabel gametruelbl = new JLabel("정답 : " + gametrue);
		JLabel gameEndmsg = new JLabel("게임결과");

		gameEndmsg.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		gameNumlbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		gametruelbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		this.add(gameEndmsg);
		this.add(gameNumlbl);
		this.add(gametruelbl);
		this.add(replayBtn);
		this.add(goMainBtn);

		replayBtn.setBounds(10, 290, 120, 50);
		goMainBtn.setBounds(160, 290, 120, 50);

		gameEndmsg.setBounds(95, 20, 200, 30);
		gameNumlbl.setBounds(85, 90, 200, 30);
		gametruelbl.setBounds(120, 150, 100, 30);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == goMainBtn) {
			gameNum = 0;
			gametrue = 0;
			resultPane.setVisible(false);
			JOptionPane.showMessageDialog(NowView, "게임을 종료합니다!");
			resultPane.removeAll();
			Controller c = Controller.getController();
			c.Viewchange(MAINPAGE);
		}
		
		if(e.getSource() == replayBtn) {
			gameNum = 0;
			gametrue = 0;
			resultPane.setVisible(false);
			JOptionPane.showMessageDialog(NowView, "game Replay");
			resultPane.removeAll();
			((GameContainer) NowView).reGame();
//			Controller c = Controller.getController();
//			c.Viewchange(PLUSMINUS);
		}
	}

}
