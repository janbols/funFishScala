package com.github.janbols.funfish

import com.github.janbols.funfish.unlimited.Fishier.lpFish
import com.github.janbols.funfish.unlimited.LensPicture._
import com.github.janbols.funfish.unlimited.Lizard.lpLizard

package object unlimited {
  val hueFishModel: PageModel = {
    val lens = Lens(Box((100,200), (800, 0), (0, 333)), Hue.Blackish)
    val p = lpFish

    val shapes = lens |> besideRatio(1,2)(p, beside(p |> rehue, p |> times(2)(rehue)))
    PageModel("Hue fish")(shapes)

  }
  val hueSquareLimitModel: PageModel = {
    val lens = Lens(Box((0,0), (800, 0), (0, 800)), Hue.Brownish)

    val shapes = lens |> squareLimit(3)(lpFish)
    PageModel("Hue square limit")(shapes)
  }

  val hueLizardModel: PageModel = {
    val lens = Lens(Box((200,200), (400, 0), (0, 400)), Hue.Greyish)

    val shapes = lens |> lpLizard
    PageModel("Hue lizard")(shapes)

  }

  val hueLizardsModel: PageModel = {
    val lens = Lens(Box((100,100), (600, 0), (0, 600)), Hue.Redish)

    val shapes = lens |> quartet2(2)(lpLizard)
    PageModel("Hue lizard quartet")(shapes)

  }
}
