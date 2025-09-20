package Java;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //当前场景要显示的图像
    private BufferedImage bgImage = null;
    private BufferedImage bgImageClone = null;
    //背景图片显示坐标
    private int bgX1 = 0;
    private int bgX2 = 1200;
    //记录当前是第几个场景
    private int sort;
    //用于存放我们的所有障碍物
    private final List<Obstacle> obstacleList = new ArrayList<>();
    //用于存放我们的所有敌人
    private final List<Enemy> enemyList = new ArrayList<>();
    //用于显示生命值
    private BufferedImage hp=null;
    //用于显示过关条件
    private BufferedImage pass = null;
    //获取马里奥
    private final Mario m = MyFrame.getMario();
    public BackGround() {

    }
    public BackGround(int sort) {
        this.sort = sort;
        this.hp = StaticValue.hp;
        this.pass=StaticValue.pass;
        //南方
        if (sort == 3) {
            bgImage = StaticValue.bg_NanFang;
            bgImageClone = StaticValue.bg_NanFang;
            obstacleList.add(new Obstacle(700,550,2,600,800,this,3));
            obstacleList.add(new Obstacle(6600,550,2,6550,6850,this,3));


            for (int i=0;i<=159;i++){//绘制地面
                for (int j=0;j<=1;j++){
                    obstacleList.add(new Obstacle(i*50,600+j*50,1,this));
                }
            }//400
            for (int i=0;i<=11;i++){
                for (int j=0;j<=6;j++){
                    obstacleList.add(new Obstacle(8000+i*50,700-j*50,1,this));//挡板
                }
            }

            obstacleList.add(new Obstacle(1950,560,2,400,560,this,2));//电梯
            obstacleList.add(new Obstacle(2050,510,2,0,0,this,1));
            obstacleList.add(new Obstacle(2250,510,2,0,0,this,1));
            obstacleList.add(new Obstacle(2400,510,2,0,0,this,1));
            obstacleList.add(new Obstacle(2550,510,2,0,0,this,1));//5


            for (int i=100;i<=4200;i+=50){//绘制普通陷阱
                if (i==100||i==300||i==750||i==3500||i==3600||i==3700||i==4100){
                    obstacleList.add(new Obstacle(i,600,3,this));
                }
            }//7

            for (int i=450;i<=4650;i+=50){//绘制隐形陷阱
                if (i==450||i==600||i==650||i==700||i==750||i==800||i==1650||i==1950||i==4650){
                    obstacleList.add(new Obstacle(i,600,42,this));
                }
            }//6

            //绘制水面
            obstacleList.add(new Obstacle(1540,600,11,this));
            obstacleList.add(new Obstacle(1800,600,11,this));//2
            for (int i=2000;i<=2700;i+=50){
                obstacleList.add(new Obstacle(i,600,11,this));
            }//15
            for (int i=4450;i<=5350;i+=50){
                obstacleList.add(new Obstacle(i,600,11,this));
            }
            for (int i=6550;i<=6900;i+=50){
                obstacleList.add(new Obstacle(i,600,11,this));
            }
            for (int i=7050;i<=7700;i+=50){
                obstacleList.add(new Obstacle(i,600,11,this));
            }


            //绘制鸟
            obstacleList.add(new Obstacle(2200,550,9,2000,2650,this));
            obstacleList.add(new Obstacle(2400,570,9,2220,2670,this));
            obstacleList.add(new Obstacle(3200,550,9,3100,3400,this));
            obstacleList.add(new Obstacle(3900,550,9,3700,4000,this));
            obstacleList.add(new Obstacle(4950,550,9,4850,5200,this));
            obstacleList.add(new Obstacle(5250,420,9,5200,5400,this));
            obstacleList.add(new Obstacle(6100,530,9,6000,6200,this));
            obstacleList.add(new Obstacle(6250,480,9,6100,6300,this));
            obstacleList.add(new Obstacle(6350,300,9,6200,6400,this));
            obstacleList.add(new Obstacle(6900,330,9,6850,7050,this));
            obstacleList.add(new Obstacle(6900,150,9,6850,7050,this));
            obstacleList.add(new Obstacle(7100,250,9,7050,7200,this));
            obstacleList.add(new Obstacle(7200,550,9,7150,7350,this));
            obstacleList.add(new Obstacle(7400,550,9,7350,7500,this));

            //绘制马蜂窝
            obstacleList.add(new Obstacle(850,330,5,this));
            obstacleList.add(new Obstacle(1200,330,5,this));
            obstacleList.add(new Obstacle(1250,330,5,this));
            obstacleList.add(new Obstacle(1300,330,5,this));
            obstacleList.add(new Obstacle(1350,330,5,this));
            obstacleList.add(new Obstacle(6750,400,5,this));
            obstacleList.add(new Obstacle(6850,400,5,this));
            obstacleList.add(new Obstacle(7520,400,5,this));
            obstacleList.add(new Obstacle(7570,400,5,this));
            obstacleList.add(new Obstacle(7150,150,5,this));
            obstacleList.add(new Obstacle(7570,150,5,this));

            //绘制冰锥
            obstacleList.add(new Obstacle(1450,570,7,this));



            //绘制砖块
            obstacleList.add(new Obstacle(4200,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(4250,280,2,0,0,this,1));
            obstacleList.add(new Obstacle(4400,340,2,0,0,this,1));
            obstacleList.add(new Obstacle(4600,380,2,0,0,this,1));
            obstacleList.add(new Obstacle(4700,370,2,230,380,this,2));
            obstacleList.add(new Obstacle(4800,260,2,0,0,this,1));
            obstacleList.add(new Obstacle(5050,400,2,0,0,this,1));
            obstacleList.add(new Obstacle(5200,400,2,0,0,this,1));
            obstacleList.add(new Obstacle(5350,450,2,0,0,this,1));
            //新增部分
            obstacleList.add(new Obstacle(6150,550,2,0,0,this,1));
            obstacleList.add(new Obstacle(6250,550,2,0,0,this,1));
            obstacleList.add(new Obstacle(6350,550,2,0,0,this,1));
            obstacleList.add(new Obstacle(6450,550,2,0,0,this,1));
            obstacleList.add(new Obstacle(6250,500,2,0,0,this,1));
            obstacleList.add(new Obstacle(6350,500,2,0,0,this,1));
            obstacleList.add(new Obstacle(6450,500,2,0,0,this,1));
            obstacleList.add(new Obstacle(6350,450,2,0,0,this,1));
            obstacleList.add(new Obstacle(6450,450,2,0,0,this,1));
            obstacleList.add(new Obstacle(6350,400,2,0,0,this,1));
            obstacleList.add(new Obstacle(6450,400,2,0,0,this,1));
            obstacleList.add(new Obstacle(6450,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(6450,300,2,0,0,this,1));
            obstacleList.add(new Obstacle(6650,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(6750,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(6850,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(6950,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(7050,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(7150,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(7250,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(7350,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(7450,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(7550,350,2,0,0,this,1));
            obstacleList.add(new Obstacle(6750,300,2,0,0,this,1));




            //绘制船
            obstacleList.add(new Obstacle(7050,560,12,this));
            obstacleList.add(new Obstacle(4450,560,12,this));
            //绘制水藻
            obstacleList.add(new Obstacle(4910,600,8,this));
            obstacleList.add(new Obstacle(5110,600,8,this));
            obstacleList.add(new Obstacle(5310,600,8,this));
            //绘制向上水流
            obstacleList.add(new Obstacle(4150,350,10,this));
            //绘制敌人
            enemyList.add(new Enemy(1050,550,true,1,1000,1150,this));
            enemyList.add(new Enemy(3000,550,true,1,2950,3050,this));
            enemyList.add(new Enemy(3090,550,true,1,3060,3120,this));
            enemyList.add(new Enemy(3900,550,true,1,3800,4050,this));
            enemyList.add(new Enemy(5600,550,true,2,-500,5900,this));
            enemyList.add(new Enemy(7800,550,true,3,7700,8000,this));
            enemyList.add(new Enemy(7850,550,true,3,7700,8000,this));
            enemyList.add(new Enemy(7950,550,true,3,7700,8000,this));

        }


        //北国
        if (sort == 2) {
            bgImage = StaticValue.bg_BeiGuo;
            bgImageClone = StaticValue.bg_BeiGuo;

            for(int i=0;i<=1200;i += 50) {
                obstacleList.add(new Obstacle(i,200,1,this));//砖块
            }
            obstacleList.add(new Obstacle(750,170,7,this));//冰锥
            obstacleList.add(new Obstacle(850,170,7,this));//冰锥
            obstacleList.add(new Obstacle(1050,170,7,this));//冰锥
            for(int i=100;i<=150;i += 50) {
                obstacleList.add(new Obstacle(200,i,1,this));//砖块，竖着的柱子
            }

//绘制第一层的陷阱（从上往下）
            obstacleList.add(new Obstacle(400,200,6,this));//加强普通陷阱




//绘制第一层敌人
            enemyList.add(new Enemy(250,150,true,2,0,1200,this));//敌人

            enemyList.add(new Enemy(500,150,true,1,400,600,this));//敌人

//绘制第一层隐形陷阱
            obstacleList.add(new Obstacle(600,200,41,this));//隐形陷阱
            obstacleList.add(new Obstacle(1080,200,41,this));//隐形陷阱

//绘制第二层冰锥和砖块
            for (int i= 1000;i<= 1800;i += 50) {
                obstacleList.add(new Obstacle(i,350,1,this));//砖块
            }
            obstacleList.add(new Obstacle(1300,320,7,this));//冰锥
            obstacleList.add(new Obstacle(1600,320,7,this));//冰锥

//绘制第二层加强普通陷阱
            obstacleList.add(new Obstacle(1400,350,6,this));//加强普通陷阱
            obstacleList.add(new Obstacle(1450,350,6,this));//加强普通陷阱


//绘制第二层隐形陷阱
            //    obstacleList.add(new Obstacle(1200,300,41,this));//隐形陷阱

//绘制第二层敌人
            enemyList.add(new Enemy(1500,300,true,1,1300,1700,this));//敌人
            enemyList.add(new Enemy(1500,300,true,2,1200,1400,this));

//绘制第三层冰锥和砖块
            for (int i= 1900;i<= 3100;i += 50) {
                obstacleList.add(new Obstacle(i,450,1,this));//砖块
            }



            obstacleList.add(new Obstacle(2600,420,7,this));//冰锥

//绘制第三层隐形陷阱
            obstacleList.add(new Obstacle(2400,450,41,this));//隐形陷阱
            obstacleList.add(new Obstacle(2550,450,41,this));//隐形陷阱



//绘制第三层普通陷阱
            obstacleList.add(new Obstacle(2130,450,6,this));//加强普通陷阱
            obstacleList.add(new Obstacle(2180,450,6,this));//加强普通陷阱
            obstacleList.add(new Obstacle(2800,450,6,this));//加强普通陷阱
            obstacleList.add(new Obstacle(2850,450,6,this));//加强普通陷阱

//绘制第三层敌人
            enemyList.add(new Enemy(2100,400,true,2,1900,3100,this));//敌人

            enemyList.add(new Enemy(2300,400,true,1,2400,2600,this));//敌人
            enemyList.add(new Enemy(3000,400,true,1,2600,3100,this));//敌人



//绘制第四层冰锥和砖块（连着的）
            for (int i= 3200;i<= 4400;i += 50) {
                obstacleList.add(new Obstacle(i,600,1,this));//砖块
            }

            for (int i= 4400;i<= 5000;i += 50) {
                obstacleList.add(new Obstacle(i,650,1,this));//砖块，即地面
            }
            obstacleList.add(new Obstacle(3700,550,1,this));//砖块
            obstacleList.add(new Obstacle(3750,550,1,this));//砖块
            obstacleList.add(new Obstacle(3750,500,1,this));//砖块

            for (int i= 3800;i<= 4250;i += 50) {
                obstacleList.add(new Obstacle(i,570,7,this));//冰锥
            }
            obstacleList.add(new Obstacle(4150,470,7,this));//冰锥

            obstacleList.add(new Obstacle(3850,450,1,this));
            obstacleList.add(new Obstacle(3900,450,1,this));
            obstacleList.add(new Obstacle(4000,400,1,this));
            obstacleList.add(new Obstacle(4050,400,1,this));
            obstacleList.add(new Obstacle(4150,500,1,this));
            obstacleList.add(new Obstacle(4200,500,1,this));
            obstacleList.add(new Obstacle(4200,450,1,this));
            obstacleList.add(new Obstacle(4200,400,1,this));
            obstacleList.add(new Obstacle(4200,350,1,this));


//绘制第四层隐形陷阱
            obstacleList.add(new Obstacle(4750,650,41,this));//隐形陷阱

//绘制第四层普通陷阱
            obstacleList.add(new Obstacle(3350,600,6,this));//加强普通陷阱
            obstacleList.add(new Obstacle(3500,600,6,this));//加强普通陷阱




//绘制第四层敌人
            enemyList.add(new Enemy(3200,550,true,2,3200,3700,this));//敌人
            enemyList.add(new Enemy(3550,550,true,2,3200,3700,this));//敌人
            enemyList.add(new Enemy(4400,600,true,2,4400,5000,this));//敌人

            enemyList.add(new Enemy(4500,600,true,1,4400,4700,this));//敌人

//绘制马蜂窝
            obstacleList.add(new Obstacle(2100,100,5,this));//马蜂窝
            obstacleList.add(new Obstacle(2300,150,5,this));//马蜂窝
            obstacleList.add(new Obstacle(2400,150,5,this));//马蜂窝
            obstacleList.add(new Obstacle(2750,150,5,this));//马蜂窝
            obstacleList.add(new Obstacle(3900,100,5,this));//马蜂窝
            for (int i= 4550;i<= 4700;i += 50) {
                obstacleList.add(new Obstacle(i,350,5,this));//马蜂窝
            }






        }


        //平原
        if(sort==1){
            bgImage = StaticValue.bg_PingYuan;
            bgImageClone = StaticValue.bg_PingYuan;
            for(int i=0;i*50<8000;i++){
                obstacleList.add(new Obstacle(i*50, 600, 1, this));
            }

            //灌木
            obstacleList.add(new Obstacle(1600, 550, 14, this));
            obstacleList.add(new Obstacle(1800, 550, 14, this));
            for(int i=0;i<6;i++){
                obstacleList.add(new Obstacle(2850+i*150, 550, 14, this));
            }
            for(int i=0;i<10;i++){
                obstacleList.add(new Obstacle(5150+i*150, 550, 14, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(6700+i*200, 550, 14, this));
            }

            //蜂窝
            obstacleList.add(new Obstacle(500, 300, 5, this));
            obstacleList.add(new Obstacle(1050, 300, 5, this));
            obstacleList.add(new Obstacle(1720, 400, 5, this));
            obstacleList.add(new Obstacle(1870, 400, 5, this));
            obstacleList.add(new Obstacle(1920, 300, 5, this));
            obstacleList.add(new Obstacle(2550, 420, 5, this));
            for(int i=4200;i<=7100;i+=10) {
                if (i == 4250 || i == 4300 || i == 4400 || i == 4500 || i == 5220 || i == 5420 || i == 5600 || i==5700 || i == 5800 || i == 6020 || i == 6550 || i == 6820 || i == 6900 || i == 7100) {
                    obstacleList.add(new Obstacle(i, 300, 5, this));
                }
            }
            for(int i=4200;i<7100;i+=10){
                if(i==4350||i==4500||i==4550||i==4700||i==5870){
                    obstacleList.add(new Obstacle(i, 300, 5, this));
                }
            }

            //隐形陷阱
            obstacleList.add(new Obstacle(250, 600, 4, this));
            obstacleList.add(new Obstacle(450, 600, 4, this));
            obstacleList.add(new Obstacle(600, 600, 4, this));
            obstacleList.add(new Obstacle(700, 600, 4, this));
            obstacleList.add(new Obstacle(800, 600, 4, this));
            obstacleList.add(new Obstacle(900, 600, 4, this));
            obstacleList.add(new Obstacle(1000, 600, 4, this));
            obstacleList.add(new Obstacle(1950, 600, 4, this));
            obstacleList.add(new Obstacle(2800, 550, 4, this));
            obstacleList.add(new Obstacle(3650, 400, 4, this));
            obstacleList.add(new Obstacle(5600, 600, 4, this));
            obstacleList.add(new Obstacle(5900, 600, 4, this));
            obstacleList.add(new Obstacle(6050, 600, 4, this));
            obstacleList.add(new Obstacle(6200, 600, 4, this));
            obstacleList.add(new Obstacle(6350, 600, 4, this));
            obstacleList.add(new Obstacle(6400, 600, 4, this));
            obstacleList.add(new Obstacle(7200, 600, 4, this));
            obstacleList.add(new Obstacle(7300, 600, 4, this));

            //陷阱
            obstacleList.add(new Obstacle(300, 600, 3, this));
            obstacleList.add(new Obstacle(400, 600, 3, this));
            obstacleList.add(new Obstacle(2300, 600, 3, this));
            obstacleList.add(new Obstacle(3200, 600, 3, this));
            obstacleList.add(new Obstacle(3400, 600, 3, this));
            obstacleList.add(new Obstacle(5250, 600, 3, this));
            obstacleList.add(new Obstacle(5450, 600, 3, this));
            //路
            for(int i=0;i<6;i++){
                obstacleList.add(new Obstacle(1150+i*50, 550, 1, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(1250+i*50, 500, 1, this));
            }
            for(int i=0;i<2;i++){
                obstacleList.add(new Obstacle(1350+i*50, 450, 1, this));
            }
            for(int i=0;i<5;i++){
                obstacleList.add(new Obstacle(2550+i*50, 550, 1, this));
            }
            for(int i=0;i<3;i++){
                obstacleList.add(new Obstacle(2850+i*50, 450, 1, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(3000+i*50, 400, 1, this));
            }
            for(int i=0;i<6;i++){
                obstacleList.add(new Obstacle(3200+i*50, 350, 1, this));
            }
            for(int i=0;i<4;i++){
                obstacleList.add(new Obstacle(3500+i*50, 400, 1, this));
            }
            for(int j=0;j<10;j++){
                for(int i =0;i<10;i++)
                    obstacleList.add(new Obstacle(7900+i*50, 400+j*50, 1, this));
            }


            //普通敌人
            enemyList.add(new Enemy(600,540,true,1,0,1120,this));
            enemyList.add(new Enemy(800,540,true,1,0,1120,this));
            enemyList.add(new Enemy(1000,540,true,1,0,1120,this));
            enemyList.add(new Enemy(2200,540,true,1,1450,2520,this));
            enemyList.add(new Enemy(2400,540,true,1,1450,2520,this));
            enemyList.add(new Enemy(3300,290,true,1,3200,3470,this));
            enemyList.add(new Enemy(3100,540,true,1,2850,5120,this));
            enemyList.add(new Enemy(3300,540,true,1,2850,5120,this));
            enemyList.add(new Enemy(3500,540,true,1,2850,5120,this));
            enemyList.add(new Enemy(3950,540,true,1,2850,5120,this));
            enemyList.add(new Enemy(4150,540,true,1,2850,5120,this));
            enemyList.add(new Enemy(4950,540,true,1,2850,5120,this));
            enemyList.add(new Enemy(6650,540,true,1,6650,7420,this));
            enemyList.add(new Enemy(6850,540,true,1,6650,7420,this));
            enemyList.add(new Enemy(7050,540,true,1,6650,7420,this));
            enemyList.add(new Enemy(5600,540,true,1,5150,7420,this));
            enemyList.add(new Enemy(5900,540,true,1,5150,7420,this));
            enemyList.add(new Enemy(6200,540,true,1,5150,7420,this));
            enemyList.add(new Enemy(6500,540,true,1,5150,7420,this));

            //追踪敌人
            enemyList.add(new Enemy(1420,390,true,2,1350,1420,this));
            enemyList.add(new Enemy(1450,540,true,2,1450,2520,this));
            enemyList.add(new Enemy(2820,490,true,2,2550,2820,this));
            enemyList.add(new Enemy(3170,340,true,2,3000,3170,this));
            enemyList.add(new Enemy(3500,340,true,2,3500,3670,this));

        }
    }
    public void bgMoveXR(){
        if (m.getX() >= 600){
            if (this.bgX1 <= -1200){
                this.bgX1 = this.bgX2 + 1200;
            }
            if (this.bgX2 <= -1200){
                this.bgX2 = this.bgX1 + 1200;
            }
            this.bgX1 -= 10;
            this.bgX2 -= 10;
        }
    }

    public int getBgX1() {
        return bgX1;
    }

    public int getBgX2() {
        return bgX2;
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }

    public BufferedImage getBgImageClone() {
        return bgImageClone;
    }

    public int getSort() {
        return sort;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
    public BufferedImage getHp(){
        return hp;
    }
    public BufferedImage getPass(){
        return pass;
    }
    public void addEnemy(Enemy enemy){
        this.enemyList.add(enemy);
    }
}