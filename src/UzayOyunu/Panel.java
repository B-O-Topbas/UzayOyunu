package UzayOyunu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Panel extends JPanel implements KeyListener, ActionListener {
    Timer t;
    ArrayList<Bullet> bullets = new <Bullet>ArrayList();
    ArrayList<Target> targets = new <Target>ArrayList();
    SpaceShip ship = new SpaceShip(0, 200, 30, 30);
    Make make;
    int point=0;
    private BufferedImage spaceShip_img, targetImg;

    public Panel() {
        make = new Make();
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(this);
        make.start();
        t = new Timer(10, this);
        loadPicture();
        t.start();
    }


    private void loadPicture() {
        try {
            spaceShip_img = ImageIO.read(new File("D:\\GamePic\\spaceShip.png"));
            targetImg = ImageIO.read(new File("D:\\GamePic\\targetPic.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.drawImage(spaceShip_img, (int) ship.x, (int) ship.y, (int) ship.width, (int) ship.height, null);
        for (Target target : targets)
            g2d.drawImage(targetImg, (int) target.x, (int) target.y, (int) target.width, (int) target.height, null);
        g2d.setColor(Color.red);
        bullets.removeIf(bullet -> bullet.x > getWidth());
        targets.removeIf(target -> target.x < 0);

        for (Bullet bullet : bullets) g2d.fillOval(bullet.x, bullet.y, 10, 5);
        for (Bullet bullet : bullets)
            for (Target target : targets) {
                if (!new Rectangle(bullet.x, bullet.y, 20, 30).intersects(new Rectangle((int) target.x, (int) target.y, (int) target.width, (int) target.height))) {
                    continue;
                }
                point+=1;
                targets.remove(target);
                bullets.remove(bullet);


            }
        for (Target target:targets){
            if (new Rectangle((int) ship.x, (int) ship.y, (int) ship.width, (int) ship.height).intersects(new Rectangle((int) target.x, (int) target.y, (int) target.width, (int) target.height))){
                t.stop();
                make.stop();
                JOptionPane.showMessageDialog(null,"Kaybettiniz!!");
                System.exit(0);
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Bullet bullet : bullets) {
            bullet.addX(2);
        }
        for (Target target : targets) {
            target.addX(-2);
        }
        repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT && ship.x < getWidth() - 30) {
            ship.addX(3);

        }
        if (key == KeyEvent.VK_LEFT && ship.x > 0) {
            ship.addX(-3);

        }
        if (key == KeyEvent.VK_UP && ship.y > 0) {
            ship.addY(-3);
        }
        if (key == KeyEvent.VK_DOWN && ship.y < getHeight() - 30) {
            ship.addY(3);

        }
        if (key == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet((int) (ship.x + 40), (int) ship.y));
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class Make extends Thread {
        @Override
        public void run() {
            for (int i = 0;100> i; i++) {
                System.out.println(i);
                targets.add(new Target((float) 610, (float) (Math.random() * 300), 30, 30));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
