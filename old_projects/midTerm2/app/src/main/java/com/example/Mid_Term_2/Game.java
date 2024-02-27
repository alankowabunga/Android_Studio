package com.example.Mid_Term_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    //    data
    private String answer;
    private boolean win = false;

    //    getter
    String getAnswer() {
        return answer;
    }

    boolean isWin() {
        return win;
    }




    // 可重複亂數
    String repeatableAnswer() {

        answer = "";
        for (int i = 0; i < 4; i++) {
            int randNum = (int) (Math.random() * 10);
            String strNum = Integer.toString(randNum);
            answer += strNum;
        }
        return answer;
    }

    // 不可重複亂數
    String nonRepeatAnswer() {

        Random rand = new Random();

        ArrayList<Integer> numList = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numList.add(i); // 建立 1-9 的數字庫
        }
        answer = "";
        for (int j = 0; j < 4; j++) {
            int index = rand.nextInt(numList.size()); // 根據數字庫長度決定隨機產商亂數的區間
            int n = numList.get(index);
            String strNum = Integer.toString(n);
            answer += strNum;
            numList.remove(index);
        }

        return answer;
    }

    // 對比答案
    String checkAnswer(String guess) {
        int a = 0, b = 0, c = 0;
        boolean[] answerUsed = {false, false, false, false};
        boolean[] guessUsed = {false, false, false, false};
        // 計算 a
        for (int i = 0; i < 4; i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                answerUsed[i] = true;
                guessUsed[i] = true;
                a++;
            }
            System.out.println();
        }
        // 計算 b
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!guessUsed[i] && !answerUsed[j] && guess.charAt(i) == answer.charAt(j)) {
                    answerUsed[j] = true;
                    guessUsed[i] = true;
                    b++;
                    break;
                }
            }
        }
        // 計算 c
        Map<Character, Integer> charCount = new HashMap<>();
        char[] charList = answer.toCharArray();
        for (char ch : charList) {
            if (!charCount.containsKey(ch)) {
                charCount.put(ch, 1);
            } else {
                charCount.put(ch, charCount.get(ch) + 1);
            }
        }
        for (int i : charCount.values()) {
            if (i > 1) {
                c++;

            }
        }

        //輸出結果
        String result = a + "A" + b + "B" + c + "C";
        if (a == 4) {
            win = true;
        }
        return result;

    }
    Integer getC(String guess){
        int repeated=0;
        Map<Character, Integer> charCount = new HashMap<>();
        char[] charList = answer.toCharArray();
        for (char ch : charList) {
            if (!charCount.containsKey(ch)) {
                charCount.put(ch, 1);
            } else {
                charCount.put(ch, charCount.get(ch) + 1);
            }
        }
        for (int i : charCount.values()) {
            if (i > 1) {
                repeated++;

            }
        }
        return repeated;
    }

}
