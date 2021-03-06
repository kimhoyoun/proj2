package org.proj.game.color;

import java.awt.Color;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.JLabel;

public class SelectColorConsole {
	HashMap<String, Color> map = new HashMap<>();
	// 글자에 입힐 색깔(스윙)
	Color[] col = { new Color(233,23,22), new Color(255,127,0), new Color(254,228,55), new Color(33,139,34),
			new Color(81,107,254), new Color(255,126,250), new Color(168,64,255)};
	
	// 색깔의 배열
	 int[] arrBtn = new int[3]; // 버튼의 배열
	 int[] arrTxt = new int[7]; // 글자의 배열

	  int ansColor; // 정답(글자와 버튼색이 같음)
	  int otherColor; // 정답이 아닌 버튼색깔
	  int ansLoc; // 정답위치(정답위치가 일정함을 방지하기위해서)
	  int paintColor; // 글자색
	
	  String ansText;
	  
	public SelectColorConsole() {
		run();
	}
	
	// 콘솔창에 찍히는 부분으로 필요없어서 주석처리
	// 숫자를 글자로 바꾸기위한 변수
	static int numToKor;
	static int numPaint;
//	static Scanner scan = new Scanner(System.in);

	// 글자 함수
	// 글자(=정답)를 정해주기위한 함수이다
	public String colorTxt() {
		for (int i = 0; i < 1; i++) {
			arrTxt[i] = (int) (Math.random() * 7);
//			System.out.println("arrTxt[i] " + arrTxt[i]);
//			ansColor = arrTxt[i];
			ansColor = ((int) (Math.random() * 10000000)%7);
		}
		
		// 콘솔창에 찍히는 부분으로 필요없어서 주석처리
		// 숫자를 글자로(한국어)
//		System.out.print("글자 : ");
		numToKor = ansColor;
		numToColorKor(numToKor);
		return numToColorKor(numToKor);
	}
	
	// 글자의 색 함수
	public Color paintTxt() {
		while(true) {
			paintColor = (int)(Math.random() * 7); // 컬러 수여서 변동가능
			if(ansColor == paintColor) {
				continue;
			} else {
				break;
			}
		}
		numPaint = paintColor;
		return col[numPaint];
		
		// 콘솔창에 찍히는 부분으로 필요없어서 주석처리
		// 숫자를 글자로
//		System.out.println(paintColor);
//		System.out.print("글자 색 : ");
//		numToKor = paintColor;
//		numToColor();
	}

	// 위치선정 함수
	// 글자함수에서 나온 정답의 위치를 정해주기위한 함수이다
	public void location() {
		ansLoc = (int) (Math.random() * 3); // 저장위치 랜덤 돌리기
//		System.out.println("ansLoc : " + ansLoc); // 저장위치 확인 용도
		System.out.println("정답위치 : " + ansLoc);

		arrBtn[ansLoc] = ansColor;
	}

	// 버튼 2개의 색 랜덤으로 돌리기
	// 정답과 오답2개를 받기위한 함수이다
	public void button() {
		for (int i = 0; i < arrBtn.length; i++) {
			// 정답을 제외한 버튼의 색깔지정하는 부분
			// 정답과 같으면 다시 위로 올라가서 for문을 돌아라
			if (i == ansLoc) { 
				continue;
			}

			otherColor = (int) (Math.random() * 7);

			// 랜덤수와 정답이 같으면 다시 위로 올라가서 for문을 돌아라
			if (ansColor == otherColor) { 
				i--;
				continue;
			}
			arrBtn[i] = otherColor;
			// 값이 나오면 그것에 대한 중복체크해주기
			for (int j = 0; j < i; j++) { // j < i => i = 전에 뽑은 랜덤수, j = 지금 뽑은 랜덤수
				if (arrBtn[j] == arrBtn[i]) {
					i--;
					break;
				}
			}
//			numToKor = otherColor;
//			numToColor();
		}

		// 콘솔창에 찍히는 부분으로 필요없어서 주석처리
		// 결정된 버튼을 출력해주기
		// 숫자를 글자로
//		System.out.print("보기 : ");
//		for (int i = 0; i < arrBtn.length; i++) {
//			System.out.print(arrBtn[i] + ":");
//			numToKor = arrBtn[i];
//			numToColor();
//		}
	}
	
	// 숫자를 글자(영어)로 바꾸는 함수
//	public String numToColor() {
//		if(num == 0) {
//			return "RED";
//		} else if(num == 1) {
//			return "ORANGE";
//		} else if(num == 2) {
//			return "YELLOW";
//		} else if(num == 3) {
//			return "GREEN";
//		} else if(num == 4) {
//			return "BLUE";
//		} else if(num == 5) {
//			return "PINK";
//		} else  {
//			return "MAGENTA";
//		}
//	}
	
	// 숫자를 글자(한국어)로 바꾸는 함수
	public String numToColorKor(int numToKor) {
		if(numToKor == 0) {
			return "빨간색";
		} else if(numToKor == 1) {
			return "주황색";
		} else if(numToKor == 2) {
			return "노란색";
		} else if(numToKor == 3) {
			return "초록색";
		} else if(numToKor == 4) {
			return "파란색";
		} else if(numToKor == 5) {
			return "분홍색";
		} else  {
			return "보라색";
		} 
	}

	public void run() {
		// 글자가 실행되는 부분 - 하나만 나와 중복제거가 필요없다
		colorTxt();
		
		// 글자색이 실행되는 부분
		paintTxt();

		// 저장위치 선정
		location();
		
		// 버튼이 실행되는 부분
		button();
		
		// 콘솔창에 찍히는 부분으로 필요없어서 주석처리
		// 정답 받기
//		System.out.print("정답 : ");
//		int answer = scan.nextInt();
		
		// 콘솔창 확인용
		// 우리는 swing으로 마우스 이벤트로 정답을 고르기 때문에 별로 신경 안써도된당~
//		if(answer == ansLoc) {
//			System.out.println("정답입니다~!!");
//		} else {
//			System.out.println("오답입니다ㅜ");
//		}
	}
	
}
