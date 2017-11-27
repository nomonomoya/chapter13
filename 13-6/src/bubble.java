import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Ex6 extends JFrame{
    GamePanel panel;
    Ex6(){
        this.setTitle("����");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new GamePanel();
        this.add(panel);
        
        this.setLocationRelativeTo(null);
        this.setSize(300,300);
        this.setVisible(true);
        panel.requestFocus();//�гο� ��Ŀ���� ����
    }
    
    class GamePanel extends JPanel{
        GamePanel(){
            this.setLayout(null);
            this.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent me) {
                }

                @Override
                public void mousePressed(MouseEvent me) {
                    //Ŭ�� ��ġ�� �˾Ƴ���
                    int x=me.getX();
                    int y=me.getY();
                    BalloonThread bt= new BalloonThread(x,y);
                    bt.start();
                }

                @Override
                public void mouseReleased(MouseEvent me) {}
                @Override
                public void mouseEntered(MouseEvent me) {}
                @Override
                public void mouseExited(MouseEvent me) {}
            });
        }
    }
    class BalloonThread extends Thread{
        int x,y;
        //��ǥ�� �ޱ�
        BalloonThread(int x, int y){
            this.x=x;
            this.y=y;
        }
        
        public void run(){
                JLabel la= new JLabel("Java"); //���ⱸ �̹��� ��� �ڹ�
                la.setSize(30,100);
                la.setLocation(x, y);
                la.setOpaque(true);
                panel.add(la);
                
            while(true){
                int x=la.getX();
                int y=la.getY()-5;//5�ȼ��� ���� �ö󰣴�.
                
                if(la.getHeight()+30<=0)
                    return;
                else
                    la.setLocation(x, y);
                try{
                    sleep(20); //20ms�� 5�ȼ��̵�
                }
                catch(Exception e){
                    return;
                }
            }
        }
    }
}

public class bubble {
    public static void main(String[] args) {
        new Ex6();
    }
}
