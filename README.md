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
      - [Features](#features)
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
#### WDM

One of the greatest and simultaneously the most annoying thing about Google Chrome is the frequency at which it is updated. Great because there are always fixes and improvements going out to the chromedriver. Annoying because the chromedriver has to be updated seperately from the Chrome browser and the versions of both have to match or else selenium will not be able to open the browser. Now, in my test suites, the chromedriver lives inside the project.

```
automation-base
├── README.md
├── build.gradle
├── .gitignore
├── gradle.properties
└── externalLibraries
  └── browsers
    ├── LICENSE.chromedriver
    ├── chromedriver
    └── chromedriver.zip
```

This is to eliminate the extra step of having to go download and place it upon installing the project. Which is something that can be confusing for nontechincal teammembers who want to run the automated tests as well as for automatic deployment and execution as a part of the CI/CD process. To solve this issue, I created the wdm (Webdriver Manager). This tool checks the version of Google Chrome that is presently installed on the local machine, searches the chromedriver repository for the matching download. Then downloads the file to the appropriate project directory and unzips the executable for use.

All one has to do to use this tool (whether human or an automated CI/CD script) is to use the following gradle tasks in the terminal...

```bash
gradle cleanupChrome
gradle setupChrome
```

#### Test Reports

Ah, yes. The most important of all thing Quality Assurance testing, the documentation...

This project also includes tools to generate thread-safe html test reports for each and every test that runs. That document the:

1. Step name
2. Brief description of the result of the action being taken
3. The status of the step
4. The exact time the step was taken
5. And a link to screenshots of the pass or fail validation

This information is all printed live to a pretty html document that looks something like this

<a href="url"><img src="https://github.com/ashleyhuey77/automation-base/blob/master/resources/reportContent/Screen%20Shot%202023-03-11%20at%206.49.52%20AM.png" align="center" height="341" width="765" ></a>

All of this is built directly into helper tools meant specifically for documenting to the report. Most of these helper tools are even built into the actions being taken themselves to make writing new scripts quicker and to reduce the likelihood of the validation aspect getting forgotten on accident.

### Features

#### Retry

Bad automated tests are flaky. They can break for reasons unrelated to an actual failure and waste everyone's time and energy trying to figure out whether or not there is an actual issue or not. I have built in multiple forms of retry functionality into my test suites to help reduce this brittleness.

The first is an actual retry function. It is wrapped around actions like click to reduce the brittleness of the click functionality.

```java
public IClick click(Via type) throws TestException {
  TestOperation test = new RetryClick(type,
    new SeleniumException(ErrorCode.CLICK),
    new SeleniumException(ErrorCode.FIND_ELEMENT)
  );
  final Retry retry = new Retry(
    test,
    3,  //3 attempts
    100, //100 ms delay between attempts
    e -> SeleniumException.class.isAssignableFrom(e.getClass())
  );
  op = retry;
  return (IClick) op.perform();
}
```
The next retry is at the test level. In real life – if a bug is found – a manual tester is supposed to repeat the retest the scenario to verify that the issue is repeatable. The same is true for my tests. If the issue isn't repeatable, it's likely not something worth reporting (because hey, weird stuff happens all the time that we can't account for, ex: network issues). And if my tests are running as a part of the CI/CD process, I don't want one of these weird unproducable issues to fail the entire test suite and prevent code from moving to the next stage in the pipeline. So we make the test retry 2-3 times to make sure the issue is due to an actual problem worth investigating further.

This is done through use of a listener that polls the state of the executing test that reexecutes a test if it happens to fail.


```java
public class RetryAnalyzer implements IRetryAnalyzer {
  int counter = 0;
  int retryLimit = 3;
	
  @Override
  public boolean retry(ITestResult result) {
    if(counter < retryLimit) {
      counter++;
      return true;
    }
    return false;
   }
  }
  
public class AnnotationTransformer implements IAnnotationTransformer {

  @SuppressWarnings("rawtypes")
  @Override
  public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
    annotation.setRetryAnalyzer(RetryAnalyzer.class);
  }
}
```
#### Unit Tests

I know, I know. You're probably thinking "Ash, why in the world are you writing unit tests for a test suite? That's ridiculous." But, I'm here to tell you that it is absolutely necessary (specifically in this base library that is shared among many different test projects that have hundreds of different tests that rely on proper working base functionality). I guess in this instance, the tests are the customer and the base project is the product that is being supplied (or some weird metaphor similar to that).

At the end of the day, the automation-base project is code. It can break, it can have bugs in it, and adding it as a dependency to another project can have disaterous effects if one is not careful. I'm in the business of quality code, my friend. And no matter how good of a java engineer I may think I am, I'm not at all ashamed to admit that these unit tests have saved my butt on more than one occassion.

Example of a unit test written for the Datepicker tool I created


```java
@Test
public void verifyGetFutureDate() throws TestException {
  //Arrange
  DatePicker picker = new FutureDate();
  DateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
  Date now = new Date();
  String date = formatDate.format(now);

  //Act
  String timeStamp = picker.getDate("dd-MM-yyyy", 1);
  new PastDate().getDate("MMddyyyy", 1);

  //Assert
  Assert.assertNotEquals(timeStamp, date);
}
```

## Acknowledgement

| [<a href="url"><img src="https://github.com/ashleyhuey77/automation-base/blob/master/resources/reportContent/IMG_4924.JPG" align="center" height="270" width="265" ></a><br>Ash Huey][contributor-one-link] |
| :--------------------------------------------------------------------------: |

That's me. I love talking automation. So please, contact me at ashleyhuey77@gmail.com if you are interested in learning more.
