scalaz-specs2
=============

Specs2 bindings for Scalaz

Usage
-----

This library is currently available for all combinations of the following:

* Scala 2.9.2 and 2.10
* specs2 1.12.3
* scalaz 6.0.4 and 7.0.0-M9

To use the latest version, include one of the following in your `build.sbt`:

```scala
// for 7.0.0-M9
libraryDependencies += "org.typelevel" %% "scalaz-specs2" % "0.1.2" % "test"

// for 6.0.4
libraryDependencies += "org.typelevel" %% "scalaz6-specs2" % "0.1" % "test"
```


ScalazMatchers
--------------

With the `ScalazMatchers` trait you can use your `Equal[T]` typeclass instance to check the equality of 2 values:

* `a must beEqualTo(b)`

### Features specific to 6.0.x

With the `ScalazMatchers` trait you can use the following ScalaCheck properties:

* `semigroup.isAssociative` checks if a `Semigroup` respects the associativity law
* `monoid.hasZero` checks if a `Monoid` zero value is really a neutral element
* `monoid.isMonoid` checks if a `Monoid` has a zero element and respects the associativity rule

This feature is not enabled for 7.0.x, since there is another, more general law checking mechanism in place (see [below](#spec)).

ValidationMatchers
------------------

With `ValidationMatchers`, you can check `Validation` instances for success or failure:

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

Spec
----

### Features specific to 7.0.x

The following example illustrates how to verify type class instances for your own datatypes under the assumption that they are in scope.

```scala
class TryTest extends Spec {

  implicit def TryArbitrary[A](implicit a: Arbitrary[A]): Arbitrary[Try[A]] =
    // ...

  checkAll(monad.laws[Try])
  checkAll(traverse.laws[Try])
  checkAll(plus.laws[Try])
  checkAll(equal.laws[Try[Int]])

}
```

Internally, it uses ScalaCheck to generate input data.
