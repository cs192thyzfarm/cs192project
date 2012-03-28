//compile with javac -cp .:jfreechart-1.0.14.jar Home.java
//run with  java -cp .:sqlitejdbc-v056.jar:jcommon-1.0.17.jar:jfreechart-1.0.14.jar Home

import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.image.*;
import java.io.File;

/**
 * project desciption: The project is to develop a computer application that would keep and organize farm records.
 * The application would become a back-up for paper records and it would make it easier to view the records.
 * @author
 * Ted Guilliano Chua, tschua@up.edu.ph
 * Charmaine Pamela Legaspi, calegaspi@up.edu.ph
 * Nathan Lemuel Santi, ngsanti@up.edu.ph
 * Ric Janus Sapasap, rosapasap@up.edu.ph
 */
public class Home extends JFrame implements ActionListener{
	int index = 0;

	// Variables declaration - do not modify//GEN-BEGIN:variables
    //SQL declarations
    /**
     * Connection to SQLite database
     */
    private static Connection sqlconn;
    /**
     * SQL statements (creating a table/adding to existing table)
     */
    private static Statement sqlstat;
    /**
     * Inserting values to a created/existing table
     */
    private static PreparedStatement sqlprep;
    /**
     * SQL queries
     */
    private static ResultSet sqlrs;
    
    //Auto-complete
    /**
     * Popup for showing the possible values to complete the user input
     */
    private static Popup showEars;
    /**
     * Used to generate popups whenever needed
     */
    private static PopupFactory popups;
    /**
     * List that contains the auto-complete 
     */
    private static JList popupEars;
    /**
     * Scroll pane for the auto-complete list
     */
    private static JScrollPane popupScroll;
	/**
	 * The table that contains the values from the showHistory panel
	 */
    private static JTable historyTable;
    /**
     * Scroll pane for the table for the showHistory panel
     */
    private static JScrollPane historyScroll;
    /**
     * Model for the style of table used
     */
    private static DefaultTableModel model;
    /**
     * button for exporting the image file in the show summaryPanel
     */
    private JButton exportButton1;
    
    /**
     * status of the GUI (what is currently shown)
     * (1 == home)
     * (2 == breeding)
     * (3 == farrowing)
     * (4 == mortality)
     * (5 == inventory)
     * (6 == finisher)
     * (7 == butchered)
     * (8 == salaries)
     * (9 == micellaneous)
     * (10 == summary)
     * (11 == add employee, buyer, item)
     */
    private int status;
    
    //GUI left side buttons
    /**
     * Home button
     */
    private JButton nav1;
	/**
	 * Add Breeding Item button
	 */
    private JButton nav2;
    /**
     * Add Farrowing Item button
     */
    private JButton nav3;
    /**
     * Add Mortality Item button
     */
    private JButton nav4;
    /**
     * Add Inventory Item button
     */
    private JButton nav5;
    /**
     * Add Finisher Sales Item button
     */
    private JButton nav6;
    /**
     * Add Butchered Sales Item button
     */
    private JButton nav7;
    /**
     * Add Salaries Expenses Item button
     */
    private JButton nav8;
    /**
     * Add Micellaneous Item button
     */
    private JButton nav9;
    /**
     * Show Summary button
     */
    private JButton nav10;
    /**
     * Add employee, buyer, or item button
     */
    private JButton nav11;
	/**
	 * Show History button
	 */
	private JButton nav12;
    
    //Panels declaration
    /**
     * Panel for the buttons on the left side
     */
    private JPanel navButtonsPanel;
	/**
	 * Panel to show the main page of the program
	 */
	private JPanel homePanel;
	
    /**
     * Panel for the contents of the add breeding item
     */
    private JPanel breedingPanel;
    /**
     * Panel for the contents of the add farrowing item
     */
    private JPanel farrowingPanel;
    /**
     * Panel for the contents of the add mortality item
     */
    private JPanel mortalityPanel;
    /**
     * Panel for the contents of the add inventory item
     */
    private JPanel inventoryPanel;
    /**
     * Panel for the contents of the add finisher sales item
     */
    private JPanel finisherPanel;
    /**
     * Panel for the contents of the add butchered sales item
     */
    private JPanel butcheredPanel;
    /**
     * Panel for the contents of the add salaries expenses item
     */
    private JPanel salariesPanel;
    /**
     * Panel for the contents of the add miscellaneous item
     */
    private JPanel micellaneousPanel;
    /**
     * Panel for the contents of the show summary
     */
    private JPanel summaryPanel;
    /**
     * Panel for the contents of the add employee, buyer, or item
     */
    private JPanel addPanel;
	
	private JPanel historyPanel;
    //Home Labels
    /**
     * Label (title) on top of the panel ie. Home, add Inventory Item, etc.
     */
    private JLabel titleLabel;
    /**
     * Label of the buttons on the left side
     */    
    private JLabel navLabel;
	
	private JLabel correctNavLabel;
	
    //Breeding Labels, TextFields, and Buttons
    /**
     * Label of breeding date
     */
    private JLabel breedingDateLabel;
    /**
     * Label of sow no.
     */
    private JLabel breedingSowLabel;
    /**
     * Label of parity
     */
    private JLabel breedingParityLabel;
    /**
     * Label of boar no.
     */	 
	private JComboBox breedingBreedingMonths;
	private JComboBox breedingBreedingDays;
	private JTextField breedingBreedingYear;
	
	private JComboBox breedingFarrowingMonths;
	private JComboBox breedingFarrowingDays;
	private JTextField breedingFarrowingYear;
	
    private JLabel breedingBoarLabel;
    /**
     * Label of farrowing date
     */
    private JLabel breedingFarrowingLabel;
    /**
     * Label of breedingNotesText
     */
    private JLabel breedingNotesLabel;
    /**
     * TextField of breeding date
     */
    private JTextField breedingBreedingDateText;
    /**
     * TextField of sow no.
     */
    private JTextField breedingSowText;
    /**
     * TextField of parity
     */
    private JTextField breedingParityText;
    /**
     * TextField of boar no.
     */
    private JTextField breedingBoarText;
    /**
     * TextField of farrowing date
     */
    private JTextField breedingFarrowingText;
    /**
     * TextArea of breedingNotesText
     */
    private JTextArea breedingNotesText;
    /**
     * ScrollPane of breedingNotesText
     */
    private JScrollPane breedingNotesScroll;
    /**
     * Button of breedingSubmitButton
     */
    private JButton breedingSubmitButton;
    /**
     * Button of clear
     */
    private JButton breedingClearButton;  
    
    //Farrowing Labels, TextFields, and Buttons
	private JComboBox farrowingMonths;
	private JComboBox farrowingDays;
	private JTextField farrowingYear;
    private JLabel farrowingDateLabel;
    private JLabel farrowingSowLabel;
    private JLabel farrowingBoarLabel;
    private JLabel farrowingLiveLabel;
    private JLabel farrowingEarLabel;
    private JLabel farrowingStillLabel;
    private JLabel farrowingMummifiedLabel;
    private JLabel farrowingAbnormalLabel;
    private JLabel farrowingNotesLabel;
    private JTextField farrowingDateText;
    private JTextField farrowingSowText;
    private JTextField farrowingBoarText;
    private JTextField farrowingLiveText;
    private JTextField farrowingEarText;   
    private JTextField farrowingStillText;
    private JTextField farrowingMummifiedText; 
    private JTextField farrowingAbnormalText;
    private JTextArea farrowingNotesText;
    private JScrollPane farrowingNotesScroll;
    private JButton farrowingSubmitButton;
    private JButton farrowingClearButton;
    
    //Mortality Labels, TextFields, and Buttons
	private JComboBox mortalityMonths;
	private JComboBox mortalityDays;
	private JTextField mortalityYear;
    private JLabel mortalityDateLabel;
    private JLabel mortalityEarLabel;
    private JLabel mortalityBuildingLabel;
    private JLabel mortalityEmployeeLabel;
    private JLabel mortalityCauseLabel;
    private JTextField mortalityDateText;
    private JTextField mortalityEarText;
    private JTextField mortalityBuildingText;
    private JComboBox mortalityEmployeeBox;
    private JTextField mortalityCauseText;
    private JButton mortalitySubmitButton;
    private JButton mortalityClearButton;
    
    //Inventory Labels, TextFields, and Buttons
	private JComboBox inventoryMonths;
	private JComboBox inventoryDays;
	private JTextField inventoryYear;
    private JLabel inventoryAmountLabel;
    private JLabel inventoryDateLabel;
    private JLabel inventoryReceiptLabel;
    private JLabel inventoryItemLabel;
    private JLabel inventoryQuantityLabel;
    private JTextField inventoryReceiptText;
    private JTextField inventoryQuantityText;
    private JTextField inventoryAmountText;
    private JTextField inventoryDateText;
    private JComboBox inventoryItemBox;
    private JButton inventorySubmitButton;
    private JButton inventoryClearButton;
    
    //Finisher Labels, TextFields, and Buttons  
	private JComboBox finisherMonths;
	private JComboBox finisherDays;
	private JTextField finisherYear;
    private JLabel finisherDateLabel;
    private JLabel finisherBuyerLabel;
    private JLabel finisherHeadsLabel;
    private JLabel finisherWeightLabel;
    private JLabel finisherKilosLabel;
    private JLabel finisherPriceLabel;
    private JLabel finisherAmountLabel;  
    private JLabel finisherEarsLabel;
    private JTextField finisherDateText;
    private JComboBox finisherBuyerBox;
    private JTextField finisherHeadsText;
    private JTextField finisherWeightText; 
    private JTextField finisherKilosText;
    private JTextField finisherPriceText;
    private JTextField finisherAmountText;
    private JTextArea finisherEarsText;
    private JScrollPane finisherEarsScroll;
    private JButton finisherSubmitButton;
    private JButton finisherClearButton;

    //Butchered Labels, TextFields, and Buttons
	private JComboBox butcheredButcherMonths;
	private JComboBox butcheredButcherDays;
	private JTextField butcheredButcherYear;
	private JComboBox butcheredSoldMonths;
	private JComboBox butcheredSoldDays;
	private JTextField butcheredSoldYear;
    private JLabel butcheredButcherLabel;
    private JLabel butcheredEarLabel;
    private JLabel butcheredWeight1Label;
    private JLabel butcheredBuyerLabel;
    private JLabel butcheredSoldLabel;
    private JLabel butcheredPriceLabel;
    private JLabel butcheredWeight2Label;
    private JLabel butcheredAmountLabel;
    private JLabel butcheredKg1Label;
    private JLabel butcheredPhp1Label;
    private JLabel butcheredKg2Label;
    private JLabel butcheredPhp2Label;
    private JTextField butcheredButcherText;
    private JTextField butcheredEarText;
    private JTextField butcheredWeight1Text;
    private JComboBox butcheredBuyerBox;    
    private JTextField butcheredSoldText;
    private JTextField butcheredPriceText;
    private JTextField butcheredWeight2Text;    
    private JTextField butcheredAmountText; 
    private JButton butcheredSubmitButton;
    private JButton butcheredClearButton;
    
    //Salaries Labels, TextFields, and Buttons
	private JComboBox salariesStartMonths;
	private JComboBox salariesStartDays;
	private JTextField salariesStartYear;
	private JComboBox salariesEndMonths;
	private JComboBox salariesEndDays;
	private JTextField salariesEndYear;
    private JLabel salariesDate1Label;
    private JLabel salariesDate2Label;
    private JLabel salariesEmployeeLabel;
    private JLabel salariesAmountLabel;
    private JTextField salariesDate1Text;
    private JTextField salariesDate2Text;
    private JComboBox salariesEmployeeBox;
    private JTextField salariesAmountText;
    private JButton salariesSubmitButton;
    private JButton salariesClearButton;
    
    //Micellaneous Labels, TextFields, and Buttons   
	private JComboBox micellaneousMonths;
	private JComboBox micellaneousDays;
	private JTextField micellaneousYear;
    private JLabel micellaneousDateLabel;
    private JLabel micellaneousItemLabel;
    private JLabel micellaneousAmountLabel;
    private JLabel micellaneousNotesLabel;
    private JLabel micellaneousPhpLabel;     
    private JTextField micellaneousDateText;
    private JComboBox micellaneousItemBox;
    private JTextField micellaneousAmountText;  
    private JTextArea micellaneousNotesText;
    private JScrollPane micellaneousNotesScroll;    
    private JButton micellaneousSubmitButton;
    private JButton micellaneousClearButton;
    
    //Summary Labels, TextFields, and Buttons
    private JComboBox summaryStartMonths;
	private JTextField summaryStartYear;
	private JComboBox summaryEndMonths;
	private JTextField summaryEndYear;
	private JComboBox summaryOption;
	
    //History Labels, TextFields, and Buttons
    private JComboBox historyMonth;
	private JTextField historyYear;
	private JComboBox historyOption;
    
    //Add Labels, TextFields, and Buttons
	private JComboBox addAddedMonths;
	private JComboBox addAddedDays;
	private JTextField addAddedYear;
	private JLabel addMonthsLabel;
	private JLabel addDaysLabel;
	private JLabel addYearLabel;
    private JLabel addWhatLabel;
    private JLabel addNameLabel;
	private JLabel addAdditionalLabel;
    private JComboBox addWhatBox;
	private JComboBox addPigBox;
    private JTextField addInputText;
    private JTextField addAdditionalText;
	private JButton dummyAddSubmitButton;
	private JButton dummyAddClearButton;
    private JButton addSubmitButton;
    private JButton addClearButton;
    
    //prepared statements to put values in the tables
    private static PreparedStatement prepBreeding;
    private static PreparedStatement prepBuyers;
    private static PreparedStatement prepEmployees;
    private static PreparedStatement prepFarrowing;
    private static PreparedStatement prepInventoryExpenseDetails;
    private static PreparedStatement prepInventoryExpenses;
    private static PreparedStatement prepItems;
    private static PreparedStatement prepMicellaneousExpenses;
    private static PreparedStatement prepPigs;
    private static PreparedStatement prepPigTypes;
    private static PreparedStatement prepSalaries;
    private static PreparedStatement prepSellingTypes;
    private static PreparedStatement prepSoldAliveDetails;
    private static PreparedStatement prepSoldButcheredDetails;
    private static PreparedStatement prepSoldPigs;    
	
    // End of variables declaration//GEN-END:variables
	
