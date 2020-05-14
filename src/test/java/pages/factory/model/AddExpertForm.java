package pages.factory.model;

public interface AddExpertForm {

     void skipSection(String section);

     void fillRequiredBasics(String position, String name);

     void fillRequiredSkills(String skill);

     void share();

     void expertShouldBeShared();
    
}
