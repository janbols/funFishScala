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
    val p = pF |> turn
    val shapes = box |> p

    PageModel("turn: (a + b, c, -b)")(shapes, box |> Box.turn)
  }

  val flipModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF |> flip
    val shapes = box |> p

    PageModel("flip: (a + b, -b, c)")(shapes, box |> Box.flip)
  }

  val tossModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pF |> Picture.toss
    val shapes = box |> p

    PageModel("toss: (a + (b + c)/2, (b + c)/2, (c - b)/2)")(shapes, box |> Box.toss)
  }

  val aboveModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = above(pF, pN)
    val shapes = box |> p

    PageModel("above")(shapes, Box.splitHorizontally(0.5)(box) |> toSeq: _*)
  }

  val besideModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = beside(pF, pN)
    val shapes = box |> p

    PageModel("beside")(shapes, Box.splitVertically(0.5)(box) |> toSeq: _*)
  }

  val quartetModel: PageModel = {
    val box = Box((0, 0), (800, 0), (0, 800))
    val p0 = quartet(
      pH, pE,
      pN, pD
    )

    val p1 = quartet(
      pH |> turn, pE |> turns(3),
      pN, pD |> turns(2)
    )

    val p2 = quartet(
      p0, blank,
      blank, p0
    )

    def quartet2(p: Picture) = quartet(p, p, p, p)

    val p3 = p2 |> times(3)(quartet2)

    val shapes = box |> p3

    PageModel("quartet: ne, nw, se, sw")(shapes)
  }

  val nonetModel: PageModel = {
    val box = Box((0, 0), (800, 0), (0, 800))
    val p0 = nonet(
      pH, pE, pN,
      pD, pE, pR,
      pS, pO, pN
    )

    def zoom(p:Picture) = nonet(
      pH, pE, pN,
      pD, p, pR,
      pS, pO, pN
    )
    val shapes = box |> times(3)(zoom)(p0)

    PageModel("nonet: ne, nm, nw, me, mm, mw, se, sm, sw")(shapes)
  }

  val overModel: PageModel = {
    val box = Box((200, 200), (400, 0), (0, 400))
    val p = pFish
    val shapes = box |> over(p, turns(2)(p))

    PageModel("over")(shapes)
  }

  val ttileModel: PageModel = {
    val box = Box((200, 200), (400, 0), (0, 400))
    val p = pFish
    val shapes = box |> ttile(p)

    PageModel("ttile")(shapes)
  }

  val utileModel: PageModel = {
    val box = Box((200, 200), (400, 0), (0, 400))
    val p = pFish
    val shapes = box |> utile(p)

    PageModel("utile")(shapes)
  }

  val sideModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pFish
    val shapes = box |> side(3)(p)

    PageModel("side")(shapes)
  }

  val cornerModel: PageModel = {
    val box = Box((100, 100), (600, 0), (0, 600))
    val p = pFish
    val shapes = box |> corner(3)(p)

    PageModel("corner")(shapes)
  }

  val squareLimitModel: PageModel = {
    val box = Box((0, 0), (800, 0), (0, 800))
    val p = pFish
    val shapes = box |> squareLimit(3)(p)

    PageModel("square limit")(shapes)
  }

  
}
