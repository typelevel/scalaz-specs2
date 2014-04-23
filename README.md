scalaz-specs2
=============

Specs2 bindings for Scalaz

[![Build Status](https://travis-ci.org/typelevel/scalaz-specs2.png?branch=master)](http://travis-ci.org/typelevel/scalaz-specs2)

Usage
-----

This library is currently available for Scala binary versions 2.10 and 2.11.

To use the latest version, include one of the following in your `build.sbt`:

```scala
// for Scalaz 7.0.x and specs2 2.3
libraryDependencies += "org.typelevel" %% "scalaz-specs2" % "0.2" % "test"
```


ScalazMatchers
--------------

With the `ScalazMatchers` trait you can use your `Equal[T]` typeclass instance to check the equality of 2 values:

* `a must equal(b)`

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
