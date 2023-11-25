## Multi Data Manager
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white&logoWidth=40)
![Discord](https://img.shields.io/discord/1149050814434914387?color=5865F2&label=Discord&logo=Discord&labelColor=23272a&style=for-the-badge)

### What is Multi Data Manager?
Multi Data Manager is a group of different Functions that makes simple things so easy to manage, Like writing data in json files or in Local Database


### How to Download
It's pretty simple, Just add the libraries and the Classes of the function you wanna use in your project

#### For LocalDatabase
```maven
    <dependency>
      <groupId>org.xerial</groupId>
      <artifactId>sqlite-jdbc</artifactId>
      <version>3.43.2.2</version>
    </dependency>

    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.4.7</version>
    </dependency>
```

#### For JsonWriter or JsonData
```maven
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.10.1</version>
</dependency>
```
Make sure to use the latest versions of the dependencies

### Dependencies
- [x] Gson
- [x] SqLite

### How to Use
Every Instruction of using Multi Data Manager is given below
#### For LocalDatabase
Firstly Import the Database
```java
LocalDatabase db = new LocalDatabase;
```
Then you can simply use it!
```java
db.POST("SQL Command");
db.GET("SQL Command", "key/value");
db.CHECK("SQL Command", "key/value");
```

#### For JsonWriter
Firstly Import the JsonWriter
```java
JsonWriter json = new JsonWriter("path.json");
```

Then you can simply use it!
```java
jsonWriter.write("Array1.Array2.Name", "value");
jsonWriter.get("Array1.Array2.Name");
jsonWriter.set("Array1.Array2.Name", "JoeMama")

jsonWriter.check("Array1");
jsonWriter.check("Array1.Array2");
jsonWriter.check("Array1.Array2.Name");

Object data = jsonWriter.get("Array1.Array2");
jsonWriter.checkArray((JsonArray) data, "value");
```

#### For JsonData
Firstly Import the JsonData
```java
JsonData JData = new JsonData();
```
Then you can simply use it!
```java
JData.ToList("value");
JData.UpdateList("List String", "value");
JData.CheckList("List String", "value")
```