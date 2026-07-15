package com.xhl.C07_Algorithm_Skills.ODExam.A2026.easy100.April;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xhl
 * @Date: 2026-07-14 13:32
 * @Description: 题目二 配置操作失败数量统计 100分
 */
public class CommandProcessor {
    static void main(String[] args) {
        String input;
    }
    public int processCommands(String input){
        if (input == null || input.isEmpty()) return 0;
        String trimmed = input.substring(1, input.length() - 1);
        String[] commands = trimmed.split("\\]\\[");
        Map<Integer, Integer> rules = new HashMap<>();
        int failCount = 0;

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