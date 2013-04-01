package org.specs2.scalaz

import scalaz._

import org.specs2.Specification

class ValidationMatchersSpec extends Specification with ValidationMatchers {
  def is =
    "beSuccessful matches Success" ! successMatches ^
    "beFailing matches Failure"    ! failureMatches

  def successMatches =
    (Scalaz.success(3) must     beSuccessful)     and
    (Scalaz.success(3) must     beSuccessful(3))  and
    (Scalaz.failure(3) must not beSuccessful)     and
    (Scalaz.success(3) must     be successful)    and
    (Scalaz.success(3) must     be successful(3)) and
    (Scalaz.failure(3) must not be successful)

  def failureMatches =
    (Scalaz.success(3) must not beFailing)     and
    (Scalaz.failure(3) must     beFailing)     and
    (Scalaz.failure(3) must     beFailing(3))  and
    (Scalaz.success(3) must not be failing)    and
    (Scalaz.failure(3) must     be failing)    and
    (Scalaz.failure(3) must     be failing(3))
}

// vim: expandtab:ts=2:sw=2
