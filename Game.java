package planewar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.FileInputStream;
import java.util.*;
import java.util.Timer;

public class Game extends JPanel {
    public static int width = 800;
    public static int height = 800;
    private int state;
    private int isboom;
    private static final int START = 0;
    private static final int RUNNING = 1;
    private static final int PAUSE = 2;
    private static final int GAME_OVER = 3;
    private int score = 0;
    private Timer timer;
    private int intervel = 40;
    public static BufferedImage background;
    public static BufferedImage start;
    public static BufferedImage smallplane;
    public static BufferedImage bigplane;
    public static BufferedImage extraplane_n1;
    public static BufferedImage extraplane_n2;
    public static BufferedImage extraplane_hit;
    public static BufferedImage bee;
    public static BufferedImage bullet;
    public static BufferedImage abullet;
    public static BufferedImage boom;
    public static BufferedImage boom0;
    public static BufferedImage myPlane0;
    public static BufferedImage myPlane1;
    public static BufferedImage emberMyPlane0;
    public static BufferedImage emberMyPlane1;
    public static BufferedImage emberMyPlane2;
    public static BufferedImage emberMyPlane3;
    public static BufferedImage pause;
    public static BufferedImage gameover;

    private ArrayList<Planefather> flyings = new ArrayList<Planefather>();
    private ArrayList<Planefather> newflyings = new ArrayList<Planefather>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> bbullets = new ArrayList<Bullet>();
    private ArrayList<Abullet> abullets = new ArrayList<Abullet>();
    private MyPlane myplane = new MyPlane();
    int flyEnteredIndex = 0;
    int shootIndex = 0;

    static {
        try {
            background = ImageIO.read(Game.class.getResource("image/background.png"));
            start = ImageIO.read(Game.class.getResourceAsStream("image/start.png"));
            smallplane = ImageIO.read(Game.class.getResourceAsStream("image/smallplane.png"));
            bigplane = ImageIO.read(Game.class.getResourceAsStream("image/bigplane.png"));
            extraplane_n1 = ImageIO.read(Game.class.getResourceAsStream("image/extraplane_n1.png"));
            extraplane_n2 = ImageIO.read(Game.class.getResourceAsStream("image/extraplane_n2.png"));
            extraplane_hit = ImageIO.read(Game.class.getResourceAsStream("image/extraplane_hit.png"));
            bee = ImageIO.read(Game.class.getResourceAsStream("image/bee.png"));
            bullet = ImageIO.read(Game.class.getResourceAsStream("image/bullet.png"));
            abullet = ImageIO.read(Game.class.getResourceAsStream("image/abullet.png"));
            boom = ImageIO.read(Game.class.getResourceAsStream("image/boom.png"));
            boom0 = ImageIO.read(Game.class.getResourceAsStream("image/boom0.png"));
            myPlane0 = ImageIO.read(Game.class.getResourceAsStream("image/myplane0.png"));
            myPlane1 = ImageIO.read(Game.class.getResourceAsStream("image/myplane1.png"));
            emberMyPlane0 = ImageIO.read(Game.class.getResourceAsStream("image/myplane_ember0.png"));
            emberMyPlane1 = ImageIO.read(Game.class.getResourceAsStream("image/myplane_ember1.png"));
            emberMyPlane2 = ImageIO.read(Game.class.getResourceAsStream("image/myplane_ember2.png"));
            emberMyPlane3 = ImageIO.read(Game.class.getResourceAsStream("image/myplane_ember3.png"));
            myPlane1 = ImageIO.read(Game.class.getResourceAsStream("image/myplane1.png"));
            pause = ImageIO.read(Game.class.getResourceAsStream("image/pause.png"));
            gameover = ImageIO.read(Game.class.getResourceAsStream("image/gameover.png"));
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, (ImageObserver) null);
        this.paintBullets(g);
        this.paintFlyingObjects(g);
        this.paintmyplane(g);
        this.paintScore(g);
        this.paintState(g);
    }


    public void paintBullets(Graphics g) {
        for (int i = 0; i < abullets.size(); i++) {
            Abullet ab = this.abullets.get(i);
            g.drawImage(ab.getImage(), ab.getX() - ab.getWidth() / 2, ab.getY(), (ImageObserver) null);
        }
        for (int i = 0; i < this.bullets.size(); i++) {
            Bullet b = this.bullets.get(i);
            g.drawImage(b.getImage(), b.getX() - b.getWidth() / 2, b.getY(), (ImageObserver) null);
        }

    }

    public void paintFlyingObjects(Graphics g) {
        for (int i = 0; i < this.flyings.size(); ++i) {
            Planefather f = this.flyings.get(i);
            if (f.getImage() == extraplane_hit) {
                Extraplane e = (Extraplane) f;
                int x = (e.getWidth() * e.getLife()) / e.getMaxlife();
                g.setColor(Color.BLACK);
                g.drawRect(e.getX() - 8, e.getY(), e.getWidth(), 5);
                g.setColor(Color.darkGray);
                g.fillRect(e.getX() - 8, e.getY(), x, 5);
            }
            g.drawImage(f.getImage(), f.getX(), f.getY(), (ImageObserver) null);
        }

    }

