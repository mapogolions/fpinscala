package io.github.mapogolions.fpinscala.other


object tokenize {
  def apply(xs: List[Char]): List[Token] =
    xs match {
        case Nil => Nil
        case '(' :: t => Token.LParen :: tokenize(t)
        case ')' :: t => Token.RParen :: tokenize(t)
        case '{' :: t => Token.LBrac :: tokenize(t)
        case '}' :: t => Token.RBrac :: tokenize(t)
        case '[' :: t => Token.LSqBrac :: tokenize(t)
        case ']' :: t => Token.RSqBrac :: tokenize(t)
        case _ => sys.error("Invalid symbol")
    }
}

object balanced {
  import Token._
  def apply(input: String): Boolean = {
    @annotation.tailrec
    def loop(tokens: List[Token], stack: List[Token]): Boolean =
      (tokens, stack) match {
        case ((open @ (LParen | LBrac | LSqBrac))::t, _) => loop(t, open :: stack)
        case (RParen::t1, LParen::t2) => loop(t1, t2)
        case (RBrac::t1, LBrac::t2) => loop(t1, t2)
        case (RSqBrac::t1, LSqBrac::t2) => loop(t1, t2)
        case (Nil, Nil) => true
        case _ => false
      }
    loop(tokenize(input.toList), Nil)
  }
}

enum Token {
  case LParen
  case RParen
  case LBrac
  case RBrac
  case LSqBrac
  case RSqBrac
}