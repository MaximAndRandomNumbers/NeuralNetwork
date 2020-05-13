import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Entry {
    public static void main(String[] args) throws IOException {
        Net net = new Net(new int[]{75, 30,  5});
        File file = new File("C:\\data\\data.txt");
        Scanner sc = new Scanner(file);
        double[] input = new double[75];
        double[] output = new double[5];
        int counter = 0;
        while (sc.hasNextLine()) {
            String[] inputStr = sc.nextLine().split(" ");
            for (int i = 0; i < input.length; i++) {
                input[i] = Double.parseDouble(inputStr[i]);
            }
            if (!sc.hasNextLine()) break;
            String[] outputStr = sc.nextLine().split(" ");
            for (int i = 0; i < output.length; i++) {
                output[i] = Double.parseDouble(outputStr[i]);
            }
            counter++;
//            System.out.println("Input:");
//            for (int i = 0; i < input.length; i++) {
//                System.out.print(input[i] + " ");
//            }
//            System.out.println("Output:");
//            for (int i = 0; i < output.length; i++) {
//                System.out.print(output[i] + " ");
//            }
            net.startLearning(input, output);

        }

        System.out.println(counter);
        drawScratchedPicture(net);
    }

    static void drawScratchedPicture(Net net) throws IOException {
        File file = new File("C:\\imageTest\\test.jpg");
        BufferedImage srcImg = ImageIO.read(file);
        BufferedImage destImg = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < destImg.getWidth(); i++) {
            for (int j = 0; j < destImg.getHeight(); j++) {
                destImg.setRGB(i, j, new Color(255, 255, 255).getRGB());
            }
        }
        int step = 5;
        int x = 0, y = 0;
        double[] input = new double[step * step * 3];
        double[] output = new double[5];

        while (y + 2 * step < srcImg.getHeight()) {
            int k = 0;
            if (x + step > srcImg.getWidth()) {
                x = 0;
                y += 1;
                for (int i = x; i < x + step; i++) {
                    for (int j = y; j < y + step; j++) {
                        Color clr = new Color(srcImg.getRGB(i, j));
                        input[k++] = (double) clr.getRed() / 255;
                        input[k++] = (double) clr.getGreen() / 255;
                        input[k++] = (double) clr.getBlue() / 255;
                    }
                }
                double[] result = net.getResult(input);
//                System.out.println("Input:");
//                for (int i = 0; i < input.length; i++){
//                    System.out.print(input[i] + " ");
//                }
//                System.out.println("Output:");
//                for (int i = 0; i < output.length; i++){
//                    System.out.print(output[i] + " ");
//                }

                drawRect(destImg, x, y, step, result);
            } else {
                for (int i = x; i < x + step; i++) {
                    for (int j = y; j < y + step; j++) {
                        Color clr = new Color(srcImg.getRGB(i, j));
                        input[k++] = (double) clr.getRed() / 255;
                        input[k++] = (double) clr.getGreen() / 255;
                        input[k++] = (double) clr.getBlue() / 255;
                    }
                }
                double[] result = net.getResult(input);
//                System.out.println("Input:");
//                for (int i = 0; i < input.length; i++){
//                    System.out.print(input[i] + " ");
//                }
//                System.out.println("Output:");
//                for (int i = 0; i < output.length; i++){
//                    System.out.print(output[i] + " ");
//                }

                drawRect(destImg, x, y, step, result);

                x += 1;
            }

        }
        File outputfile = new File("C:\\imageTest\\result.jpg");
        ImageIO.write(destImg, "jpg", outputfile);
    }

    enum DrawThings {
        HORIZONTAL,
        VERTICAL,
        RIGHT_DIAG,
        LEFT_DIAG,
        EMPTY
    }

    static void drawRect(BufferedImage dest, int x, int y, int step, double[] thingToDraw) {
        double max = thingToDraw[0];
        int indexMax = 0;
        for (int i = 1; i < thingToDraw.length; i++) {
            if (thingToDraw[i] > max) {
                max = thingToDraw[i];
                indexMax = i;
            }
        }
        switch (DrawThings.values()[indexMax]) {
            case EMPTY:
                drawEmpty(dest, x, y, step);
                //System.out.println("Пусто на координатах" + x + " " + " " + y);
                break;
            case VERTICAL:
                drawVertical(dest, x, y, step);
                //System.out.println("Вертикальная на координатах" + x + " " + " " + y);
                break;
            case HORIZONTAL:
                drawHorizontal(dest, x, y, step);
                //System.out.println("Горизонтальная на координатах" + x + " " + " " + y);
                break;
            case RIGHT_DIAG:
                drawRightDiag(dest, x, y, step);
                //System.out.println("Правая на координатах" + x + " " + " " + y);
                break;
            case LEFT_DIAG:
                drawLeftDiag(dest, x, y, step);
                //System.out.println("Левая на координатах" + x + " " + " " + y);
                break;
        }

    }

    static void drawEmpty(BufferedImage dest, int x, int y, int step) {
        for (int i = x; i < x + step; i++) {
            for (int j = y; j < y + step; j++) {
                dest.setRGB(i, j, new Color(255, 255, 255).getRGB());
            }
        }
    }

    static void drawVertical(BufferedImage dest, int x, int y, int step) {
        int middleX = x + step / 2 + 1;
        for (int i = y + 1; i < y + step - 1; i++) {
            dest.setRGB(middleX, i, new Color(0, 0, 0).getRGB());
        }
    }

    static void drawHorizontal(BufferedImage dest, int x, int y, int step) {
        int middleY = y + step / 2 + 1;
        for (int i = x + 1; i < x + step - 1; i++) {
            dest.setRGB(i, middleY, new Color(0, 0, 0).getRGB());
        }
    }

    static void drawRightDiag(BufferedImage dest, int x, int y, int step) {
        for (int i = 1; i < step - 1; i++) {
            dest.setRGB(x + i, y + step - i, new Color(0, 0, 0).getRGB());
        }
    }

    static void drawLeftDiag(BufferedImage dest, int x, int y, int step) {
        for (int i = 1; i < step - 1; i++) {
            dest.setRGB(x + i, y + i, new Color(0, 0, 0).getRGB());
        }
    }
}
