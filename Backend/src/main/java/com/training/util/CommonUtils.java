package com.training.util;

import org.apache.commons.lang3.StringUtils;

/**
 * The type Common utils.
 */
public class CommonUtils {


    /**
     * Return string value string.
     * If the value is null or blank, return empty.
     * Else return value.
     *
     * @param value the value
     * @return the string
     */
    public static String returnStringValue(String value) {
        if (StringUtils.isBlank(value)) {
            return StringUtils.EMPTY;
        }

        return value;
    }
}
