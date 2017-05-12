import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import org.omg.CORBA.Environment;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JCheckBox;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String[] players;
	String[] towns;
	ArrayList<Town> townsList = new ArrayList<Town>();
	ArrayList<Strike> strikeList = new ArrayList<Strike>();
	ArrayList<Front> frontList = new ArrayList<Front>();
	ArrayList<Thunder> thunderList = new ArrayList<Thunder>();
	int selectedIndex = -1;
	int AverageLi = 0;
	int AverageCape = 0;
	int AverageTemp = 0;
	int AverageHydro = 0;
	int li;
	int cape;
	int hydro;
	int temp;
	int thunderChance;
	boolean isNaturaly;
	int time;
	int boundMapX = 352;
	int boundMapY = 305;
	int mapImgNumber = 0;
	int timeLimit;
	int frontCounter = 0;
	int oldFrontCounter = 0;
	int thunderCounter = 0;
	int oldThunderCounter = 0;
	JProgressBar progressBar = new JProgressBar();

	/**
	 * Create the panel.
	 */
	public Panel() {
		setLayout(null);

		/*
		 * Canvas canvas = new Canvas(); canvas.setBackground(new Color(0, 100,
		 * 0)); canvas.setBounds(0, 0, 351, 304); add(canvas);
		 */

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(316, 343, 46, 14);
		add(lblNewLabel_5);

		JSlider slider_thunderChance = new JSlider();

		slider_thunderChance.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (isNaturaly) {
					slider_thunderChance.setValue(calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape));
				}
				lblNewLabel_5.setText(Integer.toString(slider_thunderChance.getValue()));
			}
		});
		slider_thunderChance.setValue(0);
		slider_thunderChance.setBounds(10, 339, 296, 23);
		add(slider_thunderChance);

		JCheckBox chckbxNewCheckBox = new JCheckBox(
				"\u0415\u0441\u0442\u0435\u0441\u0442\u0432\u0435\u043D\u043D\u043E\u0441\u0442\u044C");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isNaturaly) {
					slider_thunderChance.setValue(calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape));
					slider_thunderChance.setEnabled(false);
				} else {
					slider_thunderChance.setEnabled(true);
				}
				isNaturaly = chckbxNewCheckBox.isSelected();
				// slider_thunderChance.setValue(calcThunderChance());
			}
		});
		chckbxNewCheckBox.setBounds(224, 313, 130, 23);
		add(chckbxNewCheckBox);

		textField = new JTextField();
		textField.setBounds(454, 8, 86, 20);
		add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("\u0418\u0433\u0440\u043E\u043A\u0438:");
		label.setBounds(402, 11, 46, 14);
		add(label);

		JLabel label_1 = new JLabel("\u0413\u043E\u0440\u043E\u0434\u0430:");
		label_1.setBounds(402, 42, 46, 14);
		add(label_1);

		textField_1 = new JTextField();
		textField_1.setBounds(454, 39, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("\u041F\u043E\u0441\u0442\u0430\u0432\u0438\u0442\u044C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				players = textField.getText().split(", ");
				towns = textField_1.getText().split(", ");
				for (int i = 0; i < players.length; i++) {
					addToArrayTown(players[i], towns[i], (int) (Math.random() * (boundMapX-100)),
							(int) (Math.random() * (boundMapY-30)), false);
				}
			}
		});
		btnNewButton.setBounds(402, 67, 96, 23);
		add(btnNewButton);

		JLabel lblLiftedIndex = new JLabel("\u0421\u0440\u0435\u0434\u043D\u0438\u0439 lifted index:");
		lblLiftedIndex.setBounds(402, 152, 174, 14);
		add(lblLiftedIndex);

		JLabel lblCape = new JLabel("\u0421\u0440\u0435\u0434\u043D\u0438\u0439 CAPE:");
		lblCape.setBounds(402, 97, 174, 14);
		add(lblCape);

		JLabel label_2 = new JLabel("\u041F\u0435\u0440\u0438\u043E\u0434:");
		label_2.setBounds(402, 328, 62, 14);
		add(label_2);

		textField_2 = new JTextField();
		textField_2.setBounds(463, 325, 31, 20);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lbltq = new JLabel("\u0447\u0430\u0441\u043E\u0432");
		lbltq.setBounds(505, 328, 46, 14);
		add(lbltq);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(577, 173, 36, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(577, 122, 40, 14);
		add(lblNewLabel_2);

		JSlider slider_cape = new JSlider();
		slider_cape.setValue(0);
		slider_cape.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblNewLabel_2.setText(Integer.toString(slider_cape.getValue()));
				AverageCape = slider_cape.getValue();
				slider_thunderChance.setValue(calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape));
			}
		});
		slider_cape.setMaximum(7000);
		slider_cape.setBounds(402, 118, 174, 23);
		add(slider_cape);

		JSlider slider_li = new JSlider();

		slider_li.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblNewLabel_1.setText(Integer.toString(slider_li.getValue()));
				AverageLi = slider_li.getValue();
				slider_thunderChance.setValue(calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape));
			}
		});
		slider_li.setMaximum(20);
		slider_li.setMinimum(-8);
		slider_li.setValue(-8);
		slider_li.setBounds(402, 169, 174, 23);
		add(slider_li);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 305, 353, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(352, 0, 2, 307);
		add(separator_1);

		JLabel label_6 = new JLabel(
				"\u0421\u0440\u0435\u0434\u043D\u044F\u044F \u0442\u0435\u043C\u043F\u0435\u0440\u0430\u0442\u0443\u0440\u0430:");
		label_6.setBounds(402, 207, 174, 14);
		add(label_6);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(577, 236, 36, 14);
		add(lblNewLabel_3);
		JSlider slider_temp = new JSlider();
		slider_temp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblNewLabel_3.setText(Integer.toString(slider_temp.getValue()));
				AverageTemp = slider_temp.getValue();
				slider_thunderChance.setValue(calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape));
			}
		});
		slider_temp.setMaximum(50);
		slider_temp.setMinimum(-5);
		slider_temp.setValue(-5);
		slider_temp.setBounds(402, 232, 174, 23);
		add(slider_temp);

		JButton button = new JButton("\u0423\u0434\u0430\u043B\u0438\u0442\u044C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove();
			}
		});
		button.setBounds(505, 67, 93, 23);
		add(button);

		JButton btnNewButton_1 = new JButton(
				"\u0413\u0415\u041D\u0415\u0420\u0418\u0420\u041E\u0412\u0410\u0422\u042C!");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// addToArrayFront(1, 60, 1);
				startGeneration();
			}
		});
		btnNewButton_1.setBounds(402, 384, 196, 49);
		add(btnNewButton_1);

		JLabel label_3 = new JLabel(
				"\u0421\u0440\u0435\u0434\u043D\u044F\u044F \u0432\u043B\u0430\u0436\u043D\u043E\u0441\u0442\u044C:");
		label_3.setBounds(402, 265, 174, 14);
		add(label_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(577, 294, 31, 14);
		add(lblNewLabel_4);

		JSlider slider_hydro = new JSlider();
		slider_hydro.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblNewLabel_4.setText(Integer.toString(slider_hydro.getValue()));
				AverageHydro = slider_hydro.getValue();
				slider_thunderChance.setValue(calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape));
			}
		});
		slider_hydro.setValue(0);
		slider_hydro.setBounds(402, 290, 174, 23);
		add(slider_hydro);

		JLabel label_7 = new JLabel(
				"\u0412\u0435\u0440\u043E\u044F\u0442\u043D\u043E\u0441\u0442\u044C \u0433\u0440\u043E\u0437\u044B:");
		label_7.setBounds(10, 316, 154, 14);
		add(label_7);

		JLabel lblHours = new JLabel("");
		lblHours.setBounds(49, 401, 21, 14);
		add(lblHours);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(10, 394, 344, 31);
		add(progressBar);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println(arg0.getX() + " " + arg0.getX());
				for (int i = 0; i < townsList.size(); i++) {
					if ((townsList.get(i).getX() <= arg0.getX()) && (arg0.getX() <= townsList.get(i).getX() + 100)
							&& (townsList.get(i).getY() <= arg0.getY())
							&& (arg0.getY() <= townsList.get(i).getY() + 30)) {

						/*
						 * if (selectedIndex != -1) {
						 * townsList.get(selectedIndex).isSelected = false;
						 * townsList.get(selectedIndex).update(); }
						 */

						selectedIndex = i;

						String value = townsList.get(selectedIndex).value;
						String name = townsList.get(selectedIndex).name;
						/*
						 * if (townsList.get(selectedIndex).isSelected == false)
						 * { selectedIndex = -1; }
						 */
						townsList.get(i).mouseClicked(arg0);

					}

				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

				for (int i = 0; i < townsList.size(); i++) {
					if ((townsList.get(i).getX() <= arg0.getX()) && (arg0.getX() <= townsList.get(i).getX() + 100)
							&& (townsList.get(i).getY() <= arg0.getY())
							&& (arg0.getY() <= townsList.get(i).getY() + 30)) {
						/*
						 * townsList.get(i).screenX = arg0.getX();
						 * townsList.get(i).screenY = arg0.getY();
						 */

						townsList.get(i).mousePressed(arg0);

					}

				}
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {

				for (int i = 0; i < townsList.size(); i++) {
					if ((townsList.get(i).getX() <= e.getX()) && (e.getX() <= townsList.get(i).getX() + 100)
							&& (townsList.get(i).getY() <= e.getY()) && (e.getY() <= townsList.get(i).getY() + 100)) {
						townsList.get(i).mouseDragged(e);
					}

				}
				repaint();

			}

			public void mouseMoved(MouseEvent e) {
			}

		});

	}

	public void SaveGif() throws IOException {
		int timeLimit = Integer.parseInt(textField_2.getText());
		// grab the output image type from the first image in the sequence
		BufferedImage firstImage = ImageIO.read(new File("maps/defaultMap.png"));

		ImageOutputStream output = new FileImageOutputStream(new File("maps/animated.gif"));
		GifSequenceWriter writer = new GifSequenceWriter(output, firstImage.getType(), 10, true);

		writer.writeToSequence(firstImage);
		for (int i = 0; i < mapImgNumber; i++) {
			BufferedImage nextImage = ImageIO.read(new File("maps/map" + i + ".png"));
			writer.writeToSequence(nextImage);
		}
		writer.close();
		output.close();
	}

	public void SaveMapImg() {
		BufferedImage bufImage1 = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
		paint(bufImage1.createGraphics());
		bufImage1 = bufImage1.getSubimage(0, 0, boundMapX, boundMapY);
		try {
			ImageIO.write(bufImage1, "png", new File("maps/map" + mapImgNumber + ".png"));
		} catch (Exception ex) {
		}
		mapImgNumber++;

	}

	public void SaveWeather() {
		for (int i = 0; i < townsList.size(); i++) {
			try (FileWriter fw = new FileWriter("weather/save" + townsList.get(i).value + ".wthr", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println(townsList.get(i).name);
				out.println(townsList.get(i).value);
				out.println(townsList.get(i).cape);
				out.println(townsList.get(i).li);
				out.println(townsList.get(i).temp);
				out.println(townsList.get(i).hydro);
				out.println(townsList.get(i).isThunder);
				out.println(townsList.get(i).isFront);
			} catch (IOException e) {

			}
		}
	}

	public void SaveWeatherToImg() throws IOException {

		for (int j = 0; j < townsList.size(); j++) {
			BufferedReader reader = new BufferedReader(
					new FileReader("weather/save" + townsList.get(j).value + ".wthr"));
			String line;
			ArrayList<String> lines = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			String[] Array = lines.toArray(new String[lines.size()]);
			System.out.println(Array.length);
			BufferedImage table = new BufferedImage(810, time * 30, BufferedImage.TYPE_INT_RGB);

			Graphics g = (Graphics2D) table.getGraphics();

			g.setFont(new Font("Arial", Font.BOLD, 27));

			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, table.getWidth(), table.getHeight());
			g.setColor(Color.BLUE);
			g.drawString(Array[0], 10, 30);
			g.drawString(Array[1], 200, 30);
			g.drawString("CAPE", 10, 60);
			g.drawString("LI", 110, 60);
			g.drawString("Температура", 170, 60);
			g.drawString("Влажность", 400, 60);
			g.drawString("Гроза", 600, 60);
			g.drawString("Фронт", 720, 60);
			ImageIO.write(table, "png", new File("weather/" + Array[0] + ".png"));
			// g.drawString(name, 30, 20);
			// g.drawString(value, 30, 45);

			for (int i = 0; i < Array.length; i++) {

				if ((i) % 8 == 0) {
					String cape = Array[i + 2];
					String li = Array[i + 3];
					String temp = Array[i + 4];
					String hydro = Array[i + 5];
					String isThunder = Array[i + 6];
					String isFront = Array[i + 7];
					g.drawString(cape, 10, (i / 8) * 30 + 90);
					g.drawString(li, 110, (i / 8) * 30 + 90);
					g.drawString(temp, 170, (i / 8) * 30 + 90);
					g.drawString(hydro, 400, (i / 8) * 30 + 90);

					if (Integer.parseInt(isThunder) == 1) {
						g.drawString("Да", 600, (i / 8) * 30 + 90);
					} else {
						g.drawString("Нет", 600, (i / 8) * 30 + 90);
					}

					if (Integer.parseInt(isFront) == 1) {
						g.drawString("ХФ", 720, (i / 8) * 30 + 90);
					} else if (Integer.parseInt(isFront) == 2) {
						g.drawString("ТФ", 720, (i / 8) * 30 + 90);
					} else {
						g.drawString("Нет", 720, (i / 8) * 30 + 90);
					}

				}
			}
			ImageIO.write(table, "png", new File("weather/" + Array[0] + ".png"));
			reader.close();
		}

	}

	public void Update() {
		for (int i = 0; i < townsList.size(); i++) {
			add(townsList.get(i));
			repaint();
		}
		for (int i = 0; i < strikeList.size(); i++) {
			add(strikeList.get(i));
			repaint();
		}
		for (int i = 0; i < frontList.size(); i++) {
			add(frontList.get(i));
			repaint();
		}

	}

	public void updateThunder() {
		for (int i = 0; i < thunderList.size(); i++) {
			for (int j = 0; j < thunderList.get(i).strikeList.size(); j++) {
				add(thunderList.get(i).strikeList.get(j));
				if ((thunderList.get(i).strikeList.get(j).x >= boundMapX)
						|| (thunderList.get(i).strikeList.get(j).y >= boundMapY)) {
					remove(thunderList.get(i).strikeList.get(j));
					thunderList.get(i).strikeList.remove(j);
					repaint();
				}
			}
		}
	}

	public void addToArrayTown(String name, String value, int X, int Y, boolean isSelected) {
		townsList.add(new Town(name, value, townsList.size() - 1, X, Y, isSelected));
		Update();
	}

	public void addToArrayStrike(int x, int y) {
		strikeList.add(new Strike(x, y));
		Update();
	}

	public void addToArrayFront(int type, int x, int speed) {
		frontList.add(new Front(type, x, speed));
		Update();
	}

	public void addToArrayThunder(int limitTime, int x, int y, int x2, int y2, int speedX, int speedY) {
		thunderList.add(new Thunder(limitTime, x, y, x2, y2, speedX, speedY));
		Update();
	}

	public void remove() {
		remove(townsList.get(selectedIndex));
		townsList.remove(selectedIndex);
		repaint();

	}

	public int calcThunderChance(int Li, int Hydro, int Temp, int Cape) {
		if ((Li > 2) || (Hydro < 20) || (Temp < -3)) {
			return 0;
		} else {
			return ((Li * -5) + (Hydro / 3) + (Cape / 200) + (Temp / 2));
		}
	}
	/*
	 * public void createThunder(int timeLive){ int thunderChance =
	 * calcThunderChance() - 20 + (int) (Math.random() * (calcThunderChance() +
	 * 20)); if (thunderChance >= 50) { int x = 0 + (int) (Math.random() *
	 * (boundMapX-30)); int y = (int) (Math.random() * 275);
	 * addToArrayThunder(timeLive, x, y, x + (int) (Math.random() * 30), y +
	 * (int) (Math.random() *
	 * 30),(int)(Math.random()*20),(int)(Math.random()*20)); } }
	 */

	public void generateParameters() {
		li = AverageLi - 2 + (int) (Math.random() * (5));
		cape = AverageCape - 2000 + (int) (Math.random() * (4000));
		hydro = AverageHydro - 20 + (int) (Math.random() * (40));
		temp = AverageTemp - 3 + (int) (Math.random() * (6));
		if (hydro > 100) {
			hydro = 100;
		}
	}
	/*
	 * public void saveParametersTown(){ for (int i = 0;i<townsList.size();i++){
	 * townsList.get(i).cape = cape; townsList.get(i).li = li;
	 * townsList.get(i).hydro = hydro; townsList.get(i).temp = temp; } }
	 */

	public void startGeneration() {

		File maps = new File("maps");
		maps.mkdirs();
		File weather = new File("weather");
		weather.mkdirs();
		saveDefaultMap();
		if (textField_2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Введите период!");
		} else {
			int timeLimit = Integer.parseInt(textField_2.getText());
			progressBar.setMaximum(timeLimit);
			Timer generation = new Timer(100, new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (time >= timeLimit) {
						((Timer) arg0.getSource()).stop();
						try {
							SaveWeatherToImg();
							SaveGif();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					for (int i = 0; i < frontList.size(); i++) {
						if (frontList.get(i).x + 10 >= boundMapX) {
							remove(frontList.get(i));
							frontList.remove(i);
							repaint();
						}
					}

					for (int i = 0; i < thunderList.size(); i++) {
						thunderList.get(i).motion();
						if ((thunderList.get(i).x >= boundMapX) || (thunderList.get(i).y >= boundMapY)
								|| (thunderList.get(i).x2 >= boundMapX) || (thunderList.get(i).x >= boundMapY)) {
							thunderList.get(i).isDead = true;
						}

						for (int j = 0; j < thunderList.get(i).strikeList.size(); j++) {
							thunderList.get(i).strikeList.get(j).increaseTime();
							repaint();
							if (thunderList.get(i).strikeList.get(j).time >= 25) {
								remove((thunderList.get(i).strikeList.get(j)));
								thunderList.get(i).strikeList.remove(j);
								repaint();
								// updateThunder();
							}

						}
						/*
						 * if
						 * ((thunderList.get(i).x>=boundMapX)||(thunderList.get(
						 * i).y >=boundMapY)
						 * ||(thunderList.get(i).x2>=boundMapX)||(thunderList.
						 * get(i) .x>= boundMapY)){ for (int x = 0; x<100;x++){
						 * for (int j = 0; j <
						 * thunderList.get(i).strikeList.size(); j++) {
						 * remove((thunderList.get(i).strikeList.get(j)));
						 * thunderList.get(i).strikeList.remove(j); repaint(); }
						 * } thunderList.remove(i); repaint();
						 * 
						 * }
						 */
						if (thunderList.get(i).time >= thunderList.get(i).limitTime) {
							thunderList.get(i).isDead = true;
							boolean fullDead = false;
							for (int j = 0; j < thunderList.get(i).strikeList.size(); j++) {
								if (thunderList.get(i).strikeList.size() == 0) {
									fullDead = true;
								}

								if (fullDead) {
									remove((thunderList.get(i).strikeList.get(j)));
									thunderList.get(i).strikeList.remove(j);
									repaint();
									thunderList.remove(i);
								}
							}

						}

					}

					for (int i = 0; i < frontList.size(); i++) {
						frontList.get(i).motion();
					}

					if (time % 72 == 0) {
						int chanceFront = (int) (Math.random() * 100);
						if (chanceFront >= 50) {
							addToArrayFront(1 + (int) (Math.random() * 2), 0, 1 + (int) (Math.random() * 7));
						}
					}
					for (int i = 0; i < frontList.size(); i++) {
						for (int j = 0; j < townsList.size(); j++) {
							oldFrontCounter = frontCounter;
							if (Math.abs(frontList.get(i).x - townsList.get(j).getX()) <= 20) {
								frontCounter++;
							}
							if (oldFrontCounter != frontCounter) {
								townsList.get(j).isFront = frontList.get(i).type;

							} else {
								townsList.get(j).isFront = 0;
							}

						}

					}
					for (int i = 0; i < townsList.size(); i++) {
						generateParameters();
						townsList.get(i).li = li;
						townsList.get(i).cape = cape;
						townsList.get(i).hydro = hydro;
						townsList.get(i).temp = temp;
						if (townsList.get(i).isFront == 1) {
							townsList.get(i).li -= 3;
							townsList.get(i).cape += 700;
							townsList.get(i).temp -= 5;

						} else if (townsList.get(i).isFront == 2) {
							townsList.get(i).li -= 1;
							townsList.get(i).cape += 400;
							townsList.get(i).temp += 5;
						}

						if (hydro > 100) {
							hydro = 100;
						}
					}
					for (int i = 0; i < frontList.size(); i++) {

						if (frontList.get(i).type == 1) {
							int thunderChance = calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape)
									- 20 + (int) (Math.random() * (40));

							if (thunderChance >= 45) {
								int x = frontList.get(i).x + (int) (Math.random() * (frontList.get(i).x + 20));
								int y = (int) (Math.random() * (boundMapY - 30));
								addToArrayThunder(3 + (int) (Math.random() * 10), x, y,
										x + 10 + (int) (Math.random() * 30), y + 10 + (int) (Math.random() * 30),
										(int) (Math.random() * 7), (int) (Math.random() * 7));
							}
						} else {
							int thunderChance = calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape)
									- 20 + (int) (Math.random() * (40));

							if (thunderChance >= 70) {
								int x = frontList.get(i).x + (int) (Math.random() * (frontList.get(i).x + 20));
								int y = (int) (Math.random() * (boundMapY - 30));
								addToArrayThunder(2 + (int) (Math.random() * 4), x, y,
										x + 10 + (int) (Math.random() * 30), y + 10 + (int) (Math.random() * 30),
										(int) (Math.random() * 7), (int) (Math.random() * 7));
							}
						}

					}
					boolean isThunderPlaced = false;
					for (int i = 0; i < townsList.size(); i++) {
						isThunderPlaced = false;
						if ((int) (Math.random() * 100) <= 20) {
							if (calcThunderChance(townsList.get(i).li, townsList.get(i).hydro, townsList.get(i).temp,
									townsList.get(i).cape) >= 50) {
								int x = townsList.get(i).getX() - 50 + (int) (Math.random() * 100);
								int y = townsList.get(i).getY() - 50 + (int) (Math.random() * 100);
								addToArrayThunder(2 + (int) (Math.random() * 6), x, y, x + (int) (Math.random() * 20),
										y + (int) (Math.random() * 20), (int) (Math.random() * 7),
										(int) (Math.random() * 7));
								isThunderPlaced = true;
								System.out.println("lol");

							}
						}

					}
					if (!isThunderPlaced) {
						if ((int) (Math.random() * 100) <= 70) {
							int thunderChance = calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape)
									- 20
									+ (int) (Math.random()
											* (calcThunderChance(AverageLi, AverageHydro, AverageTemp, AverageCape)
													+ 20));
							if (thunderChance >= 50) {
								int x = 0 + (int) (Math.random() * (boundMapX - 30));
								int y = (int) (Math.random() * (boundMapY - 30));
								addToArrayThunder(2 + (int) (Math.random() * 6), x, y,
										x + 10 + (int) (Math.random() * 30), y + 10 + (int) (Math.random() * 30),
										(int) (Math.random() * 7), (int) (Math.random() * 7));
							}
						}
					}
					// Update();
					for (int i = 0; i < thunderList.size(); i++) {
						thunderList.get(i).generateStrikes((int) (Math.random() * 10));
						thunderList.get(i).increaseTime();
						repaint();
						updateThunder();
						for (int j = 0; j < townsList.size(); j++) {
							oldThunderCounter = thunderCounter;
							if ((thunderList.get(i).x - 10 <= townsList.get(j).getX())
									&& (townsList.get(j).getX() <= thunderList.get(i).x2 + 10)
									&& (thunderList.get(i).y - 10 <= townsList.get(j).getY())
									&& (townsList.get(j).getY() <= thunderList.get(i).y2 + 10)) {
								thunderCounter++;
							}
							if (oldThunderCounter != thunderCounter) {
								townsList.get(j).isThunder = 1;
							} else {
								townsList.get(j).isThunder = 0;
							}
						}
					}

					SaveMapImg();
					time++;
					SaveWeather();
					progressBar.setValue(time);
				}
			});

			generation.start();
		}
	}

	public void saveDefaultMap() {
		BufferedImage bufImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
		paint(bufImage.createGraphics());
		bufImage = bufImage.getSubimage(0, 0, boundMapX, boundMapY);
		try {
			// imageFile.createNewFile();
			ImageIO.write(bufImage, "jpeg", new File("maps/defaultMap.png"));
		} catch (Exception ex) {
		}

	}
}
