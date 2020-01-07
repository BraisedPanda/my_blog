import org.junit.jupiter.api.Test;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 11:37

 **/

public class NiukeTest {

    @Test
    public void lengthOfLongestSubstring(){
        System.out.println(lengthOfLongestSubstring("\"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~ abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\\\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~ "));
//        System.out.println(containsDouble("abcdferhgjc"));
    }


    /*** 两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n)) ***/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        double a = (nums1[0] + nums1[length1])/2;
        double b = (nums2[0] + nums2[length2])/2;
        double[] A = new double[2];
        A[0] = a;
        A[1] = b;
        return A[0];
    }



    /*** 求一个字符串中不含重复字符的最大子串长度 ***/

    public int lengthOfLongestSubstring(String s) {
        if(s == " " || s.equals("")){
            return 1;
        }
        else {
            int max = 1;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j <= s.length(); j++) {
                    String str = s.substring(i, j);
                    boolean flag = containsDouble(str);
                    if (flag == true) {
                        if (str.length() > max) {
                            max = str.length();
                        }
                    }
                }

            }
            return max;
        }
    }
    /*** 求一个字符串中是否含有重复字符 ***/
    public boolean containsDouble(String s){
        char A[] = s.toCharArray();
        boolean flag = true;
        int length = A.length;
        for(int i=0; i<length; i++){
            char a = A[i];
            for(int j=i+1; j<length; j++){
                if(a == A[j]){
                    flag = false;
                }
            }
        }
        return flag;
    }



    /*** 求一个数组中，目标值和的两个数组位置 ***/
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;

        for(int i=0; i<length; i++){
            for(int j=i+1; j<length; j++){
                if(target == nums[i] + nums[j]){
                    int[] A = new int[2];
                    A[0] = i;
                    A[1] = j;
                    return A;

                }
            }
        }return null;
    }


    public boolean duplicate(int numbers[],int length,int [] duplication) {

        for(int i=0;i<length;i++){
            int a = numbers[i];
            for(int j=i+1;j<length;j++){
                if(a == numbers[j]){
                    duplication[0] = a;
                    return true;
                }
            }
        }
        return false;
    }
}
