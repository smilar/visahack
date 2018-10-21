package com.visa.money.yodlee;

/*
 *   Copyright 2018 Yodlee, Inc. All Rights Reserved.
 *
 *   This software is the confidential and proprietary information of
 *   Yodlee, Inc. Use is subject to license terms.
 *
 */


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *FastLink lets users add accounts held at their financial institutions.
 *Users can add their accounts by searching a financial institution’s site and entering the login credentials for that particular site.
 *RessionHelper demonstrate how to access fastlink. Below are the 3 API's required to authenticate and access fastlink from yodlee API's.
 *1)CobrandLogin:Uses the /cobrand login API authenticates a cobrand
 *2)UserLogin:Uses /user API allows a registered user to login into the application
 *3)AccessToken: User /user/accessToken API  to get accessToken required to authenticate the fastlink.
 *Simple HTML request is used to post the request for the fastlink to get it invoked.
 *
 */

public class RsessionHelper {

    private String apiUrl;
    private String finappId;

    private String cobrandName;
    private String apiVersion;

    /**
     * API end points
     * 1)Cobrand Login
     * 2)User Login
     * 3)Access Token
     */
    private static String COB_LOGIN_URL = "cobrand/login";
    private static String USER_LOGIN_URL = "user/login";
    private static String GET_TOKEN = "user/accessTokens";


    private String formHtmlContent = "<div class='center processText'>Processing...</div>"
            + "<div>"
            + "<form action='${NODE_URL}' method='post' id='rsessionPost'>"
            + "	RSession : <input type='text' name='rsession' placeholder='rsession' value='${RSESSION}' id='rsession'/><br/>"
            + "	FinappId : <input type='text' name='app' placeholder='FinappId' value='${FINAPP_ID}' id='finappId'/><br/>"
            + "	Redirect : <input type='text' name='redirectReq' placeholder='true/false' value='true'/><br/>"
            + "	Token : <input type='text' name='token' placeholder='token' value='${TOKEN}' id='token'/><br/>"
            + "	Extra Params : <input type='text' name='extraParams' placeholer='Extra Params' value='${EXTRA_PARAMS}' id='extraParams'/><br/>"
            + "</form></div><script>document.getElementById('rsessionPost').submit();</script>";

    /**
     *  To call cobrand login API.
     *
     * @param cobrandLoginValue
     * @param cobrandPasswordValue
     * @return cobSession from cobrand login API response
     */

