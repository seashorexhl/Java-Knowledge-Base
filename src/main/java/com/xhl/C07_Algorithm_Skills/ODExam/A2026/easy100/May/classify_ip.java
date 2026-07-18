package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.May;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xhl
 * @Date: 2026-07-10 02:55
 * @Description:    IP地址分类识别-100分
 *
 * IPv4地址分类
 * R类（保留地址）:0.0.0.0~0.255.255.255
 * A类：1.0.0.0~126.255.255.255
 * L类（环回地址）:127.0.0.0~127.255.255.255
 * B类：128.0.0.0~191.255.255.255
 * C类：192.0.0.0~223.255.255.255
 * D类（组播地址）:224.0.0.0~239.255.255.255
 * E类（保留地址）:240.0.0.0~255.255.255.255
 * 如果输入的IP地址不符合规范，则输出"F"
 */
public class classify_ip {
    /**
     *  正则表达式
     *  匹配 0-255 的终极写法是：
     * (25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)
     * 匹配邮箱：\w+@\w+\.\w+ （或者更简单点，知道用 @ 和 . 就行）
     * 匹配手机号：1[3-9]\d{9} （以 1 开头，第二位是 3-9，后面 9 位数字）
     * 提取数字/字母：\d+ 或 [a-zA-Z]+
     * 常用元字符：知道 ^ (开头)、$ (结尾)、\d (数字)、\w (字母数字下划线)、* (0次或多次)、+ (1次或多次)、? (0次或1次) 的意思。
     * */
    // 核心正则：严格匹配 0-255 且不允许前导零
    // 利用捕获组 (0-255) 提取第一段数值，避免后续再次解析
    private static final Pattern IP_PATTERN = Pattern.compile(
            "^(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\." +
                    "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\." +
                    "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\." +
                    "(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$"
    );

    static void main(String[] args) {
        String ip = "126.255.255.255";
        String classifyIp = classify_ip(ip);
        System.out.println(classifyIp);
        String classifyIp1 = classifyIp(ip);
        System.out.println(classifyIp1);
        String classifyIp2 = classify_Ip2(ip);
        System.out.println(classifyIp2);
    }

    public static String classify_ip(String ip) {
        // 使用正则表达式 "\\." 将 IP 字符串按点号分割成数组
        // 注意：在正则中 "." 是特殊字符，所以需要转义为 "\\."
        String[] parts = ip.split("\\.");
        // 1. 基础格式校验：IPv4 地址必须正好由 4 个部分组成

        if (parts.length != 4) {
            return "F";// 如果不是4段，直接返回 "F" (Fail/False)
        }
        // 2. 遍历每一部分进行详细校验
        for (int i = 0; i < 4; i++) {
            String part = parts[i];
            // 检查是否全是数字：如果不是纯数字（例如包含字母），则非法

            if (!part.matches("\\d+")) {
                return "F";
            }
            // 检查前导零：如果长度大于1且以 '0' 开头（例如 "01", "001"），通常视为非法格式

            if (part.length() > 1 && part.charAt(0) == '0') {
                return "F";
            }
            // 尝试将字符串转换为整数，并检查数值范围

            try {
                int num = Integer.parseInt(part);
                // IP 地址的每一段必须在 0 到 255 之间

                if (num < 0 || num > 255) {
                    return "F";
                }
            } catch (NumberFormatException e) {
                // 防止转换失败（虽然前面的正则已过滤，但作为防御性编程保留）
                return "F";
            }
        }
        // 3. IP 分类逻辑：解析第一段数值来判断类别

        int first_octet = Integer.parseInt(parts[0]);
        if (first_octet == 0) {
            return "R"; // 0.x.x.x 通常作为保留地址或表示本网络
        } else if (first_octet >= 1 && first_octet <= 126) {
            return "A";// A 类地址范围：1.0.0.0 - 126.255.255.255
        } else if (first_octet == 127) {
            return "L"; // 127.x.x.x 是回环地址 (Loopback)，即 localhost
        } else if (first_octet >= 128 && first_octet <= 191) {
            return "B";// B 类地址范围：128.0.0.0 - 191.255.255.255
        } else if (first_octet >= 192 && first_octet <= 223) {
            return "C";// C 类地址范围：192.0.0.0 - 223.255.255.255
        } else if (first_octet >= 224 && first_octet <= 239) {
            return "D";// D 类地址（多播/组播）：224.0.0.0 - 239.255.255.255
        } else if (first_octet >= 240 && first_octet <= 255) {
            return "E";// E 类地址（保留用于实验）：240.0.0.0 - 255.255.255.255
        } else {
            return "F";// 其他情况（理论上不会发生，因为前面已校验范围），归为非法
        }
    }

    /**
     * 方法二：验证IP格式并返回其类别
     * 单次 for 循环 + parseInt
     * @param ip IP地址字符串
     * @return 类别字符 (A, B, C, D, E, L) 或 "F" (Fail)
     */
    public static String classifyIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return "F";
        }

        // 1. 分割并检查段数
        String[] parts = ip.split("\\.", -1); // -1 防止末尾点号导致的截断差异
        if (parts.length != 4) {
            return "F";
        }

        int[] octets = new int[4];

        // 2. 统一校验并转换数值 (避免后续重复 parseInt)
        for (int i = 0; i < 4; i++) {
            String part = parts[i];

            // 基础格式校验：非空、纯数字、无前导零(除非是"0"本身)
            if (part.isEmpty() || !part.matches("\\d+") || (part.length() > 1 && part.charAt(0) == '0')) {
                return "F";
            }

            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return "F";
                }
                octets[i] = num; // 缓存解析结果
            } catch (NumberFormatException e) {
                return "F";
            }
        }

        // 3. 根据第一段数值判断类别
        return getCategoryByFirstOctet(octets[0]);
    }

    /**
     * 根据第一个八位组判断IP类别
     */
    private static String getCategoryByFirstOctet(int first) {
        if (first == 0)   return "R"; // 保留/特殊
        if (first <= 126) return "A"; // A类: 1-126
        if (first == 127) return "L"; // 回环地址
        if (first <= 191) return "B"; // B类: 128-191
        if (first <= 223) return "C"; // C类: 192-223
        if (first <= 239) return "D"; // D类: 224-239 (组播)
        if (first <= 255) return "E"; // E类: 240-255 (实验)
        return "F";
    }

    public static String classify_Ip2(String ip) {
        if (ip == null || ip.isEmpty()) return "F";

        Matcher matcher = IP_PATTERN.matcher(ip);

        // 1. 如果正则匹配失败，说明格式非法（包含字母、前导零、超出255等）
        if (!matcher.matches()) {
            return "F";
        }

        // 2. 匹配成功，直接提取第一段的数值（避免重复 Integer.parseInt）
        int firstOctet = Integer.parseInt(matcher.group(1));

        // 3. 根据第一段判断类别
        if (firstOctet == 0)   return "R";
        if (firstOctet <= 126) return "A";
        if (firstOctet == 127) return "L";
        if (firstOctet <= 191) return "B";
        if (firstOctet <= 223) return "C";
        if (firstOctet <= 239) return "D";
        return "E"; // 匹配成功的情况下，剩下的必定是 240-255
    }
}
