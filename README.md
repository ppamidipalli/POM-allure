# Java Cucumber POM Framework with Gradle

GitHub-ready UI automation starter using:
- Java 11
- Gradle
- Selenium WebDriver
- Cucumber
- TestNG
- Page Object Model (POM)
- WebDriverManager
- Allure Reports
- GitHub Actions CI
- Jenkins pipeline support

## Included

- Thread-safe `DriverFactory`
- Environment profiles: `qa`, `uat`, `prod`
- Reusable `WaitUtils` for explicit waits
- Parallel scenario execution with TestNG DataProvider
- Hooks with screenshot capture on failure
- Allure integration for rich reporting
- Cucumber + TestNG runner
- GitHub Actions workflow with GitHub Pages publication for Allure
- Jenkinsfile for Jenkins-based execution

## Project Structure

```text
src
├── test
│   ├── java
│   │   ├── hooks
│   │   ├── pages
│   │   ├── runners
│   │   ├── stepdefinitions
│   │   └── utils
│   └── resources
│       ├── config
│       │   ├── qa.properties
│       │   ├── uat.properties
│       │   └── prod.properties
│       └── features
.github/workflows
Jenkinsfile
```

## Execution examples

### Default
```bash
gradle clean test
```

### Smoke tests in QA
```bash
gradle clean test -Denv=qa -Dcucumber.filter.tags="@smoke"
```

### UAT on Firefox
```bash
gradle clean test -Denv=uat -Dbrowser=firefox -Dheadless=true
```

### Parallel execution with 4 threads
```bash
gradle clean test -Dthreads=4
```

### Dedicated cucumber task
```bash
gradle cucumber -Denv=qa -Dthreads=2
```

## Reports

### Cucumber HTML report
```text
build/reports/cucumber/cucumber-report.html
```

### Allure results
```text
build/allure-results
```

### Generate Allure report
```bash
gradle allureReport
```

### Open Allure report locally
```bash
gradle allureServe
```

## CI/CD

### GitHub Actions
The workflow:
- runs smoke tests on push and PR
- uploads cucumber report, screenshots, and allure results
- publishes an Allure report to GitHub Pages on `main`

### Jenkins
The included `Jenkinsfile` supports:
- environment selection
- browser selection
- tag-based execution
- configurable parallel thread count
- artifact archiving

## Notes

- This sample uses Sauce Demo: `https://www.saucedemo.com/`
- Default execution is CI-friendly and headless.
- `threads` controls TestNG data-provider concurrency.
- If you want a Gradle wrapper, run `gradle wrapper` locally once before pushing.

## Recommended next enhancements

- Retry analyzer for flaky tests
- API test layer for hybrid UI + API automation
- Dockerized Selenium execution
- Separate test data layer and environment secrets strategy
- Cross-browser matrix in GitHub Actions
