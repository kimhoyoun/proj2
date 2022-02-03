package org.proj.view;

import static org.proj.Resource.FRAME_HEIGHT;
import static org.proj.Resource.FRAME_WIDTH;
import static org.proj.Resource.mainUser;
import static org.proj.Resource.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.proj.RoundJButton;
import org.proj.controller.Controller;
import org.proj.model.UserDto;

public class MainView extends GameView{
	ImageIcon backgroundImg;
	JLabel background;
	JButton settingBtn = new JButton("!");
	JButton updateBtn = new JButton("!");
	JButton profileCancelBtn = new JButton("돌아가기");
	boolean updateBtnStatedb = false;
	
	JPanel profile = new JPanel();
	
	JPanel profileUpdate;
	
	JButton pulusminusGameBtn = new RoundJButton("PlusMinus");
	JButton cardGameBtn = new RoundJButton("Card");
	JButton ballGameBtn = new RoundJButton("Ball");
	JButton lifeGameBtn = new RoundJButton("Life");
	JButton colorGameBtn = new RoundJButton("Color");
	JButton recordBtn = new RoundJButton("Record");
	JButton exitBtn = new RoundJButton("Exit");
	
JButton profileOkBtn = new JButton("수정");
	String userIddb;
	String userNamedb;
	String userPassdb;
	int userAgedb; 
	JLabel idtagdb;
	JLabel infotagdb;
	
	JTextField textname = new JTextField(20);
	JTextField textid = new JTextField(15);
	JTextField textpass = new JTextField(15);
	JTextField textage = new JTextField(15);
	
	public MainView() {
		recordBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		pulusminusGameBtn.addActionListener(this);
		settingBtn.addActionListener(this);
		profileOkBtn.addActionListener(this);
		profileCancelBtn.addActionListener(this);
		updateBtn.addActionListener(this);
	}
	
	@Override
	public void display() {
		
		backgroundImg = new ImageIcon("images/mainBack.png");
		background = new JLabel(backgroundImg);
		
		this.setLayout(null);
		this.add(background);
		
		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		displaySetting();
		
//		recordBtn.addActionListener(this);
//		exitBtn.addActionListener(this);
//		pulusminusGameBtn.addActionListener(this);
	}
	
	public void displaySetting() {
		setUpdateBox();
		JLabel title = new JLabel("메인화면");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 40));

		idtagdb.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		infotagdb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		profile.setLayout(null);
		
		profile.add(idtagdb);
		profile.add(infotagdb);
		profile.add(settingBtn);
		idtagdb.setBounds(75,5,150,40);
		infotagdb.setBounds(25,35,170,40);
		settingBtn.setBounds(160,10,30,30);
		
		background.add(profileUpdate);
		background.add(profile);
		background.add(pulusminusGameBtn);
		background.add(cardGameBtn);
		background.add(ballGameBtn);
		background.add(lifeGameBtn);
		background.add(colorGameBtn);
		background.add(recordBtn);
		background.add(exitBtn);
		background.add(title);
		
		title.setBounds(30, 20, 200,40);
		profile.setBounds(FRAME_WIDTH/2-100, 10, 200, 80);
		profileUpdate.setBounds(FRAME_WIDTH/2-300/2, FRAME_HEIGHT/2-350/2, 300, 350);
		profileUpdate.setVisible(false);
		
		pulusminusGameBtn.setBounds(FRAME_WIDTH/2-150/2-380, FRAME_HEIGHT/2-160,150,256);
		cardGameBtn.setBounds(FRAME_WIDTH/2-150/2-190, FRAME_HEIGHT/2-160,150,256);
		ballGameBtn.setBounds(FRAME_WIDTH/2-150/2, FRAME_HEIGHT/2-160,150,256);
		lifeGameBtn.setBounds(FRAME_WIDTH/2-150/2+190, FRAME_HEIGHT/2-160,150,256);
		colorGameBtn.setBounds(FRAME_WIDTH/2-150/2+380, FRAME_HEIGHT/2-160,150,256);
		
		recordBtn.setBounds(FRAME_WIDTH-180, 10,150,80);
		exitBtn.setBounds(FRAME_WIDTH-180, FRAME_HEIGHT-130,150,80);
		
