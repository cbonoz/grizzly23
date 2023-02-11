package com.solvays.util

import com.quickbirdstudios.surveykit.TextChoice

class SurveyHelper {
    companion object {
        fun generateSurveyQuestions(appName: String): List<String> {
            return listOf(
                "How easy was it to navigate and use the $appName web application?",
                "Did you find all the features and functionalities that you were looking for in $appName?",
                "Was the user interface of $appName visually appealing and user-friendly?",
                "Did you experience any technical issues while using the $appName web application?",
                "How likely are you to recommend the $appName web application to others?"
            )
        }

        fun getAgreeChoices(): List<TextChoice> {
            return listOf(
                "Strongly Agree",
                "Agree",
                "Neutral",
                "Disagree",
                "Strongly Disagree"
            ).map {
                TextChoice(it, it.lowercase())
            }
        }
    }
}