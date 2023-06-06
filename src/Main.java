import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root=new AnchorPane();
        root.setPrefWidth(300);
        root.setPrefHeight(300);
        Rectangle rectangle[][]=new Rectangle[3][3];

        Scene scene=new Scene(root);
        stage.setScene(scene);
    }
}