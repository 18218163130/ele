package com.ele.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 产生用户编码工具类
 *
 * @Author dongwf
 * @Date 2019/10/10
 */
public class UserCodeUtils {

    private static int sequence = 0;

    private static int length = 2;

    /**
     * 2位自增长码
     *
     * @return
     */
    public static synchronized String getLocalTrmSeqNum() {
        sequence = sequence >= 99 ? 1 : sequence + 1;
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date());
        String s = Integer.toString(sequence);
        return datetime + addLeftZero(s, length);
    }

    /**
     * 在左边填充0
     *
     * @param s
     * @param length
     * @return
     */
    public static String addLeftZero(String s, int length) {
        // StringBuilder sb=new StringBuilder();
        int old = s.length();
        if (length > old) {
            char[] c = new char[length];
            char[] x = s.toCharArray();
            if (x.length > length) {
                throw new IllegalArgumentException(
                        "Numeric value is larger than intended length: " + s
                                + " LEN " + length);
            }
            int lim = c.length - x.length;
            for (int i = 0; i < lim; i++) {
                c[i] = '0';
            }
            System.arraycopy(x, 0, c, lim, x.length);
            return new String(c);
        }
        return s.substring(0, length);

    }


}
