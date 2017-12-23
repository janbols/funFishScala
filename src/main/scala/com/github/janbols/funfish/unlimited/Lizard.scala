package com.github.janbols.funfish.unlimited

import com.github.janbols.funfish.{Bezier, Circle, Curve, Path, Shape, Vector}


object Lizard {


  private val lizardBeziers = Seq(
    Bezier(Vector(0.020, 0.050), // 2
      Vector(0.030, 0.120),
      Vector(0.025, 0.185)
    ),
    Bezier(Vector(0.100, 0.120), // 3
      Vector(0.200, 0.085),
      Vector(0.310, 0.090)
    ),
    Bezier(Vector(0.310, 0.000), // 4
      Vector(0.310, -0.100),
      Vector(0.310, -0.313)
    ),
    Bezier(Vector(0.450, -0.170), // 5
      Vector(0.500, -0.100),
      Vector(0.625, 0.070)
    ),
    Bezier(Vector(0.700, 0.040), // 6
      Vector(0.780, 0.010),
      Vector(0.850, 0.000)
    ),
    Bezier(Vector(0.700, -0.070), // 7
      Vector(0.563, -0.180),
      Vector(0.563, -0.313)
    ),
    Bezier(Vector(0.680, -0.310), // 8
      Vector(0.780, -0.410),
      Vector(0.813, -0.375)
    ),
    Bezier(Vector(0.792, -0.333), // 9
      Vector(0.771, -0.292),
      Vector(0.750, -0.250)
    ),
    Bezier(Vector(0.800, -0.200), // 10
      Vector(0.900, -0.100),
      Vector(1.000, 0.000)
    ),
    Bezier(Vector(0.900, 0.100), // 11
      Vector(0.800, 0.200),
      Vector(0.750, 0.250)
    ),
    Bezier(Vector(0.900, 0.650), // 12
      Vector(1.050, 0.750),
      Vector(1.250, 0.850)
    ),
    Bezier(Vector(1.200, 0.940), // 13
      Vector(1.100, 0.980),
      Vector(1.000, 1.000)
    ),
    Bezier(Vector(0.980, 0.900), // 14
      Vector(0.940, 0.800),
      Vector(0.850, 0.750)
    ),
    Bezier(Vector(0.750, 0.950), // 15
      Vector(0.650, 1.100),
      Vector(0.250, 1.250)
    ),
    Bezier(Vector(0.200, 1.200), // 16
      Vector(0.100, 1.100),
      Vector(0.000, 1.000)
    ),
    Bezier(Vector(0.050, 0.950), // 17
      Vector(0.150, 0.850),
      Vector(0.250, 0.750)
    ),
    Bezier(Vector(0.375, 0.813), // 18
      Vector(0.375, 0.813),
      Vector(0.375, 0.813)
    ),
    Bezier(Vector(0.410, 0.780), // 19
      Vector(0.310, 0.680),
      Vector(0.313, 0.563)
    ),
    Bezier(Vector(0.180, 0.563), // 20
      Vector(0.070, 0.700),
      Vector(0.000, 0.850)
    ),
    Bezier(Vector(-0.010, 0.780), // 21
      Vector(-0.040, 0.700),
      Vector(-0.070, 0.625)
    ),
    Bezier(Vector(0.100, 0.500), // 22
      Vector(0.170, 0.450),
      Vector(0.313, 0.310)
    ),
    Bezier(Vector(0.100, 0.310), // 23
      Vector(0.000, 0.310),
      Vector(-0.090, 0.310)
    ),
    Bezier(Vector(-0.085, 0.200), // 24
      Vector(-0.120, 0.100),
      Vector(-0.185, 0.025)
    ),
    Bezier(Vector(-0.120, 0.030), // 0
      Vector(-0.050, 0.020),
      Vector(0.000, 0.000)
    ))

  private val lizardPath = Path(Vector(0.000, 0.000), lizardBeziers)

  private val lizardEyeOuterCircles: Seq[Shape] = Seq(
    Circle(Vector(0.260, 1.100), Vector(0.070, 0.0)),
    Circle(Vector(0.260, 0.900), Vector(0.070, 0.0))
  )

  private val lizardEyeInnerCircles: Seq[Shape] = Seq(
    Circle(Vector(0.260, 1.100), Vector(0.050, 0.0)),
    Circle(Vector(0.260, 0.900), Vector(0.050, 0.0))
  )

  private val mainSpineCurves: Seq[Shape] = Seq(
    //main spine
    Curve(Vector(0.350, -0.200),
      Vector(0.700, 0.900),
      Vector(0.650, 1.000),
      Vector(0.075, 1.000)
    )
  )

  private def namedShapes(name: String, shapes: Seq[Shape]): Seq[(String, Shape)] = shapes.map ((name, _))

  val lizardShapes = Seq(
    ("primary", lizardPath)
  ) ++
    namedShapes("secondary", mainSpineCurves) ++
    namedShapes("secondary", lizardEyeOuterCircles)++
    namedShapes("primary", lizardEyeInnerCircles)
}
