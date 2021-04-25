package com.lxq.hotchpotch.common.tool.util;


/**
 * @author luxinqiang
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String addZero(String str, int zero) {
        StringBuilder strBuilder = new StringBuilder(str);
        for (int i = 0; i < zero; i++) {
            strBuilder.insert(0, "0");
        }
        str = strBuilder.toString();
        return str;
    }

    public static String null2String(Object o) {
        return o == null ? "" : o.toString().trim();
    }

    public static String null3String(Object o) {
        return o == null ? "null" : o.toString().trim();
    }

    public static String null2double(String s) {
        return "".equals(s) ? "0.00" : s;
    }

    public static boolean isNullString(Object o) {
        return "".equals(null2String(o));
    }

    public static boolean isNoNullString(Object o) {
        return !isNullString(o);
    }

    public static int null2Int(Object o) {
        if (isNoNullString(o)) {
            return Integer.parseInt(o.toString());
        } else {
            return 0;
        }
    }

    public static long null2Long(Object o) {
        if (isNoNullString(o)) {
            return Long.parseLong(o.toString());
        } else {
            return 0;
        }
    }

    public static String readOneString(String str, int index, String split) {
        try {
            String[] arr = str.split(split);
            return arr[index];
        } catch (Exception e) {
            return "";
        }

    }

    public static int getStringNumber(String str, String split) {
        int number = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == split.charAt(0)) {
                number++;
            }
        }
        return number;
    }

    /**
     * unicode编码转字符串
     *
     * @param str
     * @return
     */
    public static String unicode2String(String str) {
        str = (str == null ? "" : str);
        if (!str.contains("\\u")) {
            //如果不是unicode码则原样返回
            return str;
        }
        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < str.length() - 6; ) {
            String strTemp = str.substring(i, i + 6);
            String value = strTemp.substring(2);
            int c = 0;
            for (int j = 0; j < value.length(); j++) {
                char tempChar = value.charAt(j);
                int t = 0;
                switch (tempChar) {
                    case 'a':
                        t = 10;
                        break;
                    case 'b':
                        t = 11;
                        break;
                    case 'c':
                        t = 12;
                        break;
                    case 'd':
                        t = 13;
                        break;
                    case 'e':
                        t = 14;
                        break;
                    case 'f':
                        t = 15;
                        break;
                    default:
                        t = tempChar - 48;
                        break;
                }
                c += t * ((int) Math.pow(16, (value.length() - j - 1)));
            }
            sb.append((char) c);
            i = i + 6;
        }
        return sb.toString();
    }

}
