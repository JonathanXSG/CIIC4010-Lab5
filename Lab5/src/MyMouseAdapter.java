import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
    private Random generator = new Random();
    private int[] oldNum = new int[10];
    private int arrayIndex=0;
    private int newNum;
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
        case 1:        //Left mouse button
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
        case 3:        //Right mouse button
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
        case 1:        //Left mouse button
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
                        	arrayIndex=0;
                            if(gridY>0&&gridY<10 && gridX==0){
                                for(int i=1;i<10;i++){
                                    myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor(10);
                                    myPanel.repaint();
                                }
                            }
                            else if(gridX>0&&gridX<10 && gridY==0){
                                for(int i=1;i<10;i++){
                                    myPanel.colorArray[myPanel.mouseDownGridX][i] = newColor(10);
                                    myPanel.repaint();
                                }
                            }
                            else if(gridX==0 && gridY==0){
                                for(int i=1;i<10;i++){
                                    myPanel.colorArray[i][i] = newColor(10);
                                    myPanel.repaint();
                                }
                            }
                            else{
                                for(int i=4;i<=6;i++){
                                    for(int j=4;j<=6;j++){
                                        myPanel.colorArray[i][j] = newColor(10);
                                        myPanel.repaint();
                                    }
                                }
                            }
                        } else {
                            //On the grid other than on the left column and on the top row:
                            myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor(10);
                            myPanel.repaint();
                        }
                    }
                }
            }
            myPanel.repaint();
            break;
        case 3:        //Right mouse button
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
                Color color = null;
                arrayIndex=0;
                for(int i=1;i<=9;i++){
                    for(int j=1;j<=9;j++){
                    	newNum=generator.nextInt(3);

                    	switch (newNum) {
                    	case 0:
                    		color = new Color(0xF5F5DC);   //Beige (from http://simple.wikipedia.org/wiki/List_of_colors)
                    		break;
                    	case 1:
                    		color = new Color(0xCD7F32);   //Bronze (from http://simple.wikipedia.org/wiki/List_of_colors)
                    		break;
                    	case 2:
                    		color = new Color(0xB87333);   //Copper (from http://simple.wikipedia.org/wiki/List_of_colors)
                    		break;
                    	}
                        myPanel.colorArray[i][j] = color;
                        myPanel.repaint();
                    }
                }
            }
            myPanel.repaint();
            break;
        default:    //Some other button (2 = Middle mouse button, etc.)
            //Do nothing
            break;
        }
    }
    private Color newColor(int numOfColors){
        Color newColor = null;
        do {
            newNum=generator.nextInt(numOfColors);
        } while (isInArray(newNum));

        switch (newNum) {
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
            newColor = new Color(0x7FFFD4);   //Aquamarine (from http://simple.wikipedia.org/wiki/List_of_colors)
            break;
        case 6:
            newColor = new Color(0xCCCCFF);   //Periwinkle (from http://simple.wikipedia.org/wiki/List_of_colors)
            break;
        case 7:
            newColor = new Color(0x00FF7F);   //Spring green (from http://simple.wikipedia.org/wiki/List_of_colors)
            break;
        case 8:
            newColor = new Color(0xD2B48C);   //Tan (from http://simple.wikipedia.org/wiki/List_of_colors)
            break;
        case 9:
            newColor = new Color(0xC71585);   //Red-violet (from http://simple.wikipedia.org/wiki/List_of_colors)
            break;
        case 10:
            newColor = new Color(0x1C39BB);   //Persian blue (from http://simple.wikipedia.org/wiki/List_of_colors)
            break;
        }
        oldNum[arrayIndex]=newNum;
        arrayIndex++;
        if(this.arrayIndex==9){
        	 arrayIndex=0;
        }
        return newColor;
    }
    private Boolean isInArray(int value){
        for(int i=0;i<arrayIndex;i++){
            if(oldNum[i]==value){
                return true;
            }
        }
        return false;
    }

}