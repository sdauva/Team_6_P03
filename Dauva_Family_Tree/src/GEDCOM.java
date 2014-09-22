import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class GEDCOM {
	private static final String FILE = "Family-2-14-Sep-2014.ged";
	private String[] tags = { "INDI", "NAME", "SEX", "BIRT", "DEAT", "FAMC",
			"FAMS", "FAM", "MARR", "HUSB", "WIFE", "CHIL", "DATE", "TRLR",
			"NOTE" };
	private boolean flag = false;

	public void readFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line;
			while ((line = br.readLine()) != null) {
				flag = false;
				System.out.println(line.substring(7));
				String[] words = line.split(" ");
				System.out.println("Level Number: "+words[0]);
				for (int i = 0; i < tags.length; i++) {
					if (line.contains(tags[i])) {
						flag = true;
						System.out.println("Tag: "+tags[i]);
						System.out.println();
						break;
					}
				}
				if (flag == false) {
					System.out.println("Invalid Tag");
					System.out.println();
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR: unable to read file " + fileName);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		GEDCOM gc = new GEDCOM();
		gc.readFile(FILE);
	}

}
