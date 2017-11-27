import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RandomThread extends Thread {
	private Container contentPane;
	private boolean flag=false; 
	
	public RandomThread(Container contentPane) { 
		this.contentPane = contentPane;
	}
	
	public void finish() { // ������ ���� ����� flag�� ǥ��
		flag = true;
	} 
	
	@Override
	public void run() {
		while(true) { 
			int x = ((int)(Math.random()*10 + 95));
			int y = ((int)(Math.random()*10 + 95));
			JLabel label = new JLabel("���� ���̺�"); //�� ���̺� ����
			label.setSize(80, 30); // ���̺��� ũ�� 80x30
			label.setLocation(x, y); 
			contentPane.add(label); // ���̺��� ����Ʈ�ҿ� �߰�
			contentPane.repaint(); // ����Ʈ���� �ٽ� �׷� �߰��� ���̺��� ���̰� ��
			try {
				Thread.sleep(300); 
				if(flag==true) {
					contentPane.removeAll(); // ����Ʈ�ҿ� �ִ� ��� ���̺� ����
					label = new JLabel("finish"); 
					label.setSize(80, 30); 
					label.setLocation(200, 200); // ���̺��� ����Ʈ�� ���� ������ ��ġ�� ����
					label.setForeground(Color.RED);
					contentPane.add(label); // ��finish�� ���̺� �ޱ�
					contentPane.repaint(); // ����Ʈ���� �ٽ� �׷� �߰��� ���̺��� ���̰� ��
					return; // ������ ����
				}
			}
			catch(InterruptedException e) { return; }
		}
	}
}

public class ThreadEx extends JFrame {
	private RandomThread th; 
	
	public ThreadEx() {
		setTitle("Thread");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		c.addMouseListener(new MouseAdapter() { // ���콺 ������ ���
			@Override
			public void mousePressed(MouseEvent e) {
				th.finish(); 
				}
		});
		setSize(300,200);
		setVisible(true);

		th = new RandomThread(c); 
		th.start(); // ������ ����
	}

	public static void main(String[] args) {
		new ThreadEx();
	}
}