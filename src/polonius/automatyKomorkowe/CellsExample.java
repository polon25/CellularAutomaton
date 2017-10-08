package polonius.automatyKomorkowe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class CellsExample extends JPanel {
	
	boolean cells[]=new boolean[4];
	
	public CellsExample(boolean cells[]) {
		this.cells=cells;
		for (int i=0;i<4;i++)
			System.out.print(this.cells[i]);
		System.out.println("");
		repaint();
	}
	
	public Dimension getPreferredSize(){	//Ustawienie rozmiaru okna graficznego
		return new Dimension(100, 80);
	}
	
	protected void paintComponent(Graphics g){	//Rysowanie punktów
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i=0; i<3; i++){	//Pêtla po wszystkich punktach i malowanie ich w zale¿noœci od spinu.
			if (cells[i]==true) //Ustawianie koloru w zale¿noœci od spinu
				g2.setColor(Color.BLACK);
			else
				g2.setColor(Color.WHITE);
			g2.fill(new Rectangle.Double((i+1)*30-20, 10, 30, 30));	//Rysowanie punktu
		}
		if (cells[3])	//Ustawianie koloru w zale¿noœci od spinu
			g2.setColor(Color.BLACK);
		else
			g2.setColor(Color.WHITE);
		g2.fill(new Rectangle.Double(40, 50, 30, 30));	//Rysowanie punktu
	}
}
