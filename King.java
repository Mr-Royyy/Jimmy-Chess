import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class King extends Piece {
    Group root;
    double originalX;
    double originalY;
    private double offSetX;
    private double offSetY;

    King(Group root, String pieceColour, int piecePosition) {
        super(pieceColour, piecePosition);
        this.root = root;
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        Tile.createTiles();
        Image blackKingImage;
        ImageView blackKing;
        if (this.pieceColour.equals("white")) {
            blackKingImage = new Image("images/Chess_klt60.png");
            blackKing = new ImageView(blackKingImage);
            root.getChildren().add(blackKing);
            blackKing.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackKing.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.kingMovement(blackKing);
        } else if (this.pieceColour.equals("black")) {
            blackKingImage = new Image("images/Chess_kdt60.png");
            blackKing = new ImageView(blackKingImage);
            root.getChildren().add(blackKing);
            blackKing.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackKing.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.kingMovement(blackKing);
        }

    }

    public void kingMovement(final ImageView i) {
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    King.this.offSetX = event.getX() - i.getX();
                    King.this.offSetY = event.getY() - i.getY();
                    King.this.originalX = i.getX();
                    King.this.originalY = i.getY();
                }

            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(event.getX() - King.this.offSetX);
                i.setY(event.getY() - King.this.offSetY);
            }
        };
        EventHandler<MouseEvent> release = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(Tile.tiles[Tile.getTile(event.getX(), event.getY())].tileCoordinateX);
                i.setY(Tile.tiles[Tile.getTile(event.getX(), event.getY())].tileCoordinateY);
                King.this.piecePosition = Tile.tiles[Tile.getTile(event.getX(), event.getY())].tileNumber;
            }
        };
        i.setOnMousePressed(click);
        i.setOnMouseDragged(drag);
        i.setOnMouseReleased(release);
    }
}