package org.wyy.domain;

import java.util.LinkedList;

public class Field {
	private Cell[][] elements;
	private int rowNum;
	private int colNum;
	private Snack snack;
	private String dirction = "E";
	
	private boolean hasFood = false;
	
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
	
	
	public Cell getNeighbor(Cell cell,String directio) {
		int row = cell.getRow();
		int col = cell.getCol();
		switch(dirction) {
		case "E": 
			col++;
			break;
		case "W": 
			col--;
			break;
		case "S":
			row++;
			break;
		case "N": 
			row--;
			break;
		default:throw new RuntimeException("方向不对，只能东南西北");
		}
		
		if(row < 0) {
			row = rowNum-1;
		}else if(row >= rowNum) {
			row = 0;
		}
		if(col < 0) {
			col = colNum-1;
		}else if(col >= colNum) {
			col = 0;
		}
		
		return elements[row][col];
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
			
			makeFood();
		}
		/**
		 * 随机产生食物,不能产生在蛇的身上
		 */
		public void makeFood() {
			while(true) {
				int rrow = (int)(Math.random()*rowNum);
				
				int rcol = (int)(Math.random()*colNum);
				if(!elements[rrow][rcol].inSnack()) {
					elements[rrow][rcol].setFood();
					break;
				}
			}
		}
		
		public void move(String dirction) {
			if(dirction.equals("O")) {
				dirction = Field.this.dirction;
			}
			
			Cell first = list.getFirst();
			Cell newFir = getNeighbor(first, dirction);
			boolean isFood = newFir.isFood();
			if(!isFood) {
				list.removeLast().setFlag(0);
			}else {
				length++;
			}
			newFir.setFlag(0);
			list.addFirst(newFir);
			
			for (Cell cell:list) {
				cell.grow();
			}
			Field.this.dirction = dirction;
			if(isFood) {
				makeFood();
				// 如果吃了食物，就再往前移动一次
				move(dirction);
			}
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
