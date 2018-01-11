package com.github.janbols.funfish

import com.github.janbols.funfish.StyleColor.StyleColor

object StyleColor extends Enumeration {
  type StyleColor = Value
  val Black, Grey, White, Red, Brown, Beige, Green, Yellow, Blue = Value
}

case class StrokeStyle(width: Double, color: StyleColor)

case class FillStyle(color: StyleColor)

case class Style(stroke: Option[StrokeStyle], fill: Option[FillStyle])
