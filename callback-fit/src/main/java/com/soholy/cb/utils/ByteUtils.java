package com.soholy.cb.utils;


public class ByteUtils {
    private static char[] hex_chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static float byte4ToFloat(byte[] arr, int offset) {
        int i = byte4ToIntBig(arr, offset);
        return Float.intBitsToFloat(i);
    }


    public static byte[] floatToByte4(float f) {
        int i = Float.floatToIntBits(f);
        return intToByte4Big(i);
    }


    public static double byte8ToDouble(byte[] arr, int offset) {
        if ((arr == null) || (arr.length != 8)) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是8位!");
        }
        return Double.longBitsToDouble(byte8ToLong(arr, offset));
    }


    public static byte[] doubleToByte8(double i) {
        long j = Double.doubleToLongBits(i);
        return longToByte8(j);
    }


    public static byte[] charToByte2(char c) {
        byte[] arr = new byte[2];
        arr[0] = ((byte) (c >> '\b'));
        arr[1] = ((byte) (c & 0xFF));
        return arr;
    }


    public static char byte2ToChar(byte[] arr) {
        if ((arr == null) || (arr.length != 2)) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是2位!");
        }
        return (char) ((char) (arr[0] << 8) | (char) arr[1]);
    }


    public static byte[] integerToByteIs4bit(String value) {
        byte[] bytes = new byte[value.length() / 2];
        integerToByteIs4bit(value, bytes, 0);
        return bytes;
    }


    public static byte[] integerToByteIs4bit(String value, byte[] bytes, int offset) {
        String valStr = String.valueOf(value);
        if (valStr.length() % 2 != 0) {
            valStr = valStr + "0";
            byte[] newByte = new byte[bytes.length + 1];
            copyArrays(bytes, 0, bytes.length, newByte, 0);
        }

        char[] chars = valStr.toCharArray();

        for (int i = 0; i < chars.length; i += 2) {
            int temp = Integer.parseInt(String.valueOf(chars[i]) + String.valueOf(chars[(i + 1)]));
            intTobyte1(temp, bytes, offset++);
        }
        return bytes;
    }


    public static String byte2ToIntegerStr(byte[] bytes, int offset, int len) {
        StringBuffer sb = new StringBuffer();
        for (; ; ) {
            len--;
            if (len < 0) break;
            byte[] temp = {0, bytes[(offset++)]};
            short s = byte2ToShort(temp, 0);
            if (String.valueOf(s).length() % 2 != 0)
                sb.append("0");
            sb.append(s);
        }
        return sb.toString();
    }


    public static byte[] shortToByte2(Short s) {
        byte[] arr = new byte[2];
        arr[0] = ((byte) (s.shortValue() >> 8));
        arr[1] = ((byte) (s.shortValue() & 0xFF));
        return arr;
    }


    public static short byte2ToShort(byte[] arr, int offset) {
        if ((arr != null) && (offset > arr.length)) {
            throw new IllegalArgumentException("参数有误!");
        }
        return (short) ((short) arr[offset] << 8 | (short) arr[(++offset)] & 0xFF);
    }


    public static byte[] shortToByte16(short s) {
        byte[] arr = new byte[16];
        for (int i = 15; i >= 0; i--) {
            arr[i] = ((byte) (s & 0x1));
            s = (short) (s >> 1);
        }
        return arr;
    }


    public static short byte16ToShort(byte[] arr) {
        if ((arr == null) || (arr.length != 16)) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度为16!");
        }
        short sum = 0;
        for (int i = 0; i < 16; i++) {
            sum = (short) (sum | arr[i] << 15 - i);
        }
        return sum;
    }


    public static byte[] intToByte4Big(int value) {
        byte[] src = new byte[4];
        src[0] = ((byte) (value >> 24 & 0xFF));
        src[1] = ((byte) (value >> 16 & 0xFF));
        src[2] = ((byte) (value >> 8 & 0xFF));
        src[3] = ((byte) (value & 0xFF));
        return src;
    }


    public static byte[] intToByte4Little(int value) {
        byte[] src = new byte[4];
        src[3] = ((byte) (value >> 24 & 0xFF));
        src[2] = ((byte) (value >> 16 & 0xFF));
        src[1] = ((byte) (value >> 8 & 0xFF));
        src[0] = ((byte) (value & 0xFF));
        return src;
    }


    public static int byte4ToIntBig(byte[] src, int offset) {
        return (src[(offset++)] & 0xFF) << 24 | (src[(offset++)] & 0xFF) << 16 | (src[(offset++)] & 0xFF) << 8 | src[offset] & 0xFF;
    }


    public static int byte4ToIntLittle(byte[] src, int offset) {
        return src[(offset++)] & 0xFF | (src[(offset++)] & 0xFF) << 8 | (src[(offset++)] & 0xFF) << 16 | (src[offset] & 0xFF) << 24;
    }


    public static long byte8ToLong(byte[] arr, int offset) {
        return (arr[(offset++)] & 0xFF) << 56 | (arr[(offset++)] & 0xFF) << 48 | (arr[(offset++)] & 0xFF) << 40 | (arr[(offset++)] & 0xFF) << 32 | (arr[(offset++)] & 0xFF) << 24 | (arr[(offset++)] & 0xFF) << 16 | (arr[(offset++)] & 0xFF) << 8 | arr[offset] & 0xFF;
    }


    public static byte[] longToByte8(long sum) {
        byte[] arr = new byte[8];
        arr[0] = ((byte) (int) (sum >> 56));
        arr[1] = ((byte) (int) (sum >> 48));
        arr[2] = ((byte) (int) (sum >> 40));
        arr[3] = ((byte) (int) (sum >> 32));
        arr[4] = ((byte) (int) (sum >> 24));
        arr[5] = ((byte) (int) (sum >> 16));
        arr[6] = ((byte) (int) (sum >> 8));
        arr[7] = ((byte) (int) (sum & 0xFF));
        return arr;
    }


    public static byte[] intToByte32(int num) {
        byte[] arr = new byte[32];
        for (int i = 31; i >= 0; i--) {
            arr[i] = ((byte) (num & 0x1));

            num >>= 1;
        }
        return arr;
    }


    public static int byte32ToInt(byte[] arr) {
        if ((arr == null) || (arr.length != 32)) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是32!");
        }
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum |= arr[i] << 31 - i;
        }
        return sum;
    }


    public static long byte64ToLong(byte[] arr) {
        if ((arr == null) || (arr.length != 64)) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是64!");
        }
        long sum = 0L;
        for (int i = 0; i < 64; i++) {
            sum |= arr[i] << 63 - i;
        }
        return sum;
    }


    public static byte[] longToByte64(long sum) {
        byte[] arr = new byte[64];
        for (int i = 63; i >= 0; i--) {
            arr[i] = ((byte) (int) (sum & 1L));
            sum >>= 1;
        }
        return arr;
    }


    public static void fillWithNUL(byte[] src, byte value) {
        for (int i = 0; i < src.length; i++) {
            src[i] = value;
        }
    }


    public static byte[] reverseBytes(byte[] b) {
        byte[] b1 = new byte[b.length];
        int index = 0;
        for (int i = b.length - 1; i >= 0; i--) {
            b1[index] = b[i];
            index++;
        }
        return b1;
    }


    public static boolean bytesEquals(byte[] array1, int array1off, byte[] array2, int array2off, int len) {
        for (int i = 0; i < len; i++)
            if (array1[(array1off + i)] != array2[(array2off + i)])
                return false;
        return true;
    }


    public static int byte1Toint(byte[] b, int off) {
        return 0xFF & b[off];
    }


    public static void intTobyte1(int n, byte[] b, int off) {
        b[off] = 0;
        int
                tmp6_5 = off;
        byte[] tmp6_4 = b;
        tmp6_4[tmp6_5] = ((byte) (tmp6_4[tmp6_5] | n));
    }


    public static int byte2Toint(byte[] b, int off) {
        return byte2ToShort(b, off);
    }


    public static void intTobyte2(int value, byte[] bytes, int offset)
            throws Exception {
        bytes[(offset++)] = ((byte) (value >> 8 & 0xFF));
        bytes[offset] = ((byte) (value & 0xFF));
    }


    public static int byte3Toint(byte[] b, int off) {
        int n = 0;
        n |= (0xFF & b[off]) << 16;
        n |= (0xFF & b[(off + 1)]) << 8;
        n |= 0xFF & b[(off + 2)];
        return n;
    }


    public static void intTobyte3(int n, byte[] b, int off)
            throws Exception {
        if ((n < 0) || (n > 16777215))
            throw new Exception(n + " exceed unsigned short int range [0,16777215]!");
        b[off] = 0;
        b[(off + 1)] = 0;
        b[(off + 2)] = 0;
        int
                tmp55_54 = off;
        byte[] tmp55_53 = b;
        tmp55_53[tmp55_54] = ((byte) (tmp55_53[tmp55_54] | n >> 16));
        int
                tmp68_67 = (off + 1);
        byte[] tmp68_64 = b;
        tmp68_64[tmp68_67] = ((byte) (tmp68_64[tmp68_67] | n >> 8));
        int
                tmp81_80 = (off + 2);
        byte[] tmp81_77 = b;
        tmp81_77[tmp81_80] = ((byte) (tmp81_77[tmp81_80] | n));
    }


    public static long byte4Tolong(byte[] b, int off) {
        long n = 0L;
        n |= (0xFF & b[off]) << 24;
        n |= (0xFF & b[(off + 1)]) << 16;
        n |= (0xFF & b[(off + 2)]) << 8;
        n |= 0xFF & b[(off + 3)];
        return n;
    }


    public static void longTobyte4(long n, byte[] b, int off)
            throws Exception {
        if ((n < 0L) || (n > 4294967295L))
            throw new Exception(n + " exceed unsigned long int range [0,4294967295]!");
        b[off] = 0;
        b[(off + 1)] = 0;
        b[(off + 2)] = 0;
        b[(off + 3)] = 0;
        int
                tmp65_64 = off;
        byte[] tmp65_63 = b;
        tmp65_63[tmp65_64] = ((byte) (int) (tmp65_63[tmp65_64] | n >> 24));
        int
                tmp80_79 = (tmp65_64 + 1);
        byte[] tmp80_76 = b;
        tmp80_76[tmp80_79] = ((byte) (int) (tmp80_76[tmp80_79] | n >> 16));
        int
                tmp95_94 = (tmp65_64 + 2);
        byte[] tmp95_91 = b;
        tmp95_91[tmp95_94] = ((byte) (int) (tmp95_91[tmp95_94] | n >> 8));
        int
                tmp110_109 = (tmp65_64 + 3);
        byte[] tmp110_106 = b;
        tmp110_106[tmp110_109] = ((byte) (int) (tmp110_106[tmp110_109] | n));
    }


    public static int byte4ToInt(byte[] b, int offset) {
        int mask = 255;
        int temp = 0;
        int res = 0;
        for (int i = offset; i < 4; i++) {
            res <<= 8;
            temp = b[i] & mask;
            res |= temp;
        }
        return res;
    }


    public static byte[] int2byte(int value) {
        int mask = 255;
        byte[] b = new byte[4];
        b[3] = ((byte) (value & mask));
        b[2] = ((byte) (value >> 8 & mask));
        b[1] = ((byte) (value >> 16 & mask));
        b[0] = ((byte) (value >> 24 & mask));
        return b;
    }

    public static short byte2short(byte[] b) {
        if ((b == null) || (b.length != 2))
            return -1;
        int mask = 255;
        int temp = 0;
        short res = 0;
        for (int i = 0; i < 2; i++) {
            res = (short) (res << 8);
            temp = b[i] & mask;
            res = (short) (res | temp);
        }
        return res;
    }


    public static byte[] shortTobyte2(short n) {
        byte[] b = new byte[2];
        b[1] = ((byte) (n & 0xFF));
        b[0] = ((byte) (n >> 8 & 0xFF));
        return b;
    }


    public static byte[] copyArrays(byte[] byte_array, int off, int len) {
        byte[] result = new byte[len];
        System.arraycopy(byte_array, off, result, 0, len);
        return result;
    }


    public static int[] copyArrays(byte[] src, int srcOffset, int len, byte[] toArr, int toOffset) {
        while (len-- > 0) {
            toArr[(toOffset++)] = src[(srcOffset++)];
        }
        int[] result = {--srcOffset, --toOffset};
        return result;
    }


    public static String byteTohex(byte[] byte_array, int off, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = off; i < off + len; i++) {
            byte temp = byte_array[i];
            sb.append(hex_chars[(temp >>> 4 & 0xF)]);
            sb.append(hex_chars[(temp & 0xF)]);
        }
        return sb.toString();
    }


    public static String byteTohex(byte[] byte_array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byte_array.length; i++) {
            byte temp = byte_array[i];
            sb.append(hex_chars[(temp >>> 4 & 0xF)]);
            sb.append(hex_chars[(temp & 0xF)]);
        }
        return sb.toString();
    }


    public static String byteTohexWithBlank(byte[] byte_array) {
        if (byte_array == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byte_array.length; i++) {
            byte temp = byte_array[i];
            sb.append(hex_chars[(temp >>> 4 & 0xF)]);
            sb.append(hex_chars[(temp & 0xF)]);
            sb.append(" ");
        }
        return sb.toString();
    }


    public static byte[] hexTobyte(String hexString)
            throws Exception {
        byte[] hexStrBytes = hexString.getBytes();

        if (hexStrBytes.length % 2 != 0) {
            throw new IllegalArgumentException("[" + hexString + "] lengh is even");
        }
        byte[] bytes = new byte[hexStrBytes.length / 2];
        for (int i = 0; i < hexStrBytes.length; i += 2) {
            String item = new String(hexStrBytes, i, 2);
            intTobyte1(Integer.parseInt(item, 16), bytes, i / 2);
        }
        return bytes;
    }


    public static byte[] hex2byteWithBlank(String hexStringWithBlank)
            throws Exception {
        hexStringWithBlank = hexStringWithBlank.replaceAll(" ", "");
        return hexTobyte(hexStringWithBlank);
    }

    public static String revertStringFromBytes(byte[] ba) {
        int index = 0;
        for (int i = 0; i < ba.length; i++) {
            if (ba[i] == 0) {
                index = i;
                break;
            }
        }
        return new String(ba, 0, index);
    }


    public static String[] strToBinaryBytes(String src)
            throws Exception {
        byte[] bytes = src.getBytes("utf-8");

        String[] hexSStrs = new String[bytes.length];
        String[] binaryStrs = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            byte temp = bytes[i];
            String hexSStr = Integer.toHexString(temp);
            hexSStrs[i] = ("0x" + hexSStr);
            String binaryStr = Integer.toBinaryString(temp);
            binaryStrs[i] = binaryStr;
            if (binaryStr.length() < 8) {
                String t = "";
                for (int j = 0; j < 8 - binaryStr.length(); j++) {
                    t = t + 0;
                }
                binaryStrs[i] = (t + binaryStrs[i]);
            }
        }
        return hexSStrs;
    }


    public static boolean chckBytes(byte[] bytes) {
        if (bytes == null)
            return false;
        for (byte b : bytes) {
            if (255 != b) {
                return true;
            }
        }
        return false;
    }


    public static byte[] chckAndFillBytes(byte[] bytes) {
        if (!chckBytes(bytes)) {
            for (byte b : bytes) {
                b = 0;
            }
        }
        return bytes;
    }


    public static float byte4TofloatIs(byte[] b, int off) {
        int l = b[(off + 0)];
        l &= 0xFF;
        l = (int) (l | b[(off + 1)] << 8);
        l &= 0xFFFF;
        l = (int) (l | b[(off + 2)] << 16);
        l &= 0xFFFFFF;
        l = (int) (l | b[(off + 3)] << 24);
        return Float.intBitsToFloat(l);
    }


    public static byte[] floatTobyte4Is(float f) {
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = ((byte) (fbit >> 24 - i * 8));
        }


        int len = b.length;

        byte[] dest = new byte[len];

        System.arraycopy(b, 0, dest, 0, len);


        for (int i = 0; i < len / 2; i++) {
            byte temp = dest[i];
            dest[i] = dest[(len - i - 1)];
            dest[(len - i - 1)] = temp;
        }

        return dest;
    }
}


