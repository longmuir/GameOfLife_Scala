/**
 * Created by Jamie on 12/06/2014.
 */
class GridSpec extends UnitSpec{

  "possibleNeighbours" should "return stay empty if already empty" in {
    val grid = new Grid()
    assert(grid.aliveNeighbours((1,1)) == Grid())
  }

  it should "return two neighbours for middle cell" in {
    val grid = new Grid((0,0), (1,0), (2,0))
    assert(grid.aliveNeighbours((1,0)) == Grid( (0,0), (2,0)) )
  }

  "neighbourCount" should "return two for middle cell" in {
    val grid = new Grid((0,0), (1,0), (2,0))
    assert(grid.neighbourCount((1,0)) == 2)
  }

  "emptyNeighbours" should "return 10 for two cells together neighbour" in {
    val grid = new Grid((0,0), (1,0))
    assert(grid.emptyNeighbours().size  == 10)
  }

}
