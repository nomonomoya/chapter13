import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;

class Ex5 extends JFrame{
    Ex5(){
        this.setTitle("사격 게임");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GamePanel p = new GamePanel();
        this.add(p);
        
        this.setLocationRelativeTo(null);
        this.setSize(300,300);
        this.setResizable(false);
        this.setVisible(true);
        p.startGame();
    }
}

class GamePanel extends JPanel{
    TargetThread targetThread;
    JLabel base = new JLabel();
    JLabel bullet = new JLabel();
    JLabel target;
    
    
    GamePanel(){
        this.setLayout(null);
        base.setSize(40,40);
        base.setOpaque(true);
        base.setBackground(Color.black);
        
        target = new JLabel("Java"); //이미지 없어서 글자로 대체
        target.setSize(30,100);
        
        bullet.setSize(10,10);
        bullet.setOpaque(true);
        bullet.setBackground(Color.red);
        this.add(base);
        this.add(target);
        this.add(bullet);
    }
    public void startGame(){
        base.setLocation(this.getWidth()/2-20, this.getHeight()-40); //크기
        bullet.setLocation(this.getWidth()/2-5, this.getHeight()-50);
        target.setLocation(0, 0);
        
        targetThread = new TargetThread(target);
        targetThread.start();
        
        base.requestFocus();
        base.addKeyListener(new KeyListener(){
            BulletThread bulletThread = null;
        
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyChar()==KeyEvent.VK_ENTER){ //이미 맞췄는지
                    if(bulletThread==null || !bulletThread.isAlive()){
                        bulletThread = new BulletThread(bullet,target,targetThread);
                        bulletThread.start(); //타겟 확인
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
            
        });
    }
    
    class TargetThread extends Thread{
        JLabel target;
        TargetThread(JLabel target){
            this.target=target;
            target.setLocation(0, 0);
        }
        public void run(){
            while(true){
                int x=target.getX()+5;//5픽셀씩 이동
                int y=target.getY();
                if(x>GamePanel.this.getWidth())
                    target.setLocation(0, 0);
                else
                    target.setLocation(x, y);
                try{
                    sleep(20); //20ms당 5픽셀
                }
                catch(Exception e){
                    target.setLocation(0, 0);
                    try{
                        sleep(500);
                    }
                    catch(Exception e1){}
                }
            }
        }
    }
    
    class BulletThread extends Thread{
        JLabel bullet,target;
        Thread targetThread;
        
        public BulletThread(JLabel bullet, JLabel target, Thread targetThread){
            this.bullet=bullet;
            this.target=target;
            this.targetThread=targetThread;
        }
        
        public void run(){
            while(true){
                if(hit()){//타겟이 맞았다면
                    targetThread.interrupt();
                    bullet.setLocation(bullet.getParent().getWidth()/2-5, bullet.getParent().getHeight()-50);
                    return;
                }
                else{
                    int x=bullet.getX();
                    int y=bullet.getY()-5;if(y<0){
                       
                        bullet.setLocation(bullet.getParent().getWidth()/2-5, bullet.getParent().getHeight()-50);
                        return;
                    }
                    else
                        bullet.setLocation(x, y);
                }
                //0.02초마다 5픽셀씩 이동
                try{
                    sleep(20);
                }
                
                catch(Exception e){}
            }
        }
        
        private boolean hit(){
            int x=bullet.getX();
            int y=bullet.getY();
            int w=bullet.getWidth();
            int h=bullet.getHeight();
            
            if(targetContains(x,y)||targetContains(x+w-1,y)||targetContains(x+w-1,y+h-1)||targetContains(x,y+h-1))
                return true;
            else
                return false;
        }
        private boolean targetContains(int x, int y){ //타겟 좌표 확인
            if(((target.getX()<=x)&&(x<target.getX()+target.getWidth()))   
                    &&((target.getY()<=y)&&(y<target.getY()+target.getHeight())))
                return true;
            
            else
                return false;
        }
    }
}
public class Gun {
    public static void main(String[] args) {
        new Ex5();
    }
}

