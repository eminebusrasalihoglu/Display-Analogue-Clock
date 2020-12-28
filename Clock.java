/*
 *@author eminebusrasalihoglu
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;
public class Clock extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        EventHandler<ActionEvent> eventHandler = e -> {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Calendar calendar=new GregorianCalendar();
            int hour=calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second=calendar.get(Calendar.SECOND);
            if(hour>12){//when not in pm am format
                hour=hour-12;
            }
            double myArc1=30*hour; //hour angle
            double myArc2=6*minute;//minute angle
            Circle circle = new Circle();//the circle is created
            circle.centerXProperty().bind(pane.widthProperty().divide(2));//binding
            circle.centerYProperty().bind(pane.heightProperty().divide(2));//binding
            circle.setRadius(100);
            circle.setStroke(Color.DARKMAGENTA);
            circle.setFill(Color.TRANSPARENT);
            pane.getChildren().add(circle);
            Text text1 = new Text(250, 165, "12");
            text1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane.getChildren().add(text1);
            Text text2 = new Text(340, 250, "3");
            text2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane.getChildren().add(text2);
            Text text3 = new Text(250, 350, "6");
            text3.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane.getChildren().add(text3);
            Text text4 = new Text(150, 250, "9");
            text4.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 15));
            pane.getChildren().add(text4);
            Text text5=new Text(150,400,formatter.format(calendar.getTime()));
            text5.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 20));
            pane.getChildren().add(text5);

            Line line1 = new Line(250, 200, 250, 250);//hour hand
            line1.endXProperty().bind(pane.widthProperty().subtract(250));
            line1.endYProperty().bind(pane.heightProperty().subtract(250));
            line1.setStrokeWidth(5);
            line1.setStroke(Color.RED);
            Line line2 = new Line(250, 180, 250, 250);//minute hand
            line2.endXProperty().bind(pane.widthProperty().subtract(250));
            line2.endYProperty().bind(pane.heightProperty().subtract(250));
            line2.setStrokeWidth(5);
            line2.setStroke(Color.DARKBLUE);
            Rotate rotate1 = new Rotate();//We created to rotate the lines
            Rotate rotate2 = new Rotate();//We created to rotate the lines
            Rotate rotate3 =new Rotate();//We created to rotate the lines
            rotate1.setAngle(myArc1);
            rotate1.setPivotX(250);
            rotate1.setPivotY(250);
            line1.getTransforms().add(rotate1);
            pane.getChildren().add(line1);
            rotate2.setAngle(myArc2);
            rotate2.setPivotX(250);
            rotate2.setPivotY(250);
            line2.getTransforms().add(rotate2);
            double secondX = 250 + (100* 0.8) * Math.sin(second * (2 * Math.PI / 60));
            double secondY = 250- (100* 0.8)* Math.cos(second * (2 * Math.PI / 60));
            Line line3 = new Line(250, 250, secondX, secondY);//second hand
            pane.getChildren().add(line2);
            pane.getChildren().clear();
            pane.getChildren().addAll(circle,line1, line2,line3,text1,text2,text3,text4,text5);
        };
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        Scene scene=new Scene(pane,500,500);
        primaryStage.setTitle("C L O C K");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

