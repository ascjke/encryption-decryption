public interface Cipher {

    String encrypt(String message, int key);

    String decrypt(String encryptedMessage, int key);
}
