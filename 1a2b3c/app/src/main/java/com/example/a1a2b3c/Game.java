package com.example.a1a2b3c;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {

    //data (沒有修飾子會用預設: private)
    String answer;
    boolean win = false;

    // getter
    String getAnswer(){
        return answer;
    }
    boolean isWin(){
        return win;
    }

    //function
    //    產生答案的函式: 可重複的四位亂數。可重複時利用 char[] index = new Random.nextInt(list.length);
    void generateAnswer() {
        String result = "";
        for (int i = 0; i < 4; i++) {
            int randNum = new Random().nextInt(10);
            String strNum = String.valueOf(randNum); //int 是 primitive type，不能用 toString()
            result += strNum;
        }
    }
    boolean[] userUsed={false,false,false,false};
    boolean[] answerUsed={false,false,false,false};
    int A=0,B=0,C=0;
    //檢查答案
    String checkAnswer(String guess){
        //計算 A
        for(int i=0;i<4;i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                answerUsed[i] = true;
                userUsed[i] = true;
                A++;
            }
        }
        //計算 B
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(!answerUsed[i] && !userUsed[j] && guess.charAt(j) == answer.charAt(i)){
                    answerUsed[i] = true;
                    userUsed[j] = true;
                    B++;
                }
            }
        }

        //計算 C
        Map<Character,Integer> charCount=new HashMap<>();

        char[] charAnswer=answer.toCharArray();
        for(char c:charAnswer){
            if(!charCount.containsKey(c)){
                charCount.put(c,1);
            }else{
                charCount.put(c,charCount.get(c)+1);
            }
        }
        for(int i : charCount.values()){
            if (i>1){
                C++;
            }
        }

        //result
        String result=A+"A"+B+"B"+C+"C";

        if(A==4){
            win=true;
        }
        return result;
    }

}