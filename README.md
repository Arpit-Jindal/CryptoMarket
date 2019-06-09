# CryptoMarket 💰 ➡️ 📱

## DESCRIPTION

This is a Digital Currency Application displaying market price listing based on trade volume and market capitalization.


## FEATURES

This Application allows users to view Cryptocurrency prices and monitor their rise and drop.

- User can **Register** and ***create an account***.

- User can **Login** with the credentials used to ***sign up***.

- User can **View Lists of Cryptocurrencies** ***with their prices.***

- For the ***prices user can*** keep track of **Hourly** (1hr), **Daily** (24hr) & **Weekly** (7dy), **Percentage Drop or rise**.

### IMPORTANT TERMS TO TAKE NOTE. 

**Some Basic knowlegde useful to understanding the project or just for fun**

**What is "Market Capitalization" and how is it calculated?**

***Market Capitalization*** is one way to rank the relative size of a cryptocurrency. It's calculated by multiplying the Price by the Circulating Supply.

Market Cap = Price X Circulating Supply.

**What is "Circulating Supply"**

***Circulating Supply*** is the best approximation of the number of coins that are circulating in the market and in the general public's hands.

**What is "Total Supply"**

***Total Supply*** is the total amount of coins in existence right now (minus any coins that have been verifiably burned).

- **What is "Max Supply"**

***Max Supply*** is the best approximation of the maximum amount of coins that will ever exist in the lifetime of the cryptocurrency.

- **Why is the Circulating Supply used in determining the market capitalization instead of Total Supply?**

Coins that are locked, reserved, or not able to be sold on the public market are coins that can't affect the price and thus should not be allowed to affect the market capitalization as well. The method of using the Circulating Supply is analogous to the method of using public float for determining the market capitalization of companies in traditional investing.

## TECHNOLOGIES USED:

1. **[Adobe Photoshop](https://www.adobe.com/products/photoshop.html)**

***Photoshop*** is a **photo editing software**.

Photoshop was really useful in ***creating*** and ***enhancing photos***.

**The Logo** **was designed by photoshop** just as many ***images had to be redesigned to fit in to the application user expirience***. 

2. **[Java](https://java.com/en/download/)**

**source language.**

3. **[Gradle](https://gradle.org/)**

**For dependency management and running tasks.**

4. **[Android SDK](https://developer.android.com/studio/)**

**For providing all the necessary developer tools to build, test, and debug Android applications on Windows, Mac or Linux.**

5. **[Android Build Tools](https://developer.android.com/studio/releases/build-tools)**

**For debugging, building, running and testing an Android applications.**

6. **[Coin Market Cap API](https://coinmarketcap.com/api/documentation/v1/)**

**Coin Market Cap** is a platform which provides fast, reliable and unified data APIs to cryptocurrency markets.

**Coin Market API** was used to get price listings on cryptocurrencies based on trade volume and market capitalization.

For more info about the API check out the API Section below.

7. **[FireBase](https://firebase.google.com/)**

**Was used to authenticate users and register users and ensure user is logged in to run the application**

FireBase is a NoSQL **cloud database**. Data is synced across all clients in realtime, and remains available when your app goes offline. The Firebase Realtime Database is a cloud-hosted database. Data is stored as JSON and synchronized in realtime to every connected client.


## LIBRARIES TO NOTE.

1. **[GSON](https://github.com/google/gson)**

- **GSON was used in the API to convert Java Objects into JSON and back**

- Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a **JSON string** to an **equivalent** **Java object**. Gson can work with arbitrary Java objects including pre-existing objects that you do not have source code of.

2. **[okHttp 3](https://github.com/square/okhttp)**

- OkHttp is a third party library used for sending and receiving HTTP-based network requests.

3. **[Picasso](https://github.com/square/okhttp)**

- Picasso was used to display cryptocurrency logos.

- **Picasso** is an image library for **Android**.It caters to image loading and processing. It simplifies the process of displaying images from external locations

## COIN MARKET CAP API

***Coin Market API*** Provides crypto market data endpoints **market price listing based on trade volume and market capitalization**.

For CryptoByte **Gson components were used to parse JSON into Java objects**.

***Gson*** is an open source **Java library to serialize and deserialize Java objects to JSON**.

The benefit you get with GSON is that ***object mapping can save the time spent writing code***.

Click here to Check out :point_right: **[Coin Market API](https://coinmarketcap.com/api/)**

### API DESCRIPTION

**Ticker**

This endpoint displays ticker data for a specific ***cryptocurrency**. Use the **"id" field** from the ***Listings endpoint*** in the URL.

**Global Data**

This endpoint displays the **global data** found at the top of ***coinmarketcap.com***.

**Limits**

API ***Endpoints*** are update every **5 minutes**.

**Mics**

All **'last_updated'** fields are [unix timestamps](https://en.wikipedia.org/wiki/Unix_time)

