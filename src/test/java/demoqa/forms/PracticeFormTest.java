package demoqa.forms;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.PracticeFormPage;
import demoqa.pages.SidePanel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PracticeFormTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver, app.wait).getForms().hideAds();
        new SidePanel(app.driver, app.wait).selectPracticeFormMenu().hideAds();
    }

    @Test
    public void practiceFormPositiveTest() {
        new PracticeFormPage(app.driver, app.wait)
                .enterPersonalData("Beth", "Gibbons", "portishead@gmail.com", "1234567890")
                .selectGender("Female")
                .chooseDateAsString("01 February 1965")
                .chooseDate("01", "February", "1965")
                .enterSubjects(new String[]{null, "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:\\Users\\admin\\IdeaProjects\\AIT_QA_Project\\QA_Lesson_25\\robot.png")
                .enterCurrentAddress("Portishead, Bristol, UK")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }

    @Test
    @Parameters({"firstName","lastName","email","phone"})
    public void practiceFormParameterPositiveTest(String firstName, String lastName, String email, String phone) {
        new PracticeFormPage(app.driver, app.wait)
                .enterPersonalData(firstName, lastName, System.currentTimeMillis()+email, phone)
                .selectGender("Female")
                .chooseDateAsString("04 May 1965")
                .enterSubjects(new String[]{"Math", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:\\Users\\admin\\IdeaProjects\\AIT_QA_Project\\QA_Lesson_25\\robot.png")
                .enterCurrentAddress("Portishead, Bristol, UK")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }

}
