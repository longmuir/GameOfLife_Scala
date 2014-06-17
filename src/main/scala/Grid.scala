import scala.collection.SetLike
import scala.collection.generic.CanBuildFrom
import scala.collection.mutable
import scalaz._, Scalaz._
/**
 * Created by Jamie on 12/06/2014.
 * Grid is a concrete implementation of Set that adds methods for calculating neighbours.
 * This was created to learn how to create new collections from scratch in Scala.
 */

class Grid(gridSeq: (Int, Int)*) extends Set[(Int,Int)]
                                       with SetLike[(Int,Int), Grid]  {
  import Grid._

  override def empty: Grid = new Grid()
  def +(elem: (Int,Int)) : Grid = if (gridSeq contains elem) this else new Grid(elem +: gridSeq: _*)
  def -(elem: (Int,Int)) : Grid = if (gridSeq contains elem) new Grid(gridSeq filterNot(elem ==): _*) else this
  def contains(elem: (Int,Int)) : Boolean = gridSeq contains elem
  def iterator: Iterator[(Int, Int)] = gridSeq.iterator

  def neighbourCount(cell:(Int, Int)): Int = {
    aliveNeighbours(cell).size
  }

  def aliveNeighbours(cell:(Int, Int)): Grid = {
    val candidateNeighbours : Grid = NeighbourDeltas.map(_ |+| cell)
    candidateNeighbours.filter(candidate => this contains candidate)
  }

  def emptyNeighbours(): Grid = {
    foldLeft(empty) { (emptyNeighbours, cell) =>
      val candidateNeighbours = NeighbourDeltas.map(_ |+| cell)
      emptyNeighbours ++ candidateNeighbours.filterNot(candidate => this contains candidate)
    }
  }

}

object Grid {
  private val NeighbourDeltas = Grid((-1,-1),(0,-1),(1,-1),(-1, 0),       (1, 0),(-1, 1),(0, 1),(1, 1))

  def empty: Grid = new Grid()

  //newBuilder is necessary so that methods such as .filter and .foreach return type Grid
  def newBuilder: mutable.Builder[(Int,Int), Grid] = new mutable.SetBuilder[(Int,Int), Grid](empty)

  //Apply is added so that we can create a new Grid like Grid((1,1)) and not have to use 'new'
  def apply(cells: (Int,Int)*): Grid = (empty /: cells) (_ + _)

  //So that map will return a Grid object when required
  implicit def canBuildFrom : CanBuildFrom[Grid,(Int, Int), Grid] =
    new CanBuildFrom[Grid,(Int, Int), Grid] {
      def apply(): mutable.Builder[(Int,Int), Grid] = newBuilder
      def apply(from: Grid): mutable.Builder[(Int, Int), Grid] = newBuilder
    }

}
