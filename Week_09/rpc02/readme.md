<h1>问题记录</h1>

1. TCC问题<br/>
2020-12-22 01:13:50.858 ERROR 16220 --- [           main] org.dromara.hmily.spi.ExtensionLoader    : not found service provider for : org.dromara.hmily.core.service.HmilyTransactionHandlerFactory<br/>
报了一个错误，待解决

2. 疑问?<br/>
provider try异常,try->cancel<br/>
consumer正常,try->confirm

3. confirm、cancel参数需要与try参数保持一致，否则无法识别