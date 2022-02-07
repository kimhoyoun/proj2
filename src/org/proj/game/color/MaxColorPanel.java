package org.proj.game.color;

import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.proj.controller.Controller;
import org.proj.view.GameView;


public class MaxColorPanel extends GameView  {
	private ImageIcon bgImg;
	private JLabel bgImgPan;
	
	private ImageIcon bgSK;
	private JLabel bgSKPan;
	
	ImageIcon pauseIcon = new ImageIcon("images/pause.png");
	JButton pauseBtn = new JButton(pauseIcon);
	
	private ImageIcon checkIcon;
	private ImageIcon xIcon;
	private JLabel checkLabel;
	private JLabel xLabel;

	private JPanel colorPan;

	private JButton btn1 = new JButton("btn1");
	private JButton btn2 = new JButton("btn2");
	private JButton btn3 = new JButton("btn3");
	
	
	
	private Color color;
	private EmptyBorder b1;

	private JLabel txtTitle;
	private Font font;
	Timer timer;
	GameHowTo ght;
	MaxColorConsole mcc;
	public MaxColorPanel() {
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		pauseBtn.addActionListener(this);
	}
	@Override
	public void display() {
		mcc = new MaxColorConsole();
		this.setLayout(null);
		
		this.add(resultPane);
		resultPane.setBounds(FRAME_WIDTH/2-300/2, FRAME_HEIGHT/2-350/2, 300, 350);
		resultPane.setVisible(false);
		ght = new GameHowTo();
		
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

		colorPan = new JPanel();
		colorPan.setLayout(new GridLayout(3, 3));
		colorPan.setBounds(100, 150, 300, 200);

		for (int i = 0; i < 9; i++) {
			JPanel b = new JPanel();
			LineBorder b2 = new LineBorder(new Color(248, 248, 248), 1);
			b.setBorder(b2);
			b.setBackground(mcc.col[mcc.arr[i]]);
			colorPan.add(b);
		}
		
		
		color = new Color(0,0,0,0);
		btn1.setForeground(color);
		btn2.setForeground(color);
		btn3.setForeground(color);
		b1 = new EmptyBorder(5, 3, 5, 0);
		btn1.setBorder(b1);
		btn2.setBorder(b1);
		btn3.setBorder(b1);
		btn1.setBounds(500, 160, 100, 50);
		btn2.setBounds(500, 230, 100, 50);
		btn3.setBounds(500, 300, 100, 50);
		btn1.setBackground(new Color(233, 23, 22));
		btn2.setBackground(new Color(81, 107, 254));
		btn3.setBackground(new Color(254, 228, 55));
		
		MyMouseListener listener = new MyMouseListener();
//		btn1.addMouseListener(listener);
//		btn2.addMouseListener(listener);
//		btn3.addMouseListener(listener);
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		txtTitle = new JLabel("가장 많은 색을 선택해주세요");
		font = new Font("맑은 고딕", Font.BOLD, 25);
		txtTitle.setFont(font);
		txtTitle.setForeground(Color.black);
		txtTitle.setBounds(215, 50, 500, 100);
		bgImgPan.add(ght);
		ght.setBounds(100, 100, 820, 530);
		bgSKPan.add(colorPan);
		bgSKPan.add(txtTitle);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);
		bgImgPan.add(bgSKPan);
		
		ght.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btn1.addMouseListener(listener);
				btn2.addMouseListener(listener);
				btn3.addMouseListener(listener);
				btn1.setEnabled(true);
				btn2.setEnabled(true);
				btn3.setEnabled(true);
				
				ght.setVisible(false);
			}
		});
		
		this.add(bgImgPan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JButton btn = (JButton)e.getSource();
//		System.out.println("text => " + btn.getText());
//		System.out.println(btn.equals(btn2));
		if ("btn1".equals(btn.getText())) {
//			System.out.println("btn1");
			if ("RED".equals(mcc.ans)) {
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
		}else if  ("btn2".equals(btn.getText())) {
//			System.out.println("btn2");
			if ("BLUE".equals(mcc.ans)) {
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
		} else if   ("btn3".equals(btn.getText())){
//			System.out.println("btn3");
			if ("YELLOW".equals(mcc.ans)) {
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
		return MaxColor;
	}

}
