package rostelecom.sha.hrmtech

import rostelecom.sha.hrmtech2.model.ReadXML

import scala.collection.mutable.ListBuffer

class ValuesArray {

  val pos = new ListBuffer[String]
  val docs = Array("doc1", "doc2", "doc3", "doc4",
  "doc5", "doc6", "doc7", "doc8", "doc9", "doc10")


  def listTen(xs: String): Array[String] = {

    for (doc <- docs) {
      val readXML = new ReadXML
      readXML.values(doc, xs)
      pos += readXML.getNumber
      pos += readXML.getDepartment
      pos += readXML.getCentrality
    }
    return pos.toArray

  }



}
