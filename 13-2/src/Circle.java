import javax.swing.*;
import java.awt.*;
class ThreadEx extends Thread{
	Circle c = new Circle();
	@Override
	public void run() {
		while(true) {
			int x = ((int)(Math.random()*200));
			int y = ((int)(Math.random()*170)); //x,y 값 랜덤
			try {
				Thread.sleep(300);
				c.Circle(x,y); //x,y 반환
			}
			catch(InterruptedException e) {return;}
		}
	}
}
public class Circle extends JFrame {
	private MyPanel panel = new MyPanel();
	int x,y;
	public void Circle() {
		setTitle("원그리기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(200,170);
		setVisible(true);
	}
	public void Circle(int x, int y) {
		this.x = x;
		this.y = y; //랜덤값 받기
	}
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.red);
			g.drawOval(x, y, 50, 50); //원 그리기
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Circle();
		new ThreadEx();
	}

}
