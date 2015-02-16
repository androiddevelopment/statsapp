package com.gaastats.util

import scala.collection.mutable
import scala.collection.JavaConversions._

/**
 * Created by David on 2/15/2015.
 */
object Converters {

  implicit def convertBufferToList[T](buffer: mutable.Buffer[T]): List[T] = {
    buffer.toList
  }

  implicit def convertJavaListToList[T](list: java.util.List[T]): List[T] = {
    convertBufferToList(list)
  }

}
