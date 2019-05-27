# Kalah Game API

This application was built using Gradle and Spring.
To run, `gradle bootRun`.

The application will run in localhost port 8080.

### API

The following API are supported:

`PUT` `/games`: Creates a new game.

`GET` `/games/{id}`: Gets the status of a game.

`PUT` `games/{id}/pits/{pit}`: Performs a move in the game.

### How to Play

Pits are numbered from 1 to 14. Pit 7 and 14 are the kalah pit (house) of players 1 and 2, respectively. It is not possible to move the stones from those pits and empty pits.

Player 1 always starts. To check the the next player, check the game status.