    public void paintmyplane(Graphics g) {
        g.drawImage(this.myplane.getImage(), this.myplane.getX(), this.myplane.getY(), (ImageObserver) null);
    }

    public void paintScore(Graphics g) {
        int x = 10;
        int y = 25;
        Font font = new Font("", 1, 20);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("📍SCORE:  " + this.score, x, y);
        y = y + 20;
        g.drawString("❤LIFE:  ", x, y);
        y = y + 20;
        g.drawString("🚀BOOM:  ", x, y);
        y -= 20;
        x += 50;
        String a = "";
        String b = "";
        g.setColor(Color.red);
        for (int i = 0; i < this.myplane.getLife(); i++) {
            a += "  ❤";
        }
        g.drawString(a, x + 20, y);
        y += 20;
        g.setColor(Color.black);
        x += 10;
        for (int i = 0; i < this.myplane.getBoom(); i++) {
            b += "  🚀";
        }
        g.drawString(b, x + 20, y);


    }

    public void paintState(Graphics g) {
        switch (this.state) {
            case 0:
                g.drawImage(start, 0, 0, (ImageObserver) null);
            case 1:
            default:
                break;
            case 2:
                g.drawImage(pause, 0, 0, (ImageObserver) null);
                break;
            case 3:
                g.drawImage(gameover, 0, 0, (ImageObserver) null);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        //窗口大小
        frame.setSize(width, height);
        //窗口位置
        frame.setLocationRelativeTo((Component) null);
        //窗口置顶
        //frame.setAlwaysOnTop(true);
        // 窗口名字
        frame.setName("飞机大战-dv");
        //关闭类型
        frame.setDefaultCloseOperation(3);//JFrame.EXIT_ON_CLOSE=3
        //实例化对象
        Game game = new Game();
        //添加画板进窗口
        frame.add(game);
        //显示窗口
        frame.setVisible(true);
        game.action(frame);
    }

    public void action(JFrame frame) {

        MouseAdapter l = new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                if (Game.this.state == 1) {
                    int x = e.getX();
                    int y = e.getY();
                    Game.this.myplane.move(x, y);

                }

            }

            public void mouseEntered(MouseEvent e) {
                if (Game.this.state == 2) {
                    Game.this.state = 1;
                }

            }

            public void mouseExited(MouseEvent e) {
                if (Game.this.state == 1) {
                    Game.this.state = 2;
                }

            }

            public void mouseClicked(MouseEvent e) {
                switch (Game.this.state) {
                    case 0:
                        Game.this.state = 1;
                    case 1:
                    case 2:
                    default:
                        break;
                    case 3:
                        Game.this.flyings = new ArrayList<Planefather>();
                        Game.this.bullets = new ArrayList<Bullet>();
                        Game.this.abullets = new ArrayList<Abullet>();
                        Game.this.myplane = new MyPlane();
                        Game.this.score = 0;
                        Game.this.state = 0;
                }

            }
        };
        //鼠标监听器
        this.addMouseListener(l);
        this.addMouseMotionListener(l);
        //计时器
        this.timer = new Timer();
        //键盘监听器
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    isboom = 1;
                }
            }
        });
        this.timer.schedule(new TimerTask() {
            public void run() {
                if (Game.this.state == 1) {
                    Game.this.enterAction();
                    Game.this.stepAction();
                    Game.this.shootAction();
                    Game.this.shootboom();
                    Game.this.bangAction();
                    Game.this.outOfBoundsAction();
                    Game.this.checkGameOverAction();
                    Game.this.isalifes();
                    Game.this.setlv();
                }
                Game.this.repaint();//页面刷新
            }
        }, (long) this.intervel, (long) this.intervel);
    }

    public void setlv() {
        Planefather.lv = this.score / 30;
    }

    //发射炸弹💣
    public void shootboom() {
        if (isboom == 1 && myplane.getBoom() > 0)
            this.bullets.add(myplane.boom());
        isboom = 0;
    }

    //生成敌机
    public void enterAction() {
        ++this.flyEnteredIndex;
        if (this.flyEnteredIndex % 20 == 0) {//每800ms次生成一架敌机
            Planefather obj = nextOne();
            this.flyings.add(obj);
        }
    }

    //飞机和子弹和敌机的运动
    public void stepAction() {
        int i;
        for (i = 0; i < this.flyings.size(); ++i) {
            Planefather f = this.flyings.get(i);
            if (f instanceof Extraplane) {
                Extraplane e = (Extraplane) f;
                if (e.sstep()) {
                    abullets.add(e.Eshoot());
                }
            }
            f.step();
        }
        for (i = 0; i < this.bullets.size(); ++i) {
            Bullet b = this.bullets.get(i);
            b.step();
        }
        for (i = 0; i < this.abullets.size(); ++i) {
            Abullet abullet = this.abullets.get(i);
            abullet.step();
        }
        this.myplane.step();
    }

    //飞机射击
    public void shootAction() {
        ++this.shootIndex;
        if (this.shootIndex % 5 == 0) {
            ArrayList<Bullet> bu = this.myplane.shoot();
            for (int i = 0; i < bu.size(); i++) {
                this.bullets.add(bu.get(i));
            }
        }

    }

    //碰撞检测
    public void bangAction() {
        for (int i = 0; i < this.bullets.size(); ++i) {
            Bullet b = this.bullets.get(i);
            if (b instanceof Boom) {
            } else
                this.bang(b);
        }
    }

    ///是否超出屏幕
    public void outOfBoundsAction() {
        for (int i = 0; i < this.flyings.size(); i++) {
            if (this.flyings.get(i).outOfBounds()) {
                this.flyings.remove(i);
                i--;
            }
        }
        for (int i = 0; i < this.bullets.size(); i++) {
            if (!this.bullets.get(i).outOfBounds()) {
                if (this.bullets.get(i) instanceof Boom) {
                    boom();
                    bullets.add(((Boom) this.bullets.get(i)).boom());
                }
                this.bullets.remove(i);
                i--;
            }
        }
        for (int i = 0; i < this.abullets.size(); i++) {
            if (this.abullets.get(i).outOfBounds()) {
                this.abullets.remove(i);
                i--;
            }
        }
    }

    //炸弹爆炸
    public void boom() {
        for (int i = 0; i < flyings.size(); i++) {
            Planefather temp = this.flyings.get(i);
            temp.setLife(0);
            awards(temp, i);
            abullets.removeAll(abullets);
        }
    }

    //判断游戏结束
    public void checkGameOverAction() {
        if (this.isGameOver()) {
            this.state = 3;
        }

    }

    //存活判断并移除死亡的飞机
    public void isalifes() {
        for (int i = 0; i < flyings.size(); i++) {
            Planefather temp = flyings.get(i);
            if (!temp.isalife()) {
                flyings.remove(i);
                i--;
            }
        }
        for (int i = 0; i < newflyings.size(); i++) {
            flyings.add(newflyings.get(i));
        }
        for (int i = 0; i < bbullets.size(); i++) {
            bullets.add(bbullets.get(i));
        }
        bbullets.removeAll(bbullets);
        newflyings.removeAll(newflyings);
    }

    //我的飞机碰撞检测
    public boolean isGameOver() {
        for (int i = 0; i < this.flyings.size(); i++) {
            Planefather obj = this.flyings.get(i);
            if (this.myplane.hit(obj)) {
                this.myplane.subtractLife();
                this.myplane.subFire();
                this.myplane.setIsProtect(10);
                newflyings.add(this.flyings.get(i) instanceof Enemy ? ((Enemy) this.flyings.get(i)).death() : ((Award) this.flyings.get(i)).death());
                flyings.remove(i);
                i--;
            }
        }
        for (int i = 0; i < abullets.size(); i++) {
            Abullet abu = this.abullets.get(i);
            if (this.myplane.hit(abu)) {
                this.myplane.subtractLife();
                this.myplane.subFire();
                this.myplane.setIsProtect(10);
                abullets.remove(i);
                i--;
            }
        }
        Game.this.repaint();
        if (this.myplane.getLife() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    //子弹和敌机碰撞
    public void bang(Bullet bullet) {
        Planefather temp;
        for (int i = 0; i < this.flyings.size(); ++i) {
            temp = this.flyings.get(i);
            if (temp.shootBy(bullet)) {
                this.bbullets.add(bullet.boom());
                this.bullets.remove(bullet);
                awards(temp, i);
            }
        }
    }

    //判断是否获得奖励
    public void awards(Planefather temp, int i) {
        if (temp instanceof Smallplane) {
            Enemy e = (Smallplane) temp;
            this.flyings.get(i).subtractLife();
            if (!e.isalife()) {
                this.score += e.getScore();
                newflyings.add(e.death());
            }

        } else if (temp instanceof Bigplane) {
            Enemy e = (Bigplane) temp;
            this.flyings.get(i).subtractLife();
            if (!e.isalife()) {
                this.score += e.getScore();
                newflyings.add(e.death());
            }
        } else if (temp instanceof Extraplane) {
            Enemy e = (Extraplane) temp;
            this.flyings.get(i).subtractLife();
            if (!e.isalife()) {
                this.score += e.getScore();
                newflyings.add(e.death());
            }
        } else if (temp instanceof Award) {
            Award a = (Award) temp;
            int type = a.getType();
            this.flyings.get(i).subtractLife();
            switch (type) {
                case 0:
                    this.myplane.addFire();
                    break;
                case 1:
                    this.myplane.addLife();
                    break;
                case 2:
                    this.myplane.addboom();
                    break;
            }
            newflyings.add(a.death());
        }
    }

    //随机生成敌机
    public static Planefather nextOne() {
        Random random = new Random();
        int type = random.nextInt(100);
        if (type < 60) {
            return (Planefather) new Smallplane();
        } else if (type < 80) {
            return (Planefather) new Bigplane();
        } else if (type < 90) {
            return (Planefather) new Bee();
        } else {
            if (Planefather.lv > 3)
                return (Planefather) new Extraplane();
            return (Planefather) new Bigplane();
        }

    }
}
