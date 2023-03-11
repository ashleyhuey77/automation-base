# automation-base

<!-- Section for your links, references, etc. --->

[//]: # "References"
[logo]: https://github.com/ashleyhuey77/automation-base/blob/master/resources/reportContent/github-header-image.png
[shields-badge]: https://img.shields.io/github/languages/count/ashleyhuey77/automation-base?style=for-the-badge
[shields-badge2]: https://img.shields.io/github/languages/top/ashleyhuey77/automation-base?style=for-the-badge
[shields-badge3]: https://img.shields.io/github/repo-size/ashleyhuey77/automation-base?style=for-the-badge
[shields-badge4]: https://img.shields.io/tokei/lines/github/ashleyhuey77/automation-base?style=for-the-badge
[sample link with url]: https://your-external-link.com
[sample link with reference to a headline]: #automation-base
[sample link to your file in project]: ./your-folder/your-file.txt
[documentation-link]: #
[issue-tracker]: #
[contributor-one-link]: #
[contributor-two-img]: https://via.placeholder.com/150?text=profile+image
[contributor-two-link]: #
[contributor-three-img]: https://via.placeholder.com/150?text=profile+image
[contributor-three-link]: #
[license]: #
[sphinx]: https://www.sphinx-doc.org/en/master/
[mkdocs]: https://www.mkdocs.org/
[gitbook]: https://www.gitbook.com/
[bibtex-wikipedia]: https://en.wikipedia.org/wiki/BibTeX

<!-- Your project's logo --->

![Your project's logo][logo]

<!-- Your badges --->

[![shields.io badge][shields-badge] ![shields.io badge][shields-badge2] ![shields.io badge][shields-badge3] ![shields.io badge][shields-badge4]](https://shields.io)

<!-- One liner about your project --->

automation-base is a base-level project I built to hold all startup and cleanup functionality as well as a variety of tools that assist in automated testing across any test suite that uses it as a dependency.

## Table of Contents

- [Project title](#automation-base)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Information](#information)
    - [Tools Required](#tools-required)
    - [Installation](#installation)
  - [Development](#development)
      - [Test Tools](#test-tools)
      - [Misc Tools](#misc-tools)
  - [Acknowledgement](#acknowledgement)

## Introduction

When one thinks of automated tests, it's easy to shrug it off with a "how hard can it be," and assume that the test project consists only of a bunch of selenium scripts. But the true quality engineer knows from experience that an automated test project requires a whole lot more than that in order to have tests that are reliable, robust, and easy to maintain. And this base project is only a small window into all the tools (made using real life OO principles) that goes into automated testing.

## Information

### Tools Required

- [IntelliJ IDEA](#)
- [Gradle](#)
- [The google chrome browser](#)
- [Java 8 (at least)](#)
- Any operating system of your choice.

### Installation

1. Download the project to your local machine.
2. Open IntelliJ, select File –> Open, and select the project to open it.
3. Under automation-base/resources there is a config.properties file where important settings (such as the OS, browser, environment, and testing endpoint) are set.

## Development

Here are just a few of the cool things that live in automation-base along with examples of how they are used...

### Test Tools

  #### SHelper
  
  The SHelper is a tool that extracts out the selenium libraries and transforms the use of each action into "story-style" methods. The goal of this was to keep the selenium components loosely coupled from the tests themselves, so that in the event of big selenium library updates, any changes could easily be applied to all tests from one location. Another goal (specifically for the "story-style" methods, is to make selenium easier for people who aren't as technical or well-versed in Java to be able to easily use and adjust to the automated test suite.

For example....

Clicking on an element would look like this

```java
SHelper.get().click(Via.SELENIUM).on(Generic.ELEMENT.element());
```

Entering text into an element would look like this

```java
SHelper.get().enter().textInto(Generic.ELEMENT.element(), "user1");
```

To get selenium to wait would look like this

```java
SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
    .to(Condition.CONTAIN)
    .value("Done")
    .forAMaxTimeOf(30))
    .on(Generic.ELEMENT.element());
```
Each and every action is written in a way to form a sentence of the action that is being taken. To turn java code into an everyone language.

#### Page Utils

SHelper is really helpful and easy to use. But it doesn't include any of the reporting and screenshot capabilities that are necessary for proper QA Documentation and debugging. Which lead to the creation of the page utils. They are also written in the same "story-style" way, but also contain waits and reporting to both make the action more robust and to record it in a test report.

For example...

Clicking on an element and recording it to the test report would look like this

```java
click().on(Generic.ELEMENT).start();
```

Entering text into an element and recording it to the test report would look like this

```java
enter().text("Test").into(Generic.ELEMENT).start();
```

Finding a particular element based on text would look like this

```java
List<WebElement> buttons = SHelper.get().element().getListOf(Generic.ELEMENT.element());
WebElement stopButton = find(buttons).that(Condition.EQUAL).text("Stop").get();
```

### Misc Tools

#### Loki

Testing requires test data. And loooooots of it. Sometimes, a very specific value needs to be entered into or verified in a field. But other times, a tester wants to enter something random that still meets length and special character testing requirements. For this, there is Loki. A tool to select a particular value from a subset of test data at random (hence the name "Loki" – the God of mischief and chaos).

All test data is stored in the cloud – via Atlas in a mongoDB database.

<a href="url"><img src="https://github.com/ashleyhuey77/automation-base/blob/master/resources/reportContent/Screen%20Shot%202023-03-10%20at%207.39.36%20PM.png" align="center" height="341" width="765" ></a>

So getting a random name from the db would look like this

```java
String name = DataMapper.person().name();
```


## Acknowledgement

| [<a href="url"><img src="https://github.com/ashleyhuey77/automation-base/blob/master/resources/reportContent/IMG_4924.JPG" align="center" height="270" width="265" ></a><br>Ash Huey][contributor-one-link] |
| :--------------------------------------------------------------------------: |

That's me. I love talking automation. So please, contact me at ashleyhuey77@gmail.com if you are interested in learning more.
