public class UnicodeCipherFactory implements CipherFactory {

    @Override
    public Cipher createCipher() {
        return new UnicodeCipher();
    }
}
