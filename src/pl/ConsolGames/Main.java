package pl.ConsolGames;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    TicTacToe game = new TicTacToe();
    game.start();
    PacMan start1 = new PacMan();
    start1.start();
  }
}
