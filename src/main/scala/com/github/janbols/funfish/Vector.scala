package com.github.janbols.funfish

case class Vector(x: Double, y: Double)  {
  def +(other: Vector) = Vector(this.x + other.x, this.y + other.y)
  def -(other: Vector) = Vector(this.x - other.x, this.y - other.y)
  def unary_-(): Vector = Vector(-x, -y)
  def /(divisor: Int) = Vector(x / divisor, y / divisor)
  def *(t: Double) = Vector(x * t, y * t)

  def size(): Double = Math.sqrt(x * x + y * y)
}
