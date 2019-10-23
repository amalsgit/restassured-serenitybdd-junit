package com.serenity.blog.features.comments;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

import com.serenity.blog.comments.CommentActions;
import com.serenity.blog.comments.CommentQuestions;
import com.serenity.blog.commontasks.CommonQuestions;
import com.serenity.blog.posts.BlogPostActions;
import com.serenity.blog.posts.BlogPostQuestions;
import com.serenity.blog.users.BlogUserActions;
import com.serenity.blog.users.BlogUserQuestions;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetPostCommentsTest {

  @Steps
  BlogUserActions blogUserActions;

  @Steps
  BlogPostActions blogPostActions;

  @Steps
  BlogPostQuestions blogPostQuestions;

  @Steps
  BlogUserQuestions blogUserQuestions;

  @Steps
  CommentActions commentActions;

  @Steps
  CommentQuestions commentQuestions;

  @Steps
  CommonQuestions commonQuestions;

  @Test
  @Title("Should be able to get comments for a post")
  public void getPostComments() {

    //Given
    String user = "Samantha";

    blogUserActions.getUserDetails(user);
    commonQuestions.responseCodeIs(200, lastResponse());
    String userId = blogUserQuestions.getUserId(lastResponse());

    blogPostActions.getUserPost(userId);
    String postId = blogPostQuestions.getPostId(lastResponse());

    //When
    commentActions.getPostComments(postId);
    commonQuestions.responseCodeIs(200, lastResponse());

    //Then
    int commentCount = commentQuestions.getCommentCount(lastResponse());
    commentQuestions.verifyNumberOfComments(commentCount, 5);

  }
}
