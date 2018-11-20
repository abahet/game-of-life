package com.abahet;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import org.junit.Test;

/**
 * Created by abahet on 17/11/18.
 */
public class GameOfLifeTest {

  public static final List<Cell> SINGLE_LIVE_CELL_CENTER = asList(Cell.of(1, 1));
  private GameOfLife gameOfLife;

  //The Rules
  //1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
  //2. Any live cell with two or three live neighbours lives on to the next generation.
  //3. Any live cell with more than three live neighbours dies, as if by overpopulation.
  //4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.


  @Test //Rule 1 : Any live cell with fewer than two live neighbours dies, as if caused by under-population.
  public void anyLiveCellWithOneOrNoNeighborsDies() {
    //Given
    GameOfLife gameOfLife = new GameOfLife(SINGLE_LIVE_CELL_CENTER);

    //When
    List<Cell> survivors = gameOfLife.nextGeneration();

    //Then
    assertEquals(survivors.size(), 0);
  }


  @Test //Rule 2 : Any live cell with two or three live neighbours lives on to the next generation.
  public void anyLiveCellWithTwoOrThreeNeighborsSurvives() {
    //Given
    gameOfLife = new GameOfLife(asList(Cell.of(0, 1), Cell.of(1, 1), Cell.of(1, 2)));

    //When
    List<Cell> survivors = gameOfLife.nextGeneration();

    //Then
    assertEquals(survivors, asList(Cell.of(0, 1), Cell.of(1, 1), Cell.of(1, 2), Cell.of(0, 2)));
  }

  @Test //Rule 3 : Any live cell with more than three live neighbours dies, as if by overpopulation.
  public void anyLiveCellWithMoreThanThreeLiveNeighborsDies() {
    //Given
    gameOfLife = new GameOfLife(asList(Cell.of(0, 0), Cell.of(0, 2), Cell.of(1, 1), Cell.of(2, 0), Cell.of(2, 2)));

    //When
    Collection<Cell> survivors = gameOfLife.nextGeneration();

    //Then
    assertFalse(survivors.containsAll(asList(Cell.of(1, 1))));
  }


  @Test //Rule 4 : Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
  public void anyDeadCellWithThreeLiveNeighborsMustReborn() {
    //Given
    gameOfLife = new GameOfLife(asList(Cell.of(0, 1), Cell.of(0, 2), Cell.of(1, 2)));

    //When
    List<Cell> survivors = gameOfLife.nextGeneration();

    //Then
    assertEquals(survivors, asList(Cell.of(0, 1), Cell.of(0, 2), Cell.of(1, 2), Cell.of(1, 1)));
  }

  @Test
  public void rebornCellWithThreeLiveNeighbors() {
    //Given
    gameOfLife = new GameOfLife(asList(Cell.of(0, 1), Cell.of(0, 2), Cell.of(1, 2)));

    //When
    Collection<Cell> rebornCells = gameOfLife.reborn();

    //Then
    assertTrue(rebornCells.containsAll(asList(Cell.of(1, 1))));
  }

  @Test
  public void shouldHave8DeadCells() {
    //Given
    gameOfLife = new GameOfLife(asList(Cell.of(0, 1), Cell.of(0, 2), Cell.of(1, 2)));

    //When
    Collection<Cell> deadCells = gameOfLife.deadCells();

    //Then
    assertTrue(deadCells.containsAll(asList(Cell.of(0, 0), Cell.of(1, 1), Cell.of(2, 2),
        Cell.of(2, 3), Cell.of(1, 3), Cell.of(0, 3),
        Cell.of(1, 0), Cell.of(2, 1))));
  }


  @Test
  public void shouldHaveLiveNeighbors() {
    //Given
    GameOfLife gameOfLife = new GameOfLife(SINGLE_LIVE_CELL_CENTER);

    //Then
    assertEquals(gameOfLife.liveNeighbors(Cell.of(0, 0)), SINGLE_LIVE_CELL_CENTER);
  }

  @Test
  public void shouldHave8Neighbors() {
    //Given
    GameOfLife gameOfLife = new GameOfLife(SINGLE_LIVE_CELL_CENTER);

    //When
    List<Cell> neighborCells = asList(
        Cell.of(0, 0), Cell.of(0, 1), Cell.of(0, 2),
        Cell.of(1, 0), Cell.of(1, 2),
        Cell.of(2, 0), Cell.of(2, 1), Cell.of(2, 2)
    );

    //Then
    assertEquals(gameOfLife.neighbors(Cell.of(1, 1)), neighborCells);
  }
}
