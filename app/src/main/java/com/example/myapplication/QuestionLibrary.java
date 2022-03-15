package com.example.myapplication;

public class QuestionLibrary {

    private String mQuestions [] = {

            //Data Primitives Questions
            "1. How many primitive data types are there?",
            "2. We use primitive data types to: ",
            "3. Integers are decimal numbers",
            "4. We can define characters as: \n\t\t char character = 'a';",

            //Introduction Questions
            "1. The name of our application is:",
            "2. In our application we teach:",
            "3. Defining a class is mandatory in java",
            "4. System.out.println(Hi) prints Hi",

            //Conditionals Questions
            "1. int value = 2;\n\t\t\t\tif(value>1)\n\t\t\t\tSystem.out.print(\"2 is greater than 1\");",
            "2. We need curly braces to include more \n\t\t\t than 1 statement in the if-condition",
            "3. int a = 10;  \n\t\t\t\t if(a<=10) \n\t\t\t\t System.out.print(a);",
            "4. else negates the condition of \n\t\tif-statement ",

            //Loops Questions
            "1. We use loops to:",
            "2. if-statement is a loop",
            "3. while (true) \n \t\t System.out.print(\"I love Java Cracker\");",
            "4. for(int i=2;i<4;i++)\n\t\t\t\tSystem.out.print(\"I love Java Crackers\"); \n\t\t\t\t This will get printed ___ times?"
    };


    private String mChoices [][] = {

            //Data Primitives Solutions
            {"7", "8", "9"},
            {"conduct data analysis", "define our classes", "define our variables"},
            {"true", "false", "not sure"},
            {"true", "false", "not sure"},

            //Introduction Solutions
            {"Java Crackers", "Let's Crack Java", "Java"},
            {"Java", "Python", "JavaScript"},
            {"true", "false", "not sure"},
            {"true", "false", "not sure"},

            //Conditionals Solutions
            {"2 is greater than 1", "1 is greater than 2", "1 is equal to 2"},
            {"true", "false", "not sure"},
            {"10", "100", "a"},
            {"true", "false", "not sure"},

            //Loops Solutions
            {"Execute things more than once", "Checks a condition", "checking things"},
            {"true", "false", "not sure"},
            {"I will keep loving JavaCracker forever", "I'll study Java forever", "not sure"},
            {"2", "3", "4"},
    };

    private String mCorrectAnswers[] = {"8", "define our variables", "false", "true",
            "Java Crackers","Java","true", "false",
            "2 is greater than 1","true","10","true",
            "Execute things more than once", "false", "I will keep loving JavaCracker forever","2" };


    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}