package org.specs2.scalaz

import org.specs2.Specification

class ScalazMatchersSpec extends Specification with ScalazMatchers { def is = s2"""
  equal[T : Equal : Show] should
    match via Equal[T]                $equalMatches"""

  import scalaz.{Scalaz, Equal, Show}
  import scalaz.Equal._
  import scalaz.Show._
  import scalaz.std.string._

  class Foo(val bar: String)
  implicit val EqualFoo: Equal[Foo] = equalBy(_.bar)
  implicit val ShowFoo: Show[Foo] = shows(foo => "Foo(%s)".format(foo.bar))

  def equalMatches =
    (new Foo("asdf") must not  be_==(new Foo("asdf"))) and
    (new Foo("asdf") must not be_===(new Foo("asdf"))) and
    (new Foo("asdf") must      equal(new Foo("asdf"))) and
    (new Foo("asdf") must   be equal(new Foo("asdf")))
}

// vim: expandtab:ts=2:sw=2
