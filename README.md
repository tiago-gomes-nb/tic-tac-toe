# tic-tac-toe

A Clojure library designed to implement a simple Tic-Tac-Toe game.

## Usage

To play the game, run the `-main` function. The game will prompt you to enter your move in the format "line column" (e.g., "1 2" for row 1, column 2).

### Packaging
To generate de package, run the following command:

```sh
lein uberjar
```

### Running

To run the game, execute the following command:

```sh
lein run
```

## Functions
### tic-tac-toe.core
process-move!: Processes a player's move, updates the board, and returns the new game state.

play!: Starts and manages the game loop.

-main: Entry point to start the game.

### tic-tac-toe.ports.console
get-entry!: Prompts the user to enter their move.

print-board!: Prints the current state of the game board.

print-result!: Prints the result of the last move and the updated game board.

## License
Copyright © 2024 FIXME  This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0. 