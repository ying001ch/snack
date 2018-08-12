package org.wyy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import org.wyy.domain.Field;
import org.wyy.view.View;

public class SnakeApp {
	private boolean isPlaying = false;
	private boolean isOver = false;
	
	public static void main(String[] args) {
		new SnakeApp().play();
	}

	private void play() {
		Field field = new Field(15, 15, 4);
		View view = new View(field);
		
		JFrame jFrame = new JFrame("snack");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.add(view);
		jFrame.pack();
		jFrame.setResizable(false);
//		boolean isPlaying = true;
		jFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				System.out.println("keycode: "+keyCode);
				if(isOver) {
					switch(keyCode) {
					case 27:  // esc
						field.reset();
						isOver = false;
						synchronized(View.class) {
							View.class.notifyAll();
						}
						
						break;
					}
				}else {
					if(isPlaying) {
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
					switch(keyCode) {
					case 32:  // 空格键
						if(!isPlaying) {
							isPlaying = true;
							synchronized(View.class) {
								View.class.notifyAll();
							}
						}else {
							isPlaying = false;
						}
						break;
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
//		loop(field, jFrame);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						jFrame.repaint();
						if(!isOver && !isPlaying) {
							synchronized(View.class) {
								View.class.wait();
							}
							loop(field, jFrame);
						}
						if(isOver) {
							synchronized(View.class) {
								View.class.wait();
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void loop(Field field, JFrame jFrame) {
		isPlaying = true;
		while(true) {
			jFrame.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(!isPlaying) {
				System.out.println("暂停");
				break;
			}
			if(!field.move()) {
				isOver = true;
				isPlaying = false;
				System.out.println("撞到自己或者障碍物 结束");
				break;
			}
			// 重绘制界面
			//
		}
	}
}
