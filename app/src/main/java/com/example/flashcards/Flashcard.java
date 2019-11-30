package com.example.flashcards;

import java.io.Serializable;

public class Flashcard implements Serializable {
        private String question;
        private String answer;

        public Flashcard() {
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


