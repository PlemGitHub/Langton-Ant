package entity;

import java.awt.Point;
import mech.Constants;

public class Ant implements Constants {
	public int x, y;
	public Point directionPoint = DIRECTIONS_LEFT[0];
	
	public Ant(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void defineDirection(char R){
		switch (R) {
		case 'L': defineDirectionLeft();
			break;
		case 'R': defineDirectionRight();
			break;
		}
	}

	private void defineDirectionLeft() {
		int k = 0;
		for (Point p : DIRECTIONS_LEFT) {
			if (p.equals(directionPoint)){
				k++;
				if (k < 4){
					directionPoint = DIRECTIONS_LEFT[k];
					return;
				}else{
					directionPoint = DIRECTIONS_LEFT[0];
					return;
				}
			}
			k++;				
		}
	}

	private void defineDirectionRight() {
		int k = 0;
		for (Point p : DIRECTIONS_RIGHT) {
			if (p.equals(directionPoint)){
				k++;
				if (k < 4){
					directionPoint = DIRECTIONS_RIGHT[k];
					return;
				}else{
					directionPoint = DIRECTIONS_RIGHT[0];
					return;
				}
			}
			k++;				
		}
	}

	public void doMove() {
		x = x+directionPoint.x;
			if (x < 0)
				x = x+ANT_FIELD_WIDTH-STEP;
			if (x > ANT_FIELD_WIDTH-STEP)
				x = x-ANT_FIELD_WIDTH+STEP;
		y = y+directionPoint.y;
			if (y < 0)
				y = y+ANT_FIELD_HEIGHT-STEP;
			if (y > ANT_FIELD_HEIGHT-STEP)
				y = y-ANT_FIELD_HEIGHT+STEP;
	}
}
