package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import thread.BallThread;

public class EclipseGUI {
	
	@FXML
	private Pane pane;

    @FXML
    private Circle sun;

    @FXML
    private Circle moon;
    
    @FXML
    private Slider bar;

    BallThread bt;
    
    @FXML
    public void moveMoon(ActionEvent event) {
    	bt.isMoving(false);
    	bt = new BallThread(moon, pane, bar, sun);
    	bt.isMoving(true);
    	bt.start();
    }

    @FXML
    public void stopMoon(ActionEvent event) {
    	bt.isMoving(false);
    }
    
    public void initialize() {
    	bt = new BallThread(moon, pane, bar, sun);
    }

}
