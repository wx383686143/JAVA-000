# Kafka学习笔记

* 启动kafka  
    1. 进入kafka bin目录，启动zookeeper  
    zookeeper-server-start.bat D:\kafka\kafka_2.12-2.6.0\config\zookeeper.properties
    2. 启动kafka  
    kafka-server-start.bat D:\kafka\kafka_2.12-2.6.0\config\kafka9001.properties
    
* 搭建集群
    1. 在config目录新建三个配置文件，三个修改点
    ```
    broker.id=1    # 1、2、3
    listeners=PLAINTEXT://:9001    # 9001、9002、9003
    log.dirs=/tmp/kafka-logs1    # kafka-logs1、kafka-logs2、kafka-logs3   
    ```
    