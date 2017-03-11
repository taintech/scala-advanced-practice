package com.taintech

import scala.xml.{Elem, Node}

/**
  * Author: Rinat Tainov 
  * Date: 02/03/2017
  */
object XMLDeserializer {


  case class Property(name: String, value: Option[Any], javaType: String)
  object Property{
    def undefined(name: String, javaType: String) = Property(name, None, javaType)
  }
  case class Underlying(properties: List[Property])
  case class Position(properties: List[Property], underlying: Underlying)
  case class Account(properties: List[Property], positions: List[Position])

  val example: String =
    """<Account>
      | <Property Name="BaseCurrency">
      |   <String>EUR</String>
      | </Property>
      | <Property Name="CCWlist">
      |   <Set>
      |     <String>42</String>
      |     <String>43</String>
      |     <String>44</String>
      |   </Set>
      | </Property>
      | <Property Name="FO_Margin" Undefined="java.math.BigDecimal"/>
      | <Property Name="NAV">
      |   <BigDecimal>234.07</BigDecimal>
      | </Property>
      | <Property Name="positions">
      |   <List>
      |     <Position>
      |       <Property Name="PositionID">
      |         <String>13</String>
      |       </Property>
      |       <Property Name="BBGTicker"><String/></Property>
      |       <Property Name="Price">
      |         <BigDecimal>0E-19</BigDecimal>
      |       </Property>
      |       <Property Name="IndexId">
      |         <String/>
      |       </Property>
      |       <Property Name="IsBanned">
      |         <Boolean>false</Boolean>
      |       </Property>
      |       <Property Name="Underlying">
      |         <Underlying>
      |                <Property Name="BBGTicker" Undefined="java.lang.String"/>
      |         </Underlying>
      |       </Property>
      |     </Position>
      |     <Position>
      |       <Property Name="PositionID">
      |         <String>14</String>
      |       </Property>
      |       <Property Name="BBGTicker"><String>AAPL US Equity</String></Property>
      |       <Property Name="Price">
      |         <BigDecimal>1243897.123</BigDecimal>
      |       </Property>
      |       <Property Name="IndexId">
      |         <String/>
      |       </Property>
      |       <Property Name="IsBanned">
      |         <Boolean>true</Boolean>
      |       </Property>
      |       <Property Name="Underlying">
      |         <Underlying>
      |                <Property Name="BBGTicker">
      |                 <String>BB US Equity</String>
      |                </Property>
      |         </Underlying>
      |       </Property>
      |     </Position>
      |   </List>
      | </Property>
      |</Account>""".stripMargin


  def deserializeAccount(xml: Node): Option[Account] = xml match {
//    case <Account>{properties @_*}</Account> =>
//      (for {
//        property <- properties
//        name = property \ "@Name"
//        if name.nonEmpty
//      } yield name).toString()
    case _ => None
  }

  /** XMLStats(Map(),Map(),Map(
    * Set -> 1,
    * Position -> 2,
    * Boolean -> 2,
    * Underlying -> 2,
    * List -> 1,
    * Property -> 19,
    * String -> 11,
    * BigDecimal -> 3,
    * Account -> 1),
    * Map(Attribute(Name,BBGTicker) -> 4,
    * Attribute(Name,positions) -> 1,
    * Attribute(Name,Underlying) -> 2, Attribute(Name,CCWlist) -> 1,
    * Attribute(Undefined,java.lang.String) -> 1, Attribute(Name,BaseCurrency) -> 1,
    * Attribute(Name,Price) -> 2, Attribute(Name,PositionID) -> 2,
    * Attribute(Undefined,java.math.BigDecimal) -> 1, Attribute(Name,NAV) -> 1,
    * Attribute(Name,FO_Margin) -> 1, Attribute(Name,IndexId) -> 2,
    * Attribute(Name,IsBanned) -> 2))
    */

  def deserializeProperty(xml: Node): Option[Property] = xml match {
    case property @ <Property/> => {
      val name = property \ "@Name"
      val undefined = property \ "@Undefined"
      if (undefined.nonEmpty) Some(Property.undefined(name.toString(), undefined.toString()))
      else {
        println("Something strange in undefined case:")
        println(property)
        None
      }
    }
    case property @ <Property>{_*}</Property> =>
      val name = property \ "@Name"
      property.nonEmptyChildren.headOption match {
        case Some(<String>{v}</String>) => Some(Property(name.toString(), Some(v.toString()), "java.lang.String"))
        case other =>
          println("Undefined property in non empty property:")
          println(other)
          None
      }
    case other =>
      println("Not a property:")
      println(other)
      None
  }

}

