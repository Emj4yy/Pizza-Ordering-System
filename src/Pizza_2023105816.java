import javax.swing.*; // Importing the Swing library for creating graphical user interfaces
import java.awt.*; // Importing the AWT library for layout management and component handling
import java.text.DecimalFormat; // Importing DecimalFormat for formatting decimal numbers
import java.awt.event.ActionEvent; // Importing ActionEvent for handling button click events
import java.awt.event.ActionListener; // Importing ActionListener interface for event handling
import java.util.ArrayList; // Importing ArrayList for dynamic array handling

/**
 * Programmer's Details:
 * Name: Latigo, Mayrielle Joy M.
 * Date Created: January 30, 2025
 * 
 * Problem Description:
	Create a Java Swing application that allows users to customize their regular pizza by selecting 
	additional toppings. The application should calculate the final price based on the chosen toppings 
	and apply tax upon ordering.
 */

public class Pizza_2023105816 {

    // Constants defining the dimensions of images, the base price of the pizza, and the applicable tax rate
    private static final int IMAGE_WIDTH = 80; // Width of the images used in the application
    private static final int IMAGE_HEIGHT = 80; // Height of the images used in the application
    private static final double BASE_PRICE = 100.00; // The base price of the pizza before any toppings are added
    private static final double TAX_RATE = 0.20; // The tax rate applied to the total price of the pizza

    // Variables to track the current price of the pizza and to format decimal values for display
    private static double dPizzaPrice = BASE_PRICE; // Current price of the pizza, initialized to the base price
    private static DecimalFormat dF = new DecimalFormat("###,###.00"); // Formatter for displaying prices in a readable format

    // Labels for displaying the price details of the pizza, tax amount, and total cost
    private static JLabel lblPizzaCost; // Label to display the current price of the pizza
    private static JLabel lblTaxAmount; // Label to display the calculated tax amount
    private static JLabel lblTotalCost; // Label to display the total cost including tax

    // Buttons for order placement and clearing selections made by the user
    private static JButton btnClearSelections; // Button to clear all selected toppings and reset the order
    private static JButton btnPlaceOrder; // Button to finalize the order and calculate the total price
    private static boolean orderPlaced = false; // Add this line near the top of your class

