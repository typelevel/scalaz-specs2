scalaz-specs2
=============

Specs2 bindings for Scalaz

Usage
-----

This library is currently available for Scala 2.10 only, but support for multiple combinations of Scala/Specs2/Scalaz versions is planned.

To use the latest snapshot, include the following in your `build.sbt`:

```scala
scalaVersion := "2.10.0-RC5"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.typelevel" % "scalaz-specs2" % "0.1-SNAPSHOT" % "test" cross CrossVersion.full
)
```


ScalazMatchers
--------------

The `ScalazMatchers` trait provides simple matchers for:

* Values with an `Equal` typeclass
* Scalaz `Semigroup` and `Monoid` instances
* Scalaz `Validation` instances

With the `ScalazMatchers` trait you can use your `Equal[T]` typeclass instance to check the equality of 2 values:

* `a must beEqualTo(b)`

With the `ScalazMatchers` trait you can use the following ScalaCheck properties:

* `semigroup.isAssociative` checks if a `Semigroup` respects the associativity law
* `monoid.hasZero` checks if a `Monoid` zero value is really a neutral element
* `monoid.isMonoid` checks if a `Monoid` has a zero element and respects the associativity rule

Finally, you can check `Validation` instances for success or failure:

* `1.success must beSuccessful`
* `1.fail must beFailing`

You can verify what's the validated value:

* `1.success must beSuccessful (1)`
* `1.success must not be successful (2)`
* `1.fail must be failing (1)`
* `1.fail must not be failing (2)`

You can also pattern match on this value

* `List(1, 2).success must beSuccessful.like { case 1 :: _ => ok }`
* `List(1, 2).fail must beFailing.like { case 1 :: _ => ok }`
