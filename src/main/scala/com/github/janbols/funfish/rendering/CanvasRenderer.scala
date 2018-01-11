package com.github.janbols.funfish.rendering

import com.github.janbols.funfish.StyleColor.StyleColor
import com.github.janbols.funfish.{Circle, Curve, FillStyle, Line, Path, Polygon, Shape, StrokeStyle, Style, StyleColor}
import org.scalajs.dom.CanvasRenderingContext2D
import org.scalajs.dom.html.Canvas

import scala.util.Try


object CanvasRenderer {

  private def getColor(styleColor: StyleColor): String = styleColor match {
    case StyleColor.Black => "black"
    case StyleColor.Grey => "grey"
    case StyleColor.White => "white"
    case StyleColor.Red => "orangeRed"
    case StyleColor.Brown => "tan"
    case StyleColor.Beige => "beige"
    case StyleColor.Green => "green"
    case StyleColor.Yellow => "yellow"
    case StyleColor.Blue => "blue"
  }

  private def applyStyle(strokeStyle: Option[StrokeStyle], fillStyle: Option[FillStyle])(context: CanvasRenderingContext2D): Unit = {
    strokeStyle.foreach(ss => context.strokeStyle = getColor(ss.color))
    strokeStyle.foreach(ss => context.lineWidth = ss.width)
    fillStyle.foreach(fs => context.fillStyle = getColor(fs.color))
    strokeStyle.foreach(_ => context.stroke())
    fillStyle.foreach(_ => context.fill())
  }


  private def getContext2D(canvas: Canvas): Try[CanvasRenderingContext2D] = Try(
    canvas.getContext("2d") match {
      case context: CanvasRenderingContext2D => context
      case other => throw new RuntimeException(s"getContext(2d) returned $other")
    }
  )


  def create(canvas: Canvas): Renderer = new Renderer {
    override def render(width: Int, height: Int)(styledShapes: Seq[(Shape, Style)]): Unit = {

      def adjustHeight(y: Double): Double = height - y

      def drawShape(context: CanvasRenderingContext2D)(styledShape: (Shape, Style)): Unit = {
        val (shape, style) = styledShape
        shape match {
          case Polygon(points) => {
            val first = points.head
            context.beginPath()
            context.moveTo(first.x, adjustHeight(first.y))
            points.drop(1).foreach(p => context.lineTo(p.x, adjustHeight(p.y)))
            context.closePath()
          }
          case Curve(p1, p2, p3, p4) => {
            context.beginPath()
            context.moveTo(p1.x, adjustHeight(p1.y))
            context.bezierCurveTo(p2.x, adjustHeight(p2.y), p3.x, adjustHeight(p3.y), p4.x, adjustHeight(p4.y))
          }
          case Path(start, beziers) => {
            context.beginPath()
            context.moveTo(start.x, adjustHeight(start.y))
            beziers.foreach(b =>
              context.bezierCurveTo(b.controlPoint1.x, adjustHeight(b.controlPoint1.y),
                b.controlPoint2.x, adjustHeight(b.controlPoint2.y),
                b.endPoint.x, adjustHeight(b.endPoint.y))
            )
            context.closePath()
          }
          case Line(start, end) => {
            context.beginPath()
            context.moveTo(start.x, adjustHeight(start.y))
            context.lineTo(end.x, adjustHeight(end.y))
          }
          case Circle(center, radius) => {
            context.beginPath()
            context.arc(center.x, adjustHeight(center.y), radius.size(), 0.0, 2 * Math.PI)
          }
        }
        applyStyle(style.stroke, style.fill)(context)
      }

      getContext2D(canvas).fold(e => println(e.getMessage), { context =>
        context.canvas.height = height
        context.canvas.width = width
        styledShapes.foreach(drawShape(context))
      })

    }
  }
}