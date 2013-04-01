package org.specs2.scalaz

import scalaz._
import Scalaz._

import org.specs2.matcher._

trait ScalazMatchers extends ValidationMatchers { outer =>

  /** Equality matcher with a [[scalaz.Equal]] typeclass */
  def equal[T : Equal : Show](expected: T): Matcher[T] = new Matcher[T] {
    def apply[S <: T](actual: Expectable[S]): MatchResult[S] = {
      val actualT = actual.value.asInstanceOf[T]
      def test = implicitly[Equal[T]].equal(expected, actualT)
      def koMessage = "%s !== %s".format(actualT.shows, expected.shows)
      def okMessage = "%s === %s".format(actualT.shows, expected.shows)
      Matcher.result(test, okMessage, koMessage, actual)
    }
  }

  class ScalazBeHaveMatchers[T : Equal : Show](result: MatchResult[T]) {
    def equal(t: T) = result(outer.equal[T](t)(implicitly[Equal[T]], implicitly[Show[T]]))
  }

  implicit def scalazBeHaveMatcher[T : Equal : Show](result: MatchResult[T]) = new ScalazBeHaveMatchers(result)

}

// vim: expandtab:ts=2:sw=2
