package com.github.janbols

package object funfish {

  implicit class Pipe[T](val v: T) extends AnyVal {
    def |>[U] (f: T => U) = f(v)
    // Additional suggestions:
    def $$[U](f: T => U): T = {f(v); v}
    def #!(str: String = ""): T = {println(str + v); v}
  }


  def times[A](n:Int)(f: A=>A): A=>A = n match {
    case 0 => identity
    case _ => f andThen times(n-1)(f)
  }

  def toSeq[A](t: (A, A)): Seq[A] = Seq(t._1, t._2)




}
