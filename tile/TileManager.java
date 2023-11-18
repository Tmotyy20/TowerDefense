package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TileManager {

    public Tile STONE, ROAD, GRASS, GRASSUP, GRASSRIGHT, GRASSLEFT, GRASSDOWN, GRASSTOPLEFT, GRASSTOPRIGHT,
            GRASSBOTTOMLEFT, GRASSBOTTOMRIGHT, WATER, TREE, BUSH, FLOWER, CASTLE1, CASTLE2, CASTLE3, CASTLE4, WOOD; // Declare
                                                                                                              // tile
                                                                                                              // types
                                                                                                              // as Tile
                                                                                                              // objects
    private BufferedImage map; // BufferedImage to store the sprite map
    public ArrayList<Tile> tiles = new ArrayList<>(); // Create Tiles list
    public int tileSize = 64;

    public TileManager() {

        loadMap();
        createTiles();

    }

    // Method to create and add tile objects to the 'tiles' list
    private void createTiles() {

        // Creating Tile objects and adding them to 'tiles' list
        // Random Tiles
        tiles.add(BUSH = new Tile(getSprite(1, 0), false)); // 0
        tiles.add(TREE = new Tile(getSprite(0, 0), false)); // 1
        tiles.add(FLOWER = new Tile(getSprite(3, 6), false)); // 2
        tiles.add(WOOD = new Tile(getSprite(9, 0), false)); // 3
        ////////////////////////////////////////////////////////////////////////////
        tiles.add(WOOD = new Tile(getSprite(0, 6), false)); // 4
        tiles.add(WOOD = new Tile(getSprite(1, 6), false));  // 5
        tiles.add(WOOD = new Tile(getSprite(2, 6), false)); // 6
        
        ////////////////////////////////////////////////////////////////////////////
        tiles.add(ROAD = new Tile(getSprite(8, 0), false)); // 7
        tiles.add(GRASSTOPLEFT = new Tile(getSprite(2, 3), true)); // 8
        tiles.add(GRASSTOPRIGHT = new Tile(getSprite(1, 3), true)); // 9
        tiles.add(GRASSBOTTOMLEFT = new Tile(getSprite(4, 0), true)); // 10
        tiles.add(GRASSBOTTOMRIGHT = new Tile(getSprite(0, 3), true)); // 11
        tiles.add(GRASSBOTTOMRIGHT = new Tile(getSprite(0, 3), true)); // 12
        tiles.add(GRASSRIGHT = new Tile(getSprite(1, 4), true)); // 13
        tiles.add(GRASSLEFT = new Tile(getSprite(0, 4), true)); // 14
        tiles.add(GRASS = new Tile(getSprite(2, 4), true)); // 15
        tiles.add(GRASSDOWN = new Tile(getSprite(3, 0), true)); // 16
        tiles.add(GRASSUP = new Tile(getSprite(2, 0), true)); // 17
        tiles.add(CASTLE1 = new Tile(getSprite(8, 5), false)); // 18
        tiles.add(CASTLE2 = new Tile(getSprite(9, 5), false)); // 19
        tiles.add(CASTLE3 = new Tile(getSprite(9, 6), false)); // 20
        tiles.add(CASTLE4 = new Tile(getSprite(8, 6), false)); // 21
        tiles.add(WATER = new Tile(getSprite(4, 15), false));  // 22
    }

    public BufferedImage getWaterSprite(int frame) {
        // Adjust the coordinates based on your sprite sheet layout
        return getSprite(0 + frame, 15);
    }

    // Load and return the sprite map as a BufferedImage
    public static BufferedImage getSpriteMap() {

        BufferedImage img = null;
        InputStream is = TileManager.class.getClassLoader().getResourceAsStream("res/spritesheet.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    private void loadMap() {

        map = getSpriteMap();

    }

    // Method to get a specific Tile by its ID
    public BufferedImage getSprite(int id) {
        return tiles.get(id).getSprite();
    }

    // Method to get a sprite from the sprite map based on x, y coordinates
    public BufferedImage getSprite(int x, int y) {
        // Return a sub-image of the sprite based on the specified coordinates and
        // tileSize
        return map.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize);
    }
}
