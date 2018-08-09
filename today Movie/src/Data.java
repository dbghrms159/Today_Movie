import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Data {
	private String keyword,link,day;
	private ImageIcon image;
	private ImageIcon image1; //けけけけけけけけけけけけけけけけけ
	private byte[] images;
	private int size;
	public Data(String keyword,String link){
		this.link = link;
		this.keyword = keyword;
	}
	
	public Data(String keyword,String link, int size){
		this.link = link;
		this.keyword = keyword;
		this.size = size;
	}
	
	public Data(String keyword,String link,byte[] image){
		if(image == null){
			this.image = new ImageIcon(image);
			Image img = this.image.getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img, 0, 0, 99, 141, null);
			this.image = new ImageIcon();
		}else
			this.image = new ImageIcon();
		this.link = link;
		this.keyword = keyword;
		this.images = image;
		
	}
	public Data(String keyword){
		this.keyword = keyword;
	}
	
	public int getSize(){
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public  String KeywordGetter(){
		return keyword;
	}
	
	public void KeywordgSetter(String keyword){
		this.keyword = keyword;
	}
	
	public  String LinkGetter(){
		return link;
	}
	
	public void LinkSetter(String link){
		this.link = link;
	}
	public void setInage(ImageIcon image){
		this.image = image;
	}
	public ImageIcon getImage(){
		return image;
	}
	
	public void setInages(byte[] images){
		this.images = images;
	}
	public byte[] getImages(){
		return images;
	}
	@Override
	public boolean equals(Object obj) {
		Data data = (Data)obj;
		return data.KeywordGetter().equals(keyword)&&
				data.LinkGetter().equals(link);
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("day : %s 薦鯉 : %s 匝暗軒 : %s",day,link,keyword);
	}
	public Data(byte[]image1)
	{
		if(image1 != null)
		{
			this.image1 = new ImageIcon(image1);
			Image img = this.image1.getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img,0,0,99,141,null);
			this.image1 = new ImageIcon(bi);
		}
		else
		{
			this.image = new ImageIcon();
		}
	}

	public ImageIcon getImage1() {
		return image1;
	}

	public void setImage1(ImageIcon image1) {
		this.image1 = image1;
	}
	
	
	
}
