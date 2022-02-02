package org.proj.view;

import static org.proj.Resource.FRAME_HEIGHT;
import static org.proj.Resource.FRAME_WIDTH;
import static org.proj.Resource.*;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.proj.RoundJButton;
import org.proj.controller.Controller;


public class RecordView extends GameContainer{
	ImageIcon backgroundImg;
	JLabel background;
	
	JButton pulusminusRecordBtn = new RoundJButton("PlusMinus");
	JButton cardRecordBtn = new RoundJButton("Card");
	JButton ballRecordBtn = new RoundJButton("Ball");
	JButton lifeRecordBtn = new RoundJButton("Life");
	 JButton colorRecordBtn = new RoundJButton("Color");
	JButton backBtn = new RoundJButton("Back");
	
	public RecordView() {
		display();
	}
	
	@Override
	public void display() {
		backgroundImg = new ImageIcon("images/mainBack.png");
		background = new JLabel(backgroundImg);
		
		this.setLayout(null);
		this.add(background);
		
		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		displaySetting();
		
		backBtn.addActionListener(this);
	}
	
	
	public void displaySetting() {
		JLabel title = new JLabel("성적확인");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		background.add(pulusminusRecordBtn);
		background.add(cardRecordBtn);
		background.add(ballRecordBtn);
		background.add(lifeRecordBtn);
		background.add(colorRecordBtn);
		background.add(backBtn);
		background.add(title);
//		pulusminusGameBtn.setBounds(getVisibleRect());
		
		title.setBounds(30, 20, 200,40);
		pulusminusRecordBtn.setBounds(FRAME_WIDTH/2-150/2-380, FRAME_HEIGHT/2-160,150,256);
		cardRecordBtn.setBounds(FRAME_WIDTH/2-150/2-190, FRAME_HEIGHT/2-160,150,256);
		ballRecordBtn.setBounds(FRAME_WIDTH/2-150/2, FRAME_HEIGHT/2-160,150,256);
		lifeRecordBtn.setBounds(FRAME_WIDTH/2-150/2+190, FRAME_HEIGHT/2-160,150,256);
		colorRecordBtn.setBounds(FRAME_WIDTH/2-150/2+380, FRAME_HEIGHT/2-160,150,256);
		
		backBtn.setBounds(30, FRAME_HEIGHT-130,150,80);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			Controller c = Controller.getController();
			c.Viewchange(MAINPAGE);
		}
	}
	
}
