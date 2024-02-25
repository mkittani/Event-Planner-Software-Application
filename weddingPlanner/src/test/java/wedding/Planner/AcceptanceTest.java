package wedding.Planner;


import org.junit.runner.RunWith;
import io.cucumber.junit. Cucumber; 
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit. CucumberOptions. SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
	features= "MyFeatures",
	plugin = {"summary","html:target/cucumber/report.html"},
	monochrome=true,
	snippets= SnippetType.CAMELCASE,
	glue="wedding.Planner")


public class AcceptanceTest {

	public AcceptanceTest() {
		// TODO Auto-generated constructor stub
	}

}
