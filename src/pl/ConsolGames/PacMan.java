package pl.ConsolGames;

import java.io.IOException;
import java.util.Random;


public class PacMan {
  private int xCoordinate = 2;
  private int yCoordinate = 2;
  private char[][] map = {
      {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
      {'X', '*', ' ', ' ', ' ', 'X', ' ', '^', ' ', 'X'},
      {'X', ' ', ' ', ' ', ' ', 'X', '*', ' ', ' ', 'X'},
      {'X', ' ', ' ', ' ', ' ', 'X', 'X', 'X', ' ', 'X'},
      {'X', ' ', 'X', 'X', 'X', 'X', ' ', '^', ' ', 'X'},
      {'X', ' ', 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
      {'X', ' ', ' ', ' ', ' ', '^', ' ', 'X', 'X', 'X'},
      {'X', '^', 'X', ' ', ' ', 'X', ' ', ' ', ' ', 'X'},
      {'X', ' ', 'X', ' ', ' ', 'X', ' ', '*', ' ', 'X'},
      {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
  };


  PacMan() {
  }

  void inputPlayer() {
    map[xCoordinate][yCoordinate] = 'o';
  }

  public void printMap() {
    addColors("Witaj w labiryncie!");
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (j % map[i].length == 0) {
          System.out.println();
        }
        colorChar(map[i][j]);
      }
    }
    addColorGold("Number stars to score: " + countOfStars());
  }

  private void colorChar(char c) {
    String color = Color.CYAN;
    if (c == '*') {
      color = Color.YELLOW_BOLD;
    } else if (c == 'o' || c == '%') {
      color = Color.PURPLE_BOLD;
    } else if(c == '^'){
      color = Color.RED_BOLD;
    }
    System.out.print(color + c + Color.RESET + ' ');
  }

  public void start() throws IOException {
    nextMove();
  }

  private int countOfStars() {
    int addStar = 0;
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        if (map[i][j] == '*') {
          addStar++;
        }
      }
    }
    return addStar;
  }

  private void nextMove() throws IOException {
    if (!playerWins()) {
      inputPlayer();
      this.printMap();
      playerMove();
    }
  }

  private void playerMove() throws IOException {
    addColorBlue("Zmień pozycje: W - do góry, S - na dół, A - w lewo, D - w prawo");
    addColorGreen("Wciśnij 'X' aby zakończyć grę.");
    char c = (char) System.in.read();
    if (c == 'W' || c == 'w') {
      tryToMove(-1, 0);
    } else if (c == 'S' || c == 's') {
      tryToMove(1, 0);
    } else if (c == 'A' || c == 'a') {
      tryToMove(0, -1);
    } else if (c == 'D' || c == 'd') {
      tryToMove(0, 1);
    } else if (c == 'X' || c == 'x') {
      addColorRed("Zakończyłeś grę!");
    } else {
      nextMove();
    }
  }

  private void tryToMove(int x, int y) throws IOException {
    int newxCoordinate = xCoordinate + x;
    int newyCoordinate = yCoordinate + y;
    if (map[newxCoordinate][newyCoordinate] != 'X') {
      map[xCoordinate][yCoordinate] = ' ';
      xCoordinate = newxCoordinate;
      yCoordinate = newyCoordinate;
    }
    if (map[newxCoordinate][newyCoordinate] == '^'){
    suddenDeath();
    }else {
      nextMove();
    }
  }

  private boolean playerWins() {
    if (countOfStars() == 0) {
      addColorRed("Winner!!");
      return true;
    } else {
      return false;
    }
  }

  private void suddenDeath(){
    map[xCoordinate][yCoordinate] = '%';
    printMap();
    addColorRed("You died");
  }

  private void addColorRed(String s) {
    System.out.println(Color.RED + s + Color.RESET);
  }

  private void addColors(String s) {
    Random random = new Random();
    for (int i = 0; i < s.length(); i++) {
      System.out.print(Color.TABLE_BOLD[random.nextInt(8)] + s.charAt(i) + Color.RESET);
    }
  }

  private void addColorGreen(String s) {
    System.out.println(Color.GREEN_BOLD + s + Color.RESET);
  }

  private void addColorGold(String s) {
    System.out.println(Color.YELLOW_BOLD + s + Color.RESET);
  }

  private void addColorBlue(String s) {
    System.out.println(Color.BLUE_BOLD + s + Color.RESET);
  }
}