    public Token loginCobrand(String cobrandLoginValue,
                              String cobrandPasswordValue) {

        DefaultHttpClient httpclient = new DefaultHttpClient();
        String url = this.apiUrl + COB_LOGIN_URL;
        String cobrandSessionToken = null;
        Token token = new Token();
        try {
            PostMethod pm = new PostMethod(url);

            final String requestBody="{"+
                    "\"cobrand\":      {"+
                    "\"cobrandLogin\": "+"\""+cobrandLoginValue+"\""+"," +
                    "\"cobrandPassword\": "+"\""+ cobrandPasswordValue +"\""+"," +
                    "\"locale\": \"en_US\""+
                    "}"+
                    "}";

            StringRequestEntity requestEntity = new StringRequestEntity(
                    requestBody,
                    "application/json",
                    "UTF-8");
            pm.addRequestHeader("Cobrand-Name",this.cobrandName);
            pm.addRequestHeader("Api-Version",this.apiVersion);
            pm.setRequestEntity(requestEntity);

            HttpClient hc = new HttpClient();
            int rc = hc.executeMethod(pm);
            String response = pm.getResponseBodyAsString();
            JSONObject jsonObject = new JSONObject(response);
            if( jsonObject.has("Error") ) {
                ErrorInfo errorInfo = new ErrorInfo("Cobrand Login Failed", (String) jsonObject.get("errorMessage"));
                token.setErrorInfo(errorInfo);
            } else {
                JSONObject cobConvCreds = jsonObject
                        .getJSONObject("session");
                cobrandSessionToken = (String) cobConvCreds.get("cobSession");
                token.setCobrandSessionToken(cobrandSessionToken);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorInfo errorInfo = new ErrorInfo("Cobrand Login Failed", (String) e.getMessage());
            token.setErrorInfo(errorInfo);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return token;
    }

    /**
     * To call user login API
     *
     * @param cobrandSessionToken -required for first layer of authentication to access yodlee API's.
     * @param usernameValue
     * @param passwordValue
     * @return userSession from user login API
     */
    public Token loginUser(String cobrandSessionToken, String usernameValue,
                           String passwordValue) {
        String userSessionToken = null;
        DefaultHttpClient httpclient = new DefaultHttpClient();

        String url = this.apiUrl + USER_LOGIN_URL;
        Token token = new Token();
        token.setCobrandSessionToken(cobrandSessionToken);
        try {
            PostMethod pm = new PostMethod(url);


            String requestBody="{"+"\"user\":{"+
                    "\"loginName\": " +"\""+usernameValue+"\""+","+
                    "\"password\": "+"\""+passwordValue+"\""+","+
                    "\"locale\": \"en_US\""+
                    "}"+
                    "}";

            StringRequestEntity requestEntity = new StringRequestEntity(
                    requestBody,
                    "application/json",
                    "UTF-8");
            pm.addRequestHeader("Authorization", "cobSession="+cobrandSessionToken);
            pm.addRequestHeader("Cobrand-Name",this.cobrandName);
            pm.addRequestHeader("Api-Version",this.apiVersion);
            pm.setRequestEntity(requestEntity);


            HttpClient hc = new HttpClient();
            int rc = hc.executeMethod(pm);
            String source = pm.getResponseBodyAsString();

            String indented = (new JSONObject(source)).toString(4);

            JSONObject jsonObject = new JSONObject(source);
            if( jsonObject.has("errorCode") ) {
                ErrorInfo errorInfo = new ErrorInfo("User Login Failed", (String) jsonObject.get("errorMessage"));
                token.setErrorInfo(errorInfo);
            } else {
                JSONObject userContext = jsonObject.getJSONObject("user");
                JSONObject userConvCreds = userContext
                        .getJSONObject("session");
                userSessionToken = (String) userConvCreds.get("userSession");
                token.setUserSessionToken(userSessionToken);
            }

        } catch (Exception e) {
            ErrorInfo errorInfo = new ErrorInfo("User Login Failed", e.getMessage());
            token.setErrorInfo(errorInfo);
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return token;
    }

    /**
     * To call Get Access Tokens API
     * @param cobrandSessionToken
     * @param userSessionToken
     * @return return token form Get Access Tokens API
     */

    public Token getToken(String cobrandSessionToken, String userSessionToken) {
        String response = null;
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String url = this.apiUrl + GET_TOKEN+"?appIds="+finappId;

        Token token = new Token();
        token.setCobrandSessionToken(cobrandSessionToken);
        token.setUserSessionToken(userSessionToken);
        try {
            GetMethod pm = new GetMethod(url);

            pm.addRequestHeader("Authorization", "cobSession="+cobrandSessionToken+",userSession="+userSessionToken);
            pm.addRequestHeader("Cobrand-Name",this.cobrandName);
            pm.addRequestHeader("Api-Version",this.apiVersion);


            HttpClient hc = new HttpClient();
            int RC = hc.executeMethod(pm);
            String source = pm.getResponseBodyAsString();
            String indented = (new JSONObject(source)).toString(4);
            response = pm.getResponseBodyAsString();
            JSONObject jsonObject = new JSONObject(response);
            if( jsonObject.has("Error") ) {
                ErrorInfo errorInfo = new ErrorInfo("Token Generation Failed", (String) jsonObject.getJSONObject("Error").get("errorDetail"));
                token.setErrorInfo(errorInfo);
            } else {


                JSONObject userToken = jsonObject.getJSONObject("user");
                JSONArray accessToken = userToken
                        .getJSONArray("accessTokens");
                String finappToken = (String) accessToken.getJSONObject(0).getString("value");


                token.setToken(finappToken);
            }
        } catch (Exception e) {
            ErrorInfo errorInfo = new ErrorInfo("Token Generation Failed", e.getMessage());
            token.setErrorInfo(errorInfo);
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return token;
    }

    public static void main(String[] args) {

        String apiUrl = "https://developer.api.yodlee.com/ysl/";
        String nodeUrl = "https://node.developer.yodlee.com/authenticate/restserver/";


        String cobrandLoginValue = "";// Put actual cobrand login name
        String cobrandPasswordValue = "";// put actual cobrand passwrod

        String userName = ""; // Put actual user name
        String password = "" ; // Put actual passwor

        String cobrandName="restserver";
        String apiVersion="1.1";


        String finappId = "10003600";
        String extraParams = "";

        RsessionHelper rsh = new RsessionHelper(apiUrl, finappId,cobrandName,apiVersion);

        Token token = rsh.loginCobrand(cobrandLoginValue, cobrandPasswordValue);
        if( token.getCobrandSessionToken() != null ) {
            token = rsh.loginUser(token.getCobrandSessionToken(),
                    userName, password);
            if( token.getUserSessionToken() != null ) {
                token = rsh.getToken(token.getCobrandSessionToken(), token.getUserSessionToken());
            }
        }
        if( token.getErrorInfo() != null ) {
            System.err.println(token.getErrorInfo().getDescription());
            System.err.println(token.getErrorInfo().getMessage());
        } else if( token.getToken() != null) {
            try {
                String data = rsh.formHtmlContent.replace("${NODE_URL}", nodeUrl).replace("${RSESSION}", token.getUserSessionToken())
                        .replace("${TOKEN}", token.getToken()).replace("${EXTRA_PARAMS}", extraParams).replace("${FINAPP_ID}", finappId);
                File file = new File("post.html");
                if( !file.exists() ) {
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file);
                writer.write(data);
                writer.close();

                String url = "file://"+file.getAbsolutePath().replace("\\", "/");
                //chrome browser

                System.out.println("\nPlease use this url to invoke fastlink:" + url);
                //firefox browser
                Runtime.getRuntime().exec(new String[]{"cmd", "/c","start firefox "+url});

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public RsessionHelper(String apiUrl, String finappId,String cobrandName,String apiVersion) {
        this.apiUrl = apiUrl;
        this.finappId = finappId;
        this.cobrandName=cobrandName;
        this.apiVersion=apiVersion;
    }
}


class Token {

    private String cobrandSessionToken;

    private String userSessionToken;

    private String token;

    private ErrorInfo errorInfo;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getCobrandSessionToken() {
        return cobrandSessionToken;
    }

    public void setCobrandSessionToken(String cobrandSessionToken) {
        this.cobrandSessionToken = cobrandSessionToken;
    }

    public String getUserSessionToken() {
        return userSessionToken;
    }

    public void setUserSessionToken(String userSessionToken) {
        this.userSessionToken = userSessionToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

class ErrorInfo {

    private String message;

    private String description;

    public ErrorInfo(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
