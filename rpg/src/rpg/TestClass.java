package rpg;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class TestClass {
	

	public static void main(String args[]) {
		new UItest();
	}
	
}

class  UItest extends JFrame{
	
	
	
	boolean MotionEnd;
	float gravity;
	Toolkit kit;
	Image img = null;
	UItest(){
		super("test");
		gravity = 10;
		MotionEnd = false;
		kit = Toolkit.getDefaultToolkit();
		setSize(200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		PTest p = new PTest();
		add(p);
		addKeyListener(p);
		Timer t = new Timer(10, (e)-> {
			p.repaint();
		});
		Timer t2 = new Timer(10, (e)-> {
			if(p.attacknow == true) {
				p.ac++;
				if(p.ac>=35) {
					p.attacknow = false;
					p.ac = 0;
					img = kit.createImage("img\\motion.gif");
		
				}
			}
		});
		Timer t3 = new Timer(30, (e)-> {
			if(p.isJump == true) {
				p.y-=gravity;
				gravity-=1;
				if(p.y>=50) {
					p.isJump = false;
					gravity = 10;
					img = kit.createImage("img\\motion.gif");
				}
			}
		});
		t.start();
		t2.start();
		t3.start();
		setVisible(true);
	}
	
	class PTest extends JPanel implements KeyListener{
		boolean isJump;
		boolean attacknow;
		int ac;
		int ju;
		int x,y;
		
		PTest() {
			attacknow = false;
			isJump = false;
			ac = 0;
			ju = 0;
			x = 0; y = 50;
			img = kit.createImage("img\\motion.gif");
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(img!=null) {
				g.drawImage(img, x, y, this);
			}
		}
		
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (isJump == false) {
					img = kit.getImage("img\\n.png");
					isJump = true;
				}
			}else if(e.getKeyCode()==KeyEvent.VK_CONTROL) {
				if(attacknow ==false) {
					attacknow = true;
					img = kit.createImage("img\\attack.gif");
				}
			}
		}
		public void keyReleased(KeyEvent e) {}

	}
	
}
