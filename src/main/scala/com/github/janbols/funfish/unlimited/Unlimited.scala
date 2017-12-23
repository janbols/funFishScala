package com.github.janbols.funfish.unlimited

import com.github.janbols.funfish.unlimited.LensPicture._

object Unlimited {

  def ttile(hueN: LensPicture => LensPicture, hueE: LensPicture => LensPicture, f: LensPicture): LensPicture = {
    val fishN = f |> toss |> flip
    val fishE = fishN |> turn |> turn |> turn
    over(f, over(fishN |> hueN, fishE |> hueE))
  }

  def ttile0(f: LensPicture): LensPicture = ttile(
    identity,
    identity,
    f)

  def ttile1(f: LensPicture): LensPicture = ttile(
    rehue,
    rehue _ andThen rehue,
    f)

  def ttile2(f: LensPicture): LensPicture = ttile(
    rehue _ andThen rehue,
    rehue,
    f)

  def utile(hueN: LensPicture => LensPicture,
            hueW: LensPicture => LensPicture,
            hueS: LensPicture => LensPicture,
            hueE: LensPicture => LensPicture,
            f: LensPicture): LensPicture = {
    val fishN = f |> (toss _ andThen flip)
    val fishW = fishN |> turn
    val fishS = fishW |> turn
    val fishE = fishS |> turn
    over(
      over(fishN |> hueN, fishW |> hueW),
      over(fishE |> hueE, fishS |> hueS)
    )
  }

  def utile0(f: LensPicture): LensPicture = utile(
    identity,
    identity,
    identity,
    identity,
    f)

  def utile1(f: LensPicture): LensPicture = utile(
    rehue _ andThen rehue,
    identity,
    rehue _ andThen rehue,
    identity,
    f)

  def utile2(f: LensPicture): LensPicture = utile(
    identity,
    rehue _ andThen rehue,
    rehue,
    rehue _ andThen rehue,
    f)

  def utile3(f: LensPicture): LensPicture = utile(
    rehue _ andThen rehue,
    identity,
    rehue,
    identity,
    f)

  def quartet(p: LensPicture, q: LensPicture, r: LensPicture, s: LensPicture): LensPicture = above(beside(p, q), beside(r, s))


  def quartet2(depth: Int)(p: LensPicture): LensPicture = {
    def qquartet(n: Int)(p: LensPicture): LensPicture = {
      val p2 = if (n == 1) p else qquartet(n - 1)(p)
      quartet(p2, p2, p2, p2)
    }

    val p2 = p |> rehue
    val pNW = p
    val pNE = p |> rehue |> turn
    val pSW = p |> rehue |> turn |> turn |> turn
    val pSE = p |> rehue |> turn |> turn
    val q = quartet(pNW, pNE, pSW, pSE)
    qquartet(depth)( q)
  }

  val blank: LensPicture = { _ => Seq() }

  def side(tt: LensPicture => LensPicture, hueSW: LensPicture => LensPicture, hueSE: LensPicture => LensPicture)(n: Int, p: LensPicture): LensPicture = {
    val s = if (n == 1) blank else side(tt, hueSW, hueSE)(n - 1, p)
    val t = tt(p)
    quartet(s, s, t |> turn |> hueSW, t |> hueSE)
  }

  def side0(n: Int, p: LensPicture): LensPicture = side(ttile0, identity, identity)(n, p)

  def side1(n: Int, p: LensPicture): LensPicture = side(ttile1, identity, rehue)(n, p)

  def side2(n: Int, p: LensPicture): LensPicture = side(ttile2, rehue _ andThen rehue, rehue)(n, p)

  def corner(ut: LensPicture => LensPicture, sideNE: (Int, LensPicture) => LensPicture, sideSW: (Int, LensPicture) => LensPicture)(n: Int, p: LensPicture): LensPicture = {
    val (c, ne, sw) =
      if (n == 1) (blank, blank, blank)
      else (corner(ut, sideNE, sideSW)(n - 1, p), sideNE(n - 1, p), sideSW(n - 1, p))
    val u = ut(p)
    quartet(c, ne, sw |> turn, u)
  }


  def corner1(n: Int, p: LensPicture): LensPicture = corner(utile3, side1, side2)(n, p)

  def corner2(n: Int, p: LensPicture): LensPicture = corner(utile2, side2, side1)(n, p)

  def nonet(p: LensPicture, q: LensPicture, r: LensPicture, s: LensPicture, t: LensPicture, u: LensPicture, v: LensPicture, w: LensPicture, x: LensPicture) =
    aboveRatio(1, 2)(besideRatio(1, 2)(p, beside(q, r)),
      aboveRatio(1, 1)(besideRatio(1, 2)(s, beside(t, u)),
        besideRatio(1, 2)(v, beside(w, x))))


  def squareLimit(n: Int)( p: LensPicture): LensPicture = {
    val cornerNW = corner1(n, p)
    val cornerSW = corner2(n, p) |> turn
    val cornerSE = cornerNW |> turn |> turn
    val cornerNE = cornerSW |> turn |> turn
    val sideN = side1(n, p)
    val sideW = side2(n, p) |> turn
    val sideS = sideN |> turn |> turn
    val sideE = sideW |> turn |> turn
    val center = utile1(p)
    nonet(
      cornerNW, sideN, cornerNE,
      sideW, center, sideE,
      cornerSW, sideS, cornerSE
    )
  }

  def bandify(combineRatio: (Int, Int) => (LensPicture, LensPicture) => LensPicture)(n: Int)(first: LensPicture, middle: LensPicture, last: LensPicture): LensPicture = {
    val pictures = Seq(first) ++ Seq.fill(n - 2)(middle)
    val operation: (LensPicture, (LensPicture, Int)) => (LensPicture, Int) = { (item, acc) =>
      (combineRatio(1, acc._2)(item, acc._1), acc._2 + 1)
    }
    val (result, _) = pictures.foldRight((last, 1))(operation)
    result
  }


  def aboveBand(n: Int)(first: LensPicture, middle: LensPicture, last: LensPicture): LensPicture = bandify(aboveRatio)(n)(first, middle, last)

  def besideBand(n: Int)(first: LensPicture, middle: LensPicture, last: LensPicture): LensPicture = bandify(besideRatio)(n)(first, middle, last)


  def egg(n: Int, m: Int)(p: LensPicture): LensPicture = {
    val sideN = side0(n, p)
    val sideS = sideN |> LensPicture.turn |> LensPicture.turn
    val center = utile0(p)
    val topband = besideBand(m)(sideN, sideN, sideN)
    val midband = besideBand(m)(center, center, center)
    val botband = besideBand(m)(sideS, sideS, sideS)
    val band = aboveBand(3)(topband, midband, botband)
    band
  }


}
