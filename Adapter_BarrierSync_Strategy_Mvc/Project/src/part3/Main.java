package part3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gui main class
 */
public class Main extends Application {
    /**
     * Starts gui
     * @param primaryStage primaryStage
     * @throws Exception throwing exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Discrete Fourier Transform Solver");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * Gui main method
     * @param args args
     * @throws InterruptedException when error occurs state wait
     */
    public static void main(String [] args) throws InterruptedException {
        launch(args);

    }
}
