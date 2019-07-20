/*
 * Project: tree
 *
 * File Created at 2019-07-20
 *
 * Copyright 2019 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC 自动机
 * @author hejianglong
 * @date 2019-07-20 14:52
 * @email hejlong@163.com
 * @Desc
 */
public class AC {

    AcNode root = new AcNode('/', 0);

    public static void main(String[] args) {
        AC trie = new AC();
        String s1 = "abcd";
        String s2 = "bcd";
        String s3 = "c";
        String s4 = "e";
        trie.insert(s1.toCharArray());
        trie.insert(s2.toCharArray());
        trie.insert(s3.toCharArray());
        trie.buildFailurePointer();
        trie.match(s4.toCharArray());
    }

    public void insert(char[] data) {
        AcNode p = root;
        for (int i = 0; i < data.length; i++) {
            int index = data[i] - 'a';
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(data[i], i + 1);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; i++) {
                AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }

    public void match(char[] text) {
       AcNode p = root;
       for (int i = 0; i < text.length; i++) {
           int index = text[i] - 'a';
           while (p != root && p.children[index] == null) {
               p = p.fail;
           }
           p = p.children[index];
           if (p == root) {
               p = root;
           }
           AcNode tmp = p;
           while (tmp != root && tmp != null) {
               if (tmp.isEndingChar) {
                   int pos = i - tmp.length + 1;
                   System.out.println("匹配位置 " + pos + "; 长度 " + tmp.length);
               }
               tmp = tmp.fail;
           }
       }
    }

    public class AcNode {
        public char data;
        public boolean isEndingChar = false;
        public int length;
        public AcNode fail;
        public AcNode[] children = new AcNode[26];

        public AcNode(char data, int length) {
            this.data = data;
            this.length = length;
        }
    }
}
