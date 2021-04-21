package cn.edu.zut.bigdata.sparkstreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreamingTelnet extends App{
  val conf = new SparkConf().setAppName("stream").setMaster("Local[3]")
  val ssc = new StreamingContext(conf,Seconds(10))
  val lineDStream = ssc.socketTextStream("node03",8888)
  val result = lineDStream.flatMap(_.split("")).map((_,1)).reduceByKey(_+_)
  result.print()
  ssc.start()
  ssc.awaitTermination()
}
