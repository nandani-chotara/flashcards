package com.example.flashcards;

import java.io.Serializable;

public class Flashcard implements Serializable {
        private String question;
        private String answer;
//        private String Uuid;

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

//        public String getUuid() {
//            return Uuid;
//        }
//        public void setUuid(String uuid) {
//            Uuid = uuid;
//        }
}


