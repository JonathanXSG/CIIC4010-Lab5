import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public int [] colors = new int[10];
	public int numOfColors = 0;
	public int newNumber;
	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		switch (e.getButton()) {
		case 1:		//Left mouse button
			break;
		case 3:		//Right mouse button
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);
		
		switch (e.getButton()) {
		case 1:		//Left mouse button
			if ((myPanel.mouseDownGridX >= 0) && (myPanel.mouseDownGridY >= 0)) {
				if ((gridX >= 0) && (gridY >= 0)) {
					myPanel.checkCellInfo(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
					if(myPanel.cellBombInfo[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == -1){
						
					}
				}
			}
			break;
		case 3:		//Right mouse button
			if ((myPanel.mouseDownGridX >= 0) && (myPanel.mouseDownGridY >= 0)) {
				if ((gridX >= 0) && (gridY >= 0)) {
					if(!myPanel.cellIsChecked[myPanel.mouseDownGridX][myPanel.mouseDownGridY]){
						myPanel.cellIsChecked[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = !myPanel.cellIsChecked[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] =Color.RED;
					}
					else if(myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] ==Color.RED){
						myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] =Color.WHITE;
						myPanel.cellIsChecked[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = !myPanel.cellIsChecked[myPanel.mouseDownGridX][myPanel.mouseDownGridY];
					}
					
					myPanel.repaint();
				}
			}
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}