package org.geekbank.mq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivemqApplication {


    public static void main(String[] args) {
        try {
            testTopic();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testTopic() throws JMSException {
        // 创建队列目标，并表示队列名称，消费者根据队列名称接受数据
        final Destination destination = new ActiveMQTopic("test.topic");

        // 创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 创建连接
        Connection connection = factory.createConnection();
        // 打开连接
        connection.start();
        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        final AtomicInteger count = new AtomicInteger(0);
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    System.out.println(count.incrementAndGet() + " => receive from " + destination.toString() + " : " + message.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        // 绑定消费监听器
        consumer.setMessageListener(listener);


        // 创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        // 向队列推送10个文本消息数据
        for (int i = 0; i < 10; i++) {
            // 创建文本消息
            TextMessage message = session.createTextMessage("第" + i + "个文本消息");
            // 发送消息
            producer.send(message);
            System.out.println("已发送的消息：" + message.getText());
        }
        // 关闭的连接
        /*session.close();
        connection.close();*/

    }
}
