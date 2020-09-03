# othello-core

othello-core defines an API for bots that play [Othello](https://www.worldothello.org/about/about-othello/othello-rules/official-rules/english) and allows you to measure how these bots fare against each other.

**THIS README SERVES AS A PLAN FOR NOW, PARTS OF THIS DOCUMENTATION HAVE NOT BEEN IMPLEMENTED YET**

## API //TODO

Bots are implemented as a single Java class that implements the interface `io.github.vuolen.othello.api.OthelloBot`.

Each call to your bot has a *one second timeout*, failing to meet this limit automatically disqualifies your bot.

### void startGame()
Called at the start of the game, use this method to initialize your bot.

### int[] makeMove(int[][] board)
Called each time it's your bot's turn to make a move. The possible values of the board are OthelloBot.EMPTY, OthelloBot.PLAYER, OthelloBot.OPPONENT.
The return value is simply a two-element array containing the indices of the tile where you want to position your next piece. Please note that returning a move that is not valid results in your bot being disqualified.

## Creating your own bot / workflow //TODO

1. Clone this project to your local machine
2. Add your own class that conforms to the API to the folder `src/main/java/io/github/vuolen/othello/bots`
3. Build the project with `mvn package`
4. Run the created jar. See the CLI documentation.

## CLI / How to run //TODO

`òthello-core Bot1ClassName [Bot2ClassName]`

If you specify two bots, the program will play these bots against eachother. If you only specify one, you will play against that bot.
