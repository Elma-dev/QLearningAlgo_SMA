import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root=new AnchorPane();
        root.setPrefWidth(600);
        root.setPrefHeight(600);
        Rectangle rectangle[][]=new Rectangle[6][6];





        int gameGrid [][]=new int[][]{
                { 1,  0,  0,  0,  0,  0},
                {-1, -1,  0, -1, -1, -1},
                { 0,  0, 0,  0,  0,  0},
                { 0,  0,  0,  0,  0,  0},
                {-1,  0,  0,  0, -1,  0},
                {-1, -1,  0, -1, -1, -1},

            };

        //qlearning algorithm
        QLearning qLearning=new QLearning(gameGrid);


        for(int i=0;i<6;i++){
            for (int j=0;j<6;j++){
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





        System.out.println("first: "+qLearning.getStateI()+" "+qLearning.getStateJ()*50);








        Task<String> task=new Task<String>() {
            @Override
            protected String call() throws Exception {

                int it = 0;
                qLearning.resetState();
                int currentState;
                int nextState;
                while (it<20000){
                    if(gameGrid[qLearning.getStateJ()][qLearning.getStateI()]==-1){
                        rectangle[qLearning.getStateJ()][qLearning.getStateI()].setFill(Color.RED);
                    }
                    else
                        rectangle[qLearning.getStateJ()][qLearning.getStateI()].setFill(Color.WHITE);
                    currentState = qLearning.getStateI()*6 + qLearning.getStateJ();
//                    if(rectangle[qLearning.getStateJ()][qLearning.getStateI()].getFill()!=Color.RED)
//                        rectangle[qLearning.getStateJ()][qLearning.getStateI()].setFill(Color.WHITE);
                    int act = qLearning.chooseAction(currentState);
                    nextState = qLearning.executeAction(act);
                    rectangle[qLearning.getStateJ()][qLearning.getStateI()].setFill(Color.GOLD);
                    System.out.println(qLearning.getStateI() + " " + qLearning.getStateJ() + " " + gameGrid[qLearning.getStateI()][qLearning.getStateJ()]);
                    it++;
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                return null;
            }
        };
        new Thread(task).start();





        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}