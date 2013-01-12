package org.specs2.scalaz

import scalaz.{Semigroup, Monoid, Validation, Success, Failure, Scalaz, Equal}
import scalaz.syntax.monoid._

import org.scalacheck.{ Arbitrary, Shrink, Prop }

import org.specs2.matcher._
import org.specs2.matcher.MatchResultLogicalCombinators._
import org.specs2.execute.ResultLogicalCombinators._

/**
 * This trait provides matchers for some Scalaz (http://github.com/scalaz) types.
 */
trait ScalazMatchers extends ScalazBaseMatchers with ScalazBeHaveMatchers { outer: AnyMatchers => }

trait ScalazBaseMatchers extends ScalaCheckMatchers with Expectations with ValidationMatchers { outer: AnyMatchers =>

  /** equality matcher with an Equal typeclass */
  def equal[T : Equal](t: T): Matcher[T] = beTypedEqualTo(t, implicitly[Equal[T]].equal(_:T,_:T))

}

trait ScalazBeHaveMatchers { outer: ScalazMatchers with AnyMatchers =>
  implicit def scalazBeHaveMatcher[T : Equal](result: MatchResult[T]) = new ScalazBeHaveMatchers(result)
  class ScalazBeHaveMatchers[T : Equal](result: MatchResult[T]) {
    def equal(t: T) = result(outer.equal(t))
  }
}
