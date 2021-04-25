package com.lxq.hotchpotch.common.tool.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * @author luxinqiang
 */
@Slf4j
public class AddressUtils {

    /***
     * 获取本地ip地址，兼容windows和linux
     * <br/>
     * 2021-03-11 17:01
     *
     * @param
     * @return java.lang.String
     * @author luxinqiang
     */
    public static String localHostAddress() {
        String result = null;
        try {
            if (isWindowsOS()) {
                result = getWindowLocalIp();
            } else {
                result = getLinuxLocalIp();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 判断操作系统是否是Windows
     *
     * @return
     */
    public static boolean isWindowsOS() {
        boolean isWindowsOS = false;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")) {
            isWindowsOS = true;
        }
        return isWindowsOS;
    }

    /**
     * 获取Windows下的IP地址
     *
     * @return IP地址
     * @throws UnknownHostException
     */
    private static String getWindowLocalIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取Linux下的IP地址
     *
     * @return IP地址
     * @throws SocketException
     */
    private static String getLinuxLocalIp() throws SocketException {
        String ip = "";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            String name = intf.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                            ip = ipaddress;
                        }
                    }
                }
            }
        }
        return ip;
    }

}
