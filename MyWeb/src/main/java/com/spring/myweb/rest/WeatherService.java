package com.spring.myweb.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

	private final IWeatherMapper mapper;
	
	// properties에 작성된 값을 읽어오는 아노테이션
	@Value("${weather.serviceKey}")
	private String serviceKey;
	
	@Value("${weather.reqUrl}")
	private String reqUrl;

	public String getShourtTermForecast(String area1, String area2) {
		
		LocalDateTime ldt = LocalDateTime.now();
		String baseDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(ldt);
		log.info("baseDate: {}", baseDate);
		
		Map<String, String> map = mapper.getCoord(area1.trim(), area2.replace(" ", "").trim());
		log.info("좌표 결과: {}", map);
		String resStr = "";
		
		// RestTemplate을 이용하여 api 요청을 해 보자
		
		// 요청 헤더 설정 (api에서 원하는 헤더 설정이 있다면 사용하세요.)
		HttpHeaders headers = new HttpHeaders();
		//headers.set("Content-type", "application/json;");
		
		// 요청 파라미터 설정
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("serviceKey", serviceKey);
//		params.add("pageNo", "1");
//		params.add("numOfRows", "200");
//		params.add("dataType", "JSON");
//		params.add("base_date", baseDate);
//		params.add("base_time", "0200");
//		params.add("nx", String.valueOf(map.get("NX")));
//		params.add("ny", String.valueOf(map.get("NY")));
		
		UriComponents uri = UriComponentsBuilder.fromHttpUrl(reqUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("pageNo", "1")
                .queryParam("numOfRows", "10")
				.queryParam("dataType", "JSON")
				.queryParam("base_date", baseDate)
				.queryParam("base_time", "0200")
				.queryParam("nx", String.valueOf(map.get("NX")))
				.queryParam("ny", String.valueOf(map.get("NY")))
				.build(false);
		
		log.info("url: {}", uri.toString());
		
		// REST 방식의 통신을 보내줄 객체 생성
		RestTemplate template = new RestTemplate();
		
		// 위에서 세팅한 header 정보와 parameter를 하나의 엔티티로 포장
		//HttpEntity<Object> requestEntity = new HttpEntity<>(params, headers);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		
		//통신을 보내면서 응답데이터를 바로 리턴.
        //param1: 요청 url
        //param2: 요청 방식(method)
        //param3: 헤더와 요청파라미터 정보 엔티티 객체
        //param4: 응답 데이터를 받을 객체의 타입 (ex: dto, String, map...)
		ResponseEntity<Map> responseEntity = template.exchange(uri.toString(), HttpMethod.GET, requestEntity, Map.class);
		
		// 응답데이터에서 필요한 정보를 가져오자
		Map<String, Object> responseData = (Map<String, Object>) responseEntity.getBody();
		
		log.info("요청에 따른 응답 데이터: {}", responseData);
		
		
		// api에서 제공하는 예제 코드를 그대로 활용한 방식
//		StringBuilder sb = new StringBuilder();
//		try {
//			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
//	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="); /*Service Key*/
//	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
//	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
//	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*‘21년 6월 28일 발표*/
//	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0200", "UTF-8")); /*06시 발표(정시단위) */
//	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("NX")), "UTF-8")); /*예보지점의 X 좌표값*/
//	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(String.valueOf(map.get("NY")), "UTF-8")); /*예보지점의 Y 좌표값*/
//	        
//	        log.info("완성된 url: {}", urlBuilder.toString());
//	        
//	        URL url = new URL(urlBuilder.toString());
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        conn.setRequestMethod("GET");
//	        conn.setRequestProperty("Content-type", "application/json");
//	        log.info("Response code: {}", conn.getResponseCode());
//	        BufferedReader rd;
//	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        } else {
//	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//	        }
//	        
//	        String line;
//	        while ((line = rd.readLine()) != null) {
//	            sb.append(line);
//	        }
//	        rd.close();
//	        conn.disconnect();
//	        //log.info(sb.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		// SringBuilder 객체를 String으로 변환
//		String jsonString = sb.toString();
//		
//		
//		try {
//			JSONParser parser = new JSONParser();
//			JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
//			
//			// "response" 라는 이름의 키에 해당하는 JSON 데이터를 가져옵니다.
//			JSONObject response = (JSONObject) jsonObject.get("response");
//			// response 안에서 body 키에 해당하는 JSON 데이터를 가져옵니다.
//			JSONObject body = (JSONObject) response.get("body");
//			
//			// body안에서 item키에 해당하는 JSON 데이터 중 item 키를 가진 JSON 데이터를 가져옵니다.
//			// item 키에 해당하는 JSON 데이터는 여러 값이기 때문에 배열의 문법을 제공하는 객체로 받습니다.
//			JSONArray itemArray = (JSONArray) ((JSONObject) body.get("items")).get("item");
//			
//			// item 내부에 각각의 객체에 대한 반복문을 작성합니다.
//			for (Object obj : itemArray) {
//				JSONObject item = (JSONObject) obj;
//				// "category" 키에 해당하는 단일 값을 가져옵니다.
//				String category = (String) item.get("category");
//				// "fcstValue" 키에 해당하는 단일 값을 가져옵니다.
//				String fcstValue = (String) item.get("fcstValue");
//				
//				if (category.equals("TMX") || category.equals("TMN")) {
//					log.info("category: {}, fsctValue: {}", category, fcstValue);
//					resStr += "{\"category\": \"" + category + "\", \"fsctValue\": \"" + fcstValue + "\"}, ";			
//				}
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
		//resStr = "{\"items\": [" + resStr.substring(0, (resStr.length() - 1)) + "]}";
		//log.info("{}", resStr);

		return resStr;
        
	}
}
/*
List<ClientHttpRequestInterceptor> list = new ArrayList<ClientHttpRequestInterceptor>();
list.add(new ClientHttpRequestInterceptor() {
	 @Override
	    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	        return execution.execute(new HttpRequestWrapper(request) {
	            @Override
	            public URI getURI() {
	            	return super.getURI();
//	                URI u = super.getURI();
//	                String strictlyEscapedQuery = StringUtils.replace(u.getRawQuery(), "+", "%2B");
//	                return UriComponentsBuilder.fromUri(u)
//	                        .replaceQuery(strictlyEscapedQuery)
//	                        .build(true).toUri();
	            }
	        }, body);
	    }
});
template.setInterceptors(list);*/

