HOW to send Message to RabbitMQ Broker.(First setup RabbitMQ broker and create exchange and queue using web console)
--------------------------------------------------------------------------------------------------------------------
step-1
--------
create spring boot project with RabbitMq dependency

Example:
-------
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-amqp</artifactId>
		</dependency>
		
		
step-2
----------
create bean of ConnectionFactory using CachingConnectionFactory class for RabbitTemplate Bean.

Example
--------
		
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory factory =  new CachingConnectionFactory("localhost");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		return factory;
	}
	

step-3
------
Inject or Autowired	RabbitTemplate Bean to call method of Rabbitemplate

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
calling using CommandLineRunner 
---------------------------

	@Override
	public void run(String... args) throws Exception {
		System.out.println("message sending started.");
		rabbitTemplate.convertAndSend("ami.direct", "", "First Message from client code");
		System.out.println("message sending finished.");
	}	
	
	
	"ami.direct"--> Exchanged name
	"First Message from client code"--> message to exchange
	
Notes:
--------
In RabbitMQ client cant send message direct to Queue(Topic not exist)..client can send message to exchange and exchange have binding to queue <br>
(one to one and one to many or many to one relations)

	
	