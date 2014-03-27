import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//oyun clasý herseyi bunda tanýmladýk
public class Oyun extends JFrame {
	// oyunun basladýðý an
	boolean gameStart = false;
	// win kazandýgý an
	boolean win = false;
	// kaybettiði an
	boolean over = false;
	// en bastaki menünün cýktýðý anda
	boolean entry = true;
	// timera baglý degiþken 
	boolean set = false;
	static double y = 0;
	static double tick = 0;
	// klavyeye baglý,jerry hareket ettikce score artýyo
	static double move = 0;
	// tomlar için actýgýmýz array
	int a[] = new int[500];
	// cizmeleri yaptýðýmýz panelin objesi,tüm oyunun oldugu panel
	NewPaint p1 = new NewPaint();

	// arka plan hareketli
	ImageIcon image3 = new ImageIcon("stars.gif");
	Image i3 = image3.getImage();
	// hareketli jerry
	ImageIcon image4 = new ImageIcon("J.gif");
	Image i4 = image4.getImage();
	// exit simgesi
	ImageIcon image5 = new ImageIcon("close1.png");
	Image i5 = image5.getImage();

	ImageIcon image6 = new ImageIcon("cheese.png");
	Image i6 = image6.getImage();
	// hareketli tom
	ImageIcon image7 = new ImageIcon("T.gif");
	Image i7 = image7.getImage();
	// kazanýnca cýkan jerry
	ImageIcon image8 = new ImageIcon("jerryPeynir.jpg");
	Image i8 = image8.getImage();
	// kaybedince cýkan
	ImageIcon image9 = new ImageIcon("gameOver.jpg");
	Image i9 = image9.getImage();
	// en bastaki tom jerry fotosu
	ImageIcon image10 = new ImageIcon("TJ.jpg");
	Image i10 = image10.getImage();
	// en bastaki start
	ImageIcon image11 = new ImageIcon("start.png");
	Image i11 = image11.getImage();
	// en bastaki exit
	ImageIcon image12 = new ImageIcon("exit.png");
	Image i12 = image12.getImage();
	ImageIcon image13 = new ImageIcon("settings.png");
	Image i13 = image13.getImage();
	ImageIcon image14 = new ImageIcon("set.png");
	Image i14 = image14.getImage();
	ImageIcon image1 = new ImageIcon("res.png");
	Image i1 = image1.getImage();
	ImageIcon image2 = new ImageIcon("penguç.png");
	Image i2 = image2.getImage();

