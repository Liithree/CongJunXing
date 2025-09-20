package Java;

import java.awt.image.BufferedImage;

public class Mario implements Runnable,Attackable {
    //人物坐标
    private int x;
    private int y;
    //人物血量
    private int blood = 100;
    //人物状态
    private String status;
    //用于显示当前状态对应的图像
    private BufferedImage show = null;
    //移动速度
    private int velocity;
    //跳跃速度
    private int yVelocity;
    //上升时间
    private int upTime = 0;
    //用于判断马里奥是否走到了城堡的门口
    private boolean isOK = false;
    //武器
    private boolean weaponKind = true;
    private final Weapon spear = new Weapon(true);
    private final Weapon dagger = new Weapon(false);
    //伤害值
    private int attackAmount = 0;
    //攻击范围
    private int limits = 0;
    //耐久度
    private int dur = 0;
    //图片索引
    private int index;
    //定义一个BackGround对象,用来获取障碍物的信息
    private BackGround backGround = new BackGround();
    //用于判断移动时是否调用背景移动应显示的图片
    private boolean bgM = true;
    private boolean bgM2 = true;
    private boolean isRM = false;
    private boolean isLM = false;
    private boolean isAttack = false;
    private int dizzyTime=35;
    private final Music music =new Music(false);
    private int killNum=0;
    private int killNum3=0;
    private int goal1=24;
    private int goal2=11;
    private int goal3=8;


    public Mario() {

    }

    public Mario (int x,int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.stand_R_LongSword;
        this.status = "stand--right";
        //添加线程
        Thread thread = new Thread(this);
        thread.start();
    }

    //向左移动
    public void leftMove() {
        isRM = false;
        isLM = true;
        //改变速度
        velocity = -5;

        //判断马里奥是否处于空中
        if (status.contains("jump")) {
            status = "jump--left";
        }else {
            status = "move--left";
        }
    }

    public void setBgM(boolean b){
        this.bgM = b;
    }

    public void setBgM2(boolean b){
        this.bgM2 = b;
    }
    //向右移动
    public void rightMove() {
        isRM = true;
        isLM = false;

        if (!this.bgM){
            velocity = 0;
        }else{
            velocity = 5;
        }

        if (status.contains("jump")) {
            status = "jump--right";
        }else {
            status = "move--right";
        }
    }

    //向左停止
    public void leftStop() {
        isRM = false;
        isLM = false;
        velocity = 0;
        if (status.contains("jump")) {
            status = "jump--left";
        }else {
            status = "stop--left";
        }
    }

    //向右停止
    public void rightStop() {
        isRM = false;
        isLM = false;
        velocity = 0;
        if (status.contains("jump")) {
            status = "jump--right";
        }else {
            status = "stop--right";
        }
    }

    //跳跃
    public void jump() {
        if (!status.contains("jump")) {
            if (status.contains("left")) {
                status = "jump--left";
            }else {
                status = "jump--right";
            }
            if (this.bgM2 ) {
                yVelocity = -10;
                upTime = 12;
            }else {
                yVelocity = 0;
                upTime = 10;
            }
        }

    }

    //下落
    public void fall() {
        if (status.contains("left")) {
            status = "jump--left";
        }else {
            status = "jump--right";
        }
        if (bgM2) {
            yVelocity = 10;
        }else {
            yVelocity = 0;
        }
    }

    //攻击
    @Override
    public void attack() {
        isAttack = true;
        if(weaponKind){
            attackAmount = this.spear.getDamageAmount();
            limits = this.spear.getLimits();
            dur = this.spear.getDurability();
        }else {
            attackAmount = this.dagger.getDamageAmount();
            limits = this.dagger.getLimits();
            dur = this.dagger.getDurability();
        }
        if (dur == 0){
            attackAmount = 0;
        }
    }

    //切换攻击模式
    public void cutAttackMode(){
        weaponKind = !weaponKind;
    }

    //受伤
    @Override
    public void beAttacked(int und){
        this.blood -= und;
        if (this.blood <= 0){
            status = "die";
        }
    }

    public void unAttack(){
        isAttack = false;
    }

    public void beRepelL(){
        status = "beRepel--left";
        x-=80;
    }


    public void beRepelR(){
        status = "berepel--right";
        x+=80;
    }

