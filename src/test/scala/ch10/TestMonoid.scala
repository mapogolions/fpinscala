import org.junit.Test
import org.junit.Assert._


class TestMonoid {
  import io.github.mapogolions.fpinscala.ch10.Monoid._

  @Test
  def TestEndoMonoid: Unit = {
    assertEquals(
      endoMonoid.op({ a: Int => a + 1 }, { a: Int => a * a })(2),
      5
    )
  }

  @Test
  def TestBoolAndMonoid: Unit = {
    assertEquals(boolAndMonoid.op(true, false), true && false)
    assertEquals(boolAndMonoid.op(true, true), true && true)
    assertEquals(boolAndMonoid.op(false, true), false && true)
    assertEquals(boolAndMonoid.op(false, false), false && false)
  }

  @Test
  def TestBoolOrMonoid: Unit = {
    assertEquals(boolOrMonoid.op(true, false), true || false)
    assertEquals(boolOrMonoid.op(true, true), true || true)
    assertEquals(boolOrMonoid.op(false, true), false || true)
    assertEquals(boolOrMonoid.op(false, false), false || false)
  }

  @Test
  def TestOptionMonoid: Unit = {
    assertEquals(optionMonoid.op(Some(10), None), Some(10) orElse None)
    assertEquals(optionMonoid.op(Some(10), Some(-2)), Some(10) orElse Some(-2))
    assertEquals(optionMonoid.op(None, Some(-2)), None orElse Some(-2))
    assertEquals(optionMonoid.op(None, None), None orElse None)
  }

  @Test
  def TestListMonoid: Unit = {
    assertEquals(listMonoid.op(List(1, 2), List(3, 4)), List(1, 2, 3, 4))
    assertEquals(listMonoid.op(List(1, 2), listMonoid.zero), List(1, 2))
    assertEquals(listMonoid.op(listMonoid.zero, List(3, 4)), List(3, 4))    
  }

  @Test
  def TestIntMultiplicatinMonoid: Unit = {
    assertEquals(intMulMonoid.op(2, 2), 4)
    assertEquals(intMulMonoid.op(2, intMulMonoid.zero), 2)
    assertEquals(intMulMonoid.op(intMulMonoid.zero, 2), 2)
  }

  @Test
  def TestIntAdditionMonoid: Unit = {
    assertEquals(intAddMonoid.op(1, 2), 3)
    assertEquals(intAddMonoid.op(10, intAddMonoid.zero), 10)
    assertEquals(intAddMonoid.op(intAddMonoid.zero, 10), 10)
  }

  @Test
  def TestStringMonoid: Unit = {
    assertEquals(stringMonoid.op("ht", "tp"), "http")
    assertEquals(stringMonoid.op("http", stringMonoid.zero), "http")
    assertEquals(stringMonoid.op(stringMonoid.zero, "http"), "http")
  }

  @Test
  def TestAssociativity: Unit = {
    assertEquals(stringMonoid.op("one", stringMonoid.op("two", "three")), "one" + ("two" + "three"))
    assertEquals(stringMonoid.op(stringMonoid.op("one", "two"), "three"), ("one" + "two") + "three")
    assertEquals(intAddMonoid.op(1, intAddMonoid.op(2, 3)), 1 + (2 + 3))
    assertEquals(intAddMonoid.op(intAddMonoid.op(1, 2), 3), (1 + 2) + 3)
    assertEquals(intMulMonoid.op(1, intMulMonoid.op(2, 3)), 1 * (2 * 3))
    assertEquals(intMulMonoid.op(intMulMonoid.op(1, 2), 3), (1 * 2) * 3)
    assertEquals(
      listMonoid.op(List(1), listMonoid.op(List(2), List(3))), 
      List(1) ++ (List(2) ++ List(3))
    )
    assertEquals(
      listMonoid.op(listMonoid.op(List(1), List(2)), List(3)), 
      (List(1) ++ List(2)) ++ List(3)
    )
    assertEquals(boolOrMonoid.op(true, boolOrMonoid.op(true, false)), true || (true || false))
    assertEquals(boolOrMonoid.op(boolOrMonoid.op(true, true), false), (true || true) || false)
    assertEquals(boolAndMonoid.op(true, boolAndMonoid.op(true, false)), true && (true && false))
    assertEquals(boolAndMonoid.op(boolAndMonoid.op(true, true), false), (true && true) && false)
  }
}