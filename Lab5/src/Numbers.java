import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



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
		int fontSize = 20;
		Font f = new Font("Comic Sans MS",Font.BOLD, fontSize);
		t.setColor(Color.RED);
		t.setFont(f);
		t.drawString(this.numberString, this.numberX, this.numberY);
		
		
	}
}
