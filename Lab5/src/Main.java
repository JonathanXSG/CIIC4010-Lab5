import javax.swing.JFrame;

public class Main {
	private static JFrame myFrame = new JFrame("Color Grid");
	private static MyPanel myPanel = new MyPanel();
	public static void main(String[] args) {
		
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocation(400, 150);
		
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		myFrame.pack();
		myFrame.setVisible(true);
		myPanel.createCellArray();
	}
	public static MyPanel getJPanel(){
		return myPanel;
	}
	public static JFrame getJFrame(){
		return myFrame;
	}
}