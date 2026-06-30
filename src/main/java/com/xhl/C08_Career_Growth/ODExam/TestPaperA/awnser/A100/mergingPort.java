package com.xhl.C08_Career_Growth.ODExam.TestPaperA.awnser.A100;

import java.util.*;

/**
 * @Author: xhl
 * @Date: 2026-06-25 21:58
 * @Description: 06 端口合并 mergingPort
 */
public class mergingPort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());

        // M,N不在限定范围内，统一输出一组空数组[[]]
        if (m > 10 || m < 1) {
            System.out.println("[[]]");
            return;
        }

        // 这里使用ArrayList接收端口组，是为了后面更方便进行端口组之间的合并
        ArrayList<ArrayList<Integer>> ports = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            Integer[] tmp =
                    Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

            // M,N不在限定范围内，统一输出一组空数组[[]]
            int n = tmp.length;
            if (n < 1 || n > 100) {
                System.out.println("[[]]");
                return;
            }

            ArrayList<Integer> tmpList = new ArrayList<>(Arrays.asList(tmp));
            ports.add(tmpList);
        }

        System.out.println(getResult(ports));
    }

    // 算法入口
    public static String getResult(ArrayList<ArrayList<Integer>> ports) {
        outer:
        while (true) {
            // 这里倒序遍历端口组是为了实现：组外顺序保持输入顺序
            for (int i = ports.size() - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {

                    // 判断两个端口是否可以合并
                    if (canUnion(ports.get(i), ports.get(j))) {
                        // 将后面的端口组，并入前面的端口组，这样就不会破坏组外顺序
                        ports.get(j).addAll(ports.get(i));
                        ports.remove(i);
                        continue outer;
                    }
                }
            }

            break;
        }

        StringJoiner out = new StringJoiner(",", "[", "]");
        for (ArrayList<Integer> port : ports) {
            StringJoiner in = new StringJoiner(",", "[", "]");
            for (Integer v : new TreeSet<Integer>(port)) { // 这里使用TreeSet是为了实现：组内相同端口仅保留一个，从小到达排序。
                in.add(v + "");
            }
            out.add(in.toString());
        }

        return out.toString();
    }

    // 如果端口组间存在2个及以上不同端口相同，则认为这2个端口组互相关联，可以合并。
    // 下面方法实现中：对于“不同端口”的理解是：端口位置不同，端口值可以相同，即以不同位置的端口视为不同端口
    public static boolean canUnion(ArrayList<Integer> port1, ArrayList<Integer> port2) {
        port1.sort((a, b) -> a - b);
        port2.sort((a, b) -> a - b);

        int same = 0;
        int i = 0;
        int j = 0;

        while (i < port1.size() && j < port2.size()) {
            if (port1.get(i) - port2.get(j) == 0) {
                i++;
                j++;
                if (++same >= 2) return true;
            } else if (port1.get(i) > port2.get(j)) {
                j++;
            } else {
                i++;
            }
        }

        return false;
    }
}
