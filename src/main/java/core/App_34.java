package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class App_34 {

	@Parameter(names = { "-l", "--list" }, variableArity = true, description = "You must type your List")

	static List<String> list = new ArrayList<String>();

	@Parameter(names = "--help", help = true, hidden = true)

	static Boolean help = false;

	public static void main(String[] args) {

		new JCommander(new App_34(), args);

		if (help) {
			new JCommander(new App_34(), args).usage();
			System.exit(0);
		}

		if (list.size() == 0) {
			System.err.println("You must type your List");
		}

		else {
			for (String l : list) {
				System.out.println("List item = " + l);

				WebDriver driver = new FirefoxDriver();

				for (int i = 0; i < list.size(); i++) {
					String param[] = list.get(i).split("\\|");
					String text_case_id = "TC-001.01" + (i + 1);
					String url = param[0];
					String title_expected = param[1];
					driver.get(url);
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					String title_actual = driver.getTitle();

					if (title_expected.equals(title_actual)) {
						System.out.println("Test Case ID: \t\t" + text_case_id);
						System.out.println("URL: \t\t\t" + url);
						System.out.println("Title Expected: \t" + title_expected);
						System.out.println("Title Actual: \t\t" + title_actual);
						System.out.println("Test Case Result: \t" + "PASSED");

					} else {
						System.out.println("Test Case ID: \t\t" + text_case_id);
						System.out.println("URL: \t\t\t" + url);
						System.out.println("Title Expected: \t" + title_expected);
						System.out.println("Title Actual: \t\t" + title_actual);
						System.out.println("Test Case Result: \t" + "FAILED");

					}
					driver.quit();
				}
			}
		}
	}
}
