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
		switch (e.getButton()) {
		case 1:		//Left mouse button
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
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			 c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame) c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			 x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
			
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
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
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
							numOfColors = 0;
							if(gridX == 0 && (gridY<10 && gridY>0)){
								for(int i=1;i<10;i++){
									myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor();
									myPanel.repaint();
								}
							}
								
							else if(gridY == 0 && (gridX<10 && gridX>0)){
								for(int i=1;i<10;i++){
									myPanel.colorArray[myPanel.mouseDownGridX][i] = newColor();
									myPanel.repaint();
									
								}
							}
							else if(gridY == 0 && gridX== 0){
								for(int i=1;i<10;i++){
									myPanel.colorArray[i][i] = newColor();
									myPanel.repaint();
									
								}
							}
							else{
								for(int i=4;i<=6;i++){
									for(int j=4;j<=6;j++){
										myPanel.colorArray[i][j] = newColor();
										myPanel.repaint();
									}
								}
							}
							
						} else {
							//On the grid other than on the left column and on the top row:

						
							
				
							
							
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor();
							myPanel.repaint();
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			gridX = myPanel.getGridX(x, y);
			gridY = myPanel.getGridY(x, y);
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				Color newColor1 = null;
				for(int i=1;i<=9;i++){
					for(int j=1;j<=9;j++){
						newNumber = generator.nextInt(3);
						
						colors[numOfColors] = newNumber;
						numOfColors++;
						if(numOfColors == 3){
							numOfColors = 0;
						}
						switch (newNumber) {
						case 0:
							newColor1 = new Color(0xDE5D83);//Blush
							break;
						case 1:
							newColor1 = new Color(0xDC143C);//Crimson
							break;
						case 2:
							newColor1= new Color(0x50C878);//Esmerald
							break;
						}
						myPanel.colorArray[i][j] = newColor1;
						myPanel.repaint();
					}
				}
			}
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	
	
	public boolean isMember(int key){
		for(int i=0; i<numOfColors;i++){
			
			if(key == colors[i]){
				return true;
			}
		}
		return false;
}
	
	private Color newColor(){
		Color newColor = null;
		do{
			newNumber = generator.nextInt(10);
		}while(isMember(newNumber));
		
		colors[numOfColors] = newNumber;
		numOfColors++;
		if(numOfColors == 9){
			numOfColors = 0;
		}
		switch (newNumber) {
		case 0:
			newColor = Color.YELLOW;
			break;
		case 1:
			newColor = Color.MAGENTA;
			break;
		case 2:
			newColor = Color.BLACK;
			break;
		case 3:
			newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
			break;
		case 4:
			newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
			break;
		case 5:
			newColor = Color.RED;
			break;
		case 6:
			newColor = Color.BLUE;
			break;
		case 7:
			newColor = Color.green;
			break;
		case 8:
			newColor = Color.ORANGE;
			break;
		case 9:
			newColor = new Color(0x0095B6);
			break;
		case 10:
			newColor = new Color(0x7FFFD4);
			break;
			
		}
		return newColor;
	}

	
}