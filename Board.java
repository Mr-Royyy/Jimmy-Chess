import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board {
    int[][] board = new int[][]{{1, 0, 1, 0, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 1}};
    Group root;

    public void Board(Group root) {
        Rectangle[][] r = new Rectangle[this.board.length][this.board[0].length];
        this.root = root;

        for(int row = 0; row < this.board.length; ++row) {
            for(int col = 0; col < this.board[0].length; ++col) {
                r[row][col] = new Rectangle((double)(row * 64), (double)(col * 64), 64.0D, 64.0D);
                if (this.board[row][col] == 1) {
                    r[row][col].setFill(Color.BEIGE);
                } else if (this.board[row][col] == 0) {
                    r[row][col].setFill(Color.SANDYBROWN);
                }

                root.getChildren().add(r[row][col]);
            }
        }

    }

    public static int getRow(int position) {
        int row = position / 8;
        return (int)Math.floor((double)row);
    }

    public static int getFile(int position) {
        return position % 8;
    }
}