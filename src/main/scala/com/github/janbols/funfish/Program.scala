package com.github.janbols.funfish

import com.github.janbols.funfish.limited._
import com.github.janbols.funfish.rendering.CanvasRendering
import org.scalajs.dom
import org.scalajs.dom.html.Canvas

import scala.reflect.ClassTag
import scala.util.Try

object Program {

  type Renderer = (Int, Int) => Seq[(Shape, Style)] => Unit

  def fittedBox(width: Int, height: Int): Box = Box(
    Vector(0.0, 0.0),
    Vector(width.toDouble, 0.0),
    Vector(0.0, height.toDouble)
  )

  def expandedBox(width: Int, height: Int): Box = Box(
    Vector(width / 4.0, height / 4.0),
    Vector(width / 2.0, 0.0),
    Vector(0.0, height / 2.0)
  )

  def simpleBox(width: Int, height: Int)(renderer: Renderer) {
    val box = fittedBox(width, height)
    val picture = Limited.corner(4)(Picture(Letter.h))
    box |> picture |> renderer(width, height)
  }


  def main(args: Array[String]): Unit = {
    getElement[Canvas]("myCanvas").fold(
      errorMsg => println(s"Could not find canvas. Error is ${errorMsg.getMessage}"),
      canvas => {
        val renderer: Renderer = CanvasRendering.render(canvas)

        simpleBox(800, 800)(renderer)
      }
    )
  }

  private def getElement[T: ClassTag](elementId: String): Try[T] = Try(
    dom.document.querySelector(s"#$elementId") match {
      case elem: T => elem
      case other => throw new RuntimeException(s"Element with ID $elementId is $other")
    }
  )

}


