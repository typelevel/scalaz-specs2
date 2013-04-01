package org.specs2.scalaz

import org.specs2.Specification

class ScalazMatchersSpec extends Specification with ScalazMatchers {
  import scalaz._
  import Scalaz._

  def is =
    "equal[T : Equal : Show] matches via Equal[T]" ! equalMatches

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
