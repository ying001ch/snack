package org.wyy.domain;

import java.util.LinkedList;

public class Field {
	private Cell[][] elements;
	private int rowNum;
	private int colNum;
	private Snack snack;
	private String dirction = "E";
	
	public Field(int rowNum,int colNum,int snackLength) {
		this.rowNum = rowNum;
		this.colNum = colNum;
		
		elements = new Cell[rowNum][colNum];
		for (int i=0;i<rowNum;i++) {
			for (int j=0;j<colNum;j++) {
				elements[i][j] = new Cell(i,j);
			}
		}
		
		snack = new Snack(snackLength);
		
	}
	
	public class Snack {
		private int length;
		
		private LinkedList<Cell> list = new LinkedList<>();
		public Snack(int snackLength) {
			length = snackLength;
			for(int i=0;i<snackLength;i++) {
				elements[rowNum-2][i].setFlag(snackLength-i);
				list.addFirst(elements[rowNum-2][i]);
			}
		}
		
		public void move(String dirction) {
			if(dirction.equals("O")) {
				dirction = Field.this.dirction;
			}
			
			int r = 0;
			int c = 0;
			for (Cell cell:list) {
				int flag = cell.getFlag();
				int i = cell.getRow();
				int j = cell.getCol();
				if(flag == 1) {
					switch(dirction) {
					case "E": 
						r = i;
						c = j+1;
						if(c >= colNum) {
							c = colNum - 1;
						}
						break;
					case "W": 
						r = i;
						c = j-1;
						if(c < 0) {
							c = 0;
						}
						break;
					case "S":
						r = i + 1;
						c = j;
						if(r >= rowNum) {
							r = rowNum -1;
						}
						break;
					case "N": 
						r = i - 1;
						c = j;
						if(r < 0) {
							r = 0;
						}
						break;
					
					}
					elements[r][c].setFlag(1);
				}
				if(flag == length) {
					cell.setFlag(0);
				}else {
					cell.grow();
				}
			}
			list.addFirst(elements[r][c]);
			list.removeLast();
			Field.this.dirction = dirction;
		}
		
		
	}
	
	
	public Cell[][] getElements() {
		return elements;
	}

	public void setElements(Cell[][] elements) {
		this.elements = elements;
	}

	public void move() {
		snack.move(dirction);
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public String getDirction() {
		return dirction;
	}

	public void setDirction(String dirction) {
		this.dirction = dirction;
	}
	
}
