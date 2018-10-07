import org.junit.Test
import org.junit.Assert._
import io.github.mapogolions.fpinscala._

class Test1 {
  @Test def t1(): Unit = {
    assertEquals("I was compiled by dotty :)", Main.msg)
  }
}
