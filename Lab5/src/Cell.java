import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cell {
	private int x;
	private int y;
	private int adjacentBombs;
	private boolean isBomb;
	private boolean isChecked = false;
	private boolean hasFlag;
	private boolean paint = false;
	private BufferedImage bomb = null;
	
	MyPanel myPanel = Main.getJPanel();
	Insets myInsets = myPanel.myInsets;
	
	public Cell(int arrayIndexX, int arrayIndexY){
		this.x = myInsets.left + 25 + 30*arrayIndexX;
		this.y = myInsets.top + 25 + 30*arrayIndexY;
	}
	
	public void draw(Graphics g){
		Graphics t = (Graphics) g;
		
		int fontSize = 20;
		Font f = new Font("Comic Sans MS",Font.BOLD, fontSize);
		if(this.hasFlag){
			t.setColor(Color.RED);
			g.fillRect(x+1 , y+1, 29, 29);
			if(this.paint){
				try {
					 bomb = ImageIO.read(getClass().getResourceAsStream("/bomb.png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				g.drawImage(bomb, x, y, null);
			}
			
		}
		else if(this.isPaint()){
			if(this.adjacentBombs>0){
				t.setColor(new Color(0xe0e0e0));
				g.fillRect(x+1 , y+1, 29, 29);
				switch (String.valueOf(this.adjacentBombs)) {
				case "1":t.setColor(new Color(0x428cf4));break;
				case "2":t.setColor(new Color(0x7a42f4));break;
				case "3":t.setColor(new Color(0xf442e8));break;
				case "4":t.setColor(Color.RED);break;
				case "5":t.setColor(Color.ORANGE);break;
				case "6":t.setColor(Color.YELLOW);break;
				case "7":t.setColor(new Color(0x4bdb0d));break;
				case "8":t.setColor(Color.BLACK);break;
				}		
				t.setFont(f);
				t.drawString(String.valueOf(this.adjacentBombs), x+10, y+22);
			}
			else if(this.adjacentBombs==0){
				t.setColor(new Color(0xe0e0e0));
				g.fillRect(x+1 , y+1, 29, 29);
			}
			else if(this.isBomb){
				try {
					 bomb = ImageIO.read(getClass().getResourceAsStream("/bomb.png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//t.setColor(Color.BLACK);
				t.setColor(new Color(0xe0e0e0));
				g.fillRect(x+1 , y+1, 29, 29);
				g.drawImage(bomb, x, y, null);
			}
		}
	}

	
	public int getAdjacentBombs() {
		return adjacentBombs;
	}

	public void setAdyacentBombs(int adyacentBombs) {
		this.adjacentBombs = adyacentBombs;
	}
	
	public void incrementAdyacentBombs(){
		this.adjacentBombs++;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb() {
		this.isBomb = true;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean hasFlag() {
		return hasFlag;
	}

	public void setHasFlag(boolean hasFlag) {
		this.hasFlag = hasFlag;
	}

	public boolean isPaint() {
		return paint;
	}

	public void setPaint() {
		this.paint =true;
	}
	
}
