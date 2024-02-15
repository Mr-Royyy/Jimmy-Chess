import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Knight extends Piece {
    Group root;
    int[] destinationVertical = new int[4];
    int[] destinationHorizontal = new int[4];
    double originalX;
    double originalY;
    private double offSetX;
    private double offSetY;

    Knight(Group root, String pieceColour, int piecePosition) {
        super(pieceColour, piecePosition);
        this.root = root;
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        Tile.createTiles();
        Image blackKnightImage;
        ImageView blackKnight;
        if (this.pieceColour.equals("white")) {
            blackKnightImage = new Image("images/Chess_nlt60.png");
            blackKnight = new ImageView(blackKnightImage);
            root.getChildren().add(blackKnight);
            blackKnight.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackKnight.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.knightMovement(blackKnight);
        } else if (this.pieceColour.equals("black")) {
            blackKnightImage = new Image("images/Chess_ndt60.png");
            blackKnight = new ImageView(blackKnightImage);
            root.getChildren().add(blackKnight);
            blackKnight.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackKnight.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.knightMovement(blackKnight);
        }

    }

    public void KnightMoves() {
        for(int x = 0; x < this.destinationVertical.length; ++x) {
            if (x == 0) {
                if (this.piecePosition % 8 == 0) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition % 8 == 1) {
                    this.destinationVertical[x] = this.piecePosition - 17;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 0 && this.piecePosition < 8) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 8 && this.piecePosition < 16 && this.piecePosition % 8 > 2) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = this.piecePosition - 10;
                } else {
                    this.destinationVertical[x] = this.piecePosition - 17;
                    this.destinationHorizontal[x] = this.piecePosition - 10;
                }
            } else if (x == 1) {
                if (this.piecePosition % 8 == 7) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition % 8 == 6) {
                    this.destinationVertical[x] = this.piecePosition - 15;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 0 && this.piecePosition < 8) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 8 && this.piecePosition < 16 && this.piecePosition % 8 < 6) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = this.piecePosition - 6;
                } else {
                    this.destinationVertical[x] = this.piecePosition - 15;
                    this.destinationHorizontal[x] = this.piecePosition - 6;
                }
            } else if (x == 2) {
                if (this.piecePosition % 8 == 7) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition % 8 == 6) {
                    this.destinationVertical[x] = this.piecePosition + 17;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 56 && this.piecePosition < 63) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 48 && this.piecePosition < 56 && this.piecePosition % 8 < 6) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = this.piecePosition + 10;
                } else {
                    this.destinationVertical[x] = this.piecePosition + 17;
                    this.destinationHorizontal[x] = this.piecePosition + 10;
                }
            } else if (x == 3) {
                if (this.piecePosition % 8 == 0) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition % 8 == 1) {
                    this.destinationVertical[x] = this.piecePosition + 15;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 56 && this.piecePosition < 63) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = -1;
                } else if (this.piecePosition >= 48 && this.piecePosition < 56 && this.piecePosition % 8 > 2) {
                    this.destinationVertical[x] = -1;
                    this.destinationHorizontal[x] = this.piecePosition + 6;
                } else {
                    this.destinationVertical[x] = this.piecePosition + 15;
                    this.destinationHorizontal[x] = this.piecePosition + 6;
                }
            }
        }

    }

    public void knightMovement(final ImageView i) {
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Knight.this.offSetX = event.getX() - i.getX();
                    Knight.this.offSetY = event.getY() - i.getY();
                    Knight.this.originalX = i.getX();
                    Knight.this.originalY = i.getY();
                    Knight.this.KnightMoves();
                }

            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(event.getX() - Knight.this.offSetX);
                i.setY(event.getY() - Knight.this.offSetY);
            }
        };
        EventHandler<MouseEvent> release = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for(int x = 0; x < Knight.this.destinationVertical.length; ++x) {
                    if (Tile.getTile(event.getX(), event.getY()) == Knight.this.destinationVertical[x]) {
                        i.setX(Tile.tiles[Knight.this.destinationVertical[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Knight.this.destinationVertical[x]].tileCoordinateY);
                        Knight.this.piecePosition = Tile.tiles[Knight.this.destinationVertical[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Knight.this.destinationVertical[x]) {
                        i.setX(Knight.this.originalX);
                        i.setY(Knight.this.originalY);
                    }

                    if (Tile.getTile(event.getX(), event.getY()) == Knight.this.destinationHorizontal[x]) {
                        i.setX(Tile.tiles[Knight.this.destinationHorizontal[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Knight.this.destinationHorizontal[x]].tileCoordinateY);
                        Knight.this.piecePosition = Tile.tiles[Knight.this.destinationHorizontal[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Knight.this.destinationHorizontal[x]) {
                        i.setX(Knight.this.originalX);
                        i.setY(Knight.this.originalY);
                    }
                }

            }
        };
        i.setOnMouseDragged(drag);
        i.setOnMousePressed(click);
        i.setOnMouseReleased(release);
    }
}