package com.solvays.util

import com.quickbirdstudios.surveykit.AnswerFormat
import com.quickbirdstudios.surveykit.TextChoice
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.steps.Step

class SurveyHelper {
    companion object {
        fun generateSurveyQuestionSteps(appName: String): List<Step> {
            return listOf(
                "How easy was it to navigate and use the $appName web application?",
                "Did you find all the features and functionalities that you were looking for in $appName?",
                "Was the user interface of $appName visually appealing and user-friendly?",
                "Could you describe any technical issues while using the $appName web application?",
                "How likely are you to recommend the $appName web application to others?",
                "Any other comments you would like to add?"
            ).map {
                QuestionStep(
                    title = "Question",
                    text=it,
                    answerFormat = getAnswerFormat(it)
                )
            }
        }

        private fun getAnswerFormat(question: String): AnswerFormat {
            if (question.contains("describe") || question.contains("comments")) {
                return AnswerFormat.TextAnswerFormat(
                    maxLines = 5,
                    hintText = "Enter your response"
                )
            }

            return AnswerFormat.SingleChoiceAnswerFormat(getAgreeChoices())

        }

        private fun getAgreeChoices(): List<TextChoice> {
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

        fun getDemoAppNames(): List<String> {
            return listOf(
                "CryptoHive",
                "BitVault",
                "BlockBridge",
                "DigitalLedger",
                "CoinFuse"
            )
        }
    }
}