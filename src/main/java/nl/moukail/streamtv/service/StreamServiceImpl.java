package nl.moukail.streamtv.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.moukail.streamtv.annotations.FiveHours;
import nl.moukail.streamtv.dao.StreamDAO;
import nl.moukail.streamtv.entity.Stream;

@Service
@Transactional
public class StreamServiceImpl implements StreamService {

	@Autowired
	private StreamDAO streamDAO;
	
	
	public void addStream(Stream stream) {
		streamDAO.addStream(stream);
	}

	public List<Stream> listStream(int channelId) {
		return streamDAO.listStream(channelId);
	}

	public void updateStream(Stream stream) {
		streamDAO.updateStream(stream);
	}
	
	public void removeStream(Integer id) {
		streamDAO.removeStream(id);
	}

	public Stream get(Integer id) {
		return streamDAO.get(id);
	}

	@FiveHours
	public void fetchYoutubeStreams(){
		
		//Al Kahera Wal Nas    - JTq7FTfk5BM - 437
		//Al Kahera Wal Nas +2 - v1TlfQ66LQ8 - 439
		//CBC - Hf2ROEjnyxs - 440
		HashMap<Integer, String> youtubeLive = new HashMap<Integer, String>();
		
		youtubeLive.put(430, "XVKmHWf-q4o");//4Shabab 1
		youtubeLive.put(431, "7ovIlFQQ5BU");//4Shabab 2*******
		youtubeLive.put(434, "npuXEET_dMQ");//Al Nahar TV
		youtubeLive.put(437, "JTq7FTfk5BM");//Al Kahera Wal Nas
		youtubeLive.put(439, "v1TlfQ66LQ8");//Al Kahera Pal Nas +2*****
		youtubeLive.put(440, "Hf2ROEjnyxs");//CBC******
		youtubeLive.put(443, "1SD4N9HVf44");//CBC +2
		youtubeLive.put(445, "_ketpEkr0nA");//ON TV
		youtubeLive.put(447, "JEkbk-xDmZE");//Sada EL balad
		youtubeLive.put(449, "T1oJUSGjjD4");//Sada EL balad Drama
		youtubeLive.put(451, "xnW8cqW-awE");//Toyor Al Janah*******
		youtubeLive.put(454, "AUpuhW_pYyo");//El Mehwar TV
		youtubeLive.put(462, "P9kRx_KLqf0");//Al Resala TV
		youtubeLive.put(465, "JBxaWDA47FQ");//NTV Kenya
		youtubeLive.put(467, "L1ofE3wPe5Q");//Saudi TV 1
		youtubeLive.put(469, "Ko91-oaqgaU");//Al Ekhbariya
		youtubeLive.put(479, "f4qofs2W8kA");//قناة كناري الفضائية
		youtubeLive.put(483, "4be-w_hmvqg");//Play Hekayat
		youtubeLive.put(484, "_9jmpRZBGjU");//القناة الثقافية
		youtubeLive.put(485, "QFnILutVyPc");//القناة الاقتصادية
		youtubeLive.put(486, "vHEdBRrg-4E");//قناة الرياضية السعودية
		youtubeLive.put(487, "KonSX32x5YU");//قناة السنة النبوية
		youtubeLive.put(488, "S2PriQcCG38");//Al Quran TV
		youtubeLive.put(489, "eTlNaGfSkWY");//Saudi TV 2
		youtubeLive.put(493, "a1zrc0UJe8E");//AT5
		
		Iterator it1 = youtubeLive.entrySet().iterator();
		
		HttpClient httpclient = new DefaultHttpClient();
        try {
        	
        	while (it1.hasNext()) {
        		Map.Entry pairs = (Map.Entry)it1.next();
        		System.out.println(pairs.getKey() + " = " + pairs.getValue());
        		
        		HttpGet httpget = new HttpGet("http://m.youtube.com/watch?ajax=1&v="+ pairs.getValue());
            
        		httpget.setHeader("User-Agent", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16");
            
        		// Create a response handler
        		ResponseHandler<String> responseHandler = new BasicResponseHandler();
        		String responseBody = httpclient.execute(httpget, responseHandler);
            
        		JSONObject jArray = new JSONObject(responseBody.replace(")]}'", ""));
        		JSONObject content = jArray.getJSONObject("content");
        		
        		if(content.has("video")){
        			JSONObject video = content.getJSONObject("video");
            
        			if(video.has("stream_url")){
        				Stream stream = get((Integer)pairs.getKey());
        				stream.setStreamfile(video.getString("stream_url"));
        				updateStream(stream);
            
        				System.out.println(video.getString("stream_url"));
        			}
        		}
        		
        		//it.remove(); // avoids a ConcurrentModificationException
        	}
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
		
	}
	
	//@Minute
	public void testFetch(){
		//http://i.teleboy.ch/tv/player.php?station_id=1&channel_id=1&stream_profile=high&remember=long
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://i.teleboy.ch/tv/player.php?station_id=1&channel_id=1&stream_profile=high&remember=long");
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("login", "moukail"));
		nvps.add(new BasicNameValuePair("password", "meknes79"));
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			
			// Create a response handler
           /* ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpPost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");*/
			
			HttpResponse response2 = httpclient.execute(httpPost);

			System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    
		    EntityUtils.consume(entity2);
		    
		    //System.out.println(EntityUtils.toString(entity2));
		    
		    //http://i.teleboy.ch/live.php
		    
		    HttpGet httpget = new HttpGet("http://t.teleboy.ch/tv/player/player_tablet.php?station_id=1&channel_id=1&stream_profile=high&remember=short");

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");
		    
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    httpPost.releaseConnection();
		}
		
	}
}
