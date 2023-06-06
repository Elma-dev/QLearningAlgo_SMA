import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root=new AnchorPane();
        root.setPrefWidth(300);
        root.setPrefHeight(300);
        Rectangle rectangle[][]=new Rectangle[3][3];





        int gameGrid [][]=new int[][]{
                {0,0,0},
                {0,-1,0},
                {0,0,1}
            };

        //qlearning algorithm
        QLearning qLearning=new QLearning(gameGrid);


        for(int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                rectangle[i][j]=new Rectangle();
                rectangle[i][j].setLayoutX(i*100);
                rectangle[i][j].setLayoutY(j*100);
                if(gameGrid[i][j]==0){
                    rectangle[i][j].setFill(Color.WHITE);
                }
                else if(gameGrid[i][j]==1)
                    rectangle[i][j].setFill(Color.GREEN);
                else
                    rectangle[i][j].setFill(Color.RED);

                rectangle[i][j].setStroke(Color.BLACK);
                rectangle[i][j].setWidth(100);
                rectangle[i][j].setHeight(100);
                root.getChildren().add(rectangle[i][j]);
            }
        }



            int it = 0;
            qLearning.resetState();
            int currentState;
            int nextState;

        System.out.println("first: "+qLearning.getStateI()+" "+qLearning.getStateJ()*50);





        while (it<30){
            currentState = qLearning.getStateI()* 3 + qLearning.getStateJ();
            int act = qLearning.chooseAction(currentState);
            rectangle[qLearning.getStateJ()][qLearning.getStateI()].setFill(Color.GOLD);
            System.out.println(qLearning.getStateI() + " " + qLearning.getStateJ() + " " + gameGrid[qLearning.getStateI()][qLearning.getStateJ()]);
            it++;
        }






        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}