import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Entry {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\data\\data.txt");
        Scanner sc = new Scanner(file);
        double[] input = new double[75];
        double[] output = new double[5];
        double[] count = new double[]{0,0,0,0,0};

        while (sc.hasNextLine()) {
            String[] inputStr = sc.nextLine().split(" ");
            if (!sc.hasNextLine()) break;
            String[] outputStr = sc.nextLine().split(" ");
            for (int i = 0; i < output.length; i++) {
                output[i] = Double.parseDouble(outputStr[i]);
                if(Math.abs(output[i]-1.0) < 0.1) count[i]++;
            }

        }
        for (int i = 0; i < count.length; i++){
            System.out.println(count[i]);
        }
        DataSetFrame dsFrame = new DataSetFrame();
        dsFrame.setVisible(true);
    }
}
