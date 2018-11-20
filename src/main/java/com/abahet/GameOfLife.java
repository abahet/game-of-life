package com.abahet;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by abahet on 17/11/18.
 */
public class GameOfLife {

  private final List<Cell> liveCells;

  public GameOfLife(List<Cell> cells) {
    this.liveCells = cells;
  }

  public List<Cell> nextGeneration() {
    List<Cell> survivors = new ArrayList<Cell>();
    for (Cell cell : liveCells) {
      List<Cell> liveNeighborCells = liveNeighbors(cell);
      if (liveNeighborCells.size() == 2 || liveNeighborCells.size() == 3) {
        survivors.add(cell);
      }
    }
    survivors.addAll(reborn());
    return survivors;
  }

  protected Collection<Cell> reborn() {
    Collection<Cell> rebornDeadCells = new HashSet<Cell>();
    Collection<Cell> allDeadCells = deadCells();
    for (Cell deadCell : allDeadCells) {
      if (liveNeighbors(deadCell).size() == 3) {
        rebornDeadCells.add(deadCell);
      }
    }
    return rebornDeadCells;
  }

  protected Collection<Cell> deadCells() {
    Collection<Cell> allDeadCells = new HashSet<Cell>();
    for (Cell liveCell : liveCells) {
      Collection<Cell> deadNeighborCells = deadCells(liveCell);
      allDeadCells.addAll(deadNeighborCells);
    }
    return allDeadCells;
  }

  protected Collection<Cell> deadCells(Cell cell) {
    List<Cell> deadNeighborCells = new ArrayList<Cell>();
    List<Cell> neighbors = neighbors(cell);
    for (Cell neighborCell : neighbors) {
      if (!liveCells.contains(neighborCell)) {
        deadNeighborCells.add(neighborCell);
      }
    }
    return deadNeighborCells;
  }

  public List<Cell> liveNeighbors(Cell cell) {
    List<Cell> liveNeighborCells = new ArrayList<Cell>();
    List<Cell> neighbors = neighbors(cell);
    for (Cell neighborCell : neighbors) {
      if (liveCells.contains(neighborCell)) {
        liveNeighborCells.add(neighborCell);
      }
    }
    return liveNeighborCells;
  }

  public List<Cell> neighbors(Cell cell) {
    List<Cell> neighborsPositions = asList(Cell.of(-1, -1), Cell.of(-1, 0), Cell.of(-1, 1),
        Cell.of(0, -1), Cell.of(0, 1),
        Cell.of(1, -1), Cell.of(1, 0), Cell.of(1, 1));
    List<Cell> neighborCells = new ArrayList<Cell>();
    for (Cell neighborCell : neighborsPositions) {
      Cell neighbor = Cell.of(neighborCell.getX() + cell.getX(), neighborCell.getY() + cell.getY());
      if (neighbor.getX() < 0) {
        continue;
      }
      if (neighbor.getY() < 0) {
        continue;
      }
      neighborCells.add(neighbor);
    }
    return neighborCells;
  }
}
