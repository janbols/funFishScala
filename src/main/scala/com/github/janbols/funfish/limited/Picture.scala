package com.github.janbols.funfish.limited

import java.lang.Double.min

import com.github.janbols.funfish
import com.github.janbols.funfish.{Bezier, Box, Circle, Curve, Line, Path, Polygon, Shape, StrokeStyle, Style, StyleColor, Vector, limited}

import scala.Option.empty


object Picture extends PictureFactory {
  type Picture = Box => Seq[(Shape, Style)]

  def turn(p: Picture): Picture = Box.turn _ andThen p

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

  def over(p1: Picture, p2: Picture): Picture = { box => p1(box) ++ p2(box) }


  def apply(shapes: Shape*): Picture = { box =>
    val m = mapper(box)(_)
    val style = getStyle(box)
    shapes.map(mapShape(m)).map((_, style))
  }

}


trait PictureFactory {

  protected def mapper(box: Box)(v: Vector): Vector = box.a + (box.b * v.x) + (box.c * v.y)

  protected def mapShape(m: Vector => Vector)(s: Shape): Shape = s match {
    case Polygon(ps) => Polygon(ps.map(m))
    case Curve(p1, p2, p3, p4) => Curve(m(p1), m(p2), m(p3), m(p4))
    case Path(start, beziers) => {
      funfish.Path(m(start), beziers.map(mapBezier(m)))
    }
    case Line(lineStart, lineEnd) => funfish.Line(m(lineStart), m(lineEnd))
    case Circle(center, radius) => {
      val cNew = m(center)
      val rNew = m(radius) - cNew
      funfish.Circle(cNew, rNew)
    }
  }

  protected def getStrokeWidth(box: Box): Double = min(box.b.size(), box.c.size()) / 80

  protected def mapBezier(m: Vector => Vector)(b: Bezier): Bezier = funfish.Bezier(m(b.controlPoint1), m(b.controlPoint2), m(b.endPoint))

  protected def getStyle(box: Box): Style = Style(Option(StrokeStyle(getStrokeWidth(box), StyleColor.Black)), empty)


}
