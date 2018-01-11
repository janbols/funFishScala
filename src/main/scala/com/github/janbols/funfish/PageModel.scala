package com.github.janbols.funfish

import com.github.janbols.funfish.rendering.Renderer

import scala.Option.empty


case class PageModel(title: String, rendering: Renderer => Unit)

object PageModel {
  val defaultWidth = 800
  val defaultHeight = 800


  def apply(title: String, width: Int = defaultWidth, height: Int = defaultHeight)(shapes: Seq[(Shape, Style)], boxes: Box*): PageModel = {
    val boxShapes = toStyledShape(boxes)
    PageModel(title, { r =>r.render(width, height)(shapes ++ boxShapes) })
  }

  private def toStyledShape(boxes: Seq[Box]): Seq[(Shape, Style)] =
    boxes.flatMap(box =>
      Seq((Line((0, 0), box.a), Style(Option(StrokeStyle(1.0, StyleColor.Red)), empty))
        , (Line(box.a, box.a + box.b), Style(Option(StrokeStyle(1.0, StyleColor.Green)), empty))
        , (Line(box.a, box.a + box.c), Style(Option(StrokeStyle(1.0, StyleColor.Blue)), empty))
        , (Line(box.a + box.b, box.a + box.b + box.c), Style(Option(StrokeStyle(1.0, StyleColor.Grey)), empty))
        , (Line(box.a + box.c, box.a + box.b + box.c), Style(Option(StrokeStyle(1.0, StyleColor.Grey)), empty))
      )
    )
}

