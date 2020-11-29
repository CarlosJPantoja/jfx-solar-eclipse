package thread;

import javafx.application.Platform;
import model.Eclipse;
import ui.EclipseGUI;

public class LightThread extends Thread {

	private Eclipse moon;
	private EclipseGUI gui;

	public LightThread(Eclipse moon, EclipseGUI gui) {
		this.moon = moon;
		this.gui = gui;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(gui.getSleepTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					gui.updateLight(moon.getLight());
				}
			});
		}
	}
	
}
