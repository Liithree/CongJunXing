package Java;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable,Attackable{
    //坐标
    private int x;
    private final int y;
    //类型
    private final int type;
    //定义当前的图片的状态
    //private int image_type = 0;
    //判断敌人运动的方向，左、上为true，右、下为false
    private boolean face_to;
    //显示图片
    private BufferedImage show;
    //定义一个背景对象
    private final BackGround bg;
    //判断武器种类；
    boolean weaponKind = true;
    //武器
    private final Weapon spear = new Weapon(true);
    private final Weapon dagger = new Weapon(false);
    //伤害值
    private int attackAmount = 0;
    //攻击范围
    private int limits = 0;
    //状态
    String status = "normal";
    //血量
    private int blood = 5;
    //获取主人公
    private final Mario m = MyFrame.getMario();
    //警戒范围
    private int xML;
    private int xMR;
    //是否处于攻击状态
    private boolean isAttack = false;
    //攻击速度
    private int av = 500;
    private int av1 = 500;
    int index = 0;
    private boolean isDead=false;

    //普通敌人的构造函数
    public Enemy(int x, int y, boolean face_to, int type, int xML, int xMR, BackGround bg) {
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.bg = bg;
        this.xML = xML;
        this.xMR = xMR;
        //show = StaticValue.mogu.get(0);
        //定义线程
        Thread thread = new Thread(this);
        thread.start();
    }

    //死亡方法
    public void death() {
        status = "died";
        attackAmount = 0;
        limits = 0;
        blood = 0;
        this.bg.getEnemyList().remove(this);
        if(!isDead){
            m.setKillNum(m.getKillNum()+1);
            m.setKillNum3(m.getKillNum3()+1);
            isDead=true;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void unAttack(){
        isAttack = false;
        attackAmount = 0;
        limits = 0;
    }

    @Override
    public void attack() {
        isAttack = true;
        if (weaponKind ){
            attackAmount = spear.getDamageAmount();
            limits = spear.getLimits() - 10;
        }else {
            attackAmount = dagger.getDamageAmount();
            limits = dagger.getLimits() - 10;
        }
    }
    public int getType() {
        return type;
    }

    //受伤
    @Override
    public void beAttacked(int und){
        this.blood -= und;
    }

    public void EMoveR(){
        this.x -= 5;
        xMR -= 5;
        xML -= 5;
    }

    @Override
    public void run() {
        while (true) {
            boolean canLeft = true;
            boolean canRight = true;
            //判断是否是普通敌人
            //是否处于追踪状态
            boolean isHunt;
            if (type == 1) {

                if (this.blood > 0){

                    if ((this.x + 50 >= m.getX() && this.x <= m.getX() && !face_to ) || (this.x -50 <= m.getX() && this.x >= m.getX() && face_to)){
                        this.attack();
                    }else {
                        this.unAttack();
                    }

                    //追踪
                    if (m.getX() >= x - 230 && m.getX() < x && y == m.getY() && m.getX() >= xML && m.getX() <= xMR){
                        face_to = true;
                        isHunt = true;
                    }
                    if (m.getX() <= x + 170 && m.getX() > x && y == m.getY()&& m.getX() >= xML && m.getX() <= xMR){
                        face_to = false;
                        isHunt = true;
                    }else {
                        isHunt = false;
                    }

                    for (int i = 0;i < bg.getObstacleList().size();i++) {
                        Obstacle ob1 = bg.getObstacleList().get(i);

                        //判断是否可以右走
                        if (((ob1.getX() == this.x + 30 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y))  && ob1.getType() != 13) && !isAttack) {
                            canRight = false;
                            //face_to = true;
                        }

                        //判断是否可以左走
                        if (((ob1.getX() == this.x - 30 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) && ob1.getType() != 13) && !isAttack) {
                            canLeft = false;
                            //face_to = false;
                        }
                    }

                    //转向
                    if (((face_to && !canLeft) || this.x == 0 || this.x <= xML) && !isHunt) {
                        face_to = false;
                    }
                    else if (((!face_to && !canRight) || this.x == 764 || x >= xMR) && !isHunt) {
                        face_to = true;
                    }
                    if (face_to && !isAttack) {
                        this.x -= 4;
                    }else if (!face_to && !isAttack){
                        this.x += 4;
                    }
                    index = index == 0 ? 1 : 0;
                    if (face_to) {
                        show = StaticValue.enemy1_L_Run.get(index + 9);
                    }
                    if (!face_to) {
                        show = StaticValue.enemy1_R_Run.get(index + 9);
                    }
                    if (face_to && isAttack){
                        show = StaticValue.enemy1_L_Attack.get(8);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.enemy1_L_Attack.get(9);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((this.x - this.limits <= m.getX() && this.x >= m.getX())) {
                            m.beAttacked(1);
                        }
                    }
                    if (!face_to && isAttack){
                        show = StaticValue.enemy1_R_Attack.get(8);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.enemy1_R_Attack.get(9);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((this.x + this.limits >= m.getX() && this.x <= m.getX())){
                            m.beAttacked(1);
                        }
                    }
                }else {
                    death();
                }
            }

            if (type == 2){
                if (this.blood > 0){
                    if ((this.x + 50 >= m.getX() && this.x <= m.getX() && !face_to ) || (this.x -50 <= m.getX() && this.x >= m.getX() && face_to)){
                        this.attack();
                    }else {
                        this.unAttack();
                    }

                    //追踪
                    if (m.getX() <= x - 50 && m.getX() >= xML && m.getX() <= xMR){
                        face_to = true;
                    }
                    if (m.getX() >= x + 100 && m.getX() <= xMR && m.getX() >= xML){
                        face_to = false;
                        isHunt = true;
                    }else {
                        isHunt = false;
                    }


                    //转向
                    if ((this.x == 0 || this.x <= xML) && !isHunt) {
                        face_to = false;
                    }
                    else if ((this.x == 764 || x >= xMR) && !isHunt) {
                        face_to = true;
                    }
                    if (face_to && !isAttack) {
                        this.x -= 4;
                    }else if (!face_to && !isAttack){
                        this.x += 4;
                    }
                    if (face_to) {
                        show = StaticValue.enemy2_L_Run.get(3);
                    }
                    if (!face_to) {
                        show = StaticValue.enemy2_R_Run.get(3);
                    }
                    if (face_to && isAttack){
                        show = StaticValue.enemy2_L_Attack.get(2);
                        try {
                            Thread.sleep(av);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.enemy2_L_Attack.get(3);
                        try {
                            Thread.sleep(av);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((this.x - this.limits <= m.getX() && this.x >= m.getX())) {
                            m.beAttacked(1);
                            av -= 50;
                        }
                    }
                    if (!face_to && isAttack){
                        show = StaticValue.enemy2_R_Attack.get(2);
                        try {
                            Thread.sleep(av);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.enemy2_R_Attack.get(3);
                        try {
                            Thread.sleep(av);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((this.x + this.limits >= m.getX() && this.x <= m.getX())){
                            m.beAttacked(1);
                            av -= 50;
                        }
                    }
                    if (av <= 250){
                        av = 250;
                    }
                }else {
                    death();
                }

            }

            if (type == 3) {

                if (this.blood > 0){

                    if ((this.x + 50 >= m.getX() && this.x <= m.getX() && !face_to ) || (this.x -50 <= m.getX() && this.x >= m.getX() && face_to)){
                        this.attack();
                    }else {
                        this.unAttack();
                    }

                    //追踪
                    if (m.getX() >= x - 230 && m.getX() < x && y == m.getY() && m.getX() >= xML && m.getX() <= xMR){
                        face_to = true;
                        isHunt = true;
                    }
                    if (m.getX() <= x + 170 && m.getX() > x && y == m.getY()&& m.getX() >= xML && m.getX() <= xMR){
                        face_to = false;
                        isHunt = true;
                    }else {
                        isHunt = false;
                    }

                    for (int i = 0;i < bg.getObstacleList().size();i++) {
                        Obstacle ob1 = bg.getObstacleList().get(i);

                        //判断是否可以右走
                        if (((ob1.getX() == this.x + 30 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y))  && ob1.getType() != 13) && !isAttack) {
                            canRight = false;
                            //face_to = true;
                        }

                        //判断是否可以左走
                        if (((ob1.getX() == this.x - 30 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) && ob1.getType() != 13) && !isAttack) {
                            canLeft = false;
                            //face_to = false;
                        }
                    }

                    //转向
                    if (((face_to && !canLeft) || this.x == 0 || this.x <= xML) && !isHunt) {
                        face_to = false;
                    }
                    else if (((!face_to && !canRight) || this.x == 764 || x >= xMR) && !isHunt) {
                        face_to = true;
                    }
                    if (face_to && !isAttack) {
                        this.x -= 7;
                    }else if (!face_to && !isAttack){
                        this.x += 7;
                    }

                    if (face_to) {
                        show = StaticValue.enemy1_L_Run.get(3);
                    }
                    if (!face_to) {
                        show = StaticValue.enemy1_R_Run.get(3);
                    }
                    if (!face_to && isAttack){
                        show = StaticValue.enemy1_L_Attack.get(6);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.enemy1_L_Attack.get(3);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((this.x + this.limits >= m.getX() && this.x <= m.getX())){
                            m.beAttacked(attackAmount);
                        }
                    }
                    if (face_to && isAttack){
                        show = StaticValue.enemy1_R_Attack.get(6);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        show = StaticValue.enemy1_R_Attack.get(3);
                        try {
                            Thread.sleep(av1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((this.x - this.limits <= m.getX() && this.x >= m.getX())) {
                            m.beAttacked(attackAmount);
                        }
                    }
                }else {
                    death();
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void setAv1(int i){
        this.av1 -= i;
        if (av1 <= 350){
            av1 = 350;
        }

    }
}

