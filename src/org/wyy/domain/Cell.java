package org.wyy.domain;

public class Cell {
	private String color;
	private int flag = 0;
	private int row;
	private int col;
	
	public Cell(int i, int j) {
		this.row = i; this.col = j;
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
	
}
