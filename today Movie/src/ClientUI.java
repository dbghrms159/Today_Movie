

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClientUI extends JFrame implements ActionListener, ListSelectionListener {
	
	private JButton CGV; // CGV 예매
	private JButton Mega; // 메가박스 예매
	private JButton Lotte; // 롯데시네마 예매
	private JButton change; // 영화 정렬 변경
	private JButton ListSel;
	private JComboBox movieSel;
	private JList movieList;
	private DefaultListModel model;
	private ArrayList<Data> movieLists;
	private JScrollPane scroll;
	private String url[] ={"current.nhn?view=list&tab=normal&order=reserve", // 예매순 상영작
							"current.nhn?view=list&tab=normal&order=likeCount",// 좋아요순 상영작
							"premovie.nhn?order=reserve",// 예매순 예정작
							"premovie.nhn?order=interestRate",// 기대치순 예정작
							};
	private boolean url_Num = true,change_url = true,change_url2 = true, chang = true, list = false;
	private int a = 0, b= 0;
	
	public ClientUI() {
		String movies[] = {"개봉작", "개봉 예정작"};
		setTitle("영화");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		JScrollPane pane = new JScrollPane(movieList);
		
		CGV = new JButton("CGV");
		Mega = new JButton("메가박스");
		Lotte = new JButton("롯데시네마");
		ListSel = new JButton("영화 선택");
		change = new JButton("좋아요순");
		
		movieSel = new JComboBox();
		for( int i = 0 ; i < movies.length; i++) {
			movieSel.addItem(movies[i]);
		}
		
		movieList = new JList(new DefaultListModel());
		model = (DefaultListModel) movieList.getModel();
		movieLists = Parseing.Search(url[0]);
		
		for(int i = 0; i < movieLists.size(); i++)
			model.addElement(movieLists.get(i).KeywordGetter());
		scroll = new JScrollPane(movieList);
		
		movieSel.setLocation(50, 50);
		movieSel.setSize(310, 50);
		change.setLocation(360, 50);
		change.setSize(90, 50);
		scroll.setLocation(50, 120);
		scroll.setSize(400, 350);
		CGV.setLocation(50, 500);
		CGV.setSize(120, 50);
		Mega.setLocation(190, 500);
		Mega.setSize(120, 50);
		Lotte.setLocation(330, 500);
		Lotte.setSize(120, 50);
		ListSel.setLocation(110, 570);
		ListSel.setSize(300, 50);
		
		CGV.addActionListener(this);
		Mega.addActionListener(this);
		Lotte.addActionListener(this);
		change.addActionListener(this);
		ListSel.addActionListener(this);
		movieSel.addActionListener(this);
		movieList.addListSelectionListener(this);
		
		add(CGV);
		add(Mega);
		add(Lotte);
		add(change);
		add(movieSel);
		add(scroll);
		add(ListSel);
		
		setResizable(false);
		setLocation(800, 200);
		setSize(500, 660);
		setVisible(true);
	}
	
	public static void main(String[] args) {
			try{
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			}catch(Exception e){}
			new ClientUI();
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == CGV) {
			try {
				Desktop.getDesktop().browse(new java.net.URI("http://www.cgv.co.kr/ticket/"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == Lotte) {
			try {
				Desktop.getDesktop().browse(new java.net.URI("http://www.lottecinema.co.kr/LCHS/Contents/ticketing/ticketing.aspx"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == Mega) {
			try {
				Desktop.getDesktop().browse(new java.net.URI("http://www.megabox.co.kr/?show=booking&p=step1"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		if(e.getSource() == ListSel){
			
			if(!String.format("%s", movieList.getSelectedValue()).equals("null")){
				int size = movieLists.get(movieList.getSelectedIndex()).getSize();
				new MovieUI(a,b,url,String.format("%s", movieList.getSelectedValue()),size);
			}else
				JOptionPane.showMessageDialog(null,"영화를 선택해 주세요");
		}
		
		for(int i = 0; i < movieLists.size(); i++)
			model.remove(0);
		
		if(e.getSource() == movieSel) {
			
			if(movieSel.getSelectedItem().equals("개봉작")){
				url_Num = true;
				movieLists = Parseing.Search(url[0]);
				change.setText("좋아요순");
				b=0;
			}else{
				url_Num = false;
				movieLists = Parseing.Search(url[2]);
				change.setText("기대치순"); 
				b=2;

			}
			
				
		}
		
		
		if(e.getSource() == change){
			
			if(url_Num){
				url_Num = true;
				movieLists = Parseing.Search(url[0]);
				change.setText("좋아요순");
				b=0;
				change_url2 =true;
				if(!change_url){
					movieLists = Parseing.Search(url[0]);
					change_url = !change_url;
					change.setText("좋아요순");
					b=0;
				}else if(change_url){
					movieLists = Parseing.Search(url[1]);
					change_url = !change_url;
					
					change.setText("예매순");
					b=1;	
				}
			}else{
				url_Num = false;
				movieLists = Parseing.Search(url[2]);
				change.setText("기대치순"); 
				b=3;
				change_url =true;
				if(!change_url2){
					movieLists = Parseing.Search(url[2]);
					change_url2 = !change_url2;
					change.setText("기대치순"); 
					b=2;
				}else if(change_url2){
					movieLists = Parseing.Search(url[3]);
					change_url2 = !change_url2;
					
					change.setText("예매순");
					b=3;
				}
			}
			
		}
		
		
		for(int i = 0; i < movieLists.size(); i++)
			model.addElement(movieLists.get(i).KeywordGetter());
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		a = movieList.getSelectedIndex();
	}
	
}
