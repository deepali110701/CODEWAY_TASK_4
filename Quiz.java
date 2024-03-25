package com.codeway;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

class Question 
{
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) 
    {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public String getQuestion() 
    {
        return question;
    }
    public List<String> getOptions() 
    {
        return options;
    }
    public int getCorrectOptionIndex() 
    {
        return correctOptionIndex;
    }
}

public class Quiz 
{
    private List<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public Quiz(List<Question> questions) 
    {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public void start() 
    {
        timer = new Timer();
        timer.schedule(new TimerTask() 
        {
            @Override
            public void run() 
            {
                System.out.println("Time's up!");
                goToNextQuestion();
            }
        }, 60000); // 60 seconds timer for each question

        displayCurrentQuestion();
        
    }

    private void displayCurrentQuestion() 
    {
        Question currentQuestion = questions.get(currentQuestionIndex);
        System.out.println("Question: " + currentQuestion.getQuestion());
        List<String> options = currentQuestion.getOptions();
        for (int i = 0; i < options.size(); i++) 
        {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int userChoice = scanner.nextInt();

        if (userChoice == currentQuestion.getCorrectOptionIndex() + 1) 
        {
            System.out.println("Correct!");
            score++;
        } 
        else 
        {
            System.out.println("Incorrect!");
        }

        goToNextQuestion();
    }

    private void goToNextQuestion() 
    {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) 
        {
            displayCurrentQuestion();
        } 
        else 
        {
            endQuiz();
        }
    }

    private void endQuiz() 
    {
        timer.cancel();
        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) 
    {
        List<Question> questions = new ArrayList<>();
        
        questions.add(new Question("What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Rome"), 1));
        questions.add(new Question("What is the largest planet in our solar system?",
                List.of("Venus", "Mars", "Jupiter", "Saturn"), 3));
        questions.add(new Question("What is the chemical symbol for water?",
                List.of("H2O", "CO2", "O2", "H2SO4"), 0));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?",
                List.of("William Shakespeare", "Jane Austen", "Charles Dickens", "Mark Twain"), 0));
        questions.add(new Question("What is the national animal of Australia?",
                List.of("Kangaroo", "Koala", "Platypus", "Emu"), 0));
        questions.add(new Question("Which planet is known as the 'Red Planet'?",
                List.of("Venus", "Mars", "Jupiter", "Saturn"), 1));
        questions.add(new Question("What is the tallest mammal?",
                List.of("Elephant", "Giraffe", "Whale", "Horse"), 1));
        questions.add(new Question("What is the chemical symbol for gold?",
                List.of("Au", "Ag", "Fe", "Cu"), 0));
        questions.add(new Question("What is the largest ocean on Earth?",
                List.of("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"), 3));
        questions.add(new Question("Who painted the Mona Lisa?",
                List.of("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"), 1));        
        
        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
