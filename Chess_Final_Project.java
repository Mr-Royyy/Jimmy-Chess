import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chess_Final_Project extends Application {
    final int MAX_HEIGHT = 512;
    final int MAX_WIDTH = 512;
    Group root = new Group();

    public void start(Stage primaryStage) {
        Scene scene = new Scene(this.root, 512.0D, 512.0D);
        primaryStage.setTitle("Chess Final Project");
        primaryStage.setScene(scene);
        primaryStage.show();
        Board board = new Board();
        board.Board(this.root);
        Piece.p[0] = new Rook(this.root, "white", 63);
        Piece.p[1] = new Rook(this.root, "white", 56);
        Piece.p[2] = new Bishop(this.root, "white", 58);
        Piece.p[3] = new Bishop(this.root, "white", 61);
        Piece.p[4] = new Knight(this.root, "white", 57);
        Piece.p[5] = new Knight(this.root, "white", 62);
        Piece.p[6] = new Queen(this.root, "white", 59);
        Piece.p[7] = new King(this.root, "white", 60);
        Piece.p[8] = new Pawn(this.root, "white", 48);
        Piece.p[9] = new Pawn(this.root, "white", 49);
        Piece.p[10] = new Pawn(this.root, "white", 50);
        Piece.p[11] = new Pawn(this.root, "white", 51);
        Piece.p[12] = new Pawn(this.root, "white", 52);
        Piece.p[13] = new Pawn(this.root, "white", 53);
        Piece.p[14] = new Pawn(this.root, "white", 54);
        Piece.p[15] = new Pawn(this.root, "white", 55);
        Piece.p[16] = new Rook(this.root, "black", 0);
        Piece.p[17] = new Rook(this.root, "black", 7);
        Piece.p[18] = new King(this.root, "black", 3);
        Piece.p[19] = new Knight(this.root, "black", 6);
        Piece.p[20] = new Knight(this.root, "black", 1);
        Piece.p[21] = new Queen(this.root, "black", 4);
        Piece.p[22] = new Bishop(this.root, "black", 2);
        Piece.p[23] = new Bishop(this.root, "black", 5);
        Piece.p[24] = new Pawn(this.root, "black", 8);
        Piece.p[25] = new Pawn(this.root, "black", 9);
        Piece.p[26] = new Pawn(this.root, "black", 10);
        Piece.p[27] = new Pawn(this.root, "black", 11);
        Piece.p[28] = new Pawn(this.root, "black", 12);
        Piece.p[29] = new Pawn(this.root, "black", 13);
        Piece.p[30] = new Pawn(this.root, "black", 14);
        Piece.p[31] = new Pawn(this.root, "black", 15);
    }
}