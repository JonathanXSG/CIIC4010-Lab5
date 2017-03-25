import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;



public class Numbers {
	private int numberX;
	private int numberY;
	private String numberString;
	
	
	Numbers(String string, int x, int y){
		this.numberX = x;
		this.numberY = y;
		this.numberString = string;
	}

	public void draw(Graphics g){
		Graphics t = (Graphics) g;
		//Image bomb = new Image(this.getClass().getResourceAsStream("Assets/unnamed-6.png"));
//		
//		if(this.numberString == "-1"){
//			g.drawImage(bomb, this.numberX, this.numberY, null);
//		}
		int fontSize = 20;
		Font f = new Font("Comic Sans MS",Font.BOLD, fontSize);
		
		switch (this.numberString) {
		case "1":
			t.setColor(new Color(0x428cf4));
			break;

		case "2":
			t.setColor(new Color(0x7a42f4));
			break;
		case "3":
			t.setColor(new Color(0xf442e8));
			break;
		case "4":
			t.setColor(Color.RED);
			break;
		case "5":
			t.setColor(Color.ORANGE);
			break;
		case "6":
			t.setColor(Color.YELLOW);
			break;
		case "7":
			t.setColor(new Color(0x4bdb0d));
			break;
		case "8":
			t.setColor(Color.BLACK);
			break;
		}		
		
		
		t.setFont(f);
		t.drawString(this.numberString, this.numberX, this.numberY);
		
		
		
	}
}
