package edu.school21.printer.logic;

public class MyBMPPrinter {
    private char blackPixel;
    private char whitePixel;
    private MyBMPReader img;

    MyBMPPrinter(MyBMPReader img){
        blackPixel = ' ';
        whitePixel = '#';
        this.img = img;
    }

    public MyBMPPrinter(MyBMPReader img, char blackPixel, char whitePixel) {
        this(img);
        this.blackPixel = blackPixel;
        this.whitePixel = whitePixel;
    }

    public void setBlackPixel(char blackPixel) {
        this.blackPixel = blackPixel;
    }

    public void setWhitePixel(char whitePixel) {
        this.whitePixel = whitePixel;
    }

    @Override
    public String toString() {
        boolean[][] data = img.getImageBytes();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                char c = data[i][j] ? blackPixel : whitePixel;
                builder.append(c);
            }
            builder.append('\n');
        }
        return builder.toString();
    }

}
