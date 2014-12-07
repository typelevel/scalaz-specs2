package org.specs2
package scalaz

import org.specs2.execute.StandardResults
import org.specs2.matcher._
import org.specs2.specification._
import core._
import org.specs2.main.{ArgumentsShortcuts, ArgumentsArgs}

import org.scalacheck.{Gen, Arbitrary, Prop, Properties}
import org.specs2.specification.create.FormattingFragments
import org.specs2.specification.dsl.mutable.{ExampleDsl0, ArgumentsCreation, TextCreation, ActionDsl}

// Lazy code duplication from scalaz-tests

/** A lighter version of the Specs2 mutable base class */
abstract class Spec extends org.specs2.specification.core.mutable.SpecificationStructure with ScalaCheckMatchers
  with ExampleDsl0
  with TextCreation
  with ActionDsl
  with MustThrownExpectations
  with MatchersImplicits
  with FormattingFragments
  with StandardResults with StandardMatchResults
  with ArgumentsCreation with ArgumentsShortcuts with ArgumentsArgs {
    
  private val ff = fragmentFactory; import ff._
  
  setArguments(fullStackTrace)

  addFragment(break)

  def checkAll(name: String, props: Properties)(implicit p: Parameters) {
    addFragment(text(s"$name ${props.name} must satisfy"))
    addFragment(break)
    props.properties.foreach { case (n, prop) =>
      addFragment(example(n, check(prop)(p)))
      addFragment(break)
    }
    addFragment(break)
  }

  def checkAll(props: Properties)(implicit p: Parameters) {
    addFragment(text(s"${props.name} must satisfy"))
    addFragment(break)
    props.properties.foreach { case (n, prop) =>
      addFragment(example(n, check(prop)(p)))
      addFragment(break)
    }
    addFragment(break)
  }

  import scala.language.implicitConversions
  implicit def enrichProperties(props: Properties) = new {
    def withProp(propName: String, prop: Prop) = new Properties(props.name) {
      for {(name, p) <- props.properties} property(name) = p
      property(propName) = prop
    }
  }

  /**
   * Most of our scalacheck tests use (Int => Int). This generator includes non-constant
   * functions (id, inc), to have a better chance at catching bugs.
   */
  implicit def Function1IntInt[A](implicit A: Arbitrary[Int]): Arbitrary[Int => Int] =
    Arbitrary(Gen.frequency[Int => Int](
      (1, Gen.const((x: Int) => x)),
      (1, Gen.const((x: Int) => x + 1)),
      (3, A.arbitrary.map(a => (_: Int) => a))
    ))
}

// vim: expandtab:ts=2:sw=2
