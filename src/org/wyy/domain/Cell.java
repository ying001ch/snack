package org.wyy.domain;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private String color;
	/**
	 * 0代表空格； 正数代表蛇身，1是蛇头；-1是食物；-2 障碍物
	 */
	private int flag = 0;
	private int row;
	private int col;
	
	public Cell(int i, int j) {
		this.row = i; this.col = j;
	}

	public void paint(Graphics g, int x, int y,int gradeSize) {
		g.setColor(Color.BLACK);
		g.drawRect(gradeSize*x, gradeSize*y, gradeSize, gradeSize);
		if(flag == -2) {
			g.setColor(Color.RED);
			g.fillRect(gradeSize*x, gradeSize*y, gradeSize, gradeSize);
		}else if(flag == -1) {
			g.setColor(Color.green);
			g.fillRect(gradeSize*x, gradeSize*y, gradeSize, gradeSize);
		}if(flag != 0) {
			g.fillRect(gradeSize*x, gradeSize*y, gradeSize, gradeSize);
		}
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void grow() {
		this.flag++;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	public void setFood() {
		setFlag(-1);
	}
	public boolean isFood() {
		return flag == -1;
	}
	public boolean isBarrier() {
		return flag == -2;
	}

	public boolean inSnack() {
		return flag>0;
	}

	public void setBarrier() {
		flag = -2;
	}
}
