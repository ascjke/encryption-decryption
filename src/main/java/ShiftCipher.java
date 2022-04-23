public class ShiftCipher implements Cipher {

    public String encrypt(String message, int key) {

        char[] sourceMessage = message.toCharArray();
        char[] encryptedMessage = new char[sourceMessage.length];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceMessage.length; i++) {
            int decChar = sourceMessage[i];
            if (Character.isLetter((char) decChar)) {

                // in case of A...Z
                if (decChar > 64 && decChar < 91) {
                    int index = decChar + key;
                    if (index > 90) {
                        index -= 26;
                    }
                    encryptedMessage[i] = (char) index;
                }

                // in case of a...z
                if (decChar > 96 && decChar < 123) {
                    int index = decChar + key;
                    if (index > 122) {
                        index -= 26;
                    }
                    encryptedMessage[i] = (char) index;
                }
            } else {
                encryptedMessage[i] = (char) decChar;
            }
            result.append(encryptedMessage[i]);
        }

        return result.toString();
    }

    public String decrypt(String encryptedMessage, int key) {

        char[] sourceMessage = encryptedMessage.toCharArray();
        char[] decryptedMessage = new char[sourceMessage.length];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceMessage.length; i++) {
            int decChar = sourceMessage[i];
            if (Character.isLetter((char) decChar)) {

                // in case of A...Z
                if (decChar > 64 && decChar < 91) {
                    int index = decChar - key;
                    if (index < 65) {
                        index += 26;
                    }
                    decryptedMessage[i] = (char) index;
                }

                // in case of a...z
                if (decChar > 96 && decChar < 123) {
                    int index = decChar - key;
                    if (index < 97) {
                        index += 26;
                    }
                    decryptedMessage[i] = (char) index;
                }

            } else {
                decryptedMessage[i] = (char) decChar;
            }
            result.append(decryptedMessage[i]);
        }

        return result.toString();
    }
}
