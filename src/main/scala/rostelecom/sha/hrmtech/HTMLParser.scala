package rostelecom.sha.hrmtech

import java.io._

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source



class HTMLParser {

  /**
    * Переменные для определения и извлечения даты, почты отправителей и получателей
    * позволяют получить данных необходимых колонок в исходном файле HTML
    */
  var n: Int = 0
  var n2: Int = 0
  var str: String = null




  /**
    * Вызывается класс PrintWriter для форматированного вывода данных,
    * в котором создается объект File для записи результата парсинга
    */
  val pw = new PrintWriter(new File("/home/sha/Documents/hrm/data.csv"))


  /**
    * Метод паринга исходного файла HTML и формирование необходимых данных в формат csv
    * @param xs - путь к файлу для парсинга
    * @param ys -  кодировка
    * @param zs - строка для поиска дат
    */
  def getMailAndDate(xs: String, ys: String, zs: String) = {

    for (line <- Source.fromFile(xs, ys).getLines()) {    //"cp866"

      if (line.length > 4) {
        if (line.substring(0, 5) == zs) {  //"2016-"
          n2 = n2 + 1
          if (n2 == 1)
            pw.write("\n" + line + ", ")

        } else if (line != null && line.contains("@")) {
          n = n + 1
          if (n == 1 && !line.contains(","))
            pw.write(line + ", ")
          else if (n == 2 && !line.contains(","))
            pw.write(line)
          else if (line.contains(",")) {
            if (n == 2)
              pw.write(line.replace(",", ""))
            if (n == 3)
              pw.write(line.replace(",", ""))
            if (n == 4)
              pw.write(line.replace(",", ""))
            if (n == 5)
              pw.write(line.replace(",", ""))
            if (n == 6)
              pw.write(line.replace(",", ""))
            if (n == 7)
              pw.write(line.replace(",", ""))
            if (n == 8)
              pw.write(line.replace(",", ""))
            if (n == 9)
              pw.write(line.replace(",", ""))
            if (n == 10)
              pw.write(line.replace(",", ""))
            if (n == 11)
              pw.write(line.replace(",", ""))
            if (n == 12)
              pw.write(line.replace(",", ""))
          }
        }
        else if (line.contains("/tr")) {
          pw.write(", ")
          n2 = 0
          n = 0
        }
      }
    }
    pw.close()
  }

  /**
    * Построчное чтение данных для поточного формирования JSON - объектов
    * @param xs - файл csv
    */

  def getDataForJSON(xs: String, ys: Int): String = {

    for (line <- Source.fromFile(xs).getLines()) {
      n = n + 1
      if ( n == ys)
        str = line;
    }
    return str;
  }
  def getValue(xs: String, ys: Int) : String = {
    for (line <- Source.fromFile(xs).getLines()) {
      n = n + 1
      if (n == ys) {
        n = 0
        return line
      }

    }
    return null
  }





}
