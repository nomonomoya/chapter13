import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class Ex6 extends JFrame{
    GamePanel panel;
    Ex6(){
        this.setTitle("버블");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new GamePanel();
        this.add(panel);
        
        this.setLocationRelativeTo(null);
        this.setSize(300,300);
        this.setVisible(true);
        panel.requestFocus();//패널에 포커스를 맞춤
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
                    //클릭 위치를 알아내기
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
        //좌표를 받기
        BalloonThread(int x, int y){
            this.x=x;
            this.y=y;
        }
        
        public void run(){
                JLabel la= new JLabel("Java"); //열기구 이미지 대신 자바
                la.setSize(30,100);
                la.setLocation(x, y);
                la.setOpaque(true);
                panel.add(la);
                
            while(true){
                int x=la.getX();
                int y=la.getY()-5;//5픽셀씩 위로 올라간다.
                
                if(la.getHeight()+30<=0)
                    return;
                else
                    la.setLocation(x, y);
                try{
                    sleep(20); //20ms에 5픽셀이동
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
