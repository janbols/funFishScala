package com.github.janbols.funfish.limited

import com.github.janbols.funfish.limited.Picture._

object Limited {


  def ttile(f: Picture): Picture = {
    val fishN = f |> toss |> flip
    val fishE = fishN |> turn |> turn |> turn
    over(f, over(fishN, fishE))
  }

  def utile(f: Picture): Picture = {
    val fishN = f |> toss |> flip
    val fishW = fishN |> turn
    val fishS = fishW |> turn
    val fishE = fishS |> turn
    over(over(fishN, fishW), over(fishE, fishS))
  }

  def quartet(p: Picture, q: Picture, r: Picture, s: Picture): Picture = above(beside(p, q), beside(r, s))

  val blank: Picture = { _ => Seq() }


  def side(n: Int)(p: Picture): Picture = {
    val s = if (n == 1) blank else side(n - 1)(p)
    val t = ttile(p)
    quartet(s, s, t |> turn, t)
  }

  def corner(n: Int)(p: Picture): Picture = {
    val (c, s) =
      if (n == 1) (blank, blank)
      else (corner(n - 1)(p), side(n - 1)(p))
    val u = utile(p)
    quartet(c, s, s |> turn, u)
  }

  def nonet(p: Picture, q: Picture, r: Picture, s: Picture, t: Picture, u: Picture, v: Picture, w: Picture, x: Picture) =
    aboveRatio(1, 2)(besideRatio(1, 2)(p, beside(q, r)),
      aboveRatio(1, 1)(besideRatio(1, 2)(s, beside(t, u)),
        besideRatio(1, 2)(v, beside(w, x))))

  def bandify(combineRatio: (Int, Int) => (Picture, Picture) => Picture)(n: Int)(first: Picture, middle: Picture, last: Picture): Picture = {
    val pictures = Seq(first) ++ Seq.fill(n - 2)(middle)
    val operation: (Picture, (Picture, Int)) => (Picture, Int) = { (item, acc) =>
      (combineRatio(1, acc._2)(item, acc._1), acc._2 + 1)
    }
    val (result, _) = pictures.foldRight((last, 1))(operation)
    result
  }


  def aboveBand(n: Int)(first: Picture, middle: Picture, last: Picture): Picture = bandify(aboveRatio)(n)(first, middle, last)

  def besideBand(n: Int)(first: Picture, middle: Picture, last: Picture): Picture = bandify(besideRatio)(n)(first, middle, last)

  def egg(n: Int, m: Int)(p: Picture): Picture = {
    val cornerNW = corner(n)(p)
    val cornerSW = turn(cornerNW)
    val cornerSE = turn(cornerSW)
    val cornerNE = turn(cornerSE)
    val sideN = side(n)(p)
    val sideW = turn(sideN)
    val sideS = turn(sideW)
    val sideE = turn(sideS)
    val center = utile(p)
    val topband = besideBand(m)(sideN, sideN, sideN)
    val midband = besideBand(m)(center, center, center)
    val botband = besideBand(m)(sideS, sideS, sideS)
    val band = aboveBand(3)(topband, midband, botband)
    band
  }


  def eggband(n: Int)(p: Picture): Picture = {
    val theSide = side(n)(p)
    val q = theSide
    val t = p |> utile
    val w = theSide |> turn |> turn
    nonet(q, q, q, t, t, t, w, w, w)
  }

  def squareLimit(n: Int)(p: Picture): Picture = {
    val cornerNW = corner(n)(p)
    val cornerSW = turn(cornerNW)
    val cornerSE = turn(cornerSW)
    val cornerNE = turn(cornerSE)
    val sideN = side(n)(p)
    val sideW = turn(sideN)
    val sideS = turn(sideW)
    val sideE = turn(sideS)
    val center = utile(p)
    nonet(cornerNW, sideN, cornerNE,
      sideW, center, sideE,
      cornerSW, sideS, cornerSE)
  }


}
