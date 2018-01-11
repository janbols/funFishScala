package com.github.janbols.funfish

import com.github.janbols.funfish.limited._
import com.github.janbols.funfish.rendering.{CanvasRenderer, Renderer}
import com.github.janbols.funfish.unlimited._
import org.scalajs.dom
import org.scalajs.dom.html.{Anchor, Canvas, Div}

import scala.reflect.ClassTag
import scala.scalajs.js.Date
import scala.util.Try


object Program {


  private val pages: Map[Int, PageModel] = Seq(
    boxModel
    , pictureModel
    , turnModel
    , flipModel
    , tossModel
    , aboveModel
    , besideModel
    , quartetModel
    , nonetModel
    , overModel
    , ttileModel
    , utileModel
    , sideModel
    , cornerModel
    , squareLimitModel
    , hueFishModel
    , hueSquareLimitModel
    , hueLizardModel
    , hueLizardsModel
  ).zipWithIndex
    .map(_.swap)
    .toMap



  def main(args: Array[String]): Unit = {
    val page = getPage(dom.window.location.search).getOrElse(1)
    if (page > 1) setPage(getElement[Div]("prev"), page - 1)
    if (page < pages.size) setPage(getElement[Div]("next"), page + 1)

    val nullPageModel = PageModel("Could not get page", rendering => ())
    val pageModel: PageModel = pages.getOrElse(page - 1, nullPageModel)

    setTitle(s"$page: ${pageModel.title}")
    getElement[Canvas]("myCanvas").fold(
      errorMsg => println(s"Could not find canvas. Error is ${errorMsg.getMessage}"),
      canvas => {
        val renderer: Renderer = CanvasRenderer.create(canvas)
        pageModel.rendering(renderer)
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


