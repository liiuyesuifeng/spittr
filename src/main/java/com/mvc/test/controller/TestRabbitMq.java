package com.mvc.test.controller;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
public class TestRabbitMq {
    private static String url = "127.0.0.1";
    private static Integer port = 5672;
    private static String username = "root";
    private static String password = "123456";
    @Test
    public void testProduct() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(url);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明一个队列    queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
        // 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare("test",false,false,false,null);
        String message = "Hello RabbitMQ";//消息内容
        //发送消息到队列  第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
        channel.basicPublish("","test",null,message.getBytes("UTF-8"));
        System.out.println("producer send "+message);
        channel.close();
        connection.close();
    }
    @Test
    public void testConsumer() throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(url);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明关注的队列
        AMQP.Queue.DeclareOk test = channel.queueDeclare("test", false, false, false, null);
        System.out.println("========consumer wait received message================");
//        接收message方式一：
        //DefaultConsumer类实现了Consumer接口，传入一个通道，如果通道中有消息，就会执行回调函数handleDelivery  delivery：传送，投递
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"UTF-8"));
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume("test", true, defaultConsumer);
        //接收message方式二：
//        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);//QueueingConsumer extends DefaultConsumer
//        channel.basicConsume("test",true,queueingConsumer);
//        while(true){//一直执行，获取Delivery传递对象
//            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
//            System.out.println(new String(delivery.getBody(),"UTF-8"));
//        }
    }
}
