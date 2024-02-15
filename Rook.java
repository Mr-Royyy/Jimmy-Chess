import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Rook extends Piece {
    Group root;
    int[] destinationVertical = new int[7];
    int[] destinationHorizontal = new int[7];
    private double offSetX;
    private double offSetY;
    double originalX;
    double originalY;

    Rook(Group root, String pieceColour, int piecePosition) {
        super(pieceColour, piecePosition);
        this.root = root;
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        Tile.createTiles();
        Image blackRookImage;
        ImageView blackRook;
        if (this.pieceColour.equals("white")) {
            blackRookImage = new Image("images/Chess_rlt60.png");
            blackRook = new ImageView(blackRookImage);
            root.getChildren().add(blackRook);
            blackRook.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackRook.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.rookMovement(blackRook);
        } else if (this.pieceColour.equals("black")) {
            blackRookImage = new Image("images/Chess_rdt60.png");
            blackRook = new ImageView(blackRookImage);
            root.getChildren().add(blackRook);
            blackRook.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackRook.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.rookMovement(blackRook);
        }

    }

    public void RookMoves() {
        int coefficient = 1;
        int currentRow = Board.getRow(this.piecePosition);
        int add = 1;

        for(int x = 0; x < this.destinationVertical.length; ++x) {
            this.destinationVertical[x] = this.piecePosition - 8 * coefficient;
            this.destinationHorizontal[x] = this.piecePosition - coefficient++;
            int destinationRow = Board.getRow(this.destinationHorizontal[x]);
            if (this.destinationVertical[x] < 0) {
                this.destinationVertical[x] += 64;
            }

            if (currentRow != destinationRow) {
                this.destinationHorizontal[x] += 8;
            }

            if (currentRow == 0 && this.destinationHorizontal[x] < 0) {
                this.destinationHorizontal[x] = this.piecePosition + add++;
            }
        }

    }

    public void rookMovement(final ImageView i) {
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Rook.this.offSetX = event.getX() - i.getX();
                    Rook.this.offSetY = event.getY() - i.getY();
                    Rook.this.originalX = i.getX();
                    Rook.this.originalY = i.getY();
                    Rook.this.RookMoves();
                }

            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(event.getX() - Rook.this.offSetX);
                i.setY(event.getY() - Rook.this.offSetY);
            }
        };
        EventHandler<MouseEvent> release = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for(int x = 0; x < Rook.this.destinationVertical.length; ++x) {
                    if (Tile.getTile(event.getX(), event.getY()) == Rook.this.destinationVertical[x]) {
                        if (!Tile.isTileEmpty(Rook.this.destinationVertical[x])) {
                            for(int ix = 0; ix < Piece.p.length; ++ix) {
                                if (Piece.p[ix].piecePosition == Rook.this.destinationVertical[x]) {
                                    System.out.println(Piece.p[ix].piecePosition);
                                }
                            }
                        }

                        i.setX(Tile.tiles[Rook.this.destinationVertical[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Rook.this.destinationVertical[x]].tileCoordinateY);
                        Rook.this.piecePosition = Tile.tiles[Rook.this.destinationVertical[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Rook.this.destinationVertical[x]) {
                        i.setX(Rook.this.originalX);
                        i.setY(Rook.this.originalY);
                    }

                    if (Tile.getTile(event.getX(), event.getY()) == Rook.this.destinationHorizontal[x]) {
                        Tile.isTileEmpty(Rook.this.destinationHorizontal[x]);
                        Rook.this.piecePosition = Tile.tiles[Rook.this.destinationHorizontal[x]].tileNumber;
                        i.setY(Tile.tiles[Rook.this.destinationHorizontal[x]].tileCoordinateY);
                        i.setX(Tile.tiles[Rook.this.destinationHorizontal[x]].tileCoordinateX);
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Rook.this.destinationHorizontal[x]) {
                        i.setX(Rook.this.originalX);
                        i.setY(Rook.this.originalY);
                    }
                }

            }
        };
        i.setOnMouseDragged(drag);
        i.setOnMousePressed(click);
        i.setOnMouseReleased(release);
    }
}
    