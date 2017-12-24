package screen;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import mech.Constants;

public class AntField extends JPanel implements Constants{
	private static final long serialVersionUID = 1L;
		
	public AntField() {
		setBounds(0, 50, ANT_FIELD_WIDTH, ANT_FIELD_HEIGHT);
		setBackground(Color.WHITE);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}
}
