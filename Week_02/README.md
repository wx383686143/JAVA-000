1.串行，并行，cms，G1分别在Xmx512、1g、2g执行5次取平均值
a>在512m和1g的情况，并行GC生成的对象都是最少的，G1>CMS>串行>并行
b>在2g的情况，并行>G1>串行>CMS

2.使用sb压测，在串行，并行，CMS，G1 
2g情况并行gc吞吐量不如cms，g1最佳
4g情况并行gc要优于cms，g1还是最佳
不知道为啥切换不了串行gc，切换之后，使用jmap查看还是cms


