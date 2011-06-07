package com.jayway.sample

object XMLSample {
  case class Person(name: String, age: Int) {
    def toXml = <person>
                  <name>{ name }</name>
                  <age>{ age }</age>
                </person>
  }
  def main(args: Array[String]) {
    import scala.xml.{ Node, XML }
    val persons = List(Person("Bob", 22), Person("Tom", 31), Person("Sally", 29))
    val xml: Node = <employees>{ for (person <- persons) yield person.toXml }</employees>
    val names = (xml \\ "name").map(elem => elem.text)
    println(names)

    // save
    XML.saveFull("/Users/michaelkober/employees.xml", xml, "UTF-8", true, null)
    // load
    val xml2 = XML.loadFile("/Users/michaelkober/employees.xml")
  }
}