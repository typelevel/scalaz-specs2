package org.specs2.scalaz

import scalaz.{Semigroup, Monoid, Validation, Success, Failure, Scalaz, Equal, Show}
import scalaz.syntax.monoid._

import org.scalacheck.{ Arbitrary, Shrink, Prop }

import org.specs2.matcher._
import org.specs2.matcher.MatchResultLogicalCombinators._
import org.specs2.execute.ResultLogicalCombinators._

/**
 * This trait provides matchers for some Scalaz (http://github.com/scalaz) types.
 */
trait ScalazMatchers extends ScalaCheckMatchers with Expectations with ValidationMatchers { outer: AnyMatchers =>
  /** Equality matcher with an Equal typeclass */
  def equal[T : Equal : Show](expected: T): Matcher[T] = new Matcher[T] {
    def apply[S <: T](actual: Expectable[S]): MatchResult[S] = {
      val actualT = actual.value.asInstanceOf[T]
      def test = Equal[T].equal(expected, actualT)
      def koMessage = "%s !== %s".format(Show[T].shows(actualT), Show[T].shows(expected))
      def okMessage = "%s === %s".format(Show[T].shows(actualT), Show[T].shows(expected))
      Matcher.result(test, okMessage, koMessage, actual)
    }
  }

  class ScalazBeHaveMatchers[T : Equal : Show](result: MatchResult[T]) {
    def equal(t: T) = result(outer.equal[T](t)(Equal[T], Show[T]))
  }

  implicit def scalazBeHaveMatcher[T : Equal : Show](result: MatchResult[T]) = new ScalazBeHaveMatchers(result)
}
