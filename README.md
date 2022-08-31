<div align="center">

![Logo](logo.png)

# JStringManipulator
Java Frameworks manipulations by String queries

[![MIT License](https://img.shields.io/github/license/pl3xgaming/Purpur?&logo=github)](LICENSE)

---

</div>

### Feedback

+ **[Discord Server](https://discord.gg/GmT9pUy8af)**
+ **[VKontakte Page](https://vk.com/itzstonlex)**

---

## WHAT IS THIS?

This repository is for manipulating Java objects using `StringQuery`.
In one session, you can perform many types of queries.

**IMPORTANT:**
This repository is a prototype on the way to creating our own programming 
language, taking into account all the structural and caching errors, 
development of our own compiled programming language will be started in 
the near future.

---

## HOW TO USE?

Example Code:
```java
StringManipulatorContext context = new StringManipulatorContext();

StringManipulatorSession session = context.createSession();

StringQuery collectionsQuery =
        session.makeQuery("var @Collection as $collect")
                .next("add 'Misha' 'Leyn' in $collect")
                .next("add 'Egor' in $collect")
                .next("print $collect ]")
                .next("print ]");

session.submit(collectionsQuery.resetOnCommit(false));
session.submit(session.makeQuery("print 'PI:' $PI ]").repeat(3));

session.submit(
        session.makeQuery("var @String as $hello_world")
                .next("set $hello_world = 'Hello' 'World!' ]")
                .next("print $hello_world ]"));

session.submit(
        session.makeQuery("var @Number as $count")
                .next("set $count = 4 ]")
                .next("print 'Queries:' $count ]"));

session.submit(session.makeQuery("print 3 5 6 ]"));

// Commit & Handle session queries response.
StringQueryResponse response = session.commit();

System.out.println("collection values length: " + response.<Collection<?>>var("collect").size());

System.out.println("executed queries: " + response.getExecutedQueries());
System.out.println("flushed queries: " + response.getFlushedQueries());

System.out.println("Response:");
System.out.println(response.getConsoleInput());
```

Example Console Response:
```
collection values length: 2
executed queries: 7
flushed queries: 4
Response:
[Misha Leyn, Egor]

PI: 3.141592653589793
PI: 3.141592653589793
PI: 3.141592653589793
Hello World!
Queries: 4
3 5 6
```

---

## SUPPORT ME

<a href="https://www.buymeacoffee.com/itzstonlex" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>
