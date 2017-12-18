package com.github.janbols.funfish.unlimited

import com.github.janbols.funfish.Box
import com.github.janbols.funfish.unlimited.Hue.Hue


object Hue extends Enumeration {
  type Hue = Value
  val Blackish, Greyish, Whiteish, Hollow = Value
}

case class Lens (box: Box, hue: Hue)

object Lens{


  def rehue(l: Lens): Lens = l.copy(hue = l.hue match {
    case Hue.Blackish => Hue.Greyish
    case Hue.Greyish => Hue.Whiteish
    case Hue.Whiteish => Hue.Blackish
    case Hue.Hollow => Hue.Hollow
  })
}
