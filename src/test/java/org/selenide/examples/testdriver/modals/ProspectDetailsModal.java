package org.selenide.examples.testdriver.modals;

import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.selenide.examples.baseelements.*;
import org.selenide.examples.containers.studyeditor.StudyEditor;
import org.selenide.examples.dynamicproxy.BaseMethodsProxy;
import org.selenide.examples.dynamicproxy.SWImplement;
import org.selenide.examples.dynamicproxy.SeleniumWrapper;
import org.selenide.examples.testdriver.pages.ModeratorPage;

import java.util.List;

public class ProspectDetailsModal extends ElementsContainer {
    protected SeleniumWrapper proxy = BaseMethodsProxy.newInstance(new SWImplement());

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal']")
    public Button<StudyEditor> closeBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal']")
    public Button<ModeratorPage> closeModerateBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userInfoModal']/h3")
    public TextProperty userName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userInfoModal']/div")
    public TextProperty userCreated;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userImageModal']")
    public Button userImage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='imageUpload']/label/div")
    public Button imageUpload;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='editProspectDetailsBtn']")
    public Button userEditModalBtn;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userFirstNameModal']/div/div[2]")
    public TextProperty userFirstName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userFirstNameModal']/span/div/div[2]")
    public TextInput userFirstNameInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLastNameModal']/div/div[2]")
    public TextProperty userLastName;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLastNameModal']/span/div/div[2]")
    public TextInput userLastNameInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhoneModal']/div/div[2]")
    public TextProperty userPhone;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhoneModal']/div/span/div/span[1]/div/div[2]")
    public TextInput userPhoneCodeInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userPhoneModal']/div/span/div/span[2]/div/div[2]")
    public TextInput userPhoneInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLocationModal']/div/div[2]")
    public TextProperty userLocation;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLocationModal']/div/span/div/div[2]")
    public TextInput userLocationInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEmailModal']/div/div[2]")
    public TextProperty userEmail;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLanguageModal']/div/div[2]")
    public TextProperty userLanguage;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userLanguageModal']/span/div/div[2]")
    public SelectInput userLanguageInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userGenderModal']/div/div[2]")
    public TextProperty userGender;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userGenderModal']/span/div/div[2]")
    public SelectInput userGenderInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userYearOfBirthModal']/div/div[2]")
    public TextProperty userYearOfBirth;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userYearOfBirthModal']/span/div/div[2]")
    public SelectInput userYearOfBirthInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userRadioSuperstarModal']")
    public Radio radioSuperStar;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userRadioLuxuryModal']")
    public Radio radioLuxury;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userLabelModal']/div/div/div")
    public List<TextProperty> userLabel;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='sendLabel']")
    public TextInput userLabelInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userAdditionalSpoilerModal']")
    public Button userAdditionalSpoiler;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEthnicityModal']/div/div[2]")
    public TextProperty userEthnicity;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEthnicityModal']/div/div[2]")
    public SelectInput userEthnicityInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userMaritalModal']/div/div[2]")
    public TextProperty userMarital;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userMaritalModal']/div/div[2]")
    public SelectInput userMaritalInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userChildrenModal']/div/div[2]")
    public TextProperty userChildren;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userChildrenModal']/div/div[2]")
    public TextInput userChildrenInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userHHIModal']/div/div[2]")
    public TextProperty userHHI;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userHHIModal']/div/div[2]")
    public SelectInput userHHIInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userOccupationModal']/div/div[2]")
    public TextProperty userOccupation;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userOccupationModal']/div/div[2]")
    public TextInput userOccupationInput;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEmploymentModal']/div/div[2]")
    public TextProperty userEmployment;

    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='userEmploymentModal']/div/div[2]")
    public SelectInput userEmploymentInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userAddNoteModal']")
    public Button addNoteBtn;


    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='userSaveBtn']")
    public Button saveBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectModalOneTimePasswordBtn']")
    public Button prospectOneTimePasswordBtn;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectModalPasswordInput']")
    public TextInput prospectOneTimePasswordInput;

    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='prospectModalPasswordSave']")
    public Button prospectOneTimePasswordSave;
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectSuperStar']/div[2]/div/span")
//    private SelenideElement prospectSuperstar;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLuxury']/div[2]/div/span")
//    private SelenideElement prospectLuxury;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsGender']")
//    public TextProperty prospectGender;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsBirthday']")
//    public TextProperty prospectAge;
//
//    @FindBy(how = How.XPATH, xpath = ".//*[@data-e2e='closeModal']")
//    public Button<StudyEditor> closeBtn;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsLocation']")
//    public TextProperty prospectLocation;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsMarital']")
//    public TextProperty prospectMaterialStatus;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsChildren']")
//    public TextProperty prospectChildren;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsEthnicity']")
//    public TextProperty prospectEthnicity;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsHousehold']")
//    public TextProperty prospectHouseholdIncome;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsEducation']")
//    public TextProperty prospectEducation;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsEmployment']")
//    public TextProperty prospectEmploymentStatus;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='demographicsOccupation']")
//    public TextProperty prospectOccupation;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelText']")
//    public List<TextProperty> labelTextLst;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelClear']")
//    public List<Button> labelClearLst;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelNewText']")
//    public TextInput labelNewText;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelAdd']")
//    public Button labelAddBtn;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectLabels']/.//*[@data-e2e='labelCancel']")
//    public Button labelCancelBtn;
//
//    @FindBy(how = How.XPATH, xpath = "//*[@data-e2e='participantDetails']/.//*[@data-e2e='prospectDetailsBtn']")
//    public Button prospectDetailsBtn;

}
