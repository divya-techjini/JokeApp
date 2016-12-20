package com.example;

import java.util.Random;

public class JokeClass {

    public static String[] JOKES = new String[]{
            "I dreamt I was forced to eat a giant marshmallow. When I woke up, my pillow was gone.",
            "As long as there are tests, there will be prayer in schools.",
            "My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away.",
            "What did one ocean say to the other ocean? Nothing, they just waved.",
            "A day without sunshine is like, night.",
            "A bank is a place that will lend you money, if you can prove that you don’t need it.",
            "IWhat’s the difference between a new husband and a new dog? After a year, the dog is still excited to see you.",
            "Love may be blind, but marriage is a real eye-opener.",
            "I wanted to grow my own food but I couldn’t get bacon seeds anywhere.",
            "I can’t believe I forgot to go to the gym today. That’s 7 years in a row now.",
            "Woke up with a dead leg this morning. I will not take out a loan with the mafia ever again.",
            "The 21st century: Deleting history is often more important than making it.",
            "How do you tell that a crab is drunk? It walks forwards.",
            "Why do cows wear bells? Their horns don’t work.",
            "If you can’t convince them, confuse them.",
            "When everything’s coming your way, you’re in the wrong lane."
            , "Whenever I find the key to success, someone changes the lock."
    };

    private static final Random rndmGenerator = new Random();

    public static String getOneJoke() {
        return JOKES[rndmGenerator.nextInt(JOKES.length)];
    }

}
