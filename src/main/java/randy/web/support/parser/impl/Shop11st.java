package randy.web.support.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import randy.web.domain.Event;
import randy.web.domain.Product;
import randy.web.domain.Promotion;
import randy.web.support.parser.AbstractShopParser;
import randy.web.support.parser.domain.HttpResult;
import sun.org.mozilla.javascript.internal.NativeArray;
import sun.org.mozilla.javascript.internal.NativeObject;

/**
 * 11번가
 * 
 * @author jace
 */
public class Shop11st extends AbstractShopParser {

	@Override
	public Integer getMallId() {
		return 1;
	}

	@Override
	public String getEnconding() {
		return "euc-kr";
	}

	@Override
	public long getScheduleInterval() {
		return DEFAULT_SCHEDULE_INTERVAL;
	}

	@Override
	public List<Event> parseEvent() {
		return null;
	}

	@Override
	public List<Promotion> parsePromotion() {
		return null;
	}

	@Override
	public List<Product> parseProduct() {
		return null;
	}

	@Override
	public List<Product> parseTodaySpecial() {

		List<Product> resultList = new ArrayList<Product>();

		try {
			HttpResult httResult = this.getHttp("http://www.11st.co.kr/browsing/TodaySpecial.tmall?method=todaySpecialMain");

			String mainHtml = httResult.getContent();

			Document mainDoc = Jsoup.parse(mainHtml);

			Elements prdLiArray = mainDoc.select("div.top_menu > ul > li");
			if (prdLiArray != null) {
				for (int i = 0, j = prdLiArray.size(); i < j; i++) {

					Product product = new Product();
					product.setMallId(this.getMallId());

					Element li = prdLiArray.get(i);

					Element img = li.select("img").first();
					// 이미지 주소.
					String imgUrl = img.attr("src");
					product.setPrdThumbUrl(imgUrl);
					// 상품명
					String prdName = img.attr("title");
					product.setPrdName(prdName);

					// 가격
					Element em = li.select("em").first();
					String price = em.text().trim();
					price = StringUtils.remove(price, ",");
					price = StringUtils.remove(price, "원");
					product.setPrdPrice(Integer.parseInt(price));

					// 상품번호.
					String prdNo = "";
					Element div = li.select("a > div").first();
					String id = div.attr("id");
					if (id != null) {
						// 해당 div의 아이디값은 언더바를 _ 를 기준으로 3번째가 상품번호임.
						String[] idTokens = StringUtils.split(id, "_");
						if (idTokens != null && idTokens.length == 3) {
							prdNo = idTokens[2];
						}
					}
					product.setPrdOrgId(prdNo);

					String prdUrl = "http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=" + prdNo;
					product.setPrdUrl(prdUrl);

					// 상품 상세정보를 호출하여 카테고리 정보를 얻는다.
					HttpResult prdResult = getHttp(prdUrl);
					if (prdResult != null) {
						String prdContent = prdResult.getContent();
						if (StringUtils.isNotEmpty(prdContent)) {
							Document prdDetailDoc = Jsoup.parse(prdContent);

							String categoryTag = prdDetailDoc.select("#headSel_1").text();
							if (StringUtils.isEmpty(categoryTag)) {
								// TODO: <a href></a> 안에 있는 <span> 태그 삭제처리.
								logger.debug("--> 제거이전 : {} ", prdDetailDoc.select("#catestep0 > h3 > a").text() );
								categoryTag = prdDetailDoc.select("#catestep0 > h3 > a").text().replaceAll("\\<.*?\\>", ""); 
							}
							logger.debug("--> 카테고리 태그명 : {} ", categoryTag);

							product.setCategoryTag(categoryTag);
						}
					}

					resultList.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		return resultList;
	}

	/**
	 * js 호출 참조용임. 실제 호출되지않음.
	 * TODO: 차후 삭제처리.
	 * 
	 * @return
	 */
	/*
	public List<Product> parseTodaySpecialRef() {

		List<Product> resultList = new ArrayList<Product>();

		try {
			String mainHtml = this.getHtml("http://www.11st.co.kr/browsing/TodaySpecial.tmall?method=todaySpecialMain");

			System.out.println("--> 응답값 : " + mainHtml);
			
			Jsoup.parse(mainHtml);

			ScriptEngineManager sem = new ScriptEngineManager();
			ScriptEngine se = sem.getEngineByName("JavaScript");
			se.put("_dsSeverMode", "false");
			se.eval(script);

			NativeArray datas = (NativeArray)NativeObject.getProperty((NativeObject)se.get("FashionStoryAd"), "DATA");

			for (int i = 0, j = (int)datas.getLength(); i < j; i++) {
				NativeObject tempObj = (NativeObject)datas.get(i, null);
				Object[] objs = tempObj.getAllIds();

				Product prd = new Product();
				prd.setMallId(this.getMallId());

				String prdUrl = "http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=";

				for (Object obj : objs) {
					String key = obj.toString();
					String value = NativeObject.getProperty(tempObj, key).toString();

					if (key.equals("TXT1")) {
						prd.setPrdName(value);
					} else if (key.equals("JURL1")) {
						String prdNo = NativeObject.getProperty(tempObj, "NUM1").toString();
						prdUrl += prdNo;

						// 상품 url세팅.
						prd.setPrdUrl(prdUrl);
						// 상품 원래 아이디 세팅.
						prd.setPrdOrgId(prdNo);

					} else if (key.equals("IMG1")) {
						prd.setPrdThumbUrl(value);
					} else if (key.equals("PRC1")) {
						// 통화단위를 삭제처리.
						value = StringUtils.replace(StringUtils.trim(value), ",", "");
						prd.setPrdPrice(Integer.parseInt(value));
					}
				}

				// 상세페이지를 호출하여 최상위 카테고리 분류값을 찾은다음 카테고리 tags에 match되는 카테고리 상품군에 정보 저장.
				String prdHtml = getProductInfoHtml(prdUrl, null);
				if (StringUtils.isNotEmpty(prdHtml)) {
					Document prdDetailDoc = Jsoup.parse(prdHtml);

					String categoryTag = prdDetailDoc.select("#headSel_1").text();
					logger.debug("--> 카테고리 태그명 : {} ", categoryTag);

					prd.setCategoryTag(categoryTag);
				}

				resultList.add(prd);

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		return resultList;
	}
	*/

	@Test
	public void test() {
		List<Product> pList = parseTodaySpecial();
		for (Product prd : pList) {
			System.out.println(prd.toString());
		}
	}
}
