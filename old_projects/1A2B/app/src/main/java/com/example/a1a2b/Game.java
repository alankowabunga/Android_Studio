package com.example.a1a2b;
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
    //    function
    void generateAnswer(){
        char[] list = {'0','1','2','3','4','5','6','7','8','9'};
        String result = "";
        for (int i=0;i<4;i++)
        {
            int index = new Random().nextInt(list.length);
            result += list[index];
        }
        answer = result;
    }
    String checkAnswer(String guess){
        int a = 0, b = 0;
        boolean[] answerUsed = {false,false,false,false};
        boolean[] guessUsed = {false,false,false,false};
        for(int i = 0; i < 4; i++)
        {
            if(guess.charAt(i)==answer.charAt(i)) {
                answerUsed[i]=true;
                guessUsed[i]=true;
                a++;
            }
            System.out.println();
        }
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++) {
                if (!guessUsed[i] && !answerUsed[j] && guess.charAt(i) == answer.charAt(j)) {
                    answerUsed[j] = true;
                    guessUsed[i] = true;
                    b++;
                    break;
                }
            }
        }
        String result = a + "A" + b + "B";
        if(a==4)
        {
            win = true;
        }
        return result;
    }
}
