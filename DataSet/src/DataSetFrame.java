import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javax.imageio.ImageIO;
import javax.swing.*;

// -- | / \ * //
public class DataSetFrame extends JFrame {
    private JButton btnVertical = new JButton("|");
    private JButton btnHorizontal = new JButton("--");
    private JButton btnDiagRight = new JButton("/");
    private JButton btnDiagLeft = new JButton("\\");
    private JButton btnEmpty = new JButton("*");
    private JButton btnSkip = new JButton("Пропустить");
    private JButton btnBack = new JButton("Назад");

    int x = 0;
    int y = 56;
    final int step = 5;
    final int scale = 40;
    int[] currentPixels = new int[step * step * 3];
    BufferedImage srcImage;
    BufferedImage destImage;
    Path path;

    public DataSetFrame() {
        super("Data Set Generator");
        this.setBounds(100, 100, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        File img = new File("C:\\data\\images\\data1.jpg");
        path = Paths.get("C:\\data\\data.txt");
        try {
            srcImage = ImageIO.read(img);
        } catch (Exception e) {
        }
        destImage = new BufferedImage(step * scale, step * scale, BufferedImage.TYPE_INT_RGB);
        showPartOfImage();
        Container container = this.getContentPane();
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(destImage));
        container.setLayout(new GridBagLayout());
        container.add(label);

        btnSkip.addActionListener((e) -> {
            showNextPicture(label);
        });
        btnBack.addActionListener((e)->{

        });
        btnDiagLeft.addActionListener((e) -> {
            writeData("\n0 0 0 1 0\n");
            generateDataLeft();
            showNextPicture(label);
        });
        btnDiagRight.addActionListener((e) -> {
            writeData("\n0 0 1 0 0\n");
            generateDataRight();
            showNextPicture(label);
        });
        btnHorizontal.addActionListener((e) -> {
            writeData("\n1 0 0 0 0\n");
            generateDataHorizontal();
            showNextPicture(label);
        });
        btnVertical.addActionListener((e) -> {
            writeData("\n0 1 0 0 0\n");
            generateDataVertical();
            showNextPicture(label);
        });
        btnEmpty.addActionListener((e) -> {
            writeData("\n0 0 0 0 1\n");
            generateDataEmpty();
            showNextPicture(label);
        });

        container.add(btnDiagLeft);
        container.add(btnHorizontal);
        container.add(btnVertical);
        container.add(btnDiagRight);
        container.add(btnEmpty);
        container.add(btnSkip);

    }
    private void generateDataLeft(){
        BufferedImage currentImg = fillImage();
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = mirrorVertical(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");

    }
    private void generateDataRight(){
        BufferedImage currentImg = fillImage();
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = mirrorVertical(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 1 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 1 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 1 0\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 1 0\n");

    }
    private void generateDataHorizontal(){
        BufferedImage currentImg = fillImage();
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = mirrorVertical(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");

    }
    private void generateDataVertical(){
        BufferedImage currentImg = fillImage();
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = mirrorVertical(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n1 0 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n1 0 0 0 0\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 1 0 0 0\n");
        inverseColor(currentImg);
        writeData("\n0 1 0 0 0\n");

    }
    private void generateDataEmpty(){
        BufferedImage currentImg = fillImage();
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = mirrorVertical(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");
        currentImg = rotateLeft(currentImg);
        writeData("\n0 0 0 0 1\n");
        inverseColor(currentImg);
        writeData("\n0 0 0 0 1\n");

    }
    private BufferedImage mirrorVertical(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, img.getType());
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++){
                newImage.setRGB(width-1-i,j,img.getRGB(i,j));
            }
        fillCurrentPixels(newImage);
        return newImage;
    }
    private void inverseColor(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++){
                Color clr = new Color(img.getRGB(i,j));
                img.setRGB(i,j,new Color(255-clr.getRed(), 255-clr.getGreen(), 255-clr.getBlue()).getRGB());
            }
        fillCurrentPixels(img);
    }
    private BufferedImage rotateLeft(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, img.getType());

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                newImage.setRGB(height - 1 - j, i, img.getRGB(i, j));
        fillCurrentPixels(newImage);
        return newImage;
    }
    private  void fillCurrentPixels(BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        int k = 0;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++){
                Color clr = new Color(img.getRGB(i,j));
                currentPixels[k++] = clr.getRed();
                currentPixels[k++] = clr.getGreen();
                currentPixels[k++] = clr.getBlue();
            }

    }
    private BufferedImage fillImage() {
        BufferedImage newImage = new BufferedImage(step, step, BufferedImage.TYPE_INT_RGB);
        int k = 0;
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                newImage.setRGB(i, j,
                        new Color(currentPixels[k++], currentPixels[k++], currentPixels[k++]).getRGB());
            }
        }
        return newImage;
    }

    private void writeData(String output) {
        try {
            for (int i = 0; i < currentPixels.length; i++) {
                Files.write(path,
                        ((double) currentPixels[i] / 255 + " ").getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(path,
                    output.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }

    private void showNextPicture(JLabel label) {
        if (x + step > srcImage.getWidth()) {
            if (y + step > srcImage.getHeight()) {
                return;
            } else {
                y += 1;
                x = 0;
            }
        } else {
            x += 1;
        }
        showPartOfImage();
        label.repaint();
        System.out.println(x + " " + y);
    }

    private void showPartOfImage() {
        int k = 0;
        for (int i = x; i < x + step; i++) {
            for (int j = y; j < y + step; j++) {
                Color clr = new Color(srcImage.getRGB(i, j));
                currentPixels[k++] = clr.getRed();
                currentPixels[k++] = clr.getGreen();
                currentPixels[k++] = clr.getBlue();
                for (int destI = (i - x) * scale; destI < (i - x + 1) * scale; destI++) {
                    for (int destJ = (j - y) * scale; destJ < (j - y + 1) * scale; destJ++) {
                        destImage.setRGB(destI, destJ, srcImage.getRGB(i, j));
                    }
                }
            }
        }

    }
}
