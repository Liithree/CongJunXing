package Java;

import java.awt.image.BufferedImage;


public class Obstacle implements Runnable{

    //坐标
    private int X;
    private int Y;
    private int xMR;
    private int yMR;
    //障碍物类型
    private final int type;

    private int cfxType;//定义长方形方块是否运动

    public int boatNumber=0;//用于记录船碰到水藻的次数
    //显示图像
    private BufferedImage show=null;

    //关联障碍物与背景（或者人物也行），主要作用是确定在什么情况下，某些可以移动的障碍物移动（与人物活动情况关联的障碍物特性均需这个属性）
    private BackGround bg=null;
    //定义线程，此线程作用就是完成与人物活动关联的障碍物特性
    private Thread thread=null;
    //摆锤摆动参数，对其他障碍物无作用

    private int min = 0;//上下或左右移动方块
    private int max = 0;//上下或左右移动方块
    boolean face_to=true;

    private int max_right;//鸟
    private int max_left;//鸟

    private int initial_X;//开始的X值

    //设置表示机关是否触发的变量；
    private boolean strike = false;
    //设置表示机关是否可用的变量；
    private boolean status = true;
    //获取主人公类；
    private final Mario m = MyFrame.getMario();

    private String id="else";//记录水藻
    private int riversExistTime=24;
    private int riversDisappearTime=80;
    private boolean chuFa=false;



    public void OMoveR(){
        this.X -= 5;
        xMR -= 5;
    }

    public Obstacle(int X, int Y, int type, int min, int max, BackGround bg, int cfxType){//长方形构造函数,2
        this.cfxType=cfxType;
        this.X=X;
        this.Y=Y;
        this.type=type;
        this.bg=bg;
        this.min=min;
        this.max=max;
        if (type==2){
            this.show=StaticValue.obstacleList.get(3);
        }
        thread=new Thread(this);
        thread.start();

    }

    public Obstacle(int X, int Y, int type, int max_left, int max_right, BackGround bg){//鸟的构造函数，9
        this.X=X;
        this.Y=Y;
        this.type=type;
        this.bg=bg;
        this.max_left=max_left;
        this.max_right=max_right;
        if (type==9){
            this.show=StaticValue.obstacleList.get(10);
        }
        thread=new Thread(this);
        thread.start();

    }

    //含参数构造函数,此构造函数中没有bufferedImage类，即默认常量类的存在（常量类中的属性均为public static，即常量类任务是完成加载图片初始化）
    public Obstacle(int X, int Y, int type, BackGround bg){
        this.X=X;
        this.Y=Y;
        this.initial_X=X;
        this.type=type;
        this.bg=bg;
        thread=new Thread(this);
        if (type==1){
            if (this.bg.getSort() == 1) {
                this.show = StaticValue.obstacleList.get(0);
            }
            if (this.bg.getSort() == 2) {
                this.show = StaticValue.obstacleList.get(1);
            }
            if (this.bg.getSort() == 3) {
                this.show = StaticValue.obstacleList.get(2);
            }
        }
        if (type==3){
            this.show=StaticValue.obstacleList.get(4);
            thread.start();
        }
        if (type==4){
            this.show=StaticValue.obstacleList.get(0);
            thread.start();
        }
        if (type==5){
            this.show=StaticValue.obstacleList.get(6);
            thread.start();
        }
        if (type==6){
            this.show=StaticValue.obstacleList.get(7);
            thread.start();
        }
        if (type==7){
            this.show=StaticValue.obstacleList.get(8);
            thread.start();
        }
        if (type==8){
            this.show=StaticValue.obstacleList.get(9);
            if (X==4910){
                id="水藻1";
            }
            if (X==5110){
                id="水藻2";
            }
            if (X==5310){
                id="水藻3";
            }
        }
        if (type==10){
            this.show=StaticValue.obstacleList.get(11);
            thread.start();
        }
        if (type==11){
            this.show=StaticValue.obstacleList.get(12);
            thread.start();
        }
        if (type==12){
            this.show=StaticValue.obstacleList.get(13);
            thread.start();
        }
        if (type==13){
            this.show=StaticValue.obstacleList.get(14);
            thread.start();
        }
        if (type == 14){
            this.show = StaticValue.obstacleList.get(14);
        }
        if (type == 41){
            this.show = StaticValue.obstacleList.get(1);
            thread.start();
        }
        if(type == 42){
            this.show = StaticValue.obstacleList.get(2);
            thread.start();
        }
    }



