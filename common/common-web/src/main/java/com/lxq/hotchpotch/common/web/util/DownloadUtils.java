package com.lxq.hotchpotch.common.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author luxinqiang
 */
@Slf4j
public class DownloadUtils {

    public static void downloadInResources(String readFileName) throws Exception {
        log.info("开始导出resources下文件：" + readFileName);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new Exception("获取requestAttributes为null");
        }
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        if (response == null) {
            throw new Exception("获取response为null");
        }
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            /*
            1.读取resources目录下的文件
             */
            ClassPathResource classPathResource = new ClassPathResource(readFileName);
            InputStream inputStream = classPathResource.getInputStream();
            // 复制成字节，直接用流linux系统报错，此方法自动关闭流
            byte[] buffer = FileCopyUtils.copyToByteArray(inputStream);
            /*
            2.输出文件
             */
            String writeFileName = readFileName;
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(writeFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.addHeader("Content-Lenth", "" + buffer.length);
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            log.info("结束导出resources下文件：" + readFileName);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
