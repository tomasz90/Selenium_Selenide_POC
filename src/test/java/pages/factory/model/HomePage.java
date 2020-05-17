package pages.factory.model;

import com.codeborne.selenide.Condition;

public interface HomePage {

     void navigate();

     void prepareCleanState();

     void searchForDev(String tech, String location);

     void searchForDevInCategory(String category);

     void signIn(String email, String password);

     void signInWithoutUI();

     void signOut();

     void userProfileIconIs(Condition condition);

     void addExpert();
}
