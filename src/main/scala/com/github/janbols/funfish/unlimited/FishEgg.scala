package com.github.janbols.funfish.unlimited

import com.github.janbols.funfish.{Bezier, Curve, Path, Shape, Vector}


object FishEgg {

  private val eggfishyBeziers = Seq(
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


  private val eggfishyPath = Path(Vector(0.000, 0.000), eggfishyBeziers)

  private val eggfishyLeftEyeBeziers = Seq(
    //right
    Bezier(Vector(0.040, 0.750),
      Vector(0.055, 0.710),
      Vector(0.065, 0.675)
    ),
    //bottom
    Bezier(Vector(0.040, 0.655),
      Vector(0.020, 0.630),
      Vector(0.000, 0.610)
    ),
    //left
    Bezier(Vector(-0.010, 0.660),
      Vector(-0.008, 0.720),
      Vector(0.010, 0.775)
    ))

  private val eggleftEyePath = Path(Vector(0.010, 0.775), eggfishyLeftEyeBeziers)

  private val eggfishyInnerLeftEyeBeziers = Seq(
    //right
    Bezier(Vector(0.034, 0.682),
      Vector(0.040, 0.670),
      Vector(0.044, 0.660)
    ),
    //bottom
    Bezier(Vector(0.037, 0.650),
      Vector(0.030, 0.643),
      Vector(0.025, 0.638)
    ),
    //left
    Bezier(Vector(0.021, 0.650),
      Vector(0.021, 0.675),
      Vector(0.025, 0.690)
    ))

  private val egginnerLeftEyePath = Path(Vector(0.025, 0.690), eggfishyInnerLeftEyeBeziers)

  private val eggfishyRightEyeBeziers = Seq(
    //right
    Bezier(Vector(0.145, 0.845),
      Vector(0.175, 0.810),
      Vector(0.195, 0.782)
    ),
    //bottom
    Bezier(Vector(0.165, 0.760),
      Vector(0.140, 0.740),
      Vector(0.122, 0.720)
    ),
    //left
    Bezier(Vector(0.108, 0.760),
      Vector(0.100, 0.820),
      Vector(0.105, 0.860)
    ))

  private val eggrightEyePath = Path(Vector(0.105, 0.860), eggfishyRightEyeBeziers)

  private val eggfishyInnerRightEyeBeziers = Seq(
    //right
    Bezier(Vector(0.155, 0.780),
      Vector(0.162, 0.770),
      Vector(0.168, 0.763)
    ),
    //bottom
    Bezier(Vector(0.159, 0.758),
      Vector(0.149, 0.750),
      Vector(0.145, 0.747)
    ),
    //left
    Bezier(Vector(0.142, 0.760),
      Vector(0.140, 0.776),
      Vector(0.141, 0.787)
    ))

  private val egginnerRightEyePath = Path(Vector(0.141, 0.787), eggfishyInnerRightEyeBeziers)

  private val mainSpineCurves = Seq(
    //main spine
    Curve(Vector(0.840, 0.070),
      Vector(0.350, 0.120),
      Vector(0.140, 0.500),
      Vector(0.025, 0.900)
    ))

  private val finStemCurves = Seq(
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
    ))

  private val tailFinCurves = Seq(
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
    ))

  private val finDetailCurves = Seq(
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
    ))

  private def namedCurves(name: String, curves: Seq[Curve]): Seq[(String, Curve)] = curves.map((name, _))

  private val mainSpine = namedCurves("main-spine", mainSpineCurves)
  private val tailFin = namedCurves("tail-fin", tailFinCurves)
  private val finStem = namedCurves("fin-stem", finStemCurves)
  private val finDetails = namedCurves("fin-details", finDetailCurves)

  val fisheggShapes: Seq[(String, Shape)] = Seq(
    ("primary", eggfishyPath)
    , ("egg-eye-outer", eggleftEyePath)
    , ("egg-eye-outer", eggrightEyePath)
    , ("egg-eye-inner", egginnerLeftEyePath)
    , ("egg-eye-inner", egginnerRightEyePath)
  ) ++
    mainSpine ++
    tailFin ++
    finStem ++
    finDetails
}
