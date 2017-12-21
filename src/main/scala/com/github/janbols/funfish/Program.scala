package com.github.janbols.funfish

import com.github.janbols.funfish.limited.Picture.Picture
import com.github.janbols.funfish.limited._
import com.github.janbols.funfish.rendering.CanvasRendering
import org.scalajs.dom
import org.scalajs.dom.html.{Anchor, Canvas, Div}

import scala.reflect.ClassTag
import scala.scalajs.js.Date
import scala.util.Try

object Program {

  type Renderer = (Int, Int) => Seq[(Shape, Style)] => Unit


  private val defaultWidth = 800
  private val defaultHeight = 800

  private val pages: Map[Int, (Renderer) => Unit] = Map(
    1 -> draw(fittedBox, Picture(Letter.f))
    , 2 -> draw(fittedBox, Limited.corner(4)(Picture(Letter.h)))
  )


  private def fittedBox(width: Int, height: Int): Box = Box(
    Vector(0.0, 0.0),
    Vector(width.toDouble, 0.0),
    Vector(0.0, height.toDouble)
  )

  private def expandedBox(width: Int, height: Int): Box = Box(
    Vector(width / 4.0, height / 4.0),
    Vector(width / 2.0, 0.0),
    Vector(0.0, height / 2.0)
  )

  private def draw(width: Int, height: Int, boxFactory: (Int, Int) => Box, picture: Picture)(renderer: Renderer): Unit = {
    boxFactory(width, height) |> picture |> renderer(width, height)
  }

  private def draw(boxFactory: (Int, Int) => Box, picture: Picture)(renderer: Renderer): Unit = {
    boxFactory(defaultWidth, defaultHeight) |> picture |> renderer(defaultWidth, defaultHeight)
  }


  def main(args: Array[String]): Unit = {
    val page = getPage(dom.window.location.search).getOrElse(1)
    if (page > 1) setPage(getElement[Div]("prev"), page - 1)
    setPage(getElement[Div]("next"), page + 1)
    setTitle(s"page $page")

    getElement[Canvas]("myCanvas").fold(
      errorMsg => println(s"Could not find canvas. Error is ${errorMsg.getMessage}"),
      canvas => {
        val renderer: Renderer = CanvasRendering.render(canvas)

        pages.get(page).foreach(_ (renderer))
      }
    )
  }


  private def getPage(search: String): Try[Int] = {
    val baseIx = search.indexOf("page=")
    val offset = "page=".length
    Try(
      search.drop(baseIx + offset).takeWhile(_.isDigit).toInt
    )
  }

  private def setPage(triedDiv: Try[Div], pageNr: Int): Unit = {
    for {
      div <- triedDiv
      a <- createElement[Anchor]("a")
    } yield {
      div.appendChild(a)
      a.href = s"index.html?page=$pageNr&ts=${new Date().valueOf()}"
      a.text = s"to page $pageNr"
    }
  }

  private def setTitle(value: String): Unit = {
    for {
      div <- getElement[Div]("title")
    } yield {
      div.textContent = value
    }
  }


  private def getElement[T: ClassTag](elementId: String): Try[T] = Try(
    dom.document.querySelector(s"#$elementId") match {
      case elem: T => elem
      case other => throw new RuntimeException(s"Element with ID $elementId is $other")
    }
  )

  private def createElement[T: ClassTag](tagName: String): Try[T] = Try(
    dom.document.createElement(tagName) match {
      case elem: T => elem
      case other => throw new RuntimeException(s"Element $tagName could not be created: is $other")
    }
  )

}