    //此方法线程的run方法,里面存放的是与人物活动关联的障碍物 何时进行何反应的代码（2个关键要素，何时，何反应）
    @Override
    public void run(){
        while (true){

            if (this.type==3){//实现普通陷阱的伤害
                if (this.status) {
                    if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                        m.beAttacked(1);
                        this.status = false;
                        this.show=null;
                    }
                }
            }

            if (this.type==4) {//实现隐形陷阱
                if (this.status) {
                    if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.obstacleList.get(16);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.obstacleList.get(17);
                        if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                            m.beAttacked(1);
                        }
                        this.status = false;
                        this.show=null;
                    }
                }
            }

            if (this.type == 41){//实现隐形陷阱
                if (this.status) {
                    if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.obstacleList.get(18);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.obstacleList.get(19);
                        if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                            m.beAttacked(1);
                        }
                        this.status = false;
                        this.show=null;
                    }
                }
            }

            if (this.type == 42){//实现隐形陷阱
                if (this.status) {
                    if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.obstacleList.get(16);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.obstacleList.get(17);
                        if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                            m.beAttacked(1);
                        }
                        this.status = false;
                        this.show=null;
                    }
                }
            }
            if (this.type==5){//实现马蜂窝的下坠
                if (m.getX()==X){
                    strike=true;
                }
                if (strike){
                    this.Y+=10;
                }
                if (X>m.getX()-30&&X<m.getX()+30&&(Y>=m.getY()-30&&Y<=m.getY()+60) && status){
                    m.beAttacked(1);
                    status = false;
                    //strike=true;
                    chuFa=true;
                    this.show=null;
                }
            }

            if (this.type==6){//加强普通陷阱
                if (this.status) {
                    if (Y == m.getY() + 60 && (X > m.getX() - 40 && X < m.getX() + 20)) {
                        m.beAttacked(1);
                        this.status = false;
                        this.strike=true;
                        chuFa=true;
                        this.show=null;
                    }
                }
            }

            if (this.type==7){//冰锥
                if (X>=m.getX()&&X<=m.getX()+30&&Y>=m.getY()+30&&Y<=m.getY()+60) {
                    m.beAttacked(1);
                    m.beRepelL();
                }
                if (X<m.getX()&&X>=m.getX()-30&&Y>=m.getY()+30&&Y<=m.getY()+60){
                    m.beRepelR();
                    m.beAttacked(1);
                }
            }

            if (this.type==9){//实现鸟的左右移动
                if (face_to) {
                    this.X -= 5;
                    this.show=StaticValue.obstacleList.get(15);
                }else {
                    this.X += 5;
                    this.show=StaticValue.obstacleList.get(10);
                }
                //是否到达极限位置
                if (face_to && (this.X == max_left+xMR)) {
                    face_to = false;
                }
                if ((!face_to) && (this.X == max_right+xMR)) {
                    face_to = true;
                }
                if ((m.getX()+30>=X&&m.getX()-20<=X)&& m.getY()-20<Y && m.getY()+60>Y && isStatus()){
                    m.beAttacked(1);
                    this.show=null;
                    status = false;
                }
            }

            if (this.type==11){//人物碰到水面掉血
                if (status) {
                    if (Y==m.getY()+60&& (X > m.getX() - 30 && X < m.getX() + 10)) {
                        m.beAttacked(1);
                        this.status = false;
                    }
                }
            }

            if (this.type==12){//船碰到水藻的消失,按照设计有三个水藻
                if (m.getX()+30>=X&&m.getX()-100<=X&&m.getY()+60==Y){//人物碰到船,船随人动
                    X=m.getX()-30;
                }
                if (X<initial_X+xMR){
                    X=initial_X+xMR;
                }

                if (this.X+100==4900+xMR){//碰到第一个水藻
                    for(Obstacle ob:this.bg.getObstacleList()){
                        if (ob.getId().equals("水藻1")&&ob.status){
                            ob.show=null;
                            ob.status=false;
                            boatNumber++;
                        }
                    }
                }

                if (this.X+100==5100+xMR){//碰到第二个水藻
                    for(Obstacle ob:this.bg.getObstacleList()){
                        if (ob.getId().equals("水藻2")&&ob.status){
                            ob.show=null;
                            ob.status=false;
                            boatNumber++;
                        }
                    }
                }

                if (this.X+100==5300+xMR){
                    for(Obstacle ob:this.bg.getObstacleList()){
                        if (ob.getId().equals("水藻3") && ob.status){
                            ob.show=null;
                            ob.status=false;
                            boatNumber++;
                        }
                    }
                }
                if (boatNumber>=2){
                    this.bg.getObstacleList().remove(this);
                }

            }
            try {
                Thread.sleep(50);//坐标每改变一次所用的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getId() {
        return id;
    }

    public int getRiversExistTime() {
        return riversExistTime;
    }

    public void setRiversExistTime(int shuiliucunzaitime) {
        this.riversExistTime = shuiliucunzaitime;
    }

    public int getRiversDisappearTime() {
        return riversDisappearTime;
    }

    public void setRiversDisappearTime(int shuiliuxiaoshitime) {
        this.riversDisappearTime = shuiliuxiaoshitime;
    }
    public void setX(int x) {
        X = x;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public int getxMR() {
        return xMR;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }


    public boolean isStatus() {
        return status;
    }

    public void setFace_to(boolean face_to) {
        this.face_to = face_to;
    }

    public boolean isFace_to() {
        return face_to;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getType() {
        return type;
    }


    public BufferedImage getShow(){return show;}

    public int getCfxType() {
        return cfxType;
    }


    public void setY(int newY){
        this.Y=newY;
    }

    public boolean isChuFa() {
        return chuFa;
    }

    public void setChuFa(boolean chuFa) {
        this.chuFa=chuFa;
    }
    public int getyMR(){
        return yMR;
    }
}