package org.specs2.scalaz

import scalaz.\/

import org.specs2.Specification

class DisjunctionMatchersSpec extends Specification with DisjunctionMatchers { def is = s2"""
  beRightDisjunction should
    match \/-                     $rightMatches

  beLeftDisjunction should
    match -\/                     $leftMatches
  """

  def rightMatches =
    (\/.right(3) must     beRightDisjunction.like{
      case a => a must_== 3
    })                                            and
    (\/.right(3) must     beRightDisjunction)     and
    (\/.right(3) must     beRightDisjunction(3))  and
    (\/.left(3)  must not beRightDisjunction)     and
    (\/.right(4) must not beRightDisjunction(3))  and
    (\/.right(3) must     be rightDisjunction)    and
    (\/.right(3) must     be rightDisjunction(3)) and
    (\/.left(3)  must not be rightDisjunction)    and
    (\/.right(4) must not be rightDisjunction(3)) and
    (\/.right(3) must     be_\/-)                 and
    (\/.right(3) must     be_\/-(3))              and
    (\/.left(3)  must not be_\/-)                 and
    (\/.right(4) must not be_\/-(3))

  def leftMatches =
    (\/.left(3)  must     beLeftDisjunction.like{
      case a => a must_== 3
    }) and
    (\/.right(3) must not beLeftDisjunction)     and
    (\/.left(3)  must     beLeftDisjunction)     and
    (\/.left(3)  must     beLeftDisjunction(3))  and
    (\/.left(4)  must not beLeftDisjunction(3))  and
    (\/.right(3) must not be leftDisjunction)    and
    (\/.left(3)  must     be leftDisjunction)    and
    (\/.left(3)  must     be leftDisjunction(3)) and
    (\/.left(4)  must not be leftDisjunction(3)) and
    (\/.right(3) must not be_-\/)                and
    (\/.left(3)  must     be_-\/)                and
    (\/.left(3)  must     be_-\/(3))             and
    (\/.left(4)  must not be_-\/(3))
}

// vim: expandtab:ts=2:sw=2
