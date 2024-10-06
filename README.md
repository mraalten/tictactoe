# This application is an old-fashioned game of TicTacToe

## Running the application
* Check out this project and make sure to use JDK 21 (or later).
* Make sure that [Maven](https://maven.apache.org/) is installed to build the application and download all the necessary dependencies   
* Open een command line and in the root directory execute the command `mvn clean install`
* If the build ran successfully run the Main class from an IDE (eg. IntelliJ) or command line (from the root-directory) with the command `java -cp target/classes nl.aalten.dojo.tictactoe.Main`

## Considerations during development
* Separate UI from Domain
* Work TDD, write tests first to fail, then write the implementation to fix the test
* Due to the amount of time to develop only the domain will be unit-tested

## Future implementations
* Smart first move determination (current situation is left top corner when board is empty)
* Intelligence level setting: now all possible win-lines will be evaluated for best next move; the result is that often there is no winner.  
  When setting the intelligence level lower chances for a winner will increase.
