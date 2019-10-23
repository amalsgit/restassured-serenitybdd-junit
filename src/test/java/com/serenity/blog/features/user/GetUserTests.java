package com.serenity.blog.features.user;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.serenity.blog.commontasks.CommonQuestions;
import com.serenity.blog.users.BlogUserActions;
import com.serenity.blog.users.BlogUserQuestions;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetUserTests {

  @Steps
  BlogUserActions blogUserActions;

  @Steps
  CommonQuestions commonQuestions;

  @Steps
  BlogUserQuestions blogUserQuestions;

  @Test
  @Title("Should be able to get blog user details")
  public void getBlogUserDetails() {
    String userName = "Samantha";

    //When
    blogUserActions.getUserDetails(userName);

    //Then
    commonQuestions.responseCodeIs(200, lastResponse());
    blogUserQuestions.validateUserName(userName, lastResponse());
  }

  @Test
  @Title("\"Get user details\" response schema should match with specification")
  public void checkGetBlogUserSchema() {
    String userName = "Samantha";
    //When
    blogUserActions.getUserDetails(userName);

    //Then
    commonQuestions.responseCodeIs(200, lastResponse());
    commonQuestions.verifyResponseSchema(lastResponse(), "user_details.json");
  }
}
