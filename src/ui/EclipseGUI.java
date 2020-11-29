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
import thread.LightThread;

public class EclipseGUI {
	
	@FXML
    private Circle star1;
	
	@FXML
    private Circle star2;
	
	@FXML
    private Circle star3;
	
	@FXML
    private Circle star4;
	
	@FXML
    private Circle star5;
	
	@FXML
    private Circle star6;
	
	@FXML
    private Circle star7;
	
	@FXML
    private Circle star8;

    @FXML
    private Circle star9;

    @FXML
    private Circle star10;
    
    @FXML
    private Circle star11;

    @FXML
    private Circle star12;

    @FXML
    private Circle star13;

    @FXML
    private Circle star14;

    @FXML
    private Circle star15;

    @FXML
    private Circle star16;
    
    @FXML
    private Circle planet1;

    @FXML
    private Circle planet2;

    @FXML
    private Circle planet3;

    @FXML
    private Circle planet4;

    @FXML
    private Circle planet5;

    @FXML
    private Circle planet6;

    @FXML
    private Pane pane;

    @FXML
    private Slider bar;

    @FXML
    private Button btnMove;

    @FXML
    private Button btnStop;

    @FXML
    private Circle sun;

    @FXML
    private Circle moon;
    
    @FXML
    private Circle crater1;

    @FXML
    private Circle crater2;

    @FXML
    private Circle crater3;
    
    
    private Eclipse ball;
    
    private Random random;
    
    private int[] rgb;
    
    private Circle[] stars;

	private Circle[] planets;
	
	private Circle[] movingCircles;
    
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
    	ball = new Eclipse(moon.getLayoutX(), moon.getRadius(), sun.getLayoutX(), pane.getPrefWidth());
    	random = new Random();
    	rgb = new int[] {255, 255, 200}; //255, 255, 176
    	stars = new Circle[] {star1, star2, star3, star4, star5, star6, star7, star8, star9, star10, star11, star12, star13, star14, star15, star16};
    	planets = new Circle[] {planet1, planet2, planet3, planet4, planet5, planet6};
    	movingCircles = new Circle[] {crater1, crater2, crater3, moon};
    	fixColors();
    }
    
    private void fixColors() {
    	updatePane(rgb[0], rgb[1], rgb[2]);
    	updateCircles(planets, rgb[0], rgb[1], rgb[2], true);
    	updateCircles(stars, rgb[0], rgb[1], rgb[2], true);
    	LightThread bt = new LightThread(ball, this);
    	bt.setDaemon(true);
    	bt.start();
    }
    
    public void update(double x) {
    	for(int i=0; i<movingCircles.length; i++) {
    		movingCircles[i].setLayoutX(movingCircles[i].getLayoutX()-movingCircles[3].getLayoutX()+x);
    	}
    }
    
    public void updateLight(double light) {
    	if(light<=1) {
    		updateLight((int)(rgb[0]*light), (int)(rgb[1]*light), (int)(rgb[2]*light), (int)(255*(1-light)));
    	}
    }
    
    private void updateLight(int r, int g, int b, int c) {
    	updatePane(r, g, b);
    	updateCircles(planets, c+r, c+g, c+b, true);
    	updateCircles(stars, c+r, c+g, c+b, true);
    	updateCircles(stars, r, g, b, false);
    }

	private void updatePane(int r, int g, int b) {
    	pane.setStyle("-fx-background-color:rgb("+r+","+g+","+b+");");
    	
	}
	
	private void updateCircles(Circle[] circles, int r, int g, int b, boolean steady) {
		for(int i=0; i<circles.length; i++) {
			if(steady||(random.nextBoolean()&&random.nextBoolean()&&random.nextBoolean()&&random.nextBoolean()&&random.nextBoolean())) {
				circles[i].setFill(Color.rgb(r, g, b));
			}
		}
	}

	public long getSleepTime() {
    	return (long)bar.getValue();
    }
}
