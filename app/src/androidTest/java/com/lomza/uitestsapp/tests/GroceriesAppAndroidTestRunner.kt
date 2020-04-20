package com.lomza.uitestsapp.tests

import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberAndroidJUnitRunner

@CucumberOptions(glue = ["com.lomza.uitestsapp.tests"], features = ["features"], tags = ["~@wip"])
class GroceriesAppAndroidTestRunner : CucumberAndroidJUnitRunner()