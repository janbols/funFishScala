package com.github.janbols.funfish.unlimited

import com.github.janbols.funfish.{Bezier, Curve, Path, Vector}


object Fishier {

  private val fishyBeziers = Seq(
    Bezier(Vector(0.110, 0.110),
      Vector(0.175, 0.175),
      Vector(0.250, 0.250)
    ),
    Bezier(Vector(0.372, 0.194),
      Vector(0.452, 0.132),
      Vector(0.564, 0.032)
    ),
    Bezier(Vector(0.730, 0.056),
      Vector(0.834, 0.042),
      Vector(1.000, 0.000)
    ),
    Bezier(Vector(0.896, 0.062),
      Vector(0.837, 0.107),
      Vector(0.766, 0.202)
    ),
    Bezier(Vector(0.660, 0.208),
      Vector(0.589, 0.217),
      Vector(0.500, 0.250)
    ),
    Bezier(Vector(0.500, 0.410),
      Vector(0.500, 0.460),
      Vector(0.500, 0.500)
    ),
    Bezier(Vector(0.500, 0.575),
      Vector(0.500, 0.625),
      Vector(0.500, 0.750)
    ),
    Bezier(Vector(0.411, 0.783),
      Vector(0.340, 0.792),
      Vector(0.234, 0.798)
    ),
    Bezier(Vector(0.163, 0.893),
      Vector(0.104, 0.938),
      Vector(0.000, 1.000)
    ),
    Bezier(Vector(-0.042, 0.834),
      Vector(-0.056, 0.730),
      Vector(-0.032, 0.564)
    ),
    Bezier(Vector(-0.132, 0.452),
      Vector(-0.194, 0.372),
      Vector(-0.250, 0.250)
    ),
    Bezier(Vector(-0.150, 0.150),
      Vector(-0.050, 0.050),
      Vector(0.000, 0.000)
    )
  )

  private val fishyPath = Path(Vector(0.000, 0.000), fishyBeziers)

  private val fishyLeftEyeBeziers = Seq(
    Bezier(Vector(0.040, 0.772),
      Vector(0.068, 0.696),
      Vector(0.074, 0.685)
    ),

    Bezier(Vector(0.045, 0.660),
      Vector(0.010, 0.617),
      Vector(-0.008, 0.592)
    ),

    Bezier(Vector(-0.017, 0.685),
      Vector(-0.012, 0.770),
      Vector(0.004, 0.800)
    )
  )

  private val leftEyePath = Path(Vector(0.004, 0.800), fishyLeftEyeBeziers)

  private val fishyInnerLeftEyeBeziers = Seq(
    Bezier(Vector(0.038, 0.708),
      Vector(0.053, 0.684),
      Vector(0.057, 0.674)
    ),

    Bezier(Vector(0.035, 0.652),
      Vector(0.010, 0.622),
      Vector(0.008, 0.618)
    ),

    Bezier(Vector(0.005, 0.685),
      Vector(0.010, 0.700),
      Vector(0.018, 0.720)
    )
  )

  private val innerLeftEyePath = Path(Vector(0.018, 0.720), fishyInnerLeftEyeBeziers)

  private val fishyRightEyeBeziers = Seq(
    Bezier(Vector(0.160, 0.840),
      Vector(0.200, 0.790),
      Vector(0.205, 0.782)
    ),

    Bezier(Vector(0.165, 0.760),
      Vector(0.140, 0.740),
      Vector(0.115, 0.715)
    ),

    Bezier(Vector(0.095, 0.775),
      Vector(0.090, 0.830),
      Vector(0.095, 0.870)
    )
  )

  private val rightEyePath = Path(Vector(0.095, 0.870), fishyRightEyeBeziers)

  private val fishyInnerRightEyeBeziers = Seq(
    Bezier(Vector(0.150, 0.805),
      Vector(0.174, 0.783),
      Vector(0.185, 0.774)
    ),

    Bezier(Vector(0.154, 0.756),
      Vector(0.139, 0.740),
      Vector(0.132, 0.736)
    ),

    Bezier(Vector(0.126, 0.760),
      Vector(0.122, 0.795),
      Vector(0.128, 0.810)
    )
  )

  private val innerRightEyePath = Path(Vector(0.128, 0.810), fishyInnerRightEyeBeziers)

  private val fishySpineCurves = Seq(
    //main spine
    Curve(Vector(0.840, 0.070),
      Vector(0.350, 0.120),
      Vector(0.140, 0.500),
      Vector(0.025, 0.900)
    ),
    //left fin stem
    Curve(Vector(-0.015, 0.520),
      Vector(0.040, 0.400),
      Vector(0.120, 0.300),
      Vector(0.210, 0.260)
    ),
    //right fin stem
    Curve(Vector(0.475, 0.270),
      Vector(0.320, 0.350),
      Vector(0.340, 0.600),
      Vector(0.240, 0.770)
    ),
    //right fin bottom delimiter
    Curve(Vector(0.377, 0.377),
      Vector(0.410, 0.410),
      Vector(0.460, 0.460),
      Vector(0.495, 0.495)
    ),
    //tail fin stem
    Curve(Vector(0.430, 0.165),
      Vector(0.480, 0.175),
      Vector(0.490, 0.220),
      Vector(0.490, 0.230)
    ),
    //tail fin bottom line
    Curve(Vector(0.452, 0.178),
      Vector(0.510, 0.130),
      Vector(0.540, 0.110),
      Vector(0.600, 0.080)
    ),
    //tail fin top line
    Curve(Vector(0.482, 0.215),
      Vector(0.520, 0.200),
      Vector(0.600, 0.160),
      Vector(0.740, 0.150)
    ),
    //left fin top line
    Curve(Vector(-0.170, 0.237),
      Vector(-0.125, 0.355),
      Vector(-0.065, 0.405),
      Vector(0.010, 0.480)
    ),
    //left fin middle line
    Curve(Vector(-0.110, 0.175),
      Vector(-0.060, 0.250),
      Vector(-0.030, 0.300),
      Vector(0.080, 0.365)
    ),
    //left fin bottom line
    Curve(Vector(-0.045, 0.115),
      Vector(0.010, 0.180),
      Vector(0.060, 0.230),
      Vector(0.170, 0.280)
    ),
    //right fin top line
    Curve(Vector(0.270, 0.700),
      Vector(0.340, 0.720),
      Vector(0.426, 0.710),
      Vector(0.474, 0.692)
    ),
    //right fin middle line
    Curve(Vector(0.310, 0.570),
      Vector(0.400, 0.622),
      Vector(0.435, 0.618),
      Vector(0.474, 0.615)
    ),
    //right fin bottom line
    Curve(Vector(0.350, 0.435),
      Vector(0.400, 0.505),
      Vector(0.422, 0.520),
      Vector(0.474, 0.538)
    )
  )

  val fishShapes = Seq(
    ("primary", fishyPath),
    ("eye-outer", leftEyePath),
    ("eye-outer", rightEyePath),
    ("eye-inner", innerLeftEyePath),
    ("eye-inner", innerRightEyePath)
  ) ++ fishySpineCurves.map(("secondary", _))

}
