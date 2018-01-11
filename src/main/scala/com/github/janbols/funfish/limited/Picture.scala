package com.github.janbols.funfish.limited

import java.lang.Double.min

import com.github.janbols.funfish._

import scala.Option.empty


object Picture extends PictureFactory {
  type Picture = Box => Seq[(Shape, Style)]

  val blank: Picture = { _ => Seq() }


  def turn(p: Picture): Picture = Box.turn _ andThen p

  def turns(n: Int)(p: Picture): Picture = times(n)(turn)(p)

  def flip(p: Picture): Picture = Box.flip _ andThen p

  def toss(p: Picture): Picture = Box.toss _ andThen p

  def besideRatio(m: Int, n: Int)(p1: Picture, p2: Picture): Picture = { box =>
    val factor = m.toDouble / (m + n).toDouble
    val (b1, b2) = Box.splitVertically(factor)(box)
    p1(b1) ++ p2(b2)
  }

  def beside(p1: Picture, p2: Picture): Picture = besideRatio(1, 1)(p1, p2)

  def aboveRatio(m: Int, n: Int)(p1: Picture, p2: Picture): Picture = { box =>
    val factor = m.toDouble / (m + n).toDouble
    val (b1, b2) = Box.splitHorizontally(factor)(box)
    p1(b1) ++ p2(b2)

  }

  def above(p1: Picture, p2: Picture): Picture = aboveRatio(1, 1)(p1, p2)


  def quartet(p: Picture, q: Picture, r: Picture, s: Picture): Picture = above(beside(p, q), beside(r, s))


  def nonet(p: Picture, q: Picture, r: Picture, s: Picture, t: Picture, u: Picture, v: Picture, w: Picture, x: Picture) =
    aboveRatio(1, 2)(besideRatio(1, 2)(p, beside(q, r)),
      aboveRatio(1, 1)(besideRatio(1, 2)(s, beside(t, u)),
        besideRatio(1, 2)(v, beside(w, x))))


  def over(p1: Picture, p2: Picture): Picture = { box => p1(box) ++ p2(box) }

  def overs(ps: Picture*): Picture = ps.fold(blank)(over)


  def ttile(f: Picture): Picture = {
    val fishN = f |> toss |> flip
    val fishE = fishN |> turns(3)
    overs(f, fishN, fishE)
  }


  def utile(f: Picture): Picture = {
    val fishN = f |> toss |> flip
    val fishW = fishN |> turn
    val fishS = fishW |> turn
    val fishE = fishS |> turn
    overs(fishN, fishW, fishE, fishS)
  }


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


  def squareLimit(n: Int)(p: Picture): Picture = {
    val c = corner(n)(p)
    val s = side(n)(p)

    val nw = c
    val nm = s
    val ne = turns(3)(c)
    val mw = turn(s)
    val mm = utile(p)
    val me = turns(3)(s)
    val sw = turn(c)
    val sm = turns(2)(s)
    val se = turns(2)(c)
    nonet(
      nw, nm, ne,
      mw, mm, me,
      sw, sm, se
    )
  }


  def apply(shapes: Shape*): Picture = { box =>
    val m = mapper(box)(_)
    val style = getStyle(box)
    shapes
      .map(mapShape(m))
      .map((_, style))
  }

}


trait PictureFactory {

  protected def mapper(box: Box)(v: Vector): Vector = box.a + (box.b * v.x) + (box.c * v.y)

  protected def mapShape(m: Vector => Vector)(s: Shape): Shape = s match {
    case Polygon(ps) => Polygon(ps.map(m))
    case Curve(p1, p2, p3, p4) => Curve(m(p1), m(p2), m(p3), m(p4))
    case Path(start, beziers) => Path(m(start), beziers.map(b => Bezier(m(b.controlPoint1), m(b.controlPoint2), m(b.endPoint))))
    case Line(lineStart, lineEnd) => Line(m(lineStart), m(lineEnd))
    case Circle(center, radius) => Circle(m(center), m(center + radius) - m(center))
  }

  protected def getStrokeWidth(box: Box): Double = min(box.b.size(), box.c.size()) / 80

  protected def getStyle(box: Box): Style = Style(Option(StrokeStyle(getStrokeWidth(box), StyleColor.Black)), empty)


}
