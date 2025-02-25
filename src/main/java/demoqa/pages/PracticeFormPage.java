package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.Month;


public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surName, String email, String number) {
        type(firstName, name);
        type(lastName, surName);
        type(userEmail, email);
        type(userNumber, number);
        System.out.printf("✅ Personal data: [%s], [%s], [%s], [%s]%n", name, surName, email, number);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {

        try {
            String xpathGender = "//label[.='" + gender + "']";
            WebElement genderLocator = driver.findElement(By.xpath(xpathGender));
            click(genderLocator);
            System.out.printf("✅ Gender: [%s]%n", gender);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("⛔ Gender element not found: [" + gender + "]. " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("❌ Error selecting gender: [" + gender + "]. " + e);
        }
        return this;
    }

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    public PracticeFormPage chooseDateAsString(String date) {
        //type(dateOfBirthInput, date);
        click(dateOfBirthInput);

        if (System.getProperty("os.name").contains("Mac")) {
            dateOfBirthInput.sendKeys(Keys.COMMAND, "a");
        } else {
            dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ Date: [%s]%n", date);
        return this;
    }

    public PracticeFormPage chooseDate(String day, String month, String year) {
        int dayInt = Integer.parseInt(day);
        int yearInt = Integer.parseInt(year);
        int monthInt = Month.valueOf(month.toUpperCase()).getValue();

        LocalDate.of(yearInt, monthInt, dayInt);

        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateOfBirthInput")));
        dateInput.click();

        WebElement yearSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("select.react-datepicker__year-select")
        ));
        new Select(yearSelect).selectByValue(year);

        WebElement monthSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("select.react-datepicker__month-select")
        ));
        new Select(monthSelect).selectByIndex(monthInt - 1);

        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//div[contains(@class,'day') and text()='%d']", dayInt))
        ));
       dayElement.click();

        System.out.printf("✅ Дата выбрана: [%s %s %s]%n", day, month, year);
        return this;
    }

@FindBy(id = "subjectsInput")
WebElement subjectsInput;

public PracticeFormPage enterSubjects(String[] subjects) {

    for (String subject : subjects) {
//        type(subjectsInput, subject);
//        subjectsInput.sendKeys(Keys.ENTER);
//        System.out.printf("✅ Subject: [%s]%n", subject);
//    }
//    return this;
        if (subject == null || subject.isEmpty()) continue; // Пропускаем null или пустые строки

        wait.until(ExpectedConditions.elementToBeClickable(subjectsInput)).click(); // Ждем и кликаем
        subjectsInput.sendKeys(subject);
        subjectsInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ Subject: [%s]%n", subject);
    }
    return this;
}

public PracticeFormPage chooseHobbies(String[] hobbies) {
    for (String hobby : hobbies) {
        By hobbyLocator = By.xpath("//label[.='" + hobby + "']");
        WebElement element = driver.findElement(hobbyLocator);
        click(element);
        System.out.printf("✅ Hobby: [%s]%n", hobby);
    }
    return this;
}

@FindBy(id = "uploadPicture")
WebElement uploadPicture;

//    public PracticeFormPage uploadPicture(String imgPath) {
//        uploadPicture.sendKeys(imgPath);
//        System.out.printf("✅ Image: [%s]%n", imgPath);
//        return this;
//    }
public PracticeFormPage uploadPicture(String imgPath) {
    if (imgPath == null || imgPath.isEmpty()) {
        throw new IllegalArgumentException("⛔ File path can't be null or empty");
    }
    uploadPicture.sendKeys(imgPath);
    String uploadedFileName = uploadPicture.getAttribute("value");
    if (!uploadedFileName.contains(imgPath.substring(imgPath.lastIndexOf("\\") + 1))) {
        throw new RuntimeException("❌ Uploaded file name mismatch: expected [" + imgPath + "] but got [" + uploadedFileName + "]");
    }
    System.out.printf("✅ Image uploaded successfully: [%s]%n", imgPath);
    return this;
}

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public PracticeFormPage enterCurrentAddress(String address) {
        type(currentAddress, address);
        System.out.printf("✅ Address: [%s]%n", address);
        return this;
    }
    @FindBy(id = "state")
    WebElement stateContainer;

    @FindBy(id = "react-select-3-input")
    WebElement stateInput;

    public PracticeFormPage enterState(String state) {
        // type(stateContainer, state);
        click(stateContainer);
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ Address state: [%s]%n", state);
        return this;
    }

    @FindBy(id = "city")
    WebElement cityContainer;

    @FindBy(id = "react-select-4-input")
    WebElement cityInput;

    public PracticeFormPage enterCity(String city) {
        click(cityContainer);
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        System.out.printf("✅ Address city: [%s]%n", city);
        return this;
    }

    @FindBy(id = "submit")
    WebElement submitButton;

    public PracticeFormPage submitForm() {
        click(submitButton);
        return this;
    }

    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement registrationModal;

    public PracticeFormPage verifySuccessRegistration(String textToCheck) {
        shouldHaveText(registrationModal, textToCheck, 5000);
        System.out.printf("✅ Registration success: [%s]%n", textToCheck);
        return this;
    }
}
