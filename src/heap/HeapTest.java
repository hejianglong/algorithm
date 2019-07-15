/*
 * Project: heap
 *
 * File Created at 2019-07-15
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package heap;

/**
 * @author hejianglong
 * @date 2019-07-15 22:13
 * @email hejlong@163.com
 * @Desc
 */
public class HeapTest {

    public static void main(String[] args) {
        HeapTest test = new HeapTest();
        int[] a = new int[]{3,2,3,1,2,4,5,5,6};
        int rs = test.findKthLargest(a, 4);
        System.out.println();
        System.out.println(rs);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        buildHeap(nums, nums.length);
        int p = nums.length - 1;
        int i = 0;
        while (p >= 0) {
            if (++i == k) {
                return nums[0];
            }
            swap(nums, 0, p);
            p--;
            heapify(nums, p, 0);
        }

        return 1;
    }

    private void buildHeap(int[] a, int n) {
        for (int i = n/2; i >= 0; i--) {
            heapify(a, n, i);
        }
    }

    private void heapify(int[] a, int n, int i) {
        for(;;) {
            int maxPos = i;
            if (i * 2 + 1 < n && a[i] < a[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (i * 2 + 2 <n && a[maxPos] < a[i * 2 + 2]) {
                maxPos = i * 2 + 2;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] a, int i, int maxPos) {
        int tmp = a[i];
        a[i] = a[maxPos];
        a[maxPos] = tmp;
    }
}
