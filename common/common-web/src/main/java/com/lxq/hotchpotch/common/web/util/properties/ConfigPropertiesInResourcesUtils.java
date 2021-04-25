package com.lxq.hotchpotch.common.web.util.properties;

import com.lxq.hotchpotch.common.tool.util.properties.ConfigPropertiesInFilePathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @author luxinqiang
 */
@Slf4j
public class ConfigPropertiesInResourcesUtils extends ConfigPropertiesInFilePathUtils {

    private static ResourceBundle resource;

    static {
        ClassPathResource classPathResource = new ClassPathResource("config.properties");
        try (InputStream inputStream = classPathResource.getInputStream()) {
            resource = new PropertyResourceBundle(inputStream);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 2021-04-20 17:16
     * 内部resources下获取
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
