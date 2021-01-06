#Redis学习笔记  
1、配置redis的主从复制，sentinel高可用，Cluster集群。  
提交如下内容到github：  
1）config配置文件，  
2）启动和操作、验证集群下数据读写的命令步骤。  

环境配置：win7，docker  
* 主从复制  
    使用docker toolbox依次执行如下命令  
    `docker run -p 6378:6379 --name redis01 -d redis redis-server`  
    `docker run -p 6377:6379 --name redis02 -d redis redis-server`  
    `docker exec -it redis01 redis-cli`  
    ~~~
    Administrator@USER-20150610EB MINGW64 /d/Program Files/Docker Toolbox
    $ docker run -p 6378:6379 --name redis01 -d redis redis-server
    be4f68abe54ac3356c224c4d3df346718b54f73ba4b42ea9de256a40353cbf06
    
    Administrator@USER-20150610EB MINGW64 /d/Program Files/Docker Toolbox
    $ docker run -p 6377:6379 --name redis02 -d redis redis-server
    5b12bf3259400d4b88ee92e94300f46d132635e6c9b835807decd623c7a0cf68
    
    Administrator@USER-20150610EB MINGW64 /d/Program Files/Docker Toolbox
    $ docker exec -it redis01 redis-cli
    127.0.0.1:6379> set cc 92
    OK
    127.0.0.1:6379> get cc
    "92"
    ~~~
    
    执行完成之后，再开启另外一个窗口(docker启动默认ip：192.168.99.100)  
    `docker exec -it redis02 redis-cli`  
    `slaveof 192.168.99.100 6378`(把redis02设置为redis01的从机器)  
    ~~~
    Administrator@USER-20150610EB MINGW64 /d/Program Files/Docker Toolbox
    $ docker exec -it redis02 redis-cli
    127.0.0.1:6379> slaveof 192.168.99.100 6378
    OK
    127.0.0.1:6379> keys *
    (empty array)
    127.0.0.1:6379> set cc 92
    (error) READONLY You can't write against a read only replica.
    127.0.0.1:6379> get cc
    "92"
    ~~~
* sentinel高可用
    
* cluster集群    
    



