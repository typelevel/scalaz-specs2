scalaz-specs2
=============

Specs2 bindings for Scalaz

[![Build Status](https://travis-ci.org/typelevel/scalaz-specs2.png?branch=master)](http://travis-ci.org/typelevel/scalaz-specs2)

Usage
-----

This library is currently available for Scala binary versions 2.10, 2.11, and 2.12.

To use the latest version, include the following dependency in your `build.sbt`:

```scala
// for Scalaz 7.2.x and specs2 3.9.x
libraryDependencies += "org.typelevel" %% "scalaz-specs2" % "0.5.0" % "test"
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

DisjunctionMatchers
------------------

With `DisjunctionMatchers`, you can check `\/` instances for left or right:

The below examples are based on this type:

* `val res: String \/ Int = ...`

You can verify what's the value:

* `res must be_\/-.which { r => r must_== 3  }`
* `res must be_-\/.which { l => l must_== "foo "}`

You can also pattern match on this value

* `res must be_\/-.like { case 3 => ok }`
* `res must be_-\/.like { case "foo" => Ok }`

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
