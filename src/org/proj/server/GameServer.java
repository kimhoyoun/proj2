package org.proj.server;

import static org.proj.Resource.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.proj.model.GameDataDto;
import org.proj.model.UserDao;
import org.proj.model.UserDto;

public class GameServer extends JFrame {
//	public static final String LOGIN = "login";
//	public static final String NEWLOGIN = "newlogin";
//	public static final String SIGNUP = "signup";
//	public static final String IDCHECK = "idcheck";
//	public static final String LOGOUT = "logout";
	
	public static UserDao dao = new UserDao();
	public static UserDto dto;
	public static Vector<GameDataDto> vector;
	private JTextArea serverState;
	private ConnectClient cc;

	public GameServer() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("서버");
		setSize(300, 400);

		Container contentPane = getContentPane();
		serverState = new JTextArea();
		serverState.setEditable(false);
		contentPane.add(new JScrollPane(serverState), BorderLayout.CENTER);
		setVisible(true);
		this.setLocation(500, 200);

		// 서버 쓰레드 동작
		ConnectClient connect = new ConnectClient();
		connect.start();
	}

	public static void main(String[] args) {
		new GameServer();
	}

	class ConnectClient extends Thread {
		private ServerSocket listener = null;
		private Socket socket = null;

		@Override
		public void run() {
			try {
				// 서버는 한번만 생성
				listener = new ServerSocket(9999);
				serverState.append("접속대기중...\n");
				while (true) {
					// 접속은 계속 유지해야되므로 wile문 안에서 무한반복
					socket = listener.accept();
					serverState.append("클라이언트 접속 완료...\n");

					// 클라이언트가 서버에 접속 시 해당 클라이언트와 메세지를 주고 받을 쓰레드 생성
					ServerThread sth = new ServerThread(socket);
					sth.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class ServerThread extends Thread {
		Socket socket = null;
		private BufferedReader br = null;
		private BufferedWriter bw = null;
		private String userID;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private String guest = "guest" + (int) (Math.random() * 100 + 1);
		private InputStream is;
		private OutputStream os;
		// 생성자에 socket을 받아 서버에 접속한 클라이언트의 socket정보를 받아옴.
		public ServerThread(Socket socket) {
			this.socket = socket;
			try {
				// 클라이언트와 메세지를 주고받기위해 입출력 스트림 생성
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 쓰레드가 시작되면 반복할 코드
		@Override
		public void run() {
			while (true) {
				try {
					// 클라이언트로부터 메세지를 받을때까지 대기하면 받으면 msg 변수에 저장
					String msg = ois.readUTF();
					serverState.append(guest + " >> " + msg + "\n");
					// 중복검사,회원가입, 로그인, 종료
					switch (msg) {
					case LOGIN:
						login();
						break;
					case SIGNUP:
						userSignUp();
						break;
					case IDCHECK:
						idCheck();
						break;
					case LOGOUT:
						logout();
						break;
					case UserUPDATE:
						userUpdate();
						break;
					}

				} catch (IOException e) {
					e.printStackTrace();
				} 

			}
		}

		private void userUpdate() {
			try {
				dto = (UserDto)ois.readObject();
				boolean approval = dao.updateUser(dto);
				
				if(approval) {
					oos.writeUTF(UserUPDATE);
					oos.flush();
					oos.writeUTF("complete");
					oos.flush();
					serverState.append(guest + " >> Update Complete "+"\n");
				}
				else {
					oos.writeUTF(UserUPDATE);
					oos.flush();
					oos.writeUTF("fail");
					oos.flush();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void login() {
			try {
				dto = (UserDto)ois.readObject();
				boolean approval = dao.loginApproval(dto);
				
				if (approval) {
						dto = dao.selectOneUser(dto);
						vector = dao.roadOneGameData(dto);
						
					if (dto != null) {
						if(vector.size()!=0) {
							oos.writeUTF(LOGIN);
							oos.flush();
							oos.writeObject(dto);
							oos.flush();
							oos.writeObject(vector);
							oos.flush();
							serverState.append(guest + " >> Login Success! \n");
						}
						else {
							oos.writeUTF(NEWLOGIN);
							oos.flush();
							oos.writeObject(dto);
							oos.flush();
							serverState.append(guest + " >> Login Success! (No Data) \n");
						}
					}
				} else {
					dto = new UserDto(-1,null,null,null,0);
					oos.writeUTF(LOGIN);
					oos.flush();
					oos.writeObject(dto);
					oos.flush();
					serverState.append(guest + " >> Login Fail \n");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		private void userSignUp() {
			try {
				dto = (UserDto) ois.readObject();
				System.out.println(dto);
				boolean state = dao.insertUser(dto);
				
//				boolean state2 = dao.insertGameData(new GameDataDto(dto.getId(), 0,0,0,0,0,0,0,0,0,0,""));
				if (state) {
					oos.writeUTF(SIGNUP);
					oos.flush();
					oos.writeUTF("complete");
					oos.flush();
					serverState.append(guest + " >> Sign Up Complete "+"\n");
				} else {
					oos.writeUTF(SIGNUP);
					oos.flush();
					oos.writeUTF("fail");
					oos.flush();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void idCheck() {
			try {
				// userID를 넘겨 받아서 userID에 저장
				String userID = ois.readUTF();
				// swing 출력
				serverState.append(guest + " idckek>> "+userID + "\n");
				// dao에 그 값을 넣고 id 중복여부 확인하고 결과를 check에 받음 true : id사용가능, false : id 중복
				boolean check = dao.checkID(userID);
				if (check) {
					serverState.append(guest + " >> true\n");
					// idcheck 요청의 결과임을 알려주는 메세지
					oos.writeUTF(IDCHECK);
					oos.flush();
					// idcheck 요청의 결과값
					oos.writeUTF("approval");
					oos.flush();
					serverState.append(guest + " >> ID Check Complete"+"\n");
				} else {
					serverState.append(guest + " >> false" +"\n");
					// idcheck 요청의 결과임을 알려주는 메세지
					oos.writeUTF(IDCHECK);
					oos.flush();
					// idcheck 요청의 결과값
					oos.writeUTF("fail");
					oos.flush();
					serverState.append(guest + " >> ID Check Complete"+"\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		private void logout() {
		}

	}

}