    /**
     * Saves the image file from the show Summary panel outside the program
     */
	private int exportChart(JFreeChart chart)
	{
		try
		{
			String fileName = JOptionPane.showInputDialog(null,"Input Desired Filename(.jpg): ");
			if(fileName != null)
			{
				File temp = new File(fileName+".jpg");
				int ret = JOptionPane.YES_OPTION;
				if(temp.exists())
				{
					ret=JOptionPane.showConfirmDialog(null, "This file exists. Do you want to overwrite?","Confirmation", JOptionPane.YES_NO_OPTION);
				}
				
				if(ret == JOptionPane.YES_OPTION)
				{
					ChartUtilities.saveChartAsJPEG(new File(fileName+".jpg"), chart, 500, 300);
					return 1;
				}
				else
				{
					return 0;
				}
			}
			else
			{
				return -1;
			}
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * Saves the table that is shown in the show history panel outside the program into sa .csv file
	 */
	private int export(JTable jt)
	{
		try
		{
			String fileName = JOptionPane.showInputDialog(null,"Input Desired Filename(.csv): ");
			if(fileName != null)
			{
				File temp = new File(fileName+".csv");
				int ret = JOptionPane.YES_OPTION;
				if(temp.exists())
				{
					ret=JOptionPane.showConfirmDialog(null, "This file exists. Do you want to overwrite?","Confirmation", JOptionPane.YES_NO_OPTION);
				}
				
				if(ret == JOptionPane.YES_OPTION)
				{
					
					TableModel tm = jt.getModel();
					PrintWriter pw = new PrintWriter(temp);
					
					for(int x=0;x<tm.getColumnCount();x++)
					{
						pw.print(tm.getColumnName(x)+",");
					}
					pw.println();
					
					for(int y=0;y<tm.getRowCount();y++)
					{
						for(int x=0;x<tm.getColumnCount();x++)
						{
							if(tm.getValueAt(y,x) != null)
							{
								pw.print(tm.getValueAt(y,x).toString().replace(',',' ')+",");
							}
							else
							{
								pw.print("null,");
							}
						}
						pw.println();
					}
					pw.close();
				}
				else
				{
					return 0;
				}
				
				return 1;
			}
			else
			{
				return -1;
			}
		}catch(IOException ioe)
		{
			ioe.printStackTrace();
			return -1;
		}
	}
    
	/**
	 * clears all the contents of the jtextfields in all panels
	 */
    private void clearAll() {
    	breedingBreedingDateText.setText("");
        breedingSowText.setText("");
        breedingParityText.setText("");
        breedingBoarText.setText("");
        breedingFarrowingText.setText("");
        breedingNotesText.setText("");
        farrowingDateText.setText("");
        farrowingSowText.setText("");
        farrowingBoarText.setText("");
        farrowingLiveText.setText("");
        farrowingEarText.setText("");
        farrowingStillText.setText("");
        farrowingMummifiedText.setText("");
        farrowingAbnormalText.setText("");
        farrowingNotesText.setText("");
        mortalityDateText.setText("");
        mortalityEarText.setText("");
        mortalityBuildingText.setText("");
        mortalityCauseText.setText("");
        inventoryDateText.setText("");
        inventoryReceiptText.setText("");
        inventoryQuantityText.setText(""); 
        inventoryAmountText.setText("");  
    	finisherDateText.setText("");
        finisherHeadsText.setText("");
        finisherWeightText.setText("");
        finisherKilosText.setText("");    
        finisherPriceText.setText("");   
        finisherAmountText.setText("");  
        finisherEarsText.setText("");
    	butcheredButcherText.setText("");
        butcheredEarText.setText("");
        butcheredWeight1Text.setText("");
        butcheredSoldText.setText("");
        butcheredPriceText.setText("");
        butcheredWeight2Text.setText("");
        butcheredAmountText.setText("");  
    	salariesDate1Text.setText("");
        salariesDate2Text.setText("");  
        salariesAmountText.setText(""); 
    	micellaneousDateText.setText("");
        micellaneousAmountText.setText("");
        micellaneousNotesText.setText("");
    	addInputText.setText("");
    	addAdditionalText.setText("");
		
		if(showEars!=null)
		{
			showEars.hide();
		}
    }
    
    /**
     * What to do when a sow number is being typed in the add breeding item
     */
    private void breedingSowPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM pigs WHERE ear_no LIKE '" + breedingSowText.getText() + "%' AND pig_id = 2;"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("ear_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		breedingSowText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)breedingSowText.getLocationOnScreen().getX();
    		int y = (int)breedingSowText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)breedingSowText, (Component)popupScroll, x, y+breedingSowText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
    			if (breedingSowText.getText().length() > 0) {
    				breedingSowText.setText(breedingSowText.getText().substring(0, breedingSowText.getText().length() - 1));
    			}
    			showEars.hide();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * What to do when a boar number is being typed in the add breeding item
     */
    private void breedingBoarPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM pigs WHERE ear_no LIKE '" + breedingBoarText.getText() + "%' AND pig_id = 1;"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("ear_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		breedingBoarText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)breedingBoarText.getLocationOnScreen().getX();
    		int y = (int)breedingBoarText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)breedingBoarText, (Component)popupScroll, x, y+breedingBoarText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
    			if (breedingBoarText.getText().length() > 0) {
    				breedingBoarText.setText(breedingBoarText.getText().substring(0, breedingBoarText.getText().length() - 1));
    			}
    			showEars.hide();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * What to do when a sow number is being typed in the add farrowing item
     */
    private void farrowingSowPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM breeding WHERE sow_no LIKE '" + farrowingSowText.getText() + "%' AND farrowing_month IS NULL;"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("sow_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		farrowingSowText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)farrowingSowText.getLocationOnScreen().getX();
    		int y = (int)farrowingSowText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)farrowingSowText, (Component)popupScroll, x, y+farrowingSowText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
    			if (farrowingSowText.getText().length() > 0) {
    				farrowingSowText.setText(farrowingSowText.getText().substring(0, farrowingSowText.getText().length() - 1));
    			}
    			showEars.hide();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * What to do when a boar number is being typed in the add farrowing item
     */
    private void farrowingBoarPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM breeding WHERE boar_no LIKE '" + farrowingBoarText.getText() + "%' AND farrowing_month IS NULL;"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("boar_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		farrowingBoarText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)farrowingBoarText.getLocationOnScreen().getX();
    		int y = (int)farrowingBoarText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)farrowingBoarText, (Component)popupScroll, x, y+farrowingBoarText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
    			if (farrowingBoarText.getText().length() > 0) {
    				farrowingBoarText.setText(farrowingBoarText.getText().substring(0, farrowingBoarText.getText().length() - 1));
    			}
    			showEars.hide();
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * What to do when an ear number is being entered in the add mortality item
     */
    private void mortalityEarPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM pigs WHERE ear_no LIKE '" + mortalityEarText.getText() + "%' AND month_died = 0;"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("ear_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		mortalityEarText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)mortalityEarText.getLocationOnScreen().getX();
    		int y = (int)mortalityEarText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)mortalityEarText, (Component)popupScroll, x, y+mortalityEarText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
				if(mortalityEarText.getText().length() > 0)
				{
					mortalityEarText.setText(mortalityEarText.getText().substring(0, mortalityEarText.getText().length() - 1));
				}
				showEars.hide();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * What to do when an ear number is being entered in the add butchered sales item
     */
    private void butcheredEarPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM pigs WHERE ear_no LIKE '" + butcheredEarText.getText() + "%' AND month_died = 0 AND pig_id = 3;"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("ear_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		butcheredEarText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)butcheredEarText.getLocationOnScreen().getX();
    		int y = (int)butcheredEarText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)butcheredEarText, (Component)popupScroll, x, y+butcheredEarText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
    			if (butcheredEarText.getText().length() > 0) {
    				butcheredEarText.setText(butcheredEarText.getText().substring(0, butcheredEarText.getText().length() - 1));
    				
    			}
    			showEars.hide();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * What to do when an reicpt number is being entered in the add inventory sales item
     */
    private void inventoryReceiptTextPopupActionPerformed() {   
    	try {
    		if (showEars != null) {
    			showEars.hide();
    		}
    		
    		popups = PopupFactory.getSharedInstance();
        	popupEars = new JList();
    		
        	Vector<String> ears = new Vector<String>();
    		sqlrs = sqlstat.executeQuery("SELECT * FROM inventoryexpenses WHERE delivery_receipt_no LIKE '" + inventoryReceiptText.getText() + "%';"); 
    		while (sqlrs.next()) {
    			ears.add(sqlrs.getString("delivery_receipt_no"));
    		}
    		popupEars = new JList(ears);
    		popupEars.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent evt) {
            		inventoryReceiptText.setText(popupEars.getSelectedValue().toString());
            		showEars.hide();
            	}
    		});
    		popupScroll.setViewportView(popupEars);
    		sqlrs.close();
    		int x = (int)inventoryReceiptText.getLocationOnScreen().getX();
    		int y = (int)inventoryReceiptText.getLocationOnScreen().getY();
    		showEars = popups.getPopup((Component)inventoryReceiptText, (Component)popupScroll, x, y+inventoryReceiptText.getHeight());
    		
    		showEars.show();
    		if (ears.isEmpty()) {
    			if (inventoryReceiptText.getText().length() > 0) {
	    			inventoryReceiptText.setText(inventoryReceiptText.getText().substring(0, inventoryReceiptText.getText().length() - 1));
	    			
    			}
    			showEars.hide();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Convert the string to the associated pig type in the pig types table in the database
     */
    private int pigToInt(String pig) {
		int pigInt = 0;
		if (pig.equalsIgnoreCase("boar")) {
			pigInt = 1;
		} else if (pig.equalsIgnoreCase("sow")) {
			pigInt = 2;
		} else if (pig.equalsIgnoreCase("finisher")) {
			pigInt = 3;
		} else {
			pigInt = 0;
		}
		return pigInt;
	}

    /**
     * Converts the month string to its associated integer value
     */
	private int monthToInt(String month) {
		int monthInt = 0;
		if (month.equalsIgnoreCase("January")) {
			monthInt = 1;
		} else if (month.equalsIgnoreCase("February")) {
			monthInt = 2;
		} else if (month.equalsIgnoreCase("March")) {
			monthInt = 3;
		} else if (month.equalsIgnoreCase("April")) {
			monthInt = 4;
		} else if (month.equalsIgnoreCase("May")) {
			monthInt = 5;
		} else if (month.equalsIgnoreCase("June")) {
			monthInt = 6;
		} else if (month.equalsIgnoreCase("July")) {
			monthInt = 7;
		} else if (month.equalsIgnoreCase("August")) {
			monthInt = 8;
		} else if (month.equalsIgnoreCase("September")) {
			monthInt = 9;
		} else if (month.equalsIgnoreCase("October")) {
			monthInt = 10;
		} else if (month.equalsIgnoreCase("November")) {
			monthInt = 11;
		} else if (month.equalsIgnoreCase("December")) {
			monthInt = 12;
		} else {
			monthInt = 0;
		}
		return monthInt;
	}
	
	/**
	 * checks if the date inputted is after the current system date
	 * returns true if the date is before the current date
	 */
	private boolean validateDate(int month,int day,int year)
	{
		java.util.Date currDate = Calendar.getInstance().getTime();
		java.util.Date inputDate = new java.util.Date(year-1900,month-1,day);
		
		if(currDate.compareTo(inputDate)<0)
		{
			return false;
		}		
		
		return true;
	}
	
	/**
	 * checks of the order of the two dates entered is correct
	 * returns true if the first parameter(start) is before the second parameter(end)
	 */
	private boolean dateOrderCheck(java.util.Date start,java.util.Date end)
	{
		if(start.compareTo(end) <= 0) {
			return false;
		}		
		
		return true;
	}
	
	/**
	 * checks if the entered date is valid ie. no month greater than 12, no day greater than 31, etc.
	 */
	private boolean checkDate(int month,int day,int year)
	{
		boolean isLeap = false;
		
		if(year%100 == 0 && year%400 != 0)
		{	
			isLeap = false;
		}
		else	if(year%4 == 0)
		{
			isLeap = true;
		}
		
		if(month == 2)
		{
			if(isLeap)
			{
				if(day >0 && day <30)
				{
					return true;
				}
				return false;
			}
			else
			{
				if(day >0 && day <29)
				{
					return true;
				}
				return false;
			}
		}
		
		if(month == 4|| month == 6|| month ==9|| month ==11)
		{
			if(day==31)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * the additional combo box for the addPanel when pig is selected
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == addWhatBox)
		{
			if(addWhatBox.getSelectedIndex() == 0)
			{
				addAdditionalLabel.setVisible(false);
				addDaysLabel.setVisible(false);
				addMonthsLabel.setVisible(false);
				addYearLabel.setVisible(false);
				addNameLabel.setVisible(false);
				addInputText.setVisible(false);
				addAdditionalText.setVisible(false);
				addSubmitButton.setVisible(false);
				addClearButton.setVisible(false);
				addAddedMonths.setVisible(false);
				addAddedDays.setVisible(false);
				addAddedYear.setVisible(false);
				addPigBox.setVisible(false);
			} else if (addWhatBox.getSelectedIndex() == 4) {		
				addNameLabel.setText("Receipt Number");
				addAdditionalLabel.setVisible(false);
				addDaysLabel.setVisible(true);
				addMonthsLabel.setVisible(true);
				addYearLabel.setVisible(true);
				addNameLabel.setVisible(true);
				addInputText.setVisible(true);
				addAdditionalText.setVisible(false);
				addSubmitButton.setVisible(true);
				addClearButton.setVisible(true);
				addAddedMonths.setVisible(true);
				addAddedDays.setVisible(true);
				addAddedYear.setVisible(true);
				addPigBox.setVisible(false);		
			} else if(addWhatBox.getSelectedIndex() == 5)
			{
				addNameLabel.setText("Ear Number");
				addAdditionalLabel.setVisible(false);
				addDaysLabel.setVisible(true);
				addMonthsLabel.setVisible(true);
				addYearLabel.setVisible(true);
				addNameLabel.setVisible(true);
				addInputText.setVisible(true);
				addAdditionalText.setVisible(false);
				addSubmitButton.setVisible(true);
				addClearButton.setVisible(true);
				addAddedMonths.setVisible(true);
				addAddedDays.setVisible(true);
				addAddedYear.setVisible(true);
				addPigBox.setVisible(true);
				
			}
			else if(addWhatBox.getSelectedIndex() == 1 ||addWhatBox.getSelectedIndex() == 2)
			{
				addNameLabel.setText("Last Name");
				addAdditionalLabel.setVisible(true);
				addDaysLabel.setVisible(false);
				addMonthsLabel.setVisible(false);
				addYearLabel.setVisible(false);
				addNameLabel.setVisible(true);
				addAdditionalText.setVisible(true);
				addInputText.setVisible(true);
				addSubmitButton.setVisible(true);
				addClearButton.setVisible(true);
				addAddedMonths.setVisible(false);
				addAddedDays.setVisible(false);
				addAddedYear.setVisible(false);
				addPigBox.setVisible(false);
				
			}
			else
			{
				addNameLabel.setText("Name");
				addAdditionalLabel.setVisible(false);
				addDaysLabel.setVisible(false);
				addMonthsLabel.setVisible(false);
				addYearLabel.setVisible(false);
				addNameLabel.setVisible(true);
				addInputText.setVisible(true);
				addAdditionalText.setVisible(false);
				addSubmitButton.setVisible(true);
				addClearButton.setVisible(true);
				addAddedMonths.setVisible(false);
				addAddedDays.setVisible(false);
				addAddedYear.setVisible(false);
				addPigBox.setVisible(false);
			}
		}
	}

    /**
     * Creates new form Home
     */
    public Home() {
    	status = 1;
        initComponents();
		homeComponents();
        setResizable(false);
        this.setTitle("P.I.G.S");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	popupEars = new JList();
    	popupScroll = new JScrollPane();
    	popupScroll.setViewportView(popupEars);
    	historyTable = new JTable();
    	historyScroll = new JScrollPane();
    	historyScroll.setViewportView(historyTable);
    	
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "Septemer", "October", "November", "December"};
		Integer[] days = new Integer[31];
		
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		
		for(int x = 1; x <= 31 ; x++) {
			days[x-1] = x;
		}

		//GUI instantations leftside buttons
		nav1 = new JButton();
        nav2 = new JButton();
        nav3 = new JButton();
        nav4 = new JButton();
        nav5 = new JButton();
        nav6 = new JButton();
        nav7 = new JButton();
        nav8 = new JButton();
        nav9 = new JButton();
        nav10 = new JButton();
        nav11 = new JButton();
		nav12 = new JButton();
		
		homePanel = new JPanel();
		homePanel.setSize(442,467);
		
        //Essential GUI components (Home)
		correctNavLabel = new JLabel("Navigation");
        titleLabel = new JLabel();
        navLabel = new JLabel();       
        navButtonsPanel = new JPanel();
        breedingPanel = new JPanel();
        farrowingPanel = new JPanel();
        mortalityPanel = new JPanel();
        inventoryPanel = new JPanel();
        finisherPanel = new JPanel();
        butcheredPanel = new JPanel();
        salariesPanel = new JPanel();
        micellaneousPanel = new JPanel();
        summaryPanel = new JPanel();
		summaryPanel.setSize(442,467);
        addPanel = new JPanel();
		historyPanel = new JPanel();
		historyPanel.setSize(442,467);
        
        //Breeding labels, text fields, scroll
        breedingPanel = new JPanel();
        breedingDateLabel = new JLabel();
        breedingSowLabel = new JLabel();
        breedingParityLabel = new JLabel();
        breedingBoarLabel = new JLabel();
        breedingFarrowingLabel = new JLabel();
        breedingBreedingDateText = new JTextField();
		breedingBreedingMonths = new JComboBox(months);
		breedingBreedingDays = new JComboBox(days);
		breedingBreedingYear = new JTextField(year + "");
		breedingFarrowingMonths = new JComboBox(months);
		breedingFarrowingDays = new JComboBox(days);
		breedingFarrowingYear = new JTextField(year + "");
        breedingSowText = new JTextField();
        breedingParityText = new JTextField();
        breedingBoarText = new JTextField();
        breedingFarrowingText = new JTextField();
        breedingNotesScroll = new JScrollPane();
        breedingNotesText = new JTextArea();
        breedingNotesLabel = new JLabel();
        
        //Farrowing labels, texts fields, scroll
		farrowingMonths = new JComboBox(months);
		farrowingDays = new JComboBox(days);
		farrowingYear = new JTextField(year + "");
        farrowingDateLabel = new JLabel();
        farrowingSowLabel = new JLabel();
        farrowingBoarLabel = new JLabel();
        farrowingLiveLabel = new JLabel();
        farrowingEarLabel = new JLabel();
        farrowingStillLabel = new JLabel();
        farrowingMummifiedLabel = new JLabel();
        farrowingAbnormalLabel = new JLabel();
        farrowingNotesLabel = new JLabel();       
        farrowingDateText = new JTextField();
        farrowingSowText = new JTextField();
        farrowingBoarText = new JTextField();
        farrowingLiveText = new JTextField();
        farrowingEarText = new JTextField();
        farrowingStillText = new JTextField();
        farrowingMummifiedText = new JTextField();
        farrowingAbnormalText = new JTextField();
        farrowingNotesText = new JTextArea();
        farrowingNotesScroll = new JScrollPane();
        
        //Mortality labels, text fields, scroll
		mortalityMonths = new JComboBox(months);
		mortalityDays = new JComboBox(days);
		mortalityYear = new JTextField(year+"");
        mortalityDateLabel = new JLabel();
        mortalityEarLabel = new JLabel();
        mortalityBuildingLabel = new JLabel();
        mortalityEmployeeLabel = new JLabel();
        mortalityCauseLabel = new JLabel();
        mortalityDateText = new JTextField();
        mortalityEarText = new JTextField();
        mortalityBuildingText = new JTextField();
        mortalityEmployeeBox = new JComboBox();
        mortalityCauseText = new JTextField();
    
        //Inventory labels and corresponding text fields
		inventoryMonths = new JComboBox(months);
		inventoryDays = new JComboBox(days);
		inventoryYear = new JTextField(year+"");
        inventoryDateLabel = new JLabel();
        inventoryReceiptLabel = new JLabel();
        inventoryItemLabel = new JLabel();
        inventoryQuantityLabel = new JLabel();
        inventoryAmountLabel = new JLabel();          
        inventoryDateText = new JTextField();
        inventoryReceiptText = new JTextField();
        inventoryItemBox = new JComboBox();
        inventoryQuantityText = new JTextField(); 
        inventoryAmountText = new JTextField();      
        
        //Finisher labels and corresponding text fields
		finisherMonths = new JComboBox(months);
		finisherDays = new JComboBox(days);
		finisherYear = new JTextField(year+"");
        finisherDateLabel = new JLabel();
        finisherBuyerLabel = new JLabel();
        finisherHeadsLabel = new JLabel();
        finisherWeightLabel = new JLabel();
        finisherKilosLabel = new JLabel();
        finisherPriceLabel = new JLabel();
        finisherAmountLabel = new JLabel();
        finisherEarsLabel = new JLabel();
        finisherDateText = new JTextField();
        finisherBuyerBox = new JComboBox();
        finisherHeadsText = new JTextField();
        finisherWeightText = new JTextField();
        finisherKilosText = new JTextField();    
        finisherPriceText = new JTextField();   
        finisherAmountText = new JTextField();  
        finisherEarsText = new JTextArea();
        finisherEarsScroll = new JScrollPane();
        
        //Butchered labels and corresponding text fields
		butcheredButcherMonths = new JComboBox(months);
		butcheredButcherDays = new JComboBox(days);
		butcheredButcherYear = new JTextField(year + "");
		butcheredSoldMonths = new JComboBox(months);
		butcheredSoldDays = new JComboBox(days);
		butcheredSoldYear = new JTextField(year + "");
        butcheredButcherLabel = new JLabel();
        butcheredEarLabel = new JLabel();
        butcheredWeight1Label = new JLabel();
        butcheredBuyerLabel = new JLabel();
        butcheredSoldLabel = new JLabel();
        butcheredPriceLabel = new JLabel();
        butcheredWeight2Label = new JLabel();
        butcheredAmountLabel = new JLabel();
        butcheredKg1Label = new JLabel();
        butcheredPhp1Label = new JLabel();
        butcheredKg2Label = new JLabel();
        butcheredPhp2Label = new JLabel();
        butcheredButcherText = new JTextField();
        butcheredEarText = new JTextField();
        butcheredWeight1Text = new JTextField();
        butcheredBuyerBox = new JComboBox();
        butcheredSoldText = new JTextField();
        butcheredPriceText = new JTextField();
        butcheredWeight2Text = new JTextField();
        butcheredAmountText = new JTextField();   
        
        //Salaries labels and corresponding text fields
		salariesStartMonths = new JComboBox(months);
		salariesStartDays = new JComboBox(days);
		salariesStartYear = new JTextField(year+"");
		salariesEndMonths = new JComboBox(months);
		salariesEndDays = new JComboBox(days);
		salariesEndYear = new JTextField(year+"");
        salariesDate1Label = new JLabel();
        salariesDate2Label = new JLabel();
        salariesEmployeeLabel = new JLabel();
        salariesAmountLabel = new JLabel();
        salariesDate1Text = new JTextField();
        salariesDate2Text = new JTextField();
        salariesEmployeeBox = new JComboBox();   
        salariesAmountText = new JTextField();       
        
        //Micellaneous labels and corresponding text fields
		micellaneousMonths = new JComboBox(months);
		micellaneousDays = new JComboBox(days);
		micellaneousYear = new JTextField(year+"");
        micellaneousDateLabel = new JLabel();
        micellaneousItemLabel = new JLabel();
        micellaneousAmountLabel = new JLabel();
        micellaneousNotesLabel = new JLabel();
        micellaneousDateText = new JTextField();
        micellaneousItemBox = new JComboBox();
        micellaneousAmountText = new JTextField();
        micellaneousPhpLabel = new JLabel();
        micellaneousNotesScroll = new JScrollPane();
        micellaneousNotesText = new JTextArea();
        
        //Summary
		summaryStartMonths = new JComboBox(months);
		summaryStartYear = new JTextField(year+"");
		summaryEndMonths = new JComboBox(months);
		summaryOption = new JComboBox(new String[]{"Choose one","Total Expenses(Bar)","Total Expenses(Pie)","Salaries Expense","Inventory Expenses","Misc. Expense","Total Sales","Finisher Sales","Butchered Sales","Net Income"});
		summaryEndYear = new JTextField(year+"");
		
		//History
		historyMonth = new JComboBox(months);
		historyYear = new JTextField(year+"");
		historyOption = new JComboBox(new String[]{"Choose one","Breeding","Farrowing","Sold Butchered","Sold Alive","Salaries","Inventory Expenses","Misc. Expenses","pigs","employees","buyers","items"});

        //Add employee, buyer, or item labels and corresponding text fields
		addMonthsLabel = new JLabel("Month");
		addDaysLabel = new JLabel("Day");
		addYearLabel = new JLabel("Year");
		dummyAddSubmitButton = new JButton();
		dummyAddClearButton = new JButton();
		addAddedMonths = new JComboBox(months);
		addAddedDays = new JComboBox(days);
		addAddedYear = new JTextField(year+"");
        addWhatLabel = new JLabel();
        addNameLabel = new JLabel();
        String[] types = {"Select one...","employee", "buyer", "item", "receipt no.","pig"};
		String[] pigTypes = {"Boar","Sow"};
		addPigBox = new JComboBox(pigTypes);
        addWhatBox = new JComboBox(types);
        addInputText = new JTextField();
		addAdditionalText = new JTextField();
		addAdditionalLabel = new JLabel("First Name");
		
		addWhatBox.addActionListener(this);
        
        //Modifications on instantations
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        titleLabel.setBackground(new Color(51, 153, 255));
        titleLabel.setFont(new Font("Verdana", 1, 24)); // NOI18N
        titleLabel.setText("                                             ");
        navLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        navLabel.setText("");
		correctNavLabel.setBounds(94,0,100,25);

        //Adds ActionListeners to left side buttons
        nav1.setText("Home");
		nav1.setBackground(new Color(255,255,255));
        nav1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav1ActionPerformed(evt); 		
        	}
        });
        nav2.setText("Add Breeding Items");
		nav2.setBackground(new Color(200,75,75));
        nav2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav2ActionPerformed(evt); 		
        	}
        });
        nav3.setText("Add Farrowing Items");
		nav3.setBackground(new Color(200,75,75));
        nav3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav3ActionPerformed(evt); 		
        	}
        });
        nav4.setText("Add Mortality Items");
		nav4.setBackground(new Color(200,75,75));
        nav4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav4ActionPerformed(evt);
            }
        });
        nav5.setText("Add Inventory Items");
		nav5.setBackground(new Color(75,200,75));
        nav5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav5ActionPerformed(evt);
            }
        });
        nav6.setText("Add Finisher Sales Items");
		nav6.setBackground(new Color(200,200,75));
        nav6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav6ActionPerformed(evt);
            }
        });
        nav7.setText("Add Butchered Sales Items");
		nav7.setBackground(new Color(200,200,75));
        nav7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav7ActionPerformed(evt); 		
        	}
        });
        nav8.setText("Add Salaries Expenses Items");
		nav8.setBackground(new Color(75,200,75));
        nav8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav8ActionPerformed(evt);
            }
        });
        nav9.setText("Add Miscellaneous Expense Items");
		nav9.setBackground(new Color(75,200,75));
        nav9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav9ActionPerformed(evt);
            }
        });
        nav10.setText("Show Summary");
		nav10.setBackground(new Color(75,75,200));
        nav10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav10ActionPerformed(evt); 		
        	}
        });
        nav11.setText("Add Entries");
		nav11.setBackground(new Color(255,255,255));
        nav11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav11ActionPerformed(evt);
        	}
        });
		
		nav12.setText("History");
		nav12.setBackground(new Color(75,75,200));
        nav12.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav12ActionPerformed(evt);
        	}
        });
        
        //Auto-complete
        breedingSowText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(index);
        			breedingSowText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else if((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_DOWN))
				{
					if( index+1 < popupEars.getModel().getSize())
					{
						index++;
						popupEars.setSelectedIndex(index);
					}
					else
					{
						popupEars.setSelectedIndex(popupEars.getModel().getSize());
					}
				} else if((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_UP))
				{
					if( index-1 >= 0)
					{
						index--;
						popupEars.setSelectedIndex(index);
					}
					else
					{
						popupEars.setSelectedIndex(popupEars.getModel().getSize());
					}
				}
				else
				{
        			breedingSowPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        breedingSowText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		breedingSowPopupActionPerformed();
        	}
        });
        breedingBoarText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(0);
        			breedingBoarText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else {
        			breedingBoarPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        breedingBoarText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		breedingBoarPopupActionPerformed();
        	}
        });
        farrowingSowText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(0);
        			farrowingSowText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else {
        			farrowingSowPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        farrowingSowText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		farrowingSowPopupActionPerformed();
        	}
        });
        farrowingBoarText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(0);
        			farrowingBoarText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else {
        			farrowingBoarPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        farrowingBoarText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		farrowingBoarPopupActionPerformed();
        	}
        });
        mortalityEarText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(0);
        			mortalityEarText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else {
        			mortalityEarPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        mortalityEarText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		mortalityEarPopupActionPerformed();
        	}
        });
        butcheredEarText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(0);
        			butcheredEarText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else {
        			butcheredEarPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        butcheredEarText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		butcheredEarPopupActionPerformed();
        	}
        });
        
        inventoryReceiptText.addKeyListener(new KeyListener() {
        	public void keyPressed(KeyEvent evt) {
        		
        	}
        	public void keyReleased(KeyEvent evt) {
        		if ((showEars != null) && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
        			popupEars.setSelectedIndex(0);
        			inventoryReceiptText.setText(popupEars.getSelectedValue().toString());
        			showEars.hide();
        		} else {
        			inventoryReceiptTextPopupActionPerformed();
        		}
        	}
        	public void keyTyped(KeyEvent evt) {
        		
        	}
        });
        inventoryReceiptText.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent evt) {
        		inventoryReceiptTextPopupActionPerformed();
        	}
        });
        
        historyOption.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		historyPanel.removeAll();
        		historyComponents();
        		validate();
        		repaint();
        	}
        });
        historyMonth.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		historyPanel.removeAll();
        		historyComponents();
        		historyOption.setSelectedIndex(0);
        		validate();
        		repaint();
        	}
        });
        this.historyYear.addKeyListener(new KeyAdapter() {
        	public void keyReleased(KeyEvent evt) {
        		boolean through = false;
        		try {
        			Integer.parseInt(historyYear.getText());
        			through = true;
        			if (historyYear.getText().length() > 4) {
        				historyYear.setText(historyYear.getText().substring(0, 3));
        			}
        		} catch (NumberFormatException e) {
        			
        		} finally {
        			if ( !through && historyYear.getText().length() > 0) {
        				historyYear.setText(historyYear.getText().substring(0, historyYear.getText().length() - 1));
        			}
        		}
        	}
        });
        
        //Adds ActionListeners to Submit and Clear Buttons of all Panels
        breedingSubmitButton = new JButton();
        breedingSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt){
        		breedingSubmitActionPerformed(evt);
        	}
        });
        breedingClearButton = new JButton();
        breedingClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		breedingClearActionPerformed(evt);
        	}
        });
        farrowingSubmitButton = new JButton();
        farrowingSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		farrowingSubmitActionPerformed(evt);
        	}
        });
        farrowingClearButton = new JButton();
        farrowingClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		farrowingClearActionPerformed(evt);
        	}
        });
        mortalitySubmitButton = new JButton();
        mortalitySubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		mortalitySubmitActionPerformed(evt);
        	}
        });
        mortalityClearButton = new JButton();
        mortalityClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		mortalityClearActionPerformed(evt);
        	}
        });
        inventorySubmitButton = new JButton();
        inventorySubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		inventorySubmitActionPerformed(evt);
        	}
        });
        inventoryClearButton = new JButton();
        inventoryClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		inventoryClearActionPerformed(evt);
        	}
        });
        finisherSubmitButton = new JButton();
        finisherSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		finisherSubmitActionPerformed(evt);
        	}
        });
        finisherClearButton = new JButton();
        finisherClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		finisherClearActionPerformed(evt);
        	}
        });
        butcheredSubmitButton = new JButton();
        butcheredSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		butcheredSubmitActionPerformed(evt);
        	}
        });
        butcheredClearButton = new JButton();
        butcheredClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		butcheredClearActionPerformed(evt);
        	}
        });
        salariesSubmitButton = new JButton();
        salariesSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		salariesSubmitActionPerformed(evt);
        	}
        });
        salariesClearButton = new JButton(); 
        salariesClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		salariesClearActionPerformed(evt);
        	}
        });
        micellaneousSubmitButton = new JButton();
        micellaneousSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		micellaneousSubmitActionPerformed(evt);
        	}
        });
        micellaneousClearButton = new JButton();
        micellaneousClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		micellaneousClearActionPerformed(evt);
        	}
        });
        addSubmitButton = new JButton();
        addSubmitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		addSubmitActionPerformed(evt);
        	}
        });
        addClearButton = new JButton();
        addClearButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		addClearActionPerformed(evt);
        	}
        });
        
        summaryOption.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		summaryActionPerformed(evt);
        	}
        });
        
        inventoryComponents();
        
        //Layout for left side buttons
        GroupLayout navButtonsPanelLayout = new GroupLayout(navButtonsPanel);
        navButtonsPanel.setLayout(navButtonsPanelLayout);
        navButtonsPanelLayout.setHorizontalGroup(
            navButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(navButtonsPanelLayout.createSequentialGroup()
                .addGroup(navButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(navLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav1, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav2, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav3, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav4, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav5, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav6, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav7, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav8, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav9, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav10, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                    .addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav11, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addGroup(navButtonsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nav12, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))))
        );
        navButtonsPanelLayout.setVerticalGroup(
            navButtonsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(navButtonsPanelLayout.createSequentialGroup()
                .addComponent(navLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav1)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav11)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav3)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav7)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav5)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav8)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav9)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav10)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nav12)
                .addContainerGap(42, Short.MAX_VALUE))
        );
       navButtonsPanel.add(correctNavLabel);
    }// </editor-fold>//GEN-END:initComponents
    
	private void homeComponents()
	{
		homePanel.setLayout(null);
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(homePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(homePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		ImageIcon ii = new ImageIcon("pig.png");
		JLabel homeLabel = new JLabel(ii);
		homeLabel.addMouseListener(new MouseAdapter()
		{
				public void mouseClicked(MouseEvent evt) {
					try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(Home.class.getResourceAsStream("oink.wav"));
					clip.open(inputStream);
					clip.start(); 
					} catch (Exception e) {
					System.err.println(e.getMessage());
					}
            	}
		});
		JLabel txt = new JLabel("Piggery Information Generation System");
		txt.setFont(new Font("Verdana", 1, 20));
		homeLabel.setBounds(85,20,442,467);
		txt.setBounds(70,20,500,25);
	
		homePanel.add(txt);
		homePanel.add(homeLabel);
		
	}
	
    /**
     * Initializes the components of the breedingPanel
     */
    private void breedingComponents() {
		index = 0;
		popupEars.setSelectedIndex(0);
        breedingDateLabel.setText("Breeding Date");
        breedingSowLabel.setText("Sow#");
        breedingParityLabel.setText("Parity(optional)");
        breedingBoarLabel.setText("Boar#");
        breedingSubmitButton.setText("Submit");
        breedingClearButton.setText("Clear Fields");
        breedingNotesText.setColumns(20);
        breedingNotesText.setRows(5);
        breedingNotesScroll.setViewportView(breedingNotesText);
        breedingNotesLabel.setText("Notes");

        GroupLayout breedingPanelLayout = new GroupLayout(breedingPanel);
        breedingPanel.setLayout(breedingPanelLayout);
        breedingPanelLayout.setHorizontalGroup(
            breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(breedingPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(breedingPanelLayout.createSequentialGroup()
                        .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(breedingFarrowingLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(breedingParityLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(breedingSowLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(breedingDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(breedingPanelLayout.createSequentialGroup()
                        .addComponent(breedingBoarLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(breedingPanelLayout.createSequentialGroup()
                        .addComponent(breedingNotesLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breedingNotesScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(breedingPanelLayout.createSequentialGroup()
                        .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(breedingFarrowingText, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(breedingBoarText)
                            .addComponent(breedingParityText)
                            .addComponent(breedingSowText)
                            .addComponent(breedingBreedingDateText)
                            .addComponent(breedingSubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(breedingClearButton)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        breedingPanelLayout.setVerticalGroup(
            breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(breedingPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breedingDateLabel)
                    .addComponent(breedingBreedingDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breedingSowLabel)
                    .addComponent(breedingSowText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breedingParityLabel)
                    .addComponent(breedingParityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breedingBoarText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(breedingBoarLabel))
                .addGap(18, 18, 18)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breedingFarrowingLabel)
                    .addComponent(breedingFarrowingText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(breedingNotesLabel)
                    .addComponent(breedingNotesScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(breedingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(breedingClearButton)
                    .addComponent(breedingSubmitButton))
                .addContainerGap(17, Short.MAX_VALUE))
        ); 
    	
    	
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(breedingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(breedingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		
        
		breedingBreedingDateText.setVisible(false);
		breedingFarrowingText.setVisible(false);
		
		breedingBreedingMonths.setBounds(253,21,100,25);
		breedingBreedingDays.setBounds(361,21,50,25);
		breedingBreedingYear.setBounds(419,21,50,25);
		
		breedingPanel.add(breedingBreedingMonths);
		breedingPanel.add(breedingBreedingDays);
		breedingPanel.add(breedingBreedingYear);
		
    }
    
    /**
     * Initializes the components of the farrowingPanel
     */
    private void farrowingComponents() {
    	farrowingDateLabel.setText("Date Farrowed");
        farrowingSowLabel.setText("Sow#");
        farrowingBoarLabel.setText("Boar#");
        farrowingLiveLabel.setText("Live born#");
        farrowingEarLabel.setText("Ear# Assigned Start");
        farrowingStillLabel.setText("Stillborn#");
        farrowingMummifiedLabel.setText("Mummified#");
        farrowingAbnormalLabel.setText("Abnormal#");
        farrowingSubmitButton.setText("Submit");
        farrowingClearButton.setText("Clear Fields");
        farrowingNotesText.setColumns(20);
        farrowingNotesText.setRows(5);
        farrowingNotesScroll.setViewportView(farrowingNotesText);
        farrowingNotesLabel.setText("Notes");

        GroupLayout farrowingPanelLayout = new GroupLayout(farrowingPanel);
        farrowingPanel.setLayout(farrowingPanelLayout);
        farrowingPanelLayout.setHorizontalGroup(
            farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(farrowingPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(farrowingPanelLayout.createSequentialGroup()
                        .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(farrowingMummifiedLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(farrowingStillLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(farrowingLiveLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(farrowingBoarLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(farrowingSowLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(farrowingDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(farrowingNotesLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(farrowingPanelLayout.createSequentialGroup()
                        .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(farrowingEarLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(farrowingPanelLayout.createSequentialGroup()
                                .addComponent(farrowingAbnormalLabel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(farrowingPanelLayout.createSequentialGroup()
                        .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(farrowingAbnormalText)
                            .addComponent(farrowingMummifiedText)
                            .addComponent(farrowingStillText)
                            .addComponent(farrowingEarText)
                            .addComponent(farrowingLiveText)
                            .addComponent(farrowingBoarText)
                            .addComponent(farrowingSowText)
                            .addComponent(farrowingDateText)
                            .addComponent(farrowingSubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(farrowingClearButton))
                    .addComponent(farrowingNotesScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        farrowingPanelLayout.setVerticalGroup(
            farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(farrowingPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(farrowingDateLabel)
                    .addComponent(farrowingDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingSowText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(farrowingSowLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingBoarText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(farrowingBoarLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingLiveText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(farrowingLiveLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingEarText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(farrowingEarLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingStillText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(farrowingStillLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingMummifiedLabel)
                    .addComponent(farrowingMummifiedText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(farrowingAbnormalText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(farrowingAbnormalLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(farrowingPanelLayout.createSequentialGroup()
                        .addComponent(farrowingNotesScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(farrowingPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(farrowingClearButton)
                            .addComponent(farrowingSubmitButton)))
                    .addGroup(farrowingPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(farrowingNotesLabel)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(farrowingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(farrowingPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		
		farrowingDateText.setVisible(false);
		
		farrowingMonths.setBounds(211,16,100,25);
		farrowingDays.setBounds(314,16,50,25);
		farrowingYear.setBounds(367,16,75,25);
		
		farrowingPanel.add(farrowingMonths);
		farrowingPanel.add(farrowingDays);
		farrowingPanel.add(farrowingYear);
    }
    
    /**
     * Initializes the components of the mortalityPanel
     */
    private void mortalityComponents() {
    	mortalityDateLabel.setText("Date");
        mortalityEarLabel.setText("Ear#");
        mortalityBuildingLabel.setText("Building#");
        mortalityEmployeeLabel.setText("Employee-in-charge");
        mortalityCauseLabel.setText("Cause of Death");
        mortalitySubmitButton.setText("Submit");
        mortalityClearButton.setText("Clear Fields");

        try {
        	Vector<String> employees = new Vector<String>();
        	sqlrs = sqlstat.executeQuery("SELECT * FROM employees;");
        	while (sqlrs.next()) {
        		employees.add(sqlrs.getString("last_name") + ", " + sqlrs.getString("first_name"));
        	}    	
        	sqlrs.close();
        	mortalityEmployeeBox = new JComboBox(employees);
        	
        } catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	} catch(Exception e){
    		//e.printStackTrace();
    	}
        
        GroupLayout mortalityPanelLayout = new GroupLayout(mortalityPanel);
        mortalityPanel.setLayout(mortalityPanelLayout);
        mortalityPanelLayout.setHorizontalGroup(
            mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, mortalityPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mortalityPanelLayout.createSequentialGroup()
                        .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(mortalityEmployeeLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mortalityBuildingLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mortalityEarLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mortalityDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(mortalityPanelLayout.createSequentialGroup()
                        .addComponent(mortalityCauseLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(mortalityEmployeeBox, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addComponent(mortalityCauseText)
                        .addComponent(mortalityBuildingText)
                        .addComponent(mortalityEarText)
                        .addComponent(mortalityDateText))
                    .addGroup(mortalityPanelLayout.createSequentialGroup()
                        .addComponent(mortalitySubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mortalityClearButton)))
                .addGap(53, 53, 53))
        );
        mortalityPanelLayout.setVerticalGroup(
            mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mortalityPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityDateLabel)
                    .addComponent(mortalityDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityEarLabel)
                    .addComponent(mortalityEarText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityBuildingLabel)
                    .addComponent(mortalityBuildingText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityCauseText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mortalityCauseLabel))
                .addGap(18, 18, 18)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityEmployeeLabel)
                    .addComponent(mortalityEmployeeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityClearButton)
                    .addComponent(mortalitySubmitButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mortalityPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(mortalityPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		
		mortalityDateText.setVisible(false);
		
		mortalityMonths.setBounds(227,21,100,25);
		mortalityDays.setBounds(335,21,50,25);
		mortalityYear.setBounds(393,21,68,25);
		
		mortalityPanel.add(mortalityMonths);
		mortalityPanel.add(mortalityDays);
		mortalityPanel.add(mortalityYear);
    }
    
    /**
     * Initializes the components of the inventoryPanel
     */
    private void inventoryComponents() {
	    inventoryDateLabel.setText("Date");
	    inventoryReceiptLabel.setText("Receipt#");
	    inventoryItemLabel.setText("Item Type");
	    inventoryQuantityLabel.setText("Quantity");
	    inventoryAmountLabel.setText("Amount per Piece");
	    inventorySubmitButton.setText("Submit");
	    inventoryClearButton.setText("Clear Fields");
	    
	    try {
	    	Vector<String> items = new Vector<String>();
	    	sqlrs = sqlstat.executeQuery("SELECT * FROM items;");
	    	while (sqlrs.next()) {
	    		items.add(sqlrs.getString("item"));
	    	}    	
	    	sqlrs.close();
	    	inventoryItemBox = new JComboBox(items);
	    	
	    } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch(Exception e){
			//e.printStackTrace();
		}
	
	    GroupLayout inventoryPanelLayout = new GroupLayout(inventoryPanel);
	    inventoryPanel.setLayout(inventoryPanelLayout);
	    inventoryPanelLayout.setHorizontalGroup(
	        inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(GroupLayout.Alignment.TRAILING, inventoryPanelLayout.createSequentialGroup()
	            .addGap(62, 62, 62)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addGroup(inventoryPanelLayout.createSequentialGroup()
	                    .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
	                        .addComponent(inventoryAmountLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(inventoryItemLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(inventoryReceiptLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(inventoryDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addGap(67, 67, 67))
	                .addGroup(inventoryPanelLayout.createSequentialGroup()
	                    .addComponent(inventoryQuantityLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addGroup(inventoryPanelLayout.createSequentialGroup()
	                    .addComponent(inventorySubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(inventoryClearButton))
	                .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
	                    .addComponent(inventoryQuantityText, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(inventoryAmountText, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
	                    .addComponent(inventoryItemBox)
	                    .addComponent(inventoryReceiptText)
	                    .addComponent(inventoryDateText)))
	            .addGap(53, 53, 53))
	    );
	    inventoryPanelLayout.setVerticalGroup(
	        inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(inventoryPanelLayout.createSequentialGroup()
	            .addGap(21, 21, 21)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(inventoryDateLabel)
	                .addComponent(inventoryDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            .addGap(18, 18, 18)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(inventoryReceiptLabel)
	                .addComponent(inventoryReceiptText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            .addGap(18, 18, 18)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(inventoryItemLabel)
	                .addComponent(inventoryItemBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            .addGap(18, 18, 18)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(inventoryQuantityLabel)
	                .addComponent(inventoryQuantityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            .addGap(18, 18, 18)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(inventoryAmountLabel)
	                .addComponent(inventoryAmountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	            .addGap(55, 55, 55)
	            .addGroup(inventoryPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(inventoryClearButton)
	                .addComponent(inventorySubmitButton))
	            .addContainerGap(89, Short.MAX_VALUE))
	    );
	
	    
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
	        .addGroup(layout.createSequentialGroup()
	            .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            .addContainerGap())
	    );
		
		JLabel php = new JLabel("php");
		
		php.setBounds(410,175,50,25);
		
		inventoryPanel.add(php);
		inventoryDateText.setVisible(false);
		
		inventoryMonths.setBounds(227,21,100,25);
		inventoryDays.setBounds(335,21,50,25);
		inventoryYear.setBounds(393,21,68,25);
		
		inventoryPanel.add(inventoryMonths);
		inventoryPanel.add(inventoryDays);
		inventoryPanel.add(inventoryYear);
	}
    
    /**
     * Initializes the components of the finisherPanel
     */
    private void finisherComponents() {
    	finisherDateLabel.setText("Date");
        finisherBuyerLabel.setText("Buyer");
        finisherHeadsLabel.setText("# of heads");
        finisherWeightLabel.setText("Total Weight");
        finisherKilosLabel.setText("Kilos less");
        finisherPriceLabel.setText("Price per Kg");
        finisherAmountLabel.setText("Total Amount");
        finisherEarsLabel.setText("Ear nos");
        finisherEarsText.setColumns(8);
        finisherEarsText.setRows(5);
        finisherEarsScroll.setViewportView(finisherEarsText);
        finisherSubmitButton.setText("Submit");
        finisherClearButton.setText("Clear Fields");
        
        try {
        	Vector<String> buyers = new Vector<String>();
        	sqlrs = sqlstat.executeQuery("SELECT * FROM buyers;");
        	while (sqlrs.next()) {
        		buyers.add(sqlrs.getString("last_name") + ", " + sqlrs.getString("first_name"));
        	}   
        	sqlrs.close();
        	finisherBuyerBox = new JComboBox(buyers);
        	
        } catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	} catch(Exception e){
    		//e.printStackTrace();
    	}

        GroupLayout finisherPanelLayout = new GroupLayout(finisherPanel);
        finisherPanel.setLayout(finisherPanelLayout);
        finisherPanelLayout.setHorizontalGroup(
            finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(finisherPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(finisherWeightLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(finisherPanelLayout.createSequentialGroup()
                        .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(finisherKilosLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(finisherHeadsLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(finisherBuyerLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(finisherDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(finisherPriceLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(finisherEarsLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(finisherAmountLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                	.addComponent(finisherEarsScroll)
                    .addComponent(finisherAmountText)
                    .addComponent(finisherPriceText)
                    .addComponent(finisherKilosText, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(finisherWeightText, GroupLayout.Alignment.LEADING)
                    .addComponent(finisherHeadsText, GroupLayout.Alignment.LEADING)
                    .addComponent(finisherDateText, GroupLayout.Alignment.LEADING)
                    .addComponent(finisherSubmitButton, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finisherClearButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        finisherPanelLayout.setVerticalGroup(
            finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(finisherPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(finisherDateLabel)
                    .addComponent(finisherDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(finisherBuyerLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGap(10,10,10)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(finisherHeadsText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(finisherHeadsLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(finisherWeightText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(finisherWeightLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(finisherKilosText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(finisherKilosLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(finisherPriceLabel)
                    .addComponent(finisherPriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(finisherAmountLabel)
                    .addComponent(finisherAmountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(finisherEarsLabel)
                    .addComponent(finisherEarsScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(finisherPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(finisherClearButton)
                    .addComponent(finisherSubmitButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finisherPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(finisherPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		
		
		JLabel kg1 = new JLabel("kg");
		JLabel kg2 = new JLabel("kg");
		JLabel php = new JLabel("php");
		finisherDateText.setVisible(false);
		
		kg1.setBounds(350,104,50,25);
		kg2.setBounds(350,145,50,25);
		php.setBounds(350,185,50,25);
		finisherBuyerBox.setBounds(240,43,175,25);
		finisherPanel.add(finisherBuyerBox);
		finisherPanel.add(kg1);
		finisherPanel.add(kg2);
		finisherPanel.add(php);
		
		finisherMonths.setBounds(240,16,100,25);
		finisherDays.setBounds(350,16,50,25);
		finisherYear.setBounds(410,16,50,25);		

		
		finisherPanel.add(finisherMonths);
		finisherPanel.add(finisherDays);
		finisherPanel.add(finisherYear);
    }
    
    /**
     * Initializes the components of the butcheredPanel
     */
    private void butcheredComponents() {
    	butcheredButcherLabel.setText("Butcher Date");
        butcheredEarLabel.setText("Ear#");
        butcheredWeight1Label.setText("Weight Sold");
        butcheredBuyerLabel.setText("Buyer");
        butcheredSoldLabel.setText("Sold Date");
        butcheredPriceLabel.setText("Price per kg");
        butcheredWeight2Label.setText("Total Weight");
        butcheredAmountLabel.setText("Total Amount");
        butcheredSubmitButton.setText("Submit");
        butcheredClearButton.setText("Clear Fields");
        butcheredKg1Label.setText("kg");
        butcheredPhp1Label.setText("kg");
        butcheredKg2Label.setText("Php");
        butcheredPhp2Label.setText("Php");
        
        try {
        	Vector<String> buyers = new Vector<String>();
        	sqlrs = sqlstat.executeQuery("SELECT * FROM buyers;");
        	while (sqlrs.next()) {
        		buyers.add(sqlrs.getString("last_name") + ", " + sqlrs.getString("first_name"));
        	}    	
        	sqlrs.close();
        	butcheredBuyerBox = new JComboBox(buyers);
        	
        } catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	} catch(Exception e){
    		//e.printStackTrace();
    	}

        GroupLayout butcheredPanelLayout = new GroupLayout(butcheredPanel);
        butcheredPanel.setLayout(butcheredPanelLayout);
        butcheredPanelLayout.setHorizontalGroup(
            butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(butcheredPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(butcheredPanelLayout.createSequentialGroup()
                        .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(butcheredAmountLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butcheredWeight2Label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butcheredPriceLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butcheredSoldLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butcheredWeight1Label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butcheredEarLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butcheredButcherLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(butcheredPanelLayout.createSequentialGroup()
                        .addComponent(butcheredBuyerLabel, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(butcheredAmountText)
                    .addComponent(butcheredWeight2Text)
                    .addComponent(butcheredPriceText)
                    .addComponent(butcheredSoldText)
                    .addComponent(butcheredBuyerBox)
                    .addComponent(butcheredWeight1Text)
                    .addComponent(butcheredEarText)
                    .addComponent(butcheredButcherText)
                    .addGroup(butcheredPanelLayout.createSequentialGroup()
                        .addComponent(butcheredSubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(butcheredClearButton)
                    .addComponent(butcheredPhp1Label)
                    .addComponent(butcheredKg2Label)
                    .addComponent(butcheredKg1Label)
                    .addComponent(butcheredPhp2Label))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        butcheredPanelLayout.setVerticalGroup(
            butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(butcheredPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredButcherLabel)
                    .addComponent(butcheredButcherText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredEarLabel)
                    .addComponent(butcheredEarText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredWeight1Label)
                    .addComponent(butcheredWeight1Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(butcheredKg1Label))
                .addGap(18, 18, 18)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredBuyerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(butcheredBuyerLabel))
                .addGap(18, 18, 18)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredSoldLabel)
                    .addComponent(butcheredSoldText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredPriceLabel)
                    .addComponent(butcheredPriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(butcheredKg2Label))
                .addGap(20, 20, 20)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredWeight2Label)
                    .addComponent(butcheredWeight2Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(butcheredPhp1Label))
                .addGap(18, 18, 18)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredAmountLabel)
                    .addComponent(butcheredAmountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(butcheredPhp2Label))
                .addGap(29, 29, 29)
                .addGroup(butcheredPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(butcheredClearButton)
                    .addComponent(butcheredSubmitButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butcheredPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(butcheredPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		butcheredButcherText.setVisible(false);
		
		butcheredButcherMonths.setBounds(231,21,100,25);
		butcheredButcherDays.setBounds(339,21,50,25);
		butcheredButcherYear.setBounds(397,21,50,25);
		
		butcheredPanel.add(butcheredButcherMonths);
		butcheredPanel.add(butcheredButcherDays);
		butcheredPanel.add(butcheredButcherYear);

		butcheredSoldText.setVisible(false);
		
		butcheredSoldMonths.setBounds(231,191,100,25);
		butcheredSoldDays.setBounds(339,191,50,25);
		butcheredSoldYear.setBounds(397,191,50,25);
		
		butcheredPanel.add(butcheredSoldMonths);
		butcheredPanel.add(butcheredSoldDays);
		butcheredPanel.add(butcheredSoldYear);
    }
    
    /**
     * Initializes the components of the salariesPanel
     */
    private void salariesComponents() {
    	 salariesDate1Label.setText("Start Date");
    	 salariesDate2Label.setText("End Date");
         salariesEmployeeLabel.setText("Employee");
         salariesSubmitButton.setText("Submit");
         salariesClearButton.setText("Clear Fields");
         salariesAmountLabel.setText("Amount");
         
         try {
         	Vector<String> employees = new Vector<String>();
         	sqlrs = sqlstat.executeQuery("SELECT * FROM employees;");
         	while (sqlrs.next()) {
         		employees.add(sqlrs.getString("last_name") + ", " + sqlrs.getString("first_name"));
         	}
         	sqlrs.close();
			if(employees.size() == 0)
			{
				String[] temp = {"Empty.."};
				salariesEmployeeBox = new JComboBox(temp);
			}
			else
			{
				salariesEmployeeBox = new JComboBox(employees);
			}
         } catch (SQLException e) {
     		JOptionPane.showMessageDialog(null, e.getMessage());
     	} catch(Exception e){
     		//e.printStackTrace();
     	}

         GroupLayout salariesPanelLayout = new GroupLayout(salariesPanel);
         salariesPanel.setLayout(salariesPanelLayout);    
         salariesPanelLayout.setHorizontalGroup(
             salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(salariesPanelLayout.createSequentialGroup()
                 .addGap(62, 62, 62)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                     .addComponent(salariesAmountLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(salariesEmployeeLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                     .addComponent(salariesDate2Label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                     .addComponent(salariesDate1Label, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                 .addGap(67, 67, 67)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addGroup(salariesPanelLayout.createSequentialGroup()
                         .addComponent(salariesSubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                         .addGap(6, 6, 6)
                         .addComponent(salariesClearButton))
                     .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                         .addComponent(salariesAmountText, GroupLayout.Alignment.LEADING)
                         .addComponent(salariesEmployeeBox, GroupLayout.Alignment.LEADING)
                         .addComponent(salariesDate2Text, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                         .addComponent(salariesDate1Text, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                 .addContainerGap(110, Short.MAX_VALUE))
         );    
         salariesPanelLayout.setVerticalGroup(
             salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(salariesPanelLayout.createSequentialGroup()
                 .addGap(21, 21, 21)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(salariesDate1Label)
                     .addComponent(salariesDate1Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addGap(18, 18, 18)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                	 .addComponent(salariesDate2Label)
                     .addComponent(salariesDate2Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addGap(18, 18, 18)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(salariesEmployeeLabel)
                     .addComponent(salariesEmployeeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addGap(18, 18, 18)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(salariesAmountLabel)
                     .addComponent(salariesAmountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                 .addGap(203, 203, 203)
                 .addGroup(salariesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                     .addComponent(salariesClearButton)
                     .addComponent(salariesSubmitButton))
                 .addContainerGap(34, Short.MAX_VALUE))
         );

         GroupLayout layout = new GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
             .addGroup(layout.createSequentialGroup()
                 .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(salariesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                 .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                     .addGroup(layout.createSequentialGroup()
                         .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addContainerGap())
                     .addComponent(salariesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
         );
		 
		JLabel php = new JLabel("php");
		php.setBounds(340,133,50,25);
		salariesPanel.add(php);
		salariesDate1Text.setVisible(false);
		
		salariesStartMonths.setBounds(193,21,100,25);
		salariesStartDays.setBounds(301,21,50,25);
		salariesStartYear.setBounds(359,21,50,25);
		
		salariesPanel.add(salariesStartMonths);
		salariesPanel.add(salariesStartDays);
		salariesPanel.add(salariesStartYear);

		salariesDate2Text.setVisible(false);
		
		salariesEndMonths.setBounds(193,55 ,100,25);
		salariesEndDays.setBounds(301,55,50,25);
		salariesEndYear.setBounds(359,55,50,25);
		
		salariesPanel.add(salariesEndMonths);
		salariesPanel.add(salariesEndDays);
		salariesPanel.add(salariesEndYear);

    }
    
    /**
     * Initializes the components of the micellaneousPanel
     */
    private void micellaneousComponents() {
    	micellaneousDateLabel.setText("Date");
        micellaneousItemLabel.setText("Item");
        micellaneousAmountLabel.setText("Amount");
        micellaneousNotesLabel.setText("Notes");
        micellaneousSubmitButton.setText("Submit");
        micellaneousClearButton.setText("Clear Fields");
        micellaneousPhpLabel.setText("Php");
        micellaneousNotesText.setColumns(20);
        micellaneousNotesText.setRows(5);
		micellaneousNotesScroll.setViewportView(micellaneousNotesText);
		
        
        try {
        	Vector<String> items = new Vector<String>();
        	sqlrs = sqlstat.executeQuery("SELECT * FROM items;");
        	while (sqlrs.next()) {
        		items.add(sqlrs.getString("item"));
        	}
        	sqlrs.close();
        	micellaneousItemBox = new JComboBox(items);
        	
        } catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    	} catch(Exception e){
    		//e.printStackTrace();
    	}
        
        GroupLayout micellaneousPanelLayout = new GroupLayout(micellaneousPanel);
        micellaneousPanel.setLayout(micellaneousPanelLayout);
        micellaneousPanelLayout.setHorizontalGroup(
            micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(micellaneousPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(micellaneousPanelLayout.createSequentialGroup()
                        .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(micellaneousAmountLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(micellaneousItemLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(micellaneousDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(micellaneousPanelLayout.createSequentialGroup()
                        .addComponent(micellaneousNotesLabel, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(micellaneousPanelLayout.createSequentialGroup()
                        .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(micellaneousAmountText)
                            .addComponent(micellaneousItemBox)
                            .addComponent(micellaneousDateText)
                            .addGroup(micellaneousPanelLayout.createSequentialGroup()
                                .addComponent(micellaneousSubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(micellaneousClearButton)
                            .addComponent(micellaneousPhpLabel)))
                    .addComponent(micellaneousNotesScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        micellaneousPanelLayout.setVerticalGroup(
            micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(micellaneousPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(micellaneousDateLabel)
                    .addComponent(micellaneousDateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(micellaneousItemLabel)
                    .addComponent(micellaneousItemBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(micellaneousAmountLabel)
                    .addComponent(micellaneousAmountText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(micellaneousPhpLabel))
                .addGap(18, 18, 18)
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(micellaneousNotesLabel)
                    .addComponent(micellaneousNotesScroll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(micellaneousPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(micellaneousClearButton)
                    .addComponent(micellaneousSubmitButton))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(micellaneousPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(micellaneousPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

		micellaneousDateText.setVisible(false);
		
		micellaneousMonths.setBounds(207,21,100,25);
		micellaneousDays.setBounds(315,21,50,25);
		micellaneousYear.setBounds(373,21,50,25);
		
		
		micellaneousPanel.add(micellaneousMonths);
		micellaneousPanel.add(micellaneousDays);
		micellaneousPanel.add(micellaneousYear);

    }
    
    
    /**
     * Initializes the components of the summaryPanel
     */
    private void summaryComponents() {
		summaryOption.setSelectedIndex(0);
		summaryPanel.setLayout(null);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(summaryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		
		exportButton1 = new JButton("Export");
		exportButton1.setBounds(400,450,75,25);
		exportButton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				int exportSuccess = exportChart(chart);
				if(exportSuccess == 1)
				{
					JOptionPane.showMessageDialog(null, "Export Success.");
				}
				else if(exportSuccess == -1)
				{
					JOptionPane.showMessageDialog(null, "Failed to export Table.");
				}
			}
		}
		);
		
		JLabel startLabel = new JLabel("Start Date");
		summaryStartMonths.setBounds(50,21,100,25);
		summaryStartYear.setBounds(160,21,50,25);
		startLabel.setBounds(120,0,100,21);
		
		summaryPanel.add(summaryStartMonths);
		summaryPanel.add(summaryStartYear);
		
		JLabel endLabel = new JLabel("End Date");
		summaryEndMonths.setBounds(220,21 ,100,25);
		summaryEndYear.setBounds(330,21,50,25);
		summaryOption.setBounds(390,21,150,25);
		
		endLabel.setBounds(290,0,100,21);
		
		summaryPanel.add(exportButton1);
		summaryPanel.add(summaryEndMonths);
		summaryPanel.add(summaryEndYear);
		summaryPanel.add(summaryOption);
		
		summaryPanel.add(startLabel);
		summaryPanel.add(endLabel);

		exportButton1.setVisible(false);
    }
    
    /**
     * Initializes the components of the addPanel
     */
    private void addComponents() {
		addWhatBox.setSelectedIndex(0);
    	addWhatLabel.setText("Add what?");
		addSubmitButton.setText("Submit");
        addClearButton.setText("Clear Fields");
        dummyAddSubmitButton.setText("Submit");
        dummyAddClearButton.setText("Clear Fields");
        addNameLabel.setText("Name");

        GroupLayout addPanelLayout = new GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);    
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addNameLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addWhatLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addComponent(dummyAddSubmitButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(dummyAddClearButton))
                    .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(addInputText, GroupLayout.Alignment.LEADING)
                        .addComponent(addWhatBox, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                .addContainerGap(110, Short.MAX_VALUE))
        );    
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addWhatLabel)
                    .addComponent(addWhatBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addNameLabel)
                    .addComponent(addInputText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(addPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dummyAddClearButton)
                    .addComponent(dummyAddSubmitButton))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(addPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		
		
		addPigBox.setBounds(300,21,60,25);
		
		dummyAddSubmitButton.setVisible(false);
		dummyAddClearButton.setVisible(false);
		
		addYearLabel.setBounds(100,160,80,25);
		addDaysLabel.setBounds(100,130,80,25);
		addMonthsLabel.setBounds(100,100,80,25);
		
		addAddedMonths.setBounds(193,100,100,25);
		addAddedDays.setBounds(193,130,60,25);
		addAddedYear.setBounds(193,160,60,25);
		
		addAdditionalLabel.setBounds(62,100,80,25);
		addAdditionalText.setBounds(193,100,104,28);
		
		addSubmitButton.setBounds(193,300,80,25);
		addClearButton.setBounds(287,300,100,25);
		
		
		addPanel.add(addAdditionalLabel);
		addPanel.add(addYearLabel);
		addPanel.add(addMonthsLabel);
		addPanel.add(addDaysLabel);
		addPanel.add(addAddedMonths);
		addPanel.add(addAddedDays);
		addPanel.add(addAddedYear);		
		addPanel.add(addSubmitButton);
		addPanel.add(addClearButton);
		addPanel.add(addAdditionalText);
		
		addAdditionalLabel.setVisible(false);
		addDaysLabel.setVisible(false);
		addMonthsLabel.setVisible(false);
		addYearLabel.setVisible(false);
		addAdditionalText.setVisible(false);
		addNameLabel.setVisible(false);
		addInputText.setVisible(false);
		addSubmitButton.setVisible(false);
		addClearButton.setVisible(false);
		addAddedMonths.setVisible(false);
		addAddedDays.setVisible(false);
		addAddedYear.setVisible(false);
		
		addPanel.add(addPigBox);
		addPigBox.setVisible(false);
    }

    private void historyComponents()
	{
		javax.swing.GroupLayout historyPanelLayout = new javax.swing.GroupLayout(historyPanel);
        historyPanel.setLayout(historyPanelLayout);
        historyPanelLayout.setHorizontalGroup(
            historyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        historyPanelLayout.setVerticalGroup(
            historyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 372, Short.MAX_VALUE)
        );
	
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(navButtonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historyPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(navButtonsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(historyPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
		JLabel label = new JLabel("Date");
		JButton exportButton = new JButton("Export");
		
		exportButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				int exportSuccess = export(historyTable);
				if(exportSuccess == 1)
				{
					JOptionPane.showMessageDialog(null, "Export Success.");
				}
				else if(exportSuccess == -1)
				{
					JOptionPane.showMessageDialog(null, "Failed to export Table.");
				}
			}
		}
		);
		
		historyMonth.setBounds(50,21,100,25);
		historyYear.setBounds(160,21,50,25);
		label.setBounds(120,0,100,21);
		historyOption.setBounds(220,21,150,25);
		
		exportButton.setBounds(380, 21, 100, 25);
		historyPanel.add(exportButton);
		exportButton.setVisible(false);
		
		historyPanel.add(historyMonth);
		historyPanel.add(historyYear);
		historyPanel.add(historyOption);
		historyPanel.add(label);
		
		try {
			if (historyOption.getSelectedItem().toString().equals("Breeding")) {
				ResultSet rs = sqlstat.executeQuery("SELECT COUNT(*) FROM breeding WHERE breeding_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND breeding_year = " + Integer.parseInt(historyYear.getText()) + ";");
				int noofrows = rs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"boar no.", "sow no.", "breeding date", "parity", "farrowing date", "remarks"};
					
					rs.close();
					String[][] rows = new String[noofrows][6];
					int j = 0;
					while (j < noofrows) {
						rs = sqlstat.executeQuery("SELECT * FROM breeding WHERE breeding_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND breeding_year = " + Integer.parseInt(historyYear.getText()) + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = "" + rs.getInt(1);
							rows[j][1] = "" + rs.getInt(2);
							String date = "" + historyMonth.getItemAt(rs.getInt(3)-1) + " " + rs.getInt(4) + ", " + rs.getInt(5);
							rows[j][2] = date;
							if (rs.getInt(8) != 0) {
								rows[j][3] = "" + rs.getInt(6);
								date = "" + historyMonth.getItemAt(rs.getInt(7)-1) + " " + rs.getInt(8) + ", " + rs.getInt(9);
								rows[j][4] = date;
							} else {
								rows[j][3] = "";
								rows[j][4] = "";
							}
							rows[j][5] = rs.getString(10);			
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(60);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(50);
					historyTable.getColumnModel().getColumn(4).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(5).setPreferredWidth(150);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
					
				}
			} else if (historyOption.getSelectedItem().toString().equals("Farrowing")) {
				ResultSet rs = sqlstat.executeQuery("SELECT COUNT(*) FROM farrowing WHERE farrowing_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND farrowing_year = " + Integer.parseInt(historyYear.getText()) + ";");
				int noofrows = rs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"sow no.", "farrowing date", "ear nos. assigned", "live born", "still born", "mummified", "abnormal", "remarks"};
					
					rs.close();
					String[][] rows = new String[noofrows][8];
					int j = 0;
					while (j < noofrows) {
						rs = sqlstat.executeQuery("SELECT sow_no, farrowing_month, farrowing_day, farrowing_year, ear_nos_assigned_start, ear_nos_assigned_end, live_born_no, still_born_no, mummified_no, abnormal_no, remarks FROM farrowing WHERE farrowing_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND farrowing_year = " + Integer.parseInt(historyYear.getText()) + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = "" + rs.getInt(1);
							String date = "" + historyMonth.getItemAt(rs.getInt(2)-1) + " " + rs.getInt(3) + ", " + rs.getInt(4);
							rows[j][1] = date;
							rows[j][2] = "" + rs.getInt(5) + "-" + rs.getInt(6);
							rows[j][3] = "" + rs.getInt(7);
							rows[j][4] = "" + rs.getInt(8);
							rows[j][5] = "" + rs.getInt(9);
							rows[j][6] = "" + rs.getInt(10);
							rows[j][7] = rs.getString(11);			
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(60);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(140);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(4).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(5).setPreferredWidth(80);
					historyTable.getColumnModel().getColumn(6).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(7).setPreferredWidth(150);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("Sold Butchered")) {
				ResultSet rs = sqlstat.executeQuery("SELECT COUNT(*) FROM soldpigs WHERE sold_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND sold_year = " + Integer.parseInt(historyYear.getText()) + " AND selling_id = 2;");
				int noofrows = rs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"buyer", "sold date", "price per kilo", "total weight sold", "amount", "ear no.", "butchered date", "weight sold"};
					
					rs.close();
					String[][] rows = new String[noofrows][8];
					int j = 0;
					while (j < noofrows) {
						rs = sqlstat.executeQuery("SELECT * FROM soldpigs p JOIN buyers a, soldbutchereddetails b ON p.butchered_ear_no = b.ear_no WHERE a.buyer_id = p.buyer_id AND sold_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND sold_year = " + Integer.parseInt(historyYear.getText()) + " AND selling_id = 2" + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = rs.getString(11) + ", " + rs.getString(12);
							String date = "" + historyMonth.getItemAt(rs.getInt(3)-1) + " " + rs.getInt(4) + ", " + rs.getInt(5);//tama date
							rows[j][1] = date;
							rows[j][2] = "" + rs.getInt(6);// price per kilo
							rows[j][3] = "" + rs.getInt(7);//tama total weight sold
							rows[j][4] = "" + rs.getFloat(8);// amount
							rows[j][5] = "" + rs.getInt(9);//tama ear no.
							date = "" + historyMonth.getItemAt(rs.getInt(14)-1) + " " + rs.getInt(15) + ", " + rs.getInt(16);//tama butchereddate
							rows[j][6] = date;
							rows[j][7] = "" + rs.getFloat(17);//tama weight sold
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(100);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(120);
					historyTable.getColumnModel().getColumn(4).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(5).setPreferredWidth(60);
					historyTable.getColumnModel().getColumn(6).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(7).setPreferredWidth(90);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("Sold Alive")) {
				ResultSet rs = sqlstat.executeQuery("SELECT COUNT(*) FROM soldpigs p JOIN soldalivedetails a ON p.buyer_id = a.buyer_id AND p.sold_month = a.sold_month AND p.sold_day = a.sold_day AND p.sold_year = a.sold_year WHERE p.sold_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND p.sold_year = " + Integer.parseInt(historyYear.getText()) + " AND selling_id = 1;");
				int noofrows = rs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"buyer", "sold date", "price per kilo", "total weight sold", "amount", "no. of heads", "kilos less", "ear nos. sold"};
					
					rs.close();
					String[][] rows = new String[noofrows][8];
					int j = 0;
					while (j < noofrows) {
						rs = sqlstat.executeQuery("SELECT b.last_name, b.first_name, p.sold_month, p.sold_day, p.sold_year, p.price_per_kilo, p.kilos_total_weight_sold, p.amount, a.no_of_heads, a.kilos_less FROM buyers b, soldpigs p JOIN soldalivedetails a ON p.buyer_id = a.buyer_id AND p.sold_month = a.sold_month AND p.sold_day = a.sold_day AND p.sold_year = a.sold_year WHERE b.buyer_id = p.buyer_id AND p.sold_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND p.sold_year = " + Integer.parseInt(historyYear.getText()) + " AND selling_id = 1" + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = rs.getString(1) + ", " + rs.getString(2);
							String date = "" + historyMonth.getItemAt(rs.getInt(3)-1) + " " + rs.getInt(4) + ", " + rs.getInt(5);
							rows[j][1] = date;
							rows[j][2] = "" + rs.getInt(6);
							rows[j][3] = "" + rs.getFloat(7);
							rows[j][4] = "" + rs.getFloat(8);
							rows[j][5] = "" + rs.getInt(9);
							rows[j][6] = "" + rs.getInt(10);			
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(100);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(120);
					historyTable.getColumnModel().getColumn(4).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(5).setPreferredWidth(90);
					historyTable.getColumnModel().getColumn(6).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(7).setPreferredWidth(140);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("Salaries")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM salaries WHERE to_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND to_year = " + Integer.parseInt(historyYear.getText()) + ";");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"employee", "amount", "date range"};
					
					sqlrs.close();
					String[][] rows = new String[noofrows][3];
					int j = 0;
					while (j < noofrows) {
						sqlrs = sqlstat.executeQuery("SELECT e.last_name, e.first_name, s.amount, s.from_month, s.from_day, s.from_year, s.to_month, s.to_day, s.to_year FROM employees e, salaries s WHERE e.employee_id = s.employee_id AND to_month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND to_year = " + Integer.parseInt(historyYear.getText()) + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (sqlrs.next()) {
							rows[j][0] = sqlrs.getString(1) + ", " + sqlrs.getString(2);
							rows[j][1] = "" + sqlrs.getFloat(3);
							String date = "" + historyMonth.getItemAt(sqlrs.getInt(4)-1) + " " + sqlrs.getInt(5) + ", " + sqlrs.getInt(6) + " - " + historyMonth.getItemAt(sqlrs.getInt(7)-1) + " " + sqlrs.getInt(8) + ", " + sqlrs.getInt(9);
							rows[j][2] = date;
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(300);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("Inventory Expenses")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM inventoryexpenses a JOIN inventoryexpensedetails b ON a.delivery_receipt_no = b.delivery_receipt_no WHERE month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND year = " + Integer.parseInt(historyYear.getText()) + ";");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"receipt no.", "item", "quantity", "amount", "date", "total amount"};
					
					sqlrs.close();
					String[][] rows = new String[noofrows][6];
					int j = 0;
					while (j < noofrows) {
						sqlrs = sqlstat.executeQuery("SELECT a.delivery_receipt_no, i.item, b.quantity, b.amount, a.month, a.day, a.year, a.total_amount FROM items i, inventoryexpenses a JOIN inventoryexpensedetails b ON a.delivery_receipt_no = b.delivery_receipt_no WHERE i.item_id =  b.item_id AND month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND year = " + Integer.parseInt(historyYear.getText()) + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (sqlrs.next()) {
							rows[j][0] = "" + sqlrs.getInt(1);
							rows[j][1] = sqlrs.getString(2);
							rows[j][2] = "" + sqlrs.getInt(3);
							rows[j][3] = "" + sqlrs.getFloat(4);
							String date = "" + historyMonth.getItemAt(sqlrs.getInt(5)-1) + " " + sqlrs.getInt(6) + ", " + sqlrs.getInt(7);
							rows[j][4] = date;
							rows[j][5] = "" + sqlrs.getFloat(8);
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(90);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(250);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(60);
					historyTable.getColumnModel().getColumn(4).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(5).setPreferredWidth(100);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("Misc. Expenses")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM micellaneousexpenses WHERE month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND year = " + Integer.parseInt(historyYear.getText()) + ";");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"date", "item", "amount", "notes"};
					
					sqlrs.close();
					String[][] rows = new String[noofrows][4];
					int j = 0;
					while (j < noofrows) {
						sqlrs = sqlstat.executeQuery("SELECT m.month, m.day, m.year, i.item, m.amount, m.notes FROM items i, micellaneousexpenses m WHERE i.item_id = m.item_id AND month = " + monthToInt(historyMonth.getSelectedItem().toString()) + " AND year = " + Integer.parseInt(historyYear.getText()) + " LIMIT " + j + ", " + (j+40) + ";");
						while (sqlrs.next()) {
							String date = "" + historyMonth.getItemAt(sqlrs.getInt(1)-1) + " " + sqlrs.getInt(2) + ", " + sqlrs.getInt(3);
							rows[j][0] = date;
							rows[j][1] = sqlrs.getString(4);
							rows[j][2] = "" + sqlrs.getFloat(5);
							rows[j][3] = sqlrs.getString(6);
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(250);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(150);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("pigs")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM pigs");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"ear no.", "type", "date added", "date died", "building no.", "employee-in-charge", "cause of death/remarks"};
					sqlrs.close();
					String[][] rows = new String[noofrows][7];
					int j = 0;
					while (j < noofrows) {
						ResultSet rs = sqlstat.executeQuery("SELECT ear_no, pig_id, month_added, day_added, year_added, month_died, day_died, year_died, building_no, employee_id, cause_of_death_remarks FROM pigs LIMIT " + j + ", " + (j+40) + ";");
						while (rs.next()) {
							rows[j][0] = "" + rs.getInt(1);
							if (rs.getInt(2) == 1) {
								rows[j][1] = "Boar";
							} else if (rs.getInt(2) == 2) {
								rows[j][1] = "Sow";
							} else if (rs.getInt(2) == 3) {
								rows[j][1] = "Finisher";
							}
							String date = "" + historyMonth.getItemAt(rs.getInt(3)-1) + " " + rs.getInt(4) + ", " + rs.getInt(5);
							rows[j][2] = date;
							if (rs.getInt(6) != 0) {
								date = "" + historyMonth.getItemAt(rs.getInt(6)-1) + " " + rs.getInt(7) + ", " + rs.getInt(8);
								rows[j][3] = date;
								rows[j][4] = "" + sqlrs.getInt(9);
								rows[j][6] = "" + rs.getString(11);
								if (rs.getInt(10) != 0) {
									rows[j][5] = getEmployeeName(rs.getInt(10));	
								} else {
									rows[j][5] = "";
								}
							} else {
								rows[j][3] = "";
								rows[j][4] = "";
								rows[j][5] = "";
								rows[j][6] = "";
							}		
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(80);
					historyTable.getColumnModel().getColumn(2).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(3).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(4).setPreferredWidth(90);
					historyTable.getColumnModel().getColumn(5).setPreferredWidth(150);
					historyTable.getColumnModel().getColumn(6).setPreferredWidth(170);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("employees")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM employees");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"last name", "first name"};
					sqlrs.close();
					String[][] rows = new String[noofrows][2];
					int j = 0;
					while (j < noofrows) {
						ResultSet rs = sqlstat.executeQuery("SELECT last_name, first_name FROM employees" + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = rs.getString(1);
							rows[j][1] = rs.getString(2);
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(70);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("items")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM items");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"item name"};
					sqlrs.close();
					String[][] rows = new String[noofrows][1];
					int j = 0;
					while (j < noofrows) {
						ResultSet rs = sqlstat.executeQuery("SELECT item FROM items" + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = rs.getString(1);
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(70);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			} else if (historyOption.getSelectedItem().toString().equals("buyers")) {
				sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM buyers");
				int noofrows = sqlrs.getInt("COUNT(*)");
				exportButton.setVisible(true);
				if (noofrows > 0) {
					String[] col = {"last name", "first name"};
					sqlrs.close();
					String[][] rows = new String[noofrows][2];
					int j = 0;
					while (j < noofrows) {
						ResultSet rs = sqlstat.executeQuery("SELECT last_name, first_name FROM buyers" + " LIMIT " + j + ", " + (j+40) + ";");
						
						while (rs.next()) {
							rows[j][0] = rs.getString(1);
							rows[j][1] = rs.getString(2);
							j++;
						}
					}
					model = new DefaultTableModel(rows,col);	
					historyTable = new JTable(model);
					historyTable.getColumnModel().getColumn(0).setPreferredWidth(70);
					historyTable.getColumnModel().getColumn(1).setPreferredWidth(70);
					historyTable.setPreferredScrollableViewportSize(new Dimension(450,400));
					historyTable.setFillsViewportHeight(true);
					historyTable.setEnabled(false);
					historyTable.setBounds(50,50,450,400);
					historyScroll = new JScrollPane();
					historyScroll.setBounds(50,50,450,400);
					historyScroll.setViewportView(historyTable);
					historyPanel.add(historyScroll);
				} else {
					JOptionPane.showMessageDialog(null, "no records found");
					exportButton.setVisible(false);
				}
			}
			sqlrs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
    
    /**
     * This method removes the present panel on the right
     */
    private void clearPanels() {
		if (status == 1) {
			remove(homePanel);
		} else if (status == 2) {
    		remove(breedingPanel);
    	} else if (status == 3) {
    		remove(farrowingPanel);	
    	} else if (status == 4) {
    		remove(mortalityPanel);
    	} else if (status == 5) {
    		remove(inventoryPanel);
    	} else if (status == 6) {
    		remove(finisherPanel);
    	} else if (status == 7) {
    		remove(butcheredPanel);
    	} else if (status == 8) {
    		remove(salariesPanel);
    	} else if (status == 9) {
    		remove(micellaneousPanel);
    	} else if (status == 10) {
    		remove(summaryPanel);
    	} else if (status == 11) {
    		remove(addPanel);
    	}else if (status == 12) {
    		remove(historyPanel);
    	}
    }
    
    
    //code for pressing left side buttons
    /**
     * When the home button is pressed
     */
    private void nav1ActionPerformed(ActionEvent evt) {
    	clearPanels();
		this.homePanel.removeAll();
		homeComponents();
    	status = 1;
    	titleLabel.setText("                                             ");
        validate();
        repaint();
        clearAll();
    }
    /**
     * When the add breeding item is pressed
     */
    private void nav2ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.breedingPanel.removeAll();
    	breedingComponents();	
    	status = 2;
    	titleLabel.setText("                                 Add Breeding Item");
        validate();
        repaint();   
        clearAll();
    }
    /**
     * When the add farrowing item is pressed
     */
    private void nav3ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.farrowingPanel.removeAll();
    	farrowingComponents();
    	status = 3;
    	titleLabel.setText("                              Add Farrowing Item");
        validate();
        repaint();    
        clearAll();
    }
    /**
     * When the add mortality item is pressed
     */
    private void nav4ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.mortalityPanel.removeAll();
    	mortalityComponents();
    	status = 4;
    	titleLabel.setText("                                 Add Mortality Item");
        validate();
        repaint();    
        clearAll();
    }
    /**
     * When the add inventory item is pressed
     */
    private void nav5ActionPerformed(ActionEvent evt) {
    	clearPanels();    
    	this.inventoryPanel.removeAll();
    	inventoryComponents();
    	this.inventoryMonths.setVisible(false);
    	this.inventoryDays.setVisible(false);
    	this.inventoryYear.setVisible(false);
    	this.inventoryDateLabel.setVisible(false);
    	status = 5;
        titleLabel.setText("                              Add Inventory Item");
        validate();
        repaint(); 
        clearAll();
    }
    /**
     * When the add finisher sales item is pressed
     */
    private void nav6ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.finisherPanel.removeAll();
    	finisherComponents();
    	this.finisherAmountLabel.setVisible(false);
    	this.finisherAmountText.setVisible(false);
    	status = 6;
    	titleLabel.setText("                            Add Finisher Sales Item");
        validate();
        repaint();    
        clearAll();
    }
    /**
     * When the add butchered sales item is pressed
     */
    private void nav7ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.butcheredPanel.removeAll();
    	butcheredComponents();
    	this.butcheredAmountLabel.setVisible(false);
    	this.butcheredAmountText.setVisible(false);
    	this.butcheredPhp2Label.setVisible(false);
    	status = 7;
    	titleLabel.setText("                            Add Butchered Sales Item");
        validate();
        repaint();    
        clearAll();
    }
    /**
     * When the add salaries expenses item is pressed
     */
    private void nav8ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.salariesPanel.removeAll();
    	salariesComponents();
    	status = 8;
    	titleLabel.setText("                            Add Salaries Expenses Item");
        validate();
        repaint();    
        clearAll();
    }
    /**
     * When the add miscellaneous item is pressed
     */
    private void nav9ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.micellaneousPanel.removeAll();
    	micellaneousComponents();
    	status = 9;
    	titleLabel.setText("                       Add Miscellaneous Expense Item");
        validate();
        repaint();  
        clearAll();
    }
    /**
     * When the summary is pressed
     */
    private void nav10ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.summaryPanel.removeAll();
    	summaryComponents();
    	status = 10;
    	titleLabel.setText("                                           Summary");
        validate();
        repaint();    
        clearAll();
    }
    /**
     * When the add employee, buyer, or item is pressed
     */
    private void nav11ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.addPanel.removeAll();
    	addComponents();
    	status = 11;
    	titleLabel.setText("                   Add Employees, Buyer, Items or Pigs");
        validate();
        repaint();    
        clearAll();
    }
	/**
	 * When the show history button is pressed
	 */
	private void nav12ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.historyPanel.removeAll();
    	historyOption.setSelectedIndex(0);
    	historyComponents();
    	status = 12;
    	titleLabel.setText("                                           History");
        validate();
        repaint();    
        clearAll();
    }
    
	/**
	 * checks if the ear number exists in the pig table
	 */
	private boolean ifPigExists(int earno) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT * FROM pigs WHERE ear_no = " + earno + ";");
		boolean exists = !sqlrs.isClosed();
		sqlrs.close();
		return exists;
	}
	
	/**
	 * checks if the earno is still alive
	 */
	private boolean ifPigIsAlive(int earno) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT * FROM pigs WHERE ear_no = " + earno + ";");
		boolean alive = true;
		//not finished
		return alive;
	}
	
	/**
	 * checks if the receipt no. is already in the database
	 */
	private boolean ifReceiptExists(int receiptno) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT * FROM inventoryexpenses WHERE delivery_receipt_no = " + receiptno + ";");
		boolean exists = !sqlrs.isClosed();
		sqlrs.close();
		return exists;
	}
	
	/**
	 * checks if the ear number has the proper pig_id == 2 (sow)
	 */
	private boolean isASow(int earno) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT pig_id FROM pigs WHERE ear_no = " + earno + ";");
		int pigid = sqlrs.getInt(1);
		sqlrs.close();
		if (pigid == 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * checks if the ear number has the proper pig_id == 1 (boar)
	 */
	private boolean isABoar(int earno) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT pig_id FROM pigs WHERE ear_no = " + earno + ";");
		int pigid = sqlrs.getInt(1);
		sqlrs.close();
		if (pigid == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the specified column from the pigs table of the specified earno
	 */
	private int getPigDetail(int earno, int columnno) throws SQLException {
		switch (columnno) {
			case 2:
				sqlrs = sqlstat.executeQuery("SELECT pig_id FROM pigs WHERE ear_no = " + earno + ";");
				break;
			case 3:
				sqlrs = sqlstat.executeQuery("SELECT month_added FROM pigs WHERE ear_no = " + earno + ";");
				break;
			case 4:
				sqlrs = sqlstat.executeQuery("SELECT day_added FROM pigs WHERE ear_no = " + earno + ";");
				break;
			case 5:
				sqlrs = sqlstat.executeQuery("SELECT year_added FROM pigs WHERE ear_no = " + earno + ";");
				break;
			default:
				sqlrs = sqlstat.executeQuery("SELECT ear_no FROM pigs WHERE ear_no = " + earno + ";");
		}
		int detail = sqlrs.getInt(1);
		sqlrs.close();
		return detail;
	}
	
	/**
	 * Gets the specified column from the itemexpenses table of the specified receiptno
	 */
	private int getReceiptDetail(int receiptno, int columnno) throws SQLException {
		switch (columnno) {
			case 1:
				sqlrs = sqlstat.executeQuery("SELECT month FROM inventoryexpenses WHERE delivery_receipt_no = " + receiptno + ";");
				break;
			case 2:
				sqlrs = sqlstat.executeQuery("SELECT day FROM inventoryexpenses WHERE delivery_receipt_no = " + receiptno + ";");
				break;
			case 3:
				sqlrs = sqlstat.executeQuery("SELECT year FROM inventoryexpenses WHERE delivery_receipt_no = " + receiptno + ";");
				break;
			case 5:
				sqlrs = sqlstat.executeQuery("SELECT total_amount FROM inventoryexpenses WHERE delivery_receipt_no = " + receiptno + ";");
				break;
			default:
				sqlrs = sqlstat.executeQuery("SELECT delivery_receipt_no FROM inventoryexpenses WHERE delivery_receipt_no = " + receiptno + ";");
		}
		int detail = sqlrs.getInt(1);
		sqlrs.close();
		return detail;
	}
	
	/**
	 * gets the name of the employee based on the given employee_id in the employees table of the database
	 */
	private String getEmployeeName(int employeeid) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT * from employees WHERE employee_id = " + employeeid + ";");
		String name = sqlrs.getString("last_name") + ", " + sqlrs.getString("first_name");
		sqlrs.close();
		return name;
	}
	
	/**
	 * gets the employee_id of the specified last name and first name from the employees table
	 */
	private int getEmployeeId(String name) throws SQLException {
		int ctr = 0;
		while(name.charAt(ctr) != ',') {
			ctr++;
		}
		sqlrs = sqlstat.executeQuery("SELECT * FROM employees WHERE last_name = \"" + name.substring(0, ctr) + "\";");
		sqlrs = sqlstat.executeQuery("SELECT employee_id FROM employees WHERE first_name = \"" + name.substring(ctr + 2, name.length()) + "\";");
		int id = sqlrs.getInt(1);
		sqlrs.close();
		return id;
	}
	
	/**
	 * gets the buyer name based on the given buyer_id from the buyers table
	 */
	private String getBuyerName(int buyerid) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT * FROM buyers WHERE buyer_id = " + buyerid + ";");
		String name = sqlrs.getString("last_name") + ", " + sqlrs.getString("first_name");
		sqlrs.close();
		return name;
	}
	
	/**
	 * gets the buyer_id of the specified last name and first name from the buyers table
	 */
	private int getBuyerId(String name) throws SQLException {
		int ctr = 0;
		while(name.charAt(ctr) != ',') {
			ctr++;
		}
		sqlrs = sqlstat.executeQuery("SELECT * FROM buyers WHERE last_name = \"" + name.substring(0, ctr) + "\";");
		sqlrs = sqlstat.executeQuery("SELECT buyer_id FROM buyers WHERE first_name = \"" + name.substring(ctr + 2, name.length()) + "\";");
		int id = sqlrs.getInt(1);
		sqlrs.close();
		return id;
	}
	
	/**
	 * gets the item name based on the given item_id from the items table
	 */
	private String getItemName(int itemid) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT item FROM items WHERE item_id = " + itemid + ";");
		String item = sqlrs.getString(1);
		sqlrs.close();
		return item;
	}
	
	/**
	 * gets the item id of the specified item from the items table
	 */
	private int getItemId(String name) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT item_id FROM items WHERE item = \"" + name + "\";");
		int id = sqlrs.getInt(1);
		sqlrs.close();
		return id;
	}
	
	/**
	 * removes the row of the item expense with the specified receiptno from the itemexpeneses table
	 */
	private void removeItemExpenseRow(int receiptno) throws SQLException {
		prepInventoryExpenses = sqlconn.prepareStatement("DELETE FROM inventoryexpenses WHERE delivery_receipt_no = ?;");
		prepInventoryExpenses.setInt(1, receiptno);
		prepInventoryExpenses.executeUpdate();
		prepInventoryExpenses = sqlconn.prepareStatement("insert into inventoryexpenses values (?, ?, ?, ?, ?);");
	}
	
	
    //Submit buttons
    /**
     * When a breeding item is submitted
     */
	private void breedingSubmitActionPerformed(ActionEvent evt) {
		try {
			String breedingmonth = breedingBreedingMonths.getSelectedItem().toString();
			String breedingday = breedingBreedingDays.getSelectedItem().toString();
			String breedingyear = breedingBreedingYear.getText();
			String sow = breedingSowText.getText();
			String parity = breedingParityText.getText();
			String boar = breedingBoarText.getText();

			if (ifPigExists(Integer.parseInt(sow)) && isASow(Integer.parseInt(sow))) {
				if (ifPigExists(Integer.parseInt(boar)) && isABoar(Integer.parseInt(boar))) {
						String farrowingmonth = breedingFarrowingMonths.getSelectedItem().toString();
						String farrowingday = breedingFarrowingDays.getSelectedItem().toString();
						String farrowingyear = breedingFarrowingYear.getText();
						String notes = breedingNotesText.getText();
						
						prepBreeding.setInt(1, Integer.parseInt(boar));
						prepBreeding.setInt(2, Integer.parseInt(sow));
						prepBreeding.setInt(3, monthToInt(breedingmonth));
						prepBreeding.setInt(4, Integer.parseInt(breedingday));
						prepBreeding.setInt(5, Integer.parseInt(breedingyear));
						if (breedingParityText.getText().equals("")) {
							sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM breeding WHERE sow_no =" + sow + ";");
							prepBreeding.setInt(6, sqlrs.getInt(1));
							sqlrs.close();
						} else {
							prepBreeding.setNull(6, Types.INTEGER);
						}
						prepBreeding.setNull(7, Types.INTEGER);
						prepBreeding.setNull(8, Types.INTEGER);
						prepBreeding.setNull(9, Types.INTEGER);
						prepBreeding.setString(10, notes);
						
						
						
						if(!checkDate(monthToInt(breedingmonth),Integer.parseInt(breedingday),Integer.parseInt(breedingyear)))
						{
							JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!validateDate(monthToInt(breedingmonth),Integer.parseInt(breedingday),Integer.parseInt(breedingyear)))
						{
							JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0 ){
							sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM breeding WHERE boar_no = " + boar + " AND sow_no = " + sow + " AND farrowing_month IS NULL;");
							int go = sqlrs.getInt(1);
							sqlrs.close();
							
							if (go <= 0) {
								prepBreeding.addBatch();
								sqlconn.setAutoCommit(false);
								prepBreeding.executeBatch();
								sqlconn.setAutoCommit(true);
							} else {
								sqlrs = sqlstat.executeQuery("SELECT breeding_month, breeding_day, breeding_year FROM breeding WHERE boar_no = " + boar + " AND sow_no = " + sow + " AND farrowing_month IS NULL;");
								JOptionPane.showMessageDialog(null, "The sow(" + sow + ") hasn't farrowed on breeding date " + breedingBreedingMonths.getItemAt(sqlrs.getInt(1)-1) + " ," + sqlrs.getInt(2) + " " + sqlrs.getInt(3) + " with boar(" + boar + ")", "Double Breeding", JOptionPane.ERROR_MESSAGE);
							}
						}
				} else {
					if (isABoar(Integer.parseInt(boar))) {
						JOptionPane.showMessageDialog(null, "Boar number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);					
					} else {
						JOptionPane.showMessageDialog(null, "The ear number entered is not a boar", "Wrong Boar number", JOptionPane.ERROR_MESSAGE);											
					}
				}
			} else {
				if (isASow(Integer.parseInt(sow))) {
					JOptionPane.showMessageDialog(null, "Sow number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The ear number entered is not a sow", "Wrong sow number", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a farrowing item is submitted
     */
    private void farrowingSubmitActionPerformed(ActionEvent evt) {
    	try {
			String sow = farrowingSowText.getText();
			String boar = farrowingBoarText.getText();
			if (ifPigExists(Integer.parseInt(sow)) && isASow(Integer.parseInt(sow))) {
				if (ifPigExists(Integer.parseInt(boar)) && isABoar(Integer.parseInt(boar))) {
					String farrowingmonth = farrowingMonths.getSelectedItem().toString();
					String farrowingday = farrowingDays.getSelectedItem().toString();
					String farrowingyear = farrowingYear.getText();
					String earNo = farrowingEarText.getText();
					int earNoInt = Integer.parseInt(earNo);
					String liveBorn = farrowingLiveText.getText();
					int liveBornInt = Integer.parseInt(liveBorn);
					String stillBorn = farrowingStillText.getText();
					String mummified = farrowingMummifiedText.getText();
					String abnormal = farrowingAbnormalText.getText();
					String notes = farrowingNotesText.getText();
					
					prepFarrowing.setInt(1, Integer.parseInt(sow));
					prepFarrowing.setInt(2, monthToInt(farrowingmonth));
					prepFarrowing.setInt(3, Integer.parseInt(farrowingday));
					prepFarrowing.setInt(4, Integer.parseInt(farrowingyear));
					prepFarrowing.setInt(5, earNoInt);
					prepFarrowing.setInt(6, earNoInt + liveBornInt - 1);
					prepFarrowing.setInt(7, liveBornInt);
					prepFarrowing.setInt(8, Integer.parseInt(stillBorn));
					prepFarrowing.setInt(9, Integer.parseInt(mummified));
					prepFarrowing.setInt(10, Integer.parseInt(abnormal));
					prepFarrowing.setString(11, notes);
					
					sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM pigs WHERE ear_no BETWEEN " + earNoInt + " AND " + (liveBornInt-1) + ";");
					int usedearno = sqlrs.getInt(1);
					sqlrs.close();
					
					if (usedearno == 0) {
						if(!checkDate(monthToInt(farrowingmonth),Integer.parseInt(farrowingday),Integer.parseInt(farrowingyear)))
						{
							JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!validateDate(monthToInt(farrowingmonth),Integer.parseInt(farrowingday),Integer.parseInt(farrowingyear)))
						{
							JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){				
							sqlrs = sqlstat.executeQuery("SELECT COUNT(*) FROM breeding WHERE sow_no = " + sow + " AND boar_no = " + boar + " AND farrowing_month IS NULL;");
							int ctr = sqlrs.getInt(1);
							sqlrs.close();
							
							
							if (ctr == 1) {
								sqlrs = sqlstat.executeQuery("SELECT breeding_month, breeding_day, breeding_year FROM breeding WHERE sow_no = " + sow + " AND boar_no = " + boar + " AND farrowing_month IS NULL;");
								
								if(dateOrderCheck(
										new java.util.Date(sqlrs.getInt(3)-1900 ,sqlrs.getInt(1)-1,sqlrs.getInt(2)),
										new java.util.Date(Integer.parseInt(farrowingyear)-1900,monthToInt(farrowingmonth)-1,Integer.parseInt(farrowingday))))
										{
											JOptionPane.showMessageDialog(null, "Farrowing Date must be after Breeding Date(" + farrowingMonths.getItemAt(sqlrs.getInt(1)-1) + " " + sqlrs.getInt(2) + ", " + sqlrs.getInt(3) + ")", "Date Error", JOptionPane.ERROR_MESSAGE);
											sqlrs.close();
								} else {
									sqlrs.close();
									prepFarrowing.addBatch();
									sqlconn.setAutoCommit(false);
									prepFarrowing.executeBatch();
									sqlconn.setAutoCommit(true);
									
									
									prepBreeding = sqlconn.prepareStatement("UPDATE breeding SET farrowing_month = " + monthToInt(farrowingmonth) + ", farrowing_day = " + Integer.parseInt(farrowingday) + ", farrowing_year = " + Integer.parseInt(farrowingyear) + " WHERE sow_no = " + sow + " AND " + "boar_no = " + boar + " AND farrowing_month IS NULL;");
									prepBreeding.executeUpdate();
									prepBreeding = sqlconn.prepareStatement("insert into breeding values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
		
									for (int i = earNoInt; i < (earNoInt + liveBornInt); i++) {
										prepPigs.setInt(1, i);
										prepPigs.setInt(2, 3);
										prepPigs.setInt(3, monthToInt(farrowingmonth));
										prepPigs.setInt(4, Integer.parseInt(farrowingday));
										prepPigs.setInt(5, Integer.parseInt(farrowingyear));
										prepPigs.setInt(6, 0);
										prepPigs.setInt(7, 0);
										prepPigs.setInt(8, 0);
										prepPigs.setInt(9, 0);
										prepPigs.setNull(10, Types.VARCHAR);
										prepPigs.addBatch();
										sqlconn.setAutoCommit(false);
										prepPigs.executeBatch();
										sqlconn.setAutoCommit(true);			
									}
								}
							} else {
								JOptionPane.showMessageDialog(null, "The sow number and boar number hasn't been bred", "Breeding mismatch", JOptionPane.ERROR_MESSAGE);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "The ear numbers assigned are already used", "Reused ear number", JOptionPane.ERROR_MESSAGE);	
					}
				} else {
					if (isABoar(Integer.parseInt(boar))) {
						JOptionPane.showMessageDialog(null, "Boar number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "The ear number entered is not a boar", "Wrong sow number", JOptionPane.ERROR_MESSAGE);					
					}
				}
			} else {
				if (isASow(Integer.parseInt(sow))) {
					JOptionPane.showMessageDialog(null, "Sow number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The ear number entered is not a sow", "Wrong sow number", JOptionPane.ERROR_MESSAGE);					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /*
     * When a mortality item is submitted
     */
    private void mortalitySubmitActionPerformed(ActionEvent evt) {
		try {
			String mortalitymonth = mortalityMonths.getSelectedItem().toString();
			String mortalityday = mortalityDays.getSelectedItem().toString();
			String mortalityyear = mortalityYear.getText();
			String pig = mortalityEarText.getText();
			String buildingno = mortalityBuildingText.getText();
			String employee = mortalityEmployeeBox.getSelectedItem().toString();
			String cause = mortalityCauseText.getText();			
			
			if (ifPigExists(Integer.parseInt(pig))) {
				int pigno = Integer.parseInt(pig);
				if (ifPigIsAlive(pigno)) {		
					if(!checkDate(monthToInt(mortalitymonth),Integer.parseInt(mortalityday),Integer.parseInt(mortalityyear)))
					{
						JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(!validateDate(monthToInt(mortalitymonth),Integer.parseInt(mortalityday),Integer.parseInt(mortalityyear)))
					{
						JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
					}
					else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
						prepPigs = sqlconn.prepareStatement("UPDATE pigs SET building_no = " + Integer.parseInt(buildingno) + ", employee_id = " + getEmployeeId(employee) + ", month_died = " + monthToInt(mortalitymonth) + ", day_died = " + Integer.parseInt(mortalityday) + ", year_died = " + Integer.parseInt(mortalityyear) + ", cause_of_death_remarks = \""+ cause + "\" WHERE ear_no = " + pigno + ";");
						prepPigs.executeUpdate();
						prepPigs = sqlconn.prepareStatement("insert into pigs values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ear number given is not alive", "Dead pig Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ear number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a inventory item is submitted
     */
    private void inventorySubmitActionPerformed(ActionEvent evt) {
    	try {
			String receiptno = inventoryReceiptText.getText();
			String amount = inventoryAmountText.getText();
			int receipt = Integer.parseInt(receiptno);
			String item = inventoryItemBox.getSelectedItem().toString();
			String quantity = inventoryQuantityText.getText();
			
			if (Integer.parseInt(quantity) <= 0) {
				JOptionPane.showMessageDialog(null, "quantity cannot be less than 1");
				Integer.parseInt("");
			}
			else if(Double.parseDouble(amount) <= 0)
			{
				JOptionPane.showMessageDialog(null, "amount cannot be less than or equal to 0");
				Integer.parseInt("");
			}
			
			if (ifReceiptExists(receipt)) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0) {
					prepInventoryExpenseDetails.setInt(1, receipt);
					prepInventoryExpenseDetails.setInt(2, getItemId(item));
					prepInventoryExpenseDetails.setInt(3, Integer.parseInt(quantity));
					prepInventoryExpenseDetails.setDouble(4, Double.parseDouble(amount));
					prepInventoryExpenseDetails.addBatch();
					sqlconn.setAutoCommit(false);
					prepInventoryExpenseDetails.executeBatch();
					sqlconn.setAutoCommit(true);
					
					int month = getReceiptDetail(receipt, 1);
					int day = getReceiptDetail(receipt, 2);
					int year = getReceiptDetail(receipt, 3);
					int amount2 = getReceiptDetail(receipt, 5);
					double totalamount = (Double.parseDouble(amount)*Integer.parseInt(quantity)) + amount2;
					removeItemExpenseRow(receipt);
					prepInventoryExpenses.setInt(1, month);
					prepInventoryExpenses.setInt(2, day);
					prepInventoryExpenses.setInt(3, year);
					prepInventoryExpenses.setInt(4, receipt);
					prepInventoryExpenses.setDouble(5, totalamount);
					prepInventoryExpenses.addBatch();
					sqlconn.setAutoCommit(false);
					prepInventoryExpenses.executeBatch();
					sqlconn.setAutoCommit(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Receipt number is not in the database. Add it first.", "Missing Receipt number", JOptionPane.ERROR_MESSAGE);
			}
    	} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a finisher item is submitted
     */
    private void finisherSubmitActionPerformed(ActionEvent evt) {
    	try {
    		String buyer = finisherBuyerBox.getSelectedItem().toString();
			String month = finisherMonths.getSelectedItem().toString();
			String day = finisherDays.getSelectedItem().toString();
			String year = finisherYear.getText();
			String sadnoofheads = finisherHeadsText.getText();
			String sadkilosless = finisherKilosText.getText();
			String sadearnossold = finisherEarsText.getText();

			String perkilo = finisherPriceText.getText();
			String weight = this.finisherWeightText.getText();
			double totalamount = (Double.parseDouble(weight) - Double.parseDouble(sadkilosless)) * (Double.parseDouble(perkilo));
			if ((Double.parseDouble(perkilo) <= 0) || (Double.parseDouble(weight) <= 0)) {
				JOptionPane.showMessageDialog(null, "weight cannot be less than 1");
				Integer.parseInt("");
			}
			
			if(!checkDate(monthToInt(month),Integer.parseInt(day),Integer.parseInt(year)))
				{
					JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!validateDate(monthToInt(month),Integer.parseInt(day),Integer.parseInt(year)))
				{
					JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION) == 0) {
				prepSoldPigs.setInt(1, getBuyerId(buyer));
				prepSoldPigs.setInt(2, 1);
				prepSoldPigs.setInt(3, monthToInt(month));
				prepSoldPigs.setInt(4, Integer.parseInt(day));
				prepSoldPigs.setInt(5, Integer.parseInt(year));
				prepSoldPigs.setDouble(6, Double.parseDouble(perkilo));
				prepSoldPigs.setDouble(7, Double.parseDouble(weight));
				prepSoldPigs.setDouble(8, totalamount);
				prepSoldPigs.setNull(9, Types.INTEGER);
				prepSoldPigs.addBatch();
				sqlconn.setAutoCommit(false);
				prepSoldPigs.executeBatch();
				sqlconn.setAutoCommit(true);
				
				prepSoldAliveDetails.setInt(1, getBuyerId(buyer));
				prepSoldAliveDetails.setInt(2, monthToInt(month));
				prepSoldAliveDetails.setInt(3, Integer.parseInt(day));
				prepSoldAliveDetails.setInt(4, Integer.parseInt(year));
				prepSoldAliveDetails.setInt(5, Integer.parseInt(sadnoofheads));
				prepSoldAliveDetails.setDouble(6, Double.parseDouble(sadkilosless));
				prepSoldAliveDetails.setString(7, sadearnossold);
				prepSoldAliveDetails.addBatch();
				sqlconn.setAutoCommit(false);
				prepSoldAliveDetails.executeBatch();
				sqlconn.setAutoCommit(true);
			}
    	} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a butchered item is submitted
     */
    private void butcheredSubmitActionPerformed(ActionEvent evt) {
    	try {
    		String earno = butcheredEarText.getText();
			String butmonth = butcheredButcherMonths.getSelectedItem().toString();
			String butday = butcheredButcherDays.getSelectedItem().toString();
			String butyear = butcheredButcherYear.getText();
			String butweight = butcheredWeight1Text.getText();
			
			if (ifPigExists(Integer.parseInt(earno))) {
				String buyer = butcheredBuyerBox.getSelectedItem().toString();
				String soldmonth = butcheredSoldMonths.getSelectedItem().toString();
				String soldday = butcheredSoldDays.getSelectedItem().toString();
				String soldyear = butcheredSoldYear.getText();
				String perkilo = butcheredPriceText.getText();
				String weight = butcheredWeight2Text.getText();
				double totalamount = (Double.parseDouble(weight)) * (Double.parseDouble(perkilo));
				
				if ((Double.parseDouble(perkilo) <= 0) || (Double.parseDouble(weight) <= 0) || (Double.parseDouble(butweight) <= 0)) {
					JOptionPane.showMessageDialog(null, "weight cannot be less than 1");
					Integer.parseInt("");
				}
				
				if(!checkDate(monthToInt(soldmonth),Integer.parseInt(soldday),Integer.parseInt(soldyear)) || !checkDate(monthToInt(butmonth),Integer.parseInt(butday),Integer.parseInt(butyear)))
				{
					JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!validateDate(monthToInt(soldmonth),Integer.parseInt(soldday),Integer.parseInt(soldyear)) || !validateDate(monthToInt(butmonth),Integer.parseInt(butday),Integer.parseInt(butyear)))
				{
					JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0) {
					if(dateOrderCheck(
							new java.util.Date(Integer.parseInt(butyear)-1900 ,monthToInt(butmonth)-1,Integer.parseInt(butday)),
							new java.util.Date(Integer.parseInt(soldyear)-1900,monthToInt(soldmonth)-1,Integer.parseInt(soldday))))
							{
								
								JOptionPane.showMessageDialog(null, "Sold Date must be after Butchered Date", "Date Error", JOptionPane.ERROR_MESSAGE);
					} else {
						prepSoldPigs.setInt(1, getBuyerId(buyer));
						prepSoldPigs.setInt(2, 2);
						prepSoldPigs.setInt(3, monthToInt(soldmonth));
						prepSoldPigs.setInt(4, Integer.parseInt(soldday));
						prepSoldPigs.setInt(5, Integer.parseInt(soldyear));
						prepSoldPigs.setDouble(6, Double.parseDouble(perkilo));
						prepSoldPigs.setDouble(7, Double.parseDouble(weight));
						prepSoldPigs.setDouble(8, totalamount);
						prepSoldPigs.setInt(9, Integer.parseInt(earno));
						prepSoldPigs.addBatch();
						sqlconn.setAutoCommit(false);
						prepSoldPigs.executeBatch();
						sqlconn.setAutoCommit(true);
						
						prepSoldButcheredDetails.setInt(1, Integer.parseInt(earno));
						prepSoldButcheredDetails.setInt(2, monthToInt(butmonth));
						prepSoldButcheredDetails.setInt(3, Integer.parseInt(butday));
						prepSoldButcheredDetails.setInt(4, Integer.parseInt(butyear));
						prepSoldButcheredDetails.setDouble(5, Double.parseDouble(butweight));
						prepSoldButcheredDetails.addBatch();
						sqlconn.setAutoCommit(false);
						prepSoldButcheredDetails.executeBatch();
						sqlconn.setAutoCommit(true);
						
						prepPigs = sqlconn.prepareStatement("UPDATE pigs SET month_died = " + monthToInt(butmonth) + ", day_died = " + Integer.parseInt(butday) + ", year_died = " + Integer.parseInt(butyear) + ", cause_of_death_remarks = \"sold butchered\" WHERE ear_no = " + earno + ";");
						prepPigs.executeUpdate();
						prepPigs = sqlconn.prepareStatement("insert into pigs values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
					}
				}
    		} else {
    			JOptionPane.showMessageDialog(null, "Ear number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
    		}
    	} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a salaries expense item is submitted
     */
    private void salariesSubmitActionPerformed(ActionEvent evt) {
    	try {
    		String employee = salariesEmployeeBox.getSelectedItem().toString();
    		String amount = salariesAmountText.getText();
			String frommonth = salariesStartMonths.getSelectedItem().toString();
			String fromday = salariesStartDays.getSelectedItem().toString();
			String fromyear = salariesStartYear.getText();
			String tomonth = salariesEndMonths.getSelectedItem().toString();
			String today = salariesEndDays.getSelectedItem().toString();
			String toyear = salariesEndYear.getText();
			
			if(Double.parseDouble(amount) <= 0)
			{
				JOptionPane.showMessageDialog(null, "amount cannot be less than or equal to 0");
				Integer.parseInt("");
			}
			
			if(!checkDate(monthToInt(frommonth),Integer.parseInt(fromday),Integer.parseInt(fromyear)) || !checkDate(monthToInt(tomonth),Integer.parseInt(today),Integer.parseInt(toyear)))
				{
					JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!validateDate(monthToInt(frommonth),Integer.parseInt(fromday),Integer.parseInt(fromyear)) || !validateDate(monthToInt(tomonth),Integer.parseInt(today),Integer.parseInt(toyear)))
				{
					JOptionPane.showMessageDialog(null, "Cannot add after current date.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if( dateOrderCheck(
				new java.util.Date( Integer.parseInt(fromyear)-1900 ,monthToInt(frommonth)-1,Integer.parseInt(fromday)),
				new java.util.Date(Integer.parseInt(toyear)-1900,monthToInt(tomonth)-1,Integer.parseInt(today))))
				{
					JOptionPane.showMessageDialog(null, "End Date must be after Start Date", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
				prepSalaries.setInt(1, getEmployeeId(employee));
				prepSalaries.setDouble(2, Double.parseDouble(amount));
				prepSalaries.setInt(3, monthToInt(frommonth));
				prepSalaries.setInt(4, Integer.parseInt(fromday));
				prepSalaries.setInt(5, Integer.parseInt(fromyear));
				prepSalaries.setInt(6, monthToInt(tomonth));
				prepSalaries.setInt(7, Integer.parseInt(today));
				prepSalaries.setInt(8, Integer.parseInt(toyear));
				
				prepSalaries.addBatch();
				sqlconn.setAutoCommit(false);
				prepSalaries.executeBatch();
				sqlconn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a micellaneous expense item is submitted
     */
    private void micellaneousSubmitActionPerformed(ActionEvent evt) {
    	try {
			String month = micellaneousMonths.getSelectedItem().toString();
			String day = micellaneousDays.getSelectedItem().toString();
			String year = micellaneousYear.getText();
			String item = micellaneousItemBox.getSelectedItem().toString();
    		String amount = micellaneousAmountText.getText();
    		String notes = micellaneousNotesText.getText();
			
			if(Double.parseDouble(amount) <= 0)
			{
				JOptionPane.showMessageDialog(null, "amount cannot be less than or equal to 0");
				Integer.parseInt("");
			}
			
			if(!checkDate(monthToInt(month),Integer.parseInt(day),Integer.parseInt(year)))
				{
					JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!validateDate(monthToInt(month),Integer.parseInt(day),Integer.parseInt(year)))
				{
					JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
				prepMicellaneousExpenses.setInt(1, monthToInt(month));
				prepMicellaneousExpenses.setInt(2, Integer.parseInt(day));
				prepMicellaneousExpenses.setInt(3, Integer.parseInt(year));
				prepMicellaneousExpenses.setInt(4, getItemId(item));
				prepMicellaneousExpenses.setDouble(5, Double.parseDouble(amount));
				prepMicellaneousExpenses.setString(6, notes);
				
				prepMicellaneousExpenses.addBatch();
				sqlconn.setAutoCommit(false);
				prepMicellaneousExpenses.executeBatch();
				sqlconn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }
    
    /**
     * When a add employee, buyer, or item is submitted
     */
    private void addSubmitActionPerformed(ActionEvent evt) {
		try {
			String selected = addWhatBox.getSelectedItem() + "";	
			if (selected.equals("employee")) {
				String name = addInputText.getText() + addAdditionalText.getText() + "~";
				name = name.toLowerCase();
				boolean valid = true;
				int ctr = 0;
				while (name.charAt(ctr) != '~') {
					ctr++;
					if (name.charAt(ctr) == ',') {
						valid = false;
					}
				}
				if (valid) {
					prepEmployees.setNull(1, Types.INTEGER);
					prepEmployees.setString(2, addInputText.getText()); 
					prepEmployees.setString(3, addAdditionalText.getText());
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION) == 0){
						prepEmployees.addBatch();
						sqlconn.setAutoCommit(false);
						prepEmployees.executeBatch();
						sqlconn.setAutoCommit(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Names should only consist of letters", "Name Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (selected.equals("buyer")) {
				String name = addInputText.getText() + addAdditionalText.getText() + "~";
				name = name.toLowerCase();
				boolean valid = true;
				int ctr = 0;
				while (name.charAt(ctr) != '~') {
					ctr++;
					if (name.charAt(ctr) == ',') {
						valid = false;
					}
				}
				if (valid) {
					prepBuyers.setNull(1, Types.INTEGER);
					prepBuyers.setString(2, addInputText.getText());
					prepBuyers.setString(3, addAdditionalText.getText());
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
						prepBuyers.addBatch();
						sqlconn.setAutoCommit(false);
						prepBuyers.executeBatch();
						sqlconn.setAutoCommit(true);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Names should only consist of letters", "Name Error", JOptionPane.ERROR_MESSAGE);
				}
			} else if (selected.equals("item")) {
				prepItems.setNull(1, Types.INTEGER);
				prepItems.setString(2, addInputText.getText().toLowerCase());
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
					prepItems.addBatch();
					sqlconn.setAutoCommit(false);
					prepItems.executeBatch();
					sqlconn.setAutoCommit(true);
				}
			} else if (selected.equals("pig")) {
				prepPigs.setInt(1, Integer.parseInt(addInputText.getText()));
				prepPigs.setInt(2, pigToInt(addPigBox.getSelectedItem().toString()));
				prepPigs.setInt(3, monthToInt(addAddedMonths.getSelectedItem().toString()));
				prepPigs.setInt(4, Integer.parseInt(addAddedDays.getSelectedItem().toString()));
				prepPigs.setInt(5, Integer.parseInt(addAddedYear.getText()));
				prepPigs.setNull(6, Types.INTEGER);
				prepPigs.setNull(7, Types.INTEGER);
				prepPigs.setNull(8, Types.INTEGER);
				prepPigs.setNull(9, Types.INTEGER);
				prepPigs.setNull(10, Types.VARCHAR);
				if(!checkDate(monthToInt(addAddedMonths.getSelectedItem().toString()),Integer.parseInt(addAddedDays.getSelectedItem().toString()),Integer.parseInt(addAddedYear.getText())))
						{
							JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!validateDate(monthToInt(addAddedMonths.getSelectedItem().toString()),Integer.parseInt(addAddedDays.getSelectedItem().toString()),Integer.parseInt(addAddedYear.getText())))
						{
							JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
					prepPigs.addBatch();
					sqlconn.setAutoCommit(false);
					prepPigs.executeBatch();
					sqlconn.setAutoCommit(true);
				}	
			} else if (selected.equals("receipt no.")) {
				prepInventoryExpenses.setInt(1, monthToInt(addAddedMonths.getSelectedItem().toString()));
				prepInventoryExpenses.setInt(2, Integer.parseInt(addAddedDays.getSelectedItem().toString()));
				prepInventoryExpenses.setInt(3, Integer.parseInt(addAddedYear.getText()));
				prepInventoryExpenses.setInt(4, Integer.parseInt(addInputText.getText()));
				prepInventoryExpenses.setInt(5, 0);
				
				if(!checkDate(monthToInt(addAddedMonths.getSelectedItem().toString()),Integer.parseInt(addAddedDays.getSelectedItem().toString()),Integer.parseInt(addAddedYear.getText())))
						{
							JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if(!validateDate(monthToInt(addAddedMonths.getSelectedItem().toString()),Integer.parseInt(addAddedDays.getSelectedItem().toString()),Integer.parseInt(addAddedYear.getText())))
						{
							JOptionPane.showMessageDialog(null, "Cannot add after present date.", "Date Error", JOptionPane.ERROR_MESSAGE);
						}
						else if (JOptionPane.showConfirmDialog(null, "Are you sure you want to add this entry?","Confirmation", JOptionPane.YES_NO_OPTION)  == 0){
					prepInventoryExpenses.addBatch();
					sqlconn.setAutoCommit(false);
					prepInventoryExpenses.executeBatch();
					sqlconn.setAutoCommit(true);
				}	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
    }

    JLabel picLabel;
	JFreeChart chart = null;
	BufferedImage bi = null;
	/**
	 * creates a bar chart using vectors that contain the data per specific month and year
	 */
	private void createBarChart(Vector<Double> data, String name, Vector<Integer> startMon, int monthNum, Vector<Integer> startYr){
		
		String[] mon = {"Dec ", "Jan ", "Feb ", "March ", "April ", "May ", "June ", "July ", "Aug ", "Sept ", "Oct ", "Nov "};
			
		if(bi!=null){
			summaryPanel.remove(picLabel);
		}
	
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i=0; i<data.size(); i++){
			
			dataset.setValue(data.elementAt(i), "Pesos", mon[startMon.firstElement()%12]+startYr.firstElement()+"");		
			//startMon++;
			startMon.removeElementAt(0);
			startYr.removeElementAt(0);
			// System.out.println(data.elementAt(i));
		}
		
		chart = ChartFactory.createBarChart3D(name,"Months", "Pesos", dataset, PlotOrientation.VERTICAL, false, true, false);
		
		CategoryPlot categoryPlot = chart.getCategoryPlot();
		BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
		br.setMaximumBarWidth(.20); // set maximum width to 35% of chart
		
		bi = chart.createBufferedImage(500,400);
		picLabel = new JLabel(new ImageIcon( bi ));
		
		picLabel.setBounds(50, 50, 500, 400);
		summaryPanel.add(picLabel);
		repaint();
		validate();
	
	}
	
	/**
	 * creates a pie chart and show it in the show summary panel
	 */
	private void createPieChart(Vector<Double> total, Vector<Double> inv, Vector<Double> misc, Vector<Double> salaries,String name){
		
		if(bi!=null){
			summaryPanel.remove(picLabel);
		}
		
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		
		double addTotal = 0;
		double addInv = 0;
		double addMisc = 0;
		double addSalaries = 0;
		
		for(int i=0; i<total.size(); i++){
			addTotal = addTotal+total.elementAt(i);
		}
		for(int i=0; i<inv.size(); i++){
			addInv = addInv+inv.elementAt(i);
		}
		for(int i=0; i<misc.size(); i++){
			addMisc = addMisc+misc.elementAt(i);
		}
		for(int i=0; i<salaries.size(); i++){
			addSalaries = addSalaries+salaries.elementAt(i);
		}
				
		for(int i=0; i<total.size(); i++){
			pieDataset.setValue("Inventory", new Double(addInv/addTotal));
			pieDataset.setValue("Miscellaneous", new Double(addMisc/addTotal));
			pieDataset.setValue("Salaries", new Double(addSalaries/addTotal));
		}
		
		chart = ChartFactory.createPieChart3D(name, pieDataset,true, true, false);
		
		
		bi = chart.createBufferedImage(500,400);
		picLabel = new JLabel(new ImageIcon( bi ));
		
		picLabel.setBounds(50, 50, 500, 400);
		summaryPanel.add(picLabel);
		repaint();
		validate();
	}

    /**
     * show summary
     */ 
	int startMonthInt = 0;
	int numberofMonths = 0;
	int startYearInt  = 0;
	private void summaryActionPerformed(ActionEvent evt){
    	
		try {
			String startMonth = summaryStartMonths.getSelectedItem().toString();
			startMonthInt = monthToInt(startMonth);
			String startYear = summaryStartYear.getText();
			startYearInt = Integer.parseInt(startYear);
			String endMonth = summaryEndMonths.getSelectedItem().toString();
			int endMonthInt = monthToInt(endMonth);
			String endYear = summaryEndYear.getText();
			int endYearInt = Integer.parseInt(endYear);
			
			int numberofYears = endYearInt - startYearInt;
			numberofMonths = ((endMonthInt - startMonthInt) + 1) + (12 * numberofYears);
			
			Vector<Double> totalIncome = new Vector<Double>();
			Vector<Double> finisherIncome = new Vector<Double>();
			Vector<Double> butcheredIncome = new Vector<Double>();
			Vector<Double> salaryExpenses = new Vector<Double>();
			Vector<Double> itemExpenses = new Vector<Double>();
			Vector<Double> micellaneousExpenses = new Vector<Double>();
			Vector<Double> totalExpenses = new Vector<Double>();
			Vector<Double> netIncome = new Vector<Double>();
			
			if(!checkDate(startMonthInt,1,startYearInt) || !checkDate(endMonthInt,1,endYearInt)){
				JOptionPane.showMessageDialog(null, "Input Date/s not Valid.", "Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!validateDate(startMonthInt,1,startYearInt) || !validateDate(endMonthInt,1,endYearInt)){
				JOptionPane.showMessageDialog(null, "Cannot add date after current date.", "Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (dateOrderCheck(new java.util.Date(startYearInt-1900 ,startMonthInt-1,1),
									new java.util.Date(endYearInt-1900,endMonthInt-1,1))){
				JOptionPane.showMessageDialog(null, "End Date must be after Start Date", "Date Error", JOptionPane.ERROR_MESSAGE);
			}
			else{
				if(summaryOption.getSelectedIndex() != 0)
				{
					exportButton1.setVisible(true);
				}
				else
				{
					exportButton1.setVisible(false);
					if(bi!=null){
						summaryPanel.remove(picLabel);
					}
					repaint();
					validate();
				}
				
				if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Total Expenses(Bar)")){
					sqlrs = sqlstat.executeQuery("SELECT S.to_month, SUM(S.amount) AS amount, S.to_year FROM salaries S WHERE S.to_year BETWEEN " + startYearInt + " AND " + endYearInt + " GROUP BY S.to_year, S.to_month;"); 
					
					Vector<Integer> smonths = new Vector<Integer>();
					Vector<Integer> syears = new Vector<Integer>();
					while (sqlrs.next()) {
						smonths.addElement(sqlrs.getInt("to_month"));
						salaryExpenses.addElement(sqlrs.getDouble("amount"));
						syears.addElement(sqlrs.getInt("to_year"));
					}    	
					
					sqlrs.close();
					
					sqlrs = sqlstat.executeQuery("SELECT I.month, SUM(I.total_amount) AS amount, I.year FROM inventoryexpenses I WHERE I.year BETWEEN "+ startYearInt + " AND "+ endYearInt + " GROUP BY I.year, I.month;");
					
					Vector<Integer> imonths = new Vector<Integer>();
					Vector<Integer> iyears = new Vector<Integer>();
					while (sqlrs.next()) {
						imonths.addElement(sqlrs.getInt("month"));
						itemExpenses.addElement(sqlrs.getDouble("amount"));
						iyears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();		
					
					sqlrs = sqlstat.executeQuery("SELECT M.month, SUM(M.amount) AS amount, M.year FROM micellaneousexpenses M WHERE M.year BETWEEN "+ startYearInt +" AND "+ endYearInt + " GROUP BY M.year, M.month;");
					
					
					Vector<Integer> mmonths = new Vector<Integer>();
					Vector<Integer> myears = new Vector<Integer>();
					while (sqlrs.next()) {
						mmonths.addElement(sqlrs.getInt("month"));
						micellaneousExpenses.addElement(sqlrs.getDouble("amount"));
						myears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					int monthctr = startMonthInt;
					int yearctr = startYearInt;
					double total = 0;
					while (yearctr <= endYearInt) {
						total = 0;
						months.addElement(monthctr);
						years.addElement(yearctr);
						
						if (!smonths.isEmpty()) {
							if (smonths.firstElement() == monthctr && syears.firstElement() == yearctr) {
								total = total + salaryExpenses.elementAt(0);
								smonths.removeElementAt(0);
								syears.removeElementAt(0);
								salaryExpenses.removeElementAt(0);
							}
						}
						if (!imonths.isEmpty()) {
							if (imonths.firstElement() == monthctr && iyears.firstElement() == yearctr) {
								total = total + itemExpenses.elementAt(0);
								imonths.removeElementAt(0);
								iyears.removeElementAt(0);
								itemExpenses.removeElementAt(0);
							}
						}
						if (!mmonths.isEmpty()) {
							if (mmonths.firstElement() == monthctr && myears.firstElement() == yearctr) {
								total = total + micellaneousExpenses.elementAt(0);
								mmonths.removeElementAt(0);
								myears.removeElementAt(0);
								micellaneousExpenses.removeElementAt(0);
							}
						}
								
						totalExpenses.addElement(total);
						if (monthctr == 12) {
							monthctr = 1;
							yearctr++;
						} else {
							monthctr++;
							if ((monthctr+1) > (endMonthInt+1) && yearctr == endYearInt) {
								yearctr++;
							}
						}
						
					}
					
					createBarChart(totalExpenses, "Total Expenses (Bar)", months, numberofMonths, years);
					
					
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Total Expenses(Pie)")){
					sqlrs = sqlstat.executeQuery("SELECT S.to_month, SUM(S.amount) AS amount, S.to_year FROM salaries S WHERE S.to_year BETWEEN " + startYearInt + " AND " + endYearInt + " GROUP BY S.to_year, S.to_month;"); 
					
					Vector<Integer> smonths = new Vector<Integer>();
					Vector<Integer> syears = new Vector<Integer>();
					while (sqlrs.next()) {
						smonths.addElement(sqlrs.getInt("to_month"));
						salaryExpenses.addElement(sqlrs.getDouble("amount"));
						syears.addElement(sqlrs.getInt("to_year"));
					}    	
					
					sqlrs.close();
					
					sqlrs = sqlstat.executeQuery("SELECT I.month, SUM(I.total_amount) AS amount, I.year FROM inventoryexpenses I WHERE I.year BETWEEN "+ startYearInt + " AND "+ endYearInt + " GROUP BY I.year, I.month;");
					
					Vector<Integer> imonths = new Vector<Integer>();
					Vector<Integer> iyears = new Vector<Integer>();
					while (sqlrs.next()) {
						imonths.addElement(sqlrs.getInt("month"));
						itemExpenses.addElement(sqlrs.getDouble("amount"));
						iyears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();		
					
					sqlrs = sqlstat.executeQuery("SELECT M.month, SUM(M.amount) AS amount, M.year FROM micellaneousexpenses M WHERE M.year BETWEEN "+ startYearInt +" AND "+ endYearInt + " GROUP BY M.year, M.month;");
					
					
					Vector<Integer> mmonths = new Vector<Integer>();
					Vector<Integer> myears = new Vector<Integer>();
					while (sqlrs.next()) {
						mmonths.addElement(sqlrs.getInt("month"));
						micellaneousExpenses.addElement(sqlrs.getDouble("amount"));
						myears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					int monthctr = startMonthInt;
					int yearctr = startYearInt;
					double total = 0;
					while (yearctr <= endYearInt) {
						total = 0;
						months.addElement(monthctr);
						years.addElement(yearctr);
						
						if (!smonths.isEmpty()) {
							if (smonths.firstElement() == monthctr && syears.firstElement() == yearctr) {
								total = total + salaryExpenses.elementAt(0);
								smonths.removeElementAt(0);
								syears.removeElementAt(0);
								salaryExpenses.removeElementAt(0);
							}
						}
						if (!imonths.isEmpty()) {
							if (imonths.firstElement() == monthctr && iyears.firstElement() == yearctr) {
								total = total + itemExpenses.elementAt(0);
								imonths.removeElementAt(0);
								iyears.removeElementAt(0);
								itemExpenses.removeElementAt(0);
							}
						}
						if (!mmonths.isEmpty()) {
							if (mmonths.firstElement() == monthctr && myears.firstElement() == yearctr) {
								total = total + micellaneousExpenses.elementAt(0);
								mmonths.removeElementAt(0);
								myears.removeElementAt(0);
								micellaneousExpenses.removeElementAt(0);
							}
						}
								
						totalExpenses.addElement(total);
						if (monthctr == 12) {
							monthctr = 1;
							yearctr++;
						} else {
							monthctr++;
							if ((monthctr+1) > (endMonthInt+1) && yearctr == endYearInt) {
								yearctr++;
							}
						}
						
					}
					
					sqlrs = sqlstat.executeQuery("SELECT S.to_month, SUM(S.amount) AS amount, S.to_year FROM salaries S WHERE S.to_year BETWEEN " + startYearInt + " AND " + endYearInt + " GROUP BY S.to_year, S.to_month;"); 
					
					smonths = new Vector<Integer>();
					syears = new Vector<Integer>();
					while (sqlrs.next()) {
						smonths.addElement(sqlrs.getInt("to_month"));
						salaryExpenses.addElement(sqlrs.getDouble("amount"));
						syears.addElement(sqlrs.getInt("to_year"));
					}    	
					
					sqlrs.close();
					
					sqlrs = sqlstat.executeQuery("SELECT I.month, SUM(I.total_amount) AS amount, I.year FROM inventoryexpenses I WHERE I.year BETWEEN "+ startYearInt + " AND "+ endYearInt + " GROUP BY I.year, I.month;");
					
					imonths = new Vector<Integer>();
					iyears = new Vector<Integer>();
					while (sqlrs.next()) {
						imonths.addElement(sqlrs.getInt("month"));
						itemExpenses.addElement(sqlrs.getDouble("amount"));
						iyears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();		
					
					sqlrs = sqlstat.executeQuery("SELECT M.month, SUM(M.amount) AS amount, M.year FROM micellaneousexpenses M WHERE M.year BETWEEN "+ startYearInt +" AND "+ endYearInt + " GROUP BY M.year, M.month;");
					
					
					mmonths = new Vector<Integer>();
					myears = new Vector<Integer>();
					while (sqlrs.next()) {
						mmonths.addElement(sqlrs.getInt("month"));
						micellaneousExpenses.addElement(sqlrs.getDouble("amount"));
						myears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();
					
					createPieChart(totalExpenses, itemExpenses, micellaneousExpenses, salaryExpenses, "Total Expenses (Pie)");
				
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Salaries Expense")){
				
					sqlrs = sqlstat.executeQuery("SELECT S.to_month, SUM(S.amount) AS amount, S.to_year FROM salaries S WHERE S.to_year BETWEEN " + startYearInt + " AND " + endYearInt + " GROUP BY S.to_year, S.to_month;"); 
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					while (sqlrs.next()) {
						months.addElement(sqlrs.getInt("to_month"));
						salaryExpenses.addElement(sqlrs.getDouble("amount"));
						years.addElement(sqlrs.getInt("to_year"));
					}    	
					
					sqlrs.close();
					
					createBarChart(salaryExpenses, "Salary Expenses", months, numberofMonths, years);
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Inventory Expenses")){
					sqlrs = sqlstat.executeQuery("SELECT I.month, SUM(I.total_amount) AS amount, I.year FROM inventoryexpenses I WHERE I.year BETWEEN "+ startYearInt + " AND "+ endYearInt + " GROUP BY I.year, I.month;");
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					while (sqlrs.next()) {
						months.addElement(sqlrs.getInt("month"));
						itemExpenses.addElement(sqlrs.getDouble("amount"));
						years.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();
					
					createBarChart(itemExpenses, "Inventory Expenses", months, numberofMonths, years);
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Misc. Expense")){
					sqlrs = sqlstat.executeQuery("SELECT M.month, SUM(M.amount) AS amount, M.year FROM micellaneousexpenses M WHERE M.year BETWEEN "+ startYearInt +" AND "+ endYearInt + " GROUP BY M.year, M.month;");
					
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					while (sqlrs.next()) {
						months.addElement(sqlrs.getInt("month"));
						micellaneousExpenses.addElement(sqlrs.getDouble("amount"));
						years.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();
					
					createBarChart(micellaneousExpenses, "Miscellaneous Expenses", months, numberofMonths, years);
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Total Sales")){
					sqlrs = sqlstat.executeQuery("SELECT P.sold_month, SUM(P.amount) AS amount, P.sold_year FROM soldpigs P WHERE P.sold_year BETWEEN "+ startYearInt +" AND "+ endYearInt 
						+" GROUP BY P.sold_year, P.sold_month;");
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					while (sqlrs.next()) {
						months.addElement(sqlrs.getInt("sold_month"));
						totalIncome.addElement(sqlrs.getDouble("amount"));
						years.addElement(sqlrs.getInt("sold_year"));
					}    	
					sqlrs.close();
					
					createBarChart(totalIncome, "Total Sales", months, numberofMonths, years);
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Finisher Sales")){
					sqlrs = sqlstat.executeQuery("SELECT P.sold_month, SUM(P.amount) AS amount, P.sold_year FROM soldpigs P WHERE P.sold_year BETWEEN "+ startYearInt +" AND "+ endYearInt +" AND P.selling_id = 1 GROUP BY P.sold_year, P.sold_month;");
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					while (sqlrs.next()) {
						months.addElement(sqlrs.getInt("sold_month"));
						finisherIncome.addElement(sqlrs.getDouble("amount"));
						years.addElement(sqlrs.getInt("sold_year"));
					}    	
					sqlrs.close();
					
					createBarChart(finisherIncome, "Finisher Sales", months, numberofMonths, years);
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Butchered Sales")){
					sqlrs = sqlstat.executeQuery("SELECT P.sold_month, SUM(P.amount) AS amount, P.sold_year FROM soldpigs P WHERE P.sold_year BETWEEN "+ startYearInt +" AND "+ endYearInt +" AND P.selling_id = 2 GROUP BY P.sold_year, P.sold_month;");
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					while (sqlrs.next()) {
						months.addElement(sqlrs.getInt("sold_month"));
						butcheredIncome.addElement(sqlrs.getDouble("amount"));
						years.addElement(sqlrs.getInt("sold_year"));
					}    	
					sqlrs.close();
					
					createBarChart(butcheredIncome, "Butchered Sales", months, numberofMonths, years);
				}
				else if(summaryOption.getSelectedItem().toString().equalsIgnoreCase("Net Income")){
					sqlrs = sqlstat.executeQuery("SELECT P.sold_month, SUM(P.amount) AS amount, P.sold_year FROM soldpigs P WHERE P.sold_year BETWEEN "+ startYearInt +" AND "+ endYearInt 
							+" GROUP BY P.sold_year, P.sold_month;");
						
					Vector<Integer> incomemonths = new Vector<Integer>();
					Vector<Integer> incomeyears = new Vector<Integer>();
					while (sqlrs.next()) {
						incomemonths.addElement(sqlrs.getInt("sold_month"));
						totalIncome.addElement(sqlrs.getDouble("amount"));
						incomeyears.addElement(sqlrs.getInt("sold_year"));
					}    	
					sqlrs.close();
					
					sqlrs = sqlstat.executeQuery("SELECT S.to_month, SUM(S.amount) AS amount, S.to_year FROM salaries S WHERE S.to_year BETWEEN " + startYearInt + " AND " + endYearInt + " GROUP BY S.to_year, S.to_month;"); 
					
					Vector<Integer> smonths = new Vector<Integer>();
					Vector<Integer> syears = new Vector<Integer>();
					while (sqlrs.next()) {
						smonths.addElement(sqlrs.getInt("to_month"));
						salaryExpenses.addElement(sqlrs.getDouble("amount"));
						syears.addElement(sqlrs.getInt("to_year"));
					}    	
					
					sqlrs.close();
					
					sqlrs = sqlstat.executeQuery("SELECT I.month, SUM(I.total_amount) AS amount, I.year FROM inventoryexpenses I WHERE I.year BETWEEN "+ startYearInt + " AND "+ endYearInt + " GROUP BY I.year, I.month;");
					
					Vector<Integer> imonths = new Vector<Integer>();
					Vector<Integer> iyears = new Vector<Integer>();
					while (sqlrs.next()) {
						imonths.addElement(sqlrs.getInt("month"));
						itemExpenses.addElement(sqlrs.getDouble("amount"));
						iyears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();		
					
					sqlrs = sqlstat.executeQuery("SELECT M.month, SUM(M.amount) AS amount, M.year FROM micellaneousexpenses M WHERE M.year BETWEEN "+ startYearInt +" AND "+ endYearInt + " GROUP BY M.year, M.month;");
					
					
					Vector<Integer> mmonths = new Vector<Integer>();
					Vector<Integer> myears = new Vector<Integer>();
					while (sqlrs.next()) {
						mmonths.addElement(sqlrs.getInt("month"));
						micellaneousExpenses.addElement(sqlrs.getDouble("amount"));
						myears.addElement(sqlrs.getInt("year"));
					}    	
					sqlrs.close();
					
					Vector<Integer> months = new Vector<Integer>();
					Vector<Integer> years = new Vector<Integer>();
					int monthctr = startMonthInt;
					int yearctr = startYearInt;
					double total = 0;
					while (yearctr <= endYearInt) {
						total = 0;
						months.addElement(monthctr);
						years.addElement(yearctr);
						
						if (!smonths.isEmpty()) {
							if (smonths.firstElement() == monthctr && syears.firstElement() == yearctr) {
								total = total - salaryExpenses.elementAt(0);
								smonths.removeElementAt(0);
								syears.removeElementAt(0);
								salaryExpenses.removeElementAt(0);
							}
						}
						if (!imonths.isEmpty()) {
							if (imonths.firstElement() == monthctr && iyears.firstElement() == yearctr) {
								total = total - itemExpenses.elementAt(0);
								imonths.removeElementAt(0);
								iyears.removeElementAt(0);
								itemExpenses.removeElementAt(0);
							}
						}
						if (!mmonths.isEmpty()) {
							if (mmonths.firstElement() == monthctr && myears.firstElement() == yearctr) {
								total = total - micellaneousExpenses.elementAt(0);
								mmonths.removeElementAt(0);
								myears.removeElementAt(0);
								micellaneousExpenses.removeElementAt(0);
							}
						}
						if (!incomemonths.isEmpty()) {
							if (incomemonths.firstElement() == monthctr && incomeyears.firstElement() == yearctr) {
								total = total + totalIncome.elementAt(0);
								incomemonths.removeElementAt(0);
								incomeyears.removeElementAt(0);
								totalIncome.removeElementAt(0);
							}
						}
								
						totalExpenses.addElement(total);
						if (monthctr == 12) {
							monthctr = 1;
							yearctr++;
						} else {
							monthctr++;
							if ((monthctr+1) > (endMonthInt+1) && yearctr == endYearInt) {
								yearctr++;
							}
						}
						
					}
					
					
					createBarChart(totalExpenses, "Net Income", months, numberofMonths, years);
				
				}
			}
			
	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + "\n the record already exists", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
    
    //Clear buttons
    /**
     * When the breedingPanel wants to be cleared
     */
    private void breedingClearActionPerformed(ActionEvent evt) {
    	breedingBreedingDateText.setText("");
        breedingSowText.setText("");
        breedingParityText.setText("");
        breedingBoarText.setText("");
        breedingFarrowingText.setText("");
        breedingNotesText.setText("");
    }
    
    /**
     * When the farrowingPanel wants to be cleared
     */
    private void farrowingClearActionPerformed(ActionEvent evt) {
        farrowingDateText.setText("");
        farrowingSowText.setText("");
        farrowingBoarText.setText("");
        farrowingLiveText.setText("");
        farrowingEarText.setText("");
        farrowingStillText.setText("");
        farrowingMummifiedText.setText("");
        farrowingAbnormalText.setText("");
        farrowingNotesText.setText("");
    }
    
    /**
     * When the mortalityPanel wants to be cleared
     */
    private void mortalityClearActionPerformed(ActionEvent evt) {
        mortalityDateText.setText("");
        mortalityEarText.setText("");
        mortalityBuildingText.setText("");
        mortalityCauseText.setText("");
    }
    
    /**
     * When the inventoryPanel wants to be cleared
     */
    private void inventoryClearActionPerformed(ActionEvent evt) {
        inventoryDateText.setText("");
        inventoryReceiptText.setText("");
        inventoryQuantityText.setText(""); 
        inventoryAmountText.setText("");  
    }
    
    /**
     * When the finisherPanel wants to be cleared
     */
    private void finisherClearActionPerformed(ActionEvent evt) {
    	finisherDateText.setText("");
        finisherHeadsText.setText("");
        finisherWeightText.setText("");
        finisherKilosText.setText("");    
        finisherPriceText.setText("");   
        finisherAmountText.setText("");   
    }
    
    /**
     * When the butcheredPanel wants to be cleared
     */
    private void butcheredClearActionPerformed(ActionEvent evt) {
    	 butcheredButcherText.setText("");
         butcheredEarText.setText("");
         butcheredWeight1Text.setText("");
         butcheredSoldText.setText("");
         butcheredPriceText.setText("");
         butcheredWeight2Text.setText("");
         butcheredAmountText.setText("");  
    }
    
    /**
     * When the salariesPanel wants to be cleared
     */
    private void salariesClearActionPerformed(ActionEvent evt) {
    	salariesDate1Text.setText("");
        salariesDate2Text.setText("");  
        salariesAmountText.setText(""); 
    }
    
    /**
     * When the micellaneousPanel wants to be cleared
     */
    private void micellaneousClearActionPerformed(ActionEvent evt) {
    	micellaneousDateText.setText("");
        micellaneousAmountText.setText("");
        micellaneousNotesText.setText("");
    }

    /**
     * When the addPanel wants to be cleared
     */
    private void addClearActionPerformed(ActionEvent evt) {
    	addInputText.setText("");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    	try {
	    	Class.forName("org.sqlite.JDBC");
	    	sqlconn = DriverManager.getConnection("jdbc:sqlite:fd.db");
			sqlstat = sqlconn.createStatement();
					
			prepBreeding = sqlconn.prepareStatement("insert into breeding values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			prepBuyers = sqlconn.prepareStatement("insert into buyers values (?, ?, ?);");
			prepEmployees = sqlconn.prepareStatement("insert into employees values (?, ?, ?);");
			prepFarrowing = sqlconn.prepareStatement("insert into farrowing values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			prepInventoryExpenseDetails = sqlconn.prepareStatement("insert into inventoryexpensedetails values (?, ?, ?, ?);");
			prepInventoryExpenses = sqlconn.prepareStatement("insert into inventoryexpenses values (?, ?, ?, ?, ?);");
			prepItems = sqlconn.prepareStatement("insert into items values (?, ?);");
			prepMicellaneousExpenses = sqlconn.prepareStatement("insert into micellaneousexpenses values (?, ?, ?, ?, ?, ?);");
			prepPigs = sqlconn.prepareStatement("insert into pigs values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");		
			prepSalaries = sqlconn.prepareStatement("insert into salaries values (?, ?, ?, ?, ?, ?, ?, ?);");
			prepSoldAliveDetails = sqlconn.prepareStatement("insert into soldalivedetails values (?, ?, ?, ?, ?, ?, ?);");
			prepSoldButcheredDetails = sqlconn.prepareStatement("insert into soldbutchereddetails values (?, ?, ?, ?, ?);");
			prepSoldPigs = sqlconn.prepareStatement("insert into soldpigs values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
    	} catch (Exception e) {
			//e.printStackTrace();
		}
		
    	
    	
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        EventQueue.invokeLater(new Runnable() {

            public void run() {
				Home h = new Home();
				h.setSize(800,600);
                h.setVisible(true);
				h.remove(h.inventoryPanel);
				h.validate();
				h.repaint();
            }
        });
        
        try {
        	
        } catch (Exception e) {
        	//e.printStackTrace();
        }
    }
}
