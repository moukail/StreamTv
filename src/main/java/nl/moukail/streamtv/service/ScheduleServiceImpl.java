package nl.moukail.streamtv.service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nl.moukail.streamtv.annotations.Daily;
import nl.moukail.streamtv.annotations.Weekly;
import nl.moukail.streamtv.dao.ScheduleDAO;
import nl.moukail.streamtv.entity.Channel;
import nl.moukail.streamtv.entity.Schedule;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleDAO scheduleDAO;
	
	@Autowired
	@Qualifier("TvServiceImpl")
	private ChannelService channelService;
	
	public void addSchedule(Schedule schedule) {
		scheduleDAO.addSchedule(schedule);
	}

	public List<Schedule> listSchedule() {
		return scheduleDAO.listSchedule();
	}

	public void removeSchedule(Integer id) {
		scheduleDAO.removeSchedule(id);
	}

	public Schedule get(Integer id) {
		return scheduleDAO.get(id);
	}

	@Daily
	public void fetchSchedule(){
		
		try {
	    	XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://192.168.1.1/api/xmlrpc"));
			config.setBasicUserName("moukail");
			config.setBasicPassword("meknes");
			XmlRpcClient client = new XmlRpcClient();
			client.setTransportFactory(new XmlRpcCommonsTransportFactory(client));
			client.setConfig(config);

			Object[] params = new Object[]{};
		    //Integer result = (Integer) client.execute("Calculator.add", params);
			Integer result = (Integer) client.execute("epgService.dubaiOneFetchEpg", params);
			System.out.println("Response: " + result);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		//JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://example.com/UserService.json"));

		
		/*Channel channel = (Channel) channelService.get(1);
		Category category = (Category) categoryService.get(1);
		
		Epg epg = new Epg();
		epg.setCategory(category);
		epg.setChannel(channel);
		epg.setDate(new Date(2013,3,4));
		epg.setDescription("description");
		epg.setPoster("poster");
		epg.setStart(new Time(20,30,00));
		epg.setTitle("title");*/
		
		//addEpg(epg);
		/*System.out.println("-----------EPG-------------");
		try {
			Document doc = Jsoup.connect("http://www.dmi.ae/dubaione/schedule.asp?lang=en&amp;PrgDate=05/03/2013&amp;ChannelID=4").get();
			Elements elements = doc.select("body");
			System.out.println(elements.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//@Minute
	public void fetchYle(){
		try {
			/*HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("http://yle.fi/ohjelmat/tv/tv1/2013-3-7.json");
			//httpGet.setHeader("User-Agent", "");
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			
			//InputStream inputStream = entity.getContent();
			System.out.println("executing request: " + httpGet.getURI());
			System.out.println(EntityUtils.toString(entity));
			String result = EntityUtils.toString(entity);*/
			
			URL url = new URL("http://yle.fi/ohjelmat/tv/tv1/2013-3-7.json");
			URLConnection cn = url.openConnection();
			cn.connect();
			InputStream instream = cn.getInputStream();	
			DataInputStream data = new DataInputStream(instream);
			String result = data.readLine();
			System.out.println(result);
			JSONObject jArray = new JSONObject(result);
			JSONObject broadcasts = jArray.getJSONObject("broadcasts");
			for (int i = 0; i < broadcasts.length(); i++) {
				//System.out.println(broadcasts.get("g29971").toString());
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Weekly
	public void fetchJCCTV(){
		Channel channel = channelService.get(128);
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpGet = new HttpGet("http://www.jcctv.net/webservices/get-grid-days.php?langID=2");
			//httpGet.setHeader("User-Agent", "");
			HttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(entity.getContent());
				
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/JCCTV/Day");
				
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodes.getLength(); i++) {
				NodeList nodes1 = nodes.item(i).getChildNodes();
				
				HttpGet httpGet1 = new HttpGet("http://www.jcctv.net"+ nodes1.item(4).getTextContent());
				HttpResponse response1 = httpclient.execute(httpGet1);
				HttpEntity entity1 = response1.getEntity();
				
				Document doc1 = builder.parse(entity1.getContent());
				
				XPathExpression expr1 = xpath.compile("/JCCTV/statusInfo/gridDate");
				Node node2 = (Node) expr1.evaluate(doc1, XPathConstants.NODE);
				Date date = Date.valueOf(node2.getTextContent());
				System.out.println("executing request: " + date);
				
				XPathExpression expr2 = xpath.compile("/JCCTV/Playlist/Episode");
				NodeList nodes3 = (NodeList) expr2.evaluate(doc1, XPathConstants.NODESET);
				
				for (int j = 0; j < nodes3.getLength(); j++) {
					NodeList nodes4 = nodes3.item(j).getChildNodes();
					
					Schedule epg = new Schedule();
					epg.setChannel(channel);
					epg.setTitle(nodes4.item(0).getTextContent() +" - "+nodes4.item(4).getTextContent());
					epg.setDate(date);
					Time start = Time.valueOf(nodes4.item(8).getTextContent()+":00");
					epg.setStart(start);
					epg.setTimeZone("Europe/Amsterdam");
					//System.out.println("executing request: " + nodes4.item(8).getTextContent());
					epg.setDescription("");
					
					addSchedule(epg);
				}
			}
		} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XPathExpressionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// When HttpClient instance is no longer needed,
	            // shut down the connection manager to ensure
	            // immediate deallocation of all system resources
	            httpclient.getConnectionManager().shutdown();
			}
			
	}
}
