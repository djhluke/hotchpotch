package com.lxq.hotchpotch.common.web.util.properties;

import com.lxq.hotchpotch.common.tool.util.properties.ConfigPropertiesInFilePathUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luxinqiang
 */
@Slf4j
public class ConfigPropertiesUtils extends ConfigPropertiesInFilePathUtils {

    /**
     * 2021-04-20 17:14
     * 先从外部获取，外部不存在则从内部resources下获取
     *
     * @param configPropertiesName
     * @return java.lang.String
     * @author luxinqiang
     */
    public static String getResource(String configPropertiesName) {
        String result = ConfigPropertiesInFilePathUtils.getResource(configPropertiesName);
        if (result != null) {
            return result;
        }
        result = ConfigPropertiesInResourcesUtils.getResource(configPropertiesName);
        return result;
    }

}
