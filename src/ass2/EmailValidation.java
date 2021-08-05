package ass2;

import java.util.Scanner;

public class EmailValidation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the email address: ");
		String address = sc.nextLine();
		System.out.print(isValidEmail(address));
		sc.close();
	}

	/**
	 * Check if the input char is alphanumeric (a to z, or A to Z, or 0 to 9)
	 * 
	 * @param c: the char needs to be checked.
	 * @return true if it is alphanumeric. false otherwise.
	 */
	public static boolean isAlphanumeric(char c) {
		if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check if the input char is valid to be used in the prefix of an email
	 * address. It can be alphanumeric, -, ., _
	 * 
	 * @param c: the char needs to be checked
	 * @return true if it can be used for the prefix. false otherwise.
	 */
	public static boolean isValidPrefixChar(char c) {
		if (isAlphanumeric(c) || c == '-' || c == '.' || c == '_') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check if the input char is valid to be used in the first portion of the
	 * domain of an email address. It can be alphanumeric, -, .
	 * 
	 * @param c: the char needs to be checked
	 * @return true if it can be used for the prefix. false otherwise.
	 */
	public static boolean isValidDomainChar(char c) {
		if (isAlphanumeric(c) || c == '-' || c == '.') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the input email address has exactly one @
	 * 
	 * @param address: the possible email address needs to be checked
	 * @return true if it contains exactly one @. false otherwise.
	 */
	public static boolean exactlyOneAt(String address) {
		int num = 0;
		for (int i = 0; i < address.length(); i++) {
			if (address.charAt(i) == '@') {
				num++;
			}
		}
		if (num == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get the prefix of a possible email address. Assume the input address has
	 * exactly one @
	 * 
	 * @param address: input possible email address.
	 * @return: the prefix (the part before @)
	 */
	public static String getPrefix(String address) {
		String[] addSplit = address.split("@");
		return addSplit[0];
	}

	/**
	 * Get the domain of a possible email address. Assume the input address has
	 * exactly one @
	 * 
	 * @param address: input possible email address.
	 * @return: the domain (the part after @)
	 */
	public static String getDomain(String address) {
		String[] addSplit = address.split("@");
		return addSplit[1];
	}

	/**
	 * Check if the input is a valid prefix. It contains at least one character. It
	 * contains only alphanumeric characters, underscores (` '), periods (`.'), and
	 * dashes (`-'). An underscore, a period, or a dash must always be followed by
	 * one or more alphanumeric characters. The first character must be
	 * alphanumeric.
	 * 
	 * @param prefix: possible prefix
	 * @return true if it is valid; false otherwise
	 */
	public static boolean isValidPrefix(String prefix) {
		// check the length
		if (prefix.length() == 0) {
			return false;
		}

		for (int i = 0; i < prefix.length(); i++) {
			char current = prefix.charAt(i);
			// check it is a valid char for prefix
			if (!isValidPrefixChar(current)) {
				return false;
			}
			// the case if it is the first char
			if (i == 0 && !isAlphanumeric(current)) {
				return false;
			}
			// the case for _, ., -
			if ((current == '_' || current == '.' || current == '-') && i != prefix.length() - 1) {
				if (!isAlphanumeric(prefix.charAt(i + 1))) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isValidDomain(String domain) {
		String[] domainSplit = domain.split("\\.");
		// check for the number of portions
		if (domainSplit.length != 2) {
			return false;
		}
		String first = domainSplit[0];
		String second = domainSplit[1];
		// check for the first portion length
		if (first.length() == 0) {
			return false;
		}
		// check the properties for the first portion
		for (int i = 0; i < first.length(); i++) {
			char current = first.charAt(i);
			if (!isValidDomainChar(current)) {
				return false;
			}
			// case for period and dash
			if ((current == '.' || current == '-') && i < first.length() - 1) {
				if (!isAlphanumeric(first.charAt(i + 1))) {
					return false;
				}
			}
		}

		// check for the secon portion length
		if (second.length() < 2) {
			return false;
		}
		// check the properties for the second portion
		for (int i = 0; i < second.length(); i++) {
			if ((second.charAt(i) <= 'a' || second.charAt(i) >= 'z')
					&& (second.charAt(i) <= 'A' || second.charAt(i) >= 'Z')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidEmail(String address) {
		String prefix = getPrefix(address);
		String domain = getDomain(address);
		if (isValidPrefix(prefix) && isValidDomain(domain)) {
			return true;
		}
		return false;
	}
}
