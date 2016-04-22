/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjavafxapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author OleMartin
 */
public class FirstJavaFXApp extends Application {
    
    private ImageView activeBackground;
    private Rectangle activeBackgroundEdge;
    
    private ImageView inactiveBackground;
    private Rectangle inactiveBackgroundEdge;
    
    private ImageView quit;
    private ImageView clickerCookie;
    
    private int cookieCounter;
    private Text counterInfo;
    
    public FirstJavaFXApp() {
        // Starting cookie click amount.
        this.cookieCounter = 0;
        this.counterInfo = new Text();
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        // Size of the main window...............................................
        int mainHeight = 1200;
        int mainWidth = 700;
        
        // Sets borders as invisible.
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        
        // Sets the active background for when cursor is inside the window.
        activeBackground = new ImageView(new Image(FirstJavaFXApp.class.getResourceAsStream("images/background.jpg")));
        activeBackground.setFitHeight(mainHeight);
        activeBackground.setFitWidth(mainWidth);
        activeBackgroundEdge = new Rectangle(mainHeight, mainWidth);
        activeBackgroundEdge.setArcHeight(20);
        activeBackgroundEdge.setArcWidth(20);
        activeBackground.setClip(activeBackgroundEdge);
        activeBackground.setOpacity(1.0);
        
        // Sets inactive background for when cursor is out of the window.
        inactiveBackground = new ImageView(new Image(FirstJavaFXApp.class.getResourceAsStream("images/getBackToWork.jpg")));
        inactiveBackground.setFitHeight(mainHeight);
        inactiveBackground.setFitWidth(mainWidth);
        inactiveBackgroundEdge = new Rectangle(mainHeight, mainWidth);
        inactiveBackgroundEdge.setArcHeight(20);
        inactiveBackgroundEdge.setArcWidth(20);
        inactiveBackground.setClip(inactiveBackgroundEdge);
        
        
        checkIfCursorInWindow();
        
        // Sets an exit button in top right corner.
        setQuit();
        
        // Sets the cookie you are suppose to click.
        setClickerCookie();
        
        // Counts amount of cookies collected.
        setCookieCounter();
        


        Pane root = new Pane();
        root.getChildren().addAll(inactiveBackground, activeBackground, quit, clickerCookie, counterInfo);
        
        Scene myScene = new Scene(root, mainHeight,mainWidth);
        myScene.setFill(null);

        primaryStage.setScene(myScene);
        primaryStage.show();
    }

        /*
         * Check if cursor is inside the window, if it is, then use active
         * background, if not, use inactive background.
         */
    private void checkIfCursorInWindow() {
        // Enable inactive background when cursor leave the window.
        activeBackground.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                setActiveBackground();
            }
            
        });
        
        // Enable active background when cursor leave the window.
        activeBackground.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t){
                // TODO: fix inactive background
                //setInactiveBackground();
            }
            
        });
    }

    private void setActiveBackground() {
        activeBackground.setOpacity(1.0);
        quit.setOpacity(1.0);
        clickerCookie.setOpacity(1.0);
        counterInfo.setOpacity(1.0);
    }

    private void setInactiveBackground() {
        activeBackground.setOpacity(0.0);
        quit.setOpacity(0.0);
        clickerCookie.setOpacity(0.0);
        counterInfo.setOpacity(0.0);
    }

    private void setCookieCounter() {
        counterInfo.setText("Amount of cookies clicked:\n" + cookieCounter);
        counterInfo.setX(400);
        counterInfo.setY(200);
        counterInfo.setFont(Font.font("Verdana",20));
        counterInfo.setFill(Color.WHITE);
        //return t;
    }

    private void setClickerCookie() {
        clickerCookie = new ImageView(new Image(FirstJavaFXApp.class.getResourceAsStream("images/cookie.png")));
        clickerCookie.setFitHeight(250);
        clickerCookie.setFitWidth(250);
        clickerCookie.setX(0);
        clickerCookie.setY(200);
        
        clickerCookie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                updateCookieCounter();
            }
        });
    }
    
    private void updateCookieCounter(){
        cookieCounter++;
        setCookieCounter();
    }

    private void setQuit() {
        quit = new ImageView(new Image(FirstJavaFXApp.class.getResourceAsStream("images/closeIcon.png")));
        quit.setFitHeight(25);
        quit.setFitWidth(25);
        quit.setX(660);
        quit.setY(10);
        
        quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent t) {
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
