package utils;

public class UFCLP {
	private static double UF_TO_CLP; // Example conversion rate, adjust as needed

	public static int convertUFtoCLP(int priceUF) {
		return (int) (priceUF * UF_TO_CLP);
	}

	public static int convertCLPtoUF(int priceCLP) {
		return (int) (priceCLP / UF_TO_CLP);
	}
}
