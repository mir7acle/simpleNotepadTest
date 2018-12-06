$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("homePageScenarion.feature");
formatter.feature({
  "line": 1,
  "name": "2. Home page with three Arrivals only",
  "description": "",
  "id": "2.-home-page-with-three-arrivals-only",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 4,
  "name": "Home page with three Arrivals only",
  "description": "",
  "id": "2.-home-page-with-three-arrivals-only;home-page-with-three-arrivals-only",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 3,
      "name": "@ui"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "browser \"chrome\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "website loaded this address: \"http://practice.automationtesting.in/\"",
  "keyword": "And "
});
formatter.step({
  "line": 7,
  "name": "click on Shop Menu",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "click on Home menu button",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "the Home page must contains only three Arrivals",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "chrome",
      "offset": 9
    }
  ],
  "location": "MyStepdefs.browser(String)"
});
formatter.result({
  "duration": 8239371228,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "http://practice.automationtesting.in/",
      "offset": 30
    }
  ],
  "location": "MyStepdefs.websiteLoadedThisAddress(String)"
});
formatter.result({
  "duration": 8831026900,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnShopMenu()"
});
formatter.result({
  "duration": 7667723041,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.clickOnHomeMenuButton()"
});
formatter.result({
  "duration": 5124021521,
  "status": "passed"
});
formatter.match({
  "location": "MyStepdefs.theHomePageMustContainsOnlyThreeArrivals()"
});
formatter.result({
  "duration": 172812164,
  "status": "passed"
});
formatter.after({
  "duration": 1383098012,
  "status": "passed"
});
});