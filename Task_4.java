
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

    class Question {
        String questionText;
        String[] options;
        char correctAnswer;

        public Question(String questionText, String[] options, char correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    public class Task_4{
        private static final int QUESTION_TIME_LIMIT = 15; 
        private static Timer timer;
        private static boolean timeUp;
        private static int score = 0;
        private static int questionIndex = 0;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Question[] questions = {
                    new Question("What is the capital of France?", new String[]{"A. Paris", "B. London", "C. Rome", "D. Berlin"}, 'A'),
                    new Question("What is the largest planet in our solar system?", new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Saturn"}, 'C'),
                    new Question("Who wrote 'Romeo and Juliet'?", new String[]{"A. William Shakespeare", "B. Charles Dickens", "C. J.K. Rowling", "D. Mark Twain"}, 'A')
            };

            for (Question question : questions) {
                askQuestion(scanner, question);
            }

            displayResults(questions);
            scanner.close();
        }

        private static void askQuestion(Scanner scanner, Question question) {
            System.out.println("\nQuestion " + (questionIndex + 1) + ": " + question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            startTimer();
            char userAnswer = '\0';
            if (!timeUp) {
                System.out.print("Enter your answer (A/B/C/D): ");
                userAnswer = scanner.next().toUpperCase().charAt(0);
            }
            stopTimer();

            if (userAnswer == question.correctAnswer) {
                System.out.println("Correct!");
                score++;
            } else if (!timeUp) {
                System.out.println("Incorrect. The correct answer was: " + question.correctAnswer);
            } else {
                System.out.println("Time's up! The correct answer was: " + question.correctAnswer);
            }

            questionIndex++;
        }

        private static void startTimer() {
            timeUp = false;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                    timer.cancel();
                }
            }, QUESTION_TIME_LIMIT * 1000);
        }

        private static void stopTimer() {
            if (timer != null) {
                timer.cancel();
            }
        }

        private static void displayResults(Question[] questions) {
            System.out.println("\n--- Quiz Results ---");
            System.out.println("Final Score: " + score + " out of " + questions.length);
            for (int i = 0; i < questions.length; i++) {
                Question question = questions[i];
                System.out.println("Q" + (i + 1) + ": " + question.questionText);
                System.out.println("Correct Answer: " + question.correctAnswer);
            }
        }
    }


