package polonius.automatyKomorkowe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Window extends JFrame {
	
	static int X=1024; //Wymiary okna
	static int Y=768;
	
	static Cells cellsPanel;
	
	JPanel cells = new JPanel();
	
	public Window() {
		setTitle("Automaty komórkowe");
		setSize(X,Y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel options = new JPanel();
		
		add(BorderLayout.NORTH, options);
		options.setLayout(new GridLayout(1,8));
		add(BorderLayout.SOUTH, cells);
		
		JSlider coeffSlider = new JSlider(0,255,74);
		coeffSlider.setMajorTickSpacing(100);
		coeffSlider.setPaintTicks(true);
		coeffSlider.setPaintLabels(true);
		
		JTextField coeffValue = new JTextField(Integer.toString(coeffSlider.getValue()));
		JButton generate = new JButton("Wygeneruj");
		
		options.add(new JPanel());
		options.add(new JLabel("Wspó³czynnik"));
		options.add(coeffSlider);
		options.add(coeffValue);
		options.add(generate);
		
		boolean[] neighbours=int2bool(coeffSlider.getValue());
		boolean[][] examples= {{true,true,true,neighbours[0]},
		{true,true,false,neighbours[1]},
		{false,true,true,neighbours[2]},
		{true,false,true,neighbours[3]},
		{true,false,false,neighbours[4]},
		{false,true,false,neighbours[5]},
		{false,false,true,neighbours[6]},
		{false,false,false,neighbours[7]}};
		
		for (int i=0; i<8; i++) {
			boolean[] tmp=new boolean[4];
			for (int j=0; j<4; j++) {
				tmp[j]=examples[i][j];
			}
			cells.add(new CellsExample(tmp));
		}
		
		cellsPanel=new Cells(X,Y-80,coeffSlider.getValue());
		add(BorderLayout.CENTER, cellsPanel);
		this.pack();
		
		coeffSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				coeffValue.setText(Integer.toString(coeffSlider.getValue()));
				
				boolean[] neighbours=int2bool(coeffSlider.getValue());
				boolean[][] examples= {{true,true,true,neighbours[0]},
						{true,true,false,neighbours[1]},
						{false,true,true,neighbours[2]},
						{true,false,true,neighbours[3]},
						{true,false,false,neighbours[4]},
						{false,true,false,neighbours[5]},
						{false,false,true,neighbours[6]},
						{false,false,false,neighbours[7]}};
				cells = new JPanel();
				for (int i=0; i<8; i++) {
					boolean[] tmp=new boolean[4];
					for (int j=0; j<4; j++) {
						tmp[j]=examples[i][j];
					}
					cells.add(new CellsExample(tmp));
				}
				add(BorderLayout.SOUTH, cells);
				
				cellsPanel=new Cells(X,Y-80,coeffSlider.getValue());
				add(BorderLayout.CENTER, cellsPanel);
				pack();
			}
		});
		coeffValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				coeffSlider.setValue(Integer.parseInt(coeffValue.getText()));
				
				boolean[] neighbours=int2bool(coeffSlider.getValue());
				boolean[][] examples= {{true,true,true,neighbours[0]},
						{true,true,false,neighbours[1]},
						{false,true,true,neighbours[2]},
						{true,false,true,neighbours[3]},
						{true,false,false,neighbours[4]},
						{false,true,false,neighbours[5]},
						{false,false,true,neighbours[6]},
						{false,false,false,neighbours[7]}};
				cells = new JPanel();
				for (int i=0; i<8; i++) {
					boolean[] tmp=new boolean[4];
					for (int j=0; j<4; j++) {
						tmp[j]=examples[i][j];
					}
					cells.add(new CellsExample(tmp));
				}
				add(BorderLayout.SOUTH, cells);
				
				cellsPanel=new Cells(X,Y-80,coeffSlider.getValue());
				add(BorderLayout.CENTER, cellsPanel);
				pack();
				
			}			
		});
		generate.addActionListener(new ActionListener() {	//Listener na przycisk generate
			public void actionPerformed(ActionEvent arg0) {
				cellsPanel=new Cells(X,Y-80,coeffSlider.getValue());
				add(BorderLayout.CENTER, cellsPanel);
				pack();
			}
		});	
	}
	
	boolean[] int2bool(int value) {
		boolean[] neighbours=new boolean[8];
		int tmp=value;
		int k=128;
		for (int i=0; i<8; i++) { //Zamiana int na boolean
			if (tmp>=k) {
				neighbours[i]=true;
				tmp-=k;
			}
			else
				neighbours[i]=false;
			k=k/2;
		}
		return neighbours;
	}
	
	public static void main(String[] args) {
		JFrame window = new Window();
		Toolkit tools=Toolkit.getDefaultToolkit();	//Umo¿liwia ustawienie po³o¿enai okna w przestrzeni
		Dimension dim=tools.getScreenSize();
		final int framewidth=X;
		final int frameheight=Y;
		window.setLocation((int)dim.getWidth()/2 - framewidth/2, (int) dim.getHeight()/2 - frameheight/2);
		window.setVisible(true);
	}

}
