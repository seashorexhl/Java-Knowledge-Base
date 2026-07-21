package com.xhl.C07_Algorithm_Skills.ODExam.LatestOD.C0708;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xhl
 * @Date: 2026-07-19 16:29
 * @Description: Spell combinations 魔法咒语组合
 *
 */
public class SpellCombinations {
    static void main(String[] args) {

        String[] fragments = {"fire", "water", "earth"};
        SpellCombinations sc = new SpellCombinations();
        System.out.println(sc.magicFormations(fragments));
    }
    // 回朔 + 筛选 + 排序
    public List<String> magicFormations(String[] fragments) {
        List<String> pureFragments = new ArrayList<>();

        // 1. 筛选纯净碎片
        for (String frag : fragments) {
            if (isPure(frag)) {
                pureFragments.add(frag);
            }
        }

        // 如果没有纯净碎片，返回空列表
        if (pureFragments.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        boolean[] used = new boolean[pureFragments.size()];
        List<String> current = new ArrayList<>();

        // 2. 生成全排列
        permute(pureFragments, used, current, result);

        // 3. 排序结果
        Collections.sort(result);

        return result;
    }

    // 判断碎片是否纯净（内部无重复字母）
    private boolean isPure(String s) {
        boolean[] seen = new boolean[26];
        for (char c : s.toCharArray()) {
            if (seen[c - 'a']) {
                return false; // 有重复字母
            }
            seen[c - 'a'] = true;
        }
        return true;
    }

    // 回溯法生成全排列
    private void permute(List<String> pureFragments, boolean[] used, List<String> current, List<String> result) {
        if (current.size() == pureFragments.size()) {
            // 排列完成，用空格连接
            result.add(String.join(" ", current));
            return;
        }

        for (int i = 0; i < pureFragments.size(); i++) {
            if (used[i]) continue;

            used[i] = true;
            current.add(pureFragments.get(i));

            permute(pureFragments, used, current, result);

            // 回溯
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
