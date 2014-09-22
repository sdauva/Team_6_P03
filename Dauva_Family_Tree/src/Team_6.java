import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Family {
	String familyID;
	String husbandID;
	String wifeID;
	String childrensID;
}

// class with a main class
public class Team_6 {
	private static final String FILE1 = "Family-2-14-Sep-2014.ged";// file name
	private static ArrayList<Family> arr = new ArrayList<Family>();// Data
																	// structure
																	// to store
																	// data
	String line2;
	String[] hwname;
	String line1;

	// Method to read and store data
	public void readFile(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName)));
			String line;
			while ((line = br.readLine()) != null) {

				// String[] words = line.split(" ");

				if (line.contains("0") && line.contains("FAM") && line != null) {
					Family f = new Family();
					int count = 0;
					String uniq = "";

					do {
						String[] words = line.split(" ");
						if (count == 0) {
							f.familyID = words[1];
							count++;
						} else if (count == 1 && words[1].equals("HUSB")) {
							f.husbandID = words[2];
							count++;
						} else if (count == 2 && words[1].equals("WIFE")) {
							f.wifeID = words[2];
							count++;
						} else if (words[1].equals("CHIL")) {
							uniq += " " + words[2];
						}
						line = br.readLine();
					} while ((line != null)
							&& (!(line.contains("0") && line.contains("FAM")))
							&& (!line.contains("_CURRENT")));
					f.childrensID = uniq;
					uniq = "";
					arr.add(f);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR: unable to read file " + fileName);
			e.printStackTrace();
		}
	}
// method to display all names
	public void display(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName)));
		while ((line1 = br.readLine()) != null) {
			for (int i = 0; i < arr.size(); i++) {
				String[] w = line1.split(" ");
				String children = arr.get(i).childrensID;
				String[] c = children.split(" ");

				if (arr.get(i).childrensID.contains(w[1])
						|| w[1].equals(arr.get(i).husbandID)
						|| w[1].equals(arr.get(i).wifeID)) {
					line1 = br.readLine();
					String[] name = line1.split(" ");
					for (int z = 0; z < c.length; z++) {
						if (c[z].equals(w[1]))
							System.out.println(name[2] + " id:" + w[1]);
					}
					if (w[1].equals(arr.get(i).husbandID)) {
						System.out.println(name[2] + " id:" + w[1]);
					}
					if (w[1].equals(arr.get(i).wifeID)) {
						System.out.println(name[2] + " id:" + w[1]);
					}
				}

			}
		}
		br.close();
	}
// method to display name of husband and wife
	public void displayHWnames(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(fileName)));
		while ((line2 = br.readLine()) != null) {
			String[] w = line2.split(" ");
			for (int i = 0; i < arr.size(); i++) {
				if (w[1].equals(arr.get(i).husbandID)
						|| w[1].equals(arr.get(i).wifeID)) {
					line2 = br.readLine();
					hwname = line2.split(" ");
					if (w[1].equals(arr.get(i).husbandID)) {
						System.out.println("Husband: " + hwname[2] + ","
								+ " Family ID:" + arr.get(i).familyID);
					}
					if (w[1].equals(arr.get(i).wifeID)) {
						System.out.println("Wife: " + hwname[2] + ","
								+ " Family ID:" + arr.get(i).familyID);
					}
				}
			}
		}
		br.close();
	}
//main method
	public static void main(String[] args) {
		Team_6 gc = new Team_6();
		gc.readFile(FILE1);
		try {
			gc.display(FILE1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			gc.displayHWnames(FILE1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
