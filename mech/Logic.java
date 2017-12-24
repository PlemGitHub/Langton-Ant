package mech;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import screen.Screen;

public class Logic implements ActionListener, Constants {
	private Screen scr;
	public MyTimer myTimer;
	private RandomSnapshots randomSnapshots;
	
	public Logic(Screen scr) {
		this.scr = scr;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(scr.addLBtn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"L");
		
		if (e.getSource().equals(scr.addRBtn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel()+"R");
		
		if (e.getSource().equals(scr.clearLastBtn))
			scr.setTextOnRLLabel(scr.getTextFromRLLabel().substring(0, scr.getTextFromRLLabel().length()-1));
		
		if (e.getSource().equals(scr.clearAllBtn))
			scr.setTextOnRLLabel("");
		
		if (e.getSource().equals(scr.startBtn)){
			if (scr.randomChBox.isSelected()){
				randomSnapshots = new RandomSnapshots(scr, this);
				randomSnapshots.start();
			}else{
				createMyTimerAndAntField();
				scr.switchButtonsOnStart();
				scr.clearAntFieldBtn.setVisible(false);
			}
		}
		
		if (e.getSource().equals(scr.stopBtn)){
			if (randomSnapshots != null
					&& randomSnapshots.isAlive()){
				randomSnapshots.interrupt();
				randomSnapshots.myTimer.stop();
			}else
				myTimer.stop();
			myTimer.stopped = true;
			scr.switchButtonsOnStart();
			scr.stepNumberLbl.setText("0");
		}
		
		if (e.getSource().equals(scr.pauseBtn)){
			if (myTimer.isRunning()){
				myTimer.stop();
			}else{
				myTimer.start();
			}
		}
		
		if (e.getSource().equals(scr.clearAntFieldBtn)){
			scr.createAntField();
			scr.clearAntFieldBtn.setVisible(false);
		}
	}	
	
	public MyTimer createMyTimerAndAntField(){
		scr.createAntField();
		myTimer = new MyTimer(DELAY, null, scr);
		myTimer.addActionListener(myTimer);
		myTimer.start();
		return myTimer;
	}
}
