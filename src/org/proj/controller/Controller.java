package org.proj.controller;

import org.proj.model.UserDao;
import org.proj.model.UserDto;
import org.proj.view.MainFrame;
import org.proj.view.MainView;
import org.proj.view.RecordView;

import static org.proj.Resource.*;

import org.proj.game.PlusMinus;

public class Controller {
	private static Controller controller;
	MainFrame mainframe;
	
	public ClientSocket clientsocket;

	UserDao dao;

	public Controller() {
		mainframe = new MainFrame();
		clientsocket = new ClientSocket();

		dao = new UserDao();
	}
	
	public static Controller getController() {
		return controller;
	}
	
	public boolean idcheck(String userID) {
		return dao.checkID(userID);
	}
	
	public boolean signup(UserDto dto) {
		return clientsocket.reqSignUp(dto);
	}
	
	public void login(UserDto dto) {
		clientsocket.reqLogin(dto);
	}
	public void update(UserDto dto) {
		clientsocket.reqUpdate(dto);
		
	}
	public static void main(String[] args) {
		controller = new Controller();
	}

//	public static final String LOGINPAGE = "login";
//	public static final String MAINPAGE = "main";
//	public static final String PLUSMINUS = "plusminus";
//	public static final String CARD = "card";
//	public static final String BALL = "ball";
//	public static final String LIFE = "life";
//	public static final String COLOR = "color";
//	public static final String RECORD = "record";
//	public static final String PlisMinusRECORD = "plusminusRecord";
//	public static final String CardRECORD = "cardRecord";
//	public static final String LifeRECORD = "lifeRecord";
//	public static final String BallRECORD = "ballRecord";
//	public static final String ColorRECORD = "colorRecord";
	
	public void Viewchange(String viewName) {
		switch(viewName) {
		case MAINPAGE :
			mainframe.changeView(new MainView());
			break;
		case RECORD :
			mainframe.changeView(new RecordView());
			break;
		case PLUSMINUS:
			mainframe.changeView(new PlusMinus());
		}
	}
	
}