    // Main method to initialize and display the pizza ordering application.
    public static void main(String[] args) {
        // Initialize the main application window (frame) where all components will be displayed
        JFrame frmMain = new JFrame("Personalized Pizza Form"); // Create a new JFrame with the specified title
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the application
        frmMain.setSize(450, 750); // Set the size of the main frame to 450 pixels wide and 750 pixels tall

        // Set the layout manager for the main frame to GridBagLayout for flexible component arrangement
        frmMain.setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object to manage component positioning
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow components to fill the available horizontal space
        gbc.insets = new Insets(5, 5, 5, 5); // Define insets for spacing between components

        // Title Panel
        JPanel pnlTitle = new JPanel(new GridBagLayout()); // Create a panel to hold the title and images, using GridBagLayout for layout management
        gbc.anchor = GridBagConstraints.WEST; // Align components to the west (left) of the panel
        JLabel lblTitleImage = new JLabel(resizeImage("C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/pizzaicon.png")); // Load the title image
        lblTitleImage.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT)); // Set the preferred size for the title image
        gbc.gridx = 0; // Set the column position for the image
        gbc.gridy = 0; // Set the row position for the image
        gbc.gridheight = 3; // Allow the image to span 3 rows in the layout
        pnlTitle.add(lblTitleImage, gbc); // Add the title image to the title panel

        // Title Labels
        gbc.gridx = 1; // Move to the next column for the title text
        gbc.gridy = 0; // Reset to the first row
        gbc.gridheight = 1; // Reset the grid height to 1 for the title text
        gbc.anchor = GridBagConstraints.CENTER; // Center align the title text
        JLabel lblLine1 = new JLabel("-------------------------------------", SwingConstants.CENTER); // Create a decorative line above the title
        lblLine1.setFont(new Font("Arial", Font.BOLD, 18)); // Set the font style for the line
        lblLine1.setForeground(Color.RED); // Set the color of the line to red
        pnlTitle.add(lblLine1, gbc); // Add the decorative line to the title panel

        gbc.gridy = 1; // Move to the next row for the main title
        JLabel lblTitle = new JLabel("Mj's Personalized Pizza"); // Create the main title label
        lblTitle.setFont(new Font("Comic Sans MS", Font.ITALIC, 20)); // Set the font style for the title
        lblTitle.setForeground(Color.RED); // Set the color of the title to red
        pnlTitle.add(lblTitle, gbc); // Add the main title to the title panel

        gbc.gridy = 2; // Move to the next row for the bottom decorative line
        JLabel lblLine2 = new JLabel("-------------------------------------"); // Create a decorative line below the title
        lblLine2.setFont(new Font("Arial", Font.BOLD, 18)); // Set the font style for the line
        lblLine2.setForeground(Color.RED); // Set the color of the line to red
        pnlTitle.add(lblLine2, gbc); // Add the bottom decorative line to the title panel

        gbc.gridy = 3; // Move to the next row for the order form label
        JLabel lblOrderForm = new JLabel("Order Form", SwingConstants.RIGHT); // Create a label for the order form
        lblOrderForm.setFont(new Font("Comic Sans MS", Font.PLAIN, 20)); // Set the font style for the order form label
        lblOrderForm.setForeground(Color.GREEN); // Set the color of the order form label to green
        pnlTitle.add(lblOrderForm, gbc); // Add the order form label to the title panel

        gbc.gridx = 0; // Reset column position for the main frame
        gbc.gridy = 0; // Reset row position for the main frame
        gbc.gridwidth = 2; // Allow the title panel to span 2 columns in the main frame
        frmMain.add(pnlTitle, gbc); // Add the title panel to the main frame

        // Ingredients Panel
        JPanel pnlIngredients = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Create a panel for ingredients with a centered flow layout
        pnlIngredients.add(new JLabel(resizeImage("C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/pizzabase.png"))); // Add the pizza base image to the ingredients panel
        pnlIngredients.add(new JLabel(resizeImage("C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/plus.png"))); // Add a plus image to indicate additional toppings
        pnlIngredients.add(new JLabel(resizeImage("C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/cheese.png"))); // Add the cheese image to the ingredients panel
        pnlIngredients.add(new JLabel(resizeImage("C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/plus.png"))); // Add another plus image for additional toppings
        pnlIngredients.add(new JLabel(resizeImage("C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/ketchup.png"))); // Add the ketchup image to the ingredients panel

        gbc.gridy = 4; // Move to the next row for the ingredients panel
        frmMain.add(pnlIngredients, gbc); // Add the ingredients panel to the main frame

        // Text labels for ingredients
        JPanel pnlIngredientsText = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0)); // Create a panel for ingredient names with centered flow layout
        pnlIngredientsText.add(new JLabel("Pizza Crust")); // Add a label for the pizza crust
        pnlIngredientsText.add(new JLabel("Cheese")); // Add a label for cheese
        pnlIngredientsText.add(new JLabel("Pizza Sauce")); // Add a label for pizza sauce

        gbc.gridy = 5; // Move to the next row for ingredient text labels
        frmMain.add(pnlIngredientsText, gbc); // Add the ingredient text panel to the main frame

        // Separator Line
        JLabel lblSeparator1 = new JLabel("-----------------------------------------------------------------------------------------------------------", SwingConstants.CENTER); // Create a separator line for visual separation
        gbc.gridy = 6; // Move to the next row for the separator line
        frmMain.add(lblSeparator1, gbc); // Add the separator line to the main frame

        // Toppings Panel
        JLabel lblToppings = new JLabel("Toppings Choices", SwingConstants.LEFT); // Create a label for the toppings section
        lblToppings.setFont(new Font("Helvetica", Font.BOLD, 17)); // Set the font style for the toppings label
        lblToppings.setForeground(Color.magenta); // Set the color of the toppings label to magenta

        gbc.gridy = 7; // Move to the next row for the toppings label
        frmMain.add(lblToppings, gbc); // Add the toppings label to the main frame

        JPanel pnlToppings = new JPanel(new GridLayout(2, 5, 3, 3)); // Create a panel for toppings with a grid layout
        String[] arrToppings = { "Pepperoni", "Extra Cheese", "       Ham", "Pineapple", "Onion", "Bacon", "Sausage", "Olives", "Peppers", "Mushroom" }; // Array of topping names
        int[] arrPrices = { 7500, 5500, 6500, 2500, 1500, 3000, 2000, 3000, 2500, 2500 }; // Array of topping prices in cents
        String[] arrImages = { // Array of image paths for each topping
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/pepperoni.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/cheese.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/ham.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/pineapple.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/onion.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/bacon.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/sausage.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/olives.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/bellpeppers.png",
            "C:/Users/Mayrielle/eclipse-workspace/Pizza_2023105816/Images/mushroom.png",
        };

        // Topping options with images, names, and prices
        ArrayList<JCheckBox> chkToppingCheckBoxes = new ArrayList<>(); // List to hold the checkboxes for each topping

        // Loop through each topping to create checkboxes and their corresponding images
        for (int i = 0; i < arrToppings.length; i++) {
            final int index = i; // Capture the current index for use in the action listener
            JPanel pnlToppingItem = new JPanel(); // Create a panel for each topping item
            pnlToppingItem.setLayout(new BoxLayout(pnlToppingItem, BoxLayout.Y_AXIS)); // Set vertical layout for the topping item

            JLabel lblToppingImage = new JLabel(resizeImage(arrImages[i])); // Load the image for the topping
            JLabel lblToppingName = new JLabel(arrToppings[i], SwingConstants.CENTER); // Create a label for the topping name
            JCheckBox chkToppingCheckbox = new JCheckBox(" P " + dF.format(arrPrices[i] / 100.0)); // Create a checkbox for the topping with its price
            chkToppingCheckbox.setForeground(Color.BLUE); // Set the text color of the checkbox to blue

            // Action listener to update the price when a topping is selected or deselected
            chkToppingCheckbox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updatePrice(arrPrices[index], chkToppingCheckbox.isSelected(), lblPizzaCost, lblTaxAmount, lblTotalCost);
                    // Enable Clear button if toppings are selected OR order was placed
                    btnClearSelections.setEnabled(orderPlaced || isAnyToppingSelected(chkToppingCheckBoxes));
                }
            });

            chkToppingCheckBoxes.add(chkToppingCheckbox); // Add the checkbox to the list of topping checkboxes

            pnlToppingItem.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the topping item
            pnlToppingItem.add(lblToppingImage); // Add the topping image to the panel
            pnlToppingItem.add(lblToppingName); // Add the topping name label to the panel
            pnlToppingItem.add(chkToppingCheckbox); // Add the topping checkbox to the panel
            pnlToppings.add(pnlToppingItem); // Add the topping item panel to the toppings panel
        }

        gbc.gridy = 8; // Move to the next row for the toppings panel
        frmMain.add(pnlToppings, gbc); // Add the toppings panel to the main frame

        // Order and Clear Buttons
        JPanel pnlButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5)); // Create a panel for buttons with centered flow layout
        btnPlaceOrder = new JButton("Order Now"); // Create a button to place the order
        btnClearSelections = new JButton("Clear"); // Create a button to clear all selections
        btnClearSelections.setEnabled(false); // Initially disable the clear button since no toppings are selected

     // Action listener for the Order Now button
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate tax and total price
                double tax = dPizzaPrice * TAX_RATE;
                lblTaxAmount.setText("Tax (20%): Php " + dF.format(tax));
                lblTotalCost.setText("Total Price: Php " + dF.format(dPizzaPrice + tax));

                // Make tax and total labels visible to the user
                lblTaxAmount.setVisible(true);
                lblTotalCost.setVisible(true);
                
                btnPlaceOrder.setEnabled(false); // Disable the Order Now button after the order is placed
            }
        });

        // Action listener for the Clear button (resets all selections)
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate tax and total price
                double tax = dPizzaPrice * TAX_RATE;
                lblTaxAmount.setText("Tax (20%): Php: " + dF.format(tax));
                lblTotalCost.setText("Total Price: Php: " + dF.format(dPizzaPrice + tax));

                // Make tax and total labels visible
                lblTaxAmount.setVisible(true);
                lblTotalCost.setVisible(true);
                
                btnPlaceOrder.setEnabled(false); // Disable Order Now
                orderPlaced = true; // Mark order as placed
                btnClearSelections.setEnabled(true); // Enable Clear button
            }
        });
        
        btnClearSelections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Unselect all checkboxes
                for (JCheckBox checkBox : chkToppingCheckBoxes) {
                    checkBox.setSelected(false);
                }

                // Reset price and flags
                dPizzaPrice = BASE_PRICE;
                orderPlaced = false; // Reset order placement flag

                // Update labels
                updatePrice(0, false, lblPizzaCost, lblTaxAmount, lblTotalCost);
                lblTaxAmount.setText("Tax (20%): Php: ");
                lblTotalCost.setText("Total Price: Php: ");

                // Reset buttons
                btnPlaceOrder.setEnabled(true);
                btnClearSelections.setEnabled(false); // Disable Clear after reset
            }
        });

        pnlButtonPanel.add(btnPlaceOrder); // Add the Order Now button to the button panel
        pnlButtonPanel.add(btnClearSelections); // Add the Clear button to the button panel

        gbc.gridy = 9; // Move to the next row for the button panel
        frmMain.add(pnlButtonPanel, gbc); // Add the button panel to the main frame

        // Separator Line 2
        JLabel lblSeparator2 = new JLabel("-----------------------------------------------------------------------------------------------------------", SwingConstants.CENTER); // Create a second separator line for visual separation
        gbc.gridy = 10; 
        frmMain.add(lblSeparator2, gbc); // Add the second separator line to the main frame

        // Order Details Panel
        JPanel pnlOrderDetails = new JPanel(new GridLayout(3, 1)); // Create a panel for displaying order details in a vertical grid layout

        lblPizzaCost = new JLabel("Pizza Price: Php " + dF.format(dPizzaPrice), SwingConstants.LEFT); // Create a label for the pizza price
        lblTaxAmount = new JLabel("Tax (20%): Php ", SwingConstants.LEFT); // Create a label for the tax amount
        lblTotalCost = new JLabel("Total Price: Php ", SwingConstants.LEFT); // Create a label for the total price

        pnlOrderDetails.add(lblPizzaCost); // Add the pizza price label to the order details panel
        pnlOrderDetails.add(lblTaxAmount); // Add the tax label to the order details panel
        pnlOrderDetails.add(lblTotalCost); // Add the total price label to the order details panel

        gbc.gridy = 11; // Move to the next row for the order details panel
        frmMain.add(pnlOrderDetails, gbc); // Add the order details panel to the main frame

        frmMain.setVisible(true); // Make the main frame visible to the user
    }

    // Updates the pizza price based on selected toppings.
    private static void updatePrice(int toppingPrice, boolean isSelected, JLabel lblPizzaCost, JLabel lblTaxAmount, JLabel lblTotalCost) {
        if (isSelected) {
            dPizzaPrice += toppingPrice / 100.0;
        } else {
            dPizzaPrice -= toppingPrice / 100.0;
        }

        // Always update all labels
        lblPizzaCost.setText("Pizza Price: P " + dF.format(dPizzaPrice));
        double tax = dPizzaPrice * TAX_RATE;
        lblTaxAmount.setText("Tax (20%): Php " + dF.format(tax)); // Show the calculated tax
        lblTotalCost.setText("Total Price: Php " + dF.format(dPizzaPrice + tax)); // Show the total price
    }

    // Checks if any topping is selected.
    private static boolean isAnyToppingSelected(ArrayList<JCheckBox> chkToppingCheckBoxes) {
        for (JCheckBox checkBox : chkToppingCheckBoxes) {
            if (checkBox.isSelected()) {
                return true; // Return true if any checkbox is selected, indicating that at least one topping is chosen
            }
        }
        return false; // Return false if no checkboxes are selected, indicating no toppings are chosen
    }

    // Resizes an image to fit the specified dimensions.
    private static ImageIcon resizeImage(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath); // Create an ImageIcon from the specified image path
        Image image = imageIcon.getImage(); // Retrieve the image from the ImageIcon
        Image newImage = image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH); // Resize the image to the defined dimensions
        return new ImageIcon(newImage); // Return the resized image as an ImageIcon
    }
}