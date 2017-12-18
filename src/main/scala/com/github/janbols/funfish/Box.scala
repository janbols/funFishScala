package com.github.janbols.funfish

import com.github.janbols.funfish

case class Box(a: Vector, b: Vector, c: Vector)

object Box {
  def turn(box: Box): Box = funfish.Box(box.a + box.b, box.c, -box.b)

  def flip(box: Box): Box = funfish.Box(box.a + box.b, -box.b, box.c)

  def toss(box: Box): Box = funfish.Box(box.a + (box.b + box.c) / 2, (box.b + box.c) / 2, (box.c - box.b) / 2)

  def scaleHorizontally(s: Double)(box: Box): Box = Box(box.a, box.b * s, box.c)

  def scaleVertically(s: Double)(box: Box): Box = Box(box.a, box.b, box.c * s)

  def moveHorizontally(offset: Double)(box: Box): Box = funfish.Box(box.a + box.b * offset, box.b, box.c)

  def moveVertically(offset: Double)(box: Box): Box = funfish.Box(box.a + box.c * offset, box.b, box.c)

  def splitHorizontally(f: Double)(box: Box): (Box, Box) = {
    val top = box |> moveVertically(1.0 - f) |> scaleVertically(f)
    val bottom = box |> scaleVertically(1.0 - f)
    (top, bottom)
  }

  def splitVertically(f: Double)(box: Box): (Box, Box) = {
    val left = box |> scaleHorizontally(f)
    val right = box |> moveHorizontally(f) |> scaleHorizontally(1.0 - f)
    (left, right)
  }
}



