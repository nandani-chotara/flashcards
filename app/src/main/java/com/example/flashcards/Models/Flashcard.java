package com.example.flashcards.Models;

import java.io.Serializable;

public class Flashcard implements Serializable {
        private String question;
        private String answer;
        public Flashcard(){

        }

        public Flashcard(String question, String ans) {
            this.question = question;
            this.answer = ans;
        }


        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }



}


