package doTweeting;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    protected InputStream inputStream1;
    protected Properties properties;





    // GET ALL Tweet Information
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    public ValidatableResponse getUserTimeTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }



    // Create a Tweet
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    public ValidatableResponse createTextTweet(String tweet)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Retweet a Tweet
    private final String RETWEET_TWEET_ENDPOINT = "statuses/retweet/:id.json";
    public ValidatableResponse retweet(Long tweetId)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl+this.RETWEET_TWEET_ENDPOINT)
                .then();
    }


    // Create a image Tweet
    private final String UPLOAD_USER_TWEET_ENDPOINT = "/media/upload.json";
    public ValidatableResponse createImageTweet(String tweet)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("category", tweet)
                .when().post(this.baseUrl1+this.UPLOAD_USER_TWEET_ENDPOINT)
                .then();
    }



    // Delete a tweet
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }

    private final String SEARCH_TWEETS_ENDPOINT = "/search/tweets.json";
    public ValidatableResponse searchTweets(String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUrl + this.SEARCH_TWEETS_ENDPOINT)
                .then();
    }

    private final String GET_PLACES_NEAR_ENDPOINT = "/geo/reverse_geocode.json";
    public ValidatableResponse getPlacesNear(String atUsername){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUrl+this.GET_PLACES_NEAR_ENDPOINT)
                .then();
    }

    public ValidatableResponse createListTweets(String Titles ){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",Titles )
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }


}
