package org.specs2.scalaz

import scalaz._

import org.specs2.Specification

class ValidationMatchersSpec extends Specification with ValidationMatchers {
  def is =
    "beSuccessful matches Success" ! successMatches ^
    "beFailing matches Failure"    ! failureMatches

  def successMatches =
    (Validation.success(3) must     beSuccessful.like{case a => a must_== 3}) and
    (Validation.success(3) must     beSuccessful)     and
    (Validation.success(3) must     beSuccessful(3))  and
    (Validation.failure(3) must not beSuccessful)     and
    (Validation.success(3) must     be successful)    and
    (Validation.success(3) must     be successful(3)) and
    (Validation.failure(3) must not be successful)

  def failureMatches =
    (Validation.failure(3) must     beFailing.like{case a => a must_== 3}) and
    (Validation.success(3) must not beFailing)     and
    (Validation.failure(3) must     beFailing)     and
    (Validation.failure(3) must     beFailing(3))  and
    (Validation.success(3) must not be failing)    and
    (Validation.failure(3) must     be failing)    and
    (Validation.failure(3) must     be failing(3))
}

// vim: expandtab:ts=2:sw=2
