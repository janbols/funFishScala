package com.github.janbols.funfish

import com.github.janbols.funfish.limited.Fishy.hendersonFishShapes
import com.github.janbols.funfish.limited.Letter._
import com.github.janbols.funfish.limited.Picture.Picture
import com.github.janbols.funfish.limited._
import com.github.janbols.funfish.rendering.CanvasRendering
import com.github.janbols.funfish.unlimited.Fishier.fishShapes
import com.github.janbols.funfish.unlimited.Hue.Hue
import com.github.janbols.funfish.unlimited.LensPicture.LensPicture
import com.github.janbols.funfish.unlimited.Lizard.lizardShapes
import com.github.janbols.funfish.unlimited._
import org.scalajs.dom
import org.scalajs.dom.html.{Anchor, Canvas, Div}

import scala.reflect.ClassTag
import scala.scalajs.js.Date
import scala.util.Try

object Program {

  type Renderer = (Int, Int) => Seq[(Shape, Style)] => Unit


  private val defaultWidth = 800
  private val defaultHeight = 800

  private val pages: Map[Int, (Renderer) => Unit] = Seq(
    draw(fittedBox, Picture(Letter.f)) _
    , draw(fittedBox, Limited.nonet(Picture(h), Picture(e), Picture(n), Picture(d1, d2), Picture(e), Picture(r1, r2), Picture(s), Picture(o1, o2), Picture(h))) _
    , draw(expandedBox, Picture(hendersonFishShapes: _*)) _
    , draw(expandedBox, Limited.ttile(Picture(hendersonFishShapes: _*))) _
    , draw(bandBox, Limited.egg(3, 16)(Picture(hendersonFishShapes: _*)), 1200, 800) _
    , draw(fittedBox, Limited.squareLimit(3)(Picture(hendersonFishShapes: _*))) _
    , drawLens(expandedBox, Hue.Blackish, LensPicture(fishShapes: _*)) _
    , drawLens(expandedBox, Hue.Greyish, LensPicture(fishShapes: _*)) _
    , drawLens(expandedBox, Hue.Whiteish, LensPicture(fishShapes: _*)) _
    , drawLens(expandedBox, Hue.Greyish, LensPicture(lizardShapes: _*)) _
    , drawLens(fittedBox, Hue.Blackish, Unlimited.quartet2(3)(LensPicture(lizardShapes: _*))) _
    , drawLens(bandBox, Hue.Hollow, Unlimited.egg(3, 16)(LensPicture(fishShapes: _*)), 1200, 800) _
    , drawLens(fittedBox, Hue.Greyish, Unlimited.squareLimit(3)(LensPicture(fishShapes: _*))) _
    , drawLens(fittedBox, Hue.Brownish, Unlimited.squareLimit(3)(LensPicture(fishShapes: _*))) _
  ).zipWithIndex
    .map(_.swap)
    .toMap


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

  private def bandBox(width: Int, height: Int): Box = Box(
    Vector(100.0, 100.0),
    Vector(3200.0, 0.0),
    Vector(0.0, 600.0)
  )

  private def draw(boxFactory: (Int, Int) => Box, picture: Picture, width: Int = defaultWidth, height: Int = defaultHeight)(renderer: Renderer): Unit = {
    boxFactory(width, height) |> picture |> renderer(width, height)
  }

  private def drawLens(boxFactory: (Int, Int) => Box, hue: Hue, lensPicture: LensPicture, width: Int = defaultWidth, height: Int = defaultHeight)(renderer: Renderer): Unit = {
    Lens(boxFactory(width, height), hue) |> lensPicture |> renderer(width, height)
  }

  def main(args: Array[String]): Unit = {
    val page = getPage(dom.window.location.search).getOrElse(1)
    if (page > 1) setPage(getElement[Div]("prev"), page - 1)
    if (page < pages.size) setPage(getElement[Div]("next"), page + 1)
    setTitle(s"page $page")

    getElement[Canvas]("myCanvas").fold(
      errorMsg => println(s"Could not find canvas. Error is ${errorMsg.getMessage}"),
      canvas => {
        val renderer: Renderer = CanvasRendering.render(canvas)

        pages.get(page - 1).foreach(_ (renderer))
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


