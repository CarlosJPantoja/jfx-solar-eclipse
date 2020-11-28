package ui;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Eclipse;
import thread.EclipseThread;

public class EclipseGUI {
	
	@FXML
	private Pane pane;

    @FXML
    private Circle sun;

    @FXML
    private Circle moon;
    
    @FXML
    private Slider bar;
    
    @FXML
    private Button btnMove;

    @FXML
    private Button btnStop;
    
    @FXML
    private Circle star8;

    @FXML
    private Circle star9;

    @FXML
    private Circle star7;

    @FXML
    private Circle star1;

    @FXML
    private Circle star10;

    @FXML
    private Circle star5;

    @FXML
    private Circle star11;

    @FXML
    private Circle star16;

    @FXML
    private Circle star12;

    @FXML
    private Circle star13;

    @FXML
    private Circle star15;

    @FXML
    private Circle star14;

    @FXML
    private Circle planet5;

    @FXML
    private Circle star6;

    @FXML
    private Circle star3;

    @FXML
    private Circle star2;

    @FXML
    private Circle planet2;

    @FXML
    private Circle planet4;

    @FXML
    private Circle planet3;

    @FXML
    private Circle planet6;

    @FXML
    private Circle planet1;

    @FXML
    private Circle star4;
    
    @FXML
    private Circle crater1;

    @FXML
    private Circle crater2;

    @FXML
    private Circle crater3;
    
    private Eclipse ball;
    
    private int[] rgb;
    
    private Circle[] stars;

	private Circle[] planets;
    
    @FXML
    public void moveMoon(ActionEvent event) {
    	btnMove.setDisable(true);
    	btnStop.setDisable(false);
    	ball.setMoving(true);
    	EclipseThread bt = new EclipseThread(ball, this);
    	bt.setDaemon(true);
    	bt.start();
    }

    @FXML
    public void stopMoon(ActionEvent event) {
    	btnStop.setDisable(true);
    	btnMove.setDisable(false);
    	ball.setMoving(false);
    }
    
    public void initialize() {
    	ball = new Eclipse(moon.getLayoutX(), moon.getRadius(), sun.getLayoutX(), sun.getRadius(), pane.getPrefWidth());
    	rgb = new int[] {Integer.parseInt(pane.getStyle().substring(26, 29)), Integer.parseInt(pane.getStyle().substring(31, 34)), Integer.parseInt(pane.getStyle().substring(36, 39))};
    	stars = new Circle[] {star1, star2, star3, star4, star5, star6, star7, star8, star9, star10, star11, star12, star13, star14, star15, star16};
    	planets = new Circle[] {planet1, planet2, planet3, planet4, planet5, planet6};
    }
    
    public void update(double x, double light) {
    	crater1.setLayoutX(crater1.getLayoutX()-moon.getLayoutX()+x);
    	crater2.setLayoutX(crater2.getLayoutX()-moon.getLayoutX()+x);
    	crater3.setLayoutX(crater3.getLayoutX()-moon.getLayoutX()+x);
    	moon.setLayoutX(x);
    	if(light<=1) {
    		pane.setStyle("-fx-background-color:rgb("+(int)(rgb[0]*light)+","+(int)(rgb[1]*light)+","+(int)(rgb[2]*light)+");");
    		for(int i=0; i<planets.length; i++) {
    			planets[i].setFill(Color.rgb((int)(255-(255-rgb[0])*light), (int)(255-(255-rgb[1])*light), (int)(255-(255-rgb[2])*light)));
    		}
    		for(int i=0; i<stars.length; i++) {
    			Random random = new Random();
    			if(random.nextBoolean()&&random.nextBoolean()&&random.nextBoolean()) {
    				stars[i].setFill(Color.rgb((int)(rgb[0]*light), (int)(rgb[1]*light), (int)(rgb[2]*light)));
    			} else {
    				stars[i].setFill(Color.rgb((int)(255-(255-rgb[0])*light), (int)(255-(255-rgb[1])*light), (int)(255-(255-rgb[2])*light)));
    			}
    		}
    	}
    }
    
    public long getSleepTime() {
    	return (long)bar.getValue();
    }
}
