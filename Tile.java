public class Tile {
    int tileNumber;
    static Tile[] tiles = new Tile[64];
    double tileCoordinateX;
    double tileCoordinateY;

    public Tile(int number) {
        double y = 0.0D;
        this.tileNumber = number;
        double x;
        if (number / 8 >= 1) {
            x = 8.0D * Math.floor((double)(number / 8)) - (double)number;
            y = Math.floor((double)(number / 8));
        } else {
            x = (double)number;
        }

        this.tileCoordinateX = Math.abs(x * 64.0D);
        this.tileCoordinateY = y * 64.0D;
    }

    public static void createTiles() {
        for(int i = 0; i < 64; ++i) {
            tiles[i] = new Tile(i);
        }

    }

    public static boolean isTileEmpty(int tilePosition) {
        for(int i = 0; i < 32; ++i) {
            if (Piece.p[i].piecePosition == tilePosition) {
                return false;
            }
        }

        return true;
    }

    public static int getTile(double x, double y) {
        for(int t = 0; t < tiles.length; ++t) {
            if (x >= tiles[t].tileCoordinateX && x <= tiles[t].tileCoordinateX + 64.0D && y >= tiles[t].tileCoordinateY && y <= tiles[t].tileCoordinateY + 64.0D) {
                return tiles[t].tileNumber;
            }
        }

        return -1;
    }
}
    