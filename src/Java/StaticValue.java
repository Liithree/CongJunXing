package Java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    //过关条件底色
    public static BufferedImage pass=null;
    //背景
    public static BufferedImage bg_BeiGuo = null;
    public static BufferedImage bg_PingYuan = null;
    public static BufferedImage bg_NanFang=null;
    //选择关卡界面
    public static BufferedImage chooseMenu1 = null;
    public static BufferedImage chooseMenu2 = null;
    public static BufferedImage chooseMenu3 = null;
    public static BufferedImage jump_L_LongSword_up = null;
    //
    public static BufferedImage jump_R_LongSword_up = null;
    //
    public static BufferedImage jump_L_ShortSword_up = null;
    //
    public static BufferedImage jump_R_ShortSword_up = null;
    //
    public static BufferedImage jump_L_Bow_up = null;
    //
    public static BufferedImage jump_R_Bow_up = null;
    //
    public static BufferedImage jump_L_LongSword_down = null;
    //
    public static BufferedImage jump_R_LongSword_down = null;
    //
    public static BufferedImage jump_L_ShortSword_down = null;
    //
    public static BufferedImage jump_R_ShortSword_down = null;
    //
    public static BufferedImage jump_L_Bow_down = null;
    //
    public static BufferedImage jump_R_Bow_down = null;
    //马里奥向左站立
    public static BufferedImage stand_L_LongSword = null;
    public static BufferedImage stand_L_ShortSword =null;
    //马里奥向右站立
    public static BufferedImage stand_R_LongSword = null;
    public static BufferedImage stand_R_ShortSword =null;
    //旗子
    public static BufferedImage flag=null;
    //生命值
    public static BufferedImage hp=null;

    //提示信息
    public static BufferedImage tips1=null;
    public static BufferedImage tips2=null;
    //拿长剑向左攻击
    public static List<BufferedImage> attack_L_LongSword = new ArrayList<>();
    //拿长剑向右攻击
    public static List<BufferedImage> attack_R_LongSword = new ArrayList<>();
    //拿短剑向左攻击
    public static List<BufferedImage> attack_L_ShortSword = new ArrayList<>();
    //拿短剑向右攻击
    public static List<BufferedImage> attack_R_ShortSword = new ArrayList<>();
    //拿弓向左攻击
    public static List<BufferedImage> attack_L_Bow = new ArrayList<>();
    //拿弓向右攻击
    public static List<BufferedImage> attack_R_Bow = new ArrayList<>();
    //障碍物
    public static List<BufferedImage> obstacleList = new ArrayList<>();
    //跑
    public static List<BufferedImage> run_L_LongSword = new ArrayList<>();
    public static List<BufferedImage> run_R_LongSword = new ArrayList<>();
    public static List<BufferedImage> run_L_ShortSword = new ArrayList<>();
    public static List<BufferedImage> run_R_ShortSword = new ArrayList<>();
    public static List<BufferedImage> run_L_Bow = new ArrayList<>();
    public static List<BufferedImage> run_R_Bow = new ArrayList<>();
    //敌人1向左跑
    public static List<BufferedImage> enemy1_L_Run = new ArrayList<>();
    //敌人1向右跑
    public static List<BufferedImage> enemy1_R_Run = new ArrayList<>();
    //敌人2向左跑
    public static List<BufferedImage> enemy2_L_Run = new ArrayList<>();
    //敌人2向右跑
    public static List<BufferedImage> enemy2_R_Run = new ArrayList<>();
    //敌人1向左攻击
    public static List<BufferedImage> enemy1_L_Attack = new ArrayList<>();
    //敌人1向右攻击
    public static List<BufferedImage> enemy1_R_Attack = new ArrayList<>();
    //敌人2向左攻击
    public static List<BufferedImage> enemy2_L_Attack = new ArrayList<>();
    //敌人2向右攻击
    public static List<BufferedImage> enemy2_R_Attack = new ArrayList<>();
    //敌人1向左死亡
    public static List<BufferedImage> enemy1_L_Die = new ArrayList<>();
    //敌人1向右死亡
    public static List<BufferedImage> enemy1_R_Die = new ArrayList<>();
    //敌人2向左死亡
    public static List<BufferedImage> enemy2_L_Die = new ArrayList<>();
    //敌人2向右死亡
    public static List<BufferedImage> enemy2_R_Die = new ArrayList<>();
    //路径的前缀,方便后续调用
    public static String path = System.getProperty("user.dir") + "/src/image/";
    public static String path3 = System.getProperty("user.dir") + "/src/image/敌人1/";
    public static String path4 = System.getProperty("user.dir") + "/src/image/敌人2/";

    //初始化方法
    public static void init() {
        try {
            //加载背景图片
            bg_BeiGuo = ImageIO.read(new File(path + "北国背景.png"));
            bg_NanFang = ImageIO.read(new File(path + "南方背景.png"));
            bg_PingYuan=ImageIO.read(new File(path+"平原背景.png"));
            //加载选择关卡界面
            chooseMenu1 = ImageIO.read(new File(path+"menu1.png"));
            chooseMenu2 = ImageIO.read(new File(path+"menu2.png"));
            chooseMenu3 = ImageIO.read(new File(path+"menu3.png"));
            //加载过关条件
            pass= ImageIO.read(new File(path+"过关条件底.png"));
            //加载旗子
            flag = ImageIO.read(new File(path+"flag.png"));
            //加载生命值
            hp=ImageIO.read(new File(path+"生命值.png"));
            //加载提示牌
            tips1=ImageIO.read(new File(path+"提示信息1.png"));
            tips2= ImageIO.read(new File(path+"提示信息2.png"));
            //加载主角向左站立
            stand_L_LongSword=ImageIO.read(new File(path+"拿长剑向左站立.png"));
            stand_L_ShortSword=ImageIO.read(new File(path+"拿短剑向左站立.png"));
            //加载主角向右站立
            stand_R_LongSword=ImageIO.read(new File(path+"拿长剑向右站立.png"));
            stand_R_ShortSword=ImageIO.read(new File(path+"拿短剑向右站立.png"));
            //加载跳跃上升
            jump_L_LongSword_up=ImageIO.read(new File(path+"拿长剑向左跳上升.png"));
            jump_R_LongSword_up=ImageIO.read(new File(path+"拿长剑向右跳上升.png"));
            jump_L_ShortSword_up=ImageIO.read(new File(path+"拿短剑向左跳上升.png"));
            jump_R_ShortSword_up=ImageIO.read(new File(path+"拿短剑向右跳上升.png"));
            jump_L_Bow_up=ImageIO.read(new File(path+"拿弓向左跳上升.png"));
            jump_R_Bow_up=ImageIO.read(new File(path+"拿弓向右跳上升.png"));
            //加载跳跃下降
            jump_L_LongSword_down=ImageIO.read(new File(path+"拿长剑向左下降.png"));
            jump_R_LongSword_down=ImageIO.read(new File(path+"拿长剑向右下降.png"));
            jump_L_ShortSword_down=ImageIO.read(new File(path+"拿短剑向左下降.png"));
            jump_R_ShortSword_down=ImageIO.read(new File(path+"拿短剑向右下降.png"));
            jump_L_Bow_down=ImageIO.read(new File(path+"拿弓向左下降.png"));
            jump_R_Bow_down=ImageIO.read(new File(path+"拿弓向右下降.png"));
            //加载跑
            for (int i = 1;i <= 2;i++) {
                try {
                    run_L_LongSword.add(ImageIO.read(new File(path + "拿长剑向左跑"+ i +".png")));
                    run_R_LongSword.add(ImageIO.read(new File(path + "拿长剑向右跑"+ i +".png")));
                    run_L_ShortSword.add(ImageIO.read(new File(path + "拿短剑向左跑"+ i +".png")));
                    run_R_ShortSword.add(ImageIO.read(new File(path + "拿短剑向右跑"+ i +".png")));
                    run_L_Bow.add(ImageIO.read(new File(path + "拿弓向左跑"+ i +".png")));
                    run_R_Bow.add(ImageIO.read(new File(path + "拿弓向右跑"+ i +".png")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //加载向左拿长剑攻击
        for(int i=1;i<=3;i++){
            try {
                attack_L_LongSword.add(ImageIO.read(new File(path + "拿长剑向左攻击"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //加载向右拿长剑攻击
        for(int i=1;i<=3;i++){
            try {
                attack_R_LongSword.add(ImageIO.read(new File(path + "拿长剑向右攻击"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //加载向左拿短剑攻击
        for(int i=1;i<=3;i++){
            try {
                attack_L_ShortSword.add(ImageIO.read(new File(path + "拿短剑向左攻击"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //加载向右拿短剑攻击
        for(int i=1;i<=3;i++){
            try {
                attack_R_ShortSword.add(ImageIO.read(new File(path + "拿短剑向右攻击"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //加载向左拿弓攻击
        for(int i=1;i<=3;i++){
            try {
                attack_L_Bow.add(ImageIO.read(new File(path + "拿弓向左攻击"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        //加载向右拿弓攻击
        for(int i=1;i<=3;i++){
            try {
                attack_R_Bow.add(ImageIO.read(new File(path + "拿弓向右攻击"+i+".png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        try {
            //加载障碍物
            obstacleList.add(ImageIO.read(new File(path + "平原地砖.png")));
            obstacleList.add(ImageIO.read(new File(path + "北国地砖.png")));
            obstacleList.add(ImageIO.read(new File(path + "南方地砖.png")));
            obstacleList.add(ImageIO.read(new File(path + "长方形地砖.png")));
            obstacleList.add(ImageIO.read(new File(path + "普通陷阱.png")));
            obstacleList.add(ImageIO.read(new File(path + "隐形陷阱.png")));
            obstacleList.add(ImageIO.read(new File(path + "马蜂窝.png")));
            obstacleList.add(ImageIO.read(new File(path + "加强普通陷阱.png")));
            obstacleList.add(ImageIO.read(new File(path + "冰锥.png")));
            obstacleList.add(ImageIO.read(new File(path + "水藻.png")));
            obstacleList.add(ImageIO.read(new File(path + "鸟.png")));
            obstacleList.add(ImageIO.read(new File(path + "向上水流.png")));
            obstacleList.add(ImageIO.read(new File(path + "水面.png")));
            obstacleList.add(ImageIO.read(new File(path + "船.png")));
            obstacleList.add(ImageIO.read(new File(path + "灌木.png")));
            obstacleList.add(ImageIO.read(new File(path + "r鸟.png")));
            obstacleList.add(ImageIO.read(new File(path + "yellow.png")));
            obstacleList.add(ImageIO.read(new File(path + "red.png")));
            obstacleList.add(ImageIO.read(new File(path + "huangse.png")));
            obstacleList.add(ImageIO.read(new File(path + "红色.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //加载敌人1跑  敌人1向左奔跑 (1).png
        for (int i = 1;i <= 8;i++) {
            try {
                enemy1_L_Run.add(ImageIO.read(new File(path3 + "敌人1向左奔跑 ("+i+").png")));
                enemy1_R_Run.add(ImageIO.read(new File(path3 + "敌人1向右奔跑 ("+i+").png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            enemy1_R_Run.add(ImageIO.read(new File(path3 + "walk2.png")));
            enemy1_R_Run.add(ImageIO.read(new File(path3 + "walk3.png")));
            enemy1_R_Run.add(ImageIO.read(new File(path3 + "walk4.png")));
            enemy1_L_Run.add(ImageIO.read(new File(path3 + "rwalk2.png")));
            enemy1_L_Run.add(ImageIO.read(new File(path3 + "rwalk3.png")));
            enemy1_L_Run.add(ImageIO.read(new File(path3 + "rwalk4.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //加载敌人1死亡
        for (int i = 1;i <= 9;i++) {
            try {
                enemy1_L_Die.add(ImageIO.read(new File(path3 + "敌人1向左倒下 ("+i+").png")));
                enemy1_R_Die.add(ImageIO.read(new File(path3 + "敌人1向右倒下 ("+i+").png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //加载敌人1攻击
        for (int i = 1;i <= 8;i++) {
            try {
                enemy1_L_Attack.add(ImageIO.read(new File(path3 + "向左攻击 1 ("+i+").png")));
                enemy1_R_Attack.add(ImageIO.read(new File(path3+ "向右攻击 1 ("+i+").png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            enemy1_L_Attack.add(ImageIO.read(new File(path3 + "ratk1.png")));
            enemy1_L_Attack.add(ImageIO.read(new File(path3 + "ratk2.png")));
            enemy1_R_Attack.add(ImageIO.read(new File(path3 + "atk1.png")));
            enemy1_R_Attack.add(ImageIO.read(new File(path3 + "atk2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //加载敌人2跑
        for (int i = 1;i <= 8;i++) {
            try {
                enemy2_L_Run.add(ImageIO.read(new File(path4 + "敌人2向左跑"+i+".png")));
                enemy2_R_Run.add(ImageIO.read(new File(path4 + "敌人2向右跑"+i+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //加载敌人2死亡
        for (int i = 1;i <= 8;i++) {
            try {
                enemy2_L_Die.add(ImageIO.read(new File(path4 + "敌人2向左死亡"+i+".png")));
                enemy2_R_Die.add(ImageIO.read(new File(path4 + "敌人2向右死亡"+i+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //加载敌人2攻击
        for (int i = 1;i <= 3;i++) {
            try {
                enemy2_L_Attack.add(ImageIO.read(new File(path4 + "敌人2向左攻击"+i+".png")));
                enemy2_R_Attack.add(ImageIO.read(new File(path4 + "敌人2向右攻击"+i+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
