package com.abahet;

/**
 * Created by abahet on 17/11/18.
 */
public class Cell {

  private final int x;
  private final int y;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Cell of(int x, int y) {
    return new Cell(x, y);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "com.abahet.Cell{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Cell cell = (Cell) o;

    if (x != cell.x) {
      return false;
    }
    return y == cell.y;
  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

}
