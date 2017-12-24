package mech;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import entity.Ant;
import screen.Screen;

public class MyTimer extends javax.swing.Timer implements Constants, ActionListener{
	private static final long serialVersionUID = 1L;

	public Screen scr;
	private JPanel antField;
	private Graphics g;
	private Ant ant;
	public String directionChars;
	public Character newDirectionChar;
	public int colorPos;
	public BufferedImage buffImg;
	public int stepNumber;
	public boolean stopped;
	private SavePNG savePNG = new SavePNG();
	/**
	 * 0 - white <br> 1 - black
	 */
	public Color[][] antColors = new Color[ANT_FIELD_WIDTH][ANT_FIELD_HEIGHT];

	public MyTimer(int delay, ActionListener listener, Screen scr) {
		super(delay, listener);
		this.scr = scr;
		antField = scr.antField;
		if (scr.randomChBox.isSelected()){
			createRandomDirectionChars();
			scr.setTextOnRLLabel(directionChars);
		}
		else
			directionChars = scr.getTextFromRLLabel();
		
		ant = new Ant(STEP*K_WIDTH/2-STEP, STEP*K_HEIGHT/2-STEP);
		initAntColors();
		buffImg = new BufferedImage(ANT_FIELD_WIDTH, ANT_FIELD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = buffImg.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, ANT_FIELD_WIDTH, ANT_FIELD_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(0, 0, ANT_FIELD_WIDTH-1, ANT_FIELD_HEIGHT-1);
	}

	private void createRandomDirectionChars() {
		directionChars = "";
		int length = COLORS.length-3;
		Random rnd = new Random();
		int dCharsLength = rnd.nextInt(length)+3;
		for (int i = 0; i < dCharsLength; i++) {
			rnd = new Random();
			int k = rnd.nextInt(2);
				switch (k) {
					case 0: directionChars = directionChars+"R"; 
						break;
					case 1:
						directionChars = directionChars+"L"; 
						break;
				}
		}
		String home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath()+"/LangtonAntPNG/"+directionChars;
		File f = new File(home);
		if (f.exists())
			createRandomDirectionChars();
	}

	private void initAntColors() {
		for (int i = 0; i < ANT_FIELD_WIDTH; i++)
			for (int j = 0; j < ANT_FIELD_HEIGHT; j++)
				antColors[i][j] = COLORS[0];
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Color newColor = defineNewColorUnderAnt();
		
		ant.defineDirection(newDirectionChar);
		
		drawStep(newColor);
		increaseStepNumber();
	}

	private Color defineNewColorUnderAnt() {
		Color underAnt = antColors[ant.x][ant.y];
		colorPos = findColor(underAnt);
		newDirectionChar = directionChars.charAt(colorPos);
		increaseColorPos();
		return COLORS[colorPos];
	}

	private int findColor(Color underAnt) {
		int k=0;
		for (Color color : COLORS) {
			if (underAnt == color)
				return k;
			k++;		
		}
		return 0;
	}

	private void increaseColorPos() {
		colorPos++;
		if (colorPos > directionChars.length()-1)
			colorPos = 0;
	}

	private void drawStep(Color newColor) {
		g.setColor(newColor);
		g.fillRect(ant.x, ant.y, STEP, STEP);
		antColors[ant.x][ant.y] = newColor;
		ant.doMove();
		g.setColor(Color.RED);
		g.fillRect(ant.x, ant.y, STEP, STEP);
		drawBuffImage();
	}

	private void increaseStepNumber() {
		stepNumber++;
		scr.setTextOnStepNumberLabel(Integer.toString(stepNumber));
		if (scr.randomChBox.isSelected()
			&& (int) stepNumber%SNAPSHOT_FREQUENCY == 0){
			savePNG.save(buffImg, directionChars, stepNumber);
		}
	}

	public void drawBuffImage() {
			antField.getGraphics().drawImage(buffImg, 0, 0, antField);
	}
}
