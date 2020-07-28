package ai.economicdatasciences.yahoo

import scala.util.Try
import scala.io.Source

package object Ch1 {
    type Fields = Array[String]
    type DblPair = (Double, Double)
    type Features = Array[Double]

    object YahooFinancials extends Enumeration {
        type YahooFinancials = Value

        val DATE, OPEN, HIGH, LOW, CLOSE, VOLUME, ADJ_CLOSE=Value

        def toDouble(v: Value): Fields => Double = {
            (s: Fields) => s(v.id).toDouble
        }

        def toArray(vs: Array[Value]): Fields => Features = {
            (s: Fields) => vs.map(v => s(v.id).toDouble)
        }

        def load(filename: String): Try[Vector[DblPair]] = Try {
            val src = Source.fromFile(filename)
            val data = extract(src.getLines.map(_.split(",")).drop(1))
            src.close
            data
        }

        def extract(cols: Iterator[Fields]): Vector[DblPair] = {
            val features = Array[YahooFinancials](LOW, HIGH, VOLUME)
            val conversion = toArray(features)
            cols.map(conversion(_)).toVector.map(x => Array[Double](1.0 - x(0)/x(1), x(2)))
        }

    }
}


// type Weights = Array[Double]
// type ObsSet = Vector[Features]

// object YahooFinancials extends Enumeration {

//

//

//
//     class MinMax[T: ToDouble](val values: Vector[T]) {
//         val zero = (Double.MaxValue, -Double.MaxValue)
//         val (min, max) = values./:(zero){ case ((mn, mx), x) => {
//             val z = implicitly[ToDouble[T]].apply(x)
//             (if(z < mn) z else mn, if(z > mx) z else mx)
//         }}
//
//         case class ScaleFactors(
//             low: Double, high: Double, ratio: Double
//         )
//
//         var scaleFactors: Option[ScaleFactors] = None
//
//         def normalize(low: Double, high: Double): Vector[Double] = {
//             setScaleFactors(low, high).map(scale => {
//                 values.map(x => {
//                     val z = implicitly[ToDouble[T]].apply(x)
//                     (z - min)*scale.ratio + scale.low
//                 })
//             }).getOrElse(/* ... */)
//
//             def setScaleFactors(l: Double, h: Double): Option[ScaleFactors] = {
//                 // .. error handling code
//                 Some(ScaleFactors(l, h, (h - l)/(max - min)))
//             }
//         }
//         def normalize(value: Double): Double = setScaleFactors.map(
//             scale =>
//                 if(value < min) scale.low
//                 else if (value > max) scale.high
//                 else (value - min) * scale.high + scale.low
//         ).getOrElse(/* ... */)
//
//         class MinMaxVector(series: Vector[Double]) {
//             val minMaxVector: Vector[MinMax[Double]] = series.transpose.map(new MinMax[Double](_))
//             def normalize(low: Double, high: Double): Vector[Double]
//         }
//
//
//     }
//
// }
