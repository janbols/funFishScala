package com.github.janbols.funfish.rendering

import com.github.janbols.funfish.{Shape, Style}

trait Renderer {
  def render(width: Int, height: Int)(styledShapes: Seq[(Shape, Style)]): Unit
}
