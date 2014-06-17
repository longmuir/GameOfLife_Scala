import org.scalatest._

/**
 * Created by Jamie on 11/06/2014.
 */
abstract class UnitSpec extends FlatSpec with Matchers with
  OptionValues with Inside with Inspectors
