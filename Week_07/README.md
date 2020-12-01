学习笔记<br>
lesson 13<br>
原生的jdbc使用批处理的方式进行提交，100w的数据，耗时将近4分钟<br>

lesson 14<br>
自定义的实现读写分离还未实现，只是配置了多个数据源<br>

使用ShardingSphere Jdbc框架实现读写分离，确实使用很方便，只需要配置多个数据源，然后配置简单的规则即可自动实现读写分离<br>
规则代码片段：<br>
rules:<br>
- !REPLICA_QUERY<br>
  dataSources:<br>
    ds0:<br>
      primaryDataSourceName: master<br>
      replicaDataSourceNames: [slave]<br>
      loadBalancerName: roundRobin<br>
  loadBalancers:<br>
    roundRobin:<br>
      type: ROUND_ROBIN<br>