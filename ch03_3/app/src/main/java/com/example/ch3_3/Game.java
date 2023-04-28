package com.example.ch3_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    //定義方法時必須要有修飾符 (modifier)，如果沒有指定修飾符，Java 會自動將方法的訪問權限設置為 package-private
    //    data
    private String answer;
    private String nonRepeatedAnswer;
    private boolean win = false;

    boolean flag_easy = false; // 判斷是否為簡單模式 ( 亂數不重複 1A2B)


    //    getter
    String getAnswer() {
        return answer;
    }
    boolean isWin() {
        return win;
    }


    // 判斷遊戲困難模式，來選擇產生哪一種答案
    void generateAnswer(){
        if(flag_easy){
            nonReAnswer(); // 簡單模式就產生 -> 不重複亂數
        } else {
            repeatedAnswer(); // 沒有選簡單模式 -> 可重複亂數
        }
    }

//    可重複答案
    void repeatedAnswer() {
        answer = "";
        for (int i = 0; i < 4; i++) {
            int intNum = (int) (Math.random() * 10);
            String strNum = String.valueOf(intNum);
            answer += strNum;
        }
    }
//    不重複答案
    void nonReAnswer(){

        ArrayList<Integer> NumList = new ArrayList<Integer>();
        for(int i = 0 ; i<=9 ; i++){
            NumList.add(i);
        }

        answer = "";
        Random rand = new Random();
        for(int j = 0;j<4;j++){

            int index = rand.nextInt(NumList.size()); //取得隨機整數，區間由陣列字串的長度決定，因為每次迭代的長度會變化
            int n = NumList.get(index);
            String ch = Integer.toString(n);
            answer += ch; // 得到 4位 不重複亂數

            NumList.remove(index);
            // 利用隨機索引值 取得數字後將其刪除，避免取得同樣的數字
        }
    }

    String checkAnswer(String guess){
        int A=0,B=0,C=0;
        boolean[] answerUsed = {false,false,false,false};
        boolean[] guessUsed = {false,false,false,false};

        //檢查有幾A : 索引值相同代表正對
        for(int i=0;i<4;i++){
            if(answer.charAt(i)==guess.charAt(i)){
                A++;
                answerUsed[i]=true;
                guessUsed[i]=true;
            }
        }
        //檢查有幾B: 已排除 '正對情況'，只會檢查 check 陣列中' false '的位置
        // !check[j] 代表只有當 check[j] 為 false 才會執行，因為條件式用 ! 將 false 轉 true 才執行程式碼
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(!answerUsed[i]&& !guessUsed[j] && answer.charAt(i)==guess.charAt(j)){
                    B++;
                    answerUsed[i]=true;
                    guessUsed[i]=true;
                    break;
                }
            }
        }

        ///檢查有幾C:
        //    宣告
        Map<Character,Integer> charCount = new HashMap<>();

        char[] answerArray = answer.toCharArray();
        for(char c : answerArray){
            if(!charCount.containsKey(c)){
                charCount.put(c,1);
            }else{
                charCount.put(c,charCount.get(c)+1);
            }
        }
        for(int i: charCount.values()){
            if(i>1){
                C++;
            }
        }

        //Output
        if(A==4){
            win = true;
        }
        String result = A+"A"+B+"B"+C+"C";

        return result;
    }

}
