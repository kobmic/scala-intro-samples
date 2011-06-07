import sbt._
import java.io.File
import scala.io.Source

class Stack(info: ProjectInfo) extends DefaultProject(info) {
  // dependencies
  val scalatest = "org.scalatest" %% "scalatest" % "1.4.1" % "test"

  // helper functions
  def recursiveListFiles(f: File): Array[File] = {
    val these = f.listFiles
    these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
  }


  def lineCount(file: File) : Long = {
    val src = Source.fromFile(file)
    val count = src.getLines.foldLeft(0) { (i, line) => i + 1 }
    println(file.getAbsolutePath + " loc: " + count)
    count
  }

  // custom tasks
  // hello
  lazy val sayHello = task { println("Hello sbt!"); None } dependsOn(test) describedAs("Hello World")
  
  // printDeps
  lazy val printDeps = task {     recursiveListFiles(new File("lib_managed")).filter(!_.isDirectory).foreach(println(_))    None  } dependsOn(update) describedAs("print all dependecies to console") 
  
  // lines of code
  lazy val linesOfCode = task {     val files = recursiveListFiles(new File("src"))
    val sum = files.filter(!_.isDirectory).map(lineCount(_)).reduceLeft((l1,l2) => l1 + l2)
    println("Lines of code: " + sum)    None  } describedAs("print lines of source code to console") 
   
}
