import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private static final long serialVersionUID = 3426940946811133635L;
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;
	private static final int TOTAL_COLUMNS = 10;
	private static final int TOTAL_ROWS = 10;   //Last row has only one cell
	
	public Insets myInsets = getInsets();
	Random random = new Random();
	
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	public Cell[][] cellArray = new Cell[TOTAL_COLUMNS][TOTAL_ROWS];
	
	
	public MyPanel() {   //This is the constructor... this code runs first to initialize
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		
	}
	
	public void createCellArray(){
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   
			for (int y = 0; y < TOTAL_ROWS; y++) {
				cellArray[x][y] = new Cell(x, y);
			}
		}
		setRandomBombs((TOTAL_COLUMNS+TOTAL_ROWS)*2/3 + random.nextInt((TOTAL_COLUMNS+TOTAL_ROWS)/2));
		bombIndicator();
	}
	
	public void setRandomBombs(int numOfBombs){
		int x;
		int y;
		for(int i =0; i<numOfBombs;i++){
			x= new Random().nextInt(TOTAL_COLUMNS);
			y= new Random().nextInt(TOTAL_ROWS);
			if(cellArray[x][y].isBomb()){
				i--;
			}
			else{
				cellArray[x][y].setBomb();
				cellArray[x][y].setAdyacentBombs(-1);
			}
		}
	}

	public boolean isOutOfBound(int x, int y){
		return ((x>TOTAL_COLUMNS-1 || x<0) || (y>TOTAL_ROWS-1 || y<0));
	}
	
	private void bombIndicator(){
		for(int i=0;i<TOTAL_COLUMNS;i++){
			for(int j=0; j<=(TOTAL_ROWS)-1;j++){
				if(cellArray[i][j].isBomb()){
					if(!isOutOfBound(i-1, j-1) && !cellArray[i-1][j-1].isBomb()) 	cellArray[i-1][j-1].incrementAdyacentBombs();
					if(!isOutOfBound(i, j-1) && !cellArray[i][j-1].isBomb())	cellArray[i][j-1].incrementAdyacentBombs();
					if(!isOutOfBound(i+1, j-1) && !cellArray[i+1][j-1].isBomb())	cellArray[i+1][j-1].incrementAdyacentBombs();
					if(!isOutOfBound(i-1, j) && !cellArray[i-1][j].isBomb()) 	cellArray[i-1][j].incrementAdyacentBombs();
					if(!isOutOfBound(i+1, j) && !cellArray[i+1][j].isBomb())	cellArray[i+1][j].incrementAdyacentBombs();
					if(!isOutOfBound(i-1, j+1) && !cellArray[i-1][j+1].isBomb()) 	cellArray[i-1][j+1].incrementAdyacentBombs();
					if(!isOutOfBound(i, j+1) && !cellArray[i][j+1].isBomb()) 	cellArray[i][j+1].incrementAdyacentBombs();
					if(!isOutOfBound(i+1, j+1) && !cellArray[i+1][j+1].isBomb()) 	cellArray[i+1][j+1].incrementAdyacentBombs();
				}
			}
		}
	}

	public void checkCellInfo(int x, int y){
		if(!isOutOfBound(x, y) && !cellArray[x][y].isChecked() && !cellArray[x][y].hasFlag()){
			if(cellArray[x][y].isBomb()){
				showAllBombs();
				repaint();
				int optionPaneAnswer = JOptionPane.showConfirmDialog(getParent(), "You have lost... \n Do you want to restart?", "END GAME",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if(optionPaneAnswer==1){
					Main.getJFrame().dispatchEvent(new WindowEvent(Main.getJFrame(), WindowEvent.WINDOW_CLOSING));
				}
				else{
					createCellArray();
				}				
			}
			else if(cellArray[x][y].getAdjacentBombs() >0){
				cellArray[x][y].setChecked(true);
				cellArray[x][y].setPaint();				
			}
			else if(cellArray[x][y].getAdjacentBombs()==0){
				cellArray[x][y].setChecked(true);
				cellArray[x][y].setPaint();
				checkCellInfo(x-1,y-1);
				checkCellInfo(x,y-1);
				checkCellInfo(x+1,y-1);
				checkCellInfo(x-1,y);
				checkCellInfo(x+1,y);
				checkCellInfo(x-1,y+1);
				checkCellInfo(x,y+1);
				checkCellInfo(x+1,y+1);
			}
		}
		repaint();
		hasPlayerWon();
	}
	
	public void setFlag(int x, int y){
		if(!isOutOfBound(x, y) && !cellArray[x][y].isChecked()){
			cellArray[x][y].setHasFlag(!cellArray[x][y].hasFlag());
			repaint();
		}
		hasPlayerWon();
	}
	
	public void showAllBombs(){
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if(cellArray[x][y].isBomb()){
					cellArray[x][y].setChecked(true);
					cellArray[x][y].setPaint();
				}
			}
		}
	}
	
	public void hasPlayerWon(){
		boolean playerWon =true;
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   
			for (int y = 0; y < TOTAL_ROWS; y++) {
				if((!cellArray[x][y].isBomb() && !cellArray[x][y].isChecked()) || (cellArray[x][y].isBomb() && !cellArray[x][y].hasFlag())){
					playerWon =false;
				}
			}
		}
		if(playerWon){
			int optionPaneAnswer = JOptionPane.showConfirmDialog(getParent(), "You have Won!. \n Do you want to restart?", "END GAME",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if(optionPaneAnswer==1){
				Main.getJFrame().dispatchEvent(new WindowEvent(Main.getJFrame(), WindowEvent.WINDOW_CLOSING));
			}
			else{
				createCellArray();
			}	
		}
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);

		//Draw the grid 
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= TOTAL_ROWS; y++) {
			g.drawLine(x1 + GRID_X, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) {
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y, x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}
		
		//Paint cells
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
					g.setColor(Color.WHITE);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
					cellArray[x][y].draw(g);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension((INNER_CELL_SIZE+1)*TOTAL_COLUMNS + GRID_X*2, (INNER_CELL_SIZE+1)*TOTAL_ROWS + GRID_Y*2);
	}
	
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return x;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int getGridY(int x, int y) {
		
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
			return y;
		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
	

}