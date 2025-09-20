package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class MyFrame extends JFrame implements KeyListener,Runnable {
    //用于存储当前的背景
    private final BackGround nowBg;
    //用于双缓存
    private Image offScreenImage = null;
    //马里奥对象
    private static Mario mario = new Mario();
    //用于判断关卡
    private final int sort;
    File file = new File("Info.txt");

    public MyFrame(int sort) throws IOException {
        //设置窗口的大小
        this.setSize(1200,700);
        //设置窗口居中显示
        this.setLocationRelativeTo(null);
        //设置窗口的可见性
        this.setVisible(true);
        //设置点击窗口上的关闭键,结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小不可变
        this.setResizable(false);
        //向窗口对象添加键盘监听器
        this.addKeyListener(this);
        this.requestFocusInWindow();
        //设置窗口名称
        this.setTitle("从军行");
        //初始化图片
        StaticValue.init();
        //初始化
        if(sort==1){
            mario = new Mario(10,340);
        }else if(sort==2){
            mario = new Mario(10,40);
        }else if(sort==3){
            mario = new Mario(10,440);
        }
        //关卡
        this.sort=sort;
        //将第一个场景设置为当前场景
        nowBg = new BackGround(sort);
        mario.setBackGround(nowBg);
        //绘制图像
        repaint();
        //定义一个线程对象,用于实现马里奥的运动
        Thread thread = new Thread(this);
        thread.start();
    }

    public static Mario getMario(){
        return mario;
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(1200,700);
        }

        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,1200,700);

        //绘制背景
        graphics.drawImage(nowBg.getBgImage(),nowBg.getBgX1(),0,1200,700,this);
        graphics.drawImage(nowBg.getBgImageClone(),nowBg.getBgX2(),0,1200,700,this);
        //绘制敌人
        for (Enemy e : nowBg.getEnemyList()) {
            graphics.drawImage(e.getShow(),e.getX(),e.getY(),this);
        }
        //绘制生命值
        for(int i=0;i<mario.getBlood()/10;i++){
            graphics.drawImage(nowBg.getHp(),i*30+30,50,this);
        }
        //绘制过关条件
        graphics.drawImage(nowBg.getPass(),1000,100,this);
        if(sort==1){
            Color c1 = graphics.getColor();
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("黑体",Font.BOLD,16));
            graphics.drawString("过关条件: " ,1005,130);
            graphics.drawString("杀死所有怪物",1005,150);
            graphics.setColor(c1);
        }else if(sort==2){
            Color c1 = graphics.getColor();
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("黑体",Font.BOLD,16));
            graphics.drawString("过关条件: " ,1005,130);
            graphics.drawString("杀死所有怪物并至少剩余",1005,150);
            graphics.drawString("60%的血量" ,1005,170);
            graphics.setColor(c1);
        }else if(sort==3){
            Color c1 = graphics.getColor();
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("黑体",Font.BOLD,16));
            graphics.drawString("过关条件: " ,1005,130);
            graphics.drawString("杀死所有怪物并至少剩余",1005,150);
            graphics.drawString("60%的血量" ,1005,170);
            graphics.setColor(c1);
        }

        //绘制障碍物
        for (Obstacle ob : nowBg.getObstacleList()) {
            graphics.drawImage(ob.getShow(),ob.getX(),ob.getY(),this);
        }

        //绘制主角
        graphics.drawImage(mario.getShow(),mario.getX(),mario.getY(),this);

        Color c2 = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("黑体",Font.BOLD,25));
        graphics.drawString("当前的生命值为: " + mario.getBlood(),50,100);
        graphics.setColor(c2);
        //将图像绘制到窗口中
        g.drawImage(offScreenImage,0,0,this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //当键盘按下按键时调用
    @Override
    public void keyPressed(KeyEvent e) {
        //向右移动
        if (e.getKeyCode() == 68) {
            mario.rightMove();
            //        mario.getMusic().PlayMusic("走路音效.wav");
        }
        //向左移动
        if (e.getKeyCode() == 65) {
            mario.leftMove();
            //        mario.getMusic().PlayMusic("走路音效.wav");
        }
        //跳跃
        if (e.getKeyCode() == 75) {
            mario.jump();
        }
        //攻击
        if (e.getKeyCode() == 74){
            mario.attack();
            mario.getMusic().PlayMusic("攻击音效.wav");
        }
        if (e.getKeyCode() == 71){
            mario.cutAttackMode();
        }
    }
    //当键盘松开按键时调用
    @Override
    public void keyReleased(KeyEvent e) {
        //向左停止
        if (e.getKeyCode() == 65) {
            mario.setBgM(true);
            mario.setBgM2(true);
            mario.leftStop();
        }
        //向右停止
        if (e.getKeyCode() == 68) {
            mario.setBgM(true);
            mario.setBgM2(true);
            mario.rightStop();
        }
        //
        if(e.getKeyCode() == 74){
            mario.unAttack();
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(50);

                //判断是否死亡
                if (mario.getBlood()<=0 || (mario.getY()>=700 && sort == 2)) {
                    JOptionPane.showMessageDialog(this,"卷耳死亡!!!");
                    System.exit(0);
                }
                //判断游戏目标是否达成
                if(sort==1){
                    if(mario.getKillNum()==mario.getGoal1()){
                        mario.setOK(true);
                    }
                }else if(sort==2){
                    if(mario.getKillNum()==mario.getGoal2() && mario.getBlood()>=24){
                        mario.setOK(true);
                    }
                    if(mario.getBlood()<24){
                        JOptionPane.showMessageDialog(this,"很遗憾，通关失败！");
                        System.exit(0);
                    }
                }else if(sort==3){
                    if(mario.getKillNum()==mario.getGoal3() && mario.getBlood()>=24){
                        mario.setOK(true);
                    }
                    if(mario.getBlood()<24){
                        JOptionPane.showMessageDialog(this,"很遗憾，通关失败！");
                        System.exit(0);
                    }
                }

                //判断游戏是否结束
                if (mario.isOK()) {
                    FileWriter fileWriter=new FileWriter(file.getName(),true);
                    if(sort==1 && file.toString()==null){
                        fileWriter.write("12");
                        fileWriter.close();
                    }else if(sort==2 && file.toString().equals("12")){
                        fileWriter.write("3");
                        fileWriter.close();
                    }
                    JOptionPane.showMessageDialog(this,"恭喜你!成功通关了");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

