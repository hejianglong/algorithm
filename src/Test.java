/*
 * Project: PACKAGE_NAME
 *
 * File Created at 2019-07-06
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */

/**
 * @author hejianglong
 * @date 2019-07-06 11:32
 * @email hejlong@163.com
 * @Desc
 */
public class Test {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        //compareSort(arr);
        //selectSort(arr);
        //compareSort(arr);
        f1();
    }

    private static void f1() {
        float sum;
        int i;
        sum = 0;
        for (i = 1; i <= 100; i++) {
            sum += 0.1;
        }
        System.out.println(sum);
    }

    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for (;j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j+1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void compareSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isBreak = true;

            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int pre = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = pre;
                    isBreak = false;
                }
            }
            if (isBreak) {
                break;
            }
        }
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for(int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int pre = arr[i];
                    arr[i] = arr[j];
                    arr[j] = pre;
                }
            }
        }
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
