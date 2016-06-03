package com.xxx.yyy.system;


public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Initialize
		if (!initialize()) {
			Runtime.getRuntime().halt(1);
		}

		// TODO Process data

		// TODO Save result
	}

	private static boolean initialize() {
		XXXConfiguration instance = XXXConfiguration.getInstance();
		// Load system parameters
		if (!instance.readConfigFile(ConfigurationConstants.CONFIGURATION_FILE)) {
			return false;
		}
		
		// Load full file
		if (!instance.loadFullFile()) {
			return false;
		}
		
		return true;
	}
}
