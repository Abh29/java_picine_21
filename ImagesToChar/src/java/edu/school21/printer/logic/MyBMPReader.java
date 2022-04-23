package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyBMPReader {

    private String filePath;
    private static BufferedImage bufferedImage;
    private boolean[][] imageBytes;
    private int height;
    private int width;


    public static MyBMPReader read(String fileName){
        try {
            MyBMPReader mp = new MyBMPReader(fileName);
            mp.readSize();
            mp.loadImageBytes();
            return mp;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    private MyBMPReader(String filePath) throws IOException{
        this.filePath = filePath;
        bufferedImage = ImageIO.read(new File(filePath));
    }

    private void readSize(){
        height = bufferedImage.getHeight();
        width = bufferedImage.getWidth();
    }

    private void loadImageBytes(){
        imageBytes = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int rgb = bufferedImage.getRGB(j, i) & 0x00FFFFFF;
                imageBytes[i][j] = rgb != 0;
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean[][] getImageBytes() {
        return imageBytes;
    }
}
