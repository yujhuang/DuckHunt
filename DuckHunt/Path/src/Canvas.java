

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The Class BouncingPanel as the JPanel to display a ball movement inside the
 * panel area
 * 
 * author: Yujun Huang
 */
public class Canvas extends JPanel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** the max balls that can be created */
	final int max = 2;

	boolean end = false;

	private int round;

	public boolean isPaused = false;

	private BufferedImage RoundBoard;

	private int totalducks;

	private boolean hunting;

	private int kill = 0;

	private int bullet;

	Clip clip;

	private BufferedImage shot0;

	private BufferedImage shot1;
	private BufferedImage shot2;
	private BufferedImage shot3;
	/** The Runnable variable for ball movement steps */
	private Duck[] Ducks;

	private Dog dog;

	private BufferedImage[] dogs;

	private BufferedImage backgroundImg;

	private Queue<BufferedImage> blackduckImg;

	private Queue<BufferedImage> blueduckImg;

	private Queue<BufferedImage> redduckImg;

	private BufferedImage flyImg;

	private Queue<BufferedImage> DuckHitBlack;
	private Queue<BufferedImage> DuckHitBlue;
	private Queue<BufferedImage> DuckHitRed;

	private Queue<BufferedImage> FallingBlack;

	private Queue<BufferedImage> FallingBlue;

	private Queue<BufferedImage> FallingRed;

	private Queue<Color> Q;

	private int dogcount;

	private boolean first;
	private int Score;

	private BufferedImage score;

	private BufferedImage GameOver;

	private BufferedWriter write;

	private BufferedImage PauseSign;

	/**
	 * Instantiates a bouncing panel constructor
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Canvas() throws IOException {

		this.setDoubleBuffered(true);
		this.setFocusable(true);

		dogs = new BufferedImage[29];
		dog = new Dog(1, 410);
		dogcount = 0;
		isPaused = false;
		kill = 0;

		round = 1;

		totalducks = 0;

		Score = 0;
		score = ImageIO.read(new File(
				"./Path/src/images/uiScore.jpg"));

		GameOver = ImageIO.read(new File(
				"./Path/src/images/GameOver.png"));
		PauseSign = ImageIO.read(new File(
				"./Path/src/images/Pause.png"));

		RoundBoard = ImageIO.read(new File(
				"./Path/src/images/presentRound.png"));

		shot0 = ImageIO.read(new File(
				"./Path/src/images/ui0shots1.png"));
		shot1 = ImageIO.read(new File(
				"./Path/src/images/ui1shots.png"));
		shot2 = ImageIO.read(new File(
				"./Path/src/images/ui2shots.png"));
		shot3 = ImageIO.read(new File(
				"./Path/src/images/ui3shots.png"));
		bullet = 0;

		first = true;
		dogs[0] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[1] = ImageIO.read(new File(
				"./Path/src/images/dogWalking3.png"));
		dogs[2] = ImageIO.read(new File(
				"./Path/src/images/dogWalking4.png"));
		dogs[3] = ImageIO.read(new File(
				"./Path/src/images/dogWalking5.png"));
		dogs[4] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[5] = ImageIO.read(new File(
				"./Path/src/images/dogWalking3.png"));
		dogs[6] = ImageIO.read(new File(
				"./Path/src/images/dogWalking4.png"));
		dogs[7] = ImageIO.read(new File(
				"./Path/src/images/dogWalking5.png"));
		dogs[8] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[9] = ImageIO.read(new File(
				"./Path/src/images/dogWalking1.jpg"));
		dogs[10] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[11] = ImageIO.read(new File(
				"./Path/src/images/dogWalking1.jpg"));
		dogs[12] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[13] = ImageIO.read(new File(
				"./Path/src/images/dogWalking3.png"));
		dogs[14] = ImageIO.read(new File(
				"./Path/src/images/dogWalking4.png"));
		dogs[15] = ImageIO.read(new File(
				"./Path/src/images/dogWalking5.png"));
		dogs[16] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[17] = ImageIO.read(new File(
				"./Path/src/images/dogWalking3.png"));
		dogs[18] = ImageIO.read(new File(
				"./Path/src/images/dogWalking4.png"));
		dogs[19] = ImageIO.read(new File(
				"./Path/src/images/dogWalking5.png"));
		dogs[20] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[21] = ImageIO.read(new File(
				"./Path/src/images/dogWalking1.jpg"));
		dogs[22] = ImageIO.read(new File(
				"./Path/src/images/dogWalking2.png"));
		dogs[23] = ImageIO.read(new File(
				"./Path/src/images/dogWalking1.jpg"));
		dogs[24] = ImageIO.read(new File(
				"./Path/src/images/dogFound.png"));
		dogs[25] = ImageIO.read(new File(
				"./Path/src/images/dogFound.png"));
		dogs[26] = ImageIO.read(new File(
				"./Path/src/images/dogFound.png"));
		dogs[27] = ImageIO.read(new File(
				"./Path/src/images/dogJump1.png"));
		dogs[28] = ImageIO.read(new File(
				"./Path/src/images/dogJump2.png"));

		DuckHitBlack = new LinkedList<BufferedImage>();
		DuckHitBlack.add(ImageIO.read(new File(
				"./Path/src/images/duckHitBlack.png")));
		DuckHitBlue = new LinkedList<BufferedImage>();
		DuckHitBlue.add(ImageIO.read(new File(
				"./Path/src/images/duckHitBlue.png")));
		DuckHitRed = new LinkedList<BufferedImage>();
		DuckHitRed.add(ImageIO.read(new File(
				"./Path/src/images/duckHitRed.png")));

		FallingBlack = new LinkedList<BufferedImage>();
		FallingBlack.add(ImageIO.read(new File(
				"./Path/src/images/duckFallingBlack.png")));
		FallingBlue = new LinkedList<BufferedImage>();
		FallingBlue.add(ImageIO.read(new File(
				"./Path/src/images/duckFallingBlue.png")));
		FallingRed = new LinkedList<BufferedImage>();
		FallingRed.add(ImageIO.read(new File(
				"./Path/src/images/duckFallingRed.png")));

		flyImg = ImageIO.read(new File(
				"./Path/src/images/presentFlyAway.png"));

		hunting = false;

		Q = new LinkedList<Color>();

		Q.add(Color.BLACK);
		Q.add(Color.BLUE);
		Q.add(Color.RED);
		Ducks = new Duck[max];// initiate the ball Thread array
		Ducks[0] = new Duck(800, 300, Q, true);
		Q.add(Ducks[0].getColor());
		Ducks[1] = new Duck(550, 450, Q, false);
		Q.add(Ducks[1].getColor());

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("./Path/src/images/sight.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(),
				this.getY()), "img");
		this.setCursor(c);

		backgroundImg = ImageIO.read(new File("./Path/src/images/background.jpg"));

		blackduckImg = new LinkedList<BufferedImage>();

		blackduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopBlack1.png")));
		blackduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopBlack2.png")));
		blackduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopBlack3.png")));

		blueduckImg = new LinkedList<BufferedImage>();

		blueduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopBlue1.png")));
		blueduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopBlue2.png")));
		blueduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopBlue2.png")));

		redduckImg = new LinkedList<BufferedImage>();

		redduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopRed1.png")));
		redduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopRed2.png")));
		redduckImg.add(ImageIO.read(new File("./Path/src/images/duckFlyTopRed3.png")));

		setBackground(Color.PINK);// set the panel background to black

		addMouseListener(new MouseListener() {
			// add MouseListener to the Panel
			@Override
			public void mouseClicked(MouseEvent event) {
				// if the Thread has not started
			}

			@Override
			public void mouseEntered(MouseEvent event) {
			}

			@Override
			public void mouseExited(MouseEvent event) {
			}

			@Override
			public void mousePressed(MouseEvent event) {
				
				if (hunting && event.getButton() == MouseEvent.BUTTON2) {
					if (isPaused) {
						isPaused = false;
						notifyAll();
					} else {
						isPaused = true;
						try {
							Ducks[0].wait();
							Ducks[1].wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

				if (hunting && !isPaused&&event.getButton() == MouseEvent.BUTTON1) {
					if (bullet <= 3 && !Ducks[0].Away()) {
						bullet++;
						if (event.getX() >= Ducks[0].getX() - 5
								&& event.getX() <= Ducks[0].getX() + 5
										+ redduckImg.peek().getWidth()
								&& event.getY() >= Ducks[0].getY() - 5
								&& event.getY() <= Ducks[0].getY() + 5
										+ redduckImg.peek().getHeight()) {

							if (event.getX() >= Ducks[0].getX() - 5
									&& event.getX() <= Ducks[0].getX() + 5
											+ redduckImg.peek().getWidth()
									&& event.getY() >= Ducks[0].getY() - 5
									&& event.getY() <= Ducks[0].getY() + 5
											+ redduckImg.peek().getHeight() / 2) {
								Score += 500;
							} else {
								Score += 200;
							}
							Ducks[0].kill();
							kill++;
						}
					}
					if (bullet <= 3 && !Ducks[1].Away()) {
						if (event.getX() >= Ducks[1].getX() - 5
								&& event.getX() <= Ducks[1].getX() + 5
										+ redduckImg.peek().getWidth()
								&& event.getY() >= Ducks[1].getY() - 5
								&& event.getY() <= Ducks[1].getY() + 5
										+ redduckImg.peek().getHeight()) {

							if (event.getX() >= Ducks[1].getX() - 5
									&& event.getX() <= Ducks[1].getX() + 5
											+ redduckImg.peek().getWidth()
									&& event.getY() >= Ducks[1].getY() - 5
									&& event.getY() <= Ducks[1].getY() + 5
											+ redduckImg.peek().getHeight() / 2) {
								Score += 500;
							} else {
								Score += 200;
							}
							Ducks[1].kill();
							kill++;
						}
					}
					if (bullet <= 3) {
						try {
							playSound("./Path/src/sound/tir.wav");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent event) {
			}
		});

	}

	/**
	 * paint the graph of ball bouncing panel
	 * 
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {

		super.paint(g);// draw the graphics in this pane

		if (end || round > 6) {

			g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(),
					null);
			if (bullet == 0) {
				g.drawImage(shot3, 0, this.getHeight() - 100, 80, 40, null);
			} else if (bullet == 1) {
				g.drawImage(shot2, 0, this.getHeight() - 100, 80, 40, null);
			} else if (bullet == 2) {
				g.drawImage(shot1, 0, this.getHeight() - 100, 80, 40, null);
			} else if (bullet >= 3) {
				g.drawImage(shot0, 0, this.getHeight() - 100, 80, 40, null);
			}
			g.drawImage(score, this.getWidth() - 200, this.getHeight() - 100,
					200, 100, null);
			g.drawImage(RoundBoard, 0, this.getHeight() - 50, 130, 50, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString(Score + "", this.getWidth() - 150,
					this.getHeight() - 60);
			if(round == 7) {
				g.drawString(round-1 + "", 70, this.getHeight() - 15);
			}else {
				g.drawString(round + "", 70, this.getHeight() - 15);
			}			
			g.drawImage(GameOver, 540, 200, 2 * GameOver.getWidth(),
					2 * GameOver.getHeight(), null);
		} else {
			if (totalducks >= 14) {
				if (kill < 7) {
					end = true;
					File result = new File("./record.txt");
					try {
						write = new BufferedWriter(new FileWriter(result));
						write.flush();
						write.write("Score: " + Score + "\n");
						write.flush();
						write.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				round++;
				
				totalducks = 0;
			}
			if (hunting) {

				if (!Ducks[0].isAlive() && !Ducks[1].isAlive()) {

					Ducks[0].reborn();
					Ducks[1].reborn();
					if (first) {
						Ducks[0].start();
						Ducks[1].start();
						first = false;
					}
					for (int i = 0; i < 2; i++) {
						// if the ball start bouncing
						if (Ducks[i].alive()) {
							if (Ducks[i].getColor() == Color.BLACK) {
								BufferedImage image = blackduckImg.remove();
								g.drawImage(image, Ducks[i].getX(),
										Ducks[i].getY(), 80, 80, null);
								blackduckImg.add(image);
							} else if (Ducks[i].getColor() == Color.BLUE) {
								BufferedImage image = blueduckImg.remove();
								g.drawImage(image, Ducks[i].getX(),
										Ducks[i].getY(), 80, 80, null);
								blueduckImg.add(image);
							} else {
								BufferedImage image = redduckImg.remove();
								g.drawImage(image, Ducks[i].getX(),
										Ducks[i].getY(), 80, 80, null);
								redduckImg.add(image);
							}
							if (Ducks[i].getTime() > 70) {
								Ducks[i].FlyAway();
							}
						}

						if (Ducks[i].Away()) {
							if (Ducks[i].getY() >= -80
									&& Ducks[i].getX() >= -80) {
								setBackground(Color.BLACK);
								g.drawImage(flyImg, 550, 200,
										2 * flyImg.getWidth(),
										2 * flyImg.getHeight(), null);
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = blackduckImg.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									blackduckImg.add(image);
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = blueduckImg.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									blueduckImg.add(image);
								} else {
									BufferedImage image = redduckImg.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									redduckImg.add(image);
								}
							} else {
								Ducks[i].disapear();
								setBackground(Color.PINK);
							}
						}

						if (Ducks[i].dying()) {
							if (Ducks[i].getFall() <= 3) {
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = DuckHitBlack.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									DuckHitBlack.add(image);
									Ducks[i].fall();
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = DuckHitBlue.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									DuckHitBlue.add(image);
									Ducks[i].fall();
								} else {
									BufferedImage image = DuckHitRed.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									DuckHitRed.add(image);
									Ducks[i].fall();
								}
							} else {
								Ducks[i].falling();

								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = FallingBlack.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlack.add(image);
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = FallingBlue.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlue.add(image);
								} else {
									BufferedImage image = FallingRed.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingRed.add(image);
								}
							}
						}
						if (Ducks[i].FallState()) {
							if (Ducks[i].getY() <= this.getHeight()) {
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = FallingBlack.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlack.add(image);
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = FallingBlue.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlue.add(image);
								} else {
									BufferedImage image = FallingRed.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingRed.add(image);
								}
							} else {
								Ducks[i].disapear();
							}
						}

					}

				} else {
					if (Ducks[0].alive() || Ducks[1].alive() || Ducks[0].Away()
							|| Ducks[1].Away()) {
						for (int i = 0; i < 2; i++) {
							// if the ball start bouncing
							if (Ducks[i].alive()) {
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = blackduckImg.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									blackduckImg.add(image);
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = blueduckImg.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									blueduckImg.add(image);
								} else {
									BufferedImage image = redduckImg.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									redduckImg.add(image);
								}
								if (Ducks[i].getTime() > 70) {
									Ducks[i].FlyAway();
								}
							} else if (Ducks[i].Away()) {

								if (Ducks[i].getY() >= -80
										&& Ducks[i].getX() >= -80) {
									setBackground(Color.BLACK);
									g.drawImage(flyImg, 550, 200,
											2 * flyImg.getWidth(),
											2 * flyImg.getHeight(), null);
									if (Ducks[i].getColor() == Color.BLACK) {
										BufferedImage image = blackduckImg
												.remove();
										g.drawImage(image, Ducks[i].getX(),
												Ducks[i].getY(), 80, 80, null);
										blackduckImg.add(image);
									} else if (Ducks[i].getColor() == Color.BLUE) {
										BufferedImage image = blueduckImg
												.remove();
										g.drawImage(image, Ducks[i].getX(),
												Ducks[i].getY(), 80, 80, null);
										blueduckImg.add(image);
									} else {
										BufferedImage image = redduckImg
												.remove();
										g.drawImage(image, Ducks[i].getX(),
												Ducks[i].getY(), 80, 80, null);
										redduckImg.add(image);
									}
								} else {
									Ducks[i].disapear();
									setBackground(Color.PINK);
								}
							}
						}

					}

					for (int i = 0; i < 2; i++) {
						if (Ducks[i].dying()) {
							if (Ducks[i].getFall() <= 3) {
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = DuckHitBlack.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									DuckHitBlack.add(image);
									Ducks[i].fall();
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = DuckHitBlue.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									DuckHitBlue.add(image);
									Ducks[i].fall();
								} else {
									BufferedImage image = DuckHitRed.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									DuckHitRed.add(image);
									Ducks[i].fall();
								}
							} else {
								Ducks[i].falling();
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = FallingBlack.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlack.add(image);
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = FallingBlue.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlue.add(image);
								} else {
									BufferedImage image = FallingRed.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingRed.add(image);
								}
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						if (Ducks[i].FallState()) {
							if (Ducks[i].getY() <= this.getHeight()) {
								if (Ducks[i].getColor() == Color.BLACK) {
									BufferedImage image = FallingBlack.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlack.add(image);
								} else if (Ducks[i].getColor() == Color.BLUE) {
									BufferedImage image = FallingBlue.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingBlue.add(image);
								} else {
									BufferedImage image = FallingRed.remove();
									g.drawImage(image, Ducks[i].getX(),
											Ducks[i].getY(), 80, 80, null);
									FallingRed.add(image);
								}
							} else {
								Ducks[i].disapear();
							}
						}
					}
				}
			}
			g.drawImage(backgroundImg, 0, 0, this.getWidth(), this.getHeight(),
					null);
			
			g.drawImage(score, this.getWidth() - 200, this.getHeight() - 100,
					200, 100, null);
			g.drawImage(RoundBoard, 0, this.getHeight() - 50, 130, 50, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString(Score + "", this.getWidth() - 150,
					this.getHeight() - 60);
			g.drawString(round + "", 70, this.getHeight() - 15);

			if (!Ducks[1].dying && !Ducks[0].dying && !Ducks[1].FallState()
					&& !Ducks[0].FallState() && !first && !Ducks[0].alive()
					&& !Ducks[1].alive() && !Ducks[0].Away()
					&& !Ducks[1].Away()) {
				totalducks += 2;
				bullet = 0;
				Ducks[0] = new Duck(800, 300, Q, true);
				Q.add(Ducks[0].getColor());
				Ducks[1] = new Duck(550, 450, Q, false);
				Q.add(Ducks[1].getColor());
				Ducks[0].start();
				Ducks[1].start();
			}

			if (!hunting && dogcount <= 28) {
				if (dogcount == 0) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
					dog.start();
				} else if (dogcount < 8) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
				} else if (dogcount == 8 || dogcount == 20) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
					dog.setPause(true);
				} else if (dogcount == 12) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
					dog.setPause(false);
				} else if (dogcount < 24) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
				} else if (dogcount == 24) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
					try {
						playSound("./Path/src/sound/Bark.wav");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (dogcount < 27) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY(),
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
				} else if (dogcount == 27) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY() - 60,
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
					dog.setPause(true);
				} else if (dogcount == 28) {
					g.drawImage(dogs[dogcount], dog.getX(), dog.getY() - 120,
							3 * dogs[0].getWidth(), 3 * dogs[0].getHeight(),
							null);
					hunting = true;
					dogs = null;
				}
				dogcount++;
			}
			if (bullet == 0) {
				g.drawImage(shot3, 0, this.getHeight() - 100, 80, 50, null);
			} else if (bullet == 1) {
				g.drawImage(shot2, 0, this.getHeight() - 100, 80, 40, null);
			} else if (bullet == 2) {
				g.drawImage(shot1, 0, this.getHeight() - 100, 80, 40, null);
			} else if (bullet >= 3) {
				g.drawImage(shot0, 0, this.getHeight() - 100, 80, 40, null);
			}
		}

		if (isPaused) {
			g.drawImage(PauseSign, 540, 200, 2 * PauseSign.getWidth(),
					2 * PauseSign.getHeight(), null);
		}

	}

	/**
	 * The class Ball implements Runnable and store moving data
	 */
	private class Duck extends Thread  {

		/** The amount of x change and y change for next step. */
		private int Dx, Dy;

		/** The Color of the ball. */
		private Color color;
		/**
		 * The boolean variable to show if the next step x, y will increase or
		 * decrease.
		 */
		int fallcount;
		boolean alive;
		boolean dying;
		boolean falling;
		boolean flyaway;
		int time;
		private boolean Xu, Yu;

		/** The ball position coordinate */
		private int x, y;

		public Duck(int xc, int yc, Queue<Color> Q, boolean up){

			x = xc;// take in the x coordinate
			y = yc;// take in the y coordinate
			Xu = up;// first step x decrease
			Yu = false;// first step y decrease
			Dx = 35;// first step x change 1
			Dy = 35;// first step y change 1
			time = 0;
			alive = true;
			dying = false;
			flyaway = false;
			fallcount = 0;
			color = Q.remove();
		}

		/**
		 * run the Bouncing ball
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() {

			while (alive) {
				try {

					Thread.sleep(130);// cause the current Thread to sleep

				} catch (InterruptedException e) {

					e.printStackTrace();// print the stackTrace
				}
				if (!isPaused) {
					if (time % 9 == 0&&!end&&round<7)
						try {
							playSound("./Path/src/sound/Quack.wav");
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					if (Xu) {
						// if x is going to increase
						x += Dx;// increase x by specific amount for x direction
					} else {
						x -= Dx;// decrease x by specific amount for x direction
					}

					if (Yu) {
						// if y is going to increase
						y += Dy;// increase y by specific amount for y direction
					} else {
						y -= Dy;// decrease y by specific amount for y direction
					}

					if (x <= 0) {
						// if current x coordinate is less or equal to 0
						Xu = true;// set x changing direction to increase
						Dx = (int) (12*round + Math.random() * 7);// random generate
															// the
															// amount change for
															// x
					} else if (x >= 1250) {
						// otherwise if x is greater than or equal to 340
						Xu = false;// set x changing direction to decrease
						Dx = (int) (2*round + Math.random() * 25);// random generate

					}

					if (y <= 0) {
						// if current y coordinate is less or equal to 0
						Yu = true;// set y changing direction to increase
						Dy = (int) (3*round + Math.random() * 20);// random generate
																// the
																// amount change
																// for
																// y
					} else if (y >= 360) {
						Yu = false;// set y changing direction to decrease
						Dy = (int) (8*round + Math.random() * 18);// random generate
																// the
																// amount change
																// for
																// y
					}
					time++;
				}
				repaint();// repaint the graph to show ball movement status

			}
			while (flyaway) {

				try {

					Thread.sleep(130);// cause the current Thread to sleep

				} catch (InterruptedException e) {

					e.printStackTrace();// print the stackTrace
				}
				if (!isPaused) {
					x -= Dx;// decrease x by specific amount for x direction

					y -= Dy;// decrease y by specific amount for y direction
				}
				repaint();

			}
			while (dying) {
				try {

					Thread.sleep(1200);// cause the current Thread to sleep

				} catch (InterruptedException e) {

					e.printStackTrace();// print the stackTrace
				}
				repaint();
			}
			while (falling) {
				try {

					Thread.sleep(80);// cause the current Thread to sleep

				} catch (InterruptedException e) {

					e.printStackTrace();// print the stackTrace
				}
				if (!isPaused) {
					y += 70;
					if (y >= 380) {
						String file = "./Path/src/sound/05-hit-2.wav";
						try {
							playSound(file);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				repaint();
			}
		}

		public boolean Away() {
			return flyaway;
		}

		public int getTime() {
			return time;
		}

		public void FlyAway() {
			alive = false;
			flyaway = true;
			time = 0;
		}

		public boolean FallState() {
			return falling;
		}

		public void disapear() {
			falling = false;
			flyaway = false;
		}

		public void falling() {
			falling = true;
			dying = false;
			y += 50;
			fallcount = 0;
		}

		public void fall() {
			fallcount++;
		}

		public int getFall() {
			return fallcount;
		}

		public void kill() {
			// TODO Auto-generated method stub
			alive = false;
			dying = true;
			time = 0;
		}

		public void reborn() {
			// TODO Auto-generated method stub
			alive = true;
			dying = false;
			time = 0;
		}

		public Color getColor() {
			// get the color
			return color;
		}

		public int getY() {
			// get y coordinate
			return y;
		}

		public int getX() {
			// get x coordinate
			return x;
		}

		public boolean alive() {
			return alive;
		}

		public boolean dying() {
			return dying;
		}
	}

	private class Dog extends Thread {

		/** The ball position coordinate */
		private int x, y;

		boolean pause;

		public Dog(int xc, int yc) {

			x = xc;// take in the x coordinate
			y = yc;// take in the y coordinate
			pause = false;
		}

		public void setPause(boolean pause) {
			this.pause = pause;
		}

		

		public void run() {

			while (true) {
				// keep moving and bouncing
				try {

					Thread.sleep(200);// cause the current Thread to sleep

				} catch (InterruptedException e) {

					e.printStackTrace();// print the stackTrace
				}

				if (!pause) {
					this.x += 20;
				}

				repaint();// repaint the graph to show ball movement status
			}

		}

		public int getY() {
			// get y coordinate
			return y;
		}

		public int getX() {
			// get x coordinate
			return x;
		}

	}

	/**
	 * Create Gui for the Duck Hunt
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * 
	 */
	public static void CreateGui() throws IOException, FileNotFoundException{
		JFrame frame = new JFrame("Duck Hunt");// create the JFrame

		Menu menu = new Menu();
		menu.setTitle(true);
		String file1 = "./Path/src/sound/main.wav";
		menu.playSound(file1);
		frame.add(menu, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit only when
		// user click on
		frame.setVisible(true);// let the frame be visible
		frame.setSize(1280, 658);
		while (menu.getTitle()) {
			frame.repaint();
		}
		menu.stop();

		String file = "./Path/src/sound/19-start.wav";
		menu.playSound(file);
		frame.remove(menu);
		Canvas game = new Canvas();
		game.setFocusable(true);
		frame.add(game, BorderLayout.CENTER);// add bounce panel to the
		// frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit only when
		// user click on
		frame.setVisible(true);// let the frame be visible
		frame.setSize(1280, 658);
	}

	public void playSound(String file)throws FileNotFoundException {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(file).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
}
