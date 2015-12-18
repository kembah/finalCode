package ch.makery.address.view;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {
	
	@FXML
	private Label incomeLbl = new Label();
	@FXML
	private Label expenseLbl = new Label();
	@FXML
	private Label creditscoreLbl = new Label();
	@FXML
	private Label termLbl = new Label();
	@FXML
	private Label housepriceLbl = new Label();
	@FXML
	private Label mortgagelbl = new Label();
	@FXML
	private Label altPaymentLabel = new Label();
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpense;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txthousePrice;
	@FXML
	private ComboBox<String> comboTerm;
	@FXML
	private Button Button;
	


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void Mortgagecalculation() {
    	mortgagelbl.setVisible(false);
    	Double income = Double.parseDouble(this.txtIncome.getText());
    	Double monthlyExpense = Double.parseDouble(this.txtExpense.getText());
    	int cdtScore = Integer.parseInt(this.txtCreditScore.getText());
    	Double housePrice = Double.parseDouble(this.txthousePrice.getText());
    	Double term = Double.parseDouble(this.comboTerm.getValue().toString());
    	Double interestRate = RateDAL.getRate(cdtScore);
    	
    	int morgatageLoan = 0;
		int years = 0;
		double morgatage = ch.makery.address.model.Rate.getPayment(cdtScore, morgatageLoan, years);
    	
    	if(morgatage <= income * 0.36 && morgatage <= (income + (monthlyExpense * 2)) * 0.28) {
    		DecimalFormat decimalValue = new DecimalFormat("#.##");
    		String Mortgage = decimalValue.format(morgatage);
    		this.mortgagelbl.setText("You can afford this house");
    		mortgagelbl.setVisible(true);
    		this.mortgagelbl.setText("Your monthly payment is" + Mortgage);
    	} else {
    		this.mortgagelbl.setText("You cannot afford this house");
    		System.out.println("Monthly House Payment = " + morgatage);
    		System.out.println("36% of your morgatage is " + income * 0.36);
    		System.out.println("(Income + Expenses) * 28% = " + (income + (monthlyExpense * 2)) * 0.18);
    	}
 
   }
   
} 
   
