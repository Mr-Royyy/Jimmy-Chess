import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Bishop extends Piece {
    Group root;
    private double offSetX;
    private double offSetY;
    double originalX;
    double originalY;
    int[] diagonalRight = new int[7];
    int[] diagonalLeft = new int[7];

    Bishop(Group root, String pieceColour, int piecePosition) {
        super(pieceColour, piecePosition);
        this.root = root;
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        Tile.createTiles();
        Image blackBishopImage;
        ImageView blackBishop;
        if (this.pieceColour.equals("white")) {
            blackBishopImage = new Image("images/Chess_blt60.png");
            blackBishop = new ImageView(blackBishopImage);
            root.getChildren().add(blackBishop);
            blackBishop.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackBishop.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.bishopMovement(blackBishop);
        } else if (this.pieceColour.equals("black")) {
            blackBishopImage = new Image("images/Chess_bdt60.png");
            blackBishop = new ImageView(blackBishopImage);
            root.getChildren().add(blackBishop);
            blackBishop.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackBishop.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.bishopMovement(blackBishop);
        }

    }

    public void BishopMoves() {
        int coefficient1 = 1;
        int coefficient2 = 1;
        int coefficientA = 1;
        int coefficientB = 1;

        int i;
        int x;
        label132:
        for(x = 0; x < this.diagonalRight.length; ++x) {
            i = x;
            this.diagonalRight[x] = this.piecePosition - 7 * coefficient1++;
            int destinationRowRight = Board.getRow(this.diagonalRight[x]);
            if (this.piecePosition % 8 == 7) {
                this.diagonalRight[x] = this.piecePosition + 7 * coefficient2++;
            } else {
                if (this.piecePosition % 8 == 0 && this.diagonalRight[x] < 0) {
                    i = x + 1;

                    while(true) {
                        if (i >= this.diagonalRight.length) {
                            break label132;
                        }

                        this.diagonalRight[i] = -1;
                        ++i;
                    }
                }

                if (this.diagonalRight[x] <= 0) {
                    this.diagonalRight[x] = this.piecePosition + 7 * coefficient2;
                }

                if (x > 0) {
                    int prevMoveRowRight = Board.getRow(this.diagonalRight[x - 1]);
                    if (destinationRowRight < Board.getRow(this.piecePosition) && this.diagonalRight[x] <= 0) {
                        i = x;

                        while(true) {
                            if (i >= this.diagonalRight.length) {
                                break label132;
                            }

                            this.diagonalRight[i] = -1;
                            ++i;
                        }
                    }

                    if (prevMoveRowRight == destinationRowRight) {
                        this.diagonalRight[x] = this.piecePosition + 7 * coefficient2;
                    }

                    if (prevMoveRowRight > Board.getRow(this.piecePosition)) {
                        ++coefficient2;
                        this.diagonalRight[x] = this.piecePosition + 7 * coefficient2;
                    }

                    destinationRowRight = Board.getRow(this.diagonalRight[x]);
                    if (prevMoveRowRight > Board.getRow(this.piecePosition) && prevMoveRowRight == destinationRowRight) {
                        while(true) {
                            if (i >= this.diagonalRight.length) {
                                break label132;
                            }

                            this.diagonalRight[i] = -1;
                            ++i;
                        }
                    }
                }
            }
        }

        for(x = 0; x < this.diagonalLeft.length; ++x) {
            i = x;
            this.diagonalLeft[x] = this.piecePosition - 9 * coefficientA++;
            int destinationRowLeft = Board.getRow(this.diagonalLeft[x]);
            if (this.piecePosition % 8 == 0) {
                this.diagonalLeft[x] = this.piecePosition + 9 * coefficientB++;
            } else {
                if (this.piecePosition % 8 == 7 && this.diagonalLeft[x] < 0) {
                    for(i = x + 1; i < this.diagonalLeft.length; ++i) {
                        this.diagonalLeft[i] = -1;
                    }

                    return;
                }

                if (this.diagonalLeft[x] < 0) {
                    this.diagonalLeft[x] = this.piecePosition + 9 * coefficientB;
                }

                if (x > 0) {
                    int prevMoveRowLeft = Board.getRow(this.diagonalLeft[x - 1]);
                    if (destinationRowLeft < Board.getRow(this.piecePosition) && this.diagonalLeft[x] < 0) {
                        for(i = x; i < this.diagonalLeft.length; ++i) {
                            this.diagonalLeft[i] = -1;
                        }

                        return;
                    }

                    if (prevMoveRowLeft == destinationRowLeft - 2) {
                        this.diagonalLeft[x] = this.piecePosition + 9 * coefficientB;
                    }

                    if (prevMoveRowLeft > Board.getRow(this.piecePosition)) {
                        ++coefficientB;
                        this.diagonalLeft[x] = this.piecePosition + 9 * coefficientB;
                    }

                    destinationRowLeft = Board.getRow(this.diagonalLeft[x]);
                    if (prevMoveRowLeft > Board.getRow(this.piecePosition) && prevMoveRowLeft + 2 == destinationRowLeft) {
                        while(i < this.diagonalLeft.length) {
                            this.diagonalLeft[i] = -1;
                            ++i;
                        }

                        return;
                    }

                    if (prevMoveRowLeft < Board.getRow(this.piecePosition) && destinationRowLeft + 2 == prevMoveRowLeft) {
                        this.diagonalLeft[x] = this.piecePosition + 9 * coefficientB;
                    }
                }
            }
        }

    }

    public void bishopMovement(final ImageView i) {
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Bishop.this.offSetX = event.getX() - i.getX();
                    Bishop.this.offSetY = event.getY() - i.getY();
                    Bishop.this.originalX = i.getX();
                    Bishop.this.originalY = i.getY();
                    Bishop.this.BishopMoves();
                }

            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(event.getX() - Bishop.this.offSetX);
                i.setY(event.getY() - Bishop.this.offSetY);
            }
        };
        EventHandler<MouseEvent> release = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for(int x = 0; x < Bishop.this.diagonalRight.length; ++x) {
                    if (Tile.getTile(event.getX(), event.getY()) == Bishop.this.diagonalRight[x]) {
                        i.setX(Tile.tiles[Bishop.this.diagonalRight[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Bishop.this.diagonalRight[x]].tileCoordinateY);
                        Bishop.this.piecePosition = Tile.tiles[Bishop.this.diagonalRight[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Bishop.this.diagonalRight[x]) {
                        i.setX(Bishop.this.originalX);
                        i.setY(Bishop.this.originalY);
                    }

                    if (Tile.getTile(event.getX(), event.getY()) == Bishop.this.diagonalLeft[x]) {
                        Bishop.this.piecePosition = Tile.tiles[Bishop.this.diagonalLeft[x]].tileNumber;
                        i.setY(Tile.tiles[Bishop.this.diagonalLeft[x]].tileCoordinateY);
                        i.setX(Tile.tiles[Bishop.this.diagonalLeft[x]].tileCoordinateX);
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Bishop.this.diagonalLeft[x]) {
                        i.setX(Bishop.this.originalX);
                        i.setY(Bishop.this.originalY);
                    }
                }

            }
        };
        i.setOnMouseDragged(drag);
        i.setOnMousePressed(click);
        i.setOnMouseReleased(release);
    }
}