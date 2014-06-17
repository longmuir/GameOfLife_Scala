/**
 * Created by Jamie on 11/06/2014.
 */
class GameOfLife(val grid: Grid) {

  def next(): Grid = {
    survivors() ++ births()
  }

  def survivors(): Grid = {
    //for all the items in the grid
    //return a new grid containing only those that have 2-3 neighbours
    grid.filter( cell => List(2,3) contains grid.neighbourCount(cell) )
  }
  
  def births(): Grid = {
    grid.emptyNeighbours().filter( cell => grid.neighbourCount(cell) == 3 )
  }

}
