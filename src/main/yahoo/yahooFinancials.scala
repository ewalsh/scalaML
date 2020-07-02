packages ai.economicdatasciences

type Features = Array[Double]
type Weights = Array[Double]
type ObsSet = Vector[Features]
type Fields = Array[String]

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
        val data = extract(src.getLines.map(_.split(",")).drop())
    }

}
