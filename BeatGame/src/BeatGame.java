import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BeatGame extends JFrame {
	public static String Title[] = {"Faded", "Blue Zenith", "Flower Dance"};
	public static int cnt = 0;
	
	private Image screenImage;
	private Graphics screenGraphic;

	//����ȭ��
	private Image background = new ImageIcon(Main.class.getResource("./images/Background.jpg")).getImage();
	//�޴���
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("./images/menuBar.png")));
	
	//������ ��ư
	private ImageIcon exitButtonImage = new ImageIcon(Main.class.getResource("./images/exitButton.png"));
	private JButton exitButton = new JButton(exitButtonImage);
	//���� ��ư
	private ImageIcon startButtonImage = new ImageIcon(Main.class.getResource("./images/StartButton.png"));
	private JButton startButton = new JButton(startButtonImage);
	//�� ���� ��ư
	private ImageIcon leftButtonImage = new ImageIcon(Main.class.getResource("./images/leftButton.png"));
	private JButton leftButton = new JButton(leftButtonImage);
	private ImageIcon rightButtonImage = new ImageIcon(Main.class.getResource("./images/rightButton.png"));
	private JButton rightButton = new JButton(rightButtonImage);
	//�� ���� ��ư
	private ImageIcon MusicStartButtonImage = new ImageIcon(Main.class.getResource("./images/MusicStartButton.png"));
	private JButton MusicStartButton = new JButton(MusicStartButtonImage);
	
	//�ڷΰ��� ���� ��ư
	private ImageIcon BackButtonImage = new ImageIcon(Main.class.getResource("./images/backButton.png"));
	private JButton BackButton = new JButton(BackButtonImage);
	
	
	private int mouseX, mouseY;
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	// �� ����
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private int nowSelected = 0;
	
	private Music introMusic = new Music("main_music", "introMusic.mp3", true);
	
	public static Game game;
	
	BeatGame() {
		trackList.add(new Track(
				"title.png",
				"image.jpg",
				"image.jpg",
				"Faded_selected.mp3",
				"Faded.mp3",
				"Faded"
				));
		trackList.add(new Track(
				"title.png",
				"image.jpg",
				"image.jpg",
				"Blue Zenith_selected.mp3",
				"Blue Zenith.mp3",
				"Blue Zenith"
				));
		trackList.add(new Track(
				"title.png",
				"image.jpg",
				"image.jpg",
				"Flower Dance_selected.mp3",
				"Flower Dance.mp3",
				"Flower Dance"
				));
		
		setUndecorated(true);
		setTitle("��Ʈ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false); // ������ ����
		setLocationRelativeTo(null); // ȭ�� �߾ӿ� ��Ÿ���� ����
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		introMusic.start();
		
		
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �հ��� Ŀ���� ����
				Music buttonEnteredMusic = new Music("main_music", "buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // �⺻ Ŀ���� ����
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("main_music","buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(exitButton);
		
		startButton.setBounds(40, 200, 278, 127);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �հ��� Ŀ���� ����
				Music buttonEnteredMusic = new Music("main_music", "buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // �⺻ Ŀ���� ����
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("main_music", "buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				enterMain();
			}
		});
		add(startButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(0, 0, 300, 720);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �հ��� Ŀ���� ����
				Music buttonEnteredMusic = new Music("main_music", "buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // �⺻ Ŀ���� ����
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("main_music","buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ���� ��ư �̺�Ʈ
				selectLeft();
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(980, 0, 300, 720);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �հ��� Ŀ���� ����
				Music buttonEnteredMusic = new Music("main_music", "buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // �⺻ Ŀ���� ����
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("main_music","buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// ������ ��ư �̺�Ʈ
				selectRight();
			}
		});
		add(rightButton);
		
		MusicStartButton.setVisible(false);
		MusicStartButton.setBounds(515, 310, 250, 100);
		MusicStartButton.setBorderPainted(false);
		MusicStartButton.setContentAreaFilled(false);
		MusicStartButton.setFocusPainted(false);
		MusicStartButton.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseEntered(MouseEvent e) {
				MusicStartButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �հ��� Ŀ���� ����
				Music buttonEnteredMusic = new Music("main_music", "buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				MusicStartButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // �⺻ Ŀ���� ����
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("main_music","buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// �� ���� �̺�Ʈ
				gameStart(nowSelected);
			}
		});
		add(MusicStartButton);
		
		BackButton.setVisible(false);
		BackButton.setBounds(20, 50, 50, 50);
		BackButton.setBorderPainted(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setFocusPainted(false);
		BackButton.addMouseListener(new MouseAdapter( ) {
			@Override
			public void mouseEntered(MouseEvent e) {
				BackButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �հ��� Ŀ���� ����
				Music buttonEnteredMusic = new Music("main_music", "buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				BackButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // �⺻ Ŀ���� ����
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("main_music","buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				// �� ���ù�ư���� ���ư��� �̺�Ʈ
				backMain();
			}
		});
		add(BackButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //���콺 ��ǥ �ҷ�����
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter( ) {
			@Override
			public void mouseDragged(MouseEvent e) { // ���콺 �巡�׷� â �̵�
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen) {
			g.drawImage(selectedImage, 0, 0, null);
			g.drawImage(titleImage, 450, 600, null);
		}
		if(isGameScreen) {
			game.screenDraw(g);
		}
		paintComponents(g); // �������� �̹��� �׸� �� ��� ex �޴���
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	public void selectTrack(int nowSelected) {
		
		if(selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource(Title[cnt] + "/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource(Title[cnt] + "/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(Title[cnt], trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size() - 1; cnt = trackList.size() - 1;
		}
		else {
			nowSelected--; cnt--;
		}
		selectTrack(nowSelected);
	}
	public void selectRight() {
		if(nowSelected == trackList.size() - 1) {
			nowSelected = 0; cnt = 0;
		}
		else {
			nowSelected++; cnt++;
		}
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		MusicStartButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource(Title[cnt] + "/" + trackList.get(nowSelected).getGameImage())).getImage();
		BackButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), trackList.get(nowSelected).getGameMusic());
		game.start();
		setFocusable(true);
		requestFocus();
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		MusicStartButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource(Title[cnt] + "/" + trackList.get(nowSelected).getGameImage())).getImage();
		BackButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
	}
	
	public void enterMain() {
		background = new ImageIcon(Main.class.getResource("./images/mainBackground.jpg")).getImage();	
		startButton.setVisible(false);
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		MusicStartButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}
}