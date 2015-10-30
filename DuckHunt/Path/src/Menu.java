import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;

public class Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage menu;

	private BufferedImage startImg;
	
	private BufferedReader Reader; 
	
	private Socket client; 
	private BufferedWriter Writer; 

	private BufferedImage highScore;
	
	String output;

	private boolean title;
	
	int x,y;
	
	Clip clip;

	public Menu() throws IOException {

		title = true;
		
		x = this.getWidth();
		y = this.getHeight();
		
		highScore = ImageIO.read(new File("./Path/src/images/HighScore.png"));

		menu = ImageIO.read(new File("./Path/src/images/menu.png"));

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("./Path/src/images/menuCursor.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(this.getX(),
				this.getY()), "img");
		this.setCursor(c);
		startImg = ImageIO.read(new File("./Path/src/images/start.png"));
		
	/*	String filepath = "./record.txt";
		
		try {
			Writer.write(filepath, 0, filepath.length());//write the read content to the data
			Writer.flush();//send data to the output stream
			output = Reader.readLine();//read content from InputStrea
			
		}catch (IOException e) {
			e.printStackTrace();
		}*/

		repaint();

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getX()>=550&&e.getY()>=500&&e.getX()<=550+startImg.getWidth()&&e.getY()<=500+startImg.getHeight())
				title = false;
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
		});
	}

	public void paint(Graphics g) {

		super.paint(g);// draw the graphics in this pane
		g.drawImage(menu, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(startImg,550, 500,null);
		g.drawImage(highScore, this.getWidth()/2-40, this.getHeight(), 40, 20, null);
	}

	public void setTitle(boolean title) {
		this.title = title;
	}

	public boolean getTitle() {
		return title;
	}
	public void playSound(String file) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(file)
							.getAbsoluteFile());
		    clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	public void stop(){
		clip.stop();
	}
}
