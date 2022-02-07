package org.proj.game.color;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.proj.controller.Controller;
import org.proj.view.GameView;


public class SelectColorPanel extends GameView {
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	private ImageIcon bgSK;
	private JLabel bgSKPan;
	
	ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	JButton pauseBtn = new JButton(pauseIcon);
	
	 JButton btn1;
	 JButton btn2;
	 JButton btn3;
	
	private Color color;
	private EmptyBorder b1;
	
	private ImageIcon checkIcon;
	private ImageIcon xIcon;

	private Font font1;
	private Font font2;
	private JLabel txtTitle;
	private JLabel txtColor;

	private JLabel checkLabel;
	private JLabel xLabel;
	Timer timer;
	private int w = 720;
	private int h = 425;
	private int x = (int) (w / 2);
	private int y = (int) (h / 2);

	SelectColorConsole scc;
	public SelectColorPanel() {
		pauseBtn.addActionListener(this);
//		btn1.addActionListener(this);
//		btn2.addActionListener(this);
//		btn3.addActionListener(this);
	}
	
	
	@Override
	public void display() {
		scc = new SelectColorConsole();
		this.setLayout(null);

		this.add(resultPane);
		resultPane.setBounds(FRAME_WIDTH/2-300/2, FRAME_HEIGHT/2-350/2, 300, 350);
		resultPane.setVisible(false);
		
		pauseBtn.setBounds(920, 30, 50, 50);
		pauseBtn.setBorderPainted(false);    // 버튼의 외곽 투명하게 
		pauseBtn.setContentAreaFilled(false);  // 만들어 주는 것
		this.add(pauseBtn);
		
		
		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		bgSK = new ImageIcon("images/sk.png");
		bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(150, 150, 720, 425);

		 btn1 = new JButton("btn1");
		  btn2 = new JButton("btn2");
		  btn3 = new JButton("btn3");
		btn1.setFocusPainted(false);
		btn2.setFocusPainted(false);
		btn3.setFocusPainted(false);
		color = new Color(0,0,0,0);
		btn1.setForeground(color);
		btn2.setForeground(color);
		btn3.setForeground(color);
		b1 = new EmptyBorder(5, 3, 5, 0);
		btn1.setBorder(b1);
		btn2.setBorder(b1);
		btn3.setBorder(b1);
		btn1.setBounds(100, 200, 115, 150);
		btn2.setBounds(303, 200, 115, 150);
		btn3.setBounds(503, 200, 115, 150);
		btn1.setBackground(scc.col[scc.arrBtn[0]]);
		btn2.setBackground(scc.col[scc.arrBtn[1]]);
		btn3.setBackground(scc.col[scc.arrBtn[2]]);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		MyMouseListener listener = new MyMouseListener();
		btn1.addMouseListener(listener);
		btn2.addMouseListener(listener);
		btn3.addMouseListener(listener);
//		btnSet();
		
		checkIcon = new ImageIcon("images/o.png");
		checkLabel = new JLabel(checkIcon);
		xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);

		checkLabel.setBounds(670, 65, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(670, 65, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);
//		checkIconSet();
		
		if (scc.ansColor == 0) {
			txtColor = new JLabel("빨간색");
		} else if (scc.ansColor == 1) {
			txtColor = new JLabel("주황색");
		} else if (scc.ansColor == 2) {
			txtColor = new JLabel("노란색");
		} else if (scc.ansColor == 3) {
			txtColor = new JLabel("초록색");
		} else if (scc.ansColor == 4) {
			txtColor = new JLabel("파란색");
		} else if (scc.ansColor == 5) {
			txtColor = new JLabel("분홍색");
		} else {
			txtColor = new JLabel("보라색");
		}
		font1 = new Font("맑은 고딕", Font.BOLD, 44);
		txtColor.setFont(font1);
		txtColor.setForeground(scc.paintTxt());
		txtColor.setBounds(300, 75, 500, 100);

		txtTitle = new JLabel("알맞은 색을 선택해주세요");
		font2 = new Font("맑은 고딕", Font.BOLD, 25);
		txtTitle.setFont(font2);
		txtTitle.setForeground(Color.black);
		txtTitle.setBounds(220, 25, 500, 100);
//		answerSet();

		bgSKPan.add(txtTitle);
		bgSKPan.add(txtColor);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);
		
		bgImgPan.add(bgSKPan);
		this.add(bgImgPan);
	}
	
	
	private void answerSet() {
		
	}


	private void checkIconSet() {
	
	}


	public void btnSet() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if ("btn1".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[0]) {
				gameNum++;
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				gameNum++;
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
//		if (e.getSource() == btn1) {
//			if (scc.ansColor == scc.arrBtn[0]) {
//				gameNum++;
//				gametrue++;
//				checkLabel.setVisible(true);
//				revalidate();
//				repaint();
//			} else {
//				gameNum++;
//				xLabel.setVisible(true);
//				revalidate();
//				repaint();
//			}
//		}
		if ("btn2".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[1]) {
				gameNum++;
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				gameNum++;
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}

		if ("btn3".equals(btn.getText())) {
			if (scc.ansColor == scc.arrBtn[2]) {
				gameNum++;
				gametrue++;
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
				gameNum++;
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
		
		next();
		
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
						checkLabel.setVisible(false);
						xLabel.setVisible(false);
						if(gameNum==endGameNum) {
							resultPane.display();
						}else {
							Controller c = Controller.getController();
							int n = (int)((Math.random()*100000)%2);
							
							if(n == 0) {
								c.Viewchange(SelectColor);
							}
							else {
								c.Viewchange(MaxColor);
							}				
						}
						timer.stop();
					}
				});
				timer.start();
	}

	class MyMouseListener implements MouseListener {
		@Override
		public void mouseEntered(MouseEvent e) {
			btn1 = (JButton) e.getSource();
			btn1.setBorder(new LineBorder(Color.black, 2));
			btn2 = (JButton) e.getSource();
			btn2.setBorder(new LineBorder(Color.black, 2));
			btn3 = (JButton) e.getSource();
			btn3.setBorder(new LineBorder(Color.black, 2));
		}
		
		@Override 
		public void mouseExited(MouseEvent e) {
			btn1 = (JButton) e.getSource();
			btn1.setBorder(new LineBorder(Color.black, 0));
			btn2 = (JButton) e.getSource();
			btn2.setBorder(new LineBorder(Color.black, 0));
			btn3 = (JButton) e.getSource();
			btn3.setBorder(new LineBorder(Color.black, 0));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}
	@Override
	public String toString() {
		return SelectColor;
	}
}
