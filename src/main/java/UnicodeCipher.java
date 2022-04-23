public class UnicodeCipher implements Cipher {

    @Override
    public String encrypt(String message, int key) {

        char[] sourceMessage = message.toCharArray();
        char[] encryptedMessage = new char[sourceMessage.length];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceMessage.length; i++) {
            int decChar = sourceMessage[i];
            int index = decChar + key;
            if (index > 126) {
                index -= 94;
            }
            encryptedMessage[i] = (char) index;
            result.append(encryptedMessage[i]);
        }

        return result.toString();
    }

    @Override
    public String decrypt(String encryptedMessage, int key) {

        char[] sourceMessage = encryptedMessage.toCharArray();
        char[] decryptedMessage = new char[sourceMessage.length];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < sourceMessage.length; i++) {
            int decChar = sourceMessage[i];
            int index = decChar - key;
            if (index < 32) {
                index += 94;
            }
            decryptedMessage[i] = (char) index;
            result.append(decryptedMessage[i]);
        }

        return result.toString();
    }
}
