package repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.vetprac.VetPracApplication;
import com.example.vetprac.model.MySpringBootDataModel;
import com.example.vetprac.repository.MySpringBootRepository;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = { VetPracApplication.class })
@DataJpaTest
public class RepositoryTest {
	
		public static ExtentReports report; 
		public static ExtentTest test;
		
		@BeforeClass
		public static void beforeClass() {
			report = new ExtentReports("C:\\Users\\Admin\\Desktop\\eclipse-workspace\\VetPrac",true);
			test = report.startTest("test");
		}

		@Autowired
		private TestEntityManager entityManager;
		
		@Autowired
		private MySpringBootRepository myRepo;
		
		@Test
		public void retrieveByIdTest() {
			test.log(LogStatus.INFO, "Start retrieve by ID Test");
			MySpringBootDataModel model1 = new MySpringBootDataModel("Bob", "Space", 50);
			entityManager.persist(model1);
			entityManager.flush();
			if(myRepo.findById(model1.getId()).isPresent()) {
				test.log(LogStatus.PASS, "retrieve by ID test passed");
			}
			else {
				test.log(LogStatus.FAIL, "retrieve by ID test failed");
			}
			assertTrue(myRepo.findById(model1.getId()).isPresent());
		}
		
		@Test
		public void retrieveByNameTest() {
			test.log(LogStatus.INFO, "Start retrieve by name Test");
			MySpringBootDataModel model1 = new MySpringBootDataModel("Tim","Somewhere",3);
			entityManager.persist(model1);
			entityManager.flush();
			if(myRepo.findByName(model1.getName()).getName()=="Tim") {
				test.log(LogStatus.PASS, "retrieve by name test passed");
			}
			else {
				test.log(LogStatus.FAIL, "retrieve by name test failed");
			}
			assertEquals("Tim",myRepo.findByName(model1.getName()).getName());
		}

}

