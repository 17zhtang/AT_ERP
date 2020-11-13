//package com.haoyun.automationtesting.interfaces;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class HttpUtil {
//
//	// 连接池
//	private static PoolingHttpClientConnectionManager connectionMgr;
//	// 超时时间
//	private static final int MAX_TIMEOUT = 7000;
//
//	private static RequestConfig requestConfig;
//	static {
//		// 设置连接池
//		connectionMgr = new PoolingHttpClientConnectionManager();
//		// 设置连接池大小
//		connectionMgr.setMaxTotal(100);
//		connectionMgr.setDefaultMaxPerRoute(connectionMgr.getMaxTotal());
//
//		RequestConfig.Builder configBuilder = RequestConfig.custom();
//		// 设置连接超时
//		configBuilder.setConnectTimeout(MAX_TIMEOUT);
//		// 设置读取超时
//		configBuilder.setSocketTimeout(MAX_TIMEOUT);
//		// 设置从连接池获取连接实例的超时
//		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
//
//		requestConfig = configBuilder.build();
//
//	}
//
//	/**
//	 * GET 请求 带输入参数
//	 * 
//	 * @param Url
//	 *            请求host地址
//	 * @param params
//	 *            参数
//	 * @return
//	 */
//	public static String httpGet(String Url, Map<String, Object> params) {
//		// 返回结果
//		String result = null;
//		// 拼接url
//		StringBuilder builder = new StringBuilder(Url);
//		if (Url.contains("?")) {
//			builder.append("&");
//		} else {
//			builder.append("?");
//		}
//		int i = 0;
//		for (String key : params.keySet()) {
//			if (i != 0) {
//				builder.append("&");
//			}
//			builder.append(key);
//			builder.append("=");
//			builder.append(params.get(key));
//
//			i++;
//		}
//		String apiUrl = builder.toString();
//		// 创建client
//		HttpClient client = HttpClients.createDefault();
//
//		try {
//			HttpGet get = new HttpGet(apiUrl);
//			HttpResponse response = client.execute(get);
//			// 获取请求状态码
//			int statusCode = response.getStatusLine().getStatusCode();
//			System.out.println(statusCode);
//
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				result = EntityUtils.toString(entity, "UTF-8");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * POST 请求
//	 * 
//	 * @param url
//	 *            请求url
//	 * @param params
//	 *            post提交参数
//	 * @return
//	 */
//	public static String httpPost(String url, Map<String, Object> params) {
//		HttpClient client = HttpClients.createDefault();
//		String result = null;
//		try {
//			HttpPost post = new HttpPost(url);
//			// 添加post提交参数
//			ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
//			for (Map.Entry<String, Object> entry : params.entrySet()) {
//				NameValuePair pair = new BasicNameValuePair(entry.getKey(),
//						entry.getValue().toString());
//				pairList.add(pair);
//			}
//
//			post.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
//
//			HttpResponse response = client.execute(post);
//
//			// 获取状态码
//			int statueCode = response.getStatusLine().getStatusCode();
//			System.out.println(statueCode);
//
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				result = EntityUtils.toString(entity);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	
//
//	public static void main(String[] args) {
//		// HttpUtil util = new HttpUtil();
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("ytyut", "123123123123");
//		
//		Map<String, String> params1 = new HashMap<String, String>();
//		params1.put("params1", "123123params1123123");
//		params1.put("params2", "123123params1123123");
//		params1.put("params3", "123123params1123123");
//		// params.put("q", "good");
//		// params.put("salt", "1496238482428");
//		// params.put("sign", "02E15CDAF871B698FE04EE32FD2CF155");
//		// params.put("from", "en");
//		// params.put("appKey", "7743eee7f7e11d75");
//		// params.put("to", "zh-CHS");
//		
//		
//		
//		//String result = postJson("https://getman.cn/echo","1234");
//		//String result = get("https://getman.cn/echo/1234");
//		String result = getMap("https://getman.cn/echo",params1);
//		//String result = postMap("https://getman.cn/echo",params1);
//		//String result = httpPost("https://getman.cn/echo", params);
//		// String result = util.httpGet("https://getman.cn/echo", params);
//		System.out.println(result);
//		System.out.println(isstringcontain(result,"1"));
//		// https://dict.youdao.com/dictvoice?audio=good&le=auto&channel=7743eee7f7e11d75&rate=4
//	}
//
//	// /////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * get请求，参数拼接在地址上
//	 * 
//	 * @param url
//	 *            请求地址加参数
//	 * @return 响应
//	 */
//	public static String get(String url) {
//		String result = null;
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpGet get = new HttpGet(url);
//		CloseableHttpResponse response = null;
//		try {
//			response = httpClient.execute(get);
//			if (response != null
//					&& response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//				result = entityToString(entity);
//			}
//			return result;
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				httpClient.close();
//				if (response != null) {
//					response.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * get请求，参数放在map里
//	 * 
//	 * @param url
//	 *            请求地址
//	 * @param map
//	 *            参数map
//	 * @return 响应
//	 */
//	public static String getMap(String url, Map<String, String> map) {
//		String result = null;
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//		}
//		
//		CloseableHttpResponse response = null;
//		try {
//			URIBuilder builder = new URIBuilder(url);
//			builder.setParameters(pairs);
//			HttpGet get = new HttpGet(builder.build());
//			response = httpClient.execute(get);
//			if (response != null
//					&& response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//				result = entityToString(entity);
//			}
//			return result;
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				httpClient.close();
//				if (response != null) {
//					response.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return null;
//	}
//
//	/**
//	 * 发送post请求，参数用map接收
//	 * 
//	 * @param url
//	 *            地址
//	 * @param map
//	 *            参数
//	 * @return 返回值
//	 */
//	public static String postMap(String url, Map<String, String> map) {
//		String result = null;
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPost post = new HttpPost(url);
//		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//		for (Map.Entry<String, String> entry : map.entrySet()) {
//			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//		}
//		CloseableHttpResponse response = null;
//		try {
//			post.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
//			response = httpClient.execute(post);
//			if (response != null
//					&& response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//				result = entityToString(entity);
//			}
//			return result;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				httpClient.close();
//				if (response != null) {
//					response.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//		return null;
//	}
//
//	/**
//	 * post请求，参数为json字符串
//	 * 
//	 * @param url
//	 *            请求地址
//	 * @param jsonString
//	 *            json字符串
//	 * @return 响应
//	 */
//	public static String postJson(String url, String jsonString) {
//		String result = null;
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//		HttpPost post = new HttpPost(url);
//		CloseableHttpResponse response = null;
//		
//		try {
//			post.addHeader("Content-Type", "application/json");
//			post.addHeader("", "");
//			post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
//			response = httpClient.execute(post);
//			if (response != null
//					&& response.getStatusLine().getStatusCode() == 200) {
//				HttpEntity entity = response.getEntity();
//				result = entityToString(entity);
//			}
//			return result;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				httpClient.close();
//				if (response != null) {
//					response.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	private static String entityToString(HttpEntity entity) throws IOException {
//		String result = null;
//		if (entity != null) {
//			long lenth = entity.getContentLength();
//			if (lenth != -1 && lenth < 2048) {
//				result = EntityUtils.toString(entity, "UTF-8");
//			} else {
//				InputStreamReader reader1 = new InputStreamReader(
//						entity.getContent(), "UTF-8");
//				CharArrayBuffer buffer = new CharArrayBuffer(2048);
//				char[] tmp = new char[1024];
//				int l;
//				while ((l = reader1.read(tmp)) != -1) {
//					buffer.append(tmp, 0, l);
//				}
//				result = buffer.toString();
//			}
//		}
//		return result;
//	}
//	/***
//	 * 包含字符串
//	 * @param str 主
//	 * @param isstr 子
//	 * @return bool
//	 */
//	public static boolean isstringcontain(String str,String isstr){
//		boolean status = str.contains(isstr);
//		return status;
//	}
//	
//	
//}