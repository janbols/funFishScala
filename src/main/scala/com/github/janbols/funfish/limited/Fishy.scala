package com.github.janbols.funfish.limited

import com.github.janbols.funfish
import com.github.janbols.funfish.{Curve, Vector, limited}


object Fishy {

    val hendersonFishShapes = Seq(
            funfish.Curve(Vector(0.116, 0.702),  //C1
                    Vector(0.260, 0.295),
                    Vector(0.330, 0.258),
                    Vector(0.815, 0.078)
            ),
            funfish.Curve(Vector(0.564, 0.032), // C2
                    Vector(0.730, 0.056),
                    Vector(0.834, 0.042),
                    Vector(1.000, 0.000)
            ),
            funfish.Curve(Vector(0.250, 0.250), // C3
                    Vector(0.372, 0.194),
                    Vector(0.452, 0.132),
                    Vector(0.564, 0.032)
            ),
            funfish.Curve(Vector(0.000, 0.000), // C4
                    Vector(0.110, 0.110),
                    Vector(0.175, 0.175),
                    Vector(0.250, 0.250)
            ),
            funfish.Curve(Vector(-0.250, 0.250), // C5
                    Vector(-0.150, 0.150),
                    Vector(-0.090, 0.090),
                    Vector(0.000, 0.000)
            ),
            funfish.Curve(Vector(-0.250, 0.250), // C6
                    Vector(-0.194, 0.372),
                    Vector(-0.132, 0.452),
                    Vector(-0.032, 0.564)
            ),
            funfish.Curve(Vector(-0.032, 0.564), // C7
                    Vector(0.055, 0.355),
                    Vector(0.080, 0.330),
                    Vector(0.250, 0.250)
            ),
            funfish.Curve(Vector(-0.032, 0.564), // C8
                    Vector(-0.056, 0.730),
                    Vector(-0.042, 0.834),
                    Vector(0.000, 1.000)
            ),
            funfish.Curve(Vector(0.000, 1.000), // C9
                    Vector(0.104, 0.938),
                    Vector(0.163, 0.893),
                    Vector(0.234, 0.798)
            ),
            funfish.Curve(Vector(0.234, 0.798), // C10
                    Vector(0.368, 0.650),
                    Vector(0.232, 0.540),
                    Vector(0.377, 0.377)
            ),
            funfish.Curve(Vector(0.377, 0.377), // C11
                    Vector(0.400, 0.350),
                    Vector(0.450, 0.300),
                    Vector(0.500, 0.250)
            ),
            funfish.Curve(Vector(0.500, 0.250), // C12
                    Vector(0.589, 0.217),
                    Vector(0.660, 0.208),
                    Vector(0.766, 0.202)
            ),
            funfish.Curve(Vector(0.766, 0.202), // C13
                    Vector(0.837, 0.107),
                    Vector(0.896, 0.062),
                    Vector(1.000, 0.000)
            ),
            funfish.Curve(Vector(0.234, 0.798), // C14
                    Vector(0.340, 0.792),
                    Vector(0.411, 0.783),
                    Vector(0.500, 0.750)
            ),
            funfish.Curve(Vector(0.500, 0.750), // C15
                    Vector(0.500, 0.625),
                    Vector(0.500, 0.575),
                    Vector(0.500, 0.500)
            ),
            funfish.Curve(Vector(0.500, 0.500), // C16 -
                    Vector(0.460, 0.460),
                    Vector(0.410, 0.410),
                    Vector(0.377, 0.377)
            ),
            funfish.Curve(Vector(0.315, 0.710), // C17 -
                    Vector(0.378, 0.732),
                    Vector(0.426, 0.726),
                    Vector(0.487, 0.692)
            ),
            funfish.Curve(Vector(0.340, 0.605), // C18 -
                    Vector(0.400, 0.642),
                    Vector(0.435, 0.647),
                    Vector(0.489, 0.626)
            ),
            funfish.Curve(Vector(0.348, 0.502), // C19 -
                    Vector(0.400, 0.564),
                    Vector(0.422, 0.568),
                    Vector(0.489, 0.563)
            ),
            funfish.Curve(Vector(0.451, 0.418), // C20 -
                    Vector(0.465, 0.400),
                    Vector(0.480, 0.385),
                    Vector(0.490, 0.381)
            ),
            funfish.Curve(Vector(0.421, 0.388), // C21 -
                    Vector(0.440, 0.350),
                    Vector(0.455, 0.335),
                    Vector(0.492, 0.325)
            ),
            funfish.Curve(Vector(-0.170, 0.237), // C22 -
                    Vector(-0.125, 0.355),
                    Vector(-0.065, 0.405),
                    Vector(0.002, 0.436)
            ),
            funfish.Curve(Vector(-0.121, 0.188), // C23 -
                    Vector(-0.060, 0.300),
                    Vector(-0.030, 0.330),
                    Vector(0.040, 0.375)
            ),
            funfish.Curve(Vector(-0.058, 0.125), // C24 -
                    Vector(-0.010, 0.240),
                    Vector(0.030, 0.280),
                    Vector(0.100, 0.321)
            ),
            funfish.Curve(Vector(-0.022, 0.063), // C25 -
                    Vector(0.060, 0.200),
                    Vector(0.100, 0.240),
                    Vector(0.160, 0.282)
            ),
            funfish.Curve(Vector(0.053, 0.658), // C26 -
                    Vector(0.075, 0.677),
                    Vector(0.085, 0.687),
                    Vector(0.098, 0.700)
            ),
            funfish.Curve(Vector(0.053, 0.658), // C27
                    Vector(0.042, 0.710),
                    Vector(0.042, 0.760),
                    Vector(0.053, 0.819)
            ),
            funfish.Curve(Vector(0.053, 0.819), // C28 -
                    Vector(0.085, 0.812),
                    Vector(0.092, 0.752),
                    Vector(0.098, 0.700)
            ),
            funfish.Curve(Vector(0.130, 0.718), // C29 -
                    Vector(0.150, 0.730),
                    Vector(0.175, 0.745),
                    Vector(0.187, 0.752)
            ),
            funfish.Curve(Vector(0.130, 0.718), // C30 -
                    Vector(0.110, 0.795),
                    Vector(0.110, 0.810),
                    Vector(0.112, 0.845)
            ),
            funfish.Curve(Vector(0.112, 0.845), // C31 -
                    Vector(0.150, 0.805),
                    Vector(0.172, 0.780),
                    Vector(0.187, 0.752)
            ))


}
