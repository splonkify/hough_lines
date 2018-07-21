
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        ImageView imageView = addImage();
        root.getChildren().add(imageView);
        int sceneWidth = (int) imageView.getImage().getWidth();
        int sceneHeight = (int) imageView.getImage().getHeight();

        Canvas canvas = new Canvas(sceneWidth, sceneHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setTitle("Hough Lines");
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
        draw(imageView.getImage(), gc);
    }

    private ImageView addImage() {
        Image image = new Image("/vase.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setCache(true);
        return imageView;
    }

    private void draw(Image image, GraphicsContext gc) {
        HoughTransform h = new HoughTransform(image);
        List<HoughLine> lines = h.getLines(4);  //get the top scoring 4 lines

        drawHoughLines(lines, gc);
    }

    private void drawHoughLines(List<HoughLine> lines, GraphicsContext gc) {
        if (lines!=null) {
            gc.setStroke(Color.rgb(255, 0, 0));

            for (HoughLine line : lines) {
                gc.strokeLine(line.x1, line.y1, line.x2, line.y2);
                //println(j+ " " + l.score);
            }
        }
    }
}
