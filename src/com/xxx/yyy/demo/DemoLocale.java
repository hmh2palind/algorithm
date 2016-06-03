package com.xxx.yyy.demo;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * The {@link java.util.Locale} class object represents a specific geographical,
 * political. It is used to make the system relevant and usable for the users
 * from different cultures. It can customize the system for different people of
 * different region, culture and language
 * 
 */
public class DemoLocale {
	public static void main(String[] args) {
		LocaleMethodExample localeMethod = new LocaleMethodExample();
		localeMethod.getInforLocale();
		
		LocaleSensitiveExample localeSensitive = new LocaleSensitiveExample();
		localeSensitive.formatValuesExample();
	}
	
	public void demoLocale() {
		String outputString = new String();
		// Create a Locale objects
		Locale[] thaiLocale = { new Locale("th"), 
				new Locale("th", "TH"),
				new Locale("th", "TH", "TH") };

		for (Locale locale : thaiLocale) {
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			outputString = outputString + locale.toString() + ": ";
			outputString = outputString + nf.format(573.34) + "\n";
		}
		System.out.println(outputString);		
	}
}
class LocaleSensitiveExample { 
	public void formatValuesExample() {

		long number = 5000000L;

		NumberFormat numberFormatDefault = NumberFormat.getInstance();
		println("Number Format using Default Locale: "
				+ numberFormatDefault.format(number));

		NumberFormat numberFormatLocale = NumberFormat.getInstance(Locale.ITALY);
		println("Number Format using ITALY Locale: "
				+ numberFormatLocale.format(number));

		NumberFormat numberFormatDefaultCurrency = NumberFormat.getCurrencyInstance();
		println("Currency Format using Default Locale: "
				+ numberFormatDefaultCurrency.format(number));

		NumberFormat numberFormatLocaleCurrency = NumberFormat.getCurrencyInstance(Locale.ITALY);
		println("Currency Format using ITALY Locale: "
				+ numberFormatLocaleCurrency.format(number));

		Currency currency = Currency.getInstance(Locale.CHINA);
		println(currency.getCurrencyCode());
//		println(currency.getDisplayName() + " (" + currency.getCurrencyCode()
//				+ ") " + currency.getDisplayName());

		Date currentDate = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL,	Locale.GERMAN);
		System.out.println("Date Format in German Locale: "
				+ dateFormat.format(currentDate));
	}
	
	void println(String str) {
		System.out.println(str);
	}
}

class LocaleMethodExample {
	public void getInforLocale() {
		// Getting a default Locale object
		Locale locale = Locale.getDefault();
		println("Default Locale: " + locale);
		
		// Getting all available locales from current instance of the JVM
		Locale[] availableLocale = Locale.getAvailableLocales();
		for (Locale aLocale : availableLocale) {
			println("Name of Locale: " + aLocale.getDisplayName());
			println("Language Code: " + aLocale.getLanguage());
			println("Language Display Name: "+ aLocale.getDisplayLanguage());
			println("Country Code: "+aLocale.getCountry());
			println("Country Display Name: "+aLocale.getDisplayCountry());
			
//			if(!aLocale.getScript().equals("")){
//				println("Script Code: "+aLocale.getScript()+", Script Display Name: "+aLocale.getDisplayScript());
//			}
//			
//			if(!aLocale.getVariant().equals("")){
//			println("Variant Code: "+aLocale.getVariant()+", Variant Display Name: "+aLocale.getDisplayVariant());
//			}
			println("****************************************************************");
		}
	}
	
	void println(String str) {
		System.out.println(str);
	}
}