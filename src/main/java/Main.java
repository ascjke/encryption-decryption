import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    private String mode = "enc";
    private String key = "0";
    private String data = "";
    private String inputPath = "";
    private String outputPath = "";
    private String fileData = "";
    private String algorithm = "shift";
    private Cipher cipher;

    public static void main(String[] args) {

        Main main = new Main();
        parseProgramArgs(args, main);
        CipherFactory factory = createCipherFactoryByAlgorithm(main.algorithm);
        main.cipher = factory.createCipher();

        File inputFile = new File(main.inputPath);
        File outputFile = new File(main.outputPath);

        /**
         * Read from -in file or from -data
         */
        if (main.inputPath.equals("")) {
            main.fileData = main.data;
        } else {
            try (Scanner scanner = new Scanner(inputFile)) {
                while (scanner.hasNextLine()) {
                    main.fileData += scanner.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error. -in file not found: " + main.inputPath);
                e.printStackTrace();
            }
        }

        /**
         * Write to -out file or print to console
         */
        if (main.outputPath.equals("")) {
            if (main.mode.equals("dec")) {
                System.out.println(main.cipher.decrypt(main.fileData, Integer.parseInt(main.key)));
            } else {
                System.out.println(main.cipher.encrypt(main.fileData, Integer.parseInt(main.key)));
            }
        } else {
            try (PrintWriter printWriter = new PrintWriter(outputFile)) {

                if (!main.data.equals("") && !main.inputPath.equals("")) {
                    main.fileData = main.data;
                }

                if (main.mode.equals("dec")) {
                    printWriter.write(main.cipher.decrypt(main.fileData, Integer.parseInt(main.key)));
                } else {
                    printWriter.write(main.cipher.encrypt(main.fileData, Integer.parseInt(main.key)));
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error. -out file not found: " + main.outputPath);
                e.printStackTrace();
            }
        }
    }

    private static void parseProgramArgs(String[] args, Main main) {

        for (int i = 0; i < args.length - 1; i++) {

            // -mode value
            if ("-mode".equals(args[i])) {
                main.mode = args[i + 1];
                if (!main.mode.equals("dec") && !main.mode.equals("enc")) {
                    System.out.println(main.mode + " is incompatible mode");
                    return;
                }
            }

            // -key value
            if ("-key".equals(args[i])) {
                main.key = args[i + 1];
                char[] nums = main.key.toCharArray();
                for (char ch : nums) {
                    if (!Character.isDigit(ch)) {
                        System.out.println(main.key + " is not digit");
                        return;
                    }
                }
            }

            // -data value
            if ("-data".equals(args[i])) {
                main.data = args[i + 1];
            }

            // -in value
            if ("-in".equals(args[i])) {
                main.inputPath = args[i + 1];
            }

            // -out value
            if ("-out".equals(args[i])) {
                main.outputPath = args[i + 1];
            }

            // -alg value
            if ("-alg".equals(args[i])) {
                main.algorithm = args[i + 1];
            }
        }
    }

    // create the needed CipherFactory
    static CipherFactory createCipherFactoryByAlgorithm(String algorithm) {

        if (algorithm.equals("shift")) {
            return new ShiftCipherFactory();
        } else if (algorithm.equals("unicode")) {
            return new UnicodeCipherFactory();
        } else {
            throw new RuntimeException(algorithm + " is unknown cipher algorithm");
        }
    }
}
