
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by hogeun on 2017-05-07.
 */

public class Parseing {
	private static ArrayList<Data> search;
	private static Document doc;
	private static Elements root;
	private static Elements sndroot, img;
	private static Data data;

	public static ArrayList<Data> Search(String urls) {
		String url = "http://movie.naver.com/movie/running/" + urls;
		// current.nhn?view=list&tab=normal&order=reserve 상영중 예매순
		// current.nhn?view=list&tab=normal&order=likeCount 상영중 좋아요순
		// premovie.nhn?order=reserve 예정작 예매순
		// premovie.nhn?order=interestRate 예정작 기대치 수준

		InputStream in = URLManager.getURLInputStream(url);
		search = new ArrayList<Data>();
		byte[] img_data;
		try {
			doc = Jsoup.parse(in, URLManager.ENCODING_UTF8, "");
			root = doc.select("div[id=content]");

			sndroot = root.select("div").select("dt").select("a");
			

			for (int i = 0; i < sndroot.size(); ++i) {
				img = root.select("dl").select("dd[class=info_t1]").get(i).select("span[class=btn_t1]").select("a");

				data = new Data(sndroot.get(i).text(), sndroot.get(i).attr("href"),img.size());
				search.add(data);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return search;
	}

	public static ArrayList<Data> Searchimg(String urls) {
		String url = "http://movie.naver.com/movie/running/" + urls;
		// current.nhn?view=list&tab=normal&order=reserve 상영중 예매순
		// current.nhn?view=list&tab=normal&order=likeCount 상영중 좋아요순
		// premovie.nhn?order=reserve 예정작 예매순
		// premovie.nhn?order=interestRate 예정작 기대치 수준

		InputStream in = URLManager.getURLInputStream(url);
		search = new ArrayList<Data>();
		byte[] img_data;
		try {
			doc = Jsoup.parse(in, URLManager.ENCODING_UTF8, "");
			root = doc.select("div[id=content]");

			sndroot = root.select("div").select("dt").select("a");
			img = root.select("div[class=thumb]").select("img");

			for (int i = 0; i < sndroot.size(); ++i) {

				img_data = URLManager.getImage(img.get(i).attr("src"), null);
				search.add(new Data(img_data));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return search;
	}

	public static String Search1(String urls) {// 줄거리 파싱
		String url = "http://movie.naver.com" + urls;// "http://movie.naver.com/movie/bi/mi/basic.nhn?code=125473";
		InputStream in = URLManager.getURLInputStream(url);
		search = new ArrayList<Data>();
		String story;
		try {
			doc = Jsoup.parse(in, URLManager.ENCODING_UTF8, "");
			root = doc.select("div[id=content]");

		} catch (IOException e) {
			e.printStackTrace();
		}
		story = root.select("div[class=story_area]").select("p").text();
		if (story.equals(""))
			return "영화 줄거리가 아직 안나왔습니다.";
		else
			return story;
	}

	public static ArrayList<Data> Search2(String urls) {// 인물정보 및 평점
		String url = "http://movie.naver.com" + urls;
		String star;// 평점
		String man;// 인물
		InputStream in = URLManager.getURLInputStream(url);
		search = new ArrayList<Data>();

		try {
			doc = Jsoup.parse(in, URLManager.ENCODING_UTF8, "");
			root = doc.select("div[id=content]");

			sndroot = root.select("div").select("ul").select("a[class=tx_people]");

			for (int i = 0; i < sndroot.size(); ++i) {
				man = sndroot.get(i).attr("title");
				star = root.select("div").select("span[class=st_on]").get(0).text();
				if (star.equals("평점 - 총 10점 중"))// 예정작 예외처리
					data = new Data(man,"아직 개봉하지 않아서 평점정보가 없습니다.");
				else
					data = new Data(man,star);
				search.add(data);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return search;
	}
	
	public static String MoviePlay(String name) {// 예고편 url 파싱
		//System.out.println(name);
		try {
			name = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//%EB%AF%B8%EC%9D%B4%EB%9D%BC
		//%EB%AF%B8%EC%9D%B4%EB%9D%BC
		String url = "https://www.youtube.com/results?search_query=" + name;
		String urls = "";// 평점
		
		InputStream in = URLManager.getURLInputStream(url);
		search = new ArrayList<Data>();

		try {
			doc = Jsoup.parse(in, URLManager.ENCODING_UTF8, "");
            root = doc.select("div[id=content]");
            sndroot = root.select("div").select("h3").select("a");
            //for(int a = 0; a < linkss.size();a++)
            urls = sndroot.get(0).attr("href")+"\n";
                //linksss += linkss.get(a+1).attr("href")+"\n";
            
		} catch (IOException e) {
			e.printStackTrace();
		}

		return urls;
	}

}