//		settingBtn.addActionListener(this);
//		profileOkBtn.addActionListener(this);
//		profileCancelBtn.addActionListener(this);
//		updateBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == settingBtn) {
			
			pulusminusGameBtn.setEnabled(false);
			cardGameBtn.setEnabled(false);
			ballGameBtn.setEnabled(false);
			lifeGameBtn.setEnabled(false);
			colorGameBtn.setEnabled(false);
			recordBtn.setEnabled(false);
			exitBtn.setEnabled(false);
			settingBtn.setEnabled(false);
			
			textname.setText(userNamedb);
			textid.setText(userIddb);
			textpass.setText(userPassdb);
			textage.setText(userAgedb+"");
			
			profileUpdate.setVisible(true);
			
			revalidate();
			repaint();
		}
		
		if(e.getSource() == updateBtn) {
			if(!updateBtnStatedb) {
				textname.setEditable(true);
				textpass.setEditable(true);
				textage.setEditable(true);
				updateBtnStatedb = true;
			}else {
				textname.setEditable(false);
				textpass.setEditable(false);
				textage.setEditable(false);
				updateBtnStatedb = false;
			}
		}
		
		if(e.getSource() == profileOkBtn) {
			int yn = JOptionPane.showConfirmDialog(this, "프로필을 수정하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
				
			if(yn == 0) {
				userNamedb = textname.getText();
				userPassdb = textpass.getText();
				try {
					userAgedb = Integer.parseInt(textage.getText());
				}catch(NumberFormatException e0) {
					JOptionPane.showMessageDialog(this, "나이는 숫자만 입력하세요!");
					textage.setText(userAgedb+"");
					return;
				}
				if(userNamedb.length()!=0 && userPassdb.length()!=0) {
					
				infotagdb.setText("이름 : "+userNamedb+" 나이 : "+userAgedb);
				textname.setEditable(false);
				textpass.setEditable(false);
				textage.setEditable(false);
				updateBtnStatedb = false;

				UserDto user = new UserDto(0,userNamedb,userIddb,userPassdb,userAgedb);
				Controller c = Controller.getController();
				c.update(user);
				}else {
					JOptionPane.showMessageDialog(this, "내용을 전부 입력해주세요");
				}
			}
		}
		
		if(e.getSource() == profileCancelBtn) {
			pulusminusGameBtn.setEnabled(true);
			cardGameBtn.setEnabled(true);
			ballGameBtn.setEnabled(true);
			lifeGameBtn.setEnabled(true);
			colorGameBtn.setEnabled(true);
			recordBtn.setEnabled(true);
			exitBtn.setEnabled(true);
			settingBtn.setEnabled(true);
			
			profileUpdate.setVisible(false);
			
			revalidate();
			repaint();
		}
		
		if(e.getSource() == exitBtn) {
			int yn = JOptionPane.showConfirmDialog(this, "로그아웃 하시겠습니까?","확인",JOptionPane.YES_NO_OPTION);
			if(yn == 0) {
				Controller c = Controller.getController();
				
				c.logout(mainGameData);
				
				mainUser = null;
				mainGameData = null;
				mainData = null;
				
				
				c.Viewchange(LoginPage);
			}
		}
		
		
		if(e.getSource() == recordBtn) {
			Controller c = Controller.getController();
			c.Viewchange(RECORD);
		}
		
		if(e.getSource() == pulusminusGameBtn) {
			Controller c = Controller.getController();
			c.Viewchange(PlusMinus);
		}
	}
	public void setUpdateBox() {
		profileUpdate = new JPanel();
		
		userIddb = mainUser.getId();
		userNamedb = mainUser.getName();
		userPassdb = mainUser.getPassword();
		userAgedb = mainUser.getAge(); 
		
		idtagdb = new JLabel(userIddb);
		infotagdb = new JLabel("이름 : "+userNamedb+" 나이 : "+userAgedb);
		
		JLabel newUserTitle = new JLabel("프로필");
		JLabel lblName = new JLabel("이름 : ");
		JLabel lblId = new JLabel("아이디 : ");
		JLabel lblPass = new JLabel("비밀번호 : ");
		JLabel lblAge = new JLabel("나이 : ");
		
		profileUpdate.setLayout(null);
		profileUpdate.setBackground(new Color(255,255,255,255));
		
		profileUpdate.add(newUserTitle);
		profileUpdate.add(lblName);
		profileUpdate.add(lblId);
		profileUpdate.add(lblPass);
		profileUpdate.add(lblAge);
		
		newUserTitle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblName.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblPass.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		lblAge.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		
		newUserTitle.setBounds(110,10,100,40);
		lblName.setBounds(48,100, 60, 20);
		lblId.setBounds(28,140, 80, 20);
		lblPass.setBounds(10,180, 100, 20);
		lblAge.setBounds(48,220, 60, 20);

		profileUpdate.add(textname);
		profileUpdate.add(textid);
		profileUpdate.add(textpass);
		profileUpdate.add(textage);
		profileUpdate.add(profileOkBtn);
		profileUpdate.add(profileCancelBtn);
		profileUpdate.add(updateBtn);
		
		textname.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textid.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textpass.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		textage.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		textname.setText(userNamedb);
		textid.setText(userIddb);
		textpass.setText(userPassdb);
		textage.setText(userAgedb+"");

		textname.setEditable(false);
		textid.setEditable(false);
		textpass.setEditable(false);
		textage.setEditable(false);
		
		textname.setBounds(100,100, 120, 25);
		textid.setBounds(100,140, 120, 25);
		textpass.setBounds(100,180, 120, 25);
		textage.setBounds(100,220, 120, 25);
		
		updateBtn.setBounds(250,10,40,40);
		profileOkBtn.setBounds(10,290,120,50);
		profileCancelBtn.setBounds(160,290,120,50);
	}
}
