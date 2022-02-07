package org.proj.game.cup;

import static org.proj.Resource.MainPage;
import static org.proj.Resource.gameNum;
import static org.proj.Resource.gametrue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.proj.controller.Controller;
import org.proj.view.GameView;

public class CupGamePanel extends GameView {

	 ImageIcon backIcon = new ImageIcon("images/background.png");
	 ImageIcon gameBagIcon = new ImageIcon("images/cupgamebackImg.png");
	 ImageIcon cupIcon = new ImageIcon("images/cup.png");
	 ImageIcon ballIcon = new ImageIcon("images/ball.png");
	 ImageIcon checkIcon = new ImageIcon("images/checked.png");
	 ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	 ImageIcon xIcon = new ImageIcon("images/x.png");

	 JLabel backLabel;
	 JLabel gameBackLabel;
	 JLabel checkLabel;
	 JLabel xLabel;

	 JButton pauseBtn = new JButton(pauseIcon);
	 JButton playBtn = new JButton("시작하기");

	 Timer timer;
	 javax.swing.Timer otherCupTtimer;

	 int startBtn;

	 int click = 0;

	 boolean flag;

	 JLabel[] balls = new JLabel[3];

	 Cup[] cups = new Cup[3];

	public CupGamePanel() {
		cups[0] = new Cup();
		cups[1] = new Cup();
		cups[2] = new Cup();
		
		playBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		cups[0].addActionListener(this);
		cups[1].addActionListener(this);
		cups[2].addActionListener(this);
	}

	@Override
	public void display() {

		this.setLayout(null);
		this.setBounds(0, 0, 1024, 768);

		// 엑스 이미지
		xLabel = new JLabel(xIcon);
		xLabel.setBounds(680, 20, 150, 150);
		xLabel.setVisible(false);

		// 체크 이미지
		checkLabel = new JLabel(checkIcon);
		checkLabel.setBounds(680, 20, 150, 150);
		checkLabel.setVisible(false);

		// 일시정지 버튼
		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);
		pauseBtn.setContentAreaFilled(false);

		// 시작하기 버튼
		playBtn.setBounds(435, 530, 150, 50);
	
		// 컵 생성
		for (int i = 0; i < cups.length; i++) {
			
			this.add(cups[i]);
		}

		cups[0].x = 230;
		cups[1].x = 430;
		cups[2].x = 630;

		// 컵 위치
		cups[0].setBounds(cups[0].x, cups[0].y, cups[0].w, cups[0].h);
		cups[1].setBounds(cups[1].x, cups[1].y, cups[0].w, cups[0].h);
		cups[2].setBounds(cups[2].x, cups[2].y, cups[0].w, cups[0].h);

		// 공 생성
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new JLabel(ballIcon);
			this.add(balls[i]);
			if (!(i == 1)) {
				balls[i].setVisible(false);
			}
		}

		// 공 위치
		balls[0].setBounds(280, 350, 50, 50);
		balls[1].setBounds(480, 350, 50, 50);
		balls[2].setBounds(680, 350, 50, 50);

		// 흰색 배경
		gameBackLabel = new JLabel(gameBagIcon);
		gameBackLabel.setBounds(50, 20, 900, 700);

		// 초록 배경
		backLabel = new JLabel(backIcon);
		backLabel.setBounds(0, 0, 1024, 768);

		this.add(checkLabel);
		this.add(xLabel);
		this.add(pauseBtn);
		this.add(playBtn);
		this.add(gameBackLabel);
		this.add(backLabel);
	}

	// 컵 내리기
	public void cupUpDown() {

		timer = new Timer();

		// 3초 카운트
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 8;

			public void run() {

				if (i > 4) {

					cups[1].y -= 25;

					cups[1].setBounds(cups[1].x, cups[1].y, cups[1].w, cups[1].h);

					i--;

				} else if (i <= 4 && i > 0) {

					cups[1].y += 25;

					cups[1].setBounds(cups[1].x, cups[1].y, cups[1].w, cups[1].h);

					i--;

				} else if (i == 0) {

					timer.cancel();

					balls[1].setVisible(false); // 컵이 내려간 후 공 지우기

					changeCup();
				}

			}
		}, 0, 50);
	}

	// 컵 올리기
	public void cupUp(int index) {

		timer = new Timer();

		// 3초 카운트
		timer.scheduleAtFixedRate(new TimerTask() {

			int i = 3;

			public void run() {

				// 들어오는 인덱스에 따른 컵 올리기
				if (index == 0) {
					cups[0].y -= 25;
					cups[0].setBounds(cups[0].x, cups[0].y, cups[0].w, cups[0].h);
				}

				else if (index == 1) {
					cups[1].y -= 25;
					cups[1].setBounds(cups[1].x, cups[1].y, cups[0].w, cups[0].h);

				}

				else if (index == 2) {
					cups[2].y -= 25;
					cups[2].setBounds(cups[2].x, cups[2].y, cups[0].w, cups[0].h);

				}

				i--;

				if (i < 0) {

					timer.cancel();

					if (index == 0) {
						click++;
					}

					// 1번 컵의 x 위치에 맞는 공 보여주기
					else if (index == 1) {
						click++;
						if (cups[1].getX() == 230) {
							balls[0].setVisible(true);
						} else if (cups[1].getX() == 430) {
							balls[1].setVisible(true);
						} else if (cups[1].getX() == 630) {
							balls[2].setVisible(true);
						}
					}

					else if (index == 2) {
						click++;
					}

				}
			}
		}, 0, 50);
	}

	//
	public void otherCupUp(int index1, int index2) {
		otherCupTtimer = new javax.swing.Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (index1 == 1) {
					if (cups[1].getX() == 230) {
						balls[0].setVisible(true);
					} else if (cups[1].getX() == 430) {
						balls[1].setVisible(true);
					} else if (cups[1].getX() == 630) {
						balls[2].setVisible(true);
					}
				}

				cups[index1].y -= 25;
				cups[index1].setBounds(cups[index1].x, cups[index1].y, cups[index1].w, cups[index1].h);

				cups[index2].y -= 25;
				cups[index2].setBounds(cups[index2].x, cups[index2].y, cups[index2].w, cups[index2].h);

				if (cups[index1].y == 200) {
					otherCupTtimer.stop();
				}

			}
		});
		otherCupTtimer.start();
	}

	// 컵 섞기
	public void changeCup() {

		int r = 100;

		CupRoad cr = new CupRoad();

		cups[0].road = cr.cupRoadArr[0];
		cups[1].road = cr.cupRoadArr[1];
		cups[2].road = cr.cupRoadArr[2];

		CupThread ct = new CupThread(cups[0], cups[1], cups[2], r);

		ct.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(playBtn == e.getSource()) {
			if (startBtn == 1) {
				return;
			}

			cupUpDown();

			startBtn++;
		}

		if (cups[0] == e.getSource()) {
			if (click == 1) {
				return;
			}
			cupUp(0);
			otherCupUp(1, 2);
			if (flag == true) {
				return;
			}
			flag = true;
			xLabel.setVisible(flag);
		} 
		
		if (cups[1] == e.getSource()) {
			if (click == 1) {
				return;
			}
			cupUp(1);
			otherCupUp(0, 2);
			if (flag == true) {
				return;
			}
			flag = true;
			checkLabel.setVisible(flag);
		} 
		
		if (cups[2] == e.getSource()) {
			if (click == 1) {
				return;
			}
			cupUp(2);
			otherCupUp(1, 0);
			if (flag == true) {
				return;
			}
			flag = true;
			xLabel.setVisible(flag);
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

}
