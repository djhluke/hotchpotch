package com.lxq.hotchpotch.common.tool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * socket 发送tcp请求工具类
 *
 * @author ：顾兴鑫
 * @ClassName:：SocketUtil
 * @Description： TODO
 * @date ：2018年8月14日 上午11:29:35
 */
public class SocketUtil {

    protected static Logger logger = LoggerFactory.getLogger(SocketUtil.class);

    /**
     * 发送socket请求
     *
     * @param ：@param  clientIp
     * @param ：@param  clientPort
     * @param ：@param  msg
     * @param ：@return
     * @return ：String
     * @Title：tcpPost
     * @Description：TODO
     */
    @SuppressWarnings("unused")
    private static synchronized String tcpPost(String clientIp, String clientPort, String msg) {

        String rs = "";

        if (clientIp == null || "".equals(clientIp) || clientPort == null || "".equals(clientPort)) {
            logger.error("Ip或端口不存在...");
            return null;
        }

        int clientPortInt = Integer.parseInt(clientPort);

        logger.info("clientIp：" + clientIp + " clientPort：" + clientPort);

        Socket s = null;
        OutputStream out = null;
        InputStream in = null;

        try {
            s = new Socket(clientIp, clientPortInt);

            s.setSendBufferSize(4096);
            s.setTcpNoDelay(true);
            s.setSoTimeout(60 * 1000);
            s.setKeepAlive(true);

            System.out.println(s.isConnected());
            out = s.getOutputStream();
            in = s.getInputStream();

            //准备报文msg
//            logger.info("准备发送报文长度："+msg.length());
//            String mString ="00000951<?xml version='1.0' encoding='utf-8'?><Service><Service_Header><service_id>03550000000000</service_id><requester_id>0373</requester_id><service_time>20160831151055</service_time><version_id>01</version_id><service_sn>9501000000019160902</service_sn><branch_id>320114018</branch_id><channel_id>08</channel_id><requester>AFA_LAFE</requester></Service_Header><Service_Body><ext_attributes><INM-TERM-TYP>A</INM-TERM-TYP><INM-TERM-SRL>001</INM-TERM-SRL><INM-LAN-ID>01</INM-LAN-ID><TELLER-IDENTIFY>1</TELLER-IDENTIFY><INM-TELLER-ID>320114018001</INM-TELLER-ID></ext_attributes><request><QuerierName>zhang</QuerierName><QuerierCertype>0</QuerierCertype><QuerierCertno>110108195805175419</QuerierCertno><QueryReason>01</QueryReason><QueryFormat>30</QueryFormat><QueryType>03</QueryType><SysCode>03</SysCode><OtherSysUserid>B030106</OtherSysUserid><OthersysOrgcode>320223999</OthersysOrgcode><FlowId>44517</FlowId></request><response/></Service_Body></Service>";
            // out.write(("00000942"+msg).getBytes("UTF-8"));
            String msgg = "00000951<?xml version='1.0' encoding='utf-8'?><Service><Service_Header><service_id>03550000000000</service_id><requester_id>0373</requester_id><service_time>20160831151055</service_time><version_id>01</version_id><service_sn>9501000000019160902</service_sn><branch_id>320982052</branch_id><channel_id>08</channel_id><requester>AFA_LAFE</requester></Service_Header><Service_Body><ext_attributes><INM-TERM-TYP>A</INM-TERM-TYP><INM-TERM-SRL>001</INM-TERM-SRL><INM-LAN-ID>01</INM-LAN-ID><TELLER-IDENTIFY>1</TELLER-IDENTIFY><INM-TELLER-ID>320982052001</INM-TELLER-ID></ext_attributes><request><QuerierName>zhang</QuerierName><QuerierCertype>0</QuerierCertype><QuerierCertno>110108195805175419</QuerierCertno><QueryReason>01</QueryReason><QueryFormat>30</QueryFormat><QueryType>03</QueryType><SysCode>03</SysCode><OtherSysUserid>B030106</OtherSysUserid><OthersysOrgcode>320223999</OthersysOrgcode><FlowId>44517</FlowId></request><response/></Service_Body></Service>";
            out.write(msgg.getBytes());
            out.flush();

            byte[] head = new byte[8];

            if (null != in) {
                in.read(head);

                System.out.println("head length:" + new String(head));

                int contentLen = Integer.parseInt(new String(head));
                byte[] content = new byte[contentLen];
                in.read(content);

                System.out.println("content:" + new String(content));
            }

//            byte[] rsByte = readStream(in);
//            if(rsByte!=null){
//                rs = new String(rsByte, "UTF-8");
//            }
        } catch (Exception e) {
            logger.error("tcpPost发送请求异常：" + e.getMessage());
        } finally {
            logger.info("tcpPost(rs)：" + rs);
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (s != null) {
                    s.close();
                    s = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    /**
     * 读取输入流
     *
     * @param in
     * @return
     */
    private static byte[] readStream(InputStream in) {
        if (in == null) {
            return null;
        }

        byte[] b = null;
        ByteArrayOutputStream outSteam = null;
        try {
            byte[] buffer = new byte[1024];
            outSteam = new ByteArrayOutputStream();

            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            b = outSteam.toByteArray();
        } catch (IOException e) {
            logger.error("读取流信息异常" + e);
            e.printStackTrace();
        } finally {
            try {
                if (outSteam != null) {
                    outSteam.close();
                    outSteam = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }

//    public static void main(String[] args) {
//    	tcpPost("66.3.41.25", "10028", Dom4jUtil.createXmlRequest());
//	}

}
