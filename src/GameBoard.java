import java.util.ArrayList;
import java.util.List;

import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/***************************************************************
 * This class is used for creating the game board
 * as well as everything to handle in the game
 * 
 * @author Nguyen Thanh Khoi Tran
 * @date Dec 24th, 2024
 * @version proj_v01
 **************************************************************/

public class GameBoard extends Application {
    private final int ROW_STACK = 24;
    private final int COL_STACK = 10;
    private final Block[][] board = new Block[ROW_STACK][COL_STACK];
    public static String name = "";

    /***********************************************************
     * This method is used for showing the intro pane
     ***********************************************************/
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Label title = new Label("TETRIS");
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size: 50; -fx-font-weight: bold; -fx-text-fill:rgb(231, 223, 223);");

        // Create a timeline for blinking effect
        Timeline tl = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> title.setVisible(true)),
                new KeyFrame(Duration.seconds(1), e -> title.setVisible(false)));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        // Use VBox with background image
        Image background = new Image("resources/background2.jpg");
        BackgroundSize bgSize = new BackgroundSize(900, 600, true, true, true, true);
        BackgroundImage bkgd = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgSize);
        Background bgForVBox = new Background(bkgd);

        // Create TextField for player names
        TextField playerName = new TextField();
        playerName.setMaxWidth(300);
        playerName.setPrefHeight(50);

        // Clear the prompt text
        playerName.setPromptText("");
        playerName.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button startButton = new Button("Start");

        // Add event handler for the start button
        // Put the name into provided variable when the button is clicked
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    name = playerName.getText();
                    if (name.isEmpty()) {
                        throw new NoNameInputException(
                                "Wait !!!! Do you have any name? If does, please type it into this beautiful field, PLEASE!");
                    } else {
                        primaryStage.setScene(playingGame(primaryStage));
                    }
                } catch (NoNameInputException nnie) {
                    showNameAlert(" >>> ERROR <<<", nnie.getMessage());
                }
            }
        });

        // Player can press Enter to start the game
        playerName.setOnAction(event -> startButton.fire());

        // Set preferred size
        startButton.setPrefSize(200, 40);

        // Set button color to green and text to bold
        startButton.setStyle(
                "-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 30px;");

        // Add components to VBox
        VBox opening = new VBox(10);
        opening.setAlignment(Pos.CENTER);
        opening.setBackground(bgForVBox);
        opening.getChildren().addAll(title, playerName, startButton);
        root.getChildren().add(opening);

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("TETRIS GAME");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /****************************************************************
     * This method is used for creating the game board
     ****************************************************************/
    public Scene playingGame(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Image background = new Image("resources/playingGame_bkgd.png");
        BackgroundSize bgSize = new BackgroundSize(900, 600, true, true, true, true);
        BackgroundImage bkgd = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bgSize);
        Background bg = new Background(bkgd);
        root.setBackground(bg);

        // Top - Label for displaying player name
        Label playerName = new Label("Player: " + name);
        playerName.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #000080;");
        BorderPane.setAlignment(playerName, Pos.CENTER);
        root.setTop(playerName);

        // Left - Function button
        Button restartButton = new Button("Restart");
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(new Scene(new StackPane(), 900, 600));
                start(primaryStage);
            }
        });

        restartButton.setStyle(
                "-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20px;");
        root.setLeft(restartButton);
        restartButton.setPadding(new Insets(10));
        // Center - Create a game board
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(10));
        gp.setGridLinesVisible(true);

        for (int row = 0; row < ROW_STACK; row++) {
            for (int col = 0; col < COL_STACK; col++) {
                StackPane cell = new StackPane();
                cell.setPrefSize(30, 30);

                // Set background color for the board
                BackgroundFill backgroundFill = new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY);
                Background bkgd_board = new Background(backgroundFill);
                cell.setBackground(bkgd_board);

                // Set border color for the board
                cell.setStyle("-fx-border-color: lightgray; -fx-border-width: 1.5;");
                gp.add(cell, col, row);
            }
        }
        root.setCenter(gp);

        // Spawn initial block
        Block currentBlock = spawnBlock();

        // Display the block on the grid with color
        for (int row = 0; row < currentBlock.getCurrentShape().length; row++) {
            for (int col = 0; col < currentBlock.getCurrentShape()[0].length; col++) {
                if (currentBlock.getCurrentShape()[row][col] != 0) {
                    StackPane cell = new StackPane();
                    cell.setPrefSize(30, 30);
                    BackgroundFill backgroundFill = new BackgroundFill(currentBlock.getColor(), CornerRadii.EMPTY,
                            Insets.EMPTY);
                    cell.setBackground(new Background(backgroundFill));
                    gp.add(cell, currentBlock.getX() + col, currentBlock.getY() + row);
                }
            }
        }

        return new Scene(root, 900, 600);
    }

    /****************************************************************
     * Check if a block can move to a new position
     ****************************************************************/
    public boolean move(Block b, int dx, int dy) {
        int[][] shape = b.getCurrentShape();
        int newX = b.getX() + dx;
        int newY = b.getY() + dy;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int x = newX + col;
                    int y = newY + row;

                    if (x < 0 || x >= COL_STACK || y >= ROW_STACK) {
                        return false;
                    }

                    if (y >= 0 && board[y][x] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*************************************************
     * Place a block on the board
     *************************************************/
    public void place(Block b) {
        int[][] shape = b.getCurrentShape();
        int x = b.getX();
        int y = b.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    board[y + row][x + col] = b;
                }
            }
        }
    }

    /*************************************************
     * A method to detect the full line and clear it
     * as well as shift the rows down
     *************************************************/
    public void clearLines() {
        List<Integer> fullLines = new ArrayList<>();

        // Identify full lines
        for (int row = 0; row < ROW_STACK; row++) {
            boolean full = true;
            for (int col = 0; col < COL_STACK; col++) {
                if (board[row][col] == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                fullLines.add(row);
            }
        }

        // Remove full lines and shift rows down
        for (int row : fullLines) {
            for (int r = row; r > 0; r--) {
                for (int col = 0; col < COL_STACK; col++) {
                    board[r][col] = board[r - 1][col];
                }
            }
        }
    }

    /***********************************
     * Check if the game is over
     ***********************************/
    public boolean gameOver(Block b) {
        int[][] shape = b.getCurrentShape();
        int x = b.getX();
        int y = b.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = x + col;
                    int boardY = y + row;

                    // Check if the block is above the grid or overlaps existing blocks
                    if (boardY < 0 || (boardY >= 0 && board[boardY][boardX] != null)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /************************************************************************************************************
     * Make a method for exception if player does not input anything, display the
     * message to force user inputting
     ************************************************************************************************************/
    private void showNameAlert(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setTitle(title);
        alertStage.setResizable(false);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("OK, I understand");
        closeButton.setOnAction(event -> alertStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 550, 100);
        alertStage.setScene(scene);
        alertStage.showAndWait();
    }

    /**********************************
     * A function to spawn a new block
     **********************************/
    public Block spawnBlock() {
        TetrisShape[] shapes = TetrisShape.values();
        TetrisShape random = shapes[(int) (Math.random() * shapes.length)];

        // Randomly select a shape except the single one (use for other purposes)
        while (random == TetrisShape.SINGLE) {
            random = shapes[(int) (Math.random() * shapes.length)];
        }

        // Create a new block object
        Block newBlock = new Block(random.getCoordinates(), random.getColor());

        // Set the block's initial position at the top center
        int startX = (COL_STACK - newBlock.getCurrentShape()[0].length) / 2;
        newBlock.setX(startX);
        newBlock.setY(0);

        // Check if the game is over
        if (!move(newBlock, 0, 0))
            showNameAlert("GAME OVER", "THE BOARD IS FULL!");

        return newBlock;
    }
}
