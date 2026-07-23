package com.xhl.C07_Algorithm_Skills.SelfSummary.BaseAL.Math;

/**
 * @Author: xhl
 * @Date: 2026-06-25 19:38
 * @Description: 9. 回文数
 */
public class isPalindrome {
    static void main() {
        int x = 12321;
        isPalindrome ip = new isPalindrome();
        boolean palindrome = ip.isPalindrome(x);
        System.out.println("方法一：反转一半数字 ，是否是回文数？：" + palindrome);
        System.out.println("方法二：双指针法，是否是回文数？：" + ip.isPalindrome1(x));
        System.out.println("方法三：" +ip.isPalindrome2(x));
    }
    /**
     *  方法一：反转一半数字
     * */
    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
//        当原始数字小于或等于反转后的数字时，就意味着我们已经处理了一半位数的数字了
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    // 方法二 ：双指针法
    boolean isPalindrome1(int x) {
        if(x<0||(x%10==0&&x!=0)){
            return false;
        }
        //将整数下转化成字符串
        String str = Integer.toString(x);
        int right=0,left=str.length()-1;
        while(right<left){
            if(str.charAt(right) != str.charAt(left)){
                return false;
            }
            left--;
            right++;
        }
        return true;
    }
    // 字符串 反转
    public boolean isPalindrome2(int x) {
        String s=String.valueOf(x);
        StringBuffer sb1=new StringBuffer(s);
        StringBuffer sb2=sb1.reverse();
        String s1=sb2.toString();
        if(s.equals(s1)){
            return true;
        }else{
            return false;
        }

    }


}
