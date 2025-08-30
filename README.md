
# Hefazat's Backend

The present age is the age of technology and in this age of technology people have become more Busy with social media. As now a days those who have a mobile phone are busy with these social media and there is no shortage of these social media in this online world. It requires different accounts to run. And all we know that The humans are social Creatures. It is quite impossible for a human being to remember all the account information. After Some time people almost forget their valuable information and later if they will need those information they can’t recall it back. As a result, they lost their Account. To solve this problem we have introduced this application here you can save your account information and when you need that particular Account’s information you Can easily access to this application and can get back your valuable information. If your device is broken or damaged for any reason then your data will not be lost and you can restore the old data by verifying Your Identity by Re installing the app again.





# 🚀 Spring Boot Backend API

A backend REST API built with **Spring Boot** that provides authentication and CRUD operations for managing resources.  

---

## 📌 Features
- ✅ User authentication & authorization (JWT + Spring Security)  
- ✅ RESTful CRUD API endpoints  
- ✅ Global exception handling  
- ✅ MongoDB integration
- ✅ Deploy on Render.com

---

## 🛠️ Tech Stack
- Java 21 
- Spring Boot 3.5.5 
- Spring Data JPA / MongoDB  
- Spring Security (JWT)  
- Maven  

---

## ⚡ Getting Started

### 1️⃣ Clone the repository
```bash
git clone https://github.com/Shabbir108349/Hefazat-s-Backend
```
### Pull and Run the image from the docker hub on Windows

```bash
docker run -d ^
-p8090:8080 ^
--name hefazat ^
shabbir108349/backendhefazat
```

### Pull and Run the image for Mac
```bash
docker run -d \
-p8090:8080 \
--name hefazat \
shabbir108349/backendhefazat
```

### Check The Health Of the Program

To check that the program  properly build and running successfully.

```bash
GET  https://hefazats-backend.onrender.com/public/health-check
```
As I use Spring security , So the **health-check,signup and login** routes are expose without any authentication . But all the other routes are protected with spring security and jwt.
### Signup 
```bash
POST  https://hefazats-backend.onrender.com/public/signup
```
If you want to **signup** on this application then you have to use the signup url with proper properties like **{
    "username":"shabbir",
    "password":"123"
}** a json on the request body of the url and the username should be unique.

### Login

```bash
POST https://hefazats-backend.onrender.com/public/login
``` 
Now you are a part of this beautiful application , please use the **Login** url and give the proper account information at the form of a json on the request body as like **{
    "username":"shabbir",
    "password":"123"
}**. If you do this properly then  this application will give you a **JWT Token**. This token will valid for **10 Minute**.Then you can hit the other url. I am describing those  on the below.


### Basically this application can store the social media account information(Google,Facebook,Tiktok,Instagram,X,FreeFire e.t.c)

Now I am describing about this platform one by one on the below---

### To add a google account's Information

```bash
POST https://hefazats-backend.onrender.com/google/add-google
```
If You want to hit the URL properly, You have to use the **JWT Token** on the authorization section  to authenticate and also give the proper details that you want to store on the database as like ---

- accountName
- firstname
- lastname
- birthday
- phone
- email

on the request body.

### To update a google account's information

```bash
PUT https://hefazats-backend.onrender.com/google/update-google/{68b0b1ef1acfa0c40fcbb917}
```
To update the information of an existing account, you can use that URL and give the updated value on URL's request body . this method also jwt token to authenticate.

### To Delete a google account's information

```bash
DELETE https://hefazats-backend.onrender.com/google/delete-google/{68b0b1ef1acfa0c40fcbb917}
```
To delete a Google account use that link with the **objectId** of the document. you have to use the **Jwt token** to authenticate.

This is all about  a Google account Now we will talk about a Facebook account. <br>
**The Facebook's operations is totally same as the Google only the difference between them on the URL** I am giving this url on the below---

### To add a facebook account's information

```bash
POST https://hefazats-backend.onrender.com/facebook/add-facebook
```

### To update an existing facebook account's information

```bash
PUT https://hefazats-backend.onrender.com/facebook/update-facebook/{68b0b3881acfa0c40fcbb918}
```

### To delete an facebook account's information

```bash
DELETE https://hefazats-backend.onrender.com/facebook/delete-facebook/{68b0b3881acfa0c40fcbb918}
```


Now we can also see this account information with the help of the below url.<br>
this url also needs the jwt token to bypass the security.

### To see the list of the google account
```bash
GET https://hefazats-backend.onrender.com/user/get-google-list
```
### To see the list of the Facebook account

```bash
GET https://hefazats-backend.onrender.com/user/get-facebook-list
```
you can also update your current application's account information. And it is possible with the help of the given url but you have to be authenticated with the jwt token.

### To update your hefazat's account information
```bash
PUT https://hefazats-backend.onrender.com/user/update-user/{shabbir}
```

### To delete your hefazat's account
```bash
DELETE https://hefazats-backend.onrender.com/user/delete-user/{shabbir}
```

**This is all about my application. I will also integrate features gradually**

***Have a good day and May Allah bless you and your family***





