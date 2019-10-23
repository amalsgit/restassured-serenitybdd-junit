package com.serenity.blog.features.posts;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.serenity.blog.commontasks.CommonQuestions;
import com.serenity.blog.posts.BlogPostActions;
import com.serenity.blog.posts.BlogPostQuestions;
import com.serenity.blog.users.BlogUserActions;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetUserPostTests {

  @Steps
  BlogUserActions blogUserActions;

  @Steps
  BlogPostQuestions blogPostQuestions;

  @Steps
  BlogPostActions blogPostActions;

  @Steps
  CommonQuestions commonQuestions;

  @Test
  @Title("Should be able to retrieve user posts")
  public void shouldBeAbleToVerifyUserPosts() {
    String user = "Samantha";

    //Given
    blogUserActions.getUserDetails(user);
    commonQuestions.responseCodeIs(200, lastResponse());

    String userId = String.valueOf(lastResponse().getBody().jsonPath().getInt("[0].id"));

    //When
    blogPostActions.getUserPost(userId);

    //Then
    commonQuestions.responseCodeIs(200, lastResponse());
    List<Object> posts = lastResponse().jsonPath().getList("");
    blogPostQuestions.verifyUserIdInPosts(posts, userId);
  }
}
