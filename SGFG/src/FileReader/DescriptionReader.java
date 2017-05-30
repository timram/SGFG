package FileReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DescriptionReader {
	
	public static ArrayList<String> read(InputStream in) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(in);
		ArrayList<String> description = new ArrayList<String>();
		String row = "";
		final int rowLength = 20;
		while(scanner.hasNext()) {
			String next = scanner.next();
			if(row.length() + next.length() > rowLength) {
				description.add(row);
				row = "";
			}
			row += next + " ";
		}
		description.add(row);
		return description;
	}

}
