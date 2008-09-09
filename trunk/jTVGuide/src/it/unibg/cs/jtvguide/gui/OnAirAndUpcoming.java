package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.util.FileUtils;
import it.unibg.cs.jtvguide.util.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.awt.GridLayout;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class OnAirAndUpcoming extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9221049871768124350L;
	private static Thread thread;
	JPanel jp = new JPanel();
	Random r = new Random();
	Schedule schedule;
	static int counter = FileUtils.uncommentedLinesCount(UserPreferences
			.getXmltvConfigFile());

	public OnAirAndUpcoming(String title) {
		super(title);
		add(jp);
		XMLTVCommander xmltvc = new XMLTVCommander();
		XMLTVParserImpl xmltvParser = new XMLTVParserImpl();
		int tries = 0;

		while (!UserPreferences.loadFromXMLFile()
				|| !UserPreferences.getXmltvConfigFile().exists()
				|| UserPreferences.getXmltvConfigFile().length() == 0) {
			System.out.println("Configuring jTVGuide and XMLTV...");
			xmltvc.configureXMLTV();
			UserPreferences.saveToXMLFile();
		}

		boolean parsed = false;
		while (parsed == false && tries <= 3) {
			if (!new XMLTVScheduleInspector().isUpToDate()
					|| !MD5Checksum.checkMD5(UserPreferences
							.getXmltvConfigFile().toString(), MD5Checksum
							.readMD5FromFile())) {
				System.out.println("Updating schedule...");
				xmltvc.downloadSchedule();
			}
			if (tries >= 1) {
				System.out
				.println("Couldn't parsing. Downloading a new schedule.");
				UserPreferences.getXmltvOutputFile().delete();
				xmltvc.downloadSchedule();
			}
			if (tries == 4)
				throw new RuntimeException(
				"Couldn't download or parse schedule");
			System.out.println("Trying to parse schedule...");
			parsed = xmltvParser.parse();
			tries++;
		}
		schedule = xmltvParser.getSchedule();
		System.out.println("Schedule parsed correctly.");
		thread = new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		OnAirAndUpcoming jtv = new OnAirAndUpcoming("jTVGuide v2.0");
		jtv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtv.setSize(1000, 800);
		jtv.setVisible(true);
	}

	public void run() {
		while (true) {
			if (schedule != null) {
				jp.removeAll();
				List<Program> lk = schedule.getOnAirPrograms();
				List<Program> lp = schedule.getUpcomingPrograms();
				if (lk.size() != counter || lp.size() != counter)
					throw new RuntimeException("mismatch");
				jp.setLayout(new GridLayout(lk.size() + lp.size(), 2));
				for (Program p : lk) {
					JProgressBar jb = new JProgressBar();
					jb.setString(p.getInfo());
					jb.setStringPainted(true);
					jb.setValue(p.getCompletionPercentile());
					jp.add(new JLabel(p.toString()));
					jp.add(jb);
				}
				for (Program p : lp) {
					jp.add(new JLabel(p.toString()));
					jp.add(new JLabel(p.getInfo()));
				}
				jp.revalidate();
			}
			try {
				Thread.sleep(1000 * (r.nextInt(5) + 5));
			} catch (InterruptedException e) {
			}
		}
	}
}
