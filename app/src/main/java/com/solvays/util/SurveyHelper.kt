package com.solvays.util

import com.quickbirdstudios.surveykit.AnswerFormat
import com.quickbirdstudios.surveykit.TextChoice
import com.quickbirdstudios.surveykit.steps.QuestionStep
import com.quickbirdstudios.surveykit.steps.Step
import com.solvays.model.Survey

class SurveyHelper {
    companion object {
        fun generateSurveyQuestionSteps(appName: String, numQuestions: Int): List<Step> {
            val questions = listOf(
                "How easy was it to navigate and use the $appName web application?",
                "Did you find all the features and functionalities that you were looking for in $appName?",
                "Was the user interface of $appName visually appealing and user-friendly?",
                "Could you describe any technical issues while using the $appName web application?",
                "How likely are you to recommend the $appName web application to others?",
                "Any other general comments you would like to add?",
                "How satisfied are you with the speed of $appName?",
                "Were the instructions provided within $appName clear and easy to understand?",
                "Were you able to complete your tasks with $appName efficiently?",
                "How would you rate the quality of the customer support provided by $appName?",
                "Were you able to easily access the information you needed on $appName?",
                "How likely are you to continue using $appName in the future?",
                "How does $appName compare to other similar web applications you have used?",
                "Did you experience any errors or glitches while using $appName?",
                "How well does $appName meet your needs and expectations? Add any comments below",
                "How satisfied are you with the search functionality within $appName?",
                "Did $appName provide enough customization options for your needs? Could you describe any missing options?",
                "How would you rate the overall performance of $appName?",
                "Were the error messages within $appName helpful in resolving any issues?"
            )

            return questions.shuffled().take(numQuestions)
                .mapIndexed { index: Int, question: String ->
                    QuestionStep(
                        title = "Question ${index + 1} of $numQuestions",
                        text = question,
                        answerFormat = getAnswerFormat(question)
                    )
                }.toList()
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

        fun getDemoAppNames(): List<Survey> {
            return listOf(
                "CryptoHive",
                "BitVault",
                "BlockBridge",
                "DigitalLedger",
                "CoinFuse",
                "CryptoNexus",
                "BlockWave",
                "TokenForge",
                "BitBloom",
                "ChainHaven"
            ).map {
                Survey(it)
            }
        }
    }
}