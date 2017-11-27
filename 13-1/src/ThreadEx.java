import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

class TimerThread extends Thread {
	private JLabel timerLabel; 
	public TimerThread(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	@Override
	public void run() {
		int i = 0; 
		while(i<10) {
			Priority(); //Priority 10번 실행하기
			i++; //무하루프 방지
		}
	}
	public int Priority() {
		return getPriority();
	}
}
public class ThreadEx extends JFrame {
	private JLabel la = new JLabel(" "); //레이블 생성
	class MyKeyListener extends KeyAdapter { //키 리스너 생성
		TimerThread t = new TimerThread(la);
		public void KeyPressed(MouseEvent e) { //키가 줄려지면 런 실행
			t.run();
		}
		
	}
	public ThreadEx() {
		Scanner scanner = new Scanner(System.in);
		setTitle("Thread");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		System.out.print("아무키나 입력");
		c.addKeyListener(new MyKeyListener());
	
		la.setSize(50,20);
		la.setLocation(30,30);
		c.add(la);
		
		setSize(250,250);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadEx();
	}

}
