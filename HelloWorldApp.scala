package hello

import java.util._

import java.util.stream.Collectors

import Student._

import scala.beans.{BeanProperty, BooleanBeanProperty}

//remove if not needed
import scala.collection.JavaConversions._

object Student {

  @BeanProperty
  var studentCount: Int = 0

}

abstract class Student(@BeanProperty protected var name: String,
                       @BeanProperty var dateOfBirth: Date) {

  { studentCount += 1; studentCount - 1 }

  def this(dateOfBirth: Date) = this(null, dateOfBirth)

  def doWork(): Unit

}

trait GuitarPlayer {

  def playGuitar(): Unit

}

trait PianoPlayer {

  def playPiano(): Unit

}

class MusicStudent(name: String, dateOfBirth: Date)
    extends Student(dateOfBirth)
    with GuitarPlayer
    with PianoPlayer {

  super.setName(name)

  def playGuitar(): Unit = {
    println("Playing Guitar")
  }

  def playPiano(): Unit = {
    println("Playing Piano")
  }

  def doWork(): Unit = {
    this.playGuitar()
    this.playPiano()
  }

}

object HelloWorldApp {

  def main(args: Array[String]): Unit = {
    val student: Student = new MusicStudent("wimal", new Date())
    student.doWork()
// Lists
    val students: List[Student] = new ArrayList[Student]()
    students.add(student)
    val firstStudent: Student = students.get(0)
    firstStudent.doWork()
// Iterators
    val it: Iterator[Student] = students.iterator()
    while (it.hasNext) {
      val next: Student = it.next()
      next.doWork()
    }
// Maps
    val studentMap: Map[String, List[Student]] =
      new HashMap[String, List[Student]]()
    studentMap.put("musicStudents", students)
    val firstMusicStudent: Student = studentMap.get("musicStudents").get(0)
    firstMusicStudent.doWork()
// Arrays
    val studentsArray: Array[Student] = Array.ofDim[Student](1)
    studentsArray(0) = student
    studentsArray(0).doWork()
// 2D Arrays
    val students2DArray: Array[Array[Student]] = Array.ofDim[Student](1, 1)
    students2DArray(0)(0) = student
    println(students2DArray(0)(0).getName)
// Lambda
    val result: List[Student] = students
      .stream()
      .filter((lmbStudent) => ("wimal" == lmbStudent.getName))
      .collect(Collectors.toList())
    println(result.size)
    println(Student.getStudentCount)
  }

}

