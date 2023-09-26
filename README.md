# Evaluación 1.

## Team members
- Rodrigo Brevis <r.brevis01@ufromail.cl> <br>
- Jorge Cortez <j.cortez01@ufromail.cl>

## Description
API Rest developed with Java 17 and Spring Boot. The code consists of 2 queries, which are:

- Search for a video game by name.
- Recommend 2 games for one specific console.

The code works with 3 JSON type files which refer to 3 consoles respectively, these consoles are:

- GBA
- N64
- PS2

These files are be saved in the following path:

> src/main/java/cl/ufro/dci/evaluacion1/utils

## How to use

- The code runs in a Java IDE. The program will automatically load the data obtained from 3 JSON files into the database (H2).
You can consult the complete list of data with the following route:
> http://localhost:8080/

- To consult a video game you must indicate the full name of the video game. You should keep in mind that, if the name has more than 1 word, you must add spaces between the words. The console and the genre will be displayed using the following path::
> http://localhost:8080/games?name={name}

- To make a recommendation of 2 video games for a console, you must enter one of the names of the consoles mentioned above. The name of the video game, the console and the genre will be displayed using the following path:
> http://localhost:8080/consoles/{console_abreviation}/random_games