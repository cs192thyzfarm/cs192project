//run with java -cp .:sqlitejdbc-v056.jar Home

import java.util.Calendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author
 */
public class Home extends JFrame implements ActionListener{
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
	
	private JButton nav12;
    
    //Panels declaration
    /**
     * Panel for the buttons on the left side
     */
    private JPanel navButtonsPanel;
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
    private JLabel farrowingLiveLabel;
    private JLabel farrowingEarLabel;
    private JLabel farrowingStillLabel;
    private JLabel farrowingMummifiedLabel;
    private JLabel farrowingAbnormalLabel;
    private JLabel farrowingNotesLabel;
    private JTextField farrowingDateText;
    private JTextField farrowingSowText;
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
	
	private boolean checkDate(int month,int day,int year) {
		boolean isLeap = false;	
		if(year % 100 == 0 && year % 400 != 0) {	
			isLeap = false;
		} else if(year % 4 == 0) {
			isLeap = true;
		}
		if(month == 2) {
			if((!isLeap) && day >= 29) {
				return false;
			}
		}	
		if(month == 4 || month == 6 || month == 9 || month == 11) {
			if(day==31) {
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
			}
			else if(addWhatBox.getSelectedIndex() == 4)
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
    	status = 0;
        initComponents();
        setResizable(false);
        this.setTitle("Farm Database");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
	
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
        farrowingLiveLabel = new JLabel();
        farrowingEarLabel = new JLabel();
        farrowingStillLabel = new JLabel();
        farrowingMummifiedLabel = new JLabel();
        farrowingAbnormalLabel = new JLabel();
        farrowingNotesLabel = new JLabel();       
        farrowingDateText = new JTextField();
        farrowingSowText = new JTextField();
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
		summaryEndYear = new JTextField(year+"");
       

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
        String[] types = {"Select one...","employee", "buyer", "item","pig"};
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
        titleLabel.setText("                                             Home");
        navLabel.setFont(new Font("Verdana", 0, 12)); // NOI18N
        navLabel.setText("");
		correctNavLabel.setBounds(94,0,100,25);

        //Adds ActionListeners to left side buttons
        nav1.setText("Home");
        nav1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav1ActionPerformed(evt); 		
        	}
        });
        nav2.setText("Add Breeding Items");
        nav2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav2ActionPerformed(evt); 		
        	}
        });
        nav3.setText("Add Farrowing Items");
        nav3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav3ActionPerformed(evt); 		
        	}
        });
        nav4.setText("Add Mortality Items");
        nav4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav4ActionPerformed(evt);
            }
        });
        nav5.setText("Add Inventory Items");
        nav5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav5ActionPerformed(evt);
            }
        });
        nav6.setText("Add Finisher Sales Items");
        nav6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav6ActionPerformed(evt);
            }
        });
        nav7.setText("Add Butchered Sales Items");
        nav7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav7ActionPerformed(evt); 		
        	}
        });
        nav8.setText("Add Salaries Expenses Items");
        nav8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav8ActionPerformed(evt);
            }
        });
        nav9.setText("Add Miscellaneous Expense Items");
        nav9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nav9ActionPerformed(evt);
            }
        });
        nav10.setText("Show Summary");
        nav10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav10ActionPerformed(evt); 		
        	}
        });
        nav11.setText("Add Employees, Buyers, Items or Pigs");
        nav11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav11ActionPerformed(evt);
        	}
        });
		
		nav12.setText("History");
        nav12.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		nav12ActionPerformed(evt);
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
    
    /**
     * Initializes the components of the breedingPanel
     */
    private void breedingComponents() {
        breedingDateLabel.setText("Breeding Date");
        breedingSowLabel.setText("Sow#");
        breedingParityLabel.setText("Parity");
        breedingBoarLabel.setText("Boar#");
        breedingFarrowingLabel.setText("Farrowing Date");
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
		
		// System.out.println(breedingFarrowingText.getLocation().getX()+" "+breedingFarrowingText.getLocation().getY());
		breedingBreedingDateText.setVisible(false);
		breedingFarrowingText.setVisible(false);
		
		breedingBreedingMonths.setBounds(253,21,100,25);
		breedingBreedingDays.setBounds(361,21,50,25);
		breedingBreedingYear.setBounds(419,21,50,25);
		
		breedingFarrowingMonths.setBounds(253,193,100,25);
		breedingFarrowingDays.setBounds(361,193,50,25);
		breedingFarrowingYear.setBounds(419,193,50,25);
		
		breedingPanel.add(breedingFarrowingMonths);
		breedingPanel.add(breedingFarrowingDays);
		breedingPanel.add(breedingFarrowingYear);
		
		breedingPanel.add(breedingBreedingMonths);
		breedingPanel.add(breedingBreedingDays);
		breedingPanel.add(breedingBreedingYear);
		
        // pack();
    }
    
    /**
     * Initializes the components of the farrowingPanel
     */
    private void farrowingComponents() {
    	farrowingDateLabel.setText("Date Farrowed");
        farrowingSowLabel.setText("Sow#");
        farrowingLiveLabel.setText("Live born#");
        farrowingEarLabel.setText("Ear# Assigned");
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
		
		
		// System.out.println(farrowingDateText.getLocation().getX()+" "+farrowingDateText.getLocation().getY());
		farrowingDateText.setVisible(false);
		
		farrowingMonths.setBounds(211,16,100,25);
		farrowingDays.setBounds(314,16,50,25);
		farrowingYear.setBounds(367,16,75,25);
		
		farrowingPanel.add(farrowingMonths);
		farrowingPanel.add(farrowingDays);
		farrowingPanel.add(farrowingYear);
        // pack();
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
    		e.printStackTrace();
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
                            .addComponent(mortalityCauseLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mortalityBuildingLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mortalityEarLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mortalityDateLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(67, 67, 67))
                    .addGroup(mortalityPanelLayout.createSequentialGroup()
                        .addComponent(mortalityEmployeeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(mortalityCauseText, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addComponent(mortalityEmployeeBox)
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
                    .addComponent(mortalityEmployeeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mortalityEmployeeLabel))
                .addGap(18, 18, 18)
                .addGroup(mortalityPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(mortalityCauseLabel)
                    .addComponent(mortalityCauseText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
		
		// System.out.println(mortalityDateText.getLocation().getX()+" "+mortalityDateText.getLocation().getY());
		mortalityDateText.setVisible(false);
		
		mortalityMonths.setBounds(227,21,100,25);
		mortalityDays.setBounds(335,21,50,25);
		mortalityYear.setBounds(393,21,68,25);
		
		mortalityPanel.add(mortalityMonths);
		mortalityPanel.add(mortalityDays);
		mortalityPanel.add(mortalityYear);

        // pack();
    }
    
    /**
     * Initializes the components of the inventoryPanel
     */
    private void inventoryComponents() {
	    inventoryDateLabel.setText("Date");
	    inventoryReceiptLabel.setText("Receipt#");
	    inventoryItemLabel.setText("Item Type");
	    inventoryQuantityLabel.setText("Quantity");
	    inventoryAmountLabel.setText("Amount");
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
			e.printStackTrace();
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
		
		// System.out.println(inventoryDateText.getLocation().getX()+" "+inventoryDateText.getLocation().getY());
		inventoryDateText.setVisible(false);
		
		inventoryMonths.setBounds(227,21,100,25);
		inventoryDays.setBounds(335,21,50,25);
		inventoryYear.setBounds(393,21,68,25);
		
		inventoryPanel.add(inventoryMonths);
		inventoryPanel.add(inventoryDays);
		inventoryPanel.add(inventoryYear);

	    // pack();
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
    		e.printStackTrace();
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
                    .addComponent(finisherBuyerBox, GroupLayout.Alignment.LEADING)
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
                    .addComponent(finisherBuyerBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(finisherBuyerLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
		
		// System.out.println(finisherDateText.getLocation().getX()+" "+finisherDateText.getLocation().getY());
		finisherDateText.setVisible(false);
		
		finisherMonths.setBounds(276,16,100,25);
		finisherDays.setBounds(384,16,50,25);
		finisherYear.setBounds(442,16,50,25);
		
		finisherPanel.add(finisherMonths);
		finisherPanel.add(finisherDays);
		finisherPanel.add(finisherYear);
		
        // pack();
    }
    
    /**
     * Initializes the components of the butcheredPanel
     */
    private void butcheredComponents() {
    	butcheredButcherLabel.setText("Butcher Date");
        butcheredEarLabel.setText("Ear#");
        butcheredWeight1Label.setText("Total Weight");
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
    		e.printStackTrace();
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
		// System.out.println(butcheredButcherText.getLocation().getX()+" "+butcheredButcherText.getLocation().getY());
		butcheredButcherText.setVisible(false);
		
		butcheredButcherMonths.setBounds(231,21,100,25);
		butcheredButcherDays.setBounds(339,21,50,25);
		butcheredButcherYear.setBounds(397,21,50,25);
		
		butcheredPanel.add(butcheredButcherMonths);
		butcheredPanel.add(butcheredButcherDays);
		butcheredPanel.add(butcheredButcherYear);
		
		// System.out.println(butcheredSoldText.getLocation().getX()+" "+butcheredSoldText.getLocation().getY());
		butcheredSoldText.setVisible(false);
		
		butcheredSoldMonths.setBounds(231,191,100,25);
		butcheredSoldDays.setBounds(339,191,50,25);
		butcheredSoldYear.setBounds(397,191,50,25);
		
		butcheredPanel.add(butcheredSoldMonths);
		butcheredPanel.add(butcheredSoldDays);
		butcheredPanel.add(butcheredSoldYear);
        // pack();
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
     		e.printStackTrace();
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
		 
		// System.out.println(salariesDate1Text.getLocation().getX()+" "+salariesDate1Text.getLocation().getY());
		salariesDate1Text.setVisible(false);
		
		salariesStartMonths.setBounds(193,21,100,25);
		salariesStartDays.setBounds(301,21,50,25);
		salariesStartYear.setBounds(359,21,50,25);
		
		salariesPanel.add(salariesStartMonths);
		salariesPanel.add(salariesStartDays);
		salariesPanel.add(salariesStartYear);
		
		// System.out.println(salariesDate2Text.getLocation().getX()+" "+salariesDate2Text.getLocation().getY());
		salariesDate2Text.setVisible(false);
		
		salariesEndMonths.setBounds(193,55 ,100,25);
		salariesEndDays.setBounds(301,55,50,25);
		salariesEndYear.setBounds(359,55,50,25);
		
		salariesPanel.add(salariesEndMonths);
		salariesPanel.add(salariesEndDays);
		salariesPanel.add(salariesEndYear);
		// pack();
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
    		e.printStackTrace();
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

		// System.out.println(micellaneousDateText.getLocation().getX()+" "+micellaneousDateText.getLocation().getY());
		micellaneousDateText.setVisible(false);
		
		micellaneousMonths.setBounds(207,21,100,25);
		micellaneousDays.setBounds(315,21,50,25);
		micellaneousYear.setBounds(373,21,50,25);
		
		// System.out.println(micellaneousPanel.getSize().getWidth()+" "+micellaneousPanel.getSize().getHeight());
		
		micellaneousPanel.add(micellaneousMonths);
		micellaneousPanel.add(micellaneousDays);
		micellaneousPanel.add(micellaneousYear);
		
        // pack();
    }
    
    /**
     * Initializes the components of the summaryPanel
     */
    private void summaryComponents() {
		javax.swing.GroupLayout summaryPanelLayout = new javax.swing.GroupLayout(summaryPanel);
        summaryPanel.setLayout(summaryPanelLayout);
        summaryPanelLayout.setHorizontalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );
        summaryPanelLayout.setVerticalGroup(
            summaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
		JLabel startLabel = new JLabel("Start Date");
		summaryStartMonths.setBounds(50,21,100,25);
		summaryStartYear.setBounds(160,21,50,25);
		startLabel.setBounds(120,0,100,21);
		
		summaryPanel.add(summaryStartMonths);
		summaryPanel.add(summaryStartYear);
		
		JLabel endLabel = new JLabel("End Date");
		summaryEndMonths.setBounds(220,21 ,100,25);
		summaryEndYear.setBounds(330,21,50,25);
		endLabel.setBounds(290,0,100,21);
		
		summaryPanel.add(summaryEndMonths);
		summaryPanel.add(summaryEndYear);
		
		summaryPanel.add(startLabel);
		summaryPanel.add(endLabel);
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
			
		// Dimension d = addInputText.getSize(null);
		// System.out.println(addNameLabel.getLocation().getX());
		
		
		
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
        // pack();
    }

	private void historyComponents()
	{
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
	}
    
    /**
     * This method removes the present panel on the right
     */
    private void clearPanels() {
    	if (status == 2) {
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
    	status = 1;
    	titleLabel.setText("                                             Home");
        validate();
        repaint();
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
    }
    /**
     * When the add inventory item is pressed
     */
    private void nav5ActionPerformed(ActionEvent evt) {
    	clearPanels();    
    	this.inventoryPanel.removeAll();
    	inventoryComponents();
    	status = 5;
        titleLabel.setText("                              Add Inventory Item");
        validate();
        repaint();    	
    }
    /**
     * When the add finisher sales item is pressed
     */
    private void nav6ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.finisherPanel.removeAll();
    	finisherComponents();
    	status = 6;
    	titleLabel.setText("                            Add Finisher Sales Item");
        validate();
        repaint();    
    }
    /**
     * When the add butchered sales item is pressed
     */
    private void nav7ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.butcheredPanel.removeAll();
    	butcheredComponents();
    	status = 7;
    	titleLabel.setText("                            Add Butchered Sales Item");
        validate();
        repaint();    
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
    }
    /**
     * When the add miscellaneous item is pressed
     */
    private void nav9ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.salariesPanel.removeAll();
    	micellaneousComponents();
    	status = 9;
    	titleLabel.setText("                       Add Miscellaneous Expense Item");
        validate();
        repaint();    
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
    }
	
	private void nav12ActionPerformed(ActionEvent evt) {
    	clearPanels();
    	this.addPanel.removeAll();
    	historyComponents();
    	status = 12;
    	titleLabel.setText("                                           History");
        validate();
        repaint();    
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
	 * gets the employee_id of the specified last name and first name from the employees table
	 */
	private int getEmpoyeeId(String name) throws SQLException {
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
	 * gets the item id of the specified item from the items table
	 */
	private int getItemId(String name) throws SQLException {
		sqlrs = sqlstat.executeQuery("SELECT item_id FROM items WHERE item = \"" + name + "\";");
		int id = sqlrs.getInt(1);
		sqlrs.close();
		return id;
	}
	
	/**
	 * removes the row of the pig with the specified earno from the pigs table
	 */
	private void removePigRow(int earno) throws SQLException{
		prepPigs = sqlconn.prepareStatement("DELETE FROM pigs WHERE ear_no = ?;");
		prepPigs.setInt(1, earno);
		prepPigs.executeUpdate();
		prepPigs = sqlconn.prepareStatement("insert into pigs values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
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
						prepBreeding.setInt(6, Integer.parseInt(parity));
						prepBreeding.setInt(7, monthToInt(farrowingmonth));
						prepBreeding.setInt(8, Integer.parseInt(farrowingday));
						prepBreeding.setInt(9, Integer.parseInt(farrowingyear));
						prepBreeding.setString(10, notes);
			
						if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
							prepBreeding.addBatch();
							sqlconn.setAutoCommit(false);
							prepBreeding.executeBatch();
							sqlconn.setAutoCommit(true);
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
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * When a farrowing item is submitted
     */
    private void farrowingSubmitActionPerformed(ActionEvent evt) {
    	try {
			String sow = farrowingSowText.getText();			
			if (ifPigExists(Integer.parseInt(sow)) && isASow(Integer.parseInt(sow))) {
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
				
				if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
					prepFarrowing.addBatch();
					sqlconn.setAutoCommit(false);
					prepFarrowing.executeBatch();
					sqlconn.setAutoCommit(true);
				}
			} else {
				if (isASow(Integer.parseInt(sow))) {
					JOptionPane.showMessageDialog(null, "Sow number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "The ear number entered is not a sow", "Wrong sow number", JOptionPane.ERROR_MESSAGE);					
				}
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
				prepPigs.setInt(1, Integer.parseInt(pig));
				prepPigs.setInt(2, getPigDetail(pigno, 2));
				int pigid = getPigDetail(pigno, 2);
				prepPigs.setInt(3, getPigDetail(pigno, 3));
				int monthadded = getPigDetail(pigno, 3);
				prepPigs.setInt(4, getPigDetail(pigno, 4));
				int dayadded = getPigDetail(pigno, 4);
				prepPigs.setInt(5, getPigDetail(pigno, 5));
				int yearadded = getPigDetail(pigno, 5);
				
				if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
					removePigRow(pigno);
					prepPigs.setInt(1, Integer.parseInt(pig));
					prepPigs.setInt(2, pigid);
					prepPigs.setInt(3, monthadded);
					prepPigs.setInt(4, dayadded);
					prepPigs.setInt(5, yearadded);
					prepPigs.setInt(6, Integer.parseInt(buildingno));
					prepPigs.setInt(7, getEmpoyeeId(employee));
					prepPigs.setInt(8, monthToInt(mortalitymonth));
					prepPigs.setInt(9, Integer.parseInt(mortalityday));
					prepPigs.setInt(10, Integer.parseInt(mortalityyear));
					prepPigs.setString(11, cause);
					
					prepPigs.addBatch();
					sqlconn.setAutoCommit(false);
					prepPigs.executeBatch();
					sqlconn.setAutoCommit(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ear number is not in the database. Add it first.", "Missing Ear number", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * When a inventory item is submitted
     */
    private void inventorySubmitActionPerformed(ActionEvent evt) {
    	//fix GUI first implement new add Recieptno. option??
    	//make receipt no. a JComboBox
    	/**
			String inventorymonth = inventoryMonths.getSelectedItem().toString();
			String inventoryday = inventoryDays.getSelectedItem().toString();
			String inventoryyear = inventoryYear.getText();
			String recieptno = inventoryReceiptText.getText();
			String item = inventoryItemBox.getSelectedItem().toString();
			String quantity = inventoryQuantityText.getText();
			String amount = inventoryAmountText.getText();
		*/
    }
    
    /**
     * When a finisher item is submitted
     */
    private void finisherSubmitActionPerformed(ActionEvent evt) {
    	//input into two different tables (to reduce redundancy)
    	//use both this.prepSoldPigs this.prepSoldAliveDetails
    	/**
    		String sadbuyer = finisherBuyerBox.getSelectedItem().toString();
			String sadmonth = breedingBreedingMonths.getSelectedItem().toString();
			String sadday = breedingBreedingDays.getSelectedItem().toString();
			String sadyear = breedingBreedingYear.getText();
			String sadnoofheads = breedingSowText.getText();
			String sadkilosless = breedingParityText.getText();
			String sadearnossold = breedingBoarText.getText();
		*/
    }
    
    /**
     * When a butchered item is submitted
     */
    private void butcheredSubmitActionPerformed(ActionEvent evt) {
    	//input into two different tables (to reduce redundancy)
    	//use both this.prepSoldPigs this.prepSoldButcheredDetails
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
			
			if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
				prepSalaries.setInt(1, getEmpoyeeId(employee));
				prepSalaries.setInt(2, Integer.parseInt(amount));
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
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
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
			
			if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
				prepMicellaneousExpenses.setInt(1, monthToInt(month));
				prepMicellaneousExpenses.setInt(2, Integer.parseInt(day));
				prepMicellaneousExpenses.setInt(3, Integer.parseInt(year));
				prepMicellaneousExpenses.setInt(4, getItemId(item));
				prepMicellaneousExpenses.setInt(5, Integer.parseInt(amount));
				prepMicellaneousExpenses.setString(6, notes);
				
				prepMicellaneousExpenses.addBatch();
				sqlconn.setAutoCommit(false);
				prepMicellaneousExpenses.executeBatch();
				sqlconn.setAutoCommit(true);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
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
					if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
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
					if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
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
				prepItems.setString(2, addInputText.getText());
				if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
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
				if (JOptionPane.showConfirmDialog(null, "are you sure you want to add this entry?") == 0){
					prepPigs.addBatch();
					sqlconn.setAutoCommit(false);
					prepPigs.executeBatch();
					sqlconn.setAutoCommit(true);
				}	
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Re-check the required inputs", "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
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
			
			//SQL statements
			//testing part
			sqlstat.executeUpdate("drop table if exists breeding;");
			sqlstat.executeUpdate("drop table if exists buyers;");
			sqlstat.executeUpdate("drop table if exists employees;");
			sqlstat.executeUpdate("drop table if exists farrowing;");
			sqlstat.executeUpdate("drop table if exists inventoryexpensedetails;");
			sqlstat.executeUpdate("drop table if exists inventoryexpenses;");
			sqlstat.executeUpdate("drop table if exists items;");
			sqlstat.executeUpdate("drop table if exists micellaneousexpenses;");
			sqlstat.executeUpdate("drop table if exists pigs;");
			sqlstat.executeUpdate("drop table if exists pigtypes;");
			sqlstat.executeUpdate("drop table if exists salaries;");
			sqlstat.executeUpdate("drop table if exists sellingtypes;");
			sqlstat.executeUpdate("drop table if exists soldalivedetails;");
			sqlstat.executeUpdate("drop table if exists soldbutchereddetails;");
			sqlstat.executeUpdate("drop table if exists soldpigs;");
			
			//create tables		
			sqlstat.executeUpdate("CREATE TABLE \"breeding\" ( " +
										"\"boar_no\" INTEGER NOT NULL, " +
										"\"sow_no\" INTEGER  NOT NULL, " +
										"\"breeding_month\" INTEGER  NOT NULL, " +
										"\"breeding_day\" INTEGER NOT NULL, " +
										"\"breeding_year\" INTEGER NOT NULL, " +
										"\"parity\" INTEGER, " +
										"\"farrowing_month\" INTEGER, " +
										"\"farrowing_day\" INTEGER, " +
										"\"farrowing_year\" INTEGER, " +
										"\"remarks\" TEXT, " +
										"PRIMARY KEY(\"boar_no\", \"sow_no\",  \"breeding_month\", \"breeding_day\", \"breeding_year\"), " +
										"FOREIGN KEY(\"boar_no\") REFERENCES pigs(\"ear_no\"), " +
										"FOREIGN KEY(\"sow_no\") REFERENCES pigs(\"ear_no\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"buyers\" ( " +
										"\"buyer_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
										"\"last_name\" TEXT NOT NULL, " +
										"\"first_name\" TEXT NOT NULL, " +
										"UNIQUE(\"last_name\", \"first_name\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"employees\" ( " +
										"\"employee_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
										"\"last_name\" TEXT NOT NULL, " +
										"\"first_name\" TEXT NOT NULL, " +
										"UNIQUE(\"last_name\", \"first_name\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"farrowing\" ( " +
										"\"sow_no\" INTEGER NOT NULL, " +
										"\"farrowing_month\" INTEGER NOT NULL, " +
										"\"farrowing_day\" INTEGER NOT NULL, " +
										"\"farrowing_year\" INTEGER NOT NULL, " +
										"\"ear_nos_assigned_start\" INTEGER NOT NULL, " +
										"\"ear_nos_assigned_end\" INTEGER NOT NULL, " +
										"\"live_born_no\" INTEGER NOT NULL DEFAULT (0), " +
										"\"still_born_no\" INTEGER NOT NULL DEFAULT (0), " +
										"\"mummified_no\" INTEGER NOT NULL DEFAULT (0), " +
										"\"abnormal_no\" INTEGER NOT NULL DEFAULT (0), " +
										"\"remarks\" TEXT, " +
										"PRIMARY KEY(\"sow_no\", \"farrowing_month\", \"farrowing_day\", \"farrowing_year\"), " +
										"FOREIGN KEY(\"sow_no\") REFERENCES pigs(\"ear_no\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"inventoryexpensedetails\" ( " +
										"\"delivery_receipt_no\" INTEGER NOT NULL, " +
										"\"item_id\" INTEGER NOT NULL, " +
										"\"quantity\" INTEGER NOT NULL, " +
										"\"amount\" REAL NOT NULL, " +
										"PRIMARY KEY(\"delivery_receipt_no\", \"item_id\"), " +
										"FOREIGN KEY(\"delivery_receipt_no\") REFERENCES inventoryexpenses(\"delivery_receipt_no\"), " +
										"FOREIGN KEY(\"item_id\") REFERENCES items(\"item_id\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"inventoryexpenses\" ( " +
										"\"month\" INTEGER NOT NULL, " +
										"\"day\" INTEGER NOT NULL, " +
										"\"year\" INTEGER NOT NULL, " +
										"\"delivery_receipt_no\" INTEGER PRIMARY KEY NOT NULL, " +
										"\"total_amount\" REAL NOT NULL " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"items\" ( " +
										"\"item_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
										"\"item\" TEXT NOT NULL, " +
										"UNIQUE(\"item\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"micellaneousexpenses\" ( " +
										"\"month\" INTEGER NOT NULL, " +
										"\"day\" INTEGER NOT NULL, " +
										"\"year\" INTEGER NOT NULL, " +
										"\"item_id\" INTEGER NOT NULL, " +
										"\"amount\" REAL NOT NULL, " +
										"\"notes\" TEXT, " +
										"PRIMARY KEY(\"item_id\", \"month\", \"day\", \"year\"), " +
										"FOREIGN KEY(\"item_id\") REFERENCES itemes(\"item_id\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"pigs\" ( " +
									    "\"ear_no\" INTEGER PRIMARY KEY NOT NULL, " +
									    "\"pig_id\" INTEGER NOT NULL, " +
									    "\"month_added\" INTEGER NOT NULL, " +
									    "\"day_added\" INTEGER NOT NULL, " +
									    "\"year_added\" INTEGER NOT NULL, " +
									    "\"building_no\" INTEGER DEFAULT (0), " +
									    "\"employee_id\" INTEGER DEFAULT (0), " +
									    "\"month_died\" INTEGER DEFAULT (0), " +
									    "\"day_died\" INTEGER DEFAULT (0), " +
									    "\"year_died\" INTEGER DEFAULT (0), " +
									    "\"cause_of_death_remarks\" TEXT " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"pigtypes\" ( " +
										"\"pig_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
										"\"pig_type\" TEXT NOT NULL, " +
										"UNIQUE(\"pig_type\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"salaries\" ( " +
										"\"employee_id\" INTEGER NOT NULL, " +
										"\"amount\" REAL NOT NULL, " +
										"\"from_month\" INTEGER NOT NULL, " +
										"\"from_day\" INTEGER NOT NULL, " +
										"\"from_year\" INTEGER NOT NULL, " +
										"\"to_month\" INTEGER NOT NULL, " +
										"\"to_day\" INTEGER NOT NULL, " +
										"\"to_year\" INTEGER NOT NULL, " +
										"PRIMARY KEY(\"employee_id\", \"from_month\", \"from_day\", \"from_year\", \"to_month\", \"to_day\", \"to_year\"), " +
										"FOREIGN KEY(\"employee_id\") REFERENCES employees(\"employee_id\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"sellingtypes\" ( " +
										"\"selling_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
										"\"selling_type\" TEXT NOT NULL, " +
										"UNIQUE(\"selling_type\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"soldalivedetails\" ( " +
										"\"buyer_id\" INTEGER NOT NULL, " +
										"\"sold_month\" INTEGER NOT NULL, " +
										"\"sold_day\" INTEGER NOT NULL, " +
										"\"sold_year\" INTEGER NOT NULL, " +
										"\"no_of_heads\" INTEGER NOT NULL, " +
										"\"kilos_less\" INTEGER NOT NULL DEFAULT (0), " +
										"\"ear_nos_sold\" TEXT, " +
										"PRIMARY KEY(\"buyer_id\", \"sold_month\", \"sold_day\", \"sold_year\", \"ear_nos_sold\"), " +
										"FOREIGN KEY(\"buyer_id\") REFERENCES buyers(\"buyer_id\") " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"soldbutchereddetails\" ( " +
										"\"ear_no\" INTEGER PRIMARY KEY NOT NULL, " +
										"\"butchered_month\" INTEGER NOT NULL, " +
										"\"butchered_day\" INTEGER NOT NULL, " +
										"\"butchered_year\" INTEGER NOT NULL, " +
										"\"weight_sold\" REAL NOT NULL " +
									")");
			sqlstat.executeUpdate("CREATE TABLE \"soldpigs\" ( " +
										"\"buyer_id\" INTEGER NOT NULL, " +
										"\"selling_id\" INTEGER NOT NULL, " +
										"\"sold_month\" INTEGER NOT NULL, " +
										"\"sold_day\" INTEGER NOT NULL, " +
										"\"sold_year\" INTEGER NOT NULL, " +
										"\"price_per_kilo\" REAL NOT NULL, " +
										"\"kilos_total_weight_sold\" REAL NOT NULL, " +
										"\"amount\" REAL NOT NULL, " +
										"\"butchered_ear_no\", " +
										"PRIMARY KEY(\"buyer_id\", \"sold_month\", \"sold_day\", \"sold_year\", \"selling_id\", \"amount\"), " +
										"FOREIGN KEY(\"buyer_id\") REFERENCES buyers(\"buyer_id\"), " +
										"FOREIGN KEY(\"selling_id\") REFERENCES sellingtypes(\"selling_id\") " +
									")");
			
			prepPigTypes = sqlconn.prepareStatement("insert into pigtypes values (?, ?);");
			prepSellingTypes = sqlconn.prepareStatement("insert into sellingtypes values (?, ?);");
			prepPigTypes.setInt(1, 1);
			prepPigTypes.setString(2, "boar");
			prepPigTypes.addBatch();
			prepPigTypes.setInt(1, 2);
			prepPigTypes.setString(2, "sow");
			prepPigTypes.addBatch();
			prepPigTypes.setInt(1, 3);
			prepPigTypes.setString(2, "finisher");
			prepPigTypes.addBatch();
			prepSellingTypes.setNull(1, Types.INTEGER);
			prepSellingTypes.setString(2, "sold alive");
			prepSellingTypes.addBatch();
			prepSellingTypes.setNull(1, Types.INTEGER);
			prepSellingTypes.setString(2, "sold butchered");
			prepSellingTypes.addBatch();
			sqlconn.setAutoCommit(false);
			prepPigTypes.executeBatch();
			prepSellingTypes.executeBatch();
			sqlconn.setAutoCommit(true);
					
			//non-testing part
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
			e.printStackTrace();
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
        	e.printStackTrace();
        }
    }
}
