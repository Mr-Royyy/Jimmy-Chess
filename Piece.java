public class Piece {
    String pieceColour;
    int piecePosition;
    double pieceCoordinateX;
    double pieceCoordinateY;
    static int i = 0;
    static Piece[] p = new Piece[32];
    static int[] piecePositions = new int[32];

    Piece(String pieceColour, int piecePosition) {
        this.pieceColour = pieceColour;
        this.piecePosition = piecePosition;
        piecePositions[i] = this.piecePosition;
        ++i;
        double y = 0.0D;
        double x;
        if (this.piecePosition / 8 >= 1) {
            x = 8.0D * Math.floor((double)(this.piecePosition / 8)) - (double)this.piecePosition;
            y = Math.floor((double)(this.piecePosition / 8));
        } else {
            x = (double)this.piecePosition;
        }

        this.pieceCoordinateX = Math.abs(x * 64.0D);
        this.pieceCoordinateY = y * 64.0D;
    }

    public void Capture(Piece p) {
        for(int i = 0; i < piecePositions.length; ++i) {
            if (p.piecePosition == piecePositions[i]) {
            }
        }

    }
}
