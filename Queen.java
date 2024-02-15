import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Queen extends Piece {
    Group root;
    int[] destinationVertical = new int[7];
    int[] destinationHorizontal = new int[7];
    int[] diagonalRight = new int[7];
    int[] diagonalLeft = new int[7];
    double originalX;
    double originalY;
    private double offSetX;
    private double offSetY;

    Queen(Group root, String pieceColour, int piecePosition) {
        super(pieceColour, piecePosition);
        this.root = root;
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
        Tile.createTiles();
        Image blackKnightImage;
        ImageView blackKnight;
        if (this.pieceColour.equals("white")) {
            blackKnightImage = new Image("images/Chess_qlt60.png");
            blackKnight = new ImageView(blackKnightImage);
            root.getChildren().add(blackKnight);
            blackKnight.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackKnight.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.queenMovement(blackKnight);
        } else if (this.pieceColour.equals("black")) {
            blackKnightImage = new Image("images/Chess_qdt60.png");
            blackKnight = new ImageView(blackKnightImage);
            root.getChildren().add(blackKnight);
            blackKnight.setX(Tile.tiles[this.piecePosition].tileCoordinateX);
            blackKnight.setY(Tile.tiles[this.piecePosition].tileCoordinateY);
            this.queenMovement(blackKnight);
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

    public void queenMovement(final ImageView i) {
        EventHandler<MouseEvent> click = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Queen.this.offSetX = event.getX() - i.getX();
                    Queen.this.offSetY = event.getY() - i.getY();
                    Queen.this.originalX = i.getX();
                    Queen.this.originalY = i.getY();
                    Queen.this.BishopMoves();
                    Queen.this.RookMoves();
                }

            }
        };
        EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                i.setX(event.getX() - Queen.this.offSetX);
                i.setY(event.getY() - Queen.this.offSetY);
            }
        };
        EventHandler<MouseEvent> release = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                for(int x = 0; x < Queen.this.destinationVertical.length; ++x) {
                    if (Tile.getTile(event.getX(), event.getY()) == Queen.this.destinationVertical[x]) {
                        i.setX(Tile.tiles[Queen.this.destinationVertical[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Queen.this.destinationVertical[x]].tileCoordinateY);
                        Queen.this.piecePosition = Tile.tiles[Queen.this.destinationVertical[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Queen.this.destinationVertical[x]) {
                        i.setX(Queen.this.originalX);
                        i.setY(Queen.this.originalY);
                    }

                    if (Tile.getTile(event.getX(), event.getY()) == Queen.this.destinationHorizontal[x]) {
                        i.setX(Tile.tiles[Queen.this.destinationHorizontal[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Queen.this.destinationHorizontal[x]].tileCoordinateY);
                        Queen.this.piecePosition = Tile.tiles[Queen.this.destinationHorizontal[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Queen.this.destinationHorizontal[x]) {
                        i.setX(Queen.this.originalX);
                        i.setY(Queen.this.originalY);
                    }

                    if (Tile.getTile(event.getX(), event.getY()) == Queen.this.diagonalRight[x]) {
                        i.setX(Tile.tiles[Queen.this.diagonalRight[x]].tileCoordinateX);
                        i.setY(Tile.tiles[Queen.this.diagonalRight[x]].tileCoordinateY);
                        Queen.this.piecePosition = Tile.tiles[Queen.this.diagonalRight[x]].tileNumber;
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Queen.this.diagonalRight[x]) {
                        i.setX(Queen.this.originalX);
                        i.setY(Queen.this.originalY);
                    }

                    if (Tile.getTile(event.getX(), event.getY()) == Queen.this.diagonalLeft[x]) {
                        Queen.this.piecePosition = Tile.tiles[Queen.this.diagonalLeft[x]].tileNumber;
                        i.setY(Tile.tiles[Queen.this.diagonalLeft[x]].tileCoordinateY);
                        i.setX(Tile.tiles[Queen.this.diagonalLeft[x]].tileCoordinateX);
                        break;
                    }

                    if (Tile.getTile(event.getX(), event.getY()) != Queen.this.diagonalLeft[x]) {
                        i.setX(Queen.this.originalX);
                        i.setY(Queen.this.originalY);
                    }

                    System.out.println(Queen.this.diagonalLeft[x] + " " + Queen.this.diagonalRight[x]);
                }

                System.out.println();
            }
        };
        i.setOnMouseDragged(drag);
        i.setOnMousePressed(click);
        i.setOnMouseReleased(release);
    }
}
    