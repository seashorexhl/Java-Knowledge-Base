package com.xhl.Career_Growth.SelfSummary.AdvancedAL.Enum;

/**
 * @Author: xhl
 * @Date: 2026-06-12 15:32
 * @Description:
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 业务场景（如状态码、类型映射等）
 * */
/**
 * 业务类型枚举示例
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum {

    /**
     * 普通订单
     */
    NORMAL_ORDER(1, "普通订单"),

    /**
     * 预售订单
     */
    PRE_SALE_ORDER(2, "预售订单");

    /**
     * 状态编码
     */
    private final Integer code;

    /**
     * 描述信息
     */
    private final String desc;

    /**
     * 根据编码获取描述信息
     */
    public static String getDescByCode(Integer code) {
        if (code == null) {
            return "";
        }
        return Arrays.stream(values())
                .filter(item -> item.getCode().equals(code))
                .findFirst()
                .map(BusinessTypeEnum::getDesc)
                .orElse("");
    }

    /**
     * 获取所有编码与描述的映射 Map
     */
    public static Map<Integer, String> getAllCodeDescMap() {
        return Arrays.stream(values())
                .collect(Collectors.toMap(
                        BusinessTypeEnum::getCode,
                        BusinessTypeEnum::getDesc
                ));
    }
}