    @Override
    public void run() {
        while (true) {
            //判断是否可以往右走
            boolean canRight = true;
            //判断是否可以往左走
            boolean canLeft = true;
            //判断是否处于障碍物上
            boolean onObstacle = false;
            boolean onCfx=false;
            boolean canMove=true;

            for (int i = 0; i < backGround.getObstacleList().size(); i++) {
                Obstacle ob = backGround.getObstacleList().get(i);

                if (ob.getType() == 1 || ob.getType() == 11) {//方块实现不可穿越,完成
                    if (ob.getY() == y + 60 && (ob.getX() > this.x - 50 && ob.getX() < this.x + 30)) {
                        onObstacle = true;
                    }

                    //判断是否跳起来顶到砖块
                    if ((ob.getY() >= this.y - 51 && ob.getY() <= this.y - 39) && (ob.getX() > this.x - 50 && ob.getX() < this.x + 30)) {
                        upTime = 0;
                    }

                    //判断是否可以往右走
                    if (ob.getX() == this.x + 30 && (ob.getY() > this.y - 50 && ob.getY() < this.y + 60)) {
                        canRight = false;
                    }

                    //判断是否可以往左走
                    if (ob.getX() == this.x - 50 && (ob.getY() > this.y - 50 && ob.getY() < this.y + 60)) {
                        canLeft = false;
                    }
                }

                if (ob.getType() == 2 && ob.getCfxType() == 1) {//长方形不可穿越
                    if (ob.getY() >= y + 45 && ob.getY() <= y + 65 && (ob.getX() > this.x - 100 && ob.getX() < this.x + 30)) {
                        y = ob.getY() - 60;
                        onObstacle = true;
                    }

                    //判断是否跳起来顶到砖块
                    if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 100 && ob.getX() < this.x + 30)) {
                        upTime = 0;
                    }

                    //判断是否可以往右走
                    if (ob.getX() == this.x + 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 60)) {
                        canRight = false;
                    }

                    //判断是否可以往左走
                    if (ob.getX() == this.x - 100 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 60)) {
                        canLeft = false;
                    }
                }

                if (ob.getType() == 2 && ob.getCfxType() == 2) {//上下移动方块完成
                    if (ob.isFace_to()) {
                        ob.setY(ob.getY() - 5);
                    } else {
                        ob.setY(ob.getY() + 5);
                    }
                    if (ob.isFace_to() && (ob.getY() == ob.getMin() + ob.getyMR())) {
                        ob.setFace_to(false);
                    }
                    if (!ob.isFace_to() && (ob.getY() == ob.getMax() + ob.getyMR())) {
                        ob.setFace_to(true);
                    }
                    if (ob.getY() >= y + 45 && ob.getY() <= y + 65 && (ob.getX() < this.x + 30 && ob.getX() > this.x - 100)) {
                        onCfx = true;
                        y = ob.getY() - 50;
                        if (ob.getX() >= this.x + 30 && ob.getX() <= this.x - 100) {
                            onCfx = false;
                        }
                    }
                    if (!onCfx) {
                        //判断是否跳起来顶到砖块
                        if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 100 && ob.getX() < this.x + 30)) {
                            upTime = 0;
                        }

                        //判断是否可以往右走
                        if (ob.getX() == this.x + 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 60)) {
                            canRight = false;
                        }

                        //判断是否可以往左走
                        if (ob.getX() == this.x - 100 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 60)) {
                            canLeft = false;
                        }
                    }
                }
                if (ob.getType() == 2 && ob.getCfxType() == 3) {//左右移动方块完成
                    if (ob.isFace_to()) {
                        ob.setX(ob.getX() - 5);
                    } else {
                        ob.setX(ob.getX() + 5);
                    }
                    if (ob.isFace_to() && (ob.getX() == ob.getMin() + ob.getxMR())) {
                        ob.setFace_to(false);
                    }
                    if (!ob.isFace_to() && (ob.getX() == ob.getMax() + ob.getxMR())) {
                        ob.setFace_to(true);
                    }
                    if (ob.getY() == y + 60 && (ob.getX() > this.x - 100 && ob.getX() < this.x + 30)) {//判断跳上去了
                        onObstacle = true;
                    }
                    //判断是否跳起来顶到砖块
                    if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 100 && ob.getX() < this.x + 30)) {
                        upTime = 0;
                    }

                    //判断是否可以往右走
                    if (ob.getX() == this.x + 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 60)) {
                        canRight = false;
                    }

                    //判断是否可以往左
                    if (ob.getX() == this.x - 100 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 60)) {
                        canLeft = false;
                    }
                }
                if (ob.getType() == 5 || ob.getType() == 6) {//马蜂窝、加强陷阱的眩晕效果
                    if (ob.isChuFa()) {
                        if (dizzyTime >= 0) {
                            canMove = false;
                            dizzyTime--;

                        } else {
                            canMove = true;
                            dizzyTime = 35;
                            ob.setChuFa(false);

                        }
                    }
                }

                if (ob.getType() == 12) {//船的不可穿越

                    if (ob.getY() == this.y + 60 && (ob.getX() > this.x - 100 && ob.getX() < this.x + 30)) {
                        onObstacle = true;
                    }
                    if (ob.getX() == this.x + 30 && ob.getY() < this.y + 60 && ob.getY() > this.y - 40) {
                        canRight = false;
                    }
                    if (ob.getX() == this.x - 100 && ob.getY() < this.y + 60 && ob.getY() > this.y - 40) {
                        canLeft = false;
                    }
                }

                if (ob.getType() == 10) {//向上水流完成
                    if (ob.getRiversExistTime() != 0) {
                        if (ob.getX() > x - 40 && ob.getX() < x + 30 && ob.getY() + 50 <= y) {//在水流范围内
                            yVelocity = -10;
                            upTime += 2;
                        }
                        ob.setRiversExistTime(ob.getRiversExistTime() - 1);
                    }
                    if (ob.getRiversExistTime() == 0) {
                        ob.setRiversDisappearTime(ob.getRiversDisappearTime() - 1);
                        ob.setShow(null);
                    }
                    if (ob.getRiversDisappearTime() == 0) {
                        ob.setRiversExistTime(24);
                        ob.setRiversDisappearTime(80);
                        ob.setShow(StaticValue.obstacleList.get(11));
                    }
                }

            }



            for (int i1 = 0; i1 < backGround.getEnemyList().size(); i1++) {
                Enemy e = backGround.getEnemyList().get(i1);
                if ((e.getX() <= x + 30 && e.getX() > x && (e.getY() > y - 50 && e.getY() < y + 50))) {
                    canRight = false;
                }
                if ((e.getX() >= this.x - 50 && e.getX() < this.x && (e.getY() > this.y - 50 && e.getY() < this.y + 50))) {
                    canLeft = false;
                }
            }
            //攻击敌人
            for (int i = 0; i < backGround.getEnemyList().size(); i++) {
                Enemy e = backGround.getEnemyList().get(i);
                if (((isAttack && e.getX() - this.limits <= this.x && e.getX() >= this.x && (e.getY() <= y && e.getY() + 60 >= y || e.getY() >= y && e.getY() <= y + 60)) || (isAttack && (e.getX() + this.limits >= this.x && e.getX() <= this.x) && (e.getY() <= y && e.getY() + 60 >= y || e.getY() >= y && e.getY() <= y + 60)))) {
                    if (e.getType() != 3) {
                        e.beAttacked(attackAmount);
                    }else {
                        e.beAttacked(1);
                    }
                    e.setAv1(50);
                    if (weaponKind) {
                        dur -= 1;
                        spear.setDurability(dur);
                    } else {
                        dur -= 1;
                        dagger.setDurability(dur);
                    }
                }

            }
            //跳跃的操作
            if (canMove) {
                if (!onCfx) {
                    if (onObstacle && upTime == 0) {
                        if (status.contains("left")) {
                            if ((velocity != 0 || !bgM) && isLM) {
                                status = "move--left";
                            } else {
                                status = "stop--left";
                            }
                        } else {
                            if ((velocity != 0 || !bgM) && isRM) {
                                status = "move--right";
                            } else {
                                status = "stop--right";
                            }
                        }
                    } else {
                        if (upTime != 0) {
                            upTime--;
                        } else {
                            fall();
                        }
                        y += yVelocity;
                    }
                }
            }
            //判断是否允许移动；
            if (canMove) {
                if ((canLeft && velocity < 0) || (canRight && velocity > 0)) {
                    x += velocity;
                    //判断马里奥是否到了最左边
                    if (x < 0) {
                        x = 0;
                    }
                }
            }

            //通过status判断状态从而改变图片
            //判断当前是否是移动状态
            if (weaponKind) {
                if (status.contains("move")) {
                    index = index == 0 ? 1 : 0;
                }
                //判断是否向左移动
                if ("move--left".equals(status)) {
                    show = StaticValue.run_L_LongSword.get(index);
                }
                //判断是否向右移动
                if ("move--right".equals(status)) {
                    show = StaticValue.run_R_LongSword.get(index);
                }
                //判断是否向左停止
                if ("stop--left".equals(status)) {
                    show = StaticValue.stand_L_LongSword;
                }
                //判断是否向右停止
                if ("stop--right".equals(status)) {
                    show = StaticValue.stand_R_LongSword;
                }
                //判断是否向左跳跃
                if ("jump--left".equals(status)) {
                    show = StaticValue.jump_L_LongSword_up;
                }
                //判断是否向右跳跃
                if ("jump--right".equals(status)) {
                    show = StaticValue.jump_R_LongSword_up;
                }
                //判断是否攻击
                if (status.contains("right") && isAttack){
                    index = index == 0 ? 1 : 0;
                    show = StaticValue.attack_R_LongSword.get(index);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (status.contains("left") && isAttack){
                    index = index == 0 ? 1 : 0;
                    show = StaticValue.attack_L_LongSword.get(index);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                if (status.contains("move")) {
                    index = index == 0 ? 1 : 0;
                }
                //判断是否向左移动
                if ("move--left".equals(status)) {
                    show = StaticValue.run_L_ShortSword.get(index);
                }
                //判断是否向右移动
                if ("move--right".equals(status)) {
                    show = StaticValue.run_R_ShortSword.get(index);
                }
                //判断是否向左停止
                if ("stop--left".equals(status)) {
                    show = StaticValue.stand_L_ShortSword;
                }
                //判断是否向右停止
                if ("stop--right".equals(status)) {
                    show = StaticValue.stand_R_ShortSword;
                }
                //判断是否向左跳跃
                if ("jump--left".equals(status)) {
                    show = StaticValue.jump_L_ShortSword_up;
                }
                //判断是否向右跳跃
                if ("jump--right".equals(status)) {
                    show = StaticValue.jump_R_ShortSword_up;
                }
                //判断是否攻击
                if (status.contains("right") && isAttack){
                    index = index == 0 ? 1 : 0;
                    show = StaticValue.attack_R_ShortSword.get(index);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (status.contains("left") && isAttack){
                    index = index == 0 ? 1 : 0;
                    show = StaticValue.attack_L_ShortSword.get(index);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            //背景移动
            if (backGround.getBgX2() != -1200){
                setBgM(getX() < 600);
            }else {
                setBgM(true);
                if (status.contains("move--right") || (status.contains("jump--right") && isRM)){
                    velocity = 5;
                }
                if (status.contains("move--left") || (status.contains("jump--left") && isLM)){
                    velocity = -5;
                }
            }

            if ( (isRM && this.x >= 600 && !this.status.contains("stop") && !this.status.contains("left") && canRight && canMove)){

                velocity = 0;
                backGround.bgMoveXR();

                for (int i = 0; i < backGround.getObstacleList().size(); i++) {
                    Obstacle ob = backGround.getObstacleList().get(i);
                    if (isRM) {
                        ob.OMoveR();
                    }
                }

                for (int i = 0; i < backGround.getEnemyList().size(); i++){
                    Enemy e = backGround.getEnemyList().get(i);
                    if (isRM) {
                        e.EMoveR();
                    }
                }
            }
            if(killNum3==3){
                killNum3=0;
                backGround.addEnemy(new Enemy(this.x+200,this.y,true,3,0,8000,backGround));
                goal1++;
                goal2++;
                goal3++;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public boolean isOK() {
        return isOK;
    }
    public int getBlood() {
        return blood;
    }
    public Music getMusic(){
        return music;
    }
    public void setOK(boolean isOK){
        this.isOK=isOK;
    }
    public int getKillNum(){
        return killNum;
    }
    public void setKillNum(int killNum){
        this.killNum=killNum;
    }
    public int getKillNum3() {
        return killNum3;
    }
    public void setKillNum3(int killNum3) {
        this.killNum3 = killNum3;
    }
    public int getGoal1() {
        return goal1;
    }
    public int getGoal2() {
        return goal2;
    }
    public int getGoal3() {
        return goal3;
    }

}

