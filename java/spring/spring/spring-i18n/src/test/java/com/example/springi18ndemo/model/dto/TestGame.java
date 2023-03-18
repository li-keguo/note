package com.example.springi18ndemo.model.dto;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * TestGame.
 *
 * @author <a href='mailto:likeguo@apache.org'> likeguo </a>
 */
public class TestGame {
    
    private static final String[] comm = new String[]{"石头", "剪刀", "布"};
    
    static class Ribbit {
        
        public String go() {
            return comm[RandomUtil.randomInt(2)];
        }
    }
    
    private static final Map<String, Integer> map = new HashMap<>(3);
    
    public static void main(String[] args) {
        final Ribbit ribbit = new Ribbit();
        final Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            m(ribbit, scanner);
        }
        System.out.println("Game Over！");
        System.out.println(map);
    }
    
    private static void m(Ribbit ribbit, Scanner scanner) {
        System.out.println("1 石头 2 剪刀 3 布");
        final Integer common = scanner.nextInt();
        if (common > 3) {
            System.out.println("没有这个选项哦");
            return;
        }
        final String go = ribbit.go();
        System.out.println("机器人出：" + go);
        final String duibi = duibi(go, comm[common - 1]);
        System.out.printf("结果 机器人：%s 你出%s %s\n", go, comm[common - 1], duibi);
        final Integer integer = ObjectUtil.defaultIfNull(map.get(duibi), 0);
        map.put(duibi, integer + 1);
        
    }
    
    private static String duibi(String ribbit, String you) {
        Set<String> ZOMBIE_SET = new ConcurrentHashSet<>();
        if (Objects.equals(ribbit, you)) {
            // 代码完整性验证
            return "平局";
        }
        if (comm[0].equals(ribbit)) {
            if (comm[1].equals(you)) {
                return "你输了";
            }
        }
        if (comm[1].equals(ribbit)) {
            if (comm[2].equals(you)) {
                return "你输了";
            }
        }
        if (comm[2].equals(ribbit)) {
            if (comm[0].equals(you)) {
                return "你输了";
            }
        }
        return "你赢了";
    }
}
