# othello-core

othello-core defines an API for bots that play [Othello](https://www.worldothello.org/about/about-othello/othello-rules/official-rules/english) and allows you to measure how these bots fare against each other.

**THIS README SERVES AS A PLAN FOR NOW, PARTS OF THIS DOCUMENTATION HAVE NOT BEEN IMPLEMENTED YET**

## API

Bots are implemented as a single Java class that implements the interface `io.github.vuolen.othello.api.OthelloBot`.

Each call to your bot has a *one second timeout*, failing to meet this limit automatically disqualifies your bot. // TODO

### void startGame(int color)
Called at the start of the game, use this method to initialize your bot. The passed argument is either `OthelloBot.BLACK` or `OthelloBot.WHITE` and indicates which discs are yours.

### int[] makeMove(BoardAPI board)
Called each time it's your bot's turn to make a move. The return value is simply a two-element array containing the indices of the tile where you want to position your next piece. Please note that returning a move that is not valid results in your bot being disqualified.

#### BoardAPI

##### int getTile(int x, int y)
The possible return values are `OthelloBot.EMPTY`, `OthelloBot.PLAYER`, `OthelloBot.OPPONENT`.
Uses euclidean coordinate system, x is horizontal, y is vertical.

##### int size()
Returns the dimension of the board (the length of the side of the square)

## Creating your own bot / workflow

1. Clone this project to your local machine
2. Add your own class that conforms to the API to the folder `src/main/java/io/github/vuolen/othello/bots`
3. Build the project with `mvn package`
4. Run the created jar. See the CLI documentation. The default path for the built jar is in `target/`

## CLI / How to run

`java -jar <path-to-jar> [Bot1ClassName [Bot2ClassName]]`

If you specify no bots, the program will run in human vs. human mode.

If you only specify one, you will play against that bot.

If you specify two bots, the program will play these bots against eachother. // TODO
