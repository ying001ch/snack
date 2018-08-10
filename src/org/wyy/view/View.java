package org.wyy.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import org.wyy.domain.Cell;
import org.wyy.domain.Field;

public class View  extends JPanel{
	private static final long serialVersionUID = -5258995676212660595L;
	private static final int GRID_SIZE = 20;
	
	private Field field;

	public View(Field field) {
		this.field = field;
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Cell[][] elements = field.getElements();
			for(int j=0;j<elements.length;j++) {
				for(int i=0;i<elements[j].length;i++) {
				g.drawRect(GRID_SIZE*j, GRID_SIZE*i, GRID_SIZE, GRID_SIZE);
				if(elements[i][j].getFlag() != 0) {
					g.fillRect(GRID_SIZE*j, GRID_SIZE*i, GRID_SIZE, GRID_SIZE);
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(field.getRowNum()*GRID_SIZE+1, field.getColNum()*GRID_SIZE+1);
	}
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
}
