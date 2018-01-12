package com.github.janbols.funfish.limited

import java.lang.Double.min

import com.github.janbols.funfish._

import scala.Option.empty


object Picture extends PictureFactory {
  type Picture = Box => Seq[(Shape, Style)]

  val blank: Picture = { _ => Seq() }

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
