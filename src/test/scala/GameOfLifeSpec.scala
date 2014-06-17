import collection.mutable.Stack

/**
 * Created by Jamie on 11/06/2014.
 */
class GameOfLifeSpec extends UnitSpec{

  "next" should "return stay empty if already empty" in {
    val game = new GameOfLife(Grid())
    assert(game.next() == Grid())
  }

  it should "be empty if only one cell dies" in {
    val game = new GameOfLife(Grid((1,1)))
    assert(game.next() == Grid())
  }

  it should "keep stable 4 stable" in {
    val stable4 = Grid((0,0), (0,1),
                      (1,0), (1,1))
    val game = new GameOfLife(stable4)
    assert(game.next() == stable4)
  }

  it should "cause a blinker line of 3 to blink" in {
    val start = Grid((0,0), (0,1), (0,2))
    val expected = Grid((-1,1), (0,1), (1,1))
    val game = new GameOfLife(start)
    assert(game.next() == expected)
  }

  it should "cause a glider formation to glide" in {
    val start = Grid((2, 2),
      (3, 3),
      (1, 4), (2, 4), (3, 4))
    val expected = Grid((1, 3), (3, 3),
      (2, 4), (3, 4),
      (2, 5))
    val game = new GameOfLife(start)
    assert(game.next() == expected)
  }

  it should "cause a glider formation to glide again" in {
    val start = Grid((1, 3), (3, 3),
      (2, 4), (3, 4),
      (2, 5))
    val expected = Grid((3, 3),
      (1, 4), (3, 4),
      (2, 5), (3, 5))
    val game = new GameOfLife(start)
    assert(game.next() == expected)
  }


}
