package doTweettest;

import base.RestAPI;
import doTweeting.DataProviders;
import doTweeting.TweetAPIClient;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TweetAPIClientTest extends DataProviders {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }



    @Test (enabled = true)
    public void testUserTimeTweetSuccessfully(){
        String tweet="This is a BootCamp Tweet";
        ValidatableResponse response= this.tweetAPIClient.getUserTimeTweet(tweet);
        System.out.println(response);
    }



    @Test (enabled = true)
    public void testUserPlaceNearTweetSuccessfully(){
        String atUsername="@AribElhacen";
        ValidatableResponse response= this.tweetAPIClient.getPlacesNear(atUsername);
        System.out.println(response);
    }



    @Test (enabled = true)
    public void testUserCanTweetSuccessfully(){
        // user send a tweet
        String tweet="This is a BootCamp Tweet2";
        ValidatableResponse response= this.tweetAPIClient.createTextTweet(tweet);
        // Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test (enabled = true)
    public void testUserCanRetweetSuccessfully(){
        this.tweetAPIClient.retweet(1311174550017761282l);
        Assert.assertNotSame("200", 200);

    }


    @Test (enabled = false)
    public void testUserCanTweetAnImageSuccessfully(){
        // user send an image tweet
        String tweet="../Users/elhacenarib/Desktop/image.png";
        ValidatableResponse response= this.tweetAPIClient.createImageTweet(tweet);
        // Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("image");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // user send a tweet
        String tweet="This is a BootCamp Tweet";
        ValidatableResponse response= this.tweetAPIClient.createTextTweet(tweet);
        // Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
        response= this.tweetAPIClient.createTextTweet(tweet);
        // Verify that the tweet was unsuccessful
        response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    @Test(enabled = false)
    public void testDelete(){
        String tweet="";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1311143861000044544l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test (enabled = true)
    public void testUserCanTweetAnEmptySpaceSuccessfully(){
        // user send a tweet
        String tweet=" ";
        ValidatableResponse response= this.tweetAPIClient.createTextTweet(tweet);
        // Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }




     @Test (enabled = true, dataProvider = "TRY usernames", dataProviderClass = RestAPI.class)
     public void testSearchTweets(String username) {
         String userId = "@" + username;
         ValidatableResponse response = this.tweetAPIClient.searchTweets(userId);
         ResponseBody body = (ResponseBody) response.extract().body();
         System.out.println("Response Body is: " + body.asString());
         String json = response.extract().contentType();
         System.out.println(json);
         int actualResponseCode = response.extract().statusCode();
         int expectedResponseCode = 200;
         Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }


    @Test(dataProvider = "getTestData", dataProviderClass = DataProviders.class)
    public void TestListOfTweets(String Titles){
        tweetAPIClient.createListTweets(Titles );
        System.out.println(Titles +"-------->>>>>>>"+tweetAPIClient.createListTweets(Titles ));
    }

}