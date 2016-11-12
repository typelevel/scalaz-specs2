package org.specs2.scalaz

import scala.language.implicitConversions

import org.specs2._
import org.specs2.matcher._
import org.specs2.specification._
import org.specs2.specification.core._
import org.specs2.scalacheck._
import org.specs2.main.{ArgumentsShortcuts, ArgumentsArgs}

import org.scalacheck.{Gen, Arbitrary, Prop, Properties}
import org.scalacheck.util.{FreqMap, Pretty}

/** A minimal version of the Specs2 mutable base class */
trait Spec extends org.specs2.mutable.Spec with ScalaCheck
  with MatchersImplicits with StandardMatchResults
  with ArgumentsShortcuts with ArgumentsArgs {
    
  val ff = fragmentFactory; import ff._
  
  setArguments(fullStackTrace)

  def checkAll(name: String, props: Properties)(implicit p: Parameters, f: FreqMap[Set[Any]] => Pretty) {
    addFragment(text(s"$name  ${props.name} must satisfy"))
    addFragments(Fragments.foreach(props.properties) { case (name, prop) => Fragments(name in check(prop, p, f)) })
  }

  def checkAll(props: Properties)(implicit p: Parameters, f: FreqMap[Set[Any]] => Pretty) {
    addFragment(text(s"${props.name} must satisfy"))
    addFragments(Fragments.foreach(props.properties) { case (name, prop) => Fragments(name in check(prop, p, f)) })
  }

  implicit def enrichProperties(props: Properties) = new {
    def withProp(propName: String, prop: Prop) = new Properties(props.name) {
      for {(name, p) <- props.properties} property(name) = p
      property(propName) = prop
    }
  }

}

// vim: expandtab:ts=2:sw=2
