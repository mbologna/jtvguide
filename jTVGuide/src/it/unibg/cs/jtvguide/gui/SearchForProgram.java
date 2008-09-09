package it.unibg.cs.jtvguide.gui;

import it.unibg.cs.jtvguide.UserPreferences;
import it.unibg.cs.jtvguide.model.Program;
import it.unibg.cs.jtvguide.model.Schedule;
import it.unibg.cs.jtvguide.model.XMLTVScheduleInspector;
import it.unibg.cs.jtvguide.util.MD5Checksum;
import it.unibg.cs.jtvguide.xmltv.XMLTVCommander;
import it.unibg.cs.jtvguide.xmltv.XMLTVParserImpl;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SearchForProgram extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2219149016130289380L;
	/**
	 * 
	 */

	JPanel jp = new JPanel();
	JTextField jt = new JTextField();
	JTextArea ja = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(ja);
	Schedule schedule;

	public SearchForProgram(String title) {
		super(title);
		setLayout(new BorderLayout());
		jt.getDocument().addDocumentListener(new MyListener());

		add(BorderLayout.NORTH, jt);
		add(BorderLayout.CENTER, scrollPane);
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
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SearchForProgram jtv = new SearchForProgram("jTVGuide v2.0");
		jtv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jtv.setSize(1000, 800);
		jtv.setVisible(true);
	}

	class MyListener implements DocumentListener {

		@Override
		public void insertUpdate(DocumentEvent e) {
			if (jt.getText().length() >= 2)
				search(jt.getText());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (jt.getText().length() >= 2)
				search(jt.getText());
			else
				ja.setText("");
		}

		public void search(String pattern) {
			List<Program> s = schedule.getProgramsByName(pattern);
			if (s.size() > 0) {
				ja.setText("");
				for (Program program : s) {
					if (ja.getText().equals(""))
						ja.setText(program.toString() + "   "
								+ program.getInfo());
					else
						ja.setText(ja.getText() + '\n' + program.toString()
								+ "   " + program.getInfo());
				}
			}
		}

		@Override
		public void changedUpdate(DocumentEvent arg0) {
		}

	}
}
