package org.wyy.domain;

import java.util.LinkedList;

public class Field {
	private Cell[][] elements;
	private int rowNum;
	private int colNum;
	private Snake snack;
	private String preDirection = "E";
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
		
		snack = new Snake(snackLength);
		makeBarrier();
	}
	
	private void makeBarrier(){
		elements[6][6].setBarrier();
		elements[6][7].setBarrier();
		elements[6][8].setBarrier();
	}
	
	public Cell getNeighbor(Cell cell,String directio) {
		int row = cell.getRow();
		int col = cell.getCol();
		switch(directio) {
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
	public class Snake {
		private int initLength;
		
		private LinkedList<Cell> list = new LinkedList<>();
		
		public Snake(int snakeLength) {
			initLength = snakeLength;
			reset();
		}
		/**
		 * 随机产生食物,不能产生在蛇的身上
		 */
		public void makeFood() {
			while(true) {
				int rrow = (int)(Math.random()*rowNum);
				
				int rcol = (int)(Math.random()*colNum);
				if(!elements[rrow][rcol].inSnake() && !elements[rrow][rcol].isBarrier()) {
					elements[rrow][rcol].setFood();
					break;
				}
			}
		}
		
		public boolean move(String dirction) {
			if(dirction.equals("O") || Field.this.preDirection.equals(Cell.inverseDirction(dirction))) {
				dirction = Field.this.preDirection;
				System.out.println("direction: "+dirction);
			}
			
			Cell first = list.getFirst();
			Cell newFir = getNeighbor(first, dirction);
			if(newFir.isBarrier() || newFir.inSnake()) {
				return false;
			}
			boolean isFood = newFir.isFood();
			if(!isFood) {
				list.removeLast().setFlag(0);
			}else {
//				initLength++;
			}
			newFir.setFlag(0);
			list.addFirst(newFir);
			
			for (Cell cell:list) {
				cell.grow();
			}
			Field.this.preDirection = dirction;
			if(isFood) {
				makeFood();
				// 如果吃了食物，就再往前移动一次
				return move(dirction);
			}
			return true;
		}
	
		public void reset() {
			list.clear();
			for(int i=0;i<initLength;i++) {
				elements[rowNum-2][i].setFlag(initLength-i);
				list.addFirst(elements[rowNum-2][i]);
			}
			
			makeFood();
		}
	}
	
	
	public Cell[][] getElements() {
		return elements;
	}

	public void setElements(Cell[][] elements) {
		this.elements = elements;
	}

	public boolean move() {
		return snack.move(dirction);
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

	public void reset() {
		for (Cell[] cells : elements) {
			for (Cell cell : cells) {
				if(!cell.isBarrier()) {
					cell.setFlag(0);
				}
			}
		}
		this.snack.reset();
		this.dirction = "E";
	}
	
}
