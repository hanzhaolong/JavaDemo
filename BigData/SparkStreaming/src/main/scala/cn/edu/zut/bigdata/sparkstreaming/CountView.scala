package cn.edu.zut.bigdata.sparkstreaming

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object CountView extends App {
  //创建配置对象
  val conf = new SparkConf().setAppName("kafka2streaming").setMaster("local[3]")
  //创建StreamingContext对象
  val ssc = new StreamingContext(conf,Seconds(5))
  //重要：检查点目录的配置
  ssc.sparkContext.setCheckpointDir("F:\\cd\\data")
  //获取数据的主题
  val fromTopic = "logsys"
  val brokers = "node01:9092,node02:9092,node03:9092"
  //创建kafka连接参数
  val kafkaParams = Map[String,Object](
    //集群地址
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG->brokers,
    //Key与VALUE的反序列化类型
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG->classOf[StringDeserializer],
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG->classOf[StringDeserializer],
    //创建消费者组
    ConsumerConfig.GROUP_ID_CONFIG->"CountView",
    //自动移动到最新的偏移量
    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG->"latest"
  )
  //获取DStream
  val dStream = KafkaUtils.createDirectStream(
    ssc,//SparkStreaming操作对象
    LocationStrategies.PreferConsistent,//数据读取之后如何分布在各个分区上
    ConsumerStrategies.Subscribe[String,String](Array(fromTopic),kafkaParams)
  )
  //处理DStream的数据——业务逻辑
  //将数据叠加起来，计算总和
  val count = dStream.map(record=>{
    var strs = record.value().split(" ")
    (strs(4).replace("[",""),strs(11))
  }).filter(t=>{
    if(t._2.equals("200")) true
    else false
  }).map(t=>{
    ((t._1),1)
  });
  val state = count.updateStateByKey[Int]((values:Seq[Int],state:Option[Int])=>{
    // values为当前批次单词频数
    // state为之前批次单词频数
    val currentCount = values.foldLeft(0)(_+_)
    //getOrElse如果有值则获取值，如果没有则使用默认值
    val stateCount = state.getOrElse(0)
    //返回新的状态
    Some(currentCount+stateCount)
  })


  state.print()
  //结果输出
  //result.print

  //启动
  ssc.start()
  ssc.awaitTermination()

}
