package com.github.janbols.funfish

import com.github.janbols.funfish.StyleColor.StyleColor

object StyleColor extends Enumeration {
  type StyleColor = Value
  val Black, Grey, White, Red, Green, Yellow = Value
}

case class StrokeStyle(strokeWidth: Double, strokeColor: StyleColor)

case class FillStyle(fillColor: StyleColor)

case class Style(stroke: Option[StrokeStyle], fill: Option[FillStyle])
