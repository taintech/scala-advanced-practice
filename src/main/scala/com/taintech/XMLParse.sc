import scala.xml.XML
import com.taintech.XMLDeserializer._

val xml = XML.loadString(example)
XML.save("/Users/taintech/Downloads/test.xml", xml)
//val res = deserialize(xml)
//Console println res
val foMargin = <Property Name="FO_Margin" Undefined="java.math.BigDecimal"/>
deserializeProperty(foMargin)
val baseCurrency = <Property Name="BaseCurrency"><String>EUR</String></Property>
deserializeProperty(baseCurrency)
