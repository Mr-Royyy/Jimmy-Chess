import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Pawn extends Piece {
    Group root;
    double originalX;
    double originalY;
    private double offSetX;
    private double offSetY;

    Pawn(Group root, String pieceColour, int piecePosition) {
        super(pieceColour, piecePosition);
        this.root = root;
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        Tile.createTiles();
        Image blackPawnImage;
        ImageView blackPawn;
        if (this.pieceColour.equals("white")) {
            blackPawnImage = new Image("images/Chess_plt60.png");
            blackPawn = new ImageView(blackPawnImage);
            root.getChildren().add(blackPawn);
            blackPawn.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackPawn.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.pawnMovement(blackPawn);
        } else if (this.pieceColour.equals("black")) {
            blackPawnImage = new Image("images/Chess_pdt60.png");
            blackPawn = new ImageView(blackPawnImage);
            root.getChildren().add(blackPawn);
            blackPawn.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackPawn.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.pawnMovement(blackPawn);
        }

    }

    public void pawnMovement(final ImageView i) {
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Pawn.this.offSetX = event.getX() - i.getX();
                    Pawn.this.offSetY = event.getY() - i.getY();
                    Pawn.this.originalX = i.getX();
                    Pawn.this.originalY = i.getY();
                }

            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(event.getX() - Pawn.this.offSetX);
                i.setY(event.getY() - Pawn.this.offSetY);
            }
        };
        EventHandler<MouseEvent> release = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(Tile.tiles[Tile.getTile(event.getX(), event.getY())].tileCoordinateX);
                i.setY(Tile.tiles[Tile.getTile(event.getX(), event.getY())].tileCoordinateY);
                Pawn.this.piecePosition = Tile.tiles[Tile.getTile(event.getX(), event.getY())].tileNumber;
            }
        };
        i.setOnMousePressed(click);
        i.setOnMouseDragged(drag);
        i.setOnMouseReleased(release);
    }
}
    