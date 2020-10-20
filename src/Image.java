import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public class Image {

    public static void main(String[] args) throws IOException {
        int width;
        int height;
        int targetwidth;
        int targetheight;
        String path;
        String targetPath;

        if (args.length < 4) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        targetwidth = Integer.parseInt(args[0]);
        targetheight = Integer.parseInt(args[1]);
        path = args[2];
        targetPath = args[3];

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = img.getWidth();
        height = img.getHeight();
        int xIndex = width / targetwidth;
        int yIndex = height / targetheight;

        for (int i = 0; i < xIndex; i++) {
            for (int j = 0; j < yIndex; j++) {
                BufferedImage temp = new BufferedImage(targetwidth, targetheight, BufferedImage.TYPE_INT_ARGB);
                for (int k = 0; k < targetwidth; k++) {
                    for (int l = 0; l < targetheight; l++) {
                        temp.setRGB(k, l, img.getRGB(targetwidth * i + k, targetheight * j + l));
                    }
                }
                File tempfile = new File(targetPath + (i + j * yIndex) + ".png");
                ImageIO.write(temp, "png", tempfile);
            }
        }
    }

}
