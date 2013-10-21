package nl.moukail.streamtv.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nl.moukail.streamtv.annotations.FiveHours;
import nl.moukail.streamtv.annotations.Minute;
import nl.moukail.streamtv.dao.VideoDAO;
import nl.moukail.streamtv.entity.Video;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VideoServiceImpl implements VideoService{

	@Autowired
	private VideoDAO videoDAO;
	
	public void addVideo(Video video) {
		videoDAO.addVideo(video);
	}

	/*public List<Video> listVideo(Integer Id) {
		return videoDAO.listVideo(Id);
	}*/

	public List<Video> listVideo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateVideo(Video video) {
		videoDAO.updateVideo(video);
	}
	public void removeVideo(Integer id) {
		videoDAO.removeVideo(id);
	}

	public Video get(Integer id) {
		return videoDAO.get(id);
	}

	@Minute
	public void fetchYoutubeVideos(){
HashMap<Integer, String> youtubeVideo = new HashMap<Integer, String>();
		
		youtubeVideo.put(44, "KqMmukUOUH0");//سري للغاية - الجمرة الخبيثة
		youtubeVideo.put(45, "Ixxb2CEf4p4");//سري للغاية - الزئبق الأحمر- الجزء الأول
		youtubeVideo.put(46, "YaKUKqaUL34");//سري للغاية - الزئبق الأحمر- الجزء الثاني 
		youtubeVideo.put(47, "n7BM-QjdPis");//سري للغاية - وكر الجواسيس
		youtubeVideo.put(48, "JE_hZUUjv7E");//سري للغاية - حركة الماسونية - الجزء الأول 
		youtubeVideo.put(49, "_4aZAG_6ENQ");//سري للغاية - حركة الماسونية - الجزء الثاني 
		youtubeVideo.put(50, "tc-Nsn9Cd6c");//سري للغاية - عاصفة اليورانيوم - الجزء الأول 
		youtubeVideo.put(51, "vCm2ESXGiAo");//سري للغاية - عاصفة اليورانيوم - الجزء الثاني
		youtubeVideo.put(52, "3ol1PjN0RXI");//سري للغاية - مقتل يحيى المشد
		youtubeVideo.put(53, "oGzvCsJfNXs");//سري للغاية - موت الرجل الثاني - الجزء الأول 
		youtubeVideo.put(54, "qR8FxOIpHWo");//سري للغاية - موت الرجل الثاني - الجزء الثاني
		youtubeVideo.put(55, "bM5yGfx1KUk");//سري للغاية - الطريق إلى 11 سبتمبر - الجزء الأول
		youtubeVideo.put(56, "mC3P-Ai-HtU");//سري للغاية - الطريق إلى 11 سبتمبر - الجزء الثاني
		youtubeVideo.put(57, "SaV5LLLwOPQ");//سري للغاية - حصار المهد
		youtubeVideo.put(58, "LepWoCElsUY");//سري للغاية - البحث عن الصوفية
		youtubeVideo.put(59, "x_ksH4uR8V4");//سري للغاية - رحلة البطوطي- الجزء الأول
		youtubeVideo.put(60, "huRQER2cqy8");//سري للغاية - رحلة البطوطي - الجزء الثاني
		youtubeVideo.put(61, "5zes3WeS5fM");//سري للغاية - الحديث مع العدو
		youtubeVideo.put(62, "x_a-yh9t8dI");//سري للغاية - أرواح وأشباح
		youtubeVideo.put(63, "Tl6rdWcc0Hk");//سري للغاية - اليوم السابع - الجزء الأول 
		youtubeVideo.put(64, "mYe5eR4KXps");//سري للغاية - اليوم السابع - الجزء الثاني 
		youtubeVideo.put(65, "I_5FicXUNhs");//سري للغاية - تاريخ للبيع
		youtubeVideo.put(66, "l6mzJP0z6Ko");//سري للغاية - مثلث الغضب
		youtubeVideo.put(67, "NmN63NRVRdM");//سري للغاية - العبور إلى المجهول - الجزء الأول
		youtubeVideo.put(68, "_ARSSFP3ywc");//سري للغاية - العبور إلى المجهول - الجزء الثاني
		youtubeVideo.put(69, "uwS6_qk_Qxo");//سري للغاية - عباس واستيف و غلعاد
		youtubeVideo.put(70, "lQ416q50D6U");//سري للغاية - مقتل أبو البراء
		youtubeVideo.put(71, "TjezYw5h98Y");//سري للغاية - العد التنازلي
		youtubeVideo.put(72, "Jkuk2RAR9VI");//سري للغاية - الطريق إلى عتليت - الجزء الأول
		youtubeVideo.put(73, "YQcH4X7OzV0");//سري للغاية - الطريق إلى عتليت - الجزء الثاني 
		youtubeVideo.put(74, "lXCKqfJCLzc");//سري للغاية - الولايات غير المتحدة
		youtubeVideo.put(75, "cw3sJ7wwOKg");//سري للغاية - أجراس الخطر - الجزء الأول 
		youtubeVideo.put(76, "X89WBlrTXag");//سري للغاية - أجراس الخطر - الجزء الثاني 
		youtubeVideo.put(77, "zTMobJXpnSU");//سري للغاية - أجراس الخطر - الجزء الثالث 
		youtubeVideo.put(78, "UdkbNglvItA");//سري للغاية - أجراس الخطر - الجزء الرابع 
		
		youtubeVideo.put(79, "Mm8W0g07NMY");//مصانع عملاقة: مرسيدس
		
		youtubeVideo.put(14, "L_NBJ4851tw");//عمارة يعقوبيان
		
		
		Iterator it2 = youtubeVideo.entrySet().iterator();
		
		HttpClient httpclient = new DefaultHttpClient();
        try {
        	while (it2.hasNext()) {
        		Map.Entry pairs = (Map.Entry)it2.next();
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
            
        			if(video.has("fmt_stream_map")){
        				JSONArray streams = video.getJSONArray("fmt_stream_map");
        				String url = streams.getJSONObject(0).getString("url");
        				Video videoEnt = get((Integer)pairs.getKey());
        				videoEnt.setFilepath(url);
        				updateVideo(videoEnt);
        				System.out.println(url);
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
	
}
