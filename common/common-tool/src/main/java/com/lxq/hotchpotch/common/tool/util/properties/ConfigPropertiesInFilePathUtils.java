package com.lxq.hotchpotch.common.tool.util.properties;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author luxinqiang
 */
@Slf4j
public class ConfigPropertiesInFilePathUtils {

    private static ResourceBundle resource;

    static {
        String configPropertiesFilePath = System.getProperty("user.dir") + File.separator + "config" + File.separator + "config.properties";
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(configPropertiesFilePath))) {
            resource = new PropertyResourceBundle(inputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 2021-04-20 17:16
     * 外部获取
     *
     * @param configPropertiesName
     * @return java.lang.String
     * @author luxinqiang
     */
    public static String getResource(String configPropertiesName) {
        String result = null;
        try {
            result = resource.getString(configPropertiesName);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return result;
    }

}
