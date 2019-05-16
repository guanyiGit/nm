package atshunhengli.com.utils;

/**
 * 基本数据类型转换(主要是byte和其它类型之间的互转).
 *  * java basic number types:
 *  * long => 64 -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807
 *  * int  => 32 -2,147,483,648 ~ 2,147,483,647
 *  * short=> 16 -32,768 ~ 32,767
 *  * byte =>  8 -128~127
 *  
 */
public class ByteUtils {

    private static char[] hex_chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    /**
     * 将4个byte数字组成的数组合并为一个float数.
     *
     * @param arr byte数组
     * @return
     */
    public static float byte4ToFloat(byte[] arr, int offset) {
        int i = byte4ToIntBig(arr, offset);
        return Float.intBitsToFloat(i);
    }

    /**
     * 将一个float数字转换为4个byte数字组成的数组.
     *
     * @param f
     * @return
     */
    public static byte[] floatToByte4(float f) {
        int i = Float.floatToIntBits(f);
        return intToByte4Big(i);
    }

    /**
     * 将八个byte数字组成的数组转换为一个double数字.
     *
     * @param arr
     * @param offset
     * @return
     */
    public static double byte8ToDouble(byte[] arr, int offset) {
        if (arr == null || arr.length != 8) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是8位!");
        }
        return Double.longBitsToDouble(byte8ToLong(arr, offset));
    }

    /**
     * 将一个double数字转换为8个byte数字组成的数组.
     *
     * @param i
     * @return
     */
    public static byte[] doubleToByte8(double i) {
        long j = Double.doubleToLongBits(i);
        return longToByte8(j);
    }

    /**
     * 将一个char字符转换为两个byte数字转换为的数组.
     *
     * @param c
     * @return
     */
    public static byte[] charToByte2(char c) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (c >> 8);
        arr[1] = (byte) (c & 0xff);
        return arr;
    }

    /**
     * 将2个byte数字组成的数组转换为一个char字符.大端模式
     *
     * @param arr
     * @return
     */
    public static char byte2ToChar(byte[] arr) {
        if (arr == null || arr.length != 2) {
            throw new IllegalArgumentException("byte数组必须不为空,并且是2位!");
        }
        return (char) (((char) (arr[0] << 8)) | ((char) arr[1]));
    }

    /**
     * 整数转byte(每2位数字占用1byte，不是偶数后面补0)
     *
     * @param value
     * @return
     */
    public static byte[] integerToByteIs4bit(String value) {
        byte[] bytes = new byte[value.length() / 2];
        integerToByteIs4bit(value, bytes, 0);
        return bytes;
    }

    /**
     * 整数转byte(每2位数字占用1byte，不是偶数后面补0)
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static byte[] integerToByteIs4bit(String value, byte[] bytes, int offset) {
        String valStr = String.valueOf(value);
        if (valStr.length() % 2 != 0) {// 奇数后面补0
            valStr = valStr + "0";
            byte[] newByte = new byte[bytes.length + 1];
            copyArrays(bytes, 0, bytes.length, newByte, 0);
        }

        char[] chars = valStr.toCharArray();

        for (int i = 0; i < chars.length; i += 2) {
            int temp = Integer.parseInt(String.valueOf(chars[i]) + String.valueOf(chars[i + 1]));
            intTobyte1(temp, bytes, offset++);
        }
        return bytes;
    }

    /**
     * byte转整数（每1byte转为2位数字）
     *
     * @param bytes
     * @return
     */
    public static String byte2ToIntegerStr(byte[] bytes, int offset, int len) {
        StringBuffer sb = new StringBuffer();
        while (--len >= 0) {
            byte[] temp = {0b00000000, bytes[offset++]};
            short s = byte2ToShort(temp, 0);
            if (String.valueOf(s).length() % 2 != 0)
                sb.append("0");// 不够前补0
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 将一个16位的short转换为长度为2的8位byte数组.
     *
     * @param s
     * @return
     */
    public static byte[] shortToByte2(Short s) {
        byte[] arr = new byte[2];
        arr[0] = (byte) (s >> 8);
        arr[1] = (byte) (s & 0xff);
        return arr;
    }

    /**
     * <pre>
     * 长度为2的8位byte数组转换为一个16位short数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static short byte2ToShort(byte[] arr, int offset) {
        if (arr != null && offset > arr.length) {
            throw new IllegalArgumentException("参数有误!");
        }
        return (short) (((short) arr[offset] << 8) | ((short) arr[++offset] & 0xff));
    }

    /**
     * <pre>
     * 将short转换为长度为16的byte数组.
     * 实际上每个8位byte只存储了一个0或1的数字
     * 比较浪费.
     * </pre>
     *
     * @param s
     * @return
     */
    public static byte[] shortToByte16(short s) {
        byte[] arr = new byte[16];
        for (int i = 15; i >= 0; i--) {
            arr[i] = (byte) (s & 1);
            s >>= 1;
        }
        return arr;
    }

    /**
     * @param arr
     * @return
     * @Description (长度为16的byte数组转为short, 实际上每个8位byte只存储了一个0或1的数字)
     */
    public static short byte16ToShort(byte[] arr) {
        if (arr == null || arr.length != 16) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度为16!");
        }
        short sum = 0;
        for (int i = 0; i < 16; ++i) {
            sum |= (arr[i] << (15 - i));
        }
        return sum;
    }

    /**
     * 以大端模式将int转成byte[]
     */
    public static byte[] intToByte4Big(int value) {
        // arr[0] = (byte) (value >> 24);//墙砖切断，不担心符号位
        // arr[1] = (byte) (value >> 16);
        // arr[2] = (byte) (value >> 8);
        // arr[3] = (byte) (value & 0xff);
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 以小端模式将int转成byte[]
     *
     * @param value
     * @return
     */
    public static byte[] intToByte4Little(int value) {
        byte[] src = new byte[4];
        src[3] = (byte) ((value >> 24) & 0xFF);
        src[2] = (byte) ((value >> 16) & 0xFF);
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * @param src    bytes
     * @param offset 起始下标
     * @return
     * @Description (以大端模式将byte[]转成int)
     */
    public static int byte4ToIntBig(byte[] src, int offset) {
        // &oxff去掉符号位补位
        return (int) (((src[offset++] & 0xFF) << 24) | ((src[offset++] & 0xFF) << 16) | ((src[offset++] & 0xFF) << 8)
                | (src[offset] & 0xFF));
    }

    /**
     * 以小端模式将byte[]转成int
     */
    public static int byte4ToIntLittle(byte[] src, int offset) {
        return (int) ((src[offset++] & 0xFF) | ((src[offset++] & 0xFF) << 8) | ((src[offset++] & 0xFF) << 16)
                | ((src[offset] & 0xFF) << 24));
    }

    /**
     * <pre>
     * 将长度为8的byte数组转换为64位long.
     * </pre>
     * <p>
     * 0xff对应16进制,f代表1111,0xff刚好是8位 byte[]
     * arr,byte[i]&0xff刚好满足一位byte计算,不会导致数据丢失. 如果是int计算. int[] arr,arr[i]&0xffff
     *
     * @param arr
     * @param offset
     * @return
     */
    public static long byte8ToLong(byte[] arr, int offset) {
        return (long) (((long) (arr[offset++] & 0xff) << 56) | ((long) (arr[offset++] & 0xff) << 48)
                | ((long) (arr[offset++] & 0xff) << 40) | ((long) (arr[offset++] & 0xff) << 32)
                | ((long) (arr[offset++] & 0xff) << 24) | ((long) (arr[offset++] & 0xff) << 16)
                | ((long) (arr[offset++] & 0xff) << 8) | ((long) (arr[offset] & 0xff)));
    }

    /**
     * 将一个long数字转换为8个byte数组组成的数组.
     */
    public static byte[] longToByte8(long sum) {

        byte[] arr = new byte[8];
        arr[0] = (byte) (sum >> 56);
        arr[1] = (byte) (sum >> 48);
        arr[2] = (byte) (sum >> 40);
        arr[3] = (byte) (sum >> 32);
        arr[4] = (byte) (sum >> 24);
        arr[5] = (byte) (sum >> 16);
        arr[6] = (byte) (sum >> 8);
        arr[7] = (byte) (sum & 0xff);
        return arr;
    }

    /**
     * <pre>
     * 将int转换为32位byte.
     * 实际上每个8位byte只存储了一个0或1的数字
     * 比较浪费.
     * </pre>
     *
     * @param num
     * @return
     */
    public static byte[] intToByte32(int num) {
        byte[] arr = new byte[32];
        for (int i = 31; i >= 0; i--) {
            // &1 也可以改为num&0x01,表示取最地位数字.
            arr[i] = (byte) (num & 1);
            // 右移一位.
            num >>= 1;
        }
        return arr;
    }

    /**
     * <pre>
     * 将长度为32的byte数组转换为一个int类型值.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static int byte32ToInt(byte[] arr) {
        if (arr == null || arr.length != 32) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是32!");
        }
        int sum = 0;
        for (int i = 0; i < 32; ++i) {
            sum |= (arr[i] << (31 - i));
        }
        return sum;
    }

    /**
     * <pre>
     * 将长度为64的byte数组转换为一个long类型值.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     *
     * @param arr
     * @return
     */
    public static long byte64ToLong(byte[] arr) {
        if (arr == null || arr.length != 64) {
            throw new IllegalArgumentException("byte数组必须不为空,并且长度是64!");
        }
        long sum = 0L;
        for (int i = 0; i < 64; ++i) {
            sum |= ((long) arr[i] << (63 - i));
        }
        return sum;
    }

    /**
     * <pre>
     * 将一个long值转换为长度为64的8位byte数组.
     * 每一个8位byte都只存储了0或1的数字.
     * </pre>
     *
     * @param sum
     * @return
     */
    public static byte[] longToByte64(long sum) {
        byte[] arr = new byte[64];
        for (int i = 63; i >= 0; i--) {
            arr[i] = (byte) (sum & 1);
            sum >>= 1;
        }
        return arr;
    }

    /**
     * @param src
     * @param value 要填充的值
     * @Description (byte数组填充)
     */
    public static void fillWithNUL(byte[] src, byte value) {
        for (int i = 0; i < src.length; ++i) {
            src[i] = value; // 16进制表示
        }
    }

    /**
     * @param b
     * @return
     * @Description (byte数组逆序)
     */
    public static byte[] reverseBytes(byte[] b) {
        byte[] b1 = new byte[b.length];
        int index = 0;
        for (int i = b.length - 1; i >= 0; --i) {
            b1[index] = b[i];
            ++index;
        }
        return b1;
    }

    /**
     * @param array1    第一个数组
     * @param array1off 第一个起始位置
     * @param array2    第二个数组
     * @param array2off 第二个起始位置
     * @param len       判断长度
     * @return
     * @Description (两个数组是否相等)
     */
    public static boolean bytesEquals(byte[] array1, int array1off, byte[] array2, int array2off, int len) {
        for (int i = 0; i < len; ++i)
            if (array1[array1off + i] != array2[array2off + i])
                return false;
        return true;
    }

    /**
     * @param b
     * @param off
     * @return
     * @Description (byte转成int)
     */
    public static int byte1Toint(byte[] b, int off) {
        return (0xFF & b[off]); // 0xFF java数字默认是int类型的
    }

    /**
     * @param n
     * @param b
     * @param off
     * @throws Exception
     * @Description (int转为1个byte)
     */
    public static void intTobyte1(int n, byte[] b, int off) {
        b[off] = 0x00;
        b[off] |= n;
    }

    /**
     * @param b
     * @param off
     * @return
     * @Description (两个byte转成int)
     */
    public static int byte2Toint(byte[] b, int off) {
        return byte2ToShort(b, off);
    }

    /**
     * @param n
     * @param b
     * @param off
     * @throws Exception
     * @Description (int转为2byte[])
     */
    public static void intTobyte2(int value, byte[] bytes, int offset) throws Exception {
        bytes[offset++] = (byte) ((value >> 8) & 0xFF);
        bytes[offset] = (byte) (value & 0xFF);
    }

    // public static void intTobyte2(int n, byte[] b, int off) throws Exception {
    // if (!(n >= 0 && n <= 65535))
    // throw new Exception(n + " exceed unsigned short int range [0,65535]!");
    // b[off] = 0x00;
    // b[off + 1] = 0x00;
    // b[off] |= (n >> 8);
    // b[off + 1] |= n;
    // }
    //
    //

    /**
     * @param b
     * @param off
     * @return
     * @Description (三组byte转为int)
     */
    public static int byte3Toint(byte[] b, int off) {
        int n = 0;
        n |= (0xFF & b[off]) << 16;
        n |= (0xFF & b[off + 1]) << 8;
        n |= (0xFF & b[off + 2]);
        return n;
    }

    /**
     * @param n
     * @param b
     * @param off
     * @throws Exception
     * @Description (int转为三组byte)
     */
    public static void intTobyte3(int n, byte[] b, int off) throws Exception {
        if (!(n >= 0 && n <= 0xFFFFFF))
            throw new Exception(n + " exceed unsigned short int range [0,16777215]!");
        b[off] = 0x00;
        b[off + 1] = 0x00;
        b[off + 2] = 0x00;
        b[off] |= (n >> 16);
        b[off + 1] |= (n >> 8);
        b[off + 2] |= n;
    }

    /**
     * @param b
     * @param off
     * @return
     * @Description (4组byte转成long)
     */
    public static long byte4Tolong(byte[] b, int off) {
        long n = 0L;
        n |= (0xFFL & b[off]) << 24;
        n |= (0xFFL & b[off + 1]) << 16;
        n |= (0xFFL & b[off + 2]) << 8;
        n |= (0xFFL & b[off + 3]);
        return n;
    }

    /**
     * @param n
     * @param b
     * @param off
     * @throws Exception
     * @Description (long转到4个数组中)
     */
    public static void longTobyte4(long n, byte[] b, int off) throws Exception {
        if (!(n >= 0 && n <= 4294967295L))
            throw new Exception(n + " exceed unsigned long int range [0,4294967295]!");
        b[off] = 0x00;
        b[off + 1] = 0x00;
        b[off + 2] = 0x00;
        b[off + 3] = 0x00;
        b[off] |= (n >> 24);
        b[off + 1] |= (n >> 16);
        b[off + 2] |= (n >> 8);
        b[off + 3] |= n;
    }

    /**
     * @param b
     * @param offset
     * @return
     * @Description (四组byte转int, 大端模式)
     */
    public static int byte4ToInt(byte[] b, int offset) {
        int mask = 0xff;
        int temp = 0;
        int res = 0;
        for (int i = offset; i < 4; i++) {
            res <<= 8; // 左移8位，为0不变
            temp = b[i] & mask;
            res |= temp;
        }
        return res;
    }

    /**
     * @param value
     * @return
     * @Description (int转为byte数组)
     */
    public static byte[] int2byte(int value) {
        int mask = 0xff;
        byte[] b = new byte[4];
        b[3] = (byte) (value & mask); // 强转
        b[2] = (byte) (value >> 8 & mask);
        b[1] = (byte) (value >> 16 & mask);
        b[0] = (byte) (value >> 24 & mask);
        return b;
    }

    // byte转short
    public static short byte2short(byte[] b) {
        if (b == null || b.length != 2)
            return -1;
        int mask = 0xff;
        int temp = 0;
        short res = 0;
        for (int i = 0; i < 2; i++) {
            res <<= 8;
            temp = b[i] & mask;
            res |= temp;
        }
        return res;
    }

    /**
     * @param n
     * @return
     * @Description (short转为byte)
     */
    public static byte[] shortTobyte2(short n) {
        byte[] b = new byte[2];
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * @param byte_array
     * @param off
     * @param len
     * @return
     * @Description (数组拷贝)
     */
    public static byte[] copyArrays(byte[] byte_array, int off, int len) {
        byte[] result = new byte[len];
        System.arraycopy(byte_array, off, result, 0, len); // 源数组，起始位置，目的数组，起始位置，长度
        return result;
    }

    /**
     * @param src       源数组
     * @param srcOffset 原数组起始下标
     * @param toArr     目标数组
     * @param toOffset  目标数组起始下标
     * @param len       拷贝长度
     * @return byte[0源数组下标, 1目标数组下标]
     * @Description (数组拷贝)
     */
    public static int[] copyArrays(byte[] src, int srcOffset, int len, byte[] toArr, int toOffset) {
        while (len-- > 0) {
            toArr[toOffset++] = src[srcOffset++];
        }
        int[] result = {--srcOffset, --toOffset};
        return result;
    }

    /**
     * @param byte_array
     * @param off
     * @param len
     * @return
     * @Description (byte数组转为16进制 ， 利用 hex_chars 数组)
     */
    public static String byteTohex(byte[] byte_array, int off, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = off; i < off + len; ++i) {
            byte temp = byte_array[i];
            sb.append(hex_chars[temp >>> 4 & 0X0F]); // 无符号右移，左侧补0
            sb.append(hex_chars[temp & 0X0F]);
        }
        return sb.toString();
    }

    /**
     * @param byte_array
     * @return
     * @Description (16进制转为byte数组)
     */
    public static String byteTohex(byte[] byte_array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byte_array.length; ++i) {
            byte temp = byte_array[i];
            sb.append(hex_chars[temp >>> 4 & 0X0F]);
            sb.append(hex_chars[temp & 0X0F]);
        }
        return sb.toString();
    }

    /**
     * @param byte_array
     * @return
     * @Description (byte 转16进制补空白)
     */
    public static String byteTohexWithBlank(byte[] byte_array) {
        if (byte_array == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byte_array.length; ++i) {
            byte temp = byte_array[i];
            sb.append(hex_chars[temp >>> 4 & 0X0F]);
            sb.append(hex_chars[temp & 0X0F]);
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * @param hexString
     * @return
     * @throws Exception
     * @Description (16进制字符串转byte数组)
     */
    public static byte[] hexTobyte(String hexString) throws Exception {
        byte[] hexStrBytes = hexString.getBytes();

        if (hexStrBytes.length % 2 != 0)
            throw new IllegalArgumentException("[" + hexString + "] lengh is even");

        byte[] bytes = new byte[hexStrBytes.length / 2];
        for (int i = 0; i < hexStrBytes.length; i += 2) {
            String item = new String(hexStrBytes, i, 2);
            intTobyte1(Integer.parseInt(item, 16), bytes, i / 2);
        }
        return bytes;
    }

    /**
     * @param hexStringWithBlank
     * @return
     * @throws Exception
     * @Description (16进制转byte用空白)
     */
    public static byte[] hex2byteWithBlank(String hexStringWithBlank) throws Exception {
        hexStringWithBlank = hexStringWithBlank.replaceAll(" ", "");
        return hexTobyte(hexStringWithBlank);
    }

    //
    public static String revertStringFromBytes(byte[] ba) {
        int index = 0;
        for (int i = 0; i < ba.length; ++i) {
            if (ba[i] == '\0') {
                index = i;
                break;
            }
        }
        return new String(ba, 0, index); // 转为
    }

    /**
     * @param src
     * @return
     * @throws Exception
     * @Description (字符串转二进制数组)
     */
    public static String[] strToBinaryBytes(String src) throws Exception {
        System.out.println("input str:" + src + "    length:" + src.length());
        byte[] bytes = src.getBytes("utf-8");// 字符串转数组

        String[] hexSStrs = new String[bytes.length];
        String[] binaryStrs = new String[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            byte temp = bytes[i];
            String hexSStr = Integer.toHexString(temp);// 数组的每一位
            hexSStrs[i] = "0x" + hexSStr;
            String binaryStr = Integer.toBinaryString(temp);
            binaryStrs[i] = binaryStr;
            if (binaryStr.length() < 8) {
                String t = "";
                for (int j = 0; j < 8 - binaryStr.length(); j++) {
                    t += 0;
                }
                binaryStrs[i] = t + binaryStrs[i];
            }
        }
        return hexSStrs;
        // System.out.println(Arrays.toString(hexSStrs));
        // System.out.println(Arrays.toString(binaryStrs));

    }

    /**
     * @param bytes
     * @return
     * @Description (校验bytes)
     */
    public static boolean chckBytes(byte[] bytes) {
        if (bytes == null)
            return false;
        for (byte b : bytes) {
            if (0xff != (byte) b) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param bytes
     * @return 是否有效
     * @Description (校验并填充)
     */
    public static byte[] chckAndFillBytes(byte[] bytes) {
        if (!chckBytes(bytes)) { // 填充0
            for (byte b : bytes) {
                b = (byte) 0x00;
            }
        }
        return bytes;
    }

    /**
     * @param b
     * @param off
     * @return
     * @Description (手动转换)
     */
    public static float byte4TofloatIs(byte[] b, int off) {
        int l;
        l = b[off + 0];
        l &= 0xff;
        l |= ((long) b[off + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[off + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[off + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * @param f
     * @return
     * @Description (手动转换)
     */
    public static byte[] floatTobyte4Is(float f) {
        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;
    }

}
