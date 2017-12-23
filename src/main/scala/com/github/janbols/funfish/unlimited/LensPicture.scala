package com.github.janbols.funfish.unlimited

import java.lang.Double.min

import com.github.janbols.funfish.StyleColor.StyleColor
import com.github.janbols.funfish.limited.Picture.Picture
import com.github.janbols.funfish.limited._
import com.github.janbols.funfish.unlimited.Hue.Hue
import com.github.janbols.funfish.{Circle, Curve, FillStyle, Line, Path, Polygon, Shape, StrokeStyle, Style, StyleColor}

import scala.Option.empty


object LensPicture extends LensPictureFactory {

  type LensPicture = Lens => Seq[(Shape, Style)]

  private def toPicture(lens: Lens)(p: LensPicture): Picture = box => lens.copy(box = box) |> p

  private def lift(f: Picture => Picture)(p: LensPicture): LensPicture = { lens: Lens =>
    val toP = toPicture(lens)(_)
    lens.box |> (p |> toP |> f)
  }

  private def lift(f: (Picture, Picture) => Picture)(p1: LensPicture, p2: LensPicture): LensPicture = { lens: Lens =>
    val toP = toPicture(lens)(_)
    lens.box |> f(toP(p1), toP(p2))
  }

  def turn(p: LensPicture): LensPicture = lift(Picture.turn _)(p)

  def flip(p: LensPicture): LensPicture = lift(Picture.flip _)(p)

  def toss(p: LensPicture): LensPicture = lift(Picture.toss _)(p)

  def besideRatio(m: Int, n: Int)(p1: LensPicture, p2: LensPicture): LensPicture = lift(Picture.besideRatio(m, n)(_,_))(p1, p2)
  def beside(p1: LensPicture, p2: LensPicture): LensPicture = besideRatio(1, 1)(p1, p2)

  def aboveRatio(m: Int, n: Int)(p1: LensPicture, p2: LensPicture): LensPicture = lift(Picture.aboveRatio(m, n)(_,_))(p1, p2)
  def above(p1: LensPicture, p2: LensPicture): LensPicture = aboveRatio(1, 1)( p1, p2)

  def over(p1: LensPicture, p2: LensPicture): LensPicture = lift(Picture.over _)(p1, p2)

  def rehue(p: LensPicture): LensPicture = lens => lens |> Lens.rehue |> p



  def apply(shapes: (String, Shape)*): LensPicture = lens =>
    shapes.map(mapMaybeNamedShape(lens))
      .filter(_.nonEmpty)
      .map(_.get)
}



trait LensPictureFactory extends PictureFactory{

  protected def getDefaultColor(name: String, hue: Hue): StyleColor =
    if (Set("secondary", "tail-fin", "fin-stem", "fin-details", "main-spine").contains(name)) {
      hue match {
        case Hue.Blackish => StyleColor.White
        case Hue.Greyish => StyleColor.White
        case Hue.Whiteish => StyleColor.Black
        case Hue.Hollow => StyleColor.Black
      }
    } else {
      hue match {
        case Hue.Blackish => StyleColor.Black
        case Hue.Greyish => StyleColor.Grey
        case Hue.Whiteish => StyleColor.White
        case Hue.Hollow => StyleColor.White
      }
    }


  protected def getDefaultStyle(name: String, sw: Double, hue: Hue): Style =
    Style(Option(StrokeStyle(sw, getDefaultColor(name, hue))), empty)

  protected def getCircleStyle(name: String, sw: Double, hue: Hue): Style =
    Style(empty, Option(FillStyle(getDefaultColor(name, hue))))

  protected def isInnerEye(name: String): Boolean = name.endsWith("-inner")

  protected def isOuterEye(name: String): Boolean = name.endsWith("-outer")

  protected def getColor(name: String, hue: Hue): StyleColor = hue match {
    case Hue.Blackish => name match {
      case "primary" => StyleColor.Black
      case n if isOuterEye(n) => StyleColor.White
      case n if isInnerEye(n) => StyleColor.Black
      case _ => StyleColor.White
    }
    case Hue.Greyish => name match {
      case "primary" => StyleColor.Grey
      case n if isOuterEye(n) => StyleColor.White
      case n if isInnerEye(n) => StyleColor.Grey
      case _ => StyleColor.White
    }
    case Hue.Whiteish => name match {
      case "primary" => StyleColor.White
      case n if isOuterEye(n) => StyleColor.White
      case n if isInnerEye(n) => StyleColor.Black
      case _ => StyleColor.Black
    }
    case Hue.Hollow => name match {
      case "primary" => StyleColor.White
      case n if isOuterEye(n) => StyleColor.White
      case n if isInnerEye(n) => StyleColor.Black
      case _ => StyleColor.Black
    }
  }

  protected def getEyeLiner(sw: Double, hue: Hue): StrokeStyle = StrokeStyle(sw, getColor("secondary", hue))

  protected def getPathStyle(name: String, sw: Double, hue: Hue): Style = hue match {
    case Hue.Hollow => Style(Option(getEyeLiner(sw, hue)), if (isInnerEye(name)) Option(FillStyle(StyleColor.Black)) else empty)
    case _ => Style(if (isOuterEye(name)) Option(getEyeLiner(sw, hue)) else empty, Option(FillStyle(getColor(name, hue))))
  }


  protected def mapNamedShape(lens: Lens)(namedShape: (String, Shape)): (Shape, Style) = {
    val (name, shape) = namedShape
    val Lens(box, hue) = lens
    val m = mapper(box)(_)
    val sw = getStrokeWidth(box)
    shape match {
      case Polygon(points) => (Polygon(points.map(m)), getDefaultStyle(name, sw, hue))
      case Curve(p1, p2, p3, p4) => (Curve(m(p1), m(p2), m(p3), m(p4)), getDefaultStyle(name, sw, hue))
      case Path(start, beziers) => {
        val style = getPathStyle(name, sw, hue)
        val style2 =
          if (name == "egg-eye-inner" && min(box.b.size(), box.c.size()) < 200.0) style.copy(stroke = Option(StrokeStyle(2.0 * sw, StyleColor.Black)))
          else style
        (Path(m(start), beziers.map(mapBezier(m))), style2)
      }
      case Line(start, end) => (Line(m(start), m(end)), getDefaultStyle(name, sw, hue))
      case Circle(center, radius) => (Circle(m(center), m(radius) - box.a), getCircleStyle(name, sw, hue))
    }
  }

  protected def mapMaybeNamedShape(lens: Lens)(namedShape: (String, Shape)): Option[(Shape, Style)] = {
    val (name, shape) = namedShape
    val boxSize = min(lens.box.b.size(), lens.box.c.size())
    if (name == "egg-eye-inner" && boxSize < 60.0) empty
    else if (name == "egg-eye-outer" && boxSize < 200.0) empty
    else if (name == "tail-fin" && boxSize < 200.0) empty
    else if (name == "fin-details" && boxSize < 100.0) empty
    else if (name == "fin-stem" && boxSize < 60.0) empty
    else if (name == "main-spine" && boxSize < 100.0) empty
    else Option(mapNamedShape(lens)(name, shape))
  }



}