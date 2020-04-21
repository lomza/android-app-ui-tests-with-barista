# Android app UI tests with Barista

![UI tests run](https://github.com/lomza/android-app-ui-tests-with-barista/blob/master/barista_ui_tests.gif?raw=true)

This is a demo Android app with some UI tests, written using Espresso & Barista libraries. The tests cover interaction with RecyclerView, Menu, EditText and Button, alert dialog and browser, as well as the check for opened activity and the content of the view.

Written using Android Studio 4.1 and Gradle 4.1.0

## How to run all tests?
Right click on _androidTest_ package and choose **Run ‘Tests in ‘com’’** or navigate to _GroceriesSteps_ class and press on Play icon on the left of class declaration.

## How to run just some tests?
Navigate to _GroceriesAppAndroidTestRunner_ and remove _~_ from _[“~@wip"]_ and annotate the selected (or new) tests with _@wip_ tag (above _Scenario_ or _Feature_ keyword).


_Image source in the app: http://www.stephanielevinson.com_ 
