package mech;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

public interface Constants {
	int STEP = 5;
	
	int K_WIDTH = 115;
	int K_HEIGHT = 115;
	
	int WIN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	int WIN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	int ANT_FIELD_WIDTH = STEP*K_WIDTH;
	int ANT_FIELD_HEIGHT = STEP*K_HEIGHT;
	
	int DELAY = 0;
	
	Font LABEL_FONT = new Font ("TimesRoman", Font.BOLD, 25);
	int LABEL_WIDTH = WIN_WIDTH-600;
	char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	Point[] DIRECTIONS_LEFT = {new Point(0, -STEP), new Point(-STEP, 0), new Point(0, STEP), new Point(STEP, 0)};
	Point[] DIRECTIONS_RIGHT = {new Point(0, -STEP), new Point(STEP, 0), new Point(0, STEP), new Point(-STEP, 0)};
	
	int BUTTON_WIDTH = 100;
	int BUTTON_HEIGHT = 50;
	String BUTTON_NAME = "Button";
	Font BUTTON_FONT = new Font ("TimesRoman", Font.BOLD, 20);
	
	Color[] COLORS = {Color.WHITE, Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA,
						Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW};
	int COLORS_LAST_INDEX = COLORS.length-1;
}
