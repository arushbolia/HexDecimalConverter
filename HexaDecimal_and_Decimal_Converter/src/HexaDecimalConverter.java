//Imported Libraries
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;

public class HexaDecimalConverter {
	
	//Global variables
	private static JTextField outDec;
	private static JTextField tfdec;
	private static JTextField outHex;
	private static JTextField tfhex;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static FileWriter fileWriter;
	
	public static void main(String[] args) {
		
		//Get user name of the current user
		String username = System.getProperty("user.name");
		System.out.println(username);
		storeData(username);
		
		//To get current Date and Time
		Date date = new Date();
        System.out.println(sdf.format(date));
        storeData(sdf.format(date));
		
        //Creating a JFrame window and adding elements
		JFrame frame  = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.setTitle("HexaDecimal Decimal Converter");
		frame.getContentPane().setLayout(null);
		
		JButton btnConvertHtoD = new JButton("Convert");
		JLabel hex = new JLabel("Enter Hexa-decimal Value: ");
		hex.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnConvertHtoD.setBounds(38, 90, 98, 20);
		hex.setBounds(-3, 33, 180, 25);
	
		outDec = new JTextField();
		outDec.setToolTipText("Decimal Value");
		outDec.setEditable(false);
		outDec.setBounds(39, 121, 96, 20);
		
		outDec.setColumns(10);
		
		tfhex = new JTextField();
		tfhex.setToolTipText("Hex Value");
		tfhex.setBounds(38, 59, 98, 20);
		
		JButton btnConvertDtoH = new JButton("Convert");
		btnConvertDtoH.setBounds(244, 90, 98, 20);
		
		tfdec = new JTextField();
		tfdec.setToolTipText("Hex Value");
		tfdec.setBounds(244, 59, 98, 20);
		
		JLabel dec = new JLabel("Enter Decimal Value: ");
		dec.setHorizontalAlignment(SwingConstants.CENTER);
		dec.setBounds(225, 33, 137, 25);
		
		outHex = new JTextField();
		outHex.setToolTipText("Decimal Value");
		outHex.setEditable(false);
		outHex.setColumns(10);
		outHex.setBounds(245, 121, 96, 20);
		
		JLabel lConvert = new JLabel("Converter");
		lConvert.setForeground(new Color(25, 25, 112));
		lConvert.setFont(new Font("Poppins", Font.PLAIN, 16));
		lConvert.setBounds(150, 11, 84, 20);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(147, 157, 89, 23);

		//OnClickListener for the Convert Button
		btnConvertDtoH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String decValue = tfdec.getText();
				verifyDec(decValue);
				if(verifyDec(decValue))
				{
					int decV = Integer.parseInt(decValue) ;
					String hex = Integer.toHexString(decV).toUpperCase();
					outHex.setText(hex);
					System.out.println("D -> H Input: " + decValue + " Output: " + hex);
					storeData("D -> H Input: " + decValue + " Output: " + hex);
				}
				else {
					outHex.setText("Invalid Input");
					System.out.println("D -> H Input: " + decValue + " Output: " + "Invalid inpurt");
					storeData("D -> H Input: " + decValue + " Output: " + "Invalid inpurt");
				}
			}
		});
		
		//OnClickListener for the Convert Button
		btnConvertHtoD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String hexValue = tfhex.getText();
				if(verifyHex(hexValue))
				{
					int decV = Integer.parseInt(hexValue,16);
					outDec.setText(Integer.toString(decV));
					System.out.println("H -> D Input: " + hexValue + " Output: " + Integer.toString(decV));
					storeData("H -> D Input: " + hexValue + " Output: " + Integer.toString(decV));
				}
				else {
					outDec.setText("Invlid input");
					System.out.println("H -> D Input: " + hexValue + " Output: " + "Invalid input");
					storeData("H -> D Input: " + hexValue + " Output: " + "Invalid input");
				}
			}
		});
		
		//OnClickListener for the Exit Button
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("System Terminated");
				storeData("Session Terminated");
				storeData(" ");
				System.exit(0);
			}			
		});
		
		//Instead of Exit button cancel button is pressed. 
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("System Terminated");
				storeData("Session Terminated");
				storeData(" ");
				System.exit(0);
			}
		});
		
		frame.getContentPane().add(btnConvertHtoD);
		frame.getContentPane().add(tfhex);
		frame.getContentPane().add(hex);
		frame.getContentPane().add(btnConvertDtoH);
		frame.getContentPane().add(tfdec);
		frame.getContentPane().add(outHex);
		frame.getContentPane().add(lConvert);
		frame.getContentPane().add(dec);
		frame.getContentPane().add(outDec);
		frame.getContentPane().add(btnExit);
		
		frame.setSize(400, 230);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
	}

	//File Handler to Store data in the text file
	protected static void storeData(String str) {
		try {
			String lineSeparator = System.getProperty("line.separator");
			fileWriter = new FileWriter("out.txt"
					+ "", true);
			fileWriter.write(str + lineSeparator);
			fileWriter.close();
		} catch (IOException e1) {
			System.out.println("Error File Handeling block");
		}
	}

	//Function to verify if the inputed value is hexaDecimal
	protected static boolean verifyHex(String hexValue) {
		char[] stringToCharArray = hexValue. toCharArray();
		for(char x :stringToCharArray)
		{
			if(Character.getNumericValue(x) >= 0 && Character.getNumericValue(x) <= 9 || x >= 'a' && x <= 'f' || x >= 'A' && x <= 'F')
			{
				
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	//Function to verify if the inputed value is Decimal
	protected static boolean verifyDec(String decValue) {
		char[] stringToCharArray = decValue. toCharArray();
		for(char x :stringToCharArray)
		{
			if(Character.getNumericValue(x) >= 0 && Character.getNumericValue(x) <= 9)
			{
				
			}
			else
			{
				return false;
			}
		}
		return true;		
	}
}
