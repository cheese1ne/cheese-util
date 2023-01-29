package com.cheese.util.codenav.modules.targetnav.common.enums;

/**
 * 检查错误枚举
 *
 * @author sobann
 */
public enum CheckErrorEnum {

    ;
    private final String errorKey;
    private final String describe;

    CheckErrorEnum(String errorKey, String describe) {
        this.errorKey = errorKey;
        this.describe = describe;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getDescribe() {
        return describe;
    }

    /**
     * 根据类型解析枚举
     *
     * @param errorKey
     * @return
     */
    public static CheckErrorEnum parseType(String errorKey) {
        CheckErrorEnum[] values = CheckErrorEnum.values();
        for (CheckErrorEnum errorEnum : values) {
            if (errorEnum.errorKey.equals(errorKey)) {
                return errorEnum;
            }
        }
        throw new IllegalArgumentException("未知的checker错误类型：" + errorKey);
    }
}
