package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-14 13:32
 * @Description: 题目二: 配置操作失败数量统计 100分
 *  字符串解析 +
 * 理清状态流转，再写解析逻辑，最后处理边界异常
 * 题目描述：模拟一个系统的命令行 配置，包含添加、修改、删除三项操作，详情如下：
 * 添加操作命令：add_rule rule_id=1 rule_index=18
 * 修改操作命令：mod_rule rule_id=1 rule_index=100
 * 删除操作命令：del_rule rule_id=1
 */
public class CommandProcessor {
    static void main(String[] args) {
        CommandProcessor processor = new CommandProcessor();

        // 测试用例 1: 正常的增删改流程
        // add(1, 100) -> 成功
        // mod(1, 200) -> 成功
        // del(1)      -> 成功
        String test1 = "[add_rule rule_id=1 rule_index=100][mod_rule rule_id=1 rule_index=200][del_rule rule_id=1]";
        System.out.println("测试1 (正常增删改, 期望: 0): " + processor.processCommands(test1));

        // 测试用例 2: 业务逻辑失败
        // add(1, 100) -> 成功
        // add(1, 200) -> 失败 (id已存在)
        // mod(2, 100) -> 失败 (id不存在)
        // del(3)      -> 失败 (id不存在)
        String test2 = "[add_rule rule_id=1 rule_index=100][add_rule rule_id=1 rule_index=200][mod_rule rule_id=2 rule_index=100][del_rule rule_id=3]";
        System.out.println("测试2 (业务逻辑失败, 期望: 3): " + processor.processCommands(test2));

        // 测试用例 3: 修改相同的值失败
        // add(1, 100) -> 成功
        // mod(1, 100) -> 失败 (修改前后的值相同)
        String test3 = "[add_rule rule_id=1 rule_index=100][mod_rule rule_id=1 rule_index=100]";
        System.out.println("测试3 (修改相同值, 期望: 1): " + processor.processCommands(test3));

        // 测试用例 4: 参数格式与非法参数
        // add(1, 100, extra=1) -> 失败 (包含非法参数)
        // mod(1, 100=)         -> 失败 (value为空或格式错误)
        // del(1, rule_index=2) -> 失败 (del_rule多带了参数)
        String test4 = "[add_rule rule_id=1 rule_index=100 extra=1][mod_rule rule_id=1 rule_index=100=][del_rule rule_id=1 rule_index=2]";
        System.out.println("测试4 (参数格式错误, 期望: 3): " + processor.processCommands(test4));

        // 测试用例 5: 数值越界与非数字
        // add(0, 100)   -> 失败 (id越界)
        // add(10000, 1) -> 失败 (id越界)
        // mod(1, abc)   -> 失败 (非数字)
        String test5 = "[add_rule rule_id=0 rule_index=100][add_rule rule_id=10000 rule_index=1][mod_rule rule_id=1 rule_index=abc]";
        System.out.println("测试5 (数值越界与非数字, 期望: 3): " + processor.processCommands(test5));

        // 测试用例 6: 边界与空输入
        // null / 空字符串 -> 期望: 0
        System.out.println("测试6a (null输入, 期望: 0): " + processor.processCommands(null));
        System.out.println("测试6b (空字符串, 期望: 0): " + processor.processCommands(""));
        System.out.println("测试6c (只有括号, 期望: 0): " + processor.processCommands("[]"));

        // 测试用例 7: 缺少必要参数
        // add_rule rule_id=1 -> 失败 (缺少rule_index)
        // mod_rule rule_index=1 -> 失败 (缺少rule_id)
        String test7 = "[add_rule rule_id=1][mod_rule rule_index=1]";
        System.out.println("测试7 (缺少必要参数, 期望: 2): " + processor.processCommands(test7));
    }
    /**
     * 字符串解析 + 业务逻辑和状态处理
     * */
    public int processCommands(String input){
        if (input == null || input.isEmpty()) return 0;
        // 第一步：字符串预处理与分割

        String trimmed = input.substring(1, input.length() - 1);
        String[] commands = trimmed.split("\\]\\[");
        Map<Integer, Integer> rules = new HashMap<>();
        int failCount = 0;
        // 第二步：参数提取与基础校验

        for (String cmd : commands) {
            cmd = cmd.trim();
            if (cmd.isEmpty()) continue;
            String[] tokens = cmd.split("\\s+");
            if (tokens.length == 0) {
                failCount++;
                continue;
            }

            String operation = tokens[0];
            Map<String, String> params = new HashMap<>();
            boolean valid = true;

            for (int i = 1; i < tokens.length; i++) {
                String[] kv = tokens[i].split("=");
                if (kv.length != 2) {
                    valid = false;
                    break;
                }
                String key = kv[0];
                String value = kv[1];
                if (!key.equals("rule_id") && !key.equals("rule_index")) {
                    valid = false;
                    break;
                }
                params.put(key, value);
            }

            if (!valid) {
                failCount++;
                continue;
            }

            Integer ruleId = null;
            Integer ruleIndex = null;
            try {
                //第三步：业务规则校验

                if (params.containsKey("rule_id")) {
                    ruleId = Integer.parseInt(params.get("rule_id"));
                    if (ruleId < 1 || ruleId > 9999) valid = false;
                } else {
                    valid = false;
                }
                if (valid && (operation.equals("add_rule") || operation.equals("mod_rule"))) {
                    if (params.containsKey("rule_index")) {
                        ruleIndex = Integer.parseInt(params.get("rule_index"));
                        if (ruleIndex < 1 || ruleIndex > 9999) valid = false;
                    } else {
                        valid = false;
                    }
                } else if (valid && operation.equals("del_rule")) {
                    if (params.size() != 1) valid = false; // 必须只有rule_id
                } else if (!operation.equals("add_rule") && !operation.equals("mod_rule") && !operation.equals("del_rule")) {
                    valid = false;
                }
            } catch (NumberFormatException e) {
                valid = false;
            }

            if (!valid) {
                failCount++;
                continue;
            }
            //第四步：状态更新与失败计数

            if (operation.equals("add_rule")) {
                if (!rules.containsKey(ruleId)) {
                    rules.put(ruleId, ruleIndex);
                } else {
                    failCount++;
                }
            } else if (operation.equals("mod_rule")) {
                if (rules.containsKey(ruleId)) {
                    if (!rules.get(ruleId).equals(ruleIndex)) {
                        rules.put(ruleId, ruleIndex);
                    } else {
                        failCount++;
                    }
                } else {
                    failCount++;
                }
            } else if (operation.equals("del_rule")) {
                if (rules.containsKey(ruleId)) {
                    rules.remove(ruleId);
                } else {
                    failCount++;
                }
            }
        }
        return failCount;
    }
}