package pl.ConsolGames;

import java.util.Scanner;

public class TicTacToe {
  private char[] map = new char[9];
  private char nought = 'O';
  private char cross = 'X';
  private boolean crossFirst = true;

  TicTacToe() {
    for (int i = 0; i < 9; i++) {
      map[i] = Character.forDigit(i, 10);
    }
  }

  private void nextMove() {

    this.printMap();
    crossFirst = !crossFirst;
    char player = crossFirst ? nought : cross;
    playerMove(player);
  }

  private void playerMove(char player) {
    Scanner scanner = new Scanner(System.in);
    int position = 0;
    System.out.println("Podaj pozycje gracza: " + colorChar(player));
    if (scanner.hasNextInt()) {
      position = scanner.nextInt();
    } else {
      printAlert("Wybierz jedną z widocznych cyfr");
      playerMove(player);
    }
    if (position < map.length && position >= 0) {
      if (map[position] == Character.forDigit(position, 10)) {
        map[position] = player;
        if (playerWins(player)) {
          printMap();
          System.out.println("Wygrał gracz " + colorChar(player));
        } else {
          if (end()) {
            for (int i = 0; i < 9; i++) {
              map[i] = Character.forDigit(i, 10);
            }
          }
          nextMove();
        }
      } else {
        printAlert("Wybierz wole pole.");
        playerMove(player);
      }
    } else {
      printAlert("Wybierz jedna z widocznych cyfr");
      playerMove(player);
    }
  }

  public void printMap() {
    for (int i = 0; i < 5; i++) {
      System.out.println();
    }
    for (int i = 0; i < 9; i++) {
      System.out.print(colorChar(map[i]) + " ");
      if ((i + 1) % 3 == 0) {
        System.out.println();
      }
    }
  }

  private void printAlert(String s) {
    System.out.println(Color.RED + " (i) : " + s + Color.RESET + " ");
  }


  public void start() {
    nextMove();
  }

  private String colorChar(char c) {
    String color = Color.RESET;
    if (c == nought) {
      color = Color.RED;
    }
    if (c == cross) {
      color = Color.BLUE;
    }
    return color + c + Color.RESET + " ";
  }

  private boolean playerWins(char player) {
    for (int i = 0; i < 3; i++) {
      if (compareChars(player, map[i], map[1 + i], map[2 + i])) {
        return true;
      }
      if (compareChars(player, map[i], map[3 + i], map[6 + i])) {
        return true;
      }
    }
    if (compareChars(player, map[0], map[4], map[8])) {
      return true;
    }
    if (compareChars(player, map[2], map[4], map[6])) {
      return true;
    }
    return false;
  }

  private boolean compareChars(char a, char m1, char m2, char m3) {
    if (a == m1) {
      if (a == m2) {
        if (a == m3) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  private boolean end() {
    for (int i = 0; i < 9; i++) {
      if (map[i] == Integer.toString(i).charAt(0)) {
        return false;
      }
    }
    return true;
  }
}
