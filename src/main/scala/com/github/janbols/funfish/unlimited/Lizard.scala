package com.github.janbols.funfish.unlimited

import com.github.janbols.funfish.{Bezier, Circle, Curve, Path, Shape}


object Lizard {


  private val lizardBeziers = Seq(
    Bezier((0.020, 0.050), // 2
      (0.030, 0.120),
      (0.025, 0.185)
    ),
    Bezier((0.100, 0.120), // 3
      (0.200, 0.085),
      (0.310, 0.090)
    ),
    Bezier((0.310, 0.000), // 4
      (0.310, -0.100),
      (0.310, -0.313)
    ),
    Bezier((0.450, -0.170), // 5
      (0.500, -0.100),
      (0.625, 0.070)
    ),
    Bezier((0.700, 0.040), // 6
      (0.780, 0.010),
      (0.850, 0.000)
    ),
    Bezier((0.700, -0.070), // 7
      (0.563, -0.180),
      (0.563, -0.313)
    ),
    Bezier((0.680, -0.310), // 8
      (0.780, -0.410),
      (0.813, -0.375)
    ),
    Bezier((0.792, -0.333), // 9
      (0.771, -0.292),
      (0.750, -0.250)
    ),
    Bezier((0.800, -0.200), // 10
      (0.900, -0.100),
      (1.000, 0.000)
    ),
    Bezier((0.900, 0.100), // 11
      (0.800, 0.200),
      (0.750, 0.250)
    ),
    Bezier((0.900, 0.650), // 12
      (1.050, 0.750),
      (1.250, 0.850)
    ),
    Bezier((1.200, 0.940), // 13
      (1.100, 0.980),
      (1.000, 1.000)
    ),
    Bezier((0.980, 0.900), // 14
      (0.940, 0.800),
      (0.850, 0.750)
    ),
    Bezier((0.750, 0.950), // 15
      (0.650, 1.100),
      (0.250, 1.250)
    ),
    Bezier((0.200, 1.200), // 16
      (0.100, 1.100),
      (0.000, 1.000)
    ),
    Bezier((0.050, 0.950), // 17
      (0.150, 0.850),
      (0.250, 0.750)
    ),
    Bezier((0.375, 0.813), // 18
      (0.375, 0.813),
      (0.375, 0.813)
    ),
    Bezier((0.410, 0.780), // 19
      (0.310, 0.680),
      (0.313, 0.563)
    ),
    Bezier((0.180, 0.563), // 20
      (0.070, 0.700),
      (0.000, 0.850)
    ),
    Bezier((-0.010, 0.780), // 21
      (-0.040, 0.700),
      (-0.070, 0.625)
    ),
    Bezier((0.100, 0.500), // 22
      (0.170, 0.450),
      (0.313, 0.310)
    ),
    Bezier((0.100, 0.310), // 23
      (0.000, 0.310),
      (-0.090, 0.310)
    ),
    Bezier((-0.085, 0.200), // 24
      (-0.120, 0.100),
      (-0.185, 0.025)
    ),
    Bezier((-0.120, 0.030), // 0
      (-0.050, 0.020),
      (0.000, 0.000)
    ))

  private val lizardPath = Path((0.000, 0.000), lizardBeziers)

  private val lizardEyeOuterCircles: Seq[Shape] = Seq(
    Circle((0.260, 1.100), (0.070, 0.0)),
    Circle((0.260, 0.900), (0.070, 0.0))
  )

  private val lizardEyeInnerCircles: Seq[Shape] = Seq(
    Circle((0.260, 1.100), (0.050, 0.0)),
    Circle((0.260, 0.900), (0.050, 0.0))
  )

  private val mainSpineCurves: Seq[Shape] = Seq(
    //main spine
    Curve((0.350, -0.200),
      (0.700, 0.900),
      (0.650, 1.000),
      (0.075, 1.000)
    )
  )

  private def namedShapes(name: String, shapes: Seq[Shape]): Seq[(String, Shape)] = shapes.map ((name, _))

  val lizardShapes = Seq(
    ("primary", lizardPath)
  ) ++
    namedShapes("secondary", mainSpineCurves) ++
    namedShapes("secondary", lizardEyeOuterCircles)++
    namedShapes("primary", lizardEyeInnerCircles)

  val lpLizard = LensPicture(lizardShapes:_*)
}
