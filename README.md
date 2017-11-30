# 4004-SeleniumPoker
A basic Poker web application to get experience using selenium as a testing method.

Some code taken from the web (spring documentation), see:

https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-websocket-tomcat

Acknowledgments 
Some of this code was taken from Mike's blackjack game: https://github.com/Sacredify/4004-SeleniumBlackjack.git

To build this project
---------------------

Building this project requires git (or download it manually), and maven. 

  1. `git clone https://github.com/CD11/SeleniumPoker`
  2. `cd SelemiumPoker`
  3. `mvn clean install`
  

To run this project
-------------------

Build the project as above to assemble the stand-alone jar.

  1. `java -jar Poker-1.0-SNAPSHOT.jar`

Allow the code to run until you see this message:
  
  `... c.c.poker.PokerApplication       : Started PokerApplication in 4.512 seconds`
  
After that, open any browser and navigate to:

  `http://localhost:8080/`

Running the tests
-----------------

Running the tests (selenium) is best done through maven:

  `mvn clean test`
  
Tests aren't run as part of the build process, although they are compiled for errors.

IMPORTING AND RUNNING WITH ECLIPSE (Eclipse NEON)
----------------------

For the eclipse users, you can:

  1. downloand Selenium poker from the zip or githu clone
  2. `File --> Import --> Maven --> Import existing maven projects --> SeleniumPoker --> pom.xml selected.
  3. From there, you can do whatever. Select the main class (PokerApplication) and run (as application).


How To Play the Game
--------------

  1. Connect to the game
     -> if you are the first person to connect you will be assigned as admin
     -> As admin you can set the number of players and open the loby 
  2. Once enough people have connected you can start the game
  3. All UI Players will play first
  4. When it is your turn the game option buttons will be enabled and you can decide to stay or hit. 
     -> If you hit you need to click on cards to improve them.
     -> When you have clicked all cards you want to imrpove click done.
     -> Note:  Once you click a card you cannot unselect it.
  5. Once Each player has had one turn the system will display the winner and you can disconnect or start the game again.
