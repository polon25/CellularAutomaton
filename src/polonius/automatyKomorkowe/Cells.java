package polonius.automatyKomorkowe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class Cells extends JPanel{
	
	int X=0;
	int Y=0;
	int coeff=0;
	boolean cells[][];
	
	public Cells(int X, int Y, int coeff) {
		this.X=X;
		this.Y=Y;
		this.coeff=coeff;
		
		cells=new boolean[X][Y];
		boolean[] neighbours=new boolean[8];
		
		int k=128;
		for (int i=0; i<8; i++) { //Zamiana int na boolean
			if (this.coeff>=k) {
				neighbours[i]=true;
				this.coeff-=k;
			}
			else
				neighbours[i]=false;
			k=k/2;
		}
		
		for (int i=0; i<8; i++) {
			if (neighbours[i])
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println("");
		
		for (int i=0; i<X; i++) {
			for (int j=0; j<Y; j++) {
				cells[i][j]=false;
			}
		}
		
		cells[X/2][0]=true;
		
		for (int i=1; i<Y; i++) {
			for (int j=1; j<X-1; j++) {
				if (cells[j-1][i-1]==true && cells[j][i-1]==true && cells[j+1][i-1]==true)
					cells[j][i]=neighbours[0];
				else if (cells[j-1][i-1]==true && cells[j][i-1]==true && cells[j+1][i-1]==false)
					cells[j][i]=neighbours[1];
				else if (cells[j-1][i-1]==false && cells[j][i-1]==true && cells[j+1][i-1]==true)
					cells[j][i]=neighbours[2];
				else if (cells[j-1][i-1]==true && cells[j][i-1]==false && cells[j+1][i-1]==true)
					cells[j][i]=neighbours[3];
				else if (cells[j-1][i-1]==true && cells[j][i-1]==false && cells[j+1][i-1]==false)
					cells[j][i]=neighbours[4];
				else if (cells[j-1][i-1]==false && cells[j][i-1]==true && cells[j+1][i-1]==false)
					cells[j][i]=neighbours[5];
				else if (cells[j-1][i-1]==false && cells[j][i-1]==false && cells[j+1][i-1]==true)
					cells[j][i]=neighbours[6];
				else if (cells[j-1][i-1]==false && cells[j][i-1]==false && cells[j+1][i-1]==false)
					cells[j][i]=neighbours[7];
			}
		}
		repaint();
	}
	
	public Dimension getPreferredSize(){	//Ustawienie rozmiaru okna graficznego
		return new Dimension(1024, 688);
	}
	
	protected void paintComponent(Graphics g){	//Rysowanie punktów
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int i=0; i<X; i++){	//Pêtla po wszystkich punktach i malowanie ich w zale¿noœci od spinu.
			for (int j=0; j<Y; j++) {
				if (cells[i][j])	//Ustawianie koloru w zale¿noœci od spinu
					g2.setColor(Color.BLACK);
				else
					g2.setColor(Color.WHITE);
				g2.fill(new Rectangle.Double(i, j, 1, 1));	//Rysowanie punktu
			}
		}
		
	}
}
