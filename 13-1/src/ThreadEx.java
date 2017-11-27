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
			Priority(); //Priority 10�� �����ϱ�
			i++; //���Ϸ��� ����
		}
	}
	public int Priority() {
		return getPriority();
	}
}
public class ThreadEx extends JFrame {
	private JLabel la = new JLabel(" "); //���̺� ����
	class MyKeyListener extends KeyAdapter { //Ű ������ ����
		TimerThread t = new TimerThread(la);
		public void KeyPressed(MouseEvent e) { //Ű�� �ٷ����� �� ����
			t.run();
		}
		
	}
	public ThreadEx() {
		Scanner scanner = new Scanner(System.in);
		setTitle("Thread");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		System.out.print("�ƹ�Ű�� �Է�");
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
