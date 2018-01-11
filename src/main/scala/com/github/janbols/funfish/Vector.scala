package com.github.janbols.funfish

import scala.language.implicitConversions

case class Vector(x: Double, y: Double)  {
  def +(other: Vector) = Vector(this.x + other.x, this.y + other.y)
  def -(other: Vector) = Vector(this.x - other.x, this.y - other.y)
  def unary_-(): Vector = Vector(-x, -y)
  def /(divisor: Int) = Vector(x / divisor, y / divisor)
  def *(t: Double) = Vector(x * t, y * t)

  def size(): Double = Math.sqrt(x * x + y * y)

  override def toString: String = s"($x,$y)"
}

object Vector{
  implicit def fromDoublePair(p: (Double, Double)) = new Vector(p._1, p._2)
  implicit def fromIntPair(p: (Int, Int)) = new Vector(p._1, p._2)
}
