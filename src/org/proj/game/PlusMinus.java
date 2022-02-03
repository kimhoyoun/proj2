package org.proj.game;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.view.GameView;

public class PlusMinus extends GameView {
	GamePlayMain gp;
	JLabel bgLabel; // 가장 밑배경(초록잔디)
	JLabel pmbgLabel; // 게임 배경(하얀색 메모장)
	JLabel quizLabel; // 문제 나오는 곳 라벨( num + num ) 

//	JPanel gameResultPane;
	JLabel gametruelbl;
	JLabel gameNumlbl;
	JLabel gameEndmsg;
//	JButton replayBtn;
	
	JButton[] choiceBtn; // 4개의 선지
	JLabel checkLabel; // 정답일 경우 나오는 체크표시
	JLabel xLabel; // 오답일 경우 나오는 엑스표시
//	JButton pauseBtn; // 멈춤 버튼
	ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	JButton pauseBtn =new JButton(pauseIcon);
	Timer timer;
//	int gameNum =0;
//	int gametrue=0;
//	int endGameNum = 2;

	public PlusMinus() {
		
//		display();
	}

	@Override
	public void display() {
		gp = new GamePlayMain();
		
		this.setLayout(null);
		
		this.add(resultPane);
		resultPane.setBounds(FRAME_WIDTH/2-300/2, FRAME_HEIGHT/2-350/2, 300, 350);
		resultPane.setVisible(false);
		
		
		ImageIcon bgicon = new ImageIcon("images/backgroundImg.png");
		bgLabel = new JLabel(bgicon);
		bgLabel.setBounds(0, 0, 1024, 768);

		ImageIcon pmicon = new ImageIcon("images/gamebackImg.png");
		pmbgLabel = new JLabel(pmicon);
		pmbgLabel.setBounds(210, 100, 600, 500);

		quizLabel = new JLabel(gp.question);
		quizLabel.setFont(new Font("Gothic", Font.BOLD, 115));
		quizLabel.setForeground(Color.ORANGE);
		quizLabel.setBounds(330, 150, 400, 200);

		choiceBtn = new JButton[4];
		for (int i = 0; i < gp.answerArr.length; i++) {
			choiceBtn[i] = new RoundJButton();
			choiceBtn[i].setText(Integer.toString(gp.answerArr[i]));
			choiceBtn[i].setBackground(Color.orange);
			choiceBtn[i].setFont(new Font("Gothic", Font.BOLD, 70));
			choiceBtn[i].setForeground(Color.WHITE);
		}
		
		choiceBtn[0].setBounds(330, 350, 150, 70);
		choiceBtn[1].setBounds(550, 350, 150, 70);
		choiceBtn[2].setBounds(330, 450, 150, 70);
		choiceBtn[3].setBounds(550, 450, 150, 70);

		ImageIcon checkIcon = new ImageIcon("images/checked.png");
		checkLabel = new JLabel(checkIcon);
		ImageIcon xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);
		

		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);    // 버튼의 외곽 투명하게 
		pauseBtn.setContentAreaFilled(false);  // 만들어 주는 것

		checkLabel.setBounds(670, 65, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(670, 65, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		
		
		this.add(pauseBtn);
		
		this.add(choiceBtn[0]);
		this.add(choiceBtn[1]);
		this.add(choiceBtn[2]);
		this.add(choiceBtn[3]);

		this.add(quizLabel);

		this.add(pmbgLabel);

		this.add(bgLabel);

		choiceBtn[0].addActionListener(this);
		choiceBtn[1].addActionListener(this);
		choiceBtn[2].addActionListener(this);
		choiceBtn[3].addActionListener(this);
		pauseBtn.addActionListener(this);
	}
	
//	@Override
//	public void reGame() {
//		gp = new GamePlayMain();
//		
//		quizLabel.setText(gp.question);
//		for (int i = 0; i < gp.answerArr.length; i++) {
//			choiceBtn[i].setText(Integer.toString(gp.answerArr[i]));
//			choiceBtn[i].setBackground(Color.orange);
//		}
//		checkLabel.setVisible(false);
//		xLabel.setVisible(false);
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		
		if (e.getSource() == choiceBtn[0]) {
			gameNum++;
			choiceBtn[0].setBackground(Color.RED);
			
			if (gp.answer == Integer.parseInt(choiceBtn[0].getText())) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
				
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			
			next();
		}
		if (e.getSource() == choiceBtn[1]) {
			gameNum++;
			choiceBtn[1].setBackground(Color.RED);
			if (gp.answer == Integer.parseInt(choiceBtn[1].getText())) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			next();
		}
		if (e.getSource() == choiceBtn[2]) {
			gameNum++;
			choiceBtn[2].setBackground(Color.RED);
			if (gp.answer == Integer.parseInt(choiceBtn[2].getText())) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			next();
		}
		if (e.getSource() == choiceBtn[3]) {
			gameNum++;
			choiceBtn[3].setBackground(Color.RED);
			if (gp.answer == Integer.parseInt(choiceBtn[3].getText())) {
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
			next();
		}
		
		if(e.getSource() == pauseBtn) {
			int yn = JOptionPane.showConfirmDialog(this, "게임을 종료하시겠습니까? ","확인",JOptionPane.YES_NO_OPTION);
			
			if(yn==0) {
				Controller c = Controller.getController();
				gameNum = 0;
				gametrue = 0;
				c.Viewchange(MainPage);
			}
		}
		
	}
	
	public void next() {
		// 딜레이 1.5초 주고 다음게임 시작
				timer = new Timer(1500, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(gameNum==endGameNum) {
							resultPane.display();
						}else {
							Controller c = Controller.getController();
							c.Viewchange(PlusMinus);					
						}
						timer.stop();
					}
				});
				timer.start();
	}
	
	@Override
	public String toString() {
		return PlusMinus;
	}
}
