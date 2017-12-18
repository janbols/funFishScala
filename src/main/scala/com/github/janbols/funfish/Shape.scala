package com.github.janbols.funfish

sealed trait Shape

case class Polygon(points: Seq[Vector]) extends Shape

case class Curve(point1: Vector, point2: Vector, point3: Vector, point4: Vector) extends Shape

case class Bezier(controlPoint1: Vector, controlPoint2: Vector, endPoint: Vector)

case class Path(start: Vector, beziers: Seq[Bezier]) extends Shape

case class Line(lineStart: Vector, lineEnd: Vector) extends Shape

case class Circle(center: Vector, radius: Vector) extends Shape

