package thread;

import javafx.application.Platform;
import model.Eclipse;
import ui.EclipseGUI;

public class EclipseThread extends Thread {
	
	private Eclipse moon;
	private EclipseGUI gui;

	public EclipseThread(Eclipse moon, EclipseGUI gui) {
		this.moon = moon;
		this.gui = gui;
	}
	
	@Override
	public void run() {
		while(moon.isMoving()) {
			try {
				Thread.sleep(gui.getSleepTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gui.update(moon.move(), moon.getLight());
				}
			});
		}
	}
}
