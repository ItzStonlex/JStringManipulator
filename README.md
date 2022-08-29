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

session.execute(collectionsQuery);
session.execute(session.makeQuery("print 'PI:' $PI ]"));

session.execute(
session.makeQuery("var @String as $hello_world")
        .next("set $hello_world = 'Hello' 'World!' ]")
        .next("print $hello_world ]"));

session.execute(
session.makeQuery("var @Number as $count")
        .next("set $count = 4 ]")
        .next("print 'Queries:' $count ]"));

session.execute(session.makeQuery("print 3 5 6 ]"));

// Commit & execute session queries.
session.commit();
```

Example Console Response:
```
[Misha Leyn, Egor]

PI: 3.141592653589793
Hello World!
Queries: 4
3 5 6
```

---

## SUPPORT ME

<a href="https://www.buymeacoffee.com/itzstonlex" target="_blank"><img src="https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;-webkit-box-shadow: 0px 3px 2px 0px rgba(190, 190, 190, 0.5) !important;" ></a>
