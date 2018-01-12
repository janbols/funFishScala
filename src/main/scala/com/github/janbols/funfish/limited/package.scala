package com.github.janbols.funfish

import com.github.janbols.funfish.limited.Fishy.pFish
import com.github.janbols.funfish.limited.Letter._
import com.github.janbols.funfish.limited.Picture._

package object limited {


  val boxModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))

    PageModel(s"Box: (a: ${box.a}, b: ${box.b}, c: ${box.b})")(Seq(), box)
  }

  val pictureModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF
    val shapes = box |> p

    PageModel("Picture: Box => [(Shape, Style)]")(shapes, box)
  }

  val turnModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF
    val shapes = box |> p

    PageModel("turn: (a + b, c, -b)")(shapes, box)
  }

  val flipModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF
    val shapes = box |> p

    PageModel("flip: (a + b, -b, c)")(shapes, box)
  }

  val tossModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF
    val shapes = box |> p

    PageModel("toss: (a + (b + c)/2, (b + c)/2, (c - b)/2)")(shapes, box)
  }

  val aboveModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF
    val shapes = box |> p

    PageModel("above")(shapes, box)
  }

  val besideModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pN
    val shapes = box |> p

    PageModel("beside")(shapes, box)
  }

  val quartetModel: PageModel = {
    val box = Box((0, 0), (800, 0), (0, 800))
    val p = pF
    val shapes = box |> p

    PageModel("quartet: ne, nw, se, sw")(shapes)
  }

  val nonetModel: PageModel = {
    val box = Box((0, 0), (800, 0), (0, 800))
    val p = pF
    val shapes = box |> p

    PageModel("nonet: ne, nm, nw, me, mm, mw, se, sm, sw")(shapes)
  }

  val overModel: PageModel = {
    val box = Box((200, 200), (400, 0), (0, 400))
    val p = pFish
    val shapes = box |> p

    PageModel("over")(shapes)
  }

  val ttileModel: PageModel = {
    val box = Box((200, 200), (400, 0), (0, 400))
    val p = pFish
    val shapes = box |> p

    PageModel("ttile")(shapes)
  }

  val utileModel: PageModel = {
    val box = Box((200, 200), (400, 0), (0, 400))
    val p = pFish
    val shapes = box |> p

    PageModel("utile")(shapes)
  }

  val sideModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pFish
    val shapes = box |> p

    PageModel("side")(shapes)
  }

  val cornerModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pFish
    val shapes = box |> p

    PageModel("corner")(shapes)
  }

  val squareLimitModel: PageModel = {
    val box = Box((0, 0), (800, 0), (0, 800))
    val p = pFish
    val shapes = box |> p

    PageModel("square limit")(shapes)
  }

  
}
