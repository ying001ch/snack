package org.wyy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.wyy.domain.Field;
import org.wyy.view.View;

public class SnakeApp {
	public static void main(String[] args) {
		Field field = new Field(15, 15, 4);
		View view = new View(field);
		
		JFrame jFrame = new JFrame("snack");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(view);
		jFrame.pack();
		jFrame.setResizable(false);
		jFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				switch(keyCode) {
				case 37:
					field.setDirction("W");
					break;
				case 38:
					field.setDirction("N");
					break;
				case 39:
					field.setDirction("E");
					break;
				case 40:
					field.setDirction("S");
					break;
				default:
					field.setDirction("O");
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		while(true) {
			
			field.move();
			// 重绘制界面
			jFrame.repaint();
			//
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
