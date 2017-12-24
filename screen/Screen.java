package screen;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mech.Constants;
import mech.Logic;

public class Screen implements Constants {
	public Logic lgc;
	private MainFrame fr;
	private JPanel mp;
	private JLabel antRulesLbl;
	public JLabel stepNumberLbl;
	public AntField antField;
	public MyButton startBtn, stopBtn, pauseBtn;
	public MyButton addLBtn, addRBtn;
	public MyButton clearAllBtn, clearLastBtn;
	public MyButton clearAntFieldBtn;
	public JCheckBox randomChBox;
	
	public Screen() {
		lgc = new Logic(this);
		fr = new MainFrame("Langton Ant", this);
		createMainPanel();
		createAllLabels();
		createAllButtons();
		createRandomCheckBoss();
		mp.repaint();
	}

	private void createMainPanel() {
		mp = new JPanel();
		mp.setLayout(null);
		mp.setBackground(Color.WHITE);
		fr.setContentPane(mp);
	}

	public void createAntField() {
		if (antField != null)
			mp.remove(antField);
		antField = new AntField();
		mp.add(antField);
		antField.repaint();
	}
	
	private void createAllLabels() {
		antRulesLbl = new MyLabel("RL", 400, LABEL_WIDTH);
		mp.add(antRulesLbl);
		
		stepNumberLbl = new MyLabel("0", 0, 400);
		stepNumberLbl.setVisible(false);
		stepNumberLbl.setName("Button");
		mp.add(stepNumberLbl);
	}
	
	public void setTextOnRLLabel(String s){
		antRulesLbl.setText(s);
		if (s.equals("")){
			startBtn.setEnabled(false);
			clearLastBtn.setEnabled(false);
			clearAllBtn.setEnabled(false);
		}else{
			startBtn.setEnabled(true);
			clearLastBtn.setEnabled(true);
			clearAllBtn.setEnabled(true);
		}
		
		if (s.length() > COLORS.length)
			setTextOnRLLabel(s.substring(0, s.length()-1));
	}
		public String getTextFromRLLabel(){
			return antRulesLbl.getText();
		}

	private void createAllButtons() {
		addLBtn = new MyButton("+L", 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addLBtn);
		
		addRBtn = new MyButton("+R", 100, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(addRBtn);
		
		clearLastBtn = new MyButton("-LAST", 200, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(clearLastBtn);
		
		clearAllBtn = new MyButton("-ALL", 300, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(clearAllBtn);
		
		startBtn = new MyButton("START", WIN_WIDTH-100, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		mp.add(startBtn);
		
		stopBtn = new MyButton("STOP", WIN_WIDTH-100, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		stopBtn.setVisible(false);
		mp.add(stopBtn);
		
		pauseBtn = new MyButton("PAUSE", WIN_WIDTH-200, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		pauseBtn.setVisible(false);
		mp.add(pauseBtn);
		
		clearAntFieldBtn = new MyButton("CLEAR", WIN_WIDTH-200, 0, BUTTON_WIDTH, BUTTON_HEIGHT, lgc);
		clearAntFieldBtn.setVisible(false);
		mp.add(clearAntFieldBtn);
	}

	private void createRandomCheckBoss() {
		randomChBox = new JCheckBox("Do random snapshots");
		randomChBox.setBounds(clearAntFieldBtn.getX(), BUTTON_HEIGHT, BUTTON_WIDTH*2, BUTTON_HEIGHT);
		randomChBox.setBackground(Color.WHITE);
		randomChBox.setFocusable(false);
		randomChBox.setName("CheckBox");
		mp.add(randomChBox);
	}
	
	public void setTextOnStepNumberLabel(String s){
		stepNumberLbl.setText(s);
	}
	
	public void switchButtonsOnStart(){
		for (Component c: mp.getComponents()) {
			String name = c.getName();
			if (name != null){
				if (c.isVisible())
					c.setVisible(false);
				else
					c.setVisible(true);	
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Screen scr = new Screen();
	}
}
