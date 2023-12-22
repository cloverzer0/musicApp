import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;

import java.util.Scanner;

public class DVDCollectionApp1  extends Application {
    private DVDCollection model;
    private Scanner input1;
    private Scanner input2;
    private Scanner input3;

    public DVDCollectionApp1() {
        model=DVDCollection.example1();
        input1 =new Scanner(System.in);
        input2 =new Scanner(System.in);
        input3 =new Scanner(System.in);
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        DVDCollectionAppView1  view = new DVDCollectionAppView1();
        view.update(model, -1);
        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.out.println("Please Enter Title: ");
                String title=input1.nextLine();
                System.out.println("Please Enter Year: ");
                int year=input2.nextInt();
                System.out.println("Please Enter Length: ");
                int length=input3.nextInt();
                DVD newDVD=new DVD(title,year,length);
                model.add(newDVD);
                view.update(model,-1);
            }
        });
        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String selected=view.getTitleList().getSelectionModel().getSelectedItem();
                model.remove(selected);
                view.update(model,-1);
            }
        });
        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                int selected=view.getTitleList().getSelectionModel().getSelectedIndex();
                view.getLengthList().getSelectionModel().select(selected);
                view.getYearList().getSelectionModel().select(selected);
                view.update(model,selected);
            }
        });
        System.out.println(view.getTitleList().getSelectionModel().getSelectedIndex());
        view.getYearList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                int selected=view.getYearList().getSelectionModel().getSelectedIndex();
                view.getTitleList().getSelectionModel().select(selected);
                view.getLengthList().getSelectionModel().select(selected);
                view.update(model,selected);
            }
        });
        view.getLengthList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                int selected=view.getLengthList().getSelectionModel().getSelectedIndex();
                view.getTitleList().getSelectionModel().select(selected);
                view.getYearList().getSelectionModel().select(selected);
                view.update(model,selected);
            }
        });
        aPane.getChildren().add(view);

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}