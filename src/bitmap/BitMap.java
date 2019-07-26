package bitmap;

/**
 * @author hejianglong
 * @date 2019/7/26
 */
public class BitMap {
    private char[] bytes;

    private int nbits;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits/16 + 1];
    }

    public void set(int k) {
        if (k > nbits) return;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        int r = (1 << bitIndex);
        bytes[byteIndex] |= r;
    }

    public boolean get(int k) {
        if (k > nbits) {
            return false;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        int r = (bytes[byteIndex] & (1 << bitIndex));
        return r != 0;
    }

    public static void main(String[] args) {
        BitMap map = new BitMap(100000000);
        System.out.println("数组长度:" + map.bytes.length);
        map.set(10);
        map.set(14);

        System.out.println(map.get(10));
    }
}
