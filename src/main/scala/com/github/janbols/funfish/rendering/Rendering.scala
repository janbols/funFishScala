package com.github.janbols.funfish.rendering

import com.github.janbols.funfish.{Shape, Style}
import com.github.janbols.funfish.limited._

trait Rendering[T] {
  def render(t: T)(width: Int, height: Int)(styledShapes: Seq[(Shape, Style)]): Unit
}