	// constructor
	public Oyun() {
		// 500 posta tom gelirken her postada random bi boþluk
		for (int i = 0; i < 500; i++)
			a[i] = (int) (Math.random() * 10);

		addKeyListener(new Klavye());
		addMouseListener(new Mouse());
		// 10 milisaniyede tom jerrynin birbirine dogru ilerleyiþi
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (over) {

				} else if (win) {

				} else if (gameStart == false) {

				}

				else {
					tick++;
				}

				p1.repaint();
			}

		});

		timer.start();

		setLayout(new BorderLayout());
		// tüm oyunun oldugu p1 ekleme
		add(p1, BorderLayout.CENTER);

	}

	public static void main(String args[]) {

		Oyun frame = new Oyun();
		// oyunu tam ekran yapma
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		frame.setSize(xSize, ySize);

		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		// listenerlarý doðru kullanmak için
		frame.setFocusable(true);

	}

	class NewPaint extends JPanel {

		

		NewPaint() {
			

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// jerry nin x koordinatý ==peynirin x koordinatý esitse kazanýr
			if ((int) (10 + tick / 10) == getWidth() - 375) {
				win = true;
	
			}

			Font f1 = new Font("Arial", Font.BOLD, 40);
			// giristeki foto start exit yazýsý,entry, giris ekraný true,oyun
			// baslamadan
			if (entry == true && gameStart == false && set == false) {
				g.drawImage(i10, 0, 0, getWidth(), getHeight(), null);
				g.drawImage(i11, getWidth() / 2 - 200, getHeight() / 3, null);
				g.drawImage(i12, getWidth() / 2 + 100, getHeight() / 3, null);
				g.drawImage(i13, getWidth() / 2 - 50, getHeight() / 3 - 15,
						null);

			}

			else if (entry == true && gameStart == false && set == true) {
				g.drawImage(i10, 0, 0, getWidth(), getHeight(), null);
				g.drawImage(i11, getWidth() / 2 - 200, getHeight() / 3, null);
				g.drawImage(i12, getWidth() / 2 + 100, getHeight() / 3, null);
				g.drawImage(i13, getWidth() / 2 - 50, getHeight() / 3 - 15,
						null);
				g.drawImage(i14, getWidth() / 2 - 200, getHeight() / 3 + 40,
						450, 100, null);

			}

			// i9=game over yazýsý
			else if (over) {

				g.drawImage(i9, 0, 0, getWidth(), getHeight(), null);
				g.setColor(Color.black);
				// tick ilerledikçe move bastýkça artar score belirlenir
				g.setFont(f1);
				g.drawString("Your score is: " + (int) (tick + move),
						getWidth() / 3, (getHeight() / 3) * 2);
				// oyunun bitis cArpý iconu
				g.drawImage(i5, getWidth() - 80, 20, null);
				g.drawImage(i1, getWidth() - 130, 20, null);
			}

			else if (win) {

				g.setFont(f1);
				// peynirli jerry
				g.drawImage(i8, 0, 0, getWidth(), getHeight(), null);
				g.drawString("Your score is: " + (int) (tick + move),
						getWidth() / 7, (getHeight() / 5));
				g.drawImage(i5, getWidth() - 80, 20, null);
				g.drawImage(i1, getWidth() - 130, 20, null);

			}

			else if (gameStart == true && entry == false) {
				// yýldýzlý arkaplan
				g.drawImage(i3, 0, 0, getWidth(), getHeight(), null);
				for (int i = 1; i < 24; i++) {
					g.drawImage(i6, getWidth() - 350,
							getHeight() * i / 24 - 10, null);
				}
				g.drawImage(i5, getWidth() - 80, 20, null);
				g.setColor(Color.white);

				g.drawImage(i4, (int) (10 + tick / 10), (int) (10 + y), 32, 32,
						null);

				for (int i = 0; i < 500; i++) {

					if ((int) (500 - (Math.pow(tick, 1.12) - tick / 2) + 500 * i)
							- (int) (10 + tick / 10) < -20
							&& (int) (500 - (Math.pow(tick, 1.12) - tick / 2) + 500 * i)
									- (int) (10 + tick / 10) > -90
							&& !((a[i] + 1) * 75 > (int) (10 + y) && ((a[i]) * 75 < (int) (10 + y)))) {
						over = true;

					}  

					for (int j = 0; j < 10; j++) {
						if (a[i] != j) {
							g.drawImage(
									i7,
									(int) (500 - (Math.pow(tick, 1.12) - tick / 2) + 500 * i),
									75 * j + 20, null);
						}
					}
				}
			}
		}
	}

	class Mouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			if (e.getX() > getWidth() - 80 && e.getX() < getWidth() - 80 + 32
					&& e.getY() > 20 && e.getY() < 52) {

				System.exit(0);

			}
			if (e.getX() > getWidth() - 130 && e.getX() < getWidth() - 130 + 32
					&& e.getY() > 20 && e.getY() < 52) {

				tick=0;
				move=0;
				y=0;
				gameStart=true;
				entry=false;
				over=false;
				win=false;
				repaint();
				

			}
			if (e.getX() > getWidth() / 2 - 200
					&& e.getX() < getWidth() / 2 - 200 + 128
					&& e.getY() > getHeight() / 3
					&& e.getY() < getHeight() / 3 + 32) {
				gameStart = true;

				entry = false;

			}
			if (e.getX() > getWidth() / 2 + 100
					&& e.getX() < getWidth() / 2 + 100 + 128
					&& e.getY() > getHeight() / 3
					&& e.getY() < getHeight() / 3 + 32) {

				System.exit(0);
			}

			if (e.getX() > getWidth() / 2 - 50
					&& e.getX() < getWidth() / 2 - 50 + 128
					&& e.getY() > getHeight() / 3 - 15
					&& e.getY() < getHeight() / 3 - 15 + 64) {

				set = true;
				repaint();
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class Klavye implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:

				if (!((int) (y) < 5)) {

					y -= 15;
				}
				break;
			case KeyEvent.VK_DOWN:
				if (!((int) (y) > getHeight() - 32 - 20)) {

					y += 15;
				}
				break;

			}
			if (over)
				move = move;
			else if (win)
				move = move;

			else
				move = move + 10;

			repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
