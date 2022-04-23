public class ShiftCipherFactory implements CipherFactory {

    @Override
    public Cipher createCipher() {
        return new ShiftCipher();
    }
}
