package mini_project;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MqttPublisher implements MqttCallback{ 
	static MqttClient sampleClient;// Mqtt Client 객체 선언
	
    public static void main(String[] args) {
    	MqttPublisher obj = new MqttPublisher();
    	obj.run();
    }
    public void run() {    	
    	connectBroker(); // 브로커 서버에 접속
    	
    	while(true) {
    		try {
    			String shoe_data  = get_shoe_data();
    			String shoe2_data  = get_shoe2_data();
    			String shoe3_data  = get_shoe3_data();
    			String shoe4_data  = get_shoe4_data();
    			String shoe5_data  = get_shoe5_data();
    			String shoe6_data  = get_shoe6_data();
    			
    	       	publish_data("shoe", "{\"shoe\": "+shoe_data+"}"); //신발데이터 발행
    	       	publish_data("shoe2", "{\"shoe2\": "+shoe2_data+"}");
    	       	publish_data("shoe3", "{\"shoe3\": "+shoe3_data+"}");
    	       	publish_data("shoe4", "{\"shoe4\": "+shoe4_data+"}");
    	       	publish_data("shoe5", "{\"shoe5\": "+shoe5_data+"}");
    	       	publish_data("shoe6", "{\"shoe6\": "+shoe6_data+"}");
    	       	Thread.sleep(20000);
    		}catch (Exception e) {
				// TODO: handle exception
    			try {
    				sampleClient.disconnect();
				} catch (MqttException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			e.printStackTrace();
    	        System.out.println("Disconnected");
    	        System.exit(0);
			}
    	}
    }
    
    public void connectBroker() {
        String broker = "tcp://127.0.0.1:1883"; // 브로커 서버의 주소 
        String clientId = "practice"; // 클라이언트의 ID
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            sampleClient = new MqttClient(broker, clientId, persistence);// Mqtt Client 객체 초기화
            MqttConnectOptions connOpts = new MqttConnectOptions(); // 접속시 접속의 옵션을 정의하는 객체 생성
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts); // 브로커서버에 접속
            sampleClient.setCallback(this);// Call back option 추가
            System.out.println("Connected");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
    
    public void publish_data(String topic_input, String data) { 
        String topic = topic_input; // 토픽
        int qos = 0; // QoS level
        try {
            System.out.println("Publishing message: "+data);
            sampleClient.publish(topic, data.getBytes(), qos, false);
            System.out.println("Message published");
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }
    
    public String get_shoe_data() { 
    
    	String url = "http://www.kream.co.kr/products/28029";
 
    	Document doc = null;
		// Jsoup으로 API 데이터 가져오기
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Elements element = doc.select("#__layout > div > div.container.detail.lg > div.content > div > div:nth-child(2) > div > div.column_top > div.product_figure_wrap.lg > div.detail_price > div.price > div.amount > span.num");
		String shoe = element.text();
		shoe = shoe.replaceAll(",", "");
		return shoe;
    }
    
    public String get_shoe2_data() { 
    
    	String url = "http://www.kream.co.kr/products/50694"; 
    			
    	Document doc = null;
    	
		
		// Jsoup으로 API 데이터 가져오기
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("#__layout > div > div.container.detail.lg > div.content > div > div:nth-child(2) > div > div.column_top > div.product_figure_wrap.lg > div.detail_price > div.price > div.amount > span.num");
		String shoe = element.text();
		shoe = shoe.replaceAll(",", "");
		return shoe;
    }
    public String get_shoe3_data() { 
    	
    	String url = "http://www.kream.co.kr/products/43374"; 
    			
    	Document doc = null;
		
		// Jsoup으로 API 데이터 가져오기
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("#__layout > div > div.container.detail.lg > div.content > div > div:nth-child(2) > div > div.column_top > div.product_figure_wrap.lg > div.detail_price > div.price > div.amount > span.num");
		String shoe = element.text();
		shoe = shoe.replaceAll(",", "");
		return shoe;
    }
    public String get_shoe4_data() { 
    	
    	String url = "http://www.kream.co.kr/products/25922"; 
    			
    	Document doc = null;
    	
		
		// Jsoup으로 API 데이터 가져오기
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(doc);
		
		Elements element = doc.select("#__layout > div > div.container.detail.lg > div.content > div > div:nth-child(2) > div > div.column_top > div.product_figure_wrap.lg > div.detail_price > div.price > div.amount > span.num");
		String shoe = element.text();
		shoe = shoe.replaceAll(",", "");
		return shoe;
    }
    public String get_shoe5_data() { 
    	
    	String url = "http://www.kream.co.kr/products/27986"; 
    			
    	Document doc = null;
    	
		
		// Jsoup으로 API 데이터 가져오기
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Elements element = doc.select("#__layout > div > div.container.detail.lg > div.content > div > div:nth-child(2) > div > div.column_top > div.product_figure_wrap.lg > div.detail_price > div.price > div.amount > span.num");
		String shoe = element.text();
		shoe = shoe.replaceAll(",", "");
		return shoe;
    }
    
    public String get_shoe6_data() { 
    	
    	String url = "http://www.kream.co.kr/products/26108"; 
    			
    	Document doc = null;
    	
		
		// Jsoup으로 API 데이터 가져오기
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Elements element = doc.select("#__layout > div > div.container.detail.lg > div.content > div > div:nth-child(2) > div > div.column_top > div.product_figure_wrap.lg > div.detail_price > div.price > div.amount > span.num");
		String shoe = element.text();
		shoe = shoe.replaceAll(",", "");
		return shoe;
    }
    
    
    
    
	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		System.out.println("Connection lost");
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}