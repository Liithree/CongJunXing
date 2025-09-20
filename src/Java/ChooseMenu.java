package Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class ChooseMenu extends JFrame implements MouseListener {
    //用于双缓存
    private Image offScreenImage = null;
    private final int sort;

    public ChooseMenu(int sort) {
        //设置窗口的大小
        this.setSize(1200,700);
        //设置窗口居中显示
        this.setLocationRelativeTo(null);
        //设置窗口的可见性
        this.setVisible(true);
        //设置点击窗口上的关闭键,结束程序
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置窗口大小不可变
        this.setResizable(false);
        //向窗口对象添加鼠标监听器
        this.addMouseListener(this);
        this.requestFocusInWindow();
        //设置窗口名称
        this.setTitle("从军行");
        //判断游戏进度
        this.sort=sort;
        //初始化图片
        StaticValue.init();
        Music music = new Music(true);
        music.PlayMusic("Age_of_Empires_II_Main_Theme-Todd_Masten_Semitone_Media_Group-79841911.wav");
        repaint();
    }

    public static void main(String[] args) throws IOException {
        File file=new File("Info.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("Info.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String pp = br.readLine();
        if(pp != null && !"12".equals(pp) && !"123".equals(pp)){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            pp=null;
        }
        if (pp == null) {
            new ChooseMenu(1);
        } else {
            switch (pp) {
                case "12" -> new ChooseMenu(2);
                case "123" -> new ChooseMenu(3);
            }
        }
    }

    @Override
    public void paint(Graphics g){
        if (offScreenImage == null) {
            offScreenImage = createImage(1200,700);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,1200,700);
        if(sort==1){
            graphics.drawImage(StaticValue.chooseMenu1,0,0,this);
        }else if(sort==2){
            graphics.drawImage(StaticValue.chooseMenu2,0,0,this);
        }else if(sort==3){
            graphics.drawImage(StaticValue.chooseMenu3,0,0,this);
        }
        g.drawImage(offScreenImage,0,0,this);
    }

    public void Play(int sort) throws IOException {
        new MyFrame(sort);
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedX=e.getX();
        int clickedY=e.getY();
        // System.out.println(clickedX+" "+clickedY);
        try{
            if(sort==1){
                if(clickedX>=162 && clickedX<=407 && clickedY >=540 && clickedY <= 635){
                    Play(1);
                }
            }else if(sort==2){
                if(clickedX>=162 && clickedX<=407 && clickedY >=540 && clickedY <= 635){
                    Play(1);
                }
                if(clickedX>=477 && clickedX<=723 && clickedY >=540 && clickedY <= 635){
                    Play(2);
                }
            }else if(sort==3){
                if(clickedX>=162 && clickedX<=407 && clickedY >=540 && clickedY <= 635){
                    Play(1);
                }
                if(clickedX>=477 && clickedX<=723 && clickedY >=540 && clickedY <= 635){
                    Play(2);
                }
                if(clickedX>=798 && clickedX<=1042 && clickedY >=540 && clickedY <= 635){
                    Play(3);
                }
            }
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
