
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.*;

public class MovieUI extends JFrame implements ActionListener {

	private ImageIcon image;
	private JLabel imageLabel;
	private JTextArea starPoint;
	private JTextArea storyArea;
	private JButton play;
	private ArrayList<Data> movie_List;
	private JList jl = null;
	private DefaultListModel model = null;
	private String name = "";
	private boolean yes = true;


	public MovieUI(int a, int b, String url[], String name, int size) {
		this.name = name;
		String urls = Parseing.Search(url[b]).get(a).LinkGetter();
		
		if (b == 0 || b == 1){
			if (size == 1)
				yes = false;
		}
		else if(b == 2 || b == 3)
			if (size <= 1)
				yes = false;
		movie_List = Parseing.Search2(urls);
		setTitle("[" + name + "]");
		setLayout(null);

		image = Parseing.Search(url[b]).get(a).getImage();

		jl = new JList(new DefaultListModel());
		model = (DefaultListModel) jl.getModel();
		model.addElement(Parseing.Searchimg(url[b]).get(a).getImage1());

		starPoint = new JTextArea();
		storyArea = new JTextArea(20, 20);

		jl.setLocation(70, 70);
		jl.setSize(99, 141);

		starPoint.setLocation(220, 50);// 평점및 영화감독 배우 정보
		starPoint.setSize(340, 200);
		starPoint.setLineWrap(true);
		starPoint.setEditable(false);
		starPoint.append(movie_List.get(0).LinkGetter() + "\n\n");
		starPoint.append("감독\n" + movie_List.get(0).KeywordGetter() + "\n\n");
		if (!(movie_List.size() == 1)) {
			starPoint.append("배우 \n");
			for (int i = 1; i < movie_List.size(); i++)
				starPoint.append(movie_List.get(i).KeywordGetter() + "\n");
		} else {
			starPoint.append("");
		}

		storyArea.setLocation(50, 270);// 줄거리
		storyArea.setSize(510, 180);
		storyArea.setLineWrap(true);
		storyArea.setEditable(false);
		storyArea.setText(Parseing.Search1(urls));

		play = new JButton("예고편 실행");
		play.setLocation(200, 480);
		play.setSize(200, 50);
		play.addActionListener(this);

		add(play);
		add(jl);
		add(starPoint);
		add(storyArea);

		setResizable(false);
		setLocation(800, 200);
		setSize(600, 580);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == play) {
			String urls = "";
			if (yes) {
				urls = "https://www.youtube.com" + Parseing.MoviePlay(name + "예고편");
				try {
					URL url = new URL(urls);
					try {

						URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
						Desktop.getDesktop().browse(uri);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (MalformedURLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}else
				JOptionPane.showMessageDialog(null,"예고편이 아직 준비되지 않았습니다.");
		}
	}

}